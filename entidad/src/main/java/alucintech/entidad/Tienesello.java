/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 980014102
 */
@Entity
@Table(name = "tienesello")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tienesello.findAll", query = "SELECT t FROM Tienesello t")
    , @NamedQuery(name = "Tienesello.findByIdSello", query = "SELECT t FROM Tienesello t WHERE t.tieneselloPK.idSello = :idSello")
    , @NamedQuery(name = "Tienesello.findByNumFolioCarnet", query = "SELECT t FROM Tienesello t WHERE t.tieneselloPK.numFolioCarnet = :numFolioCarnet")})
public class Tienesello implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TieneselloPK tieneselloPK;
    @JoinColumn(name = "idSello", referencedColumnName = "idSello", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sello sello;
    @JoinColumn(name = "numFolioCarnet", referencedColumnName = "numFolio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Carnet carnet;

    public Tienesello() {
    }

    public Tienesello(TieneselloPK tieneselloPK) {
        this.tieneselloPK = tieneselloPK;
    }

    public Tienesello(int idSello, int numFolioCarnet) {
        this.tieneselloPK = new TieneselloPK(idSello, numFolioCarnet);
    }

    public TieneselloPK getTieneselloPK() {
        return tieneselloPK;
    }

    public void setTieneselloPK(TieneselloPK tieneselloPK) {
        this.tieneselloPK = tieneselloPK;
    }
    
    public Sello getSello() {
        return sello;
    }

    public void setSello(Sello sello) {
        this.sello = sello;
    }

    public Carnet getCarnet() {
        return carnet;
    }

    public void setCarnet(Carnet carnet) {
        this.carnet = carnet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tieneselloPK != null ? tieneselloPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tienesello)) {
            return false;
        }
        Tienesello other = (Tienesello) object;
        if ((this.tieneselloPK == null && other.tieneselloPK != null) || (this.tieneselloPK != null && !this.tieneselloPK.equals(other.tieneselloPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "alucintech.entidad.Tienesello[ tieneselloPK=" + tieneselloPK + " ]";
    }
    
}
