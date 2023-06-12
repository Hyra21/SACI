package alucintech.delegate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import alucintech.entidad.Actividad;
import alucintech.entidad.Sello;
import alucintech.integracion.ServiceLocator;
import java.util.List;

/**
 *
 * @author EduardoCardona <>
 */
public class DelegateSello {
    
    public void registrarSello(Sello sello) {
        ServiceLocator.getInstanceSelloDAO().save(sello);
    }
    
    public List<Sello> consultarSellos(){
        return ServiceLocator.getInstanceSelloDAO().findAll();
    }
    
    public void actualizarSello(Sello sello){
        ServiceLocator.getInstanceSelloDAO().update(sello);
    }
}
