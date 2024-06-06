package alucintech.delegate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import alucintech.entidad.Actividad;
import alucintech.entidad.Identificaadministrador;
import alucintech.integracion.ServiceLocator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author 980014102 <>
 */
public class DelegateActividad {

    /**
     * Método para registrar una actividad en la base de datos.
     *
     * @param actividad
     */
    public void registrarActividad(Actividad actividad) {
        ServiceLocator.getInstanceActividadDAO().save(actividad);
    }

    /**
     * Método que actualiza el registro de la actividad en la base de datos con
     * la actividad enviada como parámetro.
     *
     * @param actividad
     */
    public void modificarActividad(Actividad actividad) {
        ServiceLocator.getInstanceActividadDAO().update(actividad);
    }

    /**
     * Regresa una lista de los actividades registrados en la base de datos.
     *
     * @return
     */
    public List<Actividad> consultarActividades() {
        return ServiceLocator.getInstanceActividadDAO().findAll();
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
        Calendar fechaInicioMin = Calendar.getInstance();
        Calendar fechaFinMax = Calendar.getInstance();
        List<Actividad> actividades = consultarActividades();
        int[] errores = new int[3];

        //Verificar Fecha de la actividad
        fechaInicioMin.set(actividad.getIdEvento().getFechaInicioEvento().getYear() + 1900, actividad.getIdEvento().getFechaInicioEvento().getMonth(), actividad.getIdEvento().getFechaInicioEvento().getDate());
        fechaFinMax.set(actividad.getIdEvento().getFechaFinEvento().getYear() + 1900, actividad.getIdEvento().getFechaFinEvento().getMonth(), actividad.getIdEvento().getFechaFinEvento().getDate());
        if (actividad.getFechaActividad().before(fechaInicioMin.getTime()) || actividad.getFechaActividad().after(fechaFinMax.getTime())) {
            errores[0] = 1;
        }

        //Verificar Nombre, Fecha, Horario, Modalidad, Dirección
        for (Actividad act : actividades) {
            if (act.getNombreActividad().equalsIgnoreCase(actividad.getNombreActividad())
                    && act.getDireccionActividad().equalsIgnoreCase(actividad.getDireccionActividad())
                    && act.getFechaActividad().toString().equalsIgnoreCase(actividad.getFechaActividad().toString())
                    && act.getModalidadActividad().equalsIgnoreCase(actividad.getModalidadActividad())
                    && act.getHorarioInicioActividad().toString().equalsIgnoreCase(actividad.getHorarioInicioActividad().toString())
                    && act.getHorarioFinActividad().toString().equalsIgnoreCase(actividad.getHorarioFinActividad().toString())) {
                errores[1] = 1;

            }

            //Verificar que el ponente de la actividad no se encuentre en otra al mismo tiempo.
            if (act.getFechaActividad().toString().equalsIgnoreCase(actividad.getFechaActividad().toString())
                    && act.getHorarioInicioActividad().toString().equalsIgnoreCase(actividad.getHorarioInicioActividad().toString())
                    && act.getHorarioFinActividad().toString().equalsIgnoreCase(actividad.getHorarioFinActividad().toString())
                    && act.getPonenteActividad().equalsIgnoreCase(actividad.getPonenteActividad())) {
                errores[2] = 1;
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            }            
        }

        //
        return errores;
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
        List<Actividad> listaActividades = ServiceLocator.getInstanceActividadDAO().findAll();
        List<Actividad> listaActividadesAdmin = new ArrayList();
        for (Actividad act : listaActividades) {
            if (Objects.equals(admin.getNumEmpleado(), act.getNumEmpleadoAdministradorActividad().getNumEmpleado())) {
                listaActividadesAdmin.add(act);
            }
        }
        return listaActividadesAdmin;
    }

    /**
     * Método que elimina la actividad enviada como parámetro de la base de
     * datos.- Se utiiza para eliminar actividades canceladas.
     *
     * @param actividad
     */
    public void eliminarActividad(Actividad actividad) {
        ServiceLocator.getInstanceActividadDAO().delete(actividad);
    }

    /**
     * Método que elimina la lista de actividades enviadas como parámetro de la
     * base de datos.- Se utiiza para eliminar actividades canceladas.
     *
     * @param actividades
     */
    public void eliminarListaActividades(List<Actividad> actividades) {
        for (Actividad act : actividades) {
            ServiceLocator.getInstanceActividadDAO().delete(act);
        }
    }

}
