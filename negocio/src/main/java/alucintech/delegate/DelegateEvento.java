package alucintech.delegate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import alucintech.entidad.Evento;
import alucintech.integracion.ServiceLocator;
import alucintech.entidad.Identificaadministrador;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author EduardoCardona <>
 */
public class DelegateEvento {

    /**
     * Metodo de ejemplo para guardar Alumno
     *
     * @param evento de tipo usuario con id 0 para que se cree un id nuevo
     */
    public void saveEvento(Evento evento) {
        ServiceLocator.getInstanceEventoDAO().save(evento);
    }
    
    public void modificarEvento(Evento evento){
        ServiceLocator.getInstanceEventoDAO().update(evento);
    }

    public List<Evento> ConsultaEvento() {
        return ServiceLocator.getInstanceEventoDAO().findAll();
    }
    
    public Evento eventoSeleccionado(int id){
        List<Evento> listaEventos = ServiceLocator.getInstanceEventoDAO().findAll();
        Evento evento = new Evento();
        for(Evento ev:listaEventos){
            if(ev.getIdEvento() == id){
                evento = ev;
            }
        }
        return evento;
    }

    public int[] validarEvento(Evento evento) {
        Calendar fechaInicioMin = Calendar.getInstance();
        Calendar fechaFinMax = Calendar.getInstance();
        int[] errores = new int[3];
        List<Evento> eventos = ConsultaEvento();

        //Verificar Fecha de inicio y fecha de fin
        if (evento.getCicloEscolarEvento().charAt(evento.getCicloEscolarEvento().length() - 1) == '1') {
            fechaInicioMin.set(evento.getFechaInicioEvento().getYear() + 1900, 1, 30);
            fechaFinMax.set(evento.getFechaInicioEvento().getYear() + 1900, 6, 3);

            if (evento.getFechaInicioEvento().before(fechaInicioMin.getTime()) || evento.getFechaInicioEvento().after(fechaFinMax.getTime())) {
                errores[0] = 1;
            }
            if (evento.getFechaFinEvento().before(fechaInicioMin.getTime()) || evento.getFechaFinEvento().after(fechaFinMax.getTime())) {
                errores[0] = 1;
            }
            if (evento.getFechaInicioEvento().after(evento.getFechaFinEvento())) {
                errores[0] = 1;
            }
        }
        if (evento.getCicloEscolarEvento().charAt(evento.getCicloEscolarEvento().length() - 1) == '2') {
            fechaInicioMin.set(evento.getFechaInicioEvento().getYear() + 1900, 8, 7);
            fechaFinMax.set(evento.getFechaInicioEvento().getYear() + 1900, 12, 3);
            if (evento.getFechaFinEvento().before(fechaInicioMin.getTime()) || evento.getFechaFinEvento().after(fechaFinMax.getTime())) {
                errores[0] = 1;
            }
            if (evento.getFechaInicioEvento().before(fechaInicioMin.getTime()) || evento.getFechaInicioEvento().after(fechaFinMax.getTime())) {
                errores[0] = 1;
            }
            if (evento.getFechaFinEvento().before(evento.getFechaInicioEvento())) {
                errores[0] = 1;
            }
        }
        //Verificar nombre y ciclo escolar
        for (Evento ev : eventos) {
            if (ev.getNombreEvento().compareToIgnoreCase(evento.getNombreEvento()) == 0 && ev.getCicloEscolarEvento().compareToIgnoreCase(evento.getCicloEscolarEvento()) == 0) {
                errores[1] = 1;
            }
        }
        return errores;
    }
    
    public List<Evento> listaEventoAdmin(Identificaadministrador admin){
        List<Evento> listaEventos = ServiceLocator.getInstanceEventoDAO().findAll();
        List<Evento> listaEventosAdmin = new ArrayList();
        for(Evento ev:listaEventos){
            if(admin.getNumEmpleado() == ev.getNumEmpleadoAdministradorEvento().getNumEmpleado()){
                listaEventosAdmin.add(ev);
            }
        }
        return listaEventosAdmin;
    }
    
    public void eliminarEvento(Evento evento){
        ServiceLocator.getInstanceEventoDAO().delete(evento);
    }

}
