<%-- 
    Document   : feedback
    Created on : 21-Mar-2017, 16:04:58
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
                <h3>Submit Feedback For A Ride</h3>

                <form action="afterfeedback" method="POST" accept-charset="UTF-8">

                    <div class="panel-body">
                        <div class="input-group">
                            <span class="input-group-addon">
                                Ride ID
                            </span>
                            <input type="text" class="form-control" name="rideId" placeholder="" required>
                        </div>
                        <br>

                    </div>
                    <div class="panel-body">
                        <div class="input-group">
                            <span class="input-group-addon">
                                Ratings
                            </span>
                            <input type="number" class="form-control" name="ratings" placeholder="1-5 star" required>
                        </div>
                        <br>

                    </div>


                    <div class="panel-body">
                        <div class="input-group">
                            <span class="input-group-addon">
                                Comment
                            </span>
                            <input type="text" class="form-control" name="comment" placeholder="">
                        </div>
                        <br>

                    </div>
                    <input class="btn btn-success" type="Submit">
                </form> 
                <%
                    String message = (String) request.getAttribute("message");
                    if (message != null) {
                %>
                <h5 style="color: grey"><%= message%></h5>
                <% }%>
            </div>
        </div>

    </body>
</html>
