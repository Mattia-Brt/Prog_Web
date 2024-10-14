<%-- 
    Document   : Login
    Created on : 17-mag-2022, 16.52.35
    Author     : mattiabirti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Streaming</title>
    </head>
    <body>
        <h1>HI! WHTA'S YOUR NAME</h1>
        <p style="color: red"><span id="errorMessageLogin"></span></p>
        <form action ="createSession" method="post">
            <input type="text" id="username" name="username" required>
            <input type="submit" value="ENTER">
        </form>
    </body>
</html>
