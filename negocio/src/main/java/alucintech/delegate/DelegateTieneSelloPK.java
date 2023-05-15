package alucintech.delegate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import alucintech.entidad.TieneselloPK;
import alucintech.integracion.ServiceLocator;

/**
 *
 * @author EduardoCardona <>
 */
public class DelegateTieneSelloPK {
    
    /**
     * Metodo de ejemplo para guardar Alumno
     * @param tieneSelloPK de tipo usuario con id 0 para que se cree un id nuevo
     */
    public void saveTieneSelloPK(TieneselloPK tieneSelloPK){
        ServiceLocator.getInstanceTieneselloPKDAO().save(tieneSelloPK);
    }
    
}
