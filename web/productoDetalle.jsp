<%-- 
    Document   : productoDetalle
    Created on : 23/05/2019, 08:16:42 PM
    Author     : trebo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="menuGamers.css">
<link rel="stylesheet" href="productoDetalle.css">
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
            <div class="card">
                <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                    </ol>
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img class="d-block w-75" src="ic_producto.png" style="width: 100px;height: 300px;" alt="First slide">
                        </div>
                        <div class="carousel-item">
                            <img class="d-block w-75" src="maquina1.png" style="width: 100px;height: 300px;" alt="Second slide">
                        </div>
                        <div class="carousel-item">
                            <img class="d-block w-75" src="maquina.png" style="width: 100px;height: 300px;" alt="Third slide">
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                        <span style="background-color:#CEDABC;" class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                        <span style="background-color:#CEDABC; "class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span  class="sr-only">Next</span>
                    </a>
                </div>
                <div class="container">
                    <h4><b>Producto</b></h4> 
                    <p>Departamento</p> 
                </div>
            </div>
            <div class="card_comp" style="float: left;">
                <div class="container">
                    <h4 style="text-align: center"><b>Producto nombre</b></h4> 
                    <span style="
                          margin-top: 30px;
                          margin-left: 25%;
                          font-family: monospace;
                          font-size: 60;
                          font-weight: lighter;
                          text-align: center;
                          ">$1000</span>
                    <p style="margin-top: 40px">Departamento</p>
                    <p style="margin-top: 40px">Detalle Producto</p>
                    <div>
                        <label class="box" for="quantity">Unidades: </label>
                        <input id="quantity" type="number" value="1" min="1" max="1000" step="1"/>
                    </div>
                    <div style="text-align: center">
                        <button type="button" style="margin-top: 30%" class="btn btn-primary">Comprar ahora</button>
                        <button type="button" style="margin-top: 30%" class="btn btn-success">Añadir a carrito</button>
                    </div>
                </div>
            </div>
        </div>
        <!--Imports necesarios para menu-->
        <%@ include file="importMenu.jsp" %>  
    </body>
</html>