/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbsadminclient;

import ejb.TBSBeanRemote;
import java.util.List;
import javax.ejb.EJB;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author donghaiying
 */
public class Main {

    @EJB
    private static TBSBeanRemote tBSBean;
    public Scanner sc = new Scanner(System.in);

    public Main() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Main client = new Main();
        client.getUserChoice();
    }
    public void getUserChoice() {
        String userInput = "";
        do {
            System.out.println();
            displayMenu();
            userInput = sc.nextLine();
            if (userInput.equals("1")) {
                addUser();
            } else if (userInput.equals("2")) {
                deleteUser();
            } else if (userInput.equals("3")) {
                addCar();
            } else if (userInput.equals("4")) {
                deleteCar();
            } else if (userInput.equals("5")) {
                addDriver();
            } else if (userInput.equals("6")) {
                updateDriver();
            } else if (userInput.equals("7")) {
                deleteDriver();
            } else if (userInput.equals("8")) {
                feedbackReport();
            } else if (userInput.equals("9")) {
                viewIdleDrivers();
            } else if (userInput.equals("10")) {
                PerformanceReport();
            } else if (userInput.equals("11")) {
                viewUnpaidRides();
            } else if (userInput.equals("12")) {
                processMessages();
            } else {
                System.out.println("Please Input a Number from 1 to 12.");
                System.out.println("To exit, input 'Q' or 'q'.");
            }
        } while (userInput == null || (!userInput.equals("Q") && !userInput.equals("q")));
    }
    
    private void displayMenu() {
        System.out.println("****************************************");
        System.out.println("Welcome to the Taxi Booking System Admin");
        System.out.println("****************************************");
        System.out.println();
        System.out.println("Select an option:");
        System.out.println("1.  Add user account(s)");
        System.out.println("2.  Delete user account(s)");
        System.out.println("3.  Add car(s)");
        System.out.println("4.  Delete car(s)");
        System.out.println("5.  Add driver(s)");
        System.out.println("6.  Update driver(s)");
        System.out.println("7.  Delete driver(s)");
        System.out.println("8.  Feedback report");
        System.out.println("9.  View idle driver(s)");
        System.out.println("10. Monthly performance report");
        System.out.println("11. View unpaid rides");
        System.out.println("12. Process messages");
        System.out.println();
        System.out.println("Enter 'q' or 'Q' to exit this admin console");
        System.out.println();
        System.out.print("Enter your option: ");
    }

    private void addUser() {
        try {
            System.out.println("Add user Account:");
            System.out.println();
            System.out.print("Enter user name: ");
            String userName = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            System.out.print("Enter contact number: ");
            String contact = sc.nextLine();
            System.out.print("Enter email: ");
            String email = sc.nextLine();
            System.out.print("Enter address: ");
            String address = sc.nextLine();
            
            String result = tBSBean.addUser(userName, password, contact, email,address);
            System.out.println();
            System.out.println(result);
        }catch (Exception ex) {
            System.err.println("Caught unexpected exception.");
            ex.printStackTrace();
        }  
    }

    private void deleteUser() {
        System.out.println("Delete User Account:");
        System.out.print("Enter user name: ");
        String userName = sc.nextLine();
        String result = tBSBean.deleteUser(userName);
        System.out.println();
        System.out.println(result);
    }

    private void addCar() {
        try {
            System.out.println("Add Car:");
            System.out.println();
            System.out.print("Enter registration number: ");
            String id = sc.nextLine();
            System.out.print("Enter car brand: ");
            String brand = sc.nextLine();
            System.out.print("Enter car model: ");
            String model = sc.nextLine();
            System.out.print("Enter manufactured year: ");
            String year = sc.nextLine();
            
            System.out.println();
            System.out.println(tBSBean.addCar(id, brand, model, year));
        }catch (Exception ex) {
            System.err.println("Caught unexpected exception.");
            ex.printStackTrace();
        }  
    }

    private void deleteCar() {
        System.out.println("Delete Car:");
        System.out.print("Enter registration number: ");
        String id = sc.nextLine();
        String result = tBSBean.deleteCar(id);
        System.out.println();
        System.out.println(result);
    }

    private void addDriver() {
        try {
            System.out.println("Add Driver Account:");
            System.out.println();
            System.out.print("Enter driving license number: ");
            String id = sc.nextLine();
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter contact number: ");
            String contact = sc.nextLine();
            System.out.print("Enter address: ");
            String address = sc.nextLine();
            System.out.print("Enter latitde: ");
            int latitude = Integer.parseInt(sc.nextLine());
            System.out.print("Enter longtitude: ");
            int longtitude = Integer.parseInt(sc.nextLine());
            System.out.print("Enter car registration number: ");
            String carId = sc.nextLine();
            
            System.out.println();
            System.out.println(tBSBean.addDriver(id, name, contact,address, longtitude, latitude, carId));
        }catch (Exception ex) {
            System.err.println("Caught unexpected exception.");
            ex.printStackTrace();
        }  
    }

    private void updateDriver() {
        System.out.println("Update Driver:");
        System.out.print("Enter driving license number: ");
        String id = sc.nextLine();
        if (tBSBean.updateDriver(id).equals("have rides")) {
            System.out.print("Enter new name: ");
            String name = sc.nextLine();
            System.out.print("Enter new contact: ");
            String contact = sc.nextLine();
            System.out.print("Enter new address: ");
            String address = sc.nextLine();
            
            System.out.println(tBSBean.updateDriverHaveRides(id, name, contact, address));
        } else {
            /*System.out.print("Enter new driving license number: ");
            String newId = sc.nextLine();*/
            System.out.print("Enter new name: ");
            String name = sc.nextLine();
            System.out.print("Enter new contact: ");
            String contact = sc.nextLine();
            System.out.print("Enter new address: ");
            String address = sc.nextLine();
            System.out.print("Enter new car registration number: ");
            String carId = sc.nextLine();
            
            String result = tBSBean.updateDriverNoRides(id, carId, name, contact, address);
            if (result.equals("no car")) {
                System.out.println("Car does not exist. Please create a new car");
                addCar();
                System.out.println(tBSBean.updateDriverNoRides(id, carId, name, contact, address));
            }
            else System.out.println(result);
        }
        System.out.println();
    }

    private void deleteDriver() {
        System.out.println("Delete Driver:");
        System.out.print("Enter driving license number: ");
        String id = sc.nextLine();
        String result = tBSBean.deleteDriver(id);
        System.out.println();
        System.out.println(result);
    }

    private void feedbackReport() {
        System.out.println("View Feedback Report:");
        System.out.print("Enter driving license number: ");
        String id = sc.nextLine();
        String result = tBSBean.feedbackReport(id);
        System.out.println();
        System.out.println(result);
    }

    private void viewIdleDrivers() {
        System.out.println("View Idle Drivers:");
        System.out.println();
        List<Vector> result = tBSBean.viewIdleDrivers();
        if  (result.isEmpty()) {
            System.out.println();
            System.out.println("No idle driver to show!");
        }
        int i = 0;
        System.out.println();
        for (Vector v : result) {
            i ++;
            System.out.println(i + ". License: " + v.get(0));
            System.out.println("Name: " + v.get(1));
            System.out.println("Contact: " + v.get(2));
            System.out.println("Latitude: " + v.get(3));
            System.out.println("Longtitude: " + v.get(4));
        }        
    }

    private void PerformanceReport() {
        System.out.println("View Monthly Performance Report:");
        System.out.println();
        List<Vector> driverReports = tBSBean.performanceReport();
        for (Vector driver : driverReports) {
            System.out.println("Driver " + driver.get(0) + ":");
            Vector report = (Vector) driver.get(1);
            System.out.println("Totoal Revenue: $" + report.get(0));
            System.out.println("Number of Rides: " + report.get(1));
            System.out.println("Totoal Time of Rides: " + report.get(2));
            System.out.println("Totoal Distance: " + report.get(3));
            System.out.println();
        }
        System.out.println("****End Of Monthly Report****\n");
    }

    private void viewUnpaidRides() {
        System.out.println("View Unpaid Rides:");
        System.out.println();
        System.out.println(tBSBean.viewUnpaidRides());
    }

    private void processMessages() {
        System.out.println("Process Messages");
        String msgList = tBSBean.processMessages();
        if (msgList.equals("No messages to show!\n")) {
            
            return;
        }
        System.out.println(msgList);
        System.out.print("Enter message id: ");
        Long id = sc.nextLong();
        sc.nextLine();
        System.out.print("Change status to: ");
        String statusChange = sc.nextLine();
        System.out.print("Comments: ");
        String commentsSet = sc.nextLine();
        tBSBean.reviewMsg(id, statusChange, commentsSet);
    }
}
