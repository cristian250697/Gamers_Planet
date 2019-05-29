/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import controladores.ControladorUsuario;
import web.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
        String output ="";
        String json="";
        String usuario = request.getParameter("username");
        String contrasenia = request.getParameter("password");
        HttpSession sesion;
        try {
            URL url = new URL("http://localhost:8080/Gamers_Planet/webresources/web.usuario/" + usuario);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
             }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

        
          
            while ((output = br.readLine()) != null) {
                json+= output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();

        }
        System.out.println(json);
        Gson g = new Gson();
        Usuario usr = g.fromJson(json, Usuario.class);
        
        System.out.println(usr.getStatusRol()+" Rol");
        System.out.println(usr.getStatusUsr()+" Estatus");
        System.out.println(usr.toString());

        if (usr != null) {                                    // Si existe el usuario
            if (usr.getContrasenia().equals(contrasenia)) {   // Si la contraseña es válida

                if (usr.getStatusUsr()) {            // Si el usuario está activo
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
