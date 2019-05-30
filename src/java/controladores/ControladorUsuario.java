package controladores;

import entidades.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ControladorUsuario {

    private AccesoBD conexion;
    boolean estadoBD;

    public ControladorUsuario() {
        conexion = new AccesoBD();
        estadoBD = conexion.conectarBD();
    }

    public boolean crearUsuario(Usuario usuario) {
        String sql= "INSERT INTO usuario(nombre, apellidos,telefono, correo,contrasenia, direccion, statusRol, statusUsr, idUsrAlta, fechaAlta, idUsrMod, fechaMod)"
                + "VALUES("
                + "'" + usuario.getNombre() + "',"
                + "'" + usuario.getApellido() + "',"
                + "'" + usuario.getTelefono() + "',"
                + "'" + usuario.getCorreo() + "',"
                + "'" + usuario.getContrasenia() + "',"
                + "'" + usuario.getDireccion() + "',"
                + usuario.getStatusRol() + ","
                + usuario.getStatusUsuario() + ","
                + usuario.getIdUsuarioAlta() + ","
                + "NOW(),"
                + usuario.getIdUsuarioModificacion() + ","
                + "NOW()"
                + ");";


        if (conexion.ejecutarSQL(sql)) {
            return true;
        } else {
            return false;
        }

    }
     public LinkedList MostrarUsuario() {
        String sql = "SELECT * FROM usuario where statusUsr = 1 ";
        LinkedList<Usuario> listaUsuario= new LinkedList<Usuario>();
        ResultSet query = conexion.ejecutarSQLSelect(sql);

        try {
            while(query.next()) {
                
                listaUsuario.add(
                new Usuario(
                query.getInt(1),
                query.getString(2),
                query.getString(3),
                query.getString(4),
                query.getString(5),
                query.getString(6),
                query.getString(7),
                query.getInt(8),
                query.getInt(9),
                query.getInt(10),
                query.getString(11),
                query.getInt(12),
                query.getString(13)));                
            }
                    
            return listaUsuario;
        } catch (SQLException sqlExc) {
            System.err.println("ERROR AL OBTENER LOS DATOS DEL USUARIO");
            return null;
        }
     }

    public Usuario buscarUsuario(int idUsuario) {
        String sql = "SELECT * FROM usuario where idUsuario=1";
        ResultSet query = conexion.ejecutarSQLSelect(sql);

        try {
            if (query.next()) {
                
                return new Usuario(
                query.getInt(1),
                query.getString(2),
                query.getString(3),
                query.getString(4),
                query.getString(5),
                query.getString(6),
                query.getString(7),
                query.getInt(8),
                query.getInt(9),
                query.getInt(10),
                query.getString(11),
                query.getInt(12),
                query.getString(13));                
                
            }else{
                return null;
            }        
            
        } catch (SQLException sqlExc) {
            System.err.println("ERROR AL OBTENER LOS DATOS DEL USUARIO");
            return null;
        }

    }

    public boolean eliminarUsuario(int idUsuario) {
        String sql = "UPDATE usuario SET statusUsr = 0 WHERE idUsuario =" + idUsuario + ";";

        if (conexion.ejecutarSQL(sql)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean actualizaUsuario(Usuario usuario) {
        System.out.println(usuario.getNombre());
        String sql = "UPDATE usuario SET nombre= '"+usuario.getNombre()+"', "
                                      + "apellidos = '"+usuario.getApellido()+"',"
                                      + "telefono = '" + usuario.getTelefono() + "',"
                                       + "correo = '" + usuario.getCorreo() + "',"
                                       + "contrasenia = '" + usuario.getContrasenia() + "',"
                                       + "direccion = '" + usuario.getDireccion() + "',"
                                         + "idUsrMod = " + usuario.getIdUsuarioModificacion()+","               
                                          + "fechaMod = NOW()"
                                         + " where idUsuario="+usuario.getIdUsuario();
                /*+ "nombre = '" + usuario.getNombre() + "',"
                + "apellidos = '" + usuario.getApellido() + "',"
                + "telefono = '" + usuario.getTelefono() + "',"
                + "correo = '" + usuario.getCorreo() + "',"
                + "contrasenia = '" + usuario.getContrasenia() + "',"
                + "direccion = '" + usuario.getDireccion() + "',"
                + "statusRol = " + usuario.getStatusRol() + ","
                + "idUsrMod = " + usuario.getIdUsuarioModificacion() + ","
                + "fechaMod = '" + usuario.getFechaModificacion() + "',"
                + " WHERE idUsuario =" + usuario.getIdUsuario() + ";";*/

        if (conexion.ejecutarSQL(sql)){
            return true;
        } else {
            return false;
        }

    }
}
