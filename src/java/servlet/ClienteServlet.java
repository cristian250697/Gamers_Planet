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
import java.util.ArrayList;
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

    public ClienteServlet() {
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
        cdao = new ClienteDAO();
        ArrayList<Cliente> clientes = cdao.getAllCliente();
        if (request.getParameter("action") != null) {//Si se accede desde la url redirecciona al CRUDClientes
            if ("delete".equals(request.getParameter("action"))) {
                Cliente cliente = cdao.readCliente((Integer.parseInt(request.getParameter("idCliente"))));
                if (cliente != null) {
                    cdao.deleteCliente((Integer.parseInt(request.getParameter("idCliente"))));
                }
                if (clientes.isEmpty()) {
                    response.sendRedirect("/crudClientes.jsp");
                } else {
                    RequestDispatcher view = request.getRequestDispatcher("/crudClientes.jsp");
                    request.setAttribute("clientes", clientes);
                    view.forward(request, response);
                }
            } else if ("actualizar".equals(request.getParameter("action"))) {
                if (request.getParameter("idCliente") != null) {
                    Cliente cliente = cdao.readCliente(Integer.parseInt(request.getParameter("idCliente")));
                    if (cliente != null) {//Si el cliente existe, llena los campos de clienteAdd
                        RequestDispatcher view = request.getRequestDispatcher("/clienteAdd.jsp");
                        request.setAttribute("cliente", cliente);
                        view.forward(request, response);
                    }
                } else {
                    if (clientes.isEmpty()) {//Si no hay registros
                        response.sendRedirect("/crudClientes.jsp");
                    } else {//Si hay registros los envía al CRUDClientes para llenar la tabla
                        RequestDispatcher view = request.getRequestDispatcher("/crudClientes.jsp");
                        request.setAttribute("clientes", clientes);
                        view.forward(request, response);
                    }
                }
                //response.sendRedirect("clienteAdd.jsp?idCliente=" + request.getParameter("idCliente"));
                //out.println("Algo");
            } else if ("update".equals(request.getParameter("action"))) {
                if (request.getParameter("idCliente") != null) {
                    int status = (request.getParameter("rolid") == null)
                            ? 1 : Integer.parseInt(request.getParameter("rolid"));
                    Calendar fechaActual = new GregorianCalendar();
                    String hoy = fechaActual.get(Calendar.YEAR) + "-"
                            + (fechaActual.get(Calendar.MONTH) + 1) + "-"
                            + fechaActual.get(Calendar.DAY_OF_MONTH);
                    Cliente original = new ClienteDAO().readCliente(Integer.parseInt(request.getParameter("idCliente")));
                    Cliente cliente = new Cliente(
                            Integer.parseInt(request.getParameter("idCliente")),
                            status, 0, 1, 1, request.getParameter("name"),
                            request.getParameter("last_name"),
                            request.getParameter("telefono"),
                            request.getParameter("correo"),
                            request.getParameter("pass"),
                            request.getParameter("dir"),
                            original.getFechaAlta(),
                            hoy
                    );
                    cdao.updateCliente(cliente);
                }
                if (clientes.isEmpty()) {//Si no hay registros
                    response.sendRedirect("/crudClientes.jsp");
                } else {//Si hay registros los envía al CRUDClientes para llenar la tabla
                    RequestDispatcher view = request.getRequestDispatcher("/crudClientes.jsp");
                    request.setAttribute("clientes", clientes);
                    view.forward(request, response);
                }
            }
        } else {
            if (clientes.isEmpty()) {//Si no hay registros
                response.sendRedirect("/crudClientes.jsp");
            } else {//Si hay registros los envía al CRUDClientes para llenar la tabla
                RequestDispatcher view = request.getRequestDispatcher("/crudClientes.jsp");
                request.setAttribute("clientes", clientes);
                view.forward(request, response);
            }

        }

//        RequestDispatcher view = request.getRequestDispatcher("/crudClientes.jsp");
//        //request.setAttribute("clientes", cdao.getAllCliente());
//        view.forward(request, response);
    }

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
//        HttpSession session = (HttpSession) request.getSession();
//        Usuario user = (Usuario) session.getAttribute("usuario");//Obtener el usuario de la sesión
        Cliente cliente = new Cliente();
        Calendar fechaActual = new GregorianCalendar();
        String hoy = fechaActual.get(Calendar.YEAR) + "-" + (fechaActual.get(Calendar.MONTH) + 1) + "-" + fechaActual.get(Calendar.DAY_OF_MONTH);

        cliente.setNombre(request.getParameter("name"));
        cliente.setApellidos(request.getParameter("last_name"));
        cliente.setTelefono(request.getParameter("telefono"));
        cliente.setCorreo(request.getParameter("correo"));

        //Revisar la Base de datos
        cliente.setIdMovimiento(0);
        //
        cliente.setDireccion(request.getParameter("dir"));
        //Validar si seleccionó el status del cliente, si no fue así
        //asigna la cuenta como activa, 1
        if (request.getParameter("rolid") != null) {
            cliente.setStatusCliente(Integer.parseInt(request.getParameter("rolid")));
        } else {
            cliente.setStatusCliente(1);
        }
        cliente.setIdUsrAlta(1);//Usuario que creo el registro
//        cliente.setIdUsrAlta(user.getIdUsuario());
        cliente.setFechaAlta(hoy);
        cliente.setIdUsrMod(1);//Usuario que modificó el registro
//        cliente.setIdUsrMod(user.getIdUsuario());
        cliente.setFechaMod(hoy);//fecha del registro
        //String idCliente = request.getParameter("idCliente");

        //Valida que ambas contraseñas coincidan
        if (request.getParameter("pass").equals(request.getParameter("confirm_pass"))) {
            //si coinciden añade el dato a cliente
            cliente.setContrasenia(request.getParameter("pass"));
        } else {
            //en caso de no hacerlo redirecciona al form para la creación
            //con el resto de datos capturados
            RequestDispatcher view = request.getRequestDispatcher("/clienteAdd.jsp");
            request.setAttribute("cliente", cliente);
            view.forward(request, response);
        }

        cdao = new ClienteDAO();
        cdao.createCliente(cliente);//Inserta el cliente
//        if (idCliente == null) {
//            cdao.createCliente(cliente);
//        } else {
//            cliente.setIdCliente(Integer.parseInt(idCliente));
//        }
        response.sendRedirect(request.getContextPath()+"/ClienteServlet");
//        RequestDispatcher view = request.getRequestDispatcher("/crudClientes.jsp");
//        request.setAttribute("clientes", cdao.getAllCliente());
//        view.forward(request, response);
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
