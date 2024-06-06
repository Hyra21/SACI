/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.helper;

import alucintech.entidad.Evento;
import alucintech.entidad.Facultad;
import alucintech.entidad.Identificaadministrador;
import alucintech.integracion.ServiceFacadeLocator;
import java.io.Serializable;
import java.util.List;

/**
 * Clase de apoyo para la clase ManejoEventoBeanUI que le permitirá utilizar los
 * métodos de las clases DAO.
 *
 * @author 980014102
 */
public class ManejoEventoHelper implements Serializable {

    /**
     * Método que registra el objeto evento en la base de datos
     *
     * @param evento
     */
    public void registroEvento(Evento evento) {
        ServiceFacadeLocator.getInstanceFacadeEvento().registrarEvento(evento);
    }

    /**
     * Método que actualiza el registro del evento en la base de datos con el
     * evento enviado como parámetro.
     *
     * @param evento
     */
    public void modificarEvento(Evento evento) {
        ServiceFacadeLocator.getInstanceFacadeEvento().modificarEvento(evento);
    }

    /**
     * Método que valida la información del ovjeto evento para que concuerde con
     * las reglas de negocio. Evita que se registre un evento con información
     * contradictoria o inválida
     *
     * @param evento
     * @return
     */
    public int[] validarEvento(Evento evento) {
        return ServiceFacadeLocator.getInstanceFacadeEvento().validarEvento(evento);
    }

    /**
     * Regresa una lista de los eventos registrados en la base de datos.
     *
     * @return
     */
    public List<Evento> consultarEventos() {
        return ServiceFacadeLocator.getInstanceFacadeEvento().consultarEventos();
    }

    /**
     * Método que regresa el objeto Identificaadministrador que concuerde con el
     * parametro "correo". Sirve para identificar al administrador de eventos al
     * que le pertenece el correo.
     *
     * @param correo
     * @return
     */
    public Identificaadministrador identificarAdmin(String correo) {
        return ServiceFacadeLocator.getInstanceFacadeIdentificaAdministrador().IdentificarAdmin(correo);
    }

    /**
     * Regresa una lista de los eventos registrados por el administrador de
     * eventos enviado como parámetro Se utiliza para que solo el administrador
     * de eventos que creo los eventos pueda modificarlos al mostrarle
     * únicamente los que ha creado.
     *
     * @param admin
     * @return
     */
    public List<Evento> listaEventoAdmin(Identificaadministrador admin) {
        return ServiceFacadeLocator.getInstanceFacadeEvento().listaEventoAdmin(admin);
    }

    /**
     * Regresa una lista de los eventos que se encuentran en la base de datos.
     * Este método se usa principalmente para consultas.
     *
     * @return
     */
    public List<Evento> listaEventos() {
        return ServiceFacadeLocator.getInstanceFacadeEvento().consultarEventos();
    }

    /**
     * Método que elimina el evento enviado como parámetro de la base de datos.-
     * Se utiiza para eliminar eventos cancelados.
     *
     * @param evento
     */
    public void eliminarEvento(Evento evento) {
        ServiceFacadeLocator.getInstanceFacadeEvento().eliminarEvento(evento);
    }

    /**
     * Método que elimina la lista de eventos enviados como parámetro de la base
     * de datos.- Se utiiza para eliminar eventos cancelados.
     *
     * @param eventos
     */
    public void eliminarListaEventos(List<Evento> eventos) {
        ServiceFacadeLocator.getInstanceFacadeEvento().eliminarListaEventos(eventos);
    }

    /**
     * Regresa una lista de las facultades registradas en la base de datos.
     *
     * @return
     */
    public List<Facultad> obtenerFacultades() {
        return ServiceFacadeLocator.getInstanceFacadeFacultad().obtenerFacultades();
    }
    
    public void actualizarFacultad(Facultad facultad){
        ServiceFacadeLocator.getInstanceFacadeFacultad().actualizarFacultad(facultad);
    }
    
    public void asignarFacultadesElegidas(Integer[] idFacultadesElegidas, Evento evento) {
        ServiceFacadeLocator.getInstanceFacadeFacultad().asignarFacultadesElegidas(idFacultadesElegidas, evento);
    }
}
