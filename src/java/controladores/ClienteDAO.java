/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fcisc
 */
public class ClienteDAO {
    /**
     * Operación para añadir nuevo registro Cliente
     * 
     * @param cliente
     */
    public void createCliente(Cliente cliente){
        AccesoBD abd = new AccesoBD();
        if(abd.conectarBD()){   //Conectar a la base datos
            Connection c = abd.conexion;
            //Crear query a ser procesada
            String query = 
                    "insert cliente(nombre,apellidos,telefono,correo,contrasenia,idMovimiento,direccion,"
                    + "statusCliente,idUsrAlta,fechaAlta,idUsrMod, fechaMod) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?);";
            try {
                //Preparar query y asignar los campos
                //Query para crear nuevo registro de Cliente
                PreparedStatement ps = c.prepareStatement(query);
                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getApellidos());
                ps.setString(3, cliente.getTelefono());
                ps.setString(4, cliente.getCorreo());
                ps.setString(5, cliente.getContrasenia());
                ps.setInt(6, cliente.getIdMovimiento());
                ps.setString(7, cliente.getDireccion());
                ps.setInt(8, cliente.getStatusCliente());
                ps.setInt(9, cliente.getIdUsrAlta());
                ps.setString(10, cliente.getFechaAlta());
                ps.setInt(11, cliente.getIdUsrMod());
                ps.setString(12, cliente.getFechaMod());
                //Ejecutar query
                ps.executeUpdate();
                //Cerrar conexion
//                abd.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    /**
     * Buscar Cliente por medio del id
     * @param idCliente
     */
    public Cliente readCliente(int idCliente){
        AccesoBD abd = new AccesoBD();
        Cliente cliente = new Cliente();
        if(abd.conectarBD()){   //Conectar a la base datos
            Connection c = abd.conexion;
            //Crear query a ser procesada
            String query = 
                    "select * from cliente where idCliente = ?";
            try {
                PreparedStatement ps = c.prepareStatement(query);
                ps.setInt(1, idCliente);
                //Se almacena el resultado del query
                ResultSet resultSet = ps.executeQuery();
                if(resultSet.next()){//Si Obtuvo algún registro
                    //Asignar a cliente los datos obtenidos por medio del resultSet
                    //Por medio del nombre de la columna
                    cliente.setIdCliente(resultSet.getInt("idCliente"));
                    cliente.setNombre(resultSet.getString("nombre"));
                    cliente.setApellidos(resultSet.getString("apellidos"));
                    cliente.setTelefono(resultSet.getString("telefono"));
                    cliente.setCorreo(resultSet.getString("correo"));
                    cliente.setContrasenia(resultSet.getString("contrasenia"));
                    cliente.setDireccion(resultSet.getString("direccion"));
                    cliente.setStatusCliente(resultSet.getInt("statusCliente"));
                    cliente.setIdMovimiento(resultSet.getInt("idMovimiento"));
                    cliente.setIdUsrAlta(resultSet.getInt("idUsrAlta"));
                    cliente.setFechaAlta(resultSet.getString("fechaAlta"));
                    cliente.setIdUsrMod(resultSet.getInt("idUsrMod"));
                    cliente.setFechaMod(resultSet.getString("fechaMod"));
                }
//                abd.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cliente;
    }
    
    /**
     * Actualizar información de un Cliente
     * @param cliente 
     */
    public void updateCliente(Cliente cliente){
        AccesoBD abd = new AccesoBD();
        if(abd.conectarBD()){   //Conectar a la base datos
            Connection c = abd.conexion;
            //Crear query a ser procesada
            String query = 
                    "update cliente set nombre = ?, apellidos = ?, telefono = ?,"
                    + " correo = ?, contrasenia = ?, idMovimiento = ?, direccion = ?,"
                    + " statusCliente = ?, idUsrAlta = ?, fechaAlta = ?, idUsrMod = ?, "
                    + "fechaMod = ? where idCliente = ?";
            try {
                //Preparar query y asignar los campos
                //Query para crear nuevo registro de Cliente
                PreparedStatement ps = c.prepareStatement(query);
                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getApellidos());
                ps.setString(3, cliente.getTelefono());
                ps.setString(4, cliente.getCorreo());
                ps.setString(5, cliente.getContrasenia());
                ps.setInt(6, cliente.getIdMovimiento());
                ps.setString(7, cliente.getDireccion());
                ps.setInt(8, cliente.getStatusCliente());
                ps.setInt(9, cliente.getIdUsrAlta());
                ps.setString(10, cliente.getFechaAlta());
                ps.setInt(11, cliente.getIdUsrMod());
                ps.setString(12, cliente.getFechaMod());
                ps.setInt(13, cliente.getIdCliente());
                //Ejecutar query
                ps.executeUpdate();
                //Cerrar conexion
                //abd.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    
    
    /**
     * Eliminar un cliente por medio de su id
     * Borrado lógico
     * @param idCliente 
     */
    public void deleteCliente(int idCliente){
        AccesoBD abd = new AccesoBD();
        if(abd.conectarBD()){   //Conectar a la base datos
            Connection c = abd.conexion;
            //Crear query a ser procesada
            //Query para eliminar por medio del idCliente
            String query = 
                    "update cliente set statusCliente=0 where idCliente=?";
              try {
                  PreparedStatement ps = c.prepareStatement(query);
                  ps.setInt(1, idCliente);
                  //Ejecutar query
                  ps.executeUpdate();
                  //Cerrar conexion
//                  abd.cerrarConexion();
              } catch (SQLException ex) {
                  Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
              }
        }
    }
    
    
    public ArrayList<Cliente> getAllCliente(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        AccesoBD abd = new AccesoBD();
        if(abd.conectarBD()){   //Conectar a la base datos
            Connection c = abd.conexion;
            //Crear query a ser procesada
            //Query para eliminar por medio del idCliente
            String query = 
                    "select * from cliente";
            try{
                ResultSet resultSet = abd.ejecutarSQLSelect(query);
                while(resultSet.next()){
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(resultSet.getInt("idCliente"));
                    cliente.setNombre(resultSet.getString("nombre"));
                    cliente.setApellidos(resultSet.getString("apellidos"));
                    cliente.setTelefono(resultSet.getString("telefono"));
                    cliente.setCorreo(resultSet.getString("correo"));
                    cliente.setContrasenia(resultSet.getString("contrasenia"));
                    cliente.setDireccion(resultSet.getString("direccion"));
                    cliente.setStatusCliente(resultSet.getInt("statusCliente"));
                    cliente.setIdMovimiento(resultSet.getInt("idMovimiento"));
                    cliente.setIdUsrAlta(resultSet.getInt("idUsrAlta"));
                    cliente.setFechaAlta(resultSet.getString("fechaAlta"));
                    cliente.setIdUsrMod(resultSet.getInt("idUsrMod"));
                    cliente.setFechaMod(resultSet.getString("fechaMod"));
                    clientes.add(cliente);
                }
            }catch(SQLException sQLException){
                
            }
        }
        return clientes;
    }
}
