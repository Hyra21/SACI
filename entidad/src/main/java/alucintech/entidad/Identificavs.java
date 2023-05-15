/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 980014102
 */
@Entity
@Table(name = "identificavs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Identificavs.findAll", query = "SELECT i FROM Identificavs i")
    , @NamedQuery(name = "Identificavs.findByNumEmpleadoVS", query = "SELECT i FROM Identificavs i WHERE i.numEmpleadoVS = :numEmpleadoVS")})
public class Identificavs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numEmpleadoVS")
    private Integer numEmpleadoVS;
    @JoinColumn(name = "correoVS", referencedColumnName = "correo")
    @ManyToOne(optional = false)
    private Usuarios correoVS;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numEmpleadoSubdireccionFacultad")
    private List<Facultad> facultadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numEmpleadoVinculacionFacultad")
    private List<Facultad> facultadList1;

    public Identificavs() {
    }

    public Identificavs(Integer numEmpleadoVS) {
        this.numEmpleadoVS = numEmpleadoVS;
    }

    public Integer getNumEmpleadoVS() {
        return numEmpleadoVS;
    }

    public void setNumEmpleadoVS(Integer numEmpleadoVS) {
        this.numEmpleadoVS = numEmpleadoVS;
    }

    public Usuarios getCorreoVS() {
        return correoVS;
    }

    public void setCorreoVS(Usuarios correoVS) {
        this.correoVS = correoVS;
    }

    @XmlTransient
    public List<Facultad> getFacultadList() {
        return facultadList;
    }

    public void setFacultadList(List<Facultad> facultadList) {
        this.facultadList = facultadList;
    }

    @XmlTransient
    public List<Facultad> getFacultadList1() {
        return facultadList1;
    }

    public void setFacultadList1(List<Facultad> facultadList1) {
        this.facultadList1 = facultadList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numEmpleadoVS != null ? numEmpleadoVS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Identificavs)) {
            return false;
        }
        Identificavs other = (Identificavs) object;
        if ((this.numEmpleadoVS == null && other.numEmpleadoVS != null) || (this.numEmpleadoVS != null && !this.numEmpleadoVS.equals(other.numEmpleadoVS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "alucintech.entidad.Identificavs[ numEmpleadoVS=" + numEmpleadoVS + " ]";
    }
    
}
