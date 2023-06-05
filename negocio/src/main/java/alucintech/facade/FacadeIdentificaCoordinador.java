/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.facade;

import alucintech.delegate.DelegateIdentificaCoordinador;
import alucintech.entidad.Identificacoordinador;

/**
 *
 * @author EduardoCardona <>
 */
public class FacadeIdentificaCoordinador {
    
    private final DelegateIdentificaCoordinador delegateIdentificaCoordinador;

    public FacadeIdentificaCoordinador() {
        this.delegateIdentificaCoordinador = new DelegateIdentificaCoordinador();
    }
    
}