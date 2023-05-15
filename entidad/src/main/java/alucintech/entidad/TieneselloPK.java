/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author 980014102
 */
@Embeddable
public class TieneselloPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idSello")
    private int idSello;
    @Basic(optional = false)
    @Column(name = "numFolioCarnet")
    private int numFolioCarnet;

    public TieneselloPK() {
    }

    public TieneselloPK(int idSello, int numFolioCarnet) {
        this.idSello = idSello;
        this.numFolioCarnet = numFolioCarnet;
    }

    public int getIdSello() {
        return idSello;
    }

    public void setIdSello(int idSello) {
        this.idSello = idSello;
    }

    public int getNumFolioCarnet() {
        return numFolioCarnet;
    }

    public void setNumFolioCarnet(int numFolioCarnet) {
        this.numFolioCarnet = numFolioCarnet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idSello;
        hash += (int) numFolioCarnet;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TieneselloPK)) {
            return false;
        }
        TieneselloPK other = (TieneselloPK) object;
        if (this.idSello != other.idSello) {
            return false;
        }
        if (this.numFolioCarnet != other.numFolioCarnet) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "alucintech.entidad.TieneselloPK[ idSello=" + idSello + ", numFolioCarnet=" + numFolioCarnet + " ]";
    }
    
}
