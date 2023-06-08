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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e")
    , @NamedQuery(name = "Evento.findByIdEvento", query = "SELECT e FROM Evento e WHERE e.idEvento = :idEvento")
    , @NamedQuery(name = "Evento.findByNombreEvento", query = "SELECT e FROM Evento e WHERE e.nombreEvento = :nombreEvento")
    , @NamedQuery(name = "Evento.findByCicloEscolarEvento", query = "SELECT e FROM Evento e WHERE e.cicloEscolarEvento = :cicloEscolarEvento")
    , @NamedQuery(name = "Evento.findByFechaInicioEvento", query = "SELECT e FROM Evento e WHERE e.fechaInicioEvento = :fechaInicioEvento")
    , @NamedQuery(name = "Evento.findByFechaFinEvento", query = "SELECT e FROM Evento e WHERE e.fechaFinEvento = :fechaFinEvento")
    , @NamedQuery(name = "Evento.findByDescripcionEvento", query = "SELECT e FROM Evento e WHERE e.descripcionEvento = :descripcionEvento")
    , @NamedQuery(name = "Evento.findByEstadoEvento", query = "SELECT e FROM Evento e WHERE e.estadoEvento = :estadoEvento")})
public class Evento implements Serializable {

    @Lob
    @Column(name = "imagenEvento")
    private byte[] imagenEvento;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idEvento")
    private Integer idEvento;
    @Basic(optional = false)
    @Column(name = "nombreEvento")
    private String nombreEvento;
    @Basic(optional = false)
    @Column(name = "cicloEscolarEvento")
    private String cicloEscolarEvento;
    @Basic(optional = false)
    @Column(name = "fechaInicioEvento")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioEvento;
    @Basic(optional = false)
    @Column(name = "fechaFinEvento")
    @Temporal(TemporalType.DATE)
    private Date fechaFinEvento;
    @Basic(optional = false)
    @Column(name = "descripcionEvento")
    private String descripcionEvento;
    @Basic(optional = false)
    @Column(name = "estadoEvento")
    private String estadoEvento;
    @ManyToMany(mappedBy = "eventoList")
    private List<Facultad> facultadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEvento")
    private List<Actividad> actividadList;
    @JoinColumn(name = "numEmpleadoAdministradorEvento", referencedColumnName = "numEmpleado")
    @ManyToOne(optional = false)
    private Identificaadministrador numEmpleadoAdministradorEvento;

    public Evento() {
    }

    public Evento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Evento(Integer idEvento, String nombreEvento, String cicloEscolarEvento, Date fechaInicioEvento, Date fechaFinEvento, String descripcionEvento, String estadoEvento, byte[] imagenEvento) {
        this.idEvento = idEvento;
        this.nombreEvento = nombreEvento;
        this.cicloEscolarEvento = cicloEscolarEvento;
        this.fechaInicioEvento = fechaInicioEvento;
        this.fechaFinEvento = fechaFinEvento;
        this.descripcionEvento = descripcionEvento;
        this.estadoEvento = estadoEvento;
        this.imagenEvento = imagenEvento;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getCicloEscolarEvento() {
        return cicloEscolarEvento;
    }

    public void setCicloEscolarEvento(String cicloEscolarEvento) {
        this.cicloEscolarEvento = cicloEscolarEvento;
    }

    public Date getFechaInicioEvento() {
        return fechaInicioEvento;
    }

    public void setFechaInicioEvento(Date fechaInicioEvento) {
        this.fechaInicioEvento = fechaInicioEvento;
    }

    public Date getFechaFinEvento() {
        return fechaFinEvento;
    }

    public void setFechaFinEvento(Date fechaFinEvento) {
        this.fechaFinEvento = fechaFinEvento;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public String getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(String estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

    @XmlTransient
    public List<Facultad> getFacultadList() {
        return facultadList;
    }

    public void setFacultadList(List<Facultad> facultadList) {
        this.facultadList = facultadList;
    }

    @XmlTransient
    public List<Actividad> getActividadList() {
        return actividadList;
    }

    public void setActividadList(List<Actividad> actividadList) {
        this.actividadList = actividadList;
    }

    public Identificaadministrador getNumEmpleadoAdministradorEvento() {
        return numEmpleadoAdministradorEvento;
    }

    public void setNumEmpleadoAdministradorEvento(Identificaadministrador numEmpleadoAdministradorEvento) {
        this.numEmpleadoAdministradorEvento = numEmpleadoAdministradorEvento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvento != null ? idEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.idEvento == null && other.idEvento != null) || (this.idEvento != null && !this.idEvento.equals(other.idEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "alucintech.entidad.Evento[ idEvento=" + idEvento + " ]";
    }

    public byte[] getImagenEvento() {
        return imagenEvento;
    }

    public void setImagenEvento(byte[] imagenEvento) {
        this.imagenEvento = imagenEvento;
    }
}
