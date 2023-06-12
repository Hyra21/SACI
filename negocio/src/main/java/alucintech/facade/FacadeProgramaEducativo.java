/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.facade;

import alucintech.delegate.DelegateProgramaEducativo;
import alucintech.entidad.Actividad;
import alucintech.entidad.Programaeducativo;
import java.util.List;

/**
 *
 * @author EduardoCardona <>
 */
public class FacadeProgramaEducativo {
    
    private final DelegateProgramaEducativo delegateProgramaEducativo;

    public FacadeProgramaEducativo() {
        this.delegateProgramaEducativo = new DelegateProgramaEducativo();
    }
    
    public List<Programaeducativo> consultarProgramas(){
        return delegateProgramaEducativo.consultarProgramas();
    }
    
     public void asignarProgramasElegidos(Integer[] codigoProgramasElegidos, Actividad actividad) {
        delegateProgramaEducativo.asignarProgramasElegidos(codigoProgramasElegidos, actividad);
    }

    public boolean validarProgramasEducativos(Integer idEvento, Integer[] codigoProgramasElegidos) {
        return delegateProgramaEducativo.validarProgramasEducativos(idEvento, codigoProgramasElegidos);
    }
    
}