package alucintech.delegate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import alucintech.entidad.Evento;
import alucintech.entidad.Facultad;
import alucintech.integracion.ServiceLocator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EduardoCardona <>
 */
public class DelegateFacultad {

    /**
     * Método que regresa una lista de las facultades registradas en la base de
     * datos.
     *
     * @return
     */
    public List<Facultad> obtenerFacultades() {
        return ServiceLocator.getInstanceFacultadDAO().findAll();
    }

    public void actualizarFacultad(Facultad facultad) {
        ServiceLocator.getInstanceFacultadDAO().update(facultad);
    }

    public void asignarFacultadesElegidas(Integer[] idFacultadesElegidas, Evento evento) {
        List<Facultad> facultades = ServiceLocator.getInstanceFacultadDAO().findAll();
        List<Evento> eventoList = new ArrayList();

        for (Facultad f : facultades) {
            eventoList = f.getEventoList();
            if (eventoList.contains(evento)) {
                eventoList.remove(evento);
            }
            f.setEventoList(eventoList);
            actualizarFacultad(f);
        }

        for (Integer idFacultadElegida : idFacultadesElegidas) {
            for (Facultad f : facultades) {
                if (f.getIdFacultad() == idFacultadElegida) {

                    eventoList = f.getEventoList();
                    if (!eventoList.contains(evento)) {
                        eventoList.add(evento);
                    }
                    f.setEventoList(eventoList);
                    actualizarFacultad(f);
                }
            }
        }
    }

}
