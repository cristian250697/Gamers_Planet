<%-- 
    Document   : login
    Created on : 14/05/2019, 09:32:45 PM
    Author     : crist
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>TODO GAMER</title>
        <link rel="stylesheet" href="login.css">
    </head>
    <body>
        <div class="login-box">
            <img src="GamersPlanet.png" class="avatar" alt="Avatar Image" style ="background-color: white;">
            <h1>Iniciar sesion</h1>
            <form method="post" action="LoginServlet">
                <!-- USERNAME INPUT -->
                <label for="username">Username</label>
                <input type="text" id="username" name = "username" placeholder="Enter Username">
                <!-- PASSWORD INPUT -->
                <label for="password">Password</label>
                <input type="password" id="password" name = "password" placeholder="Enter Password">
                <input type="submit" value="Log In">
                <a href="#">No recurdas tu contraseña?</a><br>
                <a href="#">No tienes cuentas?</a>
            </form>
        </div>
    </body>
</html>
