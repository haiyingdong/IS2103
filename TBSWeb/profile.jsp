<%-- 
    Document   : profile
    Created on : 21-Mar-2017, 15:24:30
    Author     : donghaiying
--%>

<%@page import="java.util.Vector"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">       
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <title>Taxi Booking System</title>
    </head>
    <body>
        <div>
            <%
                Vector userData = (Vector) request.getAttribute("userData");
                String userName = "userName";
                String contact = "contact";
                String email = "email";
                String address = "address";
                if (userData != null) {
                    userName = (String) userData.get(0);
                    contact = (String) userData.get(2);
                    email = (String) userData.get(3);
                    address = (String) userData.get(4);
                }
                String message = (String) request.getAttribute("message");
            %>
            
            <div class="page-header" style="width: 70%; margin-left: auto; margin-right: auto;">
                <ul class="nav nav-pills">
                    <li><a href="welcome">Main</a></li>
                    <li class="active"><a href="profile">Profile</a></li>
                    <li><a href="book">Book</a></li>
                    <li><a href="rides">Rides</a></li>
                    <li><a href="payments">Payments</a></li>
                    <li><a href="messages">Messages</a></li>
                </ul>
            </div>
            <div class="jumbotron" style="width: 70%; margin-left: auto; margin-right: auto; padding: 50px;">
                <div>
                <% if (message != null) {%>
                    <h5 style="color: gray"><%= message%></h5>
                <% }%>
            </div>
                <form name="email" class="form-horizontal" action="updateInfo" method="POST">
                    
                    <div><strong>Update Email</strong></div>
                    <div class="panel-body">
                        <div class="input-group">
                            <span class="input-group-addon">
                                Email
                            </span>
                            <input type="text" class="form-control" name="email" placeholder="<%= email%>" required>
                        </div>
                        <br>
                        
                    </div>

                    <div><strong>Update Contact Number</strong></div>
                    <div class="panel-body">
                        <div class="input-group">
                            <span class="input-group-addon">
                                Contact
                            </span>
                            <input type="number" class="form-control" name="contact" placeholder="<%= contact%>" required>
                        </div>
                        <br>
                        
                    </div>

                    <div><strong>Update Address</strong></div>
                    <div class="panel-body">
                        <div class="input-group">
                            <span class="input-group-addon">
                                Address
                            </span>
                            <input type="text" class="form-control" name="address" placeholder="<%= address%>" required>
                        </div>
                        <br>
                        
                    </div>

                
                    <div><strong>Update Password</strong></div>
                    <div class="panel-body">
                        <div class="input-group">
                            <span class="input-group-addon">

                                Current Password
                            </span>
                            <input type="password" class="form-control" name="oldPwd" required>
                        </div>
                        <br>
                        <div class="input-group">
                            <span class="input-group-addon">
                                New Password
                            </span>
                            <input type="password" class="form-control" name="newPwd" required>
                        </div>
                        <br>
                        <div class="input-group">
                            <span class="input-group-addon">
                                Confirm Password
                            </span>
                            <input type="password" class="form-control" name="cfmPwd" required>
                        </div>
                        <br>
                        <div class="navbar-left">
                            <input class="btn btn-success" type="Submit">
                        </div>
                        <br><br>
                  
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>