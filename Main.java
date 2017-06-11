/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dusclient;

import ejb.DUSBeanRemote;
import java.util.Date;
import java.util.Scanner;
import javax.ejb.EJB;

/**
 *
 * @author donghaiying
 */
public class Main {

    @EJB
    private static DUSBeanRemote dUSBean;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Main client = new Main();
        client.run();
    }
    public void run() {
        Scanner sc=new Scanner(System.in);
        try {
            String req = "";

            System.out.println("Main: Client Started...");
            System.out.println("*****************************************");
            System.out.println("Welcome to the Driver Update System ");
            System.out.println("*****************************************");
           
            System.out.println("Enter License Number: ");
            String id = sc.nextLine();
            System.out.print("Enter choice: ");
            System.out.println("   1. Update Location: ");
            System.out.println("   2. End Ride: ");
            req=sc.nextLine();

            if (req.equals("1")) {

                System.out.print("Enter Latitude: ");
                int latitude = sc.nextInt();
                
                System.out.print("Enter Longtitude: ");
                int longtitude = sc.nextInt();

                dUSBean.update(id, latitude, longtitude);
                System.out.print("Request has been sent.");
            } else {
                System.out.print("Enter Latitude: ");
                int latitude = sc.nextInt();
                
                System.out.print("Enter Longtitude: ");
                int longtitude = sc.nextInt();
                
                Date date = new Date();

                dUSBean.end(id, date, latitude, longtitude);
                System.out.print("Request has been sent.");
            }
        } catch (Exception ex) {
            System.err.println("Caught an unexpected exception!");
            ex.printStackTrace();
        }
    }
}
