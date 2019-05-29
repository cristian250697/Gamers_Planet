/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controladores.ClienteDAO;
import entidades.Cliente;
import entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fcisc
 */
public class ClienteServlet extends HttpServlet {

    private ClienteDAO cdao;
    
    public ClienteServlet(){
        super();
        //cdao = new ClienteDAO();
    }
    
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
            out.println("<title>Servlet ClienteServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClienteServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        HttpSession session = (HttpSession)request.getSession();
        Usuario user = (Usuario)session.getAttribute("usuario");
        Cliente cliente = new Cliente();
        Calendar fechaActual = new GregorianCalendar();
        String hoy = fechaActual.get(Calendar.YEAR)+"-"+(fechaActual.get(Calendar.MONTH)+1)+"-"+fechaActual.get(Calendar.DAY_OF_MONTH);
        
        cliente.setNombre(request.getParameter("name"));
        cliente.setApellidos(request.getParameter("last_name"));
        cliente.setTelefono(request.getParameter("telefono"));
        cliente.setCorreo(request.getParameter("correo"));
        cliente.setContrasenia(request.getParameter("pass"));
        //Revisar la Base de datos
        cliente.setIdMovimiento(0);
        //
        cliente.setDireccion(request.getParameter("dir"));
        cliente.setStatusCliente(Integer.parseInt(request.getParameter("rolid")));
        cliente.setIdUsrAlta(1);
//        cliente.setIdUsrAlta(user.getIdUsuario());
        cliente.setFechaAlta(hoy);
        cliente.setIdUsrMod(1);
//        cliente.setIdUsrMod(user.getIdUsuario());
        cliente.setFechaMod(hoy);
        String idCliente = request.getParameter("idCliente");
        
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ClienteServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.print("nombre: ");
//            out.println(request.getParameter("name"));
//            out.print("rol: ");
//            out.println(request.getParameter("rolid"));
//            out.println("</body>");
//            out.println("</html>");
//        }
        cdao = new ClienteDAO();
        if(idCliente == null){
            cdao.createCliente(cliente);
        }else{
            cliente.setIdCliente(Integer.parseInt(idCliente));
        }
        RequestDispatcher view = request.getRequestDispatcher("/crudClientes.jsp");
        request.setAttribute("clientes", cdao.getAllCliente());
        view.forward(request, response);
        
//         nombre,apellidos,telefono,correo,contrasenia,idMovimiento,direccion,statusCliente,
//         idUsrAlta,fechaAlta,idUsrMod, fechaMod
        
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
