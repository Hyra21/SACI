/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.facade;

import alucintech.delegate.DelegateActividad;
import alucintech.entidad.Actividad;

/**
 *
 * @author EduardoCardona <>
 */
public class FacadeActividad {
    
    private final DelegateActividad delegateActividad;

    public FacadeActividad() {
        this.delegateActividad = new DelegateActividad();
    }
    
    public void guardarAlumno(Actividad actividad){
        delegateActividad.saveActividad(actividad);
    }
    
}