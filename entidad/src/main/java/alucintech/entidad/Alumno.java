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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 980014102
 */
@Entity
@Table(name = "alumno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a")
    , @NamedQuery(name = "Alumno.findByMatriculaAlumno", query = "SELECT a FROM Alumno a WHERE a.matriculaAlumno = :matriculaAlumno")
    , @NamedQuery(name = "Alumno.findByNumSemestreAlumno", query = "SELECT a FROM Alumno a WHERE a.numSemestreAlumno = :numSemestreAlumno")
    , @NamedQuery(name = "Alumno.findByNumcarnetSemestre", query = "SELECT a FROM Alumno a WHERE a.numcarnetSemestre = :numcarnetSemestre")
    , @NamedQuery(name = "Alumno.findByNumCarnetsCarrera", query = "SELECT a FROM Alumno a WHERE a.numCarnetsCarrera = :numCarnetsCarrera")})
public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matriculaAlumno")
    private Integer matriculaAlumno;
    @Basic(optional = false)
    @Column(name = "numSemestreAlumno")
    private int numSemestreAlumno;
    @Column(name = "numcarnetSemestre")
    private Integer numcarnetSemestre;
    @Column(name = "numCarnetsCarrera")
    private Integer numCarnetsCarrera;
    @JoinColumn(name = "matriculaAlumno", referencedColumnName = "matricula", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Identificaalumno identificaalumno;
    @JoinColumn(name = "codigoProgramaEducativoAlumno", referencedColumnName = "codigoProgramaEducativo")
    @ManyToOne(optional = false)
    private Programaeducativo codigoProgramaEducativoAlumno;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matriculaAlumno")
    private List<Carnet> carnetList;

    public Alumno() {
    }

    public Alumno(Integer matriculaAlumno) {
        this.matriculaAlumno = matriculaAlumno;
    }

    public Alumno(Integer matriculaAlumno, int numSemestreAlumno) {
        this.matriculaAlumno = matriculaAlumno;
        this.numSemestreAlumno = numSemestreAlumno;
    }

    public Integer getMatriculaAlumno() {
        return matriculaAlumno;
    }

    public void setMatriculaAlumno(Integer matriculaAlumno) {
        this.matriculaAlumno = matriculaAlumno;
    }

    public int getNumSemestreAlumno() {
        return numSemestreAlumno;
    }

    public void setNumSemestreAlumno(int numSemestreAlumno) {
        this.numSemestreAlumno = numSemestreAlumno;
    }

    public Integer getNumcarnetSemestre() {
        return numcarnetSemestre;
    }

    public void setNumcarnetSemestre(Integer numcarnetSemestre) {
        this.numcarnetSemestre = numcarnetSemestre;
    }

    public Integer getNumCarnetsCarrera() {
        return numCarnetsCarrera;
    }

    public void setNumCarnetsCarrera(Integer numCarnetsCarrera) {
        this.numCarnetsCarrera = numCarnetsCarrera;
    }

    public Identificaalumno getIdentificaalumno() {
        return identificaalumno;
    }

    public void setIdentificaalumno(Identificaalumno identificaalumno) {
        this.identificaalumno = identificaalumno;
    }

    public Programaeducativo getCodigoProgramaEducativoAlumno() {
        return codigoProgramaEducativoAlumno;
    }

    public void setCodigoProgramaEducativoAlumno(Programaeducativo codigoProgramaEducativoAlumno) {
        this.codigoProgramaEducativoAlumno = codigoProgramaEducativoAlumno;
    }

    @XmlTransient
    public List<Carnet> getCarnetList() {
        return carnetList;
    }

    public void setCarnetList(List<Carnet> carnetList) {
        this.carnetList = carnetList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matriculaAlumno != null ? matriculaAlumno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.matriculaAlumno == null && other.matriculaAlumno != null) || (this.matriculaAlumno != null && !this.matriculaAlumno.equals(other.matriculaAlumno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "alucintech.entidad.Alumno[ matriculaAlumno=" + matriculaAlumno + " ]";
    }
    
}
