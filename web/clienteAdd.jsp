<%-- 
    Document   : clienteAdd
    Created on : 15/05/2019, 09:38:43 PM
    Author     : trebo
--%>

<%@page import="controladores.ClienteDAO"%>
<%@page import="entidades.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <h1>Edita Clientes</h1>
            </div> 
            
            <c:set var="existe" value="false" />
            <c:if test = "${cliente != null}">
                <c:set var="existe" value="true" />
            </c:if>
            
            <form action="ClienteServlet" method="${existe ? "get":"post"}">
                <div class="form-group"> <!--Nombre-->
                    <label for="name_id" class="control-label">Nombre</label>
                    <input required="true" type="text" class="form-control" id="name_id" name="name" placeholder="José Benito" value="${existe ? cliente.getNombre():""}" >
                </div>    


                <div class="form-group"> <!--Apellido-->
                    <label for="last_name_id" class="control-label">Apellido</label>
                    <input required="true" type="text" class="form-control" id="last_name_id" name="last_name" placeholder="Rosas Macano" value="${existe ? cliente.getApellidos():""}">
                </div>                    

                <div class="form-group"> <!-- Telefono-->
                    <label for="telefono_id" class="control-label">Telefono</label>
                    <input required="true" type="text" class="form-control" id="telefono_id" name="telefono" placeholder="4771110022" value="${existe ? cliente.getTelefono():""}">
                </div>    

                <div class="form-group"> <!--Correo-->
                    <label for="correo_id" class="control-label">Correo</label>
                    <input required="true" type="text" class="form-control" id="correo_id" name="correo" placeholder="something@example.com" value="${existe ? cliente.getCorreo():""}">
                </div>      
                <div class="form-group"> 
                    <label for="pass_id" class="control-label">Contraseña</label>
                    <input required="true" type="password" class="form-control" id="pass_id" name="pass" placeholder="YY#$asj01Qw" >
                </div>    
                <div class="form-group"> 
                    <label for="confirm_pass_id" class="control-label">Confirmar contraseña</label>
                    <input required="true" type="password" class="form-control" id="confirm_pass_id" name="confirm_pass" placeholder="YY#$asj01Qw">
                </div>    
                <div class="form-group"> 
                    <label for="dir_id" class="control-label">Dirección</label>
                    <input required="true" type="text" class="form-control" id="dir_id" name="dir" placeholder="Valle Hermoso, Bricho #213, Léon, GTO" value="${existe ? cliente.getDireccion():""}">
                </div>   
                <div class="form-group">
                    <label for="rol_id" class="control-label">Estatus</label>
                    <select id="rol_id" name="rolid" class="browser-default custom-select">
                        <option value="" selected disabled>Escoje el estatus</option>
                        <option value="1">Activo</option>
                        <option value="0">Inactivo</option>
                    </select>     
                </div>
                <div class="form-group"> <!-- Submit Button -->
                    
                    ${existe ?  "<input type=hidden name=idCliente value=".concat(cliente.getIdCliente()).concat(">"):""}
                    ${existe ?  "<input type=hidden name=action value=update>":""}
                    
                    <input type="submit" class="btn btn-primary" ${existe ? "value=\"update\" name=\"update\"" :""}>
                </div>     
            </form>
        </div>
        <!--Imports necesarios para menu-->
        <%@ include file="importMenu.jsp" %>  
    </body>
</html>