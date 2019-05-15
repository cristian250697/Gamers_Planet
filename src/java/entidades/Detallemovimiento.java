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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MartinCoss
 */
@Entity
@Table(name = "detallemovimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallemovimiento.findAll", query = "SELECT d FROM Detallemovimiento d")
    , @NamedQuery(name = "Detallemovimiento.findByIdDetMov", query = "SELECT d FROM Detallemovimiento d WHERE d.idDetMov = :idDetMov")
    , @NamedQuery(name = "Detallemovimiento.findByOrigen", query = "SELECT d FROM Detallemovimiento d WHERE d.origen = :origen")
    , @NamedQuery(name = "Detallemovimiento.findByDestino", query = "SELECT d FROM Detallemovimiento d WHERE d.destino = :destino")})
public class Detallemovimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetMov")
    private Integer idDetMov;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "origen")
    private String origen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "destino")
    private String destino;
    @JoinColumn(name = "idMovimiento", referencedColumnName = "idMovimiento")
    @ManyToOne(optional = false)
    private Movimiento idMovimiento;

    public Detallemovimiento() {
    }

    public Detallemovimiento(Integer idDetMov) {
        this.idDetMov = idDetMov;
    }

    public Detallemovimiento(Integer idDetMov, String origen, String destino) {
        this.idDetMov = idDetMov;
        this.origen = origen;
        this.destino = destino;
    }

    public Integer getIdDetMov() {
        return idDetMov;
    }

    public void setIdDetMov(Integer idDetMov) {
        this.idDetMov = idDetMov;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Movimiento getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Movimiento idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetMov != null ? idDetMov.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallemovimiento)) {
            return false;
        }
        Detallemovimiento other = (Detallemovimiento) object;
        if ((this.idDetMov == null && other.idDetMov != null) || (this.idDetMov != null && !this.idDetMov.equals(other.idDetMov))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Detallemovimiento[ idDetMov=" + idDetMov + " ]";
    }
    
}
