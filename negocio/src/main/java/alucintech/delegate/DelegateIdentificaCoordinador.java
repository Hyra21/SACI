package alucintech.delegate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import alucintech.entidad.Identificacoordinador;
import alucintech.integracion.ServiceLocator;

/**
 *
 * @author EduardoCardona <>
 */
public class DelegateIdentificaCoordinador {
    
    /**
     * Metodo de ejemplo para guardar Alumno
     * @param identificaCoordinador de tipo usuario con id 0 para que se cree un id nuevo
     */
    public void saveIdentificaCoordinador(Identificacoordinador identificaCoordinador){
        ServiceLocator.getInstanceIdentificacoordinadorDAO().save(identificaCoordinador);
    }
    
}