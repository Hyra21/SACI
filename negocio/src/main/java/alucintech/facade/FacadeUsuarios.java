/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.facade;

import alucintech.delegate.DelegateUsuarios;
import alucintech.entidad.Usuarios;

/**
 * Clase que llamará a DelegateUsuarios para acceder a los métodos que interactuán con la base de datos.
 * @author 980014102 <>
 */
public class FacadeUsuarios {
    
    private final DelegateUsuarios delegateUsuarios;

    public FacadeUsuarios() {
        this.delegateUsuarios = new DelegateUsuarios();
    }
    /**
     * Metodo para hacer login y validar el correo y contraseña; llamara a la instancia de DelegateUsuarios.
     * @param correo
     * @param password
     * @return 
     */
    public Usuarios login(String password, String correo){
        return delegateUsuarios.login(password, correo);
    }
    
}