/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.facade;

import alucintech.delegate.DelegateEvento;
import alucintech.entidad.Evento;

/**
 *
 * @author EduardoCardona <>
 */
public class FacadeEvento {
    
    private final DelegateEvento delegateEvento;

    public FacadeEvento() {
        this.delegateEvento = new DelegateEvento();
    }
    
    public void guardarEvento(Evento evento){
        delegateEvento.saveEvento(evento);
    }
    
}
