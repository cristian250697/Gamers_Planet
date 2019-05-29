<%-- 
    Document   : crudClientes
    Created on : 15/05/2019, 11:43:03 AM
    Author     : trebo
--%>

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
        <!-- Sidebar  -->
        <%@ include file="menuCode.jsp" %>  
        <!-- Page Content  -->
        <div id="content">
            <%@ include file="menuToolBar.jsp" %>  
            <!--Aqui poner contenido de vistas-->
            <div class="container">
                <h1>Alta Clientes</h1>
                <p>Clientes <strong>(Invitados)</strong></p>

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
                            <td><a href="clienteAdd.jsp"><button type="button" class="btn btn-success">Añadir</button></a></td>
                        </tr>
                        <%
                            List<Cliente> clientes = new ClienteDAO().getAllCliente();
                            if(clientes != null){
                                for (Cliente clien : clientes) {
                        %>
                        <tr>
                            <td><%=clien.getNombre() %></td>
                            <td><%=clien.getApellidos()%></td>
                            <td><%=clien.getTelefono() %></td>
                            <td><%=clien.getCorreo() %></td>
                            <td><%=clien.getContrasenia() %></td>
                            <td><%=clien.getDireccion() %></td>
                            <td><%=clien.getStatusCliente() %></td>
                            <td><button type="button" class="btn btn-primary">Actualizar</button></td>
                            <td><button type="button" class="btn btn-danger">Eliminar</button></td>
                        </tr>
                        <%
                                }
                            }
                        %>
                    </table>
                </div>             
            </div>
        </div>

        <!--Imports necesarios para menu-->
        <%@ include file="importMenu.jsp" %>  
    </body>
</html>