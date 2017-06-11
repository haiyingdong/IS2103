<%-- 
    Document   : payments
    Created on : 26-Mar-2017, 11:09:06
    Author     : donghaiying
--%>

<%@page import="java.util.Vector"%>
<%@page import="java.util.List"%>
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
            <div class="page-header" style="width: 70%; margin-left: auto; margin-right: auto;">
                <ul class="nav nav-pills">
                    <li><a href="welcome">Main</a></li>
                    <li><a href="profile">Profile</a></li>
                    <li><a href="book">Book</a></li>
                    <li><a href="rides">Rides</a></li>
                    <li class="active"><a href="payments">Payments</a></li>
                    <li><a href="messages">Messages</a></li>
                </ul>
            </div>
            <div class="jumbotron" style="width: 70%; margin-left: auto; margin-right: auto; padding: 50px;">
                <h3>Your Previous Payments</h3>
                <div>
                    <%
                        List<Vector> allPayments = (ArrayList) request.getAttribute("viewPayment");
                        for (Object o : allPayments) {
                            Vector payment = (Vector) o;
                    %>
                    <div>
                    ID: <%=payment.get(0)%>
                    </div>
                    <div>
                    Time:<%=payment.get(1)%>
                    </div>
                    <div>
                    Card Type:<%=payment.get(2)%>
                    </div>
                    <div>
                    Card Number:<%=payment.get(3)%>
                    </div>
                    <div>
                    Holder Name:<%=payment.get(4)%>
                    </div>
                    <br>
                    <br>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>