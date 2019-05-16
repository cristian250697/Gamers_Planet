<%-- 
    Document   : contactos
    Created on : 15/05/2019, 11:57:10 PM
    Author     : trebo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="menuGamers.css">
<!DOCTYPE html>
<html>
    <head>
         <!------------- MAPA------->
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.4/dist/leaflet.css" integrity="sha512-puBpdR0798OZvTTbP4A8Ix/l+A4dHDD0DGqYW6RQ+9jxkRFclaxxQb/SJAWZfWAkuyeQUytO7+7N4QKrDh+drA==" crossorigin=""/>
   <!-- Make sure you put this AFTER Leaflet's CSS -->
   <script src="https://unpkg.com/leaflet@1.3.4/dist/leaflet.js" integrity="sha512-nMMmRyTVoLYqjP9hrbed9S+FzjZHW5gY1TWCHA5ckwXZBadntCNs8kEqAWdrb9O7rxbCaA4lKTIWjDXZxflOcA==" crossorigin=""></script>
  
    <!--------------- MAPA------->
   
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>GAMERS PLANET</title>
    </head>
    <!-- Sidebar  -->
    <%@ include file="menuCode.jsp" %>  
    <!-- Page Content  -->
    <div id="content">
        <%@ include file="menuToolBar.jsp" %>  
        <!--Aqui poner contenido de vistas-->
        <!-- Content -->
        <section>
            <div class="container">
                <div class="container">
                    <h4>GAMERS PLANET</h4>
                   <div id="mapaContacto"  style="width: 100%; height: 290px; display: block;"></div>
                    <script type="text/javascript" src="mapaContacto.js"></script> 
                    <strong>Contactenos</strong><br><strong>Oficina:</strong> 123456789
                    <hr>
                </div>
                <div class="container">
                    <form role="form" id="Formulario" action="../php/contacto2.php" method="POST">
                        <div class="form-group">
                            <label class="control-label" for="Nombre">Nombres</label>
                            <input type="text" class="form-control" id="Nombre" name="Nombre" placeholder="Introduzca su nombre" required autofocus />
                        </div>            
                        <div class="form-group">
                            <label class="control-label" for="Motivo">Motivo de Contacto</label>
                            <select name="Motivo" class="form-control">
                                <option value="Consulta General">Consulta General</option>
                                <option value="Realizar Pedido">Realizar Pedido</option>
                                <option value="Informe un problema">Informe un problema</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="Empresa">Empresa</label>
                            <input type="text" class="form-control" id="Empresa" name="Empresa" placeholder="Introduzca el nombre de su empresa" required />
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="Correo">Dirección de Correo Electrónico</label>
                            <input type="email" class="form-control" id="Correo" name="Correo" placeholder="Introduzca su correo electrónico" required />
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="Mensaje">Mensaje</label>
                            <textarea rows="5" cols="30" class="form-control" id="Mensaje" name="Mensaje" placeholder="Introduzca su mensaje" required ></textarea>
                            <input type="hidden" id="latitud" name="Latitud"/>
                            <input type="hidden" id="longitud" name="longitud" />
                        </div>
                        <div class="form-group">                
                            <input type="submit" class="btn btn-primary" onclick="obtenDatos()" value="Enviar">
                            <input type="reset" class="btn btn-default" value="Limpiar">                
                        </div>
                        <div id="respuesta" style="display: none;"></div>
                    </form>
                </div>       
            </div>
        </section>
        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="../js/jquery.min.js"><\/script>')</script>
        <script src="../js/bootstrap.min.js"></script>
        <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
        <script src="../js/holder.min.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="../js/ie10-viewport-bug-workaround.js"></script>
    </div>

    <!--Imports necesarios para menu-->
    <%@ include file="importMenu.jsp" %>  
</body>
</html>