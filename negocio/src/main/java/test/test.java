/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import alucintech.entidad.Usuarios;
import alucintech.integracion.ServiceFacadeLocator;
/**
 *
 * @author 980014102
 */
public class test {
    public static void main(String[] args){
        Usuarios usuario = new Usuarios();
        
        usuario = ServiceFacadeLocator.getInstanceFacadeUsuarios().login("1234", "admin@uabc.edu.mx");
        if(usuario.getCorreo() != null){
            System.out.println("Login exitoso con Correo" + usuario.getCorreo());
        }else{
            System.out.println("No se encontraron registros");
        }
    }
    
}
