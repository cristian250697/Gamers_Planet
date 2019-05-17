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
import javax.servlet.http.HttpSession;

/**
 *
 * @author crist
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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

        String usuario = request.getParameter("username");
        String contrasenia = request.getParameter("password");
        HttpSession sesion;

        ControladorUsuario cUsr = new ControladorUsuario();
        Usuario usr = cUsr.buscarUsuario(Integer.parseInt(usuario));

        if (usr != null) {                                    // Si existe el usuario
            if (usr.getContrasenia().equals(contrasenia)) {   // Si la contraseña es válida

                if (usr.getStatusUsuario() == 1) {            // Si el usuario está activo
                    // Administrador
                    if (usr.getStatusRol() == 0) {
                        sesion = request.getSession(true);
                        sesion.setAttribute("usuario", usr);
                        response.sendRedirect("perfilUsuario.jsp");
                        // Empleado
                    } else if (usr.getStatusRol() == 1) {
                        sesion = request.getSession(true);
                        sesion.setAttribute("usuario", usr);
                        response.sendRedirect("perfilUsuario.jsp");
                        // Cliente
                    } else if (usr.getStatusRol() == 2) {
                        sesion = request.getSession(true);
                        sesion.setAttribute("usuario", usr);
                        response.sendRedirect("perfilUsuario.jsp");
                    }
                } else {
                    System.err.println("Usuario Inactivo");
                    response.sendRedirect("login.jsp");
                }
            } else {
                System.err.println("Contraseña inválida");
                response.sendRedirect("login.jsp");
            }
        } else {
            System.err.println("No existe el usuario");
            response.sendRedirect("login.jsp");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet que valida el inicio de sesión";
    }// </editor-fold>

}
