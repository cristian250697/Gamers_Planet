<%-- 
    Document   : usuario
    Created on : 15/05/2019, 11:41:49 AM
    Author     : trebo
--%>

<%@page import="entidades.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="menuGamers.css">
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>GAMERS PLANET</title>
    </head>
    <body>
        <%
        
        HttpSession sesion = request.getSession();
        Usuario usr = (Usuario)sesion.getAttribute("usuario");
        
        %>
        <!-- Sidebar  -->
        <%@ include file="menuCode.jsp" %>  
        <!-- Page Content  -->
        <div id="content">
            <%@ include file="menuToolBar.jsp" %>  
            <!--Aqui poner contenido de vistas-->
            <img src="usuario.png" style="width: 100px;
                 height: 100px;
                 position: absolute;
                 top: 200px;
                 left: calc(80% - 50px)"class="img-rounded">
            <div class="form-group">
                <label for="name" class="control-label">Nombre</label>
                <p class="name"><% out.println(usr.getNombre()); %></p>
            </div>
            <div class="form-group">
                <label for="last_name" class="control-label">Apellido</label>
                <p class="last_name"><% out.println(usr.getApellido()); %></p>
            </div>
            <div class="form-group">
                <label for="telefono" class="control-label">Telefono</label>
                <p class="telefono"><% out.println(usr.getTelefono()); %></p>
            </div> <div class="form-group">
                <label for=correo" class="control-label">Correo</label>
                <p class="correo"><% out.println(usr.getCorreo()); %></p>
            </div>
            <div class="form-group">
                <label for="pass" class="control-label">Contraseña</label>
                <p class="pass"><% out.println(usr.getContrasenia()); %></p>
            </div>
            <div class="form-group">
                <label for="dir" class="control-label">Dirección</label>
                <p class="dir"><% out.println(usr.getDireccion()); %></p>
            </div>
            <div class="form-group">
                <label for="rol" class="control-label">Rol</label>
                <p class="rol"><% 
                    if(usr.getStatusRol() == 0){
                        out.println("Administrador");
                    }else if(usr.getStatusRol() == 1){
                        out.println("Empleado");
                    }else if(usr.getStatusRol() == 2){
                        out.println("Cliente");
                    }
                %></p>
            </div>
            <!--Imports necesarios para menu-->
            <%@ include file="importMenu.jsp" %>  
    </body>
</html>
