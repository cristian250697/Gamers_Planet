package entidades;

/*
    Clase encapsulada del Usuario1
*/

public class Usuario1 {
    
    private int idUsuario, statusRol, statusUsuario, idUsuarioAlta, idUsuarioModificacion;
    private String nombre, apellido, telefono, correo, contrasenia, direccion, fechaAlta, fechaModificacion;
    
    public Usuario1(){
        
    }

    public Usuario1(int idUsuario, String nombre, String apellido, String telefono, String correo, String contrasenia, String direccion, int statusRol, int statusUsuario, int idUsuarioAlta, String fechaAlta, int idUsuarioModificacion, String fechaModificacion) {
        this.idUsuario = idUsuario;
        this.statusRol = statusRol;
        this.statusUsuario = statusUsuario;
        this.idUsuarioAlta = idUsuarioAlta;
        this.idUsuarioModificacion = idUsuarioModificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.direccion = direccion;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getStatusRol() {
        return statusRol;
    }

    public void setStatusRol(int statusRol) {
        this.statusRol = statusRol;
    }

    public int getStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(int statusUsuario) {
        this.statusUsuario = statusUsuario;
    }

    public int getIdUsuarioAlta() {
        return idUsuarioAlta;
    }

    public void setIdUsuarioAlta(int idUsuarioAlta) {
        this.idUsuarioAlta = idUsuarioAlta;
    }

    public int getIdUsuarioModificacion() {
        return idUsuarioModificacion;
    }

    public void setIdUsuarioModificacion(int idUsuarioModificacion) {
        this.idUsuarioModificacion = idUsuarioModificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
        
}
