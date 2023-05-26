/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.ui;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import alucintech.entidad.Evento;
import alucintech.entidad.Facultad;
import alucintech.entidad.Identificaadministrador;
import alucintech.helper.ManejoEventoHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 980014102
 */
@ManagedBean(name = "ModificarEventoUI")
@SessionScoped
public class ManejoEventoBeanUI implements Serializable {

    private ManejoEventoHelper manejoEventoHelper;
    private Evento evento;
    private String correo;
    private Identificaadministrador idAdmin;
    private String nombreFacultad;
    private Facultad facultad;
    private List<Evento> listaEventos;
    private List<Facultad> facultadesEvento;

    ManejoEventoBeanUI() {
        manejoEventoHelper = new ManejoEventoHelper();
    }

    @PostConstruct
    public void init() {
        evento = new Evento();
        facultad = new Facultad();
        nombreFacultad = new String();
        idAdmin = manejoEventoHelper.identificar(correo);
        listaEventos = manejoEventoHelper.listaEventoAdmin(idAdmin);
    }

    public void modificarEvento() throws IOException {
        int[] errores = new int[3];
        boolean error = false;

        facultad = manejoEventoHelper.identificarFacultad(nombreFacultad);

        facultadesEvento = new ArrayList();
        facultadesEvento.add(facultad);

        errores = manejoEventoHelper.validarEvento(evento);

        evento.setFacultadList(facultadesEvento);

        if (errores[0] == 1 || errores[1] == 1) {
            error = true;

        }

        if (error == false) {
            manejoEventoHelper.modificarEvento(evento);
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/consulta.xhtml");

        } else {
            if (errores[0] == 1 && errores[1] == 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El evento ya existe y la fecha no está dentro del rango permitido:", "Intente de nuevo"));
            }
            if (errores[0] == 1 && errores[1] == 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La fecha no está dentro del rango permitido:", "Intente de nuevo"));
            }
            if (errores[0] == 0 && errores[1] == 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El evento ya existe", "Intente de nuevo"));
            }
        }

    }

    public void eliminarEvento() {
        manejoEventoHelper.eliminarEvento(manejoEventoHelper.eventoSeleccionado(evento.getIdEvento()));
    }

    public void actualizarObjetoEvento() {
        evento = manejoEventoHelper.eventoSeleccionado(evento.getIdEvento());
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getNombreFacultad() {
        return nombreFacultad;
    }

    public void setNombreFacultad(String nombreFacultad) {
        this.nombreFacultad = nombreFacultad;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }

    public List<Facultad> getFacultadesEvento() {
        return facultadesEvento;
    }

    public void setFacultadesEvento(List<Facultad> facultadesEvento) {
        this.facultadesEvento = facultadesEvento;
    }

}
