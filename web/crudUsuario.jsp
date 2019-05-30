<%-- 
    Document   : crudUsuario
    Created on : 15/05/2019, 11:42:28 AM
    Author     : trebo
--%>

<%@page import="entidades.Usuario"%>
<%@page import="java.util.LinkedList"%>
<%@page import="controladores.ControladorUsuario"%>
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
                <h1>Alta Usuarios</h1>
                <p>Usuarios <strong>(Empleados)</strong></p>
                <form>
                <p>Busquedas</p>
                Id    <input type="text" name="idUser">
                <button type="submit">buscar</button>
                </form>
                
                <div id="table" class="table-editable">
                    <span class="table-add glyphicon glyphicon-plus"></span>
                    <form action="Modify" method="POST">
                    <table class="table">
                        <tr>
                            <th>id</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Telefono</th>
                            <th>Correo</th>
                            <th>Contraseña</th>
                            <th>Dirección</th>
                            <th>Rol</th>
                            <td><a href="usuarioAdd.jsp"><button type="button"  class="btn btn-success">Añadir</button></a></td>
                            
                             <td><button type='submit' name="actUser" value='1'  class='btn btn-primary'>Actualizar</button></td>
                        </tr>
                        <tr>
                            
                            <%
                                LinkedList<Usuario> lista=null;
                                 String idUser=request.getParameter("idUser");
                    
                                ControladorUsuario usuario = new ControladorUsuario();
                                if(idUser!=null){
                                   Usuario us= usuario.buscarUsuario(Integer.parseInt(idUser));
                                    String rol="";
                                if(us.getStatusRol()==0){
                                    rol="administrador";
                                }else if(us.getStatusRol()==1){
                                    rol="usuario";
                                }else{
                                    rol="cliente";
                                }
                               out.println("<tr>");
                               
                               out.println("<td>"+us.getIdUsuario()+"</td>");
                               out.println("<td>"+us.getNombre()+"</td>");
                               out.println("<td>"+us.getApellido()+"</td>");
                               out.println("<td>"+us.getTelefono()+"</td>");
                               out.println("<td>"+us.getCorreo()+"</td>");
                               out.println("<td>"+us.getContrasenia()+"</td>");
                               out.println("<td>"+us.getDireccion()+"</td>");
                               out.println("<td>"+rol+"</td>"); 
                               out.println("<input type='hidden' name='accion' value='0'");                                                              
                               out.println("<input type='text' name='id' value='"+us.getIdUsuario()+"'>");                              
                               out.println("<td><button type='submit' name='identificador' value='" +us.getIdUsuario()+"'  onclick='this.form.action=\"RegistroUsuariosServlet\"' class='btn btn-danger' >Eliminar</button></td>");                              
                            
                                    
                           }else{
                            lista = usuario.MostrarUsuario();
                            for (int i=0;i<lista.size();i++)
                            {
                                String rol="";
                                if(lista.get(i).getStatusRol()==0){
                                    rol="administrador";
                                }else if(lista.get(i).getStatusRol()==1){
                                    rol="usuario";
                                }else{
                                    rol="cliente";
                                }
                               out.println("<tr>");
                               
                               out.println("<td>"+lista.get(i).getIdUsuario()+"</td>");
                               out.println("<td>"+lista.get(i).getNombre()+"</td>");
                               out.println("<td>"+lista.get(i).getApellido()+"</td>");
                               out.println("<td>"+lista.get(i).getTelefono()+"</td>");
                               out.println("<td>"+lista.get(i).getCorreo()+"</td>");
                               out.println("<td>"+lista.get(i).getContrasenia()+"</td>");
                               out.println("<td>"+lista.get(i).getDireccion()+"</td>");
                               out.println("<td>"+rol+"</td>"); 
                               out.println("<input type='hidden' name='accion' value='0'");                                                              
                               out.println("<input type='text' name='id' value='"+lista.get(i).getIdUsuario()+"'>");                              
                               out.println("<td><button type='submit' name='identificador' value='" +lista.get(i).getIdUsuario()+"'  onclick='this.form.action=\"RegistroUsuariosServlet\"' class='btn btn-danger' >Eliminar</button></td>");                              
                            }
                            %>
                            
                             
                               <%
                            out.println("</tr>");
                            }
%>

                        </tr>
                    </table>
                    </form>
                </div>             
            </div>
        </div>

        <!--Imports necesarios para menu-->
        <%@ include file="importMenu.jsp" %>  
    </body>
</html>