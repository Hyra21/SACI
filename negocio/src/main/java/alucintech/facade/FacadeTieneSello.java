/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.facade;

import alucintech.delegate.DelegateTieneSello;
import alucintech.entidad.Tienesello;

/**
 *
 * @author EduardoCardona <>
 */
public class FacadeTieneSello {
    
    private final DelegateTieneSello delegateTieneSello;

    public FacadeTieneSello() {
        this.delegateTieneSello = new DelegateTieneSello();
    }
    
    public void guardarTieneSello(Tienesello tieneSello){
        delegateTieneSello.saveTieneSello(tieneSello);
    }
    
}