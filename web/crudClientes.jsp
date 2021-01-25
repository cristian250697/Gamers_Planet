<%-- 
    Document   : crudClientes
    Created on : 15/05/2019, 11:43:03 AM
    Author     : trebo
--%>
<%@page import="entidades.Usuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="controladores.ClienteDAO"%>
<%@page import="entidades.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
        <%

            HttpSession sesion = request.getSession();
            Usuario usr = (Usuario) sesion.getAttribute("usuario");

        %>
        <!-- Sidebar  -->
        <%@ include file="menuCode.jsp" %>  
        <!-- Page Content  -->
        <div id="content">
            <%@ include file="menuToolBar.jsp" %>  
            <!--Aqui poner contenido de vistas-->
            <div class="container">
                <h1>Alta Clientes</h1>
                <p>Usuario: <strong><%                    if (usr.getStatusRol() == 0) {
                        out.println("Administrador");
                    } else if (usr.getStatusRol() == 1) {
                        out.println("Empleado");
                    } else if (usr.getStatusRol() == 2) {
                        out.println("Cliente");
                    }


                        %>

                    </strong></p>

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
                            <th>Estatus</th>
                            <% if(usr.getStatusRol() == 0){ %>
                            <td><a href="clienteAdd.jsp"><button type="button" class="btn btn-success">Añadir</button></a></td>
                            <%} %>
                        </tr>


                        <c:set var="existe" value="false" />
                        <c:if test = "${clientes != null}">

                            <c:forEach var="cliente" items="${clientes}">
                                <c:if test = "${cliente.getStatusCliente() == 1}">
                                    <tr>
                                        <td><c:out value="${cliente.getNombre()}"/></td>
                                        <td><c:out value="${cliente.getApellidos()}"/></td>
                                        <td><c:out value="${cliente.getTelefono()}"/></td>
                                        <td><c:out value="${cliente.getCorreo()}"/></td>
                                        <td><c:out value="${cliente.getContrasenia()}"/></td>
                                        <td><c:out value="${cliente.getDireccion()}"/></td>
                                        <td><c:out value="${cliente.getStatusCliente()}"/></td>
                                        <td></td>
                                        <% if(usr.getStatusRol() == 0){ %>
                                        <td><a href="ClienteServlet?action=actualizar&idCliente=${cliente.getIdCliente()}"><button type="button" class="btn btn-primary">Actualizar</button></a></td>
                                        <td><a href="ClienteServlet?action=delete&idCliente=${cliente.getIdCliente()}"><button type="button" class="btn btn-danger">Eliminar</button></a></td>
                                        <%} %>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </table>
                </div>             
            </div>
        </div>

        <!--Imports necesarios para menu-->
        <%@ include file="importMenu.jsp" %>  
    </body>
</html>