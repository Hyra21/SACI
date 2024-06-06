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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
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
@Table(name = "sello")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sello.findAll", query = "SELECT s FROM Sello s")
    , @NamedQuery(name = "Sello.findByIdSello", query = "SELECT s FROM Sello s WHERE s.idSello = :idSello")
    , @NamedQuery(name = "Sello.findByCantidad", query = "SELECT s FROM Sello s WHERE s.cantidad = :cantidad")})
public class Sello implements Serializable {

    @Basic(optional = false)
    @Lob
    @Column(name = "imagenSello")
    private byte[] imagenSello;
    @JoinTable(name = "tienesello", joinColumns = {
        @JoinColumn(name = "idSello", referencedColumnName = "idSello")}, inverseJoinColumns = {
        @JoinColumn(name = "numFolioCarnet", referencedColumnName = "numFolio")})
    @ManyToMany
    private List<Carnet> carnetList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSello")
    private Integer idSello;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sello")
    private List<Tienesello> tieneselloList;
    @JoinColumn(name = "idActividad", referencedColumnName = "idActividad")
    @ManyToOne(optional = false)
    private Actividad idActividad;

    public Sello() {
    }

    public Sello(Integer idSello) {
        this.idSello = idSello;
    }

    public Sello(Integer idSello, int cantidad, byte[] imagenSello) {
        this.idSello = idSello;
        this.cantidad = cantidad;
        this.imagenSello = imagenSello;
    }

    public Integer getIdSello() {
        return idSello;
    }

    public void setIdSello(Integer idSello) {
        this.idSello = idSello;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    @XmlTransient
    public List<Tienesello> getTieneselloList() {
        return tieneselloList;
    }

    public void setTieneselloList(List<Tienesello> tieneselloList) {
        this.tieneselloList = tieneselloList;
    }

    public Actividad getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Actividad idActividad) {
        this.idActividad = idActividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSello != null ? idSello.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sello)) {
            return false;
        }
        Sello other = (Sello) object;
        if ((this.idSello == null && other.idSello != null) || (this.idSello != null && !this.idSello.equals(other.idSello))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "alucintech.entidad.Sello[ idSello=" + idSello + " ]";
    }

    public byte[] getImagenSello() {
        return imagenSello;
    }

    public void setImagenSello(byte[] imagenSello) {
        this.imagenSello = imagenSello;
    }

    @XmlTransient
    public List<Carnet> getCarnetList() {
        return carnetList;
    }

    public void setCarnetList(List<Carnet> carnetList) {
        this.carnetList = carnetList;
    }
    
}
