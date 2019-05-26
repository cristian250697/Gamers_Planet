package entidades;

import java.sql.Timestamp;

public class Producto {

    private int idProducto;
    private String nombre;
    private String descripcion;
    private String unidad;
    private int existencia;
    private int idArea;
    private boolean statusProd;
    private int idUsrAlta;
    private Timestamp fechaAlta;
    private int idUsrMod;
    private Timestamp fechaMod;
    private float precio;

    public Producto() {
    }

    public Producto(int idProducto, String nombre, String descripcion, String unidad, int existencia, int idArea, boolean statusProd, int idUsrAlta, Timestamp fechaAlta, int idUsrMod, Timestamp fechaMod, float precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.existencia = existencia;
        this.idArea = idArea;
        this.statusProd = statusProd;
        this.idUsrAlta = idUsrAlta;
        this.fechaAlta = fechaAlta;
        this.idUsrMod = idUsrMod;
        this.fechaMod = fechaMod;
        this.precio = precio;
    }

    public Timestamp getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(Timestamp fechaMod) {
        this.fechaMod = fechaMod;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public boolean isStatusProd() {
        return statusProd;
    }

    public void setStatusProd(boolean statusProd) {
        this.statusProd = statusProd;
    }

    public int getIdUsrAlta() {
        return idUsrAlta;
    }

    public void setIdUsrAlta(int idUsrAlta) {
        this.idUsrAlta = idUsrAlta;
    }

    public Timestamp getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Timestamp fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getIdUsrMod() {
        return idUsrMod;
    }

    public void setIdUsrMod(int idUsrMod) {
        this.idUsrMod = idUsrMod;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

}
