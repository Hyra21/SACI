package alucintech.delegate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import alucintech.entidad.Identificaadministrador;
import alucintech.integracion.ServiceLocator;
import alucintech.entidad.Usuarios;
import java.util.List;
/**
 *
 * @author EduardoCardona <>
 */
public class DelegateIdentificaAdministrador {
    
    /**
     * Metodo de ejemplo para guardar Alumno
     * @param identificaAdministrador de tipo usuario con id 0 para que se cree un id nuevo
     */
    public void saveIdentificaAdministrador(Identificaadministrador identificaAdministrador){
        ServiceLocator.getInstanceIdentificaadministradorDAO().save(identificaAdministrador);
    }
    
    public Identificaadministrador identificar(String correoAdministrador){
        Identificaadministrador idAdmin = new Identificaadministrador();
        List<Identificaadministrador> idAdmins = ServiceLocator.getInstanceIdentificaadministradorDAO().findAll();
        
        for(Identificaadministrador id:idAdmins){
            if(id.getCorreoAdministrador().getCorreo().equalsIgnoreCase(correoAdministrador)){
                idAdmin = id;
            }
        }
        return idAdmin;
    }
    
}
