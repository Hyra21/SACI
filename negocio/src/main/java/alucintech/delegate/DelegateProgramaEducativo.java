package alucintech.delegate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import alucintech.entidad.Actividad;
import alucintech.entidad.Evento;
import alucintech.entidad.Programaeducativo;
import alucintech.integracion.ServiceLocator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EduardoCardona <>
 */
public class DelegateProgramaEducativo {

    public List<Programaeducativo> consultarProgramas() {
        return ServiceLocator.getInstanceProgramaeducativoDAO().findAll();
    }

    public void asignarProgramasElegidos(Integer[] codigoProgramasElegidos, Actividad actividad) {
        List<Programaeducativo> programas = ServiceLocator.getInstanceProgramaeducativoDAO().findAll();
        List<Actividad> actividadList = new ArrayList();
        boolean programaFueraFacultad = false;
        //En caso de que sea una modificación primero revisa en que programas ya 
        //se encuentra la actividad y la desvincula del programa, para que al 
        //vincular la nueva lista de actividades no ocurran repeticiones.

        for (Programaeducativo p : programas) {
            actividadList = p.getActividadList();
            if (actividadList.contains(actividad)) {
                actividadList.remove(actividad);
            }
            p.setActividadList(actividadList);
            actualizarProgramaEducativo(p);
        }

        //Vincula la actividad a los programas educativos seleccionados.
        for (Integer codigoProgramaElegido : codigoProgramasElegidos) {
            for (Programaeducativo p : programas) {
                if (p.getCodigoProgramaEducativo() == codigoProgramaElegido) {

                    actividadList = p.getActividadList();

                    if (!actividadList.contains(actividad)) {
                        actividadList.add(actividad);
                    }
                    p.setActividadList(actividadList);
                    actualizarProgramaEducativo(p);
                }
            }
        }
    }

    public boolean validarProgramasEducativos(Integer idEventoActividad, Integer[] codigoProgramasElegidos) {
        boolean validado = false;

        for (Integer codigoProgramaElegido : codigoProgramasElegidos) {
            for (Programaeducativo p : consultarProgramas()) {
                if (p.getCodigoProgramaEducativo() == codigoProgramaElegido) {
                    for (Evento ev : p.getIdFacultadProgramaEducativo().getEventoList()) {
                        if (ev.getIdEvento() == idEventoActividad) {
                            System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
                            validado = true;
                        }
                    }

                }
            }
        }

        return validado;
    }

    public void actualizarProgramaEducativo(Programaeducativo programa) {
        ServiceLocator.getInstanceProgramaeducativoDAO().update(programa);
    }
}
