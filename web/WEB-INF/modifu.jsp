<%-- 
    Document   : modifu
    Created on : 29/05/2019, 09:53:32 AM
    Author     : 52477
--%>


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
        <!-- Sidebar  -->
        <%@ include file="menuCode.jsp" %>  
        <!-- Page Content  -->
        <div id="content">
            <%@ include file="menuToolBar.jsp" %>  
            <!--Aqui poner contenido de vistas-->
            <div class="form-group">
                 <h1>Edita Usuarios</h1>
            </div> 
            <form action="RegistroUsuariosServlet" method="post">
                <div class="form-group"> <!--Nombre-->
                    <label for="name_id" class="control-label">Nombre </label>
                    <input type="text" class="form-control" id="name_id" name="name">
                </div>    

                <div class="form-group"> <!--Apellido-->
                    <label for="last_name_id" class="control-label">Apellido</label>
                    <input type="text" class="form-control" id="last_name_id" name="last_name" >
                </div>                    

                <div class="form-group"> <!-- Telefono-->
                    <label for="telefono_id" class="control-label">Telefono</label>
                    <input type="text" class="form-control" id="telefono_id" name="telefono" >
                </div>    

                <div class="form-group"> <!--Correo-->
                    <label for="correo_id" class="control-label">Correo</label>
                    <input type="text" class="form-control" id="correo_id" name="correo">
                </div>      
                <div class="form-group"> 
                    <label for="pass_id" class="control-label">Contraseña</label>
                    <input type="password" class="form-control" id="pass_id" name="pass" >
                </div>    
                <div class="form-group"> 
                    <label for="confirm_pass_id" class="control-label">Confirmar contraseña</label>
                    <input type="password" class="form-control" id="confirm_pass_id" name="confirm_pass">
                </div>    
                <div class="form-group"> 
                    <label for="dir_id" class="control-label">Dirección</label>
                    <input type="text" class="form-control" id="dir_id" name="dir" placeholder="Valle Hermoso, Bricho #213, Léon, GTO">
                </div>   
                <div class="form-group">
                    <label for="rol_id" class="control-label">Rol</label>
                    <select id="rol_id "class="browser-default custom-select">
                        <option value="" selected>Escoje el rol</option>
                        <option value="usuario">Usuario</option>
                        <option value="administrador">Administrador</option>
                    </select>     
                </div>
                <div class="form-group"> <!-- Submit Button -->
                    <button type="submit" class="btn btn-primary">Modifica!</button>
                </div>     

            </form>
        </div>
        <!--Imports necesarios para menu-->
        <%@ include file="importMenu.jsp" %>  
    </body>
</html>