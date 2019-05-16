<%-- 
    Document   : productosAdd
    Created on : 15/05/2019, 09:59:53 PM
    Author     : trebo
--%>

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
            <form>
                <div class="form-group"> <!--Nombre-->
                    <label for="name_id" class="control-label">Nombre</label>
                    <input type="text" class="form-control" id="name_id" name="name" placeholder="Consola">
                </div>    

                <div class="form-group"> <!--Descripcion-->
                    <label for="descrip_id" class="control-label">Descripción</label>
                    <input type="text" class="form-control" id="descrip_id" name="descrip" placeholder="Articulo de tal categoria">
                </div>                    

                <div class="form-group"> <!-- Unidades-->
                    <label for="unidades_id" class="control-label">Unidades</label>
                    <input type="number" class="form-control" id="unidades_id" name="unidades" placeholder="1pz">
                </div>    

                <div class="form-group"> <!--Existecia-->
                    <label for="existencia_id" class="control-label">Existencia</label>
                    <input type="number" class="form-control" id="existencia_id" name="existencia" placeholder="23">
                </div>      
                <div class="form-group"> 
                    <label for="area_id" class="control-label">Area</label>
                    <input type="text" class="form-control" id="area_id" name="area" placeholder="Electronica">
                </div>    
                <div class="form-group">
                    <label for="rol_id" class="control-label">Estatus producto</label>
                    <select id="rol_id "class="browser-default custom-select">
                        <option value="" selected>Escoje el estatus</option>
                        <option value="1">Activo</option>
                        <option value="0">Inactivo</option>
                    </select>     
                </div>
                <div class="form-group"> 
                    <label for="date_alt_id" class="control-label">Fecha de alta</label>
                    <input type="date" class="form-control" id="date_alt_id" name="date_alt" placeholder="14/02/2019">
                </div>    
                <div class="form-group"> 
                    <label for="dir_id" class="control-label">Usuario utlima modificación</label>
                    <input type="text" class="form-control" id="dir_id" name="dir" placeholder="Valle Hermoso, Bricho #213, Léon, GTO">
                </div>   
                <div class="form-group"> 
                    <label for="date_alt_id" class="control-label">Fecha ultima modificación</label>
                    <input type="date" class="form-control" id="date_alt_id" name="date_alt" placeholder="14/02/2019">
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