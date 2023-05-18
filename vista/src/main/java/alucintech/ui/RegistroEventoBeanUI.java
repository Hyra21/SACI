/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.ui;

import alucintech.ui.LoginBeanUI;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import alucintech.entidad.Usuarios;
import alucintech.entidad.Identificaadministrador;
import alucintech.entidad.Evento;
import alucintech.entidad.Facultad;
import alucintech.helper.RegistroEventoHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author 980014102
 */
@ManagedBean(name = "RegistroEventoUI")
@SessionScoped
public class RegistroEventoBeanUI implements Serializable {

    private RegistroEventoHelper registroEventoHelper;
    private Evento evento;
    private Identificaadministrador idAdmin;
    private String nombreFacultad;
    private Facultad facultad;
    private String correo;
    private List<Facultad> facultadesEvento;

    public RegistroEventoBeanUI() {
        registroEventoHelper = new RegistroEventoHelper();
    }

    /**
     * Metodo postconstructor todo lo que este dentro de este metodo sera la
     * primero que haga cuando cargue la pagina
     */
    @PostConstruct
    public void init() {
        evento = new Evento();
        idAdmin = new Identificaadministrador();
        correo = new String();
        facultad = new Facultad();
        nombreFacultad = new String();
    }

    public void registro() throws IOException {

        int[] errores = new int[3];
        boolean error = false;

        facultad = registroEventoHelper.identificarFacultad(nombreFacultad);
        idAdmin = registroEventoHelper.identificar(correo);

        facultadesEvento = new ArrayList();
        facultadesEvento.add(facultad);

        evento.setIdEvento(1);
        evento.setNumEmpleadoAdministradorEvento(idAdmin);
        evento.setEstadoEvento("Postulado");
        evento.setFacultadList(facultadesEvento);
        
        
        errores = registroEventoHelper.validarEvento(evento);

        if (errores[0] == 1) {
            System.out.println("La fecha no está denttro del rango permitido");
        }
        if (errores[1] == 1) {
            System.out.println("Ya existe este evento");
        }

        if (error == false) {
            registroEventoHelper.RegistroEvento(evento);
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/menu.xhtml");
    }

    /* getters y setters*/
    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public List<Facultad> getFacultadesEvento() {
        return facultadesEvento;
    }

    public void setFacultadesEvento(List<Facultad> facultadesEvento) {
        this.facultadesEvento = facultadesEvento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreFacultad() {
        return nombreFacultad;
    }

    public void setNombreFacultad(String nombreFacultad) {
        this.nombreFacultad = nombreFacultad;
    }

}
