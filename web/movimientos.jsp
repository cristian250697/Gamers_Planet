<%-- 
    Document   : movimientos
    Created on : 15/05/2019, 11:44:05 AM
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
                            <th>Cliente</th>
                            <th>Tipo de movimiento</th>
                            <th>Fecha movimiento</th>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>Juan Silvestre</td>
                            <td>Compra</td>
                            <td>12/01/2019</td>                          
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