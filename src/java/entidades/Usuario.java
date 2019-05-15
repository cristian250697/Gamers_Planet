/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MartinCoss
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidos = :apellidos")
    , @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono")
    , @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo")
    , @NamedQuery(name = "Usuario.findByContrasenia", query = "SELECT u FROM Usuario u WHERE u.contrasenia = :contrasenia")
    , @NamedQuery(name = "Usuario.findByDireccion", query = "SELECT u FROM Usuario u WHERE u.direccion = :direccion")
    , @NamedQuery(name = "Usuario.findByStatusRol", query = "SELECT u FROM Usuario u WHERE u.statusRol = :statusRol")
    , @NamedQuery(name = "Usuario.findByStatusUsr", query = "SELECT u FROM Usuario u WHERE u.statusUsr = :statusUsr")
    , @NamedQuery(name = "Usuario.findByFechaAlta", query = "SELECT u FROM Usuario u WHERE u.fechaAlta = :fechaAlta")
    , @NamedQuery(name = "Usuario.findByFechaMod", query = "SELECT u FROM Usuario u WHERE u.fechaMod = :fechaMod")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "contrasenia")
    private String contrasenia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "statusRol")
    private boolean statusRol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "statusUsr")
    private boolean statusUsr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaAlta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaMod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsrAlta")
    private Collection<Cliente> clienteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsrMod")
    private Collection<Cliente> clienteCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsrAlta")
    private Collection<Areaalmacen> areaalmacenCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsrMod")
    private Collection<Areaalmacen> areaalmacenCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsrAlta")
    private Collection<Usuario> usuarioCollection;
    @JoinColumn(name = "idUsrAlta", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario idUsrAlta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsrMod")
    private Collection<Usuario> usuarioCollection1;
    @JoinColumn(name = "idUsrMod", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario idUsrMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsrAlta")
    private Collection<Producto> productoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsrMod")
    private Collection<Producto> productoCollection1;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String nombre, String apellidos, String telefono, String correo, String contrasenia, String direccion, boolean statusRol, boolean statusUsr, Date fechaAlta, Date fechaMod) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.direccion = direccion;
        this.statusRol = statusRol;
        this.statusUsr = statusUsr;
        this.fechaAlta = fechaAlta;
        this.fechaMod = fechaMod;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    public boolean getStatusRol() {
        return statusRol;
    }

    public void setStatusRol(boolean statusRol) {
        this.statusRol = statusRol;
    }

    public boolean getStatusUsr() {
        return statusUsr;
    }

    public void setStatusUsr(boolean statusUsr) {
        this.statusUsr = statusUsr;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(Date fechaMod) {
        this.fechaMod = fechaMod;
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection1() {
        return clienteCollection1;
    }

    public void setClienteCollection1(Collection<Cliente> clienteCollection1) {
        this.clienteCollection1 = clienteCollection1;
    }

    @XmlTransient
    public Collection<Areaalmacen> getAreaalmacenCollection() {
        return areaalmacenCollection;
    }

    public void setAreaalmacenCollection(Collection<Areaalmacen> areaalmacenCollection) {
        this.areaalmacenCollection = areaalmacenCollection;
    }

    @XmlTransient
    public Collection<Areaalmacen> getAreaalmacenCollection1() {
        return areaalmacenCollection1;
    }

    public void setAreaalmacenCollection1(Collection<Areaalmacen> areaalmacenCollection1) {
        this.areaalmacenCollection1 = areaalmacenCollection1;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    public Usuario getIdUsrAlta() {
        return idUsrAlta;
    }

    public void setIdUsrAlta(Usuario idUsrAlta) {
        this.idUsrAlta = idUsrAlta;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection1() {
        return usuarioCollection1;
    }

    public void setUsuarioCollection1(Collection<Usuario> usuarioCollection1) {
        this.usuarioCollection1 = usuarioCollection1;
    }

    public Usuario getIdUsrMod() {
        return idUsrMod;
    }

    public void setIdUsrMod(Usuario idUsrMod) {
        this.idUsrMod = idUsrMod;
    }

    @XmlTransient
    public Collection<Producto> getProductoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
    }

    @XmlTransient
    public Collection<Producto> getProductoCollection1() {
        return productoCollection1;
    }

    public void setProductoCollection1(Collection<Producto> productoCollection1) {
        this.productoCollection1 = productoCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
