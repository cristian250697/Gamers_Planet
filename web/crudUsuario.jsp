<%-- 
    Document   : crudUsuario
    Created on : 15/05/2019, 11:42:28 AM
    Author     : trebo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="menuGamers.css">
<link rel="stylesheet" href="table.css">
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
            <div class="container">
                <h1>Alta Usuarios</h1>
                <p>Usuarios <strong>(Empleados)</strong></p>

                <ul>
                    <li>Edita con responsabilidad.</li> 
                    <li>Trabaja duro.</li>
                </ul>

                <div id="table" class="table-editable">
                    <span class="table-add glyphicon glyphicon-plus"></span>
                    <table class="table">
                        <tr>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Telefono</th>
                            <th>Correo</th>
                            <th>Contraseña</th>
                            <th>Dirección</th>
                            <th>Rol</th>
                            <td><a href="usuarioAdd.jsp"><button type="button" class="btn btn-success">Añadir</button></a></td>
                        </tr>
                        <tr>
                            <td>Juan Silvestre</td>
                            <td>Ramírez Becerra</td>
                            <td>4774408656</td>
                            <td>juansilvestre@gmail.com</td>
                            <td>ASJ3343#joYw</td>
                            <td>Valle Hermoso, Bricho #213, Léon, GTO</td>
                            <td>Administrador</td>
                            <td><button type="button" class="btn btn-primary">Actualizar</button></td>
                            <td><button type="button" class="btn btn-danger">Eliminar</button></td>


                        </tr>
                    </table>
                </div>             
            </div>
        </div>

        <!--Imports necesarios para menu-->
        <%@ include file="importMenu.jsp" %>  
    </body>
</html>