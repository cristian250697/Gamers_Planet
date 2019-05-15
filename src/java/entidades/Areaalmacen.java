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
@Table(name = "areaalmacen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Areaalmacen.findAll", query = "SELECT a FROM Areaalmacen a")
    , @NamedQuery(name = "Areaalmacen.findByIdArea", query = "SELECT a FROM Areaalmacen a WHERE a.idArea = :idArea")
    , @NamedQuery(name = "Areaalmacen.findByNombre", query = "SELECT a FROM Areaalmacen a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Areaalmacen.findByFechaAlta", query = "SELECT a FROM Areaalmacen a WHERE a.fechaAlta = :fechaAlta")
    , @NamedQuery(name = "Areaalmacen.findByFechaMod", query = "SELECT a FROM Areaalmacen a WHERE a.fechaMod = :fechaMod")})
public class Areaalmacen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idArea")
    private Integer idArea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
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
    @JoinColumn(name = "idUsrAlta", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario idUsrAlta;
    @JoinColumn(name = "idUsrMod", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario idUsrMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArea")
    private Collection<Producto> productoCollection;

    public Areaalmacen() {
    }

    public Areaalmacen(Integer idArea) {
        this.idArea = idArea;
    }

    public Areaalmacen(Integer idArea, String nombre, Date fechaAlta, Date fechaMod) {
        this.idArea = idArea;
        this.nombre = nombre;
        this.fechaAlta = fechaAlta;
        this.fechaMod = fechaMod;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @XmlTransient
    public Collection<Producto> getProductoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArea != null ? idArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Areaalmacen)) {
            return false;
        }
        Areaalmacen other = (Areaalmacen) object;
        if ((this.idArea == null && other.idArea != null) || (this.idArea != null && !this.idArea.equals(other.idArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Areaalmacen[ idArea=" + idArea + " ]";
    }
    
}
