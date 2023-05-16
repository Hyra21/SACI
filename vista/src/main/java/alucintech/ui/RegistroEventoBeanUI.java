/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.ui;

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
import alucintech.helper.RegistroEventoHelper;
import java.util.Calendar;

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
    }

    public void Registro() throws IOException {

        Calendar fechaInicio = Calendar.getInstance();
        Calendar fechaFin = Calendar.getInstance();

        fechaInicio.set(2023, 05, 15);
        fechaFin.set(2023, 05, 22);

        idAdmin = registroEventoHelper.identificar("admin@uabc.edu.mx");

        String appURL = "/index.xhtml";

// los atributos del evento vienen del xhtml 
        Evento ev = new Evento();

        ev.setNombreEvento("Feria del libro");
        ev.setCicloEscolarEvento("2023-1");
        ev.setFechaInicioEvento(fechaInicio.getTime());
        ev.setFechaFinEvento(fechaFin.getTime());
        ev.setDescripcionEvento("Prueba de descripción de evento");
        ev.setEstadoEvento("Postulado");
        ev.setNumEmpleadoAdministradorEvento(idAdmin);
        ev.setComentarioEvento("");

        registroEventoHelper.RegistroEvento(ev);

    }
    /* getters y setters*/

}
