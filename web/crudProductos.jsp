<%-- 
    Document   : crudProductos
    Created on : 15/05/2019, 11:43:17 AM
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
                <h1>Alta Productos</h1>
                <p>Productos <strong>(Mercancía)</strong></p>

                <ul>
                    <li>Edita con responsabilidad.</li> 
                    <li>Trabaja duro.</li>
                </ul>

                <div id="table" class="table-editable">
                    <span class="table-add glyphicon glyphicon-plus"></span>
                    <table class="table">
                        <tr>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>Unidad(s)</th>
                            <th>Existencia</th>
                            <th>Area</th>
                            <th>Estatus producto</th>
                            <th>Fecha de alta</th>
                            <th>Usuario ultima modificación</th>
                            <th>Fecha ultima modificación</th>
                            <td><a href="productosAdd.jsp"><button type="button" class="btn btn-success">Añadir</button></a></td>
                        </tr>
                        <tr>
                            <td>Xbox 360</td>
                            <td>Consola de Microsoft corporation</td>
                            <td>1pz</td>
                            <td>30</td>
                            <td>Juegos</td>
                            <td>Activo</td>
                            <td>15/05/2019</td>
                            <td>Juan Silvestre</td>
                            <td>15/05/2019</td>
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
