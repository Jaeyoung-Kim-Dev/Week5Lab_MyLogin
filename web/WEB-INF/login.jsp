<%-- 
    Document   : login
    Created on : Oct 9, 2020, 2:48:59 AM
    Author     : kornk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>

        <form method="post" action="action">
            Username:
            <input type="text" name="username" value="${accountService.username}">
            <br>
            Password:
            <input type="text" name="password" value="${accountService.password}">
            <br>
            <input type="submit" value="Log in">
        </form>

        ${message}
    </body>
</html>
