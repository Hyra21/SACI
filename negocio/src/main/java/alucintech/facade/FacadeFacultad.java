/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.facade;

import alucintech.delegate.DelegateFacultad;
import alucintech.entidad.Facultad;
import java.util.List;

/**
 *
 * @author EduardoCardona <>
 */
public class FacadeFacultad {
    
    private final DelegateFacultad delegateFacultad;

    public FacadeFacultad() {
        this.delegateFacultad = new DelegateFacultad();
    }
    
    public void guardarFacultad(Facultad facultad){
        delegateFacultad.saveFacultad(facultad);
    }
    
    public Facultad identificarFacultad(String nombreFacultad){
        return delegateFacultad.identificarFacultad(nombreFacultad);
    }
    
    public List<Facultad> obtenerFacultades(){
        return delegateFacultad.obtenerFacultades();
    }
    
}