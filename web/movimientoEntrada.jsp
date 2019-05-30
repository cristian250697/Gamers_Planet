<%-- 
    Document   : movimientos
    Created on : 15/05/2019, 11:44:05 AM
    Author     : trebo
--%>

<%@page import="entidades.Usuario"%>
<%@page import="models.ModelMovimientoP"%>
<%@page import="entidades.MovimientoP"%>
<%@page import="models.ModelMovimiento"%>
<%@page import="entidades.Movimiento"%>
<%@page import="java.util.LinkedList"%>
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
            <%
                Usuario usr = (Usuario) request.getSession().getAttribute("usuario");    
            %>
            <%@ include file="menuToolBar.jsp" %>  
            <!--Aqui poner contenido de vistas-->
            <div class="container">
                <h1>Movimientos</h1>
                <p>Movimientos <strong>(Transacciones)</strong></p>

                <ul>             
                    <li>Trabaja duro.</li>
                </ul>

                <div id="table" class="table-editable">
                    <span class="table-add glyphicon glyphicon-plus"></span>
                    <table class="table">
                        <tr>
                            <th>ID movimiento</th>
                            <th>Usuario</th>
                            <th>Tipo de movimiento</th>
                            <th>Fecha movimiento</th>
                        </tr>
                        <%

                            LinkedList<MovimientoP> movimientos;

                            ModelMovimientoP modelo = new ModelMovimientoP();

                            movimientos = modelo.getMovimientos();

                            for (int i = 0; i < movimientos.size(); i++) {


                        %>
                        <tr>
                            <td><%=movimientos.get(i).getIdMovimientoProducto()%></td>
                            <td><%=movimientos.get(i).getIdUsuario()%></td>
                            <td><%=movimientos.get(i).getTipoMovimiento()%></td>
                            <td><%=movimientos.get(i).getFechaMov()%></td>                          
                            <%
                                if(usr.getStatusRol() == 0){
                                %>
                            <td><a href="ControllMovimientoP?id=<%=movimientos.get(i).getIdMovimientoProducto()%>"<button type="button" class="btn btn-danger">Eliminar</button></a></td>
                            <%
                                }
                            %>
                        </tr>
                        <%}%>
                    </table>
                </div>             
            </div>
        </div>
        <!--Imports necesarios para menu-->
        <%@ include file="importMenu.jsp" %>  
    </body>
</html>