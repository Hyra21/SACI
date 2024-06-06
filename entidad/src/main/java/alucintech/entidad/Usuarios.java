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
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")
    , @NamedQuery(name = "Usuarios.findByCorreo", query = "SELECT u FROM Usuarios u WHERE u.correo = :correo")
    , @NamedQuery(name = "Usuarios.findByNombreUsuario", query = "SELECT u FROM Usuarios u WHERE u.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "Usuarios.findByApellidoMaternoUsuario", query = "SELECT u FROM Usuarios u WHERE u.apellidoMaternoUsuario = :apellidoMaternoUsuario")
    , @NamedQuery(name = "Usuarios.findByApellidoPaternoUsuario", query = "SELECT u FROM Usuarios u WHERE u.apellidoPaternoUsuario = :apellidoPaternoUsuario")
    , @NamedQuery(name = "Usuarios.findByContrasenaUsuario", query = "SELECT u FROM Usuarios u WHERE u.contrasenaUsuario = :contrasenaUsuario")
    , @NamedQuery(name = "Usuarios.findByTipoUsuario", query = "SELECT u FROM Usuarios u WHERE u.tipoUsuario = :tipoUsuario")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "nombreUsuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "apellidoMaternoUsuario")
    private String apellidoMaternoUsuario;
    @Basic(optional = false)
    @Column(name = "apellidoPaternoUsuario")
    private String apellidoPaternoUsuario;
    @Basic(optional = false)
    @Column(name = "contrasenaUsuario")
    private String contrasenaUsuario;
    @Basic(optional = false)
    @Column(name = "tipoUsuario")
    private String tipoUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "correoAdministrador")
    private List<Identificaadministrador> identificaadministradorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "correoAlumno")
    private List<Identificaalumno> identificaalumnoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "correoVS")
    private List<Identificavs> identificavsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "correoCoordinador")
    private List<Identificacoordinador> identificacoordinadorList;

    public Usuarios() {
    }

    public Usuarios(String correo) {
        this.correo = correo;
    }

    public Usuarios(String correo, String nombreUsuario, String apellidoMaternoUsuario, String apellidoPaternoUsuario, String contrasenaUsuario, String tipoUsuario) {
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.apellidoMaternoUsuario = apellidoMaternoUsuario;
        this.apellidoPaternoUsuario = apellidoPaternoUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoMaternoUsuario() {
        return apellidoMaternoUsuario;
    }

    public void setApellidoMaternoUsuario(String apellidoMaternoUsuario) {
        this.apellidoMaternoUsuario = apellidoMaternoUsuario;
    }

    public String getApellidoPaternoUsuario() {
        return apellidoPaternoUsuario;
    }

    public void setApellidoPaternoUsuario(String apellidoPaternoUsuario) {
        this.apellidoPaternoUsuario = apellidoPaternoUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @XmlTransient
    public List<Identificaadministrador> getIdentificaadministradorList() {
        return identificaadministradorList;
    }

    public void setIdentificaadministradorList(List<Identificaadministrador> identificaadministradorList) {
        this.identificaadministradorList = identificaadministradorList;
    }

    @XmlTransient
    public List<Identificaalumno> getIdentificaalumnoList() {
        return identificaalumnoList;
    }

    public void setIdentificaalumnoList(List<Identificaalumno> identificaalumnoList) {
        this.identificaalumnoList = identificaalumnoList;
    }

    @XmlTransient
    public List<Identificavs> getIdentificavsList() {
        return identificavsList;
    }

    public void setIdentificavsList(List<Identificavs> identificavsList) {
        this.identificavsList = identificavsList;
    }

    @XmlTransient
    public List<Identificacoordinador> getIdentificacoordinadorList() {
        return identificacoordinadorList;
    }

    public void setIdentificacoordinadorList(List<Identificacoordinador> identificacoordinadorList) {
        this.identificacoordinadorList = identificacoordinadorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (correo != null ? correo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.correo == null && other.correo != null) || (this.correo != null && !this.correo.equals(other.correo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "alucintech.entidad.Usuarios[ correo=" + correo + " ]";
    }
    
}
