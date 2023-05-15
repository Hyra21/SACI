/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.facade;

import alucintech.delegate.DelegateSello;
import alucintech.entidad.Sello;

/**
 *
 * @author EduardoCardona <>
 */
public class FacadeSello {
    
    private final DelegateSello delegateSello;

    public FacadeSello() {
        this.delegateSello = new DelegateSello();
    }
    
    public void guardarSello(Sello sello){
        delegateSello.saveSello(sello);
    }
    
}