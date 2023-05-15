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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividad.findAll", query = "SELECT a FROM Actividad a")
    , @NamedQuery(name = "Actividad.findByIdActividad", query = "SELECT a FROM Actividad a WHERE a.idActividad = :idActividad")
    , @NamedQuery(name = "Actividad.findByNombreActividad", query = "SELECT a FROM Actividad a WHERE a.nombreActividad = :nombreActividad")
    , @NamedQuery(name = "Actividad.findByDescripcionActividad", query = "SELECT a FROM Actividad a WHERE a.descripcionActividad = :descripcionActividad")
    , @NamedQuery(name = "Actividad.findByFechaActividad", query = "SELECT a FROM Actividad a WHERE a.fechaActividad = :fechaActividad")
    , @NamedQuery(name = "Actividad.findByHorarioInicioActividad", query = "SELECT a FROM Actividad a WHERE a.horarioInicioActividad = :horarioInicioActividad")
    , @NamedQuery(name = "Actividad.findByHorarioFinActividad", query = "SELECT a FROM Actividad a WHERE a.horarioFinActividad = :horarioFinActividad")
    , @NamedQuery(name = "Actividad.findByDireccionActividad", query = "SELECT a FROM Actividad a WHERE a.direccionActividad = :direccionActividad")
    , @NamedQuery(name = "Actividad.findByLugarActividad", query = "SELECT a FROM Actividad a WHERE a.lugarActividad = :lugarActividad")
    , @NamedQuery(name = "Actividad.findByEspaciosDisponiblesActividad", query = "SELECT a FROM Actividad a WHERE a.espaciosDisponiblesActividad = :espaciosDisponiblesActividad")
    , @NamedQuery(name = "Actividad.findByModalidadActividad", query = "SELECT a FROM Actividad a WHERE a.modalidadActividad = :modalidadActividad")
    , @NamedQuery(name = "Actividad.findByEnlaceVirtual", query = "SELECT a FROM Actividad a WHERE a.enlaceVirtual = :enlaceVirtual")
    , @NamedQuery(name = "Actividad.findByComentarioActividad", query = "SELECT a FROM Actividad a WHERE a.comentarioActividad = :comentarioActividad")})
public class Actividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idActividad")
    private Integer idActividad;
    @Basic(optional = false)
    @Column(name = "nombreActividad")
    private String nombreActividad;
    @Basic(optional = false)
    @Column(name = "descripcionActividad")
    private String descripcionActividad;
    @Basic(optional = false)
    @Column(name = "fechaActividad")
    @Temporal(TemporalType.DATE)
    private Date fechaActividad;
    @Basic(optional = false)
    @Column(name = "horarioInicioActividad")
    @Temporal(TemporalType.TIME)
    private Date horarioInicioActividad;
    @Basic(optional = false)
    @Column(name = "horarioFinActividad")
    @Temporal(TemporalType.TIME)
    private Date horarioFinActividad;
    @Basic(optional = false)
    @Column(name = "direccionActividad")
    private String direccionActividad;
    @Basic(optional = false)
    @Column(name = "lugarActividad")
    private String lugarActividad;
    @Basic(optional = false)
    @Column(name = "espaciosDisponiblesActividad")
    private int espaciosDisponiblesActividad;
    @Basic(optional = false)
    @Column(name = "modalidadActividad")
    private String modalidadActividad;
    @Column(name = "enlaceVirtual")
    private String enlaceVirtual;
    @Lob
    @Column(name = "imagenActividad")
    private byte[] imagenActividad;
    @Column(name = "comentarioActividad")
    private String comentarioActividad;
    @ManyToMany(mappedBy = "actividadList")
    private List<Programaeducativo> programaeducativoList;
    @JoinColumn(name = "idEvento", referencedColumnName = "idEvento")
    @ManyToOne(optional = false)
    private Evento idEvento;
    @JoinColumn(name = "numEmpleadoAdministradorActividad", referencedColumnName = "numEmpleado")
    @ManyToOne(optional = false)
    private Identificaadministrador numEmpleadoAdministradorActividad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idActividad")
    private List<Sello> selloList;

    public Actividad() {
    }

    public Actividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public Actividad(Integer idActividad, String nombreActividad, String descripcionActividad, Date fechaActividad, Date horarioInicioActividad, Date horarioFinActividad, String direccionActividad, String lugarActividad, int espaciosDisponiblesActividad, String modalidadActividad) {
        this.idActividad = idActividad;
        this.nombreActividad = nombreActividad;
        this.descripcionActividad = descripcionActividad;
        this.fechaActividad = fechaActividad;
        this.horarioInicioActividad = horarioInicioActividad;
        this.horarioFinActividad = horarioFinActividad;
        this.direccionActividad = direccionActividad;
        this.lugarActividad = lugarActividad;
        this.espaciosDisponiblesActividad = espaciosDisponiblesActividad;
        this.modalidadActividad = modalidadActividad;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }

    public Date getFechaActividad() {
        return fechaActividad;
    }

    public void setFechaActividad(Date fechaActividad) {
        this.fechaActividad = fechaActividad;
    }

    public Date getHorarioInicioActividad() {
        return horarioInicioActividad;
    }

    public void setHorarioInicioActividad(Date horarioInicioActividad) {
        this.horarioInicioActividad = horarioInicioActividad;
    }

    public Date getHorarioFinActividad() {
        return horarioFinActividad;
    }

    public void setHorarioFinActividad(Date horarioFinActividad) {
        this.horarioFinActividad = horarioFinActividad;
    }

    public String getDireccionActividad() {
        return direccionActividad;
    }

    public void setDireccionActividad(String direccionActividad) {
        this.direccionActividad = direccionActividad;
    }

    public String getLugarActividad() {
        return lugarActividad;
    }

    public void setLugarActividad(String lugarActividad) {
        this.lugarActividad = lugarActividad;
    }

    public int getEspaciosDisponiblesActividad() {
        return espaciosDisponiblesActividad;
    }

    public void setEspaciosDisponiblesActividad(int espaciosDisponiblesActividad) {
        this.espaciosDisponiblesActividad = espaciosDisponiblesActividad;
    }

    public String getModalidadActividad() {
        return modalidadActividad;
    }

    public void setModalidadActividad(String modalidadActividad) {
        this.modalidadActividad = modalidadActividad;
    }

    public String getEnlaceVirtual() {
        return enlaceVirtual;
    }

    public void setEnlaceVirtual(String enlaceVirtual) {
        this.enlaceVirtual = enlaceVirtual;
    }

    public byte[] getImagenActividad() {
        return imagenActividad;
    }

    public void setImagenActividad(byte[] imagenActividad) {
        this.imagenActividad = imagenActividad;
    }

    public String getComentarioActividad() {
        return comentarioActividad;
    }

    public void setComentarioActividad(String comentarioActividad) {
        this.comentarioActividad = comentarioActividad;
    }

    @XmlTransient
    public List<Programaeducativo> getProgramaeducativoList() {
        return programaeducativoList;
    }

    public void setProgramaeducativoList(List<Programaeducativo> programaeducativoList) {
        this.programaeducativoList = programaeducativoList;
    }

    public Evento getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Evento idEvento) {
        this.idEvento = idEvento;
    }

    public Identificaadministrador getNumEmpleadoAdministradorActividad() {
        return numEmpleadoAdministradorActividad;
    }

    public void setNumEmpleadoAdministradorActividad(Identificaadministrador numEmpleadoAdministradorActividad) {
        this.numEmpleadoAdministradorActividad = numEmpleadoAdministradorActividad;
    }

    @XmlTransient
    public List<Sello> getSelloList() {
        return selloList;
    }

    public void setSelloList(List<Sello> selloList) {
        this.selloList = selloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActividad != null ? idActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividad)) {
            return false;
        }
        Actividad other = (Actividad) object;
        if ((this.idActividad == null && other.idActividad != null) || (this.idActividad != null && !this.idActividad.equals(other.idActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "alucintech.entidad.Actividad[ idActividad=" + idActividad + " ]";
    }
    
}
