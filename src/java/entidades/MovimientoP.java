
package entidades;

import java.sql.Timestamp;


public class MovimientoP {
    
    private int idMovimientoProducto;
    private int idUsuario;
    private String tipoMovimiento;
    private Timestamp fechaMov;

    public MovimientoP() {
    }

    public MovimientoP(int idMovimientoProducto, int idUsuario, String tipoMovimiento, Timestamp fechaMov) {
        this.idMovimientoProducto = idMovimientoProducto;
        this.idUsuario = idUsuario;
        this.tipoMovimiento = tipoMovimiento;
        this.fechaMov = fechaMov;
    }

    public Timestamp getFechaMov() {
        return fechaMov;
    }

    public void setFechaMov(Timestamp fechaMov) {
        this.fechaMov = fechaMov;
    }

    public int getIdMovimientoProducto() {
        return idMovimientoProducto;
    }

    public void setIdMovimientoProducto(int idMovimientoProducto) {
        this.idMovimientoProducto = idMovimientoProducto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }
    
    
}
