package ejb;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author donghaiying
 */
@Stateless
public class TBSBean implements TBSBeanRemote {

    @PersistenceContext
    private EntityManager em;

    private CarEntity carEntity;
    private DriverEntity driverEntity;
    private FeedbackEntity feedbackEntity;
    private MessageEntity msgEntity;
    private PaymentEntity paymentEntity;
    private RideEntity rideEntity;
    private UsersEntity usersEntity;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    private DecimalFormat df = new DecimalFormat("#.##");

    public TBSBean() {
    }

    @Override
    public String addUser(String userName, String password, String contact, String email, String address) {
        usersEntity = new UsersEntity();
        usersEntity.create(userName, password, contact, email, address);
        if (findUser(userName) == null) {
            em.persist(usersEntity);
            return ("User " + userName + " has been successfully added!");
        }
        return ("Account already exists!");
    }

    public UsersEntity findUser(String userName) {
        System.out.println(userName);
        return em.find(UsersEntity.class, userName);
    }

    @Override
    public Vector getUserData(String userName) {
        System.out.println("getUserData()");
        usersEntity = findUser(userName);
        System.out.println("findUser()");
        Vector userData = new Vector();
        if (usersEntity != null) {
            userData.add(usersEntity.getUserName());
            userData.add(usersEntity.getPassword());
            userData.add(usersEntity.getContact());
            userData.add(usersEntity.getEmail());
            userData.add(usersEntity.getAddress());
            
        }
        return userData;
    }

    @Override
    public void updateUser(String userName, String email, String contact, String address, String pwd) {
        UsersEntity u = new UsersEntity();
        u = findUser(userName);
        u.setEmail(email);
        u.setContact(contact);
        u.setAddress(address);
        u.setPassword(pwd);
        //em.persist(u);
        em.flush();
        em.clear();

    }

    @Override
    public String addCar(String id, String brand, String model, String year) {
        carEntity = new CarEntity();
        carEntity.create(id, brand, model, year);
        if (findCar(id) == null) {
            em.persist(carEntity);
            return ("Car " + id + " has been successfully added!");
        }
        return ("Account already exists!");
    }

    public CarEntity findCar(String id) {
        return em.find(CarEntity.class, id);
    }

    @Override
    public String addDriver(String id, String name, String contact, String address, int longtitude, int latitude, String carId) {
        driverEntity = new DriverEntity();
        driverEntity.create(id, name, contact, address, longtitude, latitude);
        if (findDriver(id) == null) {
            carEntity = findCar(carId);
            if (carEntity == null) {
                return ("Unknown car! Fail to add driver.");
            } else {
                if (carEntity.getDriver() == null) {
                    driverEntity.setCar(carEntity);
                    em.persist(driverEntity);
                    carEntity.setDriver(driverEntity);
                    em.flush();
                    return ("Driver " + id + " has been successfully added!");
                } else {
                    return ("Car is already associated with another driver. Fail to add driver.");
                }
            }

        } else {
            return ("Account already exists!");
        }
    }

    public DriverEntity findDriver(String id) {
        return em.find(DriverEntity.class, id);
    }

    @Override
    public String deleteUser(String userName) {
        usersEntity = findUser(userName);
        if (usersEntity == null) {
            return "User not found!\n";
        }
        Collection<RideEntity> rides = usersEntity.getRides();
        Collection<PaymentEntity> payments = usersEntity.getPayments();
        Collection<FeedbackEntity> feedbacks = usersEntity.getFeedbacks();
        Collection<MessageEntity> msgs = usersEntity.getMessages();
        if (rides.isEmpty() && payments.isEmpty() && feedbacks.isEmpty() && msgs.isEmpty()) {
            em.remove(usersEntity);
            em.flush();
            em.clear();
            return "User is successfully deleted\n";
        } else {
            em.clear();
            return "User cannot be deleted!\n";
        }
    }

    @Override
    public String deleteCar(String id) {
        carEntity = findCar(id);
        if (carEntity == null) {
            return "Car not found!\n";
        }
        DriverEntity driver = carEntity.getDriver();
        if (driver == null) {
            em.remove(carEntity);
            em.flush();
            em.clear();
            return "Car is successfully deleted\n";
        } else {
            em.clear();
            return "Car cannot be deleted!\n";
        }
    }

