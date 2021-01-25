package entidades;

import java.sql.Timestamp;

public class Movimiento {
    private int idMovimiento, idCliente;
    private String tipoMovimiento;
    private Timestamp fechaMovimiento;
    
    public Movimiento(){
        
    }

    public Movimiento(int idMovimiento, int idCliente, String tipoMovimiento, Timestamp fechaMovimiento) {
        this.idMovimiento = idMovimiento;
        this.idCliente = idCliente;
        this.tipoMovimiento = tipoMovimiento;
        this.fechaMovimiento = fechaMovimiento;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Timestamp getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Timestamp fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }
    
    
}
