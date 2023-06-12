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
 * @author 980014102 <>
 */
public class FacadeEvento {
    
    private final DelegateEvento delegateEvento;

    public FacadeEvento() {
        this.delegateEvento = new DelegateEvento();
    }
    
    public List<Evento> consultarEventos(){
        return delegateEvento.consultarEventos();
    }
    
    public void registrarEvento(Evento evento){
        delegateEvento.registrarEvento(evento);
    }
    
    public void modificarEvento(Evento evento){
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
    
    /**
     * Método que elimina la lista de eventos enviados como parámetro de la base
     * de datos.- Se utiiza para eliminar eventos cancelados.
     * @param eventos 
     */
    public void eliminarListaEventos(List<Evento> eventos) {
        delegateEvento.eliminarListaEventos(eventos);
    }
}
