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
 * Clase que se encargará de ejecutar las acciones requeridas por el archivo
 * login.xhtml
 *
 * @author 980014102
 */
//Nombre por el cual la clase será reconocida en el archivo login.xhtml
@ManagedBean(name = "loginUI")
@SessionScoped

public class LoginBeanUI implements Serializable {

    //Objeto loginHelper que ejecutará los métodos necesarios.
    private LoginHelper loginHelper;
    //Objeto Usuarios que recibirá los valores ingresados en login.xhtml
    private Usuarios usuario;

    //Constructor
    public LoginBeanUI() {
        loginHelper = new LoginHelper();
    }

    /**
     * Metodo postconstructor todo lo que este dentro de este metodo sera la
     * primero que haga cuando cargue la pagina
     */
    @PostConstruct
    public void init() {
        usuario = new Usuarios();
    }

    /**
     * Método encargado de iniciar sesión en la página validando las
     * credenciales ingresadas
     *
     * @throws IOException
     */
    public void login() throws IOException {
        // los atributos de "usuario" vienen del xhtml 
        // "us" recibirá lo que se obtenga del método Login que utiliza el loginHelper

        Usuarios us = new Usuarios();
        us = loginHelper.Login(usuario.getCorreo(), usuario.getContrasenaUsuario());

        //Este if sirve para decidiri a que ventana enviar al usuario 
        //dependiendo de su tipo y además para mostrar el mensaje de error 
        //en caso de que la validación no regrese un objeto evento.
        if (us != null && us.getCorreo() != null) {
            usuario = us;
            if (us.getTipoUsuario().compareToIgnoreCase("Administrador de eventos") == 0) {
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/index.xhtml");
            }
            if (us.getTipoUsuario().compareToIgnoreCase("Cordinador de carrera") == 0) {

            }
            if (us.getTipoUsuario().compareToIgnoreCase("Subdireccion") == 0) {

            }
            if (us.getTipoUsuario().compareToIgnoreCase("Encargad@ de vinculacion") == 0) {

            }
            if (us.getTipoUsuario().compareToIgnoreCase("Alumno") == 0) {

            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrecta:", "Intente de nuevo"));
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
