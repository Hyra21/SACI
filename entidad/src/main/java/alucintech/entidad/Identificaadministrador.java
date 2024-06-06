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
@Table(name = "identificaadministrador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Identificaadministrador.findAll", query = "SELECT i FROM Identificaadministrador i")
    , @NamedQuery(name = "Identificaadministrador.findByNumEmpleado", query = "SELECT i FROM Identificaadministrador i WHERE i.numEmpleado = :numEmpleado")})
public class Identificaadministrador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numEmpleado")
    private Integer numEmpleado;
    @JoinColumn(name = "correoAdministrador", referencedColumnName = "correo")
    @ManyToOne(optional = false)
    private Usuarios correoAdministrador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numEmpleadoAdministradorActividad")
    private List<Actividad> actividadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numEmpleadoAdministradorEvento")
    private List<Evento> eventoList;

    public Identificaadministrador() {
    }

    public Identificaadministrador(Integer numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public Integer getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(Integer numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public Usuarios getCorreoAdministrador() {
        return correoAdministrador;
    }

    public void setCorreoAdministrador(Usuarios correoAdministrador) {
        this.correoAdministrador = correoAdministrador;
    }

    @XmlTransient
    public List<Actividad> getActividadList() {
        return actividadList;
    }

    public void setActividadList(List<Actividad> actividadList) {
        this.actividadList = actividadList;
    }

    @XmlTransient
    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numEmpleado != null ? numEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Identificaadministrador)) {
            return false;
        }
        Identificaadministrador other = (Identificaadministrador) object;
        if ((this.numEmpleado == null && other.numEmpleado != null) || (this.numEmpleado != null && !this.numEmpleado.equals(other.numEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "alucintech.entidad.Identificaadministrador[ numEmpleado=" + numEmpleado + " ]";
    }
    
}
