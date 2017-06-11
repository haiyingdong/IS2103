<%-- 
    Document   : signup
    Created on : 21-Mar-2017, 15:14:08
    Author     : donghaiying
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">       
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <title>Module Search System</title>
    </head>
    <body>
        <div>
            <%
                String message = (String) request.getAttribute("message");
                if (message != null) {
            %>
            <h4 style="color: red"><%= message%></h4>
            <% }%>
            
            <div class="jumbotron" style="width: 70%; margin-left: auto; margin-right: auto; padding: 50px;">        
                <form action="checksignup" name="password" class="form-horizontal" action="updatePassword" method="POST">
                    <div><strong>Sign Up</strong></div>
                    <div class="panel-body">
                        <div class="input-group">
                            <span class="input-group-addon">
                                Enter User Name
                            </span>
                            <input type="text" class="form-control" name="name" required>
                        </div>
                        <br>
                        <div class="input-group">
                            <span class="input-group-addon">
                                Enter Password
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
                        <div class="input-group">
                            <span class="input-group-addon">
                                Enter Contact Number
                            </span>
                            <input type="number" class="form-control" name="contact" required>
                        </div>
                        <br>
                        <div class="input-group">
                            <span class="input-group-addon">
                                Enter Email
                            </span>
                            <input type="text" class="form-control" name="email" required>
                        </div>
                        <br>
                        <div class="input-group">
                            <span class="input-group-addon">
                                Enter Address
                            </span>
                            <input type="text" class="form-control" name="address" required>
                        </div>
                        <br>
                        <div class="navbar-left">
                            <button class="btn btn-success" type="submit" value="submit">Submit</button>
                        </div>
                     
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