    @Override
    public String updateDriverHaveRides(String id, String name, String contact, String address) {
        driverEntity = findDriver(id);
        driverEntity.setName(name);
        driverEntity.setContact(contact);
        driverEntity.setAddress(address);
        em.persist(driverEntity);
        em.flush();
        em.clear();
        return "Driver is successfully updated!";
    }

    @Override
    public String updateDriverNoRides(String id, String carId, String name, String contact, String address) {
        driverEntity = findDriver(id);
        if (driverEntity != null) {
            //driverEntity.setId(newId);
            driverEntity.setName(name);
            driverEntity.setContact(contact);
            driverEntity.setAddress(address);

            carEntity = findCar(carId);
            if (carEntity == null) {
                return "no car";
            } else {
                driverEntity.setCar(carEntity);
                carEntity.setDriver(driverEntity);
                em.persist(driverEntity);
                em.persist(carEntity);
                em.flush();
                em.clear();
                return "Driver is successfully updated!";
            }
        } else {
            return "Driver does not exist!";
        }
    }

    @Override
    public String updateDriver(String id) {
        driverEntity = findDriver(id);
        if (driverEntity == null) {
            return "Driver not found!\n";
        }
        if (driverEntity.getRides() == null) {
            return "no rides";
        } else {
            return "have rides";
        }
    }

    @Override
    public String deleteDriver(String id) {
        driverEntity = findDriver(id);
        if (driverEntity == null) {
            return "Driver not found!\n";
        }
        Collection<RideEntity> rides = driverEntity.getRides();
        CarEntity car = driverEntity.getCar();
        if (rides.isEmpty() && car == null) {
            em.remove(driverEntity);
            em.flush();
            em.clear();
            return "Driver is successfully deleted\n";
        } else {
            em.clear();
            return "Driver cannot be deleted!\n";
        }
    }

    @Override
    public String feedbackReport(String id) {
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        int five = 0;
        driverEntity = findDriver(id);
        if (driverEntity == null) {
            return "Driver not found!\n";
        }
        Collection<RideEntity> rides = driverEntity.getRides();
        if (rides.isEmpty()) {
            return "No feedbacks to show!";
        }

        for (RideEntity ride : rides) {
            if (ride.getFeedback() != null) {
                int r = ride.getFeedback().getRatings();
                switch (r) {
                    case 1:
                        one++;
                        break;
                    case 2:
                        two++;
                        break;
                    case 3:
                        three++;
                        break;
                    case 4:
                        four++;
                        break;
                    case 5:
                        five++;
                        break;
                    default:
                        break;
                }
            }
        }
        return "1 star: " + one + "\n2 star: " + two + "\n3 star: " + three + "\n4 star: " + four + "\n5 star: " + five;
    }

    private boolean hasUnfinishedRides(DriverEntity d) {
        Collection<RideEntity> rList = d.getRides();
        for (RideEntity r : rList) {
            if (r.getEndDate() == null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Vector> viewIdleDrivers() {
        //String result = "";
        Query q = em.createQuery("SELECT d FROM DriverEntity d");
        List<Vector> result = new ArrayList();
        for (Object o : q.getResultList()) {
            DriverEntity d = (DriverEntity) o;
            if (d.getRides().isEmpty() || !hasUnfinishedRides(d)) {
                Vector driver = new Vector();
                driver.add(d.getId());
                driver.add(d.getName());
                driver.add(d.getContact());
                driver.add(d.getLatitude());
                driver.add(d.getLongtitude());
                result.add(driver);
            }
        }
        return result;
    }

    @Override
    public List<Vector> performanceReport() {
        System.out.println("performanceReport()");
        List<Vector> driverReport = new ArrayList();
        Query q = em.createQuery("SELECT d from DriverEntity d");
        for (Object obj : q.getResultList()) {
            DriverEntity d = (DriverEntity) obj;
            Collection<RideEntity> allRides = d.getRides();
            Vector report = new Vector();
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_MONTH, 1); //c is the first day of this month
            Date firstDay = new Date(c.getTimeInMillis());
            double totalRevenue = 0;
            int numOfRides = 0;
            double totalRideTime = 0;
            double totalDistance = 0;
            DecimalFormat d2 = new DecimalFormat("#.##");
            for (Object o : allRides) {
                RideEntity r = (RideEntity) o;
                if (r.getEndDate().after(firstDay)) {
                    totalRevenue = totalRevenue + r.getFee();
                    numOfRides++;
                    double rideTime = (r.getEndDate().getTime() - r.getStartDate().getTime()) / 60000.0; //millisec to min
                    totalRideTime = totalRideTime + rideTime;
                    int latitudeDiff = r.getEndLatitude() - r.getStartLatitude();
                    int longitudeDiff = r.getEndLongtitude() - r.getEndLongtitude();
                    double distance = Math.sqrt(Math.pow(latitudeDiff, 2) + Math.pow(longitudeDiff, 2));
                    totalDistance = totalDistance + distance;
                }
            }
            report.add(d2.format(totalRevenue));
            report.add(String.valueOf(numOfRides));
            report.add(d2.format(totalRideTime));
            report.add(d2.format(totalDistance));
            Vector driver = new Vector();
            driver.add(d.getId());
            driver.add(report);
            driverReport.add(driver);
        }

        return driverReport;
    }

    @Override
    public String viewUnpaidRides() {
        String result = "";
        Query q = em.createQuery("SELECT r FROM RideEntity r");
        List<RideEntity> rList = q.getResultList();
        int i = 0;
        for (RideEntity r : rList) {
            if (r.getPayment() == null) {
                i++;
                result = result + i + ". Ride start time: " + r.getStartDate() + ".\n"
                        + "Ride end time: " + r.getEndDate() + ".\n"
                        + "Name: " + r.getUser().getUserName() + ".\n"
                        + "Contact: " + r.getUser().getContact() + ".\n"
                        + "Email: " + r.getUser().getEmail() + ".\n"
                        + "Fee: $" + r.getFee() + ".\n";
            }
        }
        if (result.equals("")) {
            return "No messages to show!\n";
        }
        return result;
    }

    @Override
    public String processMessages() {
        String result = "";
        Query q = em.createQuery("SELECT m FROM MessageEntity m");
        List<MessageEntity> msgList = q.getResultList();
        for (MessageEntity msg : msgList) {
            if (msg.getStatus().equals("unread")) {
                result = result + msg.getMessageId() + ": " + msg.getDate() + ", " + msg.getContent() + ".\n";
            }
        }
        if (result.equals("")) {
            return "No messages to show!\n";
        }
        return result;
    }

    @Override
    public void reviewMsg(Long id, String statusChange, String commentsSet) {
        msgEntity = em.find(MessageEntity.class, id);
        msgEntity.setStatus(statusChange);
        msgEntity.setComment(commentsSet);
        em.persist(msgEntity);
        em.flush();
        em.clear();
    }

    @Override
    public String payments(String userName) {
        String result = "";
        Collection<RideEntity> rList = findUser(userName).getRides();
        int i = 0;
        for (RideEntity r : rList) {
            PaymentEntity p = r.getPayment();
            if (p != null) {
                result = result + i + ". Amount: " + r.getFee() + " | \n"
                        + "Pay Date: " + p.getDate() + " | \n"
                        + "Car (Last 4 Digits): " + p.getNumber();
            }
        }
        if (result == null) {
            return "No payments to show!";
        }
        return result;
    }

    @Override
    public String feedback(String rideId, String userName, int ratings, String comment) {
        RideEntity r = em.find(RideEntity.class, Long.parseLong(rideId));
        if (r.getPayment() == null) {
            return "You need to pay for this ride first!";
        }
        Date d = new Date();
        UsersEntity u = findUser(userName);
        FeedbackEntity f = new FeedbackEntity();
        f.create(d, ratings, comment);
        Collection<FeedbackEntity> fList = u.getFeedbacks();
        fList.add(f);
        r.setFeedback(f);
        //em.persist(u);
        //em.persist(r);
        em.persist(f);
        em.flush();
        return "Feedback is submitted!";
    }

    /*@Override
    public String paidRides(String userName) {
        String result = "";
        Collection <RideEntity> rList = findUser(userName).getRides();
        for (RideEntity r : rList) {
            if (r.getPayment() != null)
            result = result + r.getId() + ". Start:\n" +
                    "Time: " + r.getStartDate()+ "\n" +
                    "Longtitude: " + r.getStartLongtitude() + "\n" +
                    "Latitude: " + r.getStartLatitude() + "\n" + "End:\n" +
                    "Time: " + r.getEndDate()+ "\n" +
                    "Longtitude: " + r.getEndLongtitude() + "\n" +
                    "Latitude: " + r.getEndLatitude();
        }
        if (result == null) return "No paid rides to show!";
        return result;
    }*/
    //get all rides
    @Override
    public String getRides(String userName) {
        //return "1";
        String result = "";
        Collection<RideEntity> rList = findUser(userName).getRides();
        /*if (rList.isEmpty()) {    
            return "No rides to show!";           
        }*/
        for (RideEntity r : rList) {
            result = result + "Ride Id: " + r.getId() + "<br>"
                    + "Driver: " + r.getDriver().getName() + "    Contact: " + r.getDriver().getContact() + "<br>"
                    + "Start:<br>"
                    + "Time: " + r.getStartDate() + "<br>"
                    + "Longtitude: " + r.getStartLongtitude() + "<br>"
                    + "Latitude: " + r.getStartLatitude();
            if (r.getEndDate() != null) {
                result = result + "<br>" + "End:<br>"
                        + "Time: " + r.getEndDate() + "<br>"
                        + "Longtitude: " + r.getEndLongtitude() + "<br>"
                        + "Latitude: " + r.getEndLatitude() + "<br>"
                        + "Fee: " + r.getFee();
            }
            if (r.getFeedback() != null) {
                result = result + "<br>Your Feedback: " + "<br>"
                        + "Ratings: " + r.getFeedback().getRatings() + " star" + "<br>"
                        + "Comment: " + r.getFeedback().getComment() + "<br>";
            }
            result += "<br><br>";

        }
        if (result == null) {
            return "No rides to show!";
        }
        return result;
    }

    @Override
    public String pay(String rideId, String userName,
            String type, long number, String name,
            double value) {

        RideEntity r = em.find(RideEntity.class, Long.parseLong(rideId));
        if (r.getPayment() != null) {
            return "You have already paid for this ride!";
        }
        if (r.getFee() == value) {
            Date d = new Date();
            UsersEntity u = findUser(userName);
            PaymentEntity p = new PaymentEntity();
            p.create(d, type, number, name);

            Collection<PaymentEntity> pList = u.getPayments();
            pList.add(p);
            u.setPayments(pList);
            r.setPayment(p);
            em.persist(u);
            em.persist(r);
            em.persist(p);
            return "Payment is successful!";
        } else {
            return "Payment is rejected! Please pay the exact value.";
        }
    }

    @Override
    public boolean hasUnpaidRide(String username
    ) {
        usersEntity = em.find(UsersEntity.class, username);
        if (!usersEntity.getRides().isEmpty()) {
            Collection allRides = usersEntity.getRides();
            for (Object o : allRides) {
                RideEntity r = (RideEntity) o;
                if (r.getFee() != 0 && r.getPayment() == null) {
                    //Ride is not paid
                    return true;
                }
            }
        }
        return false;
    }

    private String findNearestDriver(int latitude, int longitude) {
        //String result = "";
        double distance = 100 * (Math.sqrt(2));
        String id = null;
        List<Vector> idleDrivers = viewIdleDrivers();
        for (Object o : idleDrivers) {
            Vector driver = (Vector) o;
            double diff1 = (int) driver.get(3) - latitude;
            double diff2 = (int) driver.get(4) - longitude;
            double dis = Math.sqrt(Math.pow(diff1, 2) + Math.pow(diff2, 2));
            if (dis < distance) {
                distance = dis;
                id = (String) driver.get(0);
            }
        }

        return id;

        /*result = "Booking Successful! Your driver, " + driverEntity.getName()
                + " is on the way. Taxi car number: " + driverEntity.getCar().getId()
                + ". Contact: " + driverEntity.getContact();

        return result;
        for (Object obj : idleDrivers) {
            Vector driver = (Vector) obj;
            if (((String) driver.get(0)).equals(license)) {
                result = driver;
                driverEntity = em.find(DriverEntity.class, (String) driver.get(0));
                result.add(driverEntity.getCar().getId());
                break;
            }
        }
        return result;*/
    }

    @Override
    public String addRide(String username, int latitude, int longtitude) {
        String result = "";
        System.out.println("addRide()");
        String driverId = findNearestDriver(latitude, longtitude);
        System.out.println("nearest driver is " + driverId);
        rideEntity = new RideEntity();
        rideEntity.create(new Date(), latitude, longtitude);
        em.persist(rideEntity);
        System.out.println("persist(rideEntity)");
        usersEntity = em.find(UsersEntity.class, username);
        rideEntity.setUser(usersEntity);
        driverEntity = em.find(DriverEntity.class, driverId);
        rideEntity.setDriver(driverEntity);
        Date date = new Date();
        rideEntity.setStartDate(date);

        //add ride to user
        Collection<RideEntity> rides = usersEntity.getRides();
        rides.add(rideEntity);
        usersEntity.setRides(rides);

        //add ride to driver
        Collection<RideEntity> rides1 = driverEntity.getRides();
        rides1.add(rideEntity);
        driverEntity.setRides(rides1);

        em.flush();
        result = "Booking Successful! Your driver, " + driverEntity.getName()
                + " is on the way. Taxi car number: " + driverEntity.getCar().getId()
                + ". Contact: " + driverEntity.getContact();
        return result;
    }

    @Override
    public Vector currentRide(String username) {
        Vector result = new Vector();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
        usersEntity = em.find(UsersEntity.class, username);
        for (Object o : usersEntity.getRides()) {
            RideEntity r = (RideEntity) o;
            if (r.getEndDate() == null) {
                result.add(r.getId());
                result.add(sdf.format(r.getStartDate()));
                //result.add(r.getStartDate());
                result.add(r.getStartLatitude());
                result.add(r.getStartLongtitude());
                break;
            }
        }
        return result;
    }

    @Override
    public double cancelRide(Long id) {
        double fee;
        Date current = new Date();
        rideEntity = em.find(RideEntity.class, id);
        rideEntity.setEndDate(current);
        rideEntity.setEndLatitude(rideEntity.getStartLatitude());
        rideEntity.setEndLongtitude(rideEntity.getStartLongtitude());
        Date startTime = rideEntity.getStartDate();
        if ((current.getTime() - startTime.getTime()) > 120000) {
            rideEntity.setFee(5);
            fee = 5;
        } else {
            rideEntity.setFee(0);
            fee = 0;
        }
        em.flush();
        return fee;
    }

    @Override
    public List<Vector> getAllPayments(String username) {
        List<Vector> allPayments = new ArrayList();
        usersEntity = em.find(UsersEntity.class, username);
        for (Object o : usersEntity.getPayments()) {
            Vector payment = new Vector();
            PaymentEntity p = (PaymentEntity) o;
            payment.add(p.getId());
            payment.add(sdf.format(p.getDate()));
            payment.add(p.getType());
            payment.add(p.getNumber());
            payment.add(p.getName());
            allPayments.add(payment);
        }
        return allPayments;
    }

    @Override
    public List<Vector> getMessages(String username) {
        List<Vector> allMessages = new ArrayList();
        usersEntity = em.find(UsersEntity.class, username);
        for (Object o : usersEntity.getMessages()) {
            Vector message = new Vector();
            MessageEntity m = (MessageEntity) o;
            message.add(sdf.format(m.getDate()));
            message.add(m.getContent());
            message.add(m.getStatus());
            if (m.getStatus().equals("unread")) {

                message.add("N.A.");//No Comment
            } else {
                message.add(m.getComment());
            }
            allMessages.add(message);
        }
        return allMessages;
    }

    @Override
    public String addMessage(String username, String content) {
        usersEntity = em.find(UsersEntity.class, username);
        MessageEntity m = new MessageEntity();
        m.create(new Date(), content);
        m.setUser(usersEntity);
        m.setStatus("unread");
        em.persist(m);
        Collection<MessageEntity> allMessages = usersEntity.getMessages();
        allMessages.add(m);
        usersEntity.setMessages(allMessages);
        em.flush();
        return "Your message has been sent!";
    }
}
