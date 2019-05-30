
package models;

import controladores.AccesoBD;
import entidades.Movimiento;
import entidades.MovimientoP;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author trebo
 */
public class ModelMovimientoP {
    private AccesoBD conexion;
    boolean estadoBD;
    String query;

    public ModelMovimientoP() {
        conexion = new AccesoBD();
        estadoBD = conexion.conectarBD();
        query = "";

    }

    public boolean crearMovimiento(MovimientoP movimiento) {
        query = "";
        query = "INSERT INTO movimientoP(idUsuario,tipoMovimiento,fechaMov)"
                + "VALUES("
                + movimiento.getIdUsuario() + ","
                + "'" + movimiento.getTipoMovimiento() + "',"
                + "NOW());";

        if (conexion.ejecutarSQL(query)) {
            return true;
        } else {
            return false;
        }

    }

    public LinkedList getMovimientos() {
        String sql = "SELECT * FROM movimientoP";
        LinkedList<Movimiento> movimientos = new LinkedList<Movimiento>();
        ResultSet query = conexion.ejecutarSQLSelect(sql);

        try {
            while (query.next()) {

                movimientos.add(
                        new Movimiento(
                                query.getInt(1),
                                query.getInt(2),
                                query.getString(3),
                                query.getTimestamp(4)));
            }

            return movimientos;
        } catch (SQLException sqlExc) {
            System.err.println("ERROR AL OBTENER LOS DATOS DEL MOVIMIENTO");
            return null;
        }
    }

//    public Movimiento buscarMovimiento(int idMovimiento) {
//        String sql = "SELECT * FROM movimiento where idMovimento=" +idMovimiento;
//        ResultSet query = conexion.ejecutarSQLSelect(sql);
//
//        try {
//            if (query.next()) {
//
//                return new Movimiento(
//                        query.getInt(1),
//                        query.getInt(2),
//                        query.getString(3),
//                        query.getTimestamp(4));
//
//            } else {
//                return null;
//            }
//
//        } catch (SQLException sqlExc) {
//            System.err.println("ERROR AL OBTENER LOS DATOS DEL MOVIMIENTO");
//            return null;
//        }
//
//    }

    public boolean eliminarMovimiento(int idMovimiento) {
        String sql = "DELETE FROM movimientoP WHERE idMovimientoProducto=" + idMovimiento + ";";

        if (conexion.ejecutarSQL(sql)) {
            return true;
        } else {
            return false;
        }
    }

//    public boolean actualizaMovimiento(Movimiento movimiento) {
//        String sql = "UPDATE movimiento SET tipoMovimiento= '" + movimiento.getTipoMovimiento() + "', "
//                + " where idMovimiento=" + movimiento.getIdMovimiento();
//
//        if (conexion.ejecutarSQL(sql)) {
//            return true;
//        } else {
//            return false;
//        }
//
//    }
}
