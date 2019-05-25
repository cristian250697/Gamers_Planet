<%-- 
    Document   : compraProducto
    Created on : 21/05/2019, 10:32:10 PM
    Author     : trebo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="menuGamers.css">
<link rel="stylesheet" href="compraProducto.css">
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
            <%for (int i = 0; i < 6; i++) {
            %>
            <div class="card">
                <a id="link" href="productoDetalle.jsp">
                <img id="ic_car" src="icon_add_cart.png" alt="Avatar">
                <img  id="ic_prod" src="ic_producto.png" alt="Avatar" >
                <div class="container">
                    <h4><b>Producto</b></h4> 
                    <p>Departamento</p> 
                    <span style=" margin-left: 80%; margin-bottom: 50px;">$1000</span>
                </div>
                </a>
            </div>
            <%
                }
            %>
        </div>
        <!--Imports necesarios para menu-->
        <%@ include file="importMenu.jsp" %>  
    </body>
</html>