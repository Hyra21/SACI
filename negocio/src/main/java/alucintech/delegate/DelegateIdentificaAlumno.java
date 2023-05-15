package alucintech.delegate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import alucintech.entidad.Identificaalumno;
import alucintech.integracion.ServiceLocator;

/**
 *
 * @author EduardoCardona <>
 */
public class DelegateIdentificaAlumno {
    
    /**
     * Metodo de ejemplo para guardar Alumno
     * @param identificaAlumno de tipo usuario con id 0 para que se cree un id nuevo
     */
    public void saveIdentificaAlumno(Identificaalumno identificaAlumno){
        ServiceLocator.getInstanceIdentificaalumnoDAO().save(identificaAlumno);
    }
    
}