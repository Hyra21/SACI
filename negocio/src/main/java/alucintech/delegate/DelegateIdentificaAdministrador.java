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
     * Método que regresa el objeto Identificaadministrador que concuerde con el
     * parametro "correo". Sirve para identificar al administrador de eventos al
     * que le pertenece el correo.
     *
     * @param correo
     * @return
     */
    public Identificaadministrador identificarAdmin(String correoAdministrador){
        Identificaadministrador idAdmin = new Identificaadministrador();
        List<Identificaadministrador> idAdmins = ServiceLocator.getInstanceIdentificaadministradorDAO().findAll();
        
        for(Identificaadministrador id:idAdmins){
            System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH"+ id.getCorreoAdministrador().getCorreo());
            System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH"+ correoAdministrador);
            if(id.getCorreoAdministrador().getCorreo().equalsIgnoreCase(correoAdministrador)){
                System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH"+ id.getCorreoAdministrador().getCorreo());
                idAdmin = id;
                System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH"+ idAdmin.getCorreoAdministrador().getCorreo());
            }
        }
        return idAdmin;
    }
    
}
