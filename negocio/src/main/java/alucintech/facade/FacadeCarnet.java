/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.facade;

import alucintech.delegate.DelegateCarnet;
import alucintech.entidad.Carnet;

/**
 *
 * @author EduardoCardona <>
 */
public class FacadeCarnet {
    
    private final DelegateCarnet delegateCarnet;

    public FacadeCarnet() {
        this.delegateCarnet = new DelegateCarnet();
    }
    
    public void guardarCarnet(Carnet carnet){
        delegateCarnet.saveCarnet(carnet);
    }
    
}
