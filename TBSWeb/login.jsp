<%-- 
    Document   : login
    Created on : 21-Mar-2017, 14:13:00
    Author     : donghaiying
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Taxi Booking System</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">       
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div style="margin-top: 100px;">
            <div class="jumbotron" style="width: 60%; margin-left: auto; margin-right: auto; padding: 50px;">
                <header class="page-header">
                    <h3>Login</h3>             
                </header>
                <%
                    String message = (String) request.getAttribute("message");
                    if (message != null) {
                %>
                <h5 style="color: red"><%= message%></h5>
                <% }%>
                <div class="well">
                    <form action="checklogin" method="POST" accept-charset="UTF-8"/>
                    <h4>User Name</h4>
                    <input type="text" required name="userName" length="30"/>
                    <h4>Password</h4>
                    <input type="password" required name="password" length="30"/>
                    <br>
                    <br>
                    <input class="btn btn-success" type="Submit">
                    </form>     
                </div>
                <a href="signup" style="color: gray">No account? Sign up now!</a>
            </div>      
        </div>
    </body>
</html>