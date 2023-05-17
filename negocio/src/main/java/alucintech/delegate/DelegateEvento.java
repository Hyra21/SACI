package alucintech.delegate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import alucintech.entidad.Evento;
import alucintech.integracion.ServiceLocator;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author EduardoCardona <>
 */
public class DelegateEvento {
    
    /**
     * Metodo de ejemplo para guardar Alumno
     * @param evento de tipo usuario con id 0 para que se cree un id nuevo
     */
    public void saveEvento(Evento evento){
        ServiceLocator.getInstanceEventoDAO().save(evento);
    }
    
    public List<Evento> ConsultaEvento(){
        return ServiceLocator.getInstanceEventoDAO().findAll();
    }
    
    public int[] validarEvento(Evento evento){
        Calendar fechaInicioMin = Calendar.getInstance();
        Calendar fechaFinMax = Calendar.getInstance();
        int[] errores = new int[3];
        List<Evento> eventos = ConsultaEvento();
        
        //Verificar Fecha de inicio y fecha de fin
        if(evento.getCicloEscolarEvento().charAt(evento.getCicloEscolarEvento().length() - 1) == '1'){
            
            fechaInicioMin.set(evento.getFechaInicioEvento().getYear(), 1, 30);
            fechaFinMax.set(evento.getFechaInicioEvento().getYear(), 6, 3);
            
            if( evento.getFechaInicioEvento().before(fechaInicioMin.getTime()) && evento.getFechaFinEvento().after(fechaFinMax.getTime())){
                errores[0] = 1;
            }
        }
        if(evento.getCicloEscolarEvento().charAt(evento.getCicloEscolarEvento().length() - 1) == '2'){
            
            fechaInicioMin.set(evento.getFechaInicioEvento().getYear(), 8, 7);
            fechaFinMax.set(evento.getFechaInicioEvento().getYear(), 12, 3);
            
            if( evento.getFechaInicioEvento().before(fechaInicioMin.getTime()) && evento.getFechaFinEvento().after(fechaFinMax.getTime())){
                errores[0] = 1;
            }
        }
        //Verificar nombre y ciclo escolar
        for(Evento ev:eventos){
            if(ev.getNombreEvento().compareToIgnoreCase(evento.getNombreEvento())==0 || ev.getCicloEscolarEvento().compareToIgnoreCase(evento.getCicloEscolarEvento())==0){
                errores[1] = 1;
            }
        }
        return errores;
    }
    
}
