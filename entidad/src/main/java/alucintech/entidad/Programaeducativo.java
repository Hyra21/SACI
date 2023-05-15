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
@Table(name = "programaeducativo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programaeducativo.findAll", query = "SELECT p FROM Programaeducativo p")
    , @NamedQuery(name = "Programaeducativo.findByCodigoProgramaEducativo", query = "SELECT p FROM Programaeducativo p WHERE p.codigoProgramaEducativo = :codigoProgramaEducativo")
    , @NamedQuery(name = "Programaeducativo.findByNombreProgramaEducativo", query = "SELECT p FROM Programaeducativo p WHERE p.nombreProgramaEducativo = :nombreProgramaEducativo")})
public class Programaeducativo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigoProgramaEducativo")
    private Integer codigoProgramaEducativo;
    @Basic(optional = false)
    @Column(name = "nombreProgramaEducativo")
    private String nombreProgramaEducativo;
    @JoinTable(name = "afinprogramaseducativos", joinColumns = {
        @JoinColumn(name = "codigoProgramaEducativo", referencedColumnName = "codigoProgramaEducativo")}, inverseJoinColumns = {
        @JoinColumn(name = "idActividad", referencedColumnName = "idActividad")})
    @ManyToMany
    private List<Actividad> actividadList;
    @JoinColumn(name = "idFacultadProgramaEducativo", referencedColumnName = "idFacultad")
    @ManyToOne(optional = false)
    private Facultad idFacultadProgramaEducativo;
    @JoinColumn(name = "numEmpleadoCoordinadorProgramaEducativo", referencedColumnName = "numEmpleadoCoordinador")
    @ManyToOne(optional = false)
    private Identificacoordinador numEmpleadoCoordinadorProgramaEducativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoProgramaEducativoAlumno")
    private List<Alumno> alumnoList;

    public Programaeducativo() {
    }

    public Programaeducativo(Integer codigoProgramaEducativo) {
        this.codigoProgramaEducativo = codigoProgramaEducativo;
    }

    public Programaeducativo(Integer codigoProgramaEducativo, String nombreProgramaEducativo) {
        this.codigoProgramaEducativo = codigoProgramaEducativo;
        this.nombreProgramaEducativo = nombreProgramaEducativo;
    }

    public Integer getCodigoProgramaEducativo() {
        return codigoProgramaEducativo;
    }

    public void setCodigoProgramaEducativo(Integer codigoProgramaEducativo) {
        this.codigoProgramaEducativo = codigoProgramaEducativo;
    }

    public String getNombreProgramaEducativo() {
        return nombreProgramaEducativo;
    }

    public void setNombreProgramaEducativo(String nombreProgramaEducativo) {
        this.nombreProgramaEducativo = nombreProgramaEducativo;
    }

    @XmlTransient
    public List<Actividad> getActividadList() {
        return actividadList;
    }

    public void setActividadList(List<Actividad> actividadList) {
        this.actividadList = actividadList;
    }

    public Facultad getIdFacultadProgramaEducativo() {
        return idFacultadProgramaEducativo;
    }

    public void setIdFacultadProgramaEducativo(Facultad idFacultadProgramaEducativo) {
        this.idFacultadProgramaEducativo = idFacultadProgramaEducativo;
    }

    public Identificacoordinador getNumEmpleadoCoordinadorProgramaEducativo() {
        return numEmpleadoCoordinadorProgramaEducativo;
    }

    public void setNumEmpleadoCoordinadorProgramaEducativo(Identificacoordinador numEmpleadoCoordinadorProgramaEducativo) {
        this.numEmpleadoCoordinadorProgramaEducativo = numEmpleadoCoordinadorProgramaEducativo;
    }

    @XmlTransient
    public List<Alumno> getAlumnoList() {
        return alumnoList;
    }

    public void setAlumnoList(List<Alumno> alumnoList) {
        this.alumnoList = alumnoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoProgramaEducativo != null ? codigoProgramaEducativo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programaeducativo)) {
            return false;
        }
        Programaeducativo other = (Programaeducativo) object;
        if ((this.codigoProgramaEducativo == null && other.codigoProgramaEducativo != null) || (this.codigoProgramaEducativo != null && !this.codigoProgramaEducativo.equals(other.codigoProgramaEducativo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "alucintech.entidad.Programaeducativo[ codigoProgramaEducativo=" + codigoProgramaEducativo + " ]";
    }
    
}
