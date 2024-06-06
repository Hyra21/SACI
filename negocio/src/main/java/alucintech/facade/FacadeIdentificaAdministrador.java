/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.facade;

import alucintech.delegate.DelegateIdentificaAdministrador;
import alucintech.entidad.Identificaadministrador;

/**
 *
 * @author EduardoCardona <>
 */
public class FacadeIdentificaAdministrador {
    
    private final DelegateIdentificaAdministrador delegateIdentificaAdministrador;

    public FacadeIdentificaAdministrador() {
        this.delegateIdentificaAdministrador = new DelegateIdentificaAdministrador();
    }
    
    public Identificaadministrador IdentificarAdmin(String correoAdministrador){
        return delegateIdentificaAdministrador.identificarAdmin(correoAdministrador);
    }  
}