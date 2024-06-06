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
@Table(name = "identificacoordinador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Identificacoordinador.findAll", query = "SELECT i FROM Identificacoordinador i")
    , @NamedQuery(name = "Identificacoordinador.findByNumEmpleadoCoordinador", query = "SELECT i FROM Identificacoordinador i WHERE i.numEmpleadoCoordinador = :numEmpleadoCoordinador")})
public class Identificacoordinador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numEmpleadoCoordinador")
    private Integer numEmpleadoCoordinador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numEmpleadoCoordinadorProgramaEducativo")
    private List<Programaeducativo> programaeducativoList;
    @JoinColumn(name = "correoCoordinador", referencedColumnName = "correo")
    @ManyToOne(optional = false)
    private Usuarios correoCoordinador;

    public Identificacoordinador() {
    }

    public Identificacoordinador(Integer numEmpleadoCoordinador) {
        this.numEmpleadoCoordinador = numEmpleadoCoordinador;
    }

    public Integer getNumEmpleadoCoordinador() {
        return numEmpleadoCoordinador;
    }

    public void setNumEmpleadoCoordinador(Integer numEmpleadoCoordinador) {
        this.numEmpleadoCoordinador = numEmpleadoCoordinador;
    }

    @XmlTransient
    public List<Programaeducativo> getProgramaeducativoList() {
        return programaeducativoList;
    }

    public void setProgramaeducativoList(List<Programaeducativo> programaeducativoList) {
        this.programaeducativoList = programaeducativoList;
    }

    public Usuarios getCorreoCoordinador() {
        return correoCoordinador;
    }

    public void setCorreoCoordinador(Usuarios correoCoordinador) {
        this.correoCoordinador = correoCoordinador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numEmpleadoCoordinador != null ? numEmpleadoCoordinador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Identificacoordinador)) {
            return false;
        }
        Identificacoordinador other = (Identificacoordinador) object;
        if ((this.numEmpleadoCoordinador == null && other.numEmpleadoCoordinador != null) || (this.numEmpleadoCoordinador != null && !this.numEmpleadoCoordinador.equals(other.numEmpleadoCoordinador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "alucintech.entidad.Identificacoordinador[ numEmpleadoCoordinador=" + numEmpleadoCoordinador + " ]";
    }
    
}
