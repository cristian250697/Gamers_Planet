<%-- 
    Document   : crudProductos
    Created on : 15/05/2019, 11:43:17 AM
    Author     : trebo
--%>

<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Producto"%>
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
                            <td><a href="ControllProductoCRUD?action=add"><button type="button" class="btn btn-success">Añadir</button></a></td>
                        </tr>
                        <%
                            ArrayList<Producto> productos = (ArrayList<Producto>) request.getAttribute("Productos");
                            Timestamp fechaActual = (Timestamp) request.getAttribute("fecha");
                            for (Producto producto : productos) {

                        %>
                        <tr>
                            <td><%=producto.getNombre()%></td>
                            <td><%=producto.getDescripcion()%></td>
                            <td><%=producto.getUnidad()%></td>
                            <td><%=producto.getExistencia()%></td>
                            <td><%=producto.getIdAreaS()%></td>
                            <%
                                String status = "";
                                if (producto.isStatusProd()) {
                                    status = "Activo";
                                } else {
                                    status = "Inactivo";
                                }
                            %>
                            <td><%=status%></td>
                            <td><%=producto.getFechaAlta()%></td>
                            <td>Aqui validas crisitan</td>
                            <td><%=fechaActual%></td>
                            <td><a href="ControllProductoCRUD?action=update"><button type="button" class="btn btn-primary">Actualizar</button></a></td>
                            <td><a href="ControllProductoCRUD?action=delete"><button type="button" class="btn btn-danger">Eliminar</button></a></td>
                        </tr>
                        <%                                }
                        %>

                    </table>
                </div>             
            </div>
        </div>

        <!--Imports necesarios para menu-->
        <%@ include file="importMenu.jsp" %>  
    </body>
</html>
