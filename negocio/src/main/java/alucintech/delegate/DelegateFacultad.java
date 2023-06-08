package alucintech.delegate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import alucintech.entidad.Facultad;
import alucintech.integracion.ServiceLocator;
import java.util.List;

/**
 *
 * @author EduardoCardona <>
 */
public class DelegateFacultad {

    /**
     * Método que regresa una lista de las facultades registradas en la base de
     * datos.
     *
     * @return
     */
    public List<Facultad> obtenerFacultades() {
        return ServiceLocator.getInstanceFacultadDAO().findAll();
    }
    
    public void actualizarFacultad(Facultad facultad){
        ServiceLocator.getInstanceFacultadDAO().update(facultad);
    }
    
}
