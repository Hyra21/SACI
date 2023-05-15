/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.integracion;

import alucintech.facade.FacadeActividad;
import alucintech.facade.FacadeAlumno;
import alucintech.facade.FacadeCarnet;
import alucintech.facade.FacadeEvento;
import alucintech.facade.FacadeFacultad;
import alucintech.facade.FacadeIdentificaAdministrador;
import alucintech.facade.FacadeIdentificaAlumno;
import alucintech.facade.FacadeIdentificaCoordinador;
import alucintech.facade.FacadeIdentificaVS;
import alucintech.facade.FacadeProgramaEducativo;
import alucintech.facade.FacadeSello;
import alucintech.facade.FacadeTieneSello;
import alucintech.facade.FacadeTieneSelloPK;
import alucintech.facade.FacadeUsuarios;

/**
 *
 * @author EduardoCardona <>
 */
public class ServiceFacadeLocator {
    
    private static FacadeActividad facadeActividad;
    private static FacadeAlumno facadeAlumno;
    private static FacadeCarnet facadeCarnet;
    private static FacadeEvento facadeEvento;
    private static FacadeFacultad facadeFacultad;
    private static FacadeIdentificaAdministrador facadeIdentificaAdministrador;
    private static FacadeIdentificaAlumno facadeIdentificaAlumno;
    private static FacadeIdentificaCoordinador facadeIdentificaCoordinador;
    private static FacadeIdentificaVS facadeIdentificaVS;
    private static FacadeProgramaEducativo facadeProgramaEducativo;
    private static FacadeSello facadeSello;
    private static FacadeTieneSello facadeTieneSello;
    private static FacadeTieneSelloPK facadeTieneSelloPK;
    private static FacadeUsuarios facadeUsuarios;
    
    public static FacadeActividad getInstanceFacadeActividad() {
        if (facadeActividad == null) {
            facadeActividad = new FacadeActividad();
            return facadeActividad;
        } else {
            return facadeActividad;
        }
    }
    
    public static FacadeAlumno getInstanceFacadeAlumno() {
        if (facadeAlumno == null) {
            facadeAlumno = new FacadeAlumno();
            return facadeAlumno;
        } else {
            return facadeAlumno;
        }
    }

    public static FacadeCarnet getInstanceFacadeCarnet() {
        if (facadeCarnet == null) {
            facadeCarnet = new FacadeCarnet();
            return facadeCarnet;
        } else {
            return facadeCarnet;
        }
    }
    
    public static FacadeEvento getInstanceFacadeEvento() {
        if (facadeEvento == null) {
            facadeEvento = new FacadeEvento();
            return facadeEvento;
        } else {
            return facadeEvento;
        }
    }    
        
    public static FacadeFacultad getInstanceFacadeFacultad() {
        if (facadeFacultad == null) {
            facadeFacultad = new FacadeFacultad();
            return facadeFacultad;
        } else {
            return facadeFacultad;
        }
    }
    
    public static FacadeIdentificaAdministrador getInstanceFacadeIdentificaAdministrador() {
        if (facadeIdentificaAdministrador == null) {
            facadeIdentificaAdministrador = new FacadeIdentificaAdministrador();
            return facadeIdentificaAdministrador;
        } else {
            return facadeIdentificaAdministrador;
        }
    }  
    
    public static FacadeIdentificaAlumno getInstanceFacadeIdentificaAlumno() {
        if (facadeIdentificaAlumno == null) {
            facadeIdentificaAlumno = new FacadeIdentificaAlumno();
            return facadeIdentificaAlumno;
        } else {
            return facadeIdentificaAlumno;
        }
    }
    
    public static FacadeIdentificaCoordinador getInstanceFacadeIdentificaCoordinador() {
        if (facadeIdentificaCoordinador == null) {
            facadeIdentificaCoordinador = new FacadeIdentificaCoordinador();
            return facadeIdentificaCoordinador;
        } else {
            return facadeIdentificaCoordinador;
        }
    }
    
    public static FacadeIdentificaVS getInstanceFacadeIdentificaVS() {
        if (facadeIdentificaVS == null) {
            facadeIdentificaVS = new FacadeIdentificaVS();
            return facadeIdentificaVS;
        } else {
            return facadeIdentificaVS;
        }
    }
    
    public static FacadeProgramaEducativo getInstanceFacadeProgramaEducativo() {
        if (facadeProgramaEducativo== null) {
            facadeProgramaEducativo = new FacadeProgramaEducativo();
            return facadeProgramaEducativo;
        } else {
            return facadeProgramaEducativo;
        }
    }
    
    public static FacadeSello getInstanceFacadeSello() {
        if (facadeSello == null) {
            facadeSello = new FacadeSello();
            return facadeSello;
        } else {
            return facadeSello;
        }
    }
    
    public static FacadeTieneSello getInstanceFacadeTieneSello() {
        if (facadeTieneSello == null) {
            facadeTieneSello = new FacadeTieneSello();
            return facadeTieneSello;
        } else {
            return facadeTieneSello;
        }
    }
    
    public static FacadeTieneSelloPK getInstanceFacadeTieneSelloPK() {
        if (facadeTieneSelloPK == null) {
            facadeTieneSelloPK = new FacadeTieneSelloPK();
            return facadeTieneSelloPK;
        } else {
            return facadeTieneSelloPK;
        }
    }
    
    public static FacadeUsuarios getInstanceFacadeUsuarios() {
        if (facadeUsuarios == null) {
            facadeUsuarios = new FacadeUsuarios();
            return facadeUsuarios;
        } else {
            return facadeUsuarios;
        }
    }
    
        
}
