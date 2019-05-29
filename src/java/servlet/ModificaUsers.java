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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 52477
 */
public class ModificaUsers extends HttpServlet {

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
        int id=Integer.parseInt(request.getParameter("ident"));
        ControladorUsuario n = new ControladorUsuario(); 
         Usuario user=n.buscarUsuario(id);
         
         if(!request.getParameter("name").isEmpty()){
             user.setNombre(request.getParameter("name"));
         }
         if(!request.getParameter("last_name").isEmpty()){
             user.setApellido(request.getParameter("last_name"));
         }
         if(!request.getParameter("telefono").isEmpty()){
             user.setTelefono(request.getParameter("telefono"));
         }
         if(!request.getParameter("correo").isEmpty()){
             user.setCorreo(request.getParameter("correo"));
         }
         if(!request.getParameter("pass").isEmpty()){
             user.setContrasenia(request.getParameter("pass"));
         }
         if(!request.getParameter("dir").isEmpty()){
             user.setDireccion(request.getParameter("dir"));
         }
         
         if(n.actualizaUsuario(user)){
            response.sendRedirect("/Gamers_Planet/crudUsuario.jsp");
         }else{
            try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ModificaUsers</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>no</h1>");
            out.println("</body>");
            out.println("</html>");
        }
         }
        
       /* try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ModificaUsers</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>usuario modificado!</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
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
