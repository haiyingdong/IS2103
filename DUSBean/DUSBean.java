/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

/**
 *
 * @author donghaiying
 */
@Stateless
public class DUSBean implements DUSBeanRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Resource(mappedName = "jms/QueueConnectionFactory")
    private ConnectionFactory queueConnectionFactory;
    @Resource(mappedName = "jms/Queue")
    private Queue queue;

    @Override
    public void update(String id, int longtitude, int latitude) {
        System.out.println("update()");
        MapMessage message = null;
        Connection queueConnection = null;
        Session session = null;
        MessageProducer producer = null;
        try {
            queueConnection = queueConnectionFactory.createConnection();
            session = queueConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            queueConnection.start();
            producer = session.createProducer(queue);
            message = session.createMapMessage();
            message.setInt("req", 1);
            message.setString("id", id);
            message.setInt("longtitude", longtitude);
            message.setInt("latitude", latitude);

            producer.send(message);
            System.out.println("Msg sent.");
        } catch (Exception e) {
            System.err.println("RMSServerBean: Exception: " + e.toString());
        } finally {
            if (queueConnection != null) {
                try {
                    queueConnection.close();
                } catch (Exception e) {
                    System.out.println("DUSBean: queueConn.close(): Exception caught: " + e.toString());
                }
            }
        }
    }

    @Override
    public void end(String id, Date date, int longtitude, int latitude) {
        System.out.println("end()");
        MapMessage message = null;
        Connection queueConnection = null;
        Session session = null;
        MessageProducer producer = null;
        try {
            queueConnection = queueConnectionFactory.createConnection();
            session = queueConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            queueConnection.start();
            producer = session.createProducer(queue);
            message = session.createMapMessage();
            message.setInt("req", 2);
            message.setString("id", id);
            message.setLong("date", date.getTime());
            message.setInt("longtitude", longtitude);
            message.setInt("latitude", latitude);
           
            producer.send(message);
            System.out.println("msg sent.");
        } catch (Exception e) {
            System.err.println("RMSServerBean: Exception: " + e.toString());
        } finally {
            if (queueConnection != null) {
                try {
                    queueConnection.close();
                } catch (Exception e) {
                    System.out.println("DUSBean: queueConn.close(): Exception caught: " + e.toString());
                }
            }
        }
    }       
}
