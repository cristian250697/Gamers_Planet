/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MartinCoss
 */
@Entity
@Table(name = "movimientoproducto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimientoproducto.findAll", query = "SELECT m FROM Movimientoproducto m")
    , @NamedQuery(name = "Movimientoproducto.findByIdMovimientoProducto", query = "SELECT m FROM Movimientoproducto m WHERE m.idMovimientoProducto = :idMovimientoProducto")})
public class Movimientoproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMovimientoProducto")
    private Integer idMovimientoProducto;
    @JoinColumn(name = "idMovimiento", referencedColumnName = "idMovimiento")
    @ManyToOne(optional = false)
    private Movimiento idMovimiento;
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")
    @ManyToOne(optional = false)
    private Producto idProducto;

    public Movimientoproducto() {
    }

    public Movimientoproducto(Integer idMovimientoProducto) {
        this.idMovimientoProducto = idMovimientoProducto;
    }

    public Integer getIdMovimientoProducto() {
        return idMovimientoProducto;
    }

    public void setIdMovimientoProducto(Integer idMovimientoProducto) {
        this.idMovimientoProducto = idMovimientoProducto;
    }

    public Movimiento getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Movimiento idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovimientoProducto != null ? idMovimientoProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimientoproducto)) {
            return false;
        }
        Movimientoproducto other = (Movimientoproducto) object;
        if ((this.idMovimientoProducto == null && other.idMovimientoProducto != null) || (this.idMovimientoProducto != null && !this.idMovimientoProducto.equals(other.idMovimientoProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Movimientoproducto[ idMovimientoProducto=" + idMovimientoProducto + " ]";
    }
    
}
