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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "facultad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facultad.findAll", query = "SELECT f FROM Facultad f")
    , @NamedQuery(name = "Facultad.findByIdFacultad", query = "SELECT f FROM Facultad f WHERE f.idFacultad = :idFacultad")
    , @NamedQuery(name = "Facultad.findByNombreFacultad", query = "SELECT f FROM Facultad f WHERE f.nombreFacultad = :nombreFacultad")})
public class Facultad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idFacultad")
    private Integer idFacultad;
    @Basic(optional = false)
    @Column(name = "nombreFacultad")
    private String nombreFacultad;
    @JoinTable(name = "afinfacultad", joinColumns = {
        @JoinColumn(name = "idFacultad", referencedColumnName = "idFacultad")}, inverseJoinColumns = {
        @JoinColumn(name = "idEvento", referencedColumnName = "idEvento")})
    @ManyToMany
    private List<Evento> eventoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFacultadProgramaEducativo")
    private List<Programaeducativo> programaeducativoList;
    @JoinColumn(name = "numEmpleadoSubdireccionFacultad", referencedColumnName = "numEmpleadoVS")
    @ManyToOne(optional = false)
    private Identificavs numEmpleadoSubdireccionFacultad;
    @JoinColumn(name = "numEmpleadoVinculacionFacultad", referencedColumnName = "numEmpleadoVS")
    @ManyToOne(optional = false)
    private Identificavs numEmpleadoVinculacionFacultad;

    public Facultad() {
    }

    public Facultad(Integer idFacultad) {
        this.idFacultad = idFacultad;
    }

    public Facultad(Integer idFacultad, String nombreFacultad) {
        this.idFacultad = idFacultad;
        this.nombreFacultad = nombreFacultad;
    }

    public Integer getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(Integer idFacultad) {
        this.idFacultad = idFacultad;
    }

    public String getNombreFacultad() {
        return nombreFacultad;
    }

    public void setNombreFacultad(String nombreFacultad) {
        this.nombreFacultad = nombreFacultad;
    }

    @XmlTransient
    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    @XmlTransient
    public List<Programaeducativo> getProgramaeducativoList() {
        return programaeducativoList;
    }

    public void setProgramaeducativoList(List<Programaeducativo> programaeducativoList) {
        this.programaeducativoList = programaeducativoList;
    }

    public Identificavs getNumEmpleadoSubdireccionFacultad() {
        return numEmpleadoSubdireccionFacultad;
    }

    public void setNumEmpleadoSubdireccionFacultad(Identificavs numEmpleadoSubdireccionFacultad) {
        this.numEmpleadoSubdireccionFacultad = numEmpleadoSubdireccionFacultad;
    }

    public Identificavs getNumEmpleadoVinculacionFacultad() {
        return numEmpleadoVinculacionFacultad;
    }

    public void setNumEmpleadoVinculacionFacultad(Identificavs numEmpleadoVinculacionFacultad) {
        this.numEmpleadoVinculacionFacultad = numEmpleadoVinculacionFacultad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFacultad != null ? idFacultad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facultad)) {
            return false;
        }
        Facultad other = (Facultad) object;
        if ((this.idFacultad == null && other.idFacultad != null) || (this.idFacultad != null && !this.idFacultad.equals(other.idFacultad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "alucintech.entidad.Facultad[ idFacultad=" + idFacultad + " ]";
    }
    
}
