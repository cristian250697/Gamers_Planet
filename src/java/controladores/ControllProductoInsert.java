package controladores;

import entidades.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.modelProducto;

public class ControllProductoInsert extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                modelProducto productoM = new modelProducto();
                String nombre = request.getParameter("name");
                String descripcion = request.getParameter("descrip");
                String unidad = request.getParameter("unidades");
                int existencia = Integer.parseInt(request.getParameter("existencia"));
                int idArea = Integer.parseInt(request.getParameter("area"));
                boolean statusProd;
                if (Integer.parseInt(request.getParameter("status")) == 1) {
                    statusProd = true;
                } else {
                    statusProd = false;
                }
                int idUsrAlta = Integer.parseInt(request.getParameter("userA"));
                java.sql.Timestamp alta = java.sql.Timestamp.valueOf(request.getParameter("date_alt"));
                java.sql.Timestamp mod = java.sql.Timestamp.valueOf(request.getParameter("date_act"));
                Timestamp fechaAlta = alta;
                int idUsrMod = Integer.parseInt(request.getParameter("userM"));
                Timestamp fechaMod = mod;
                float precio = Float.parseFloat(request.getParameter("precio"));

                Producto producto = new Producto();
                producto.setNombre(nombre);
                producto.setDescripcion(descripcion);
                producto.setUnidad(unidad);
                producto.setExistencia(existencia);
                producto.setIdArea(idArea);
                producto.setStatusProd(statusProd);
                producto.setIdUsrAlta(idUsrAlta);
                producto.setFechaAlta(fechaAlta);
                producto.setIdUsrMod(idUsrMod);
                producto.setFechaMod(fechaMod);
                producto.setPrecio(precio);
               

                if (productoM.crearProducto(producto)) {
                    response.sendRedirect("ControllProdcutoCRUD");
                } else {
                    out.println("No se creo producto");
                }
            } catch (Exception e) {
                System.out.println("Error contollProductInsert: "+e);
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
