<%-- 
    Document   : welcome
    Created on : 26-Mar-2017, 01:18:21
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
                String userName = (String) session.getAttribute("userName");
                String message = request.getParameter("message");
            %>
            
            <div class="page-header" style="width: 70%; margin-left: auto; margin-right: auto;">
                <ul class="nav nav-pills">
                    <li class="active"><a href="welcome">Main</a></li>
                    <li><a href="profile">Profile</a></li>
                    <li><a href="book">Book</a></li>
                    <li><a href="rides">Rides</a></li>
                    <li><a href="payments">Payments</a></li>
                    <li><a href="messages">Messages</a></li>
                </ul>
            </div>
            <div class="jumbotron" style="width: 70%; margin-left: auto; margin-right: auto; padding: 50px;">
                <h3>Welcome, <%= userName%>! </h3>
                <div>
                <% if (message != null) {%>
                    <h5 style="color: green"><%= message%></h5>
                <% }%>
                </div>
           </div>
        </div>
    </body>
</html>