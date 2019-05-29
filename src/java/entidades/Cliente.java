/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author fcisc
 */
public class Cliente {
    private int idCliente, statusCliente, idMovimiento, idUsrAlta, idUsrMod;
    private String nombre, apellidos, telefono, correo, contrasenia, direccion, fechaAlta, fechaMod;

    public Cliente() {
    }

    public Cliente(int idCliente, int statusCliente, int idMovimiento, int idUsrAlta, int idUsrMod, String nombre, String apellidos, String telefono, String correo, String contrasenia, String direccion) {
        this.idCliente = idCliente;
        this.statusCliente = statusCliente;
        this.idMovimiento = idMovimiento;
        this.idUsrAlta = idUsrAlta;
        this.idUsrMod = idUsrMod;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.direccion = direccion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getStatusCliente() {
        return statusCliente;
    }

    public void setStatusCliente(int statusCliente) {
        this.statusCliente = statusCliente;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getIdUsrAlta() {
        return idUsrAlta;
    }

    public void setIdUsrAlta(int idUsrAlta) {
        this.idUsrAlta = idUsrAlta;
    }

    public int getIdUsrMod() {
        return idUsrMod;
    }

    public void setIdUsrMod(int idUsrMod) {
        this.idUsrMod = idUsrMod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(String fechaMod) {
        this.fechaMod = fechaMod;
    }
    
}
