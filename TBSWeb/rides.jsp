<%-- 
    Document   : rides
    Created on : 21-Mar-2017, 15:33:54
    Author     : donghaiying
--%>

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
                //String rides = "rides";                
                String message = (String) request.getAttribute("message");
            %>
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
                <h3>Your Previous Rides</h3>
                <div>
                    <a href="pay">Pay For Rides</a>   
                </div>

                <div>
                    <a href="feedback">Leave Feedbacks For Paid Rides</a>
                </div>

                <div>
                    <a href="cancel">Cancel Current Ride</a>
                </div>

                <div><br>
                    <%= message%>
                </div>
            </div>
        </div>
    </body>
</html>