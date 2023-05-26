/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.facade;

import alucintech.delegate.DelegateEvento;
import alucintech.entidad.Evento;
import alucintech.entidad.Identificaadministrador;
import java.util.List;

/**
 *
 * @author EduardoCardona <>
 */
public class FacadeEvento {
    
    private final DelegateEvento delegateEvento;

    public FacadeEvento() {
        this.delegateEvento = new DelegateEvento();
    }
    
    public List<Evento> ConsultaEvento(){
        return delegateEvento.ConsultaEvento();
    }
    
    public Evento eventoSeleccionado(int id){
        return delegateEvento.eventoSeleccionado(id);
    }
    
    public void RegistrarEvento(Evento evento){
        delegateEvento.saveEvento(evento);
    }
    
    public void ModificarEvento(Evento evento){
        delegateEvento.modificarEvento(evento);
    }
    
    public List<Evento> listaEventoAdmin(Identificaadministrador admin){
        return delegateEvento.listaEventoAdmin(admin);
    }
    
    public int[] validarEvento(Evento evento){
        return delegateEvento.validarEvento(evento);
    }
    
    public void eliminarEvento(Evento evento){
        delegateEvento.eliminarEvento(evento);
    }
}
