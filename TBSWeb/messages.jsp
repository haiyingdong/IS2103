<%-- 
    Document   : messages
    Created on : 21-Mar-2017, 15:34:03
    Author     : donghaiying
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Vector" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">       
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <title>Taxi Booking System</title>
    </head>
    <div class="page-header" style="width: 70%; margin-left: auto; margin-right: auto;">
        <ul class="nav nav-pills">
            <li><a href="welcome">Main</a></li>
            <li><a href="profile">Profile</a></li>
            <li><a href="book">Book</a></li>
            <li><a href="rides">Rides</a></li>
            <li><a href="payments">Payments</a></li>
            <li class="active"><a href="messages">Messages</a></li>
            
        </ul>
    </div>
    <body>
        <div>

            <div class="jumbotron" style="width: 80%; margin-left: auto; margin-right: auto; padding: 60px;">
                <h3>Your Messages</h3>
                <%
                    List<Vector> allMessages = (ArrayList) request.getAttribute("messages");
                    if (allMessages.isEmpty()) {
                %>
                <h4 style="color: grey">You have no messages!</h4> 
                <%} else {%>
                <%
                    for (Object o : allMessages) {
                        Vector message = (Vector) o;
                %>
                
                <div>
                    Time: <%=message.get(0)%>
                </div>
                <div>
                    Content: <%=message.get(1)%>
                </div>
                <div>
                    Status: <%=message.get(2)%>
                </div>
                <div>
                    Comment: <%=message.get(3)%>
                </div>
                <br><br>
                <%
                        }
                    }
                %>
                <br>
                <div class="navbar-right">
                    <a href="newmsg" class="btn btn-primary btn-lg" role="button">
                        Send A New Message
                    </a>
                </div>
            </div>

        </div>
    </body>
</html>
