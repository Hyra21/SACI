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
     * Metodo de ejemplo para guardar Alumno
     * @param facultad de tipo usuario con id 0 para que se cree un id nuevo
     */
    public void saveFacultad(Facultad facultad){
        ServiceLocator.getInstanceFacultadDAO().save(facultad);
    }
    public Facultad identificarFacultad(String nombreFacultad){
        Facultad facultad = new Facultad();
        List<Facultad> facultadList = ServiceLocator.getInstanceFacultadDAO().findAll();
        
        for(Facultad f:facultadList){
            if(f.getNombreFacultad().equalsIgnoreCase(nombreFacultad)){
                facultad = f;
            }
        }
        return facultad;
    }
    public List<Facultad> obtenerFacultades(){
        return ServiceLocator.getInstanceFacultadDAO().findAll();
    }
}
