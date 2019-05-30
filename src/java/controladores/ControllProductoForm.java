package controladores;

import entidades.Area;
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
import models.modeloArea;

public class ControllProductoForm extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            modelProducto productoM = new modelProducto();

            String action = request.getParameter("action");
            String nombreProducto = request.getParameter("producto");
            Producto producto = productoM.searchProductoByName(nombreProducto);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            if (action.equalsIgnoreCase("update")) {
                request.setAttribute("Producto", producto);
                request.setAttribute("fecha", timestamp);
                request.getRequestDispatcher("productosAdd.jsp").forward(request, response);
            } else if (action.equalsIgnoreCase("delete")) {
                if (productoM.eliminarProducto(producto.getIdProducto())) {
                    response.sendRedirect("ControllProdcutoCRUD");
                } else {
                    out.print("no se elimino");
                }
            } else if (action.equalsIgnoreCase("add")) {
                modeloArea areaM = new modeloArea();
                ArrayList<Area> areas = new ArrayList<>();
                areas = areaM.getAllAreas();
                
                request.setAttribute("Areas", areas);
                request.setAttribute("fecha", timestamp);
                request.getRequestDispatcher("productosInsert.jsp").forward(request, response);
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
