<%-- 
    Document   : cancel
    Created on : 26-Mar-2017, 05:11:27
    Author     : donghaiying
--%>

<%@page import="java.util.Vector"%>
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
            <div class="page-header" style="width: 70%; margin-left: auto; margin-right: auto;">
                <ul class="nav nav-pills">
                    <li><a href="welcome">Main</a></li>
                    <li><a href="profile">Profile</a></li>
                    <li><a href="book">Book</a></li>
                    <li class="active"><a href="rides">Rides</a></li>
                    <li><a href="payments">Payments</a></li>
                    <li><a href="messages">Messages</a></li>
                </ul>
            </div>
            <div class="jumbotron" style="width: 70%; margin-left: auto; margin-right: auto; padding: 50px;">
                <h3>Cancel Current Ride</h3>

                <form action="aftercancel" method="POST" accept-charset="UTF-8">
                    <%
                        Vector currRide = (Vector) request.getAttribute("currRide");
                        String message = (String) request.getAttribute("message");
                        if (message != null) {
                    %>
                    <div>
                    <h5 style="color: grey"><%= message%></h5>
                    </div>
                    <% } else if (!currRide.isEmpty()) {
                    %>
                    <div>
                        <h4>You are currently on ride <%=currRide.get(0)%></h4>
                    </div>
                    <div>
                    Start Time: <%=currRide.get(1)%>
                    </div>
                    <div>
                    Start Latitude: <%=currRide.get(2)%>
                    </div>
                    <div>
                    Start Longitude: <%=currRide.get(3)%>
                    </div>
                    <br>
                    <input class="btn btn-success" type="Submit" value="Cancel">
                    <%} else {
                    %>
                    <h4 style="color: red">You cannot cancel any ride!</h4>
                    <%}%>
                    
                </form> 

            </div>
        </div>

    </body>
</html>
