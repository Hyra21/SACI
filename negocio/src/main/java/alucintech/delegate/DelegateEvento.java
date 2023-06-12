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
 * @author 980014102 <>
 */
public class DelegateEvento {

    /**
     * Método para registrar un evento en la base de datos.
     *
     * @param evento
     */
    public void registrarEvento(Evento evento) {
        ServiceLocator.getInstanceEventoDAO().save(evento);
    }

    /**
     * Método que actualiza el registro del evento en la base de datos con el
     * evento enviado como parámetro.
     *
     * @param evento
     */
    public void modificarEvento(Evento evento) {
        ServiceLocator.getInstanceEventoDAO().update(evento);
    }

    /**
     * Regresa una lista de los eventos registrados en la base de datos.
     *
     * @return
     */
    public List<Evento> consultarEventos() {
        return ServiceLocator.getInstanceEventoDAO().findAll();
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
        Calendar fechaInicioMin = Calendar.getInstance();
        Calendar fechaFinMax = Calendar.getInstance();
        int[] errores = new int[3];
        int contador = 0;
        List<Evento> eventos = consultarEventos();

        //Verificar Fecha de inicio y fecha de fin
        if (evento.getCicloEscolarEvento().charAt(evento.getCicloEscolarEvento().length() - 1) == '1') {
            fechaInicioMin.set(evento.getFechaInicioEvento().getYear() + 1900, 0, 30);
            System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII" + fechaInicioMin.getTime().toString());
            fechaFinMax.set(evento.getFechaInicioEvento().getYear() + 1900, 5, 3);
            System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF" + fechaFinMax.getTime().toString());
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
            fechaInicioMin.set(evento.getFechaInicioEvento().getYear() + 1900, 7, 7);
            fechaFinMax.set(evento.getFechaInicioEvento().getYear() + 1900, 11, 3);
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
                contador++;
            }

        }
        if (contador == 2) {
            errores[1] = 1;
        }
        if (contador == 1 && evento.getIdEvento() == null) {
            errores[1] = 1;
        }
        return errores;
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
        List<Evento> listaEventos = ServiceLocator.getInstanceEventoDAO().findAll();
        List<Evento> listaEventosAdmin = new ArrayList();
        for (Evento ev : listaEventos) {
            if (admin.getNumEmpleado() == ev.getNumEmpleadoAdministradorEvento().getNumEmpleado()) {
                listaEventosAdmin.add(ev);
            }
        }
        return listaEventosAdmin;
    }

    /**
     * Método que elimina el evento enviado como parámetro de la base de datos.-
     * Se utiiza para eliminar eventos cancelados.
     *
     * @param evento
     */
    public void eliminarEvento(Evento evento) {
        ServiceLocator.getInstanceEventoDAO().delete(evento);
    }

    /**
     * Método que elimina la lista de eventos enviados como parámetro de la base
     * de datos.- Se utiiza para eliminar eventos cancelados.
     *
     * @param eventos
     */
    public void eliminarListaEventos(List<Evento> eventos) {
        for (Evento ev : eventos) {
            ServiceLocator.getInstanceEventoDAO().delete(ev);
        }
    }

    public Evento obtenerEvento(Integer idEvento) {
        Evento eventoEncontrado = new Evento();
        for (Evento ev : ServiceLocator.getInstanceEventoDAO().findAll()) {
            if (ev.getIdEvento() == idEvento) {
                eventoEncontrado = ev;
            }
        }
        return eventoEncontrado;
    }

}
