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
                    <div class="container">
                        <iframe src="https://www.google.com/maps/embed?pb=!1m21!1m12!1m3!1d7889.40621095499!2d-70.23299216136742!3d8.624475075086659!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m6!3e6!4m0!4m3!3m2!1d8.626995899999999!2d-70.2301559!5e0!3m2!1ses-419!2sve!4v1456756695234" style="border:0" allowfullscreen="" frameborder="0" height="250" width="100%"></iframe>
                    </div>
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
                        </div>
                        <div class="form-group">                
                            <input type="submit" class="btn btn-primary" value="Enviar">
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