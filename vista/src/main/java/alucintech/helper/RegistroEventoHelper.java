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
 *
 * @author 980014102
 */
public class RegistroEventoHelper {
    /**
     * Metodo para hacer login llamara a la instancia de usuarioFacade
     * @param correo
     * @param password
     * @return 
     */
    public Usuarios Login(String correo, String password){
        return ServiceFacadeLocator.getInstanceFacadeUsuarios().login(password, correo);
    }
}
