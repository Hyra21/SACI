/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.ui;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import alucintech.entidad.Usuarios;
import alucintech.helper.LoginHelper;

/**
 *
 * @author Kevin
 */
@ManagedBean(name = "loginUI")
@SessionScoped
public class LoginBeanUI implements Serializable{
    private LoginHelper loginHelper;
    private Usuarios usuario;
    
    public LoginBeanUI() {
        loginHelper = new LoginHelper();
    }
    
    /**
     * Metodo postconstructor todo lo que este dentro de este metodo
     * sera la primero que haga cuando cargue la pagina
     */
    @PostConstruct
    public void init(){
        usuario = new Usuarios();
    }

     public void login() throws IOException{
        String appURL = "/index.xhtml";
        // los atributos de usuario vienen del xhtml 
        Usuarios us= new Usuarios();
        us.setCorreo("admin@uabc.edu.mx");
        us = loginHelper.Login(usuario.getCorreo(), usuario.getContrasenaUsuario());
          if(us != null && us.getCorreo()!=null){
            // asigno el usuario encontrado al usuario de esta clase para que 
            // se muestre correctamente en la pagina de informacion
            usuario=us;
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + appURL);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario o contraseña incorrecta:", "Intente de nuevo"));          
        }
    }

    
    /* getters y setters*/

    public Usuarios getUsuarios() {
        return usuario;
    }

    public void setUsuario(Usuarios usuarios) {
        this.usuario = usuario;
    }
    
    
    
    
    
    
    
    

    

    
}
