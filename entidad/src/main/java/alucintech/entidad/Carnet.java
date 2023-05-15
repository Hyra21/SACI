/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.entidad;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 980014102
 */
@Entity
@Table(name = "carnet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carnet.findAll", query = "SELECT c FROM Carnet c")
    , @NamedQuery(name = "Carnet.findByNumFolio", query = "SELECT c FROM Carnet c WHERE c.numFolio = :numFolio")
    , @NamedQuery(name = "Carnet.findByNumeroSellosCarnet", query = "SELECT c FROM Carnet c WHERE c.numeroSellosCarnet = :numeroSellosCarnet")
    , @NamedQuery(name = "Carnet.findByCicloEscolarCarnet", query = "SELECT c FROM Carnet c WHERE c.cicloEscolarCarnet = :cicloEscolarCarnet")
    , @NamedQuery(name = "Carnet.findByFechaCreacionCarnet", query = "SELECT c FROM Carnet c WHERE c.fechaCreacionCarnet = :fechaCreacionCarnet")
    , @NamedQuery(name = "Carnet.findByEstadoCarnet", query = "SELECT c FROM Carnet c WHERE c.estadoCarnet = :estadoCarnet")
    , @NamedQuery(name = "Carnet.findByCodigoCarnet", query = "SELECT c FROM Carnet c WHERE c.codigoCarnet = :codigoCarnet")})
public class Carnet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numFolio")
    private Integer numFolio;
    @Basic(optional = false)
    @Column(name = "numeroSellosCarnet")
    private int numeroSellosCarnet;
    @Basic(optional = false)
    @Column(name = "cicloEscolarCarnet")
    private int cicloEscolarCarnet;
    @Basic(optional = false)
    @Column(name = "fechaCreacionCarnet")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacionCarnet;
    @Basic(optional = false)
    @Column(name = "estadoCarnet")
    private String estadoCarnet;
    @Basic(optional = false)
    @Column(name = "codigoCarnet")
    private int codigoCarnet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carnet")
    private List<Tienesello> tieneselloList;
    @JoinColumn(name = "matriculaAlumno", referencedColumnName = "matriculaAlumno")
    @ManyToOne(optional = false)
    private Alumno matriculaAlumno;

    public Carnet() {
    }

    public Carnet(Integer numFolio) {
        this.numFolio = numFolio;
    }

    public Carnet(Integer numFolio, int numeroSellosCarnet, int cicloEscolarCarnet, Date fechaCreacionCarnet, String estadoCarnet, int codigoCarnet) {
        this.numFolio = numFolio;
        this.numeroSellosCarnet = numeroSellosCarnet;
        this.cicloEscolarCarnet = cicloEscolarCarnet;
        this.fechaCreacionCarnet = fechaCreacionCarnet;
        this.estadoCarnet = estadoCarnet;
        this.codigoCarnet = codigoCarnet;
    }

    public Integer getNumFolio() {
        return numFolio;
    }

    public void setNumFolio(Integer numFolio) {
        this.numFolio = numFolio;
    }

    public int getNumeroSellosCarnet() {
        return numeroSellosCarnet;
    }

    public void setNumeroSellosCarnet(int numeroSellosCarnet) {
        this.numeroSellosCarnet = numeroSellosCarnet;
    }

    public int getCicloEscolarCarnet() {
        return cicloEscolarCarnet;
    }

    public void setCicloEscolarCarnet(int cicloEscolarCarnet) {
        this.cicloEscolarCarnet = cicloEscolarCarnet;
    }

    public Date getFechaCreacionCarnet() {
        return fechaCreacionCarnet;
    }

    public void setFechaCreacionCarnet(Date fechaCreacionCarnet) {
        this.fechaCreacionCarnet = fechaCreacionCarnet;
    }

    public String getEstadoCarnet() {
        return estadoCarnet;
    }

    public void setEstadoCarnet(String estadoCarnet) {
        this.estadoCarnet = estadoCarnet;
    }

    public int getCodigoCarnet() {
        return codigoCarnet;
    }

    public void setCodigoCarnet(int codigoCarnet) {
        this.codigoCarnet = codigoCarnet;
    }

    @XmlTransient
    public List<Tienesello> getTieneselloList() {
        return tieneselloList;
    }

    public void setTieneselloList(List<Tienesello> tieneselloList) {
        this.tieneselloList = tieneselloList;
    }

    public Alumno getMatriculaAlumno() {
        return matriculaAlumno;
    }

    public void setMatriculaAlumno(Alumno matriculaAlumno) {
        this.matriculaAlumno = matriculaAlumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numFolio != null ? numFolio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carnet)) {
            return false;
        }
        Carnet other = (Carnet) object;
        if ((this.numFolio == null && other.numFolio != null) || (this.numFolio != null && !this.numFolio.equals(other.numFolio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "alucintech.entidad.Carnet[ numFolio=" + numFolio + " ]";
    }
    
}
