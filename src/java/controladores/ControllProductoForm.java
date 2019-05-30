package controladores;

import entidades.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.modelProducto;

public class ControllProductoForm extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            modelProducto productoM = new modelProducto();
            
            String action =  request.getParameter("action");
            String nombreProducto = request.getParameter("producto");
            Producto producto = productoM.searchProductoByName(nombreProducto);

            if (action.equalsIgnoreCase("update")) {

            } else if (action.equalsIgnoreCase("delete")) {
                if(productoM.eliminarProducto(producto.getIdProducto())){
                    response.sendRedirect("ControllProdcutoCRUD");
                }else{
                    out.print("no se elimino");
                }
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
