/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.helper;

import alucintech.entidad.Actividad;
import alucintech.entidad.Evento;
import alucintech.entidad.Identificaadministrador;
import alucintech.entidad.Programaeducativo;
import alucintech.entidad.Sello;
import alucintech.integracion.ServiceFacadeLocator;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 980014102
 */
public class ManejoActividadHelper implements Serializable {

    /**
     * Método para registrar una actividad en la base de datos.
     *
     * @param actividad
     */
    public void registrarActividad(Actividad actividad) {
        ServiceFacadeLocator.getInstanceFacadeActividad().registrarActividad(actividad);
    }

    /**
     * Método que actualiza el registro de la actividad en la base de datos con
     * la actividad enviada como parámetro.
     *
     * @param actividad
     */
    public void modificarActividad(Actividad actividad) {
        ServiceFacadeLocator.getInstanceFacadeActividad().modificarActividad(actividad);
    }

    /**
     * Regresa una lista de los actividades registrados en la base de datos.
     *
     * @return
     */
    public List<Actividad> consultarActividades() {
        return ServiceFacadeLocator.getInstanceFacadeActividad().consultarActividades();
    }

    /**
     * Método que valida la información del objeto actividad para que concuerde
     * con las reglas de negocio. Evita que se registre una actividad con
     * información contradictoria o inválida
     *
     * @param actividad
     * @return
     */
    public int[] validarActividad(Actividad actividad) {

        return ServiceFacadeLocator.getInstanceFacadeActividad().validarActividad(actividad);
    }

    /**
     * Regresa una lista de los eventos registrados por el administrador de
     * eventos enviado como parámetro Se utiliza para que solo el administrador
     * que creo las actividades pueda modificarlas al mostrarle únicamente las
     * que ha creado.
     *
     * @param admin
     * @return
     */
    public List<Actividad> listaActividadesAdmin(Identificaadministrador admin) {
        return ServiceFacadeLocator.getInstanceFacadeActividad().listaActividadesAdmin(admin);
    }

    /**
     * Método que elimina la actividad enviada como parámetro de la base de
     * datos.- Se utiiza para eliminar actividades canceladas.
     *
     * @param actividad
     */
    public void eliminarActividad(Actividad actividad) {
        ServiceFacadeLocator.getInstanceFacadeActividad().eliminarActividad(actividad);
    }

    /**
     * Método que elimina la lista de actividades enviadas como parámetro de la
     * base de datos.- Se utiiza para eliminar actividades canceladas.
     *
     * @param actividades
     */
    public void eliminarListaActividades(List<Actividad> actividades) {
        ServiceFacadeLocator.getInstanceFacadeActividad().eliminarListaActividades(actividades);
    }

    public Identificaadministrador identificarAdmin(String correoAdministrador) {
        return ServiceFacadeLocator.getInstanceFacadeIdentificaAdministrador().IdentificarAdmin(correoAdministrador);
    }

    public void registrarSello(Sello sello) {
        ServiceFacadeLocator.getInstanceFacadeSello().registrarSello(sello);
    }

    public List<Sello> consultarSellos() {
        return ServiceFacadeLocator.getInstanceFacadeSello().consultarSellos();
    }

    public void asignarProgramasElegidos(Integer[] codigoProgramasElegidos, Actividad actividad) {
        ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().asignarProgramasElegidos(codigoProgramasElegidos, actividad);
    }

    public boolean validarProgramasEducativos(Integer idEvento, Integer[] codigoProgramasElegidos) {
        return ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().validarProgramasEducativos(idEvento, codigoProgramasElegidos);
    }

    public List<Programaeducativo> consultarProgramas() {
        return ServiceFacadeLocator.getInstanceFacadeProgramaEducativo().consultarProgramas();
    }

    public void actualizarSello(Sello sello) {
        ServiceFacadeLocator.getInstanceFacadeSello().actualizarSello(sello);
    }
    
    public List<Evento> consultaEventos(){
        return ServiceFacadeLocator.getInstanceFacadeEvento().consultarEventos();
    }
    
     public Evento obtenerEvento(Integer idEvento){
         return ServiceFacadeLocator.getInstanceFacadeEvento().obtenerEvento(idEvento);
     }

}
