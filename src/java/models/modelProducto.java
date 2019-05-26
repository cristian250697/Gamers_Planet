package models;

import controladores.AccesoBD;
import entidades.Producto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class modelProducto {

    private AccesoBD conexion;
    boolean estadoBD;
    String query;

    public modelProducto() {
        conexion = new AccesoBD();
        estadoBD = conexion.conectarBD();
        query = "";

    }

    public boolean crearProducto(Producto producto) {
        query = "INSERT INTO producto(nombre,descripcion,unidad,existencia,idArea,statusProd,idUsrAlta,fechaAlta,idUsrMod,fechaMod,precio)"
                + "VALUES("
                + "'" + producto.getNombre() + "',"
                + "'" + producto.getDescripcion() + "',"
                + "'" + producto.getUnidad() + "',"
                + producto.getExistencia() + ","
                + producto.getIdArea() + ","
                + producto.isStatusProd() + ","
                + producto.getIdUsrAlta() + ","
                + producto.getFechaAlta() + ","
                + producto.getIdUsrMod() + ","
                + producto.getFechaMod() + ","
                + producto.getPrecio() + ");";

        if (conexion.ejecutarSQL(query)) {
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<Producto> getAllProducts() {
        ArrayList<Producto> productos = new ArrayList<>();

        query = "SELECT * FROM producto";
        ResultSet response = conexion.ejecutarSQLSelect(query);
        if (response != null) {
            try {
                boolean status = false;
                while (response.next()) {
                    Producto producto = new Producto();
                    producto.setIdProducto(response.getInt(1));
                    producto.setNombre(response.getString(2));
                    producto.setDescripcion(response.getString(3));
                    producto.setUnidad(response.getString(4));
                    producto.setExistencia(response.getInt(5));
                    producto.setIdArea(response.getInt(6));
                    if (response.getInt(7) == 0) {
                        status = false;
                    } else if (response.getInt(7) == 1) {
                        status = true;
                    }
                    producto.setStatusProd(status);
                    producto.setIdUsrAlta(response.getInt(8));
                    producto.setFechaAlta(response.getTimestamp(9));
                    producto.setIdUsrMod(response.getInt(10));
                    producto.setFechaMod(response.getTimestamp(11));
                    producto.setPrecio(response.getFloat(12));
                    productos.add(producto);
                }
                conexion.cerrarConexion();

            } catch (SQLException e) {
                conexion.cerrarConexion();
                System.out.println("fallo traer todos los productos");
            }
            return productos;
        } else {
            return productos;
        }
    }

    private String getIdToString(int id) {
        query = "";
        return "";
    }

//    public Producto buscarProducto(int idProdcuto) {
//        String query = "SELECT * FROM usuario WHERE idUsuario = " + idUsuario;
//        ResultSet query = conexion.ejecutarSQLSelect(query);
//
//        try {
//            if (query.next()) {
//                
//                return new Usuario(
//                query.getInt(1),
//                query.getString(2),
//                query.getString(3),
//                query.getString(4),
//                query.getString(5),
//                query.getString(6),
//                query.getString(7),
//                query.getInt(8),
//                query.getInt(9),
//                query.getInt(10),
//                query.getString(11),
//                query.getInt(12),
//                query.getString(13));                
//                
//            }else{
//                return null;
//            }        
//            
//        } catch (SQLException sqlExc) {
//            System.err.println("ERROR AL OBTENER LOS DATOS DEL USUARIO");
//            return null;
//        }
//
//    }
//
//    public boolean eliminarUsuario(int idUsuario) {
//        String query = "UPDATE usuario SET statusUsr = 0 WHERE idUsuario =" + idUsuario + ";";
//
//        if (conexion.ejecutarSQL(query)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public boolean actualizaUsuario(Usuario usuario) {
//
//        String query = "UPDATE usuario SET "
//                + "nombre = '" + usuario.getNombre() + "',"
//                + "apellidos = '" + usuario.getApellido() + "',"
//                + "telefono = '" + usuario.getTelefono() + "',"
//                + "correo = '" + usuario.getCorreo() + "',"
//                + "contrasenia = '" + usuario.getContrasenia() + "',"
//                + "direccion = '" + usuario.getDireccion() + "',"
//                + "statusRol = " + usuario.getStatusRol() + ","
//                + "idUsrMod = " + usuario.getIdUsuarioModificacion() + ","
//                + "fechaMod = '" + usuario.getFechaModificacion() + "',"
//                + " WHERE idUsuario =" + usuario.getIdUsuario() + ";";
//
//        if (conexion.ejecutarSQL(query)){
//            return true;
//        } else {
//            return false;
//        }
//
//    }
}
