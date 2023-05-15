/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.facade;

import alucintech.delegate.DelegateUsuarios;
import alucintech.entidad.Usuarios;

/**
 *
 * @author EduardoCardona <>
 */
public class FacadeUsuarios {
    
    private final DelegateUsuarios delegateUsuarios;

    public FacadeUsuarios() {
        this.delegateUsuarios = new DelegateUsuarios();
    }
    
    public void guardarUsuarios(Usuarios usuarios){
        delegateUsuarios.saveUsuarios(usuarios);
    }
    
    public Usuarios login(String password, String correo){
        return delegateUsuarios.login(password, correo);
    }
    
}