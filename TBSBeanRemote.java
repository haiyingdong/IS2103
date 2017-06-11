/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

/**
 *
 * @author donghaiying
 */
@Remote
public interface TBSBeanRemote {
    
    public String addUser (String userName, String password, String contact, String email, String address);
    
    public void updateUser(String userName, String email, String contact, String address, String pwd);

    public String addCar(String id, String brand, String model, String year);

    public String addDriver(String id, String name, String contact, String address, int longtitude, int latitude, String carId);

    public String deleteUser(String userName);

    public String deleteCar(String id);

    public String updateDriver(String id);

    public String deleteDriver(String id);

    public String feedbackReport(String id);

    public List<Vector> viewIdleDrivers();

    public List<Vector> performanceReport();

    public String viewUnpaidRides();

    public String processMessages();

    public void reviewMsg(Long id, String statusChange, String commentsSet);

    public String updateDriverHaveRides(String id, String name, String contact, String address);

    public String updateDriverNoRides(String id, String carId, String name, String contact, String address);
    
    public Vector getUserData(String userName);
    
    public String payments(String userName);
    
    //public String paidRides(String userName);
    
    public String feedback (String id, String userName, int ratings, String comment);
    
    public String getRides(String userName);
    
    public String pay (String rideId, String userName, String type, long number, String name, double value);
    
    public boolean hasUnpaidRide(String username);
    
    //public String findNearestDriver(int latitude, int longitude);
    
    public String addRide(String username, int latitude, int longtitude);
    
    public Vector currentRide(String username);
    
    public double cancelRide(Long id);
    
    public List<Vector> getAllPayments(String username);
    
    public List<Vector> getMessages(String username);
    
    public String addMessage(String username, String content) ;
}
