
package alucintech.facade;

import alucintech.delegate.DelegateActividad;
import alucintech.entidad.Actividad;
import alucintech.entidad.Identificaadministrador;
import java.util.List;

/**
 *
 * @author EduardoCardona <>
 */
public class FacadeActividad {
    
    private final DelegateActividad delegateActividad;

    public FacadeActividad() {
        this.delegateActividad = new DelegateActividad();
    }
    
    /**
     * Método para registrar una actividad en la base de datos.
     *
     * @param actividad
     */
    public void registrarActividad(Actividad actividad) {
        delegateActividad.registrarActividad(actividad);
    }

    /**
     * Método que actualiza el registro de la actividad en la base de datos con
     * la actividad enviada como parámetro.
     *
     * @param actividad
     */
    public void modificarActividad(Actividad actividad) {
        delegateActividad.modificarActividad(actividad);
    }

    /**
     * Regresa una lista de los actividades registrados en la base de datos.
     *
     * @return
     */
    public List<Actividad> consultarActividades() {
        return delegateActividad.consultarActividades();
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
        
        return delegateActividad.validarActividad(actividad);
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
        return delegateActividad.listaActividadesAdmin(admin);
    }

    /**
     * Método que elimina la actividad enviada como parámetro de la base de
     * datos.- Se utiiza para eliminar actividades canceladas.
     *
     * @param actividad
     */
    public void eliminarActividad(Actividad actividad) {
        delegateActividad.eliminarActividad(actividad);
    }

    /**
     * Método que elimina la lista de actividades enviadas como parámetro de la
     * base de datos.- Se utiiza para eliminar actividades canceladas.
     *
     * @param actividades
     */
    public void eliminarListaActividades(List<Actividad> actividades) {
        delegateActividad.eliminarListaActividades(actividades);
    }
    
}