package alucintech.delegate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import alucintech.entidad.Usuarios;
import alucintech.integracion.ServiceLocator;
import java.util.List;

/**
 *
 * @author EduardoCardona <>
 */
public class DelegateUsuarios {
    
    public Usuarios login(String password, String correo){
        Usuarios usuario = new Usuarios();
        List<Usuarios> usuarios = ServiceLocator.getInstanceUsuariosDAO().findAll();
        
        for(Usuarios us:usuarios){
            if(us.getContrasenaUsuario().equalsIgnoreCase(password) && us.getCorreo().equalsIgnoreCase(correo)){
                usuario = us;
            }
        }
        return usuario;
    }
    
    /**
     * Metodo de ejemplo para guardar Alumno
     * @param usuarios de tipo usuario con id 0 para que se cree un id nuevo
     */
    public void saveUsuarios(Usuarios usuarios){
        ServiceLocator.getInstanceUsuariosDAO().save(usuarios);
    }
    
}