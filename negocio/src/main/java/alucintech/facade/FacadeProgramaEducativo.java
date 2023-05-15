/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.facade;

import alucintech.delegate.DelegateProgramaEducativo;
import alucintech.entidad.Programaeducativo;

/**
 *
 * @author EduardoCardona <>
 */
public class FacadeProgramaEducativo {
    
    private final DelegateProgramaEducativo delegateProgramaEducativo;

    public FacadeProgramaEducativo() {
        this.delegateProgramaEducativo = new DelegateProgramaEducativo();
    }
    
    public void guardarProgramaEducativo(Programaeducativo programaEducativo){
        delegateProgramaEducativo.saveProgramaEducativo(programaEducativo);
    }
    
}