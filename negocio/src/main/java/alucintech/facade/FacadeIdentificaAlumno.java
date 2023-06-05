/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.facade;

import alucintech.delegate.DelegateIdentificaAlumno;
import alucintech.entidad.Identificaalumno;

/**
 *
 * @author EduardoCardona <>
 */
public class FacadeIdentificaAlumno {
    
    private final DelegateIdentificaAlumno delegateIdentificaAlumno;

    public FacadeIdentificaAlumno() {
        this.delegateIdentificaAlumno = new DelegateIdentificaAlumno();
    }
    
}