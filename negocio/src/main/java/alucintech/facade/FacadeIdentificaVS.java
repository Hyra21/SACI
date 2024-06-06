/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.facade;

import alucintech.delegate.DelegateIdentificaVS;
import alucintech.entidad.Identificavs;

/**
 *
 * @author EduardoCardona <>
 */
public class FacadeIdentificaVS {
    
    private final DelegateIdentificaVS delegateIdentificaVS;

    public FacadeIdentificaVS() {
        this.delegateIdentificaVS = new DelegateIdentificaVS();
    }
    
}