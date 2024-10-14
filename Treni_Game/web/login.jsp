<%-- 
    Document   : login
    Created on : 4-mag-2022, 14.52.45
    Author     : mattiabirti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <% String message = (String) application.getAttribute("loginErrorMessage"); %>
    </head>
    <body>
        <h1>Welcome new user</h1>
        <h2>Please insert here your username and click on START button</h2>
        <form action="createSession" method="post">
            <div> Username : <input type="text" name="username" required=""> </div>
            <br>
            <input type="submit" value="START" id="startButton">
        </form>
        <h4 style="color: red"> <% if(message!=null) out.println(message);%> </h4>
    </body>
</html>
