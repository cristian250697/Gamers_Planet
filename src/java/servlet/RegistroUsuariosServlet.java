/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controladores.ClienteDAO;
import controladores.ControladorUsuario;
import entidades.Usuario;
import entidades.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author 52477
 */
public class RegistroUsuariosServlet extends HttpServlet {

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
            out.println("<title>Servlet RegistroUsuariosServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistroUsuariosServlet at " + request.getContextPath() + "</h1>");
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
        ControladorUsuario n = new ControladorUsuario(); 
        
        
       if(request.getParameter("accion").equals("0")){
           //0 es borrar
           
           String id=request.getParameter("identificador");
           
           n.eliminarUsuario(Integer.parseInt(id));
           try (PrintWriter out = response.getWriter()) {
               
            response.sendRedirect("/Gamers_Planet/crudUsuario.jsp");
            
            
        }
       }else{
           
        String nombre=request.getParameter("name");
         String apellido=request.getParameter("last_name");
         String telefono=request.getParameter("telefono");
         String correo=request.getParameter("correo");
         String contrasenia=request.getParameter("pass");
         String confirmarCont=request.getParameter("confirm_pass");
         String direccion=request.getParameter("dir");
         int rol=Integer.parseInt(request.getParameter("rol"));
         
            
         if(contrasenia.equals(confirmarCont)){
             if(rol==2){
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
            RequestDispatcher view = request.getRequestDispatcher("usuarioAdd.jsp");
            request.setAttribute("cliente", cliente);
            view.forward(request, response);
        }

       ClienteDAO cdao = new ClienteDAO();
        cdao.createCliente(cliente);
        response.sendRedirect("/Gamers_Planet/crudUsuario.jsp");
                 
             
             
         }else{
                 Usuario newUser = new Usuario(1,nombre,apellido,telefono,correo,contrasenia,direccion,rol,1,1,"19/05/25",1,"19/05/25");
        
             
         if(n.crearUsuario(newUser)){
            response.sendRedirect("/Gamers_Planet/crudUsuario.jsp");
         }else{
             try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>No se pudo crear el usuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>error</h1>");
            out.println("</body>");
            out.println("</html>");
          }
       }
         }
         }else{
            try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>No se pudo crear el usuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Las contraseñas no coinciden</h1>");
            out.println("<a href='/Gamers_Planet/usuarioAdd.jsp'><button >Regresar</button></a>");
            out.println("</body>");
            out.println("</html>");
            } 
         }
       
         }
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
