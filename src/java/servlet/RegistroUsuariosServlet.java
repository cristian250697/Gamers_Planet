/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controladores.ControladorUsuario;
import entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 52477
 */
public class RegistroUsuariosServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistroUsuariosServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistroUsuariosServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ControladorUsuario n = new ControladorUsuario(); 
        
        
       if(request.getParameter("accion").equals("0")){
           //0 es borrar
           
           String id=request.getParameter("identificador");
           
           n.eliminarUsuario(Integer.parseInt(id));
           try (PrintWriter out = response.getWriter()) {
               
            response.sendRedirect("/Gamers_Planet/crudUsuario.jsp");
            
            
        }
       }else{
           
        String nombre=request.getParameter("name");
         String apellido=request.getParameter("last_name");
         String telefono=request.getParameter("telefono");
         String correo=request.getParameter("correo");
         String contrasenia=request.getParameter("pass");
         String confirmarCont=request.getParameter("confirm_pass");
         String direccion=request.getParameter("dir");
         int rol=Integer.parseInt(request.getParameter("rol"));
         Usuario newUser = new Usuario(1,nombre,apellido,telefono,correo,contrasenia,direccion,rol,1,1,"19/05/25",1,"19/05/25");
         if(contrasenia.equals(confirmarCont)){
         if(n.crearUsuario(newUser)){
            response.sendRedirect("/Gamers_Planet/crudUsuario.jsp");
         }else{
             try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>No se pudo crear el usuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>error</h1>");
            out.println("</body>");
            out.println("</html>");
        }}
         }else{
            try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>No se pudo crear el usuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Las contraseñas no coinciden</h1>");
            out.println("<a href='/Gamers_Planet/usuarioAdd.jsp'><button >Regresar</button></a>");
            out.println("</body>");
            out.println("</html>");
        } 
         }
         }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
