/*
 * Clase utilizada para controlar la conexión a la Base de Datos
 */
package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class AccesoBD {

    //Se crea un objeto de la clase conexion
    public Connection conexion = null;
    private Statement sentencia = null;

    /**
     * Constructor de la clase
     */
    public AccesoBD() {
    }

    /**
     * Crear conexión con la Base de datos
     *
     * @return estado de la conexión
     */
    public boolean conectarBD() {
        //con el objeto conexion 
        try {
            //se carga el driver para realizar la conexión a la bd con Class.froName
            //con la cadena depende de la gestión de la base de datos a utilizar
            //El Driver se tiene que agregar al proyecto web en Llibraries
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gamers_Planet", "root", "MyNewPass");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
    }

    /**
     * Ejecutar instrucciones SQL INSERT, UPDATE y DELETE Realiza alguna
     * instrucción a la base de datos
     *
     * @param sql instrucción a ejecutar
     * @return estado de la acción
     */
    public boolean ejecutarSQL(String sql) {
        try {
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    /**
     * Ejecutar instrucción SELECT SQL Regresa la información de la base de
     * datos
     *
     * @param sql isntrucción a ejecutar
     * @return resultado de la consulta
     */
    public ResultSet ejecutarSQLSelect(String sql) {

        try {
            sentencia = conexion.createStatement();
            return sentencia.executeQuery(sql);
        } catch (SQLException ex) {
            return null;
        }
    }

    /**
     * Ejecuta una transacción
     *
     * @param sql instrucciones sql a enviar a la BD
     * @return estado de la operación
     */
    public boolean ejecutarTransaccion(String[] sql) {
        boolean estado = true;
        try {
            sentencia = conexion.createStatement();
            conexion.setAutoCommit(false);
            for (String sql1 : sql) {
                sentencia.executeUpdate(sql1);
                JOptionPane.showMessageDialog(null, "Caída del sistema");
            }
            conexion.commit();
        } catch (SQLException ex) {
            try {
                estado = false;
                conexion.rollback();
            } catch (SQLException ex1) {
            }
        } finally {
            try {
                conexion.setAutoCommit(true);
                return estado;
            } catch (SQLException ex) {
                return estado;
            }
        }
    }

    /**
     * Cierra la conexión con la base de datos
     */
    public void cerrarConexion() {
        try {
            conexion.close();
            sentencia.close();
        } catch (SQLException e) {
        }
    }
}
