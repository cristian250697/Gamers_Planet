package controladores;

import entidades.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.modelProducto;

public class ControllProductoAdd extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                modelProducto productoM = new modelProducto();
                int idProducto = Integer.parseInt(request.getParameter("id_prd"));
                String nombre = request.getParameter("name");
                String descripcion = request.getParameter("descrip");
                String unidad = request.getParameter("unidades");
                int existencia = Integer.parseInt(request.getParameter("existencia"));
                int idArea = Integer.parseInt(request.getParameter("id_area"));
                boolean statusProd;
                if (Integer.parseInt(request.getParameter("status")) == 1) {
                    statusProd = true;
                } else {
                    statusProd = false;
                }
                int idUsrAlta = Integer.parseInt(request.getParameter("id_usrA"));
                java.sql.Timestamp alta = java.sql.Timestamp.valueOf(request.getParameter("date_alt"));
                java.sql.Timestamp mod = java.sql.Timestamp.valueOf(request.getParameter("date_act"));
                Timestamp fechaAlta = alta;
                int idUsrMod = Integer.parseInt(request.getParameter("id_userM"));
                Timestamp fechaMod = mod;
                float precio = Float.parseFloat(request.getParameter("precio"));

                Producto producto = new Producto(idProducto, nombre, descripcion, unidad, existencia, idArea, statusProd, idUsrAlta, fechaAlta, idUsrMod, fechaMod, precio);

                if (productoM.actualizaProducto(producto)) {
                    response.sendRedirect("ControllProdcutoCRUD");
                } else {
                    out.println("No se actualizo");
                }

                //java.sql.Timestamp ts = java.sql.Timestamp.valueOf(date);
//                out.print(date);
            } catch (Exception e) {
                System.out.println("error->: " + e);
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
