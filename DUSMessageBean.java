/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.text.DecimalFormat;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.ConnectionFactory;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author donghaiying
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/Queue")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class DUSMessageBean implements MessageListener {

    @PersistenceContext()
    private EntityManager em;
    DriverEntity d;
    RideEntity r;
    @Resource(mappedName = "jms/QueueConnectionFactory")
    private ConnectionFactory queueConnectionFactory;

    public DUSMessageBean() {
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("onMessage()");
        MapMessage msg = null;
        try {
            if (message instanceof MapMessage) {
                msg = (MapMessage) message;
                setUpEntities(msg);
            } else {
                System.out.println("DUSMessageBean.onMessage: "
                        + "Message of wrong type: " + message.getClass().getName());
            }
        } catch (Throwable te) {
            System.out.println("DUSMessageBean.onMessage: Exception: " + te.toString());
        }
    }

    private void setUpEntities(MapMessage msg) {
        int req = 0;
        RideEntity ride = null;

        try {
            System.out.println("0"); // can print

            req = msg.getInt("req");
            System.out.println("req: " + req); // can print
            if (req == 1) {
                String id = msg.getString("id");
                int longtitude = msg.getInt("longtitude");
                int latitude = msg.getInt("latitude");
                System.out.println("id: " + id);
                System.out.println("long: " + longtitude);
                System.out.println("lati: " + latitude);

                d = em.find(DriverEntity.class, id);
                if (d != null) {
                    d.setLatitude(latitude);
                    d.setLongtitude(longtitude);
                    em.flush();
                }
                System.out.println("set up");
            } else {
                String id = msg.getString("id");
                System.out.println("driver id: " + id);
                long endTimeInMillisec = msg.getLong("date");
                System.out.println("endTimeInMillisec: " + endTimeInMillisec);
                Date endTime = new Date(endTimeInMillisec);
                System.out.println("endTime: " + endTime);
                int longtitude = msg.getInt("longtitude");
                int latitude = msg.getInt("latitude");
                System.out.println("long, lat: " + longtitude + ", " + latitude);

                DriverEntity d = findDriver(id);
                System.out.println("found driver");

                for (RideEntity r : d.getRides()) {
                    if (r.getEndDate() == null) {
                        ride = r;
                        System.out.println("found ride: " + ride.getId());
                        break;
                    }
                }
                ride.setEndLatitude(latitude);
                ride.setEndLongtitude(longtitude);
                ride.setEndDate(endTime);

                //Calculate Fee
                long timeInMilliseconds = endTime.getTime() - ride.getStartDate().getTime();
                double timeInMinutes = timeInMilliseconds / 3600.0;
                int latitudeDiff = latitude - ride.getStartLatitude();
                int longtitudeDiff = longtitude - ride.getStartLongtitude();
                double distance = Math.sqrt(Math.pow(latitudeDiff, 2) + Math.pow(longtitudeDiff, 2));
                double rideFee = distance * 1.0 + timeInMinutes * 0.1;
                DecimalFormat d2 = new DecimalFormat("#.##");
                ride.setFee(Double.parseDouble(d2.format(rideFee)));

                //Update Driver
                d.setLatitude(latitude);
                d.setLongtitude(longtitude);
                em.flush();

            }

        } catch (Exception e) {
            System.out.println("DUSMessageBean.setUpEntities: "
                    + "Could not create entities");
        }
    }

    private DriverEntity findDriver(String license) {
        DriverEntity d = new DriverEntity();
        try {
            Query q = em.createQuery("SELECT d FROM DriverEntity AS d WHERE d.id=:license");
            q.setParameter("license", license);
            d = (DriverEntity) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return d;
    }
}
