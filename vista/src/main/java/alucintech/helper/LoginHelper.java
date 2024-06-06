/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.helper;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import alucintech.entidad.Alumno;
import alucintech.entidad.Usuarios;
import alucintech.integracion.ServiceFacadeLocator;

/**
 * Clase de apoyo para la clase LoginBeanUI que le permitirá utilizar los
 * métodos que interactúan con la base de datos.
 *
 * @author 980014102
 */
public class LoginHelper implements Serializable{
    

    /**
     * Metodo para hacer login y validar el correo y contraseña; llamara a la instancia de FacadeUsuarios.
     * @param correo
     * @param password
     * @return 
     */
    public Usuarios Login(String correo, String password){
        return ServiceFacadeLocator.getInstanceFacadeUsuarios().login(password, correo);
    }
    
    
    
}
