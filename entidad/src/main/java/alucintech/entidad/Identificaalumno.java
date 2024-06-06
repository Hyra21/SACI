/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 980014102
 */
@Entity
@Table(name = "identificaalumno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Identificaalumno.findAll", query = "SELECT i FROM Identificaalumno i")
    , @NamedQuery(name = "Identificaalumno.findByMatricula", query = "SELECT i FROM Identificaalumno i WHERE i.matricula = :matricula")})
public class Identificaalumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "matricula")
    private Integer matricula;
    @JoinColumn(name = "correoAlumno", referencedColumnName = "correo")
    @ManyToOne(optional = false)
    private Usuarios correoAlumno;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "identificaalumno")
    private Alumno alumno;

    public Identificaalumno() {
    }

    public Identificaalumno(Integer matricula) {
        this.matricula = matricula;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Usuarios getCorreoAlumno() {
        return correoAlumno;
    }

    public void setCorreoAlumno(Usuarios correoAlumno) {
        this.correoAlumno = correoAlumno;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricula != null ? matricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Identificaalumno)) {
            return false;
        }
        Identificaalumno other = (Identificaalumno) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "alucintech.entidad.Identificaalumno[ matricula=" + matricula + " ]";
    }
    
}
