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
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto")
    , @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Producto.findByUnidad", query = "SELECT p FROM Producto p WHERE p.unidad = :unidad")
    , @NamedQuery(name = "Producto.findByExistencia", query = "SELECT p FROM Producto p WHERE p.existencia = :existencia")
    , @NamedQuery(name = "Producto.findByStatusProd", query = "SELECT p FROM Producto p WHERE p.statusProd = :statusProd")
    , @NamedQuery(name = "Producto.findByFechaAlta", query = "SELECT p FROM Producto p WHERE p.fechaAlta = :fechaAlta")
    , @NamedQuery(name = "Producto.findByFechaMod", query = "SELECT p FROM Producto p WHERE p.fechaMod = :fechaMod")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProducto")
    private Integer idProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "unidad")
    private String unidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "existencia")
    private int existencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "statusProd")
    private boolean statusProd;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private Collection<Movimientoproducto> movimientoproductoCollection;
    @JoinColumn(name = "idArea", referencedColumnName = "idArea")
    @ManyToOne(optional = false)
    private Areaalmacen idArea;
    @JoinColumn(name = "idUsrAlta", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario idUsrAlta;
    @JoinColumn(name = "idUsrMod", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario idUsrMod;

    public Producto() {
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(Integer idProducto, String nombre, String descripcion, String unidad, int existencia, boolean statusProd, Date fechaAlta, Date fechaMod) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.existencia = existencia;
        this.statusProd = statusProd;
        this.fechaAlta = fechaAlta;
        this.fechaMod = fechaMod;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
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

    public boolean getStatusProd() {
        return statusProd;
    }

    public void setStatusProd(boolean statusProd) {
        this.statusProd = statusProd;
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
    public Collection<Movimientoproducto> getMovimientoproductoCollection() {
        return movimientoproductoCollection;
    }

    public void setMovimientoproductoCollection(Collection<Movimientoproducto> movimientoproductoCollection) {
        this.movimientoproductoCollection = movimientoproductoCollection;
    }

    public Areaalmacen getIdArea() {
        return idArea;
    }

    public void setIdArea(Areaalmacen idArea) {
        this.idArea = idArea;
    }

    public Usuario getIdUsrAlta() {
        return idUsrAlta;
    }

    public void setIdUsrAlta(Usuario idUsrAlta) {
        this.idUsrAlta = idUsrAlta;
    }

    public Usuario getIdUsrMod() {
        return idUsrMod;
    }

    public void setIdUsrMod(Usuario idUsrMod) {
        this.idUsrMod = idUsrMod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Producto[ idProducto=" + idProducto + " ]";
    }
    
}
