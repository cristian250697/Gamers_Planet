package controladores;

import entidades.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.modelProducto;

public class ControllProdcutoCRUD extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            modelProducto productoM = new modelProducto();
            ArrayList<Producto> productos = productoM.getAllProducts();
            
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            request.setAttribute("Productos", productos);
            request.setAttribute("fecha", timestamp);
            request.getRequestDispatcher("crudProductos.jsp").forward(request, response);
//
//            if (action.equalsIgnoreCase("add")) {
//
//            } else if (action.equalsIgnoreCase("update")) {
//
//            }
//            if (action.equalsIgnoreCase("delete")) {
//
//            }
        }
    }

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
        processRequest(request, response);
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
