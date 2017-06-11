/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import ejb.TBSBeanRemote;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author donghaiying
 */
@WebServlet(name = "TBSServlet", urlPatterns = {"/TBS"})
public class TBSServlet extends HttpServlet {

    @EJB
    private TBSBeanRemote tbs;
    private String reply;
    private ArrayList data = null;
    //private String userName = "";

    public void init() {
        System.out.println("TBSServlet: init()");
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("TBSServlet: processRequest()");
        response.setContentType("text/html;charset=UTF-8");
        try {
            /* TODO output your page here. You may use following sample code. */
            RequestDispatcher d;
            ServletContext servletContext = getServletContext();
            String page = request.getPathInfo();
            page = page.substring(1);
            String userName = (String) request.getSession().getAttribute("userName");

            switch (page) {
                case "index":
                    break;
                case "book":
                    break;
                case "afterbook":
                    //log("1");
                    request.setAttribute("message", bookRide(request));
                    //log("2");
                    page = "book";
                    //log("3");
                    break;
                case "cancel":
                    //log("1");
                    Vector currRide = tbs.currentRide((String) request.getSession().getAttribute("userName"));
                    //log("2");
                    request.setAttribute("currRide", currRide);
                    //log("3");
                    break;
                case "aftercancel":
                    Vector currRide1 = tbs.currentRide((String) request.getSession().getAttribute("userName"));
                    //log("2");
                    request.setAttribute("currRide", currRide1);
                    log("hi");
                    request.setAttribute("message", cancelRide(request));
                    log("hu");
                    page = "cancel";
                    log("ha");
                    break;
                case "feedback":
                    break;
                case "afterfeedback":
                    //request.setAttribute("paidRides", tbs.paidRides(userName));
                    request.setAttribute("message", tbs.feedback(request.getParameter("rideId"), userName,
                            Integer.parseInt(request.getParameter("ratings")),
                            (String) request.getParameter("comment")));
                    page = "feedback";
                    break;
                case "login":
                    System.out.println("login");
                    request.setAttribute("message", null);
                    break;
                case "checklogin":
                    System.out.println("checklogin");
                    if (checkLogin(request)) {
                        //String userName = (String) request.getSession().getAttribute("userName");
                        //request.setAttribute("userData", tbs.getUserData(userName));
                        request.setAttribute("message", "Login successfully!");
                        page = "welcome";
                    } else {
                        request.setAttribute("message", "Incorrect user name or password!");
                        page = "login";
                    }
                    break;
                case "updateInfo":
                    if (checkUpdate(request) == 1) {
                        request.setAttribute("userData", tbs.getUserData(userName));
                        request.setAttribute("message", "Invalid current password!");
                        page = "profile";
                    } else if (checkUpdate(request) == 2) {
                        request.setAttribute("userData", tbs.getUserData(userName));
                        request.setAttribute("message", "New password is inconsistent! Please confirm your password.");
                        page = "profile";
                    } else if (checkUpdate(request) == 3) {
                        request.setAttribute("userData", tbs.getUserData(userName));
                        request.setAttribute("message", "Update successfully!");
                        page = "profile";
                    } else page = "profile";
                    break;
                case "messages":
                    List<Vector> allMessages = viewMessages(request);
                    request.setAttribute("messages", allMessages);
                    break;
                case "newmsg":
                    break;
                case "sendmsg":
                    String content = request.getParameter("content");
                    String user = (String) request.getSession().getAttribute("userName");
                    request.setAttribute("message", tbs.addMessage(user, content));
                    page = "newmsg";
                    break;
                case "pay":
                    break;
                case "afterpay":
                    String rideId = request.getParameter("rideId");
                    //System.out.println("request.getParameter(\"rideId\")");

                    String type = request.getParameter("type");
                    //System.out.println("request.getParameter(\"type\")");

                    long number = Long.parseLong(request.getParameter("number"));
                    //System.out.println("long number");

                    String name = request.getParameter("name");
                    //System.out.println("String name");

                    double value = Double.parseDouble(request.getParameter("value"));

                    request.setAttribute("message", tbs.pay(rideId, userName, type, number, name, value));
                    //System.out.println("tbs.pay");
                    page = "pay";

                    break;
                case "payments":
                    List<Vector> allPayments = viewPayments(request);
                    request.setAttribute("viewPayment", allPayments);
                    break;
                case "profile":
                    //String userName = (String) request.getSession().getAttribute("userName");
                    request.setAttribute("userData", tbs.getUserData(userName));
                    page = "profile";
                    break;
                case "rides":
                    //request.setAttribute("message", "1");
                    String msg = tbs.getRides((String) request.getSession().getAttribute("userName"));
                    System.out.println(msg);
                    request.setAttribute("message", msg);
                    log("1");
                    page = "rides";
                    break;

                case "signup":
                    break;
                case "checksignup":
                    int i = signup(request);
                    if (i == 1) {
                        request.setAttribute("message", "Password is inconsistent!");
                        page = "signup";
                    } else if (i == 2) {
                        request.setAttribute("message", "User already exists!");
                        page = "signup";
                    } else if (i == 3) {
                        request.setAttribute("message", "Sign up successfully! Please login.");
                        page = "login";
                    } else {
                        page = "error";
                    }
                    break;

                case "welcome":
                    break;
                default:
                    page = "error";
            }

            d = servletContext.getNamedDispatcher(page);
            if (d == null) {
                d = servletContext.getNamedDispatcher("error");
            }
            d.forward(request, response);

        } catch (Exception e) {
            log("Exception in TBSServlet.processRequest()");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "TBS Servlet";
    }

    @Override
    public void destroy() {
        System.out.println("TBSServlet: destroy()");
    }

    private int checkUpdate(HttpServletRequest request) {
        System.out.println("TBS: checkUpdate()");
        try {
            String password = request.getParameter("oldPwd");
            String newPassword = request.getParameter("newPwd");
            String newPasswordConfirmed = request.getParameter("cfmPwd");
            String email = request.getParameter("email");
            String contact = request.getParameter("contact");
            String address = request.getParameter("address");
            String userName = (String) request.getSession().getAttribute("userName");
            Vector user = tbs.getUserData(userName);

            // Invalid current password!
            if (!((String) user.get(1)).equals(password)) {
                return 1;
            } // New password is inconsistent! Please confirm your password. 
            else if (!newPassword.equals(newPasswordConfirmed)) {
                return 2;
            } // Update successfully!
            else {
                tbs.updateUser(userName, email, contact, address, newPassword);
                return 3;
            }

        } catch (Exception e) {
            System.out.println("There is an exception in updateProfile method!");
            return -1;
        }
    }

    private boolean checkLogin(HttpServletRequest request) {
        System.out.println("TBS: checkLogin()");
        String name = request.getParameter("userName");
        String password = request.getParameter("password");
        Vector user = tbs.getUserData(name);
        if (user != null) {
            if (((String) user.get(1)).equals(password)) {
                System.out.println("login successful");
                request.getSession().setAttribute("userName", (String) user.get(0));
                //userName = (String) user.get(0);
                return true;
            }
        }
        return false;
    }

    private int signup(HttpServletRequest request) {
        System.out.println("TBS: signup()");
        String userName = request.getParameter("name");
        //log("1");
        String password = request.getParameter("newPwd");
        //log("2");
        String passwordCfm = request.getParameter("cfmPwd");
        //log("3");
        String contact = request.getParameter("contact");
        //log("4");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int reply = 0;
        if (!password.equals(passwordCfm)) {
            reply = 1;
        } else if (!tbs.getUserData(userName).isEmpty()) {
            reply = 2;
        } else {
            tbs.addUser(userName, password, contact, email, address);
            reply = 3;
        }
        System.out.println(reply);
        return reply;
        
    }

    private String cancelRide(HttpServletRequest request) {
        //String result = "";
        log("1");
        Vector r = tbs.currentRide((String) request.getSession().getAttribute("userName"));
        if (r.isEmpty()) {
            log("2");
            return "You are not on any ride!";
        }
        log("3");
        DecimalFormat df = new DecimalFormat("#.##");
        log("4");
        double tmp = tbs.cancelRide((Long) r.get(0));
        log("5");
        System.out.println(tmp);
        String fee = df.format(tmp);

        return "Cancel successfully! Fee is: " + fee;
    }

    private String bookRide(HttpServletRequest request) {
        //log("4");
        String username = (String) request.getSession().getAttribute("userName");
        //log("5");
        int latitude = Integer.parseInt(request.getParameter("latitude"));

        int longtitude = Integer.parseInt(request.getParameter("longtitude"));
        //log("6");
        if (tbs.hasUnpaidRide(username)) {
            return "Cannot Book Rides! Please pay for your previous ride(s) first!";
        }
        if (tbs.viewIdleDrivers().isEmpty()) {
            return "Sorry, no drivers are available now. Please try again later.";
        }
        return tbs.addRide(username, latitude, longtitude);
    }
    
    private List<Vector> viewPayments(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("userName");
        List<Vector> allPayments = new ArrayList();
        allPayments = tbs.getAllPayments(username);
        return allPayments;
    }
    
    private List<Vector> viewMessages(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("userName");
        List<Vector> allMessages = new ArrayList();
        allMessages = tbs.getMessages(username);
        return allMessages;
    }
}
