<%-- 
    Document   : productosAdd
    Created on : 15/05/2019, 09:59:53 PM
    Author     : trebo
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="entidades.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="menuGamers.css">
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
            <div class="form-group">
                <h1>Edita Productos</h1>
            </div> 
            <form action="ControllProductoAdd" method="post">
                <% Producto producto = (Producto) request.getAttribute("Producto");
                    Timestamp fechaActual = (Timestamp) request.getAttribute("fecha");                    
                %>
                <input type="hidden" name="id_prd" value="<%=producto.getIdProducto()%>">
                <div class="form-group"> <!--Nombre-->
                    <label for="name_id" class="control-label">Nombre</label>
                    <input type="text" class="form-control" id="name_id" name="name" placeholder="Consola" value="<%=producto.getNombre()%>">
                </div>    

                <div class="form-group"> <!--Descripcion-->
                    <label for="descrip_id" class="control-label">Descripción</label>
                    <input type="text" class="form-control" id="descrip_id" name="descrip" placeholder="Articulo de tal categoria" value="<%=producto.getDescripcion()%>">
                </div>                    

                <div class="form-group"> <!-- Unidades-->
                    <label for="unidades_id" class="control-label">Unidades</label>
                    <input type="text" class="form-control" id="unidades_id" name="unidades" placeholder="1pz" value="<%=producto.getUnidad()%>" >
                </div>  
                <div class="form-group"> <!-- Unidades-->
                    <label for="precio" class="control-label">Precio</label>
                    <input type="text" class="form-control" id="precio" name="precio" placeholder="$000" value="<%=producto.getPrecio()%>" >
                </div> 

                <div class="form-group"> <!--Existecia-->
                    <label for="existencia_id" class="control-label">Existencia</label>
                    <input type="number" class="form-control" id="existencia_id" name="existencia" placeholder="23" value="<%=producto.getExistencia()%>">
                </div>      
                <input type="hidden" name="id_area" value="<%=producto.getIdArea()%>">
                <div class="form-group"> 
                    <label for="area_id" class="control-label">Area</label>
                    <input type="text" class="form-control" id="area_id" name="area" placeholder="Electronica" value="<%=producto.getIdAreaS()%>" readonly>
                </div>    
                <div class="form-group">
                    <label for="rol_id" class="control-label">Estatus producto</label>
                    <%String status = "";
                        int valor = 0;
                        if (producto.isStatusProd()) {
                            status = "Activo";
                            valor = 1;
                        } else {
                            status = "Inactivo";
                            valor = 0;
                        }%>
                    <select id="rol_id " name="status" class="browser-default custom-select">
                        <option value="<%=valor%>" selected><%=status%></option>
                        <option value="1">Activo</option>
                        <option value="0">Inactivo</option>
                    </select>     
                </div>
                <input type="hidden" name="id_usrA" value="<%=producto.getIdUsrAlta()%>">
                <div class="form-group"> 
                    <label for="dirA_id" class="control-label">Usuario Alta producto</label>
                    <input type="number" class="form-control" id="dirA_id" name="userA" placeholder="Usuario" value="<%=producto.getIdUsrAltaS()%>" readonly>
                </div>   
                <div class="form-group"> 
                    <label for="date_alt_id" class="control-label">Fecha de alta</label>
                    <input type="text" class="form-control" id="date_alt_id" name="date_alt" placeholder="14/02/2019" value="<%=producto.getFechaAlta()%>" readonly>
                </div>    
                <input type="hidden" name="id_userM" value="<%=producto.getIdUsrMod()%>">
                <div class="form-group"> 
                    <label for="dir_id" class="control-label">Usuario utlima modificación</label>
                    <input type="number" class="form-control" id="dir_id" name="userM" placeholder="Usuario" value="<%=producto.getIdUsrModS()%>" readonly>
                </div>   
                <div class="form-group"> 
                    <label for="date_act_id" class="control-label">Fecha ultima modificación</label>
                    <input type="datetime" class="form-control" id="date_act_id" name="date_act" placeholder="14/02/2019" value="<%=fechaActual%>" readonly>
                </div>    
                <div class="form-group"> <!-- Submit Button -->
                    <button type="submit" class="btn btn-primary">Done!</button>
                </div>     
            </form>
        </div>
        <!--Imports necesarios para menu-->
        <%@ include file="importMenu.jsp" %>  
    </body>
</html>