package alucintech.delegate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import alucintech.entidad.Carnet;
import alucintech.integracion.ServiceLocator;

/**
 *
 * @author EduardoCardona <>
 */
public class DelegateCarnet {
    
    /**
     * Metodo de ejemplo para guardar Alumno
     * @param carnet de tipo usuario con id 0 para que se cree un id nuevo
     */
    public void saveCarnet(Carnet carnet){
        ServiceLocator.getInstanceCarnetDAO().save(carnet);
    }
    
}
