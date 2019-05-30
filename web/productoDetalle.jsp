<%-- 
    Document   : productoDetalle
    Created on : 23/05/2019, 08:16:42 PM
    Author     : trebo
--%>

<%@page import="entidades.Usuario"%>
<%@page import="entidades.Producto"%>
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
            <%                Producto producto = (Producto) request.getAttribute("Producto");
            %>
            <div class="card">
                <!--Carousel Wrapper-->
                <div id="carousel-thumb" class="carousel slide carousel-fade carousel-thumbnails" data-ride="carousel">
                    <!--Slides-->
                    <div class="carousel-inner" role="listbox">
                        <div class="carousel-item active">
                            <img class="d-block w-100" src="https://mdbootstrap.com/img/Photos/Slides/img%20(88).jpg"
                                 alt="First slide">
                        </div>
                        <div class="carousel-item">
                            <img class="d-block w-100" src="https://mdbootstrap.com/img/Photos/Slides/img%20(121).jpg"
                                 alt="Second slide">
                        </div>
                        <div class="carousel-item">
                            <img class="d-block w-100" src="https://mdbootstrap.com/img/Photos/Slides/img%20(31).jpg"
                                 alt="Third slide">
                        </div>
                    </div>
                    <!--/.Slides-->
                    <!--Controls-->
                    <a class="carousel-control-prev" href="#carousel-thumb" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carousel-thumb" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                    <!--/.Controls-->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-thumb" data-slide-to="0" class="active">
                            <img src="https://mdbootstrap.com/img/Photos/Others/Carousel-thumbs/img%20(88).jpg" width="100">
                        </li>
                        <li data-target="#carousel-thumb" data-slide-to="1">
                            <img src="https://mdbootstrap.com/img/Photos/Others/Carousel-thumbs/img%20(121).jpg" width="100">
                        </li>
                        <li data-target="#carousel-thumb" data-slide-to="2">
                            <img src="https://mdbootstrap.com/img/Photos/Others/Carousel-thumbs/img%20(31).jpg" width="100">
                        </li>
                    </ol>
                </div>
                <!--/.Carousel Wrapper-->
                <div class="container">
                    <h4><b><%=producto.getNombre()%></b></h4> 
                    <p><%=producto.getIdAreaS()%></p> 
                </div>
            </div>
            <div class="card_comp" style="float: left;">
                <div class="container">
                    <form method="post" action="Salidas">
                        <h4 style="text-align: center"><b><%=producto.getNombre()%></b></h4> 

                        <span style="
                              margin:19%;                        
                              font-family: monospace;
                              font-size: 60;
                              font-weight: lighter;

                              ">$<%=producto.getPrecio()%></span>
                        <p style="margin-top: 40px"><%=producto.getIdAreaS()%></p>
                        <p style="margin-top: 40px"><%=producto.getDescripcion()%></p>
                        <div>
                            <label class="box" for="quantity">Unidades: </label>
                            <input id="quantity" type="number" value="1" min="1" max="1000" step="1" name="cantidadProducto"/>
                        </div>
                        <div style="text-align: center; margin: 20px">
                            <input type="hidden" value="<% out.print(producto.getIdProducto());%>" name="idProducto"/>
                            
                            
                            <button type="submit" class="btn btn-primary">Comprar ahora</button>

                            <!--<a href="#myModal" class="trigger-btn" data-toggle="modal"><button type="button"  class="btn btn-success">Añadir a carrito</button></a> -->
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--Imports necesarios para menu-->
        <%@ include file="importMenu.jsp" %>  
    </body>
</html>