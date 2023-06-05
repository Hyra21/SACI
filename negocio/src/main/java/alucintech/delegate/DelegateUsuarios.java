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
 * Clase que contiene los métodos que interactúan con la base de datos.
 *
 * @author EduardoCardona <>
 */
public class DelegateUsuarios {

    /**
     * Metodo para hacer login y validar el correo y contraseña.
     *
     * @param correo
     * @param password
     * @return
     */
    public Usuarios login(String password, String correo) {
        Usuarios usuario = new Usuarios();
        List<Usuarios> usuarios = ServiceLocator.getInstanceUsuariosDAO().findAll();

        for (Usuarios us : usuarios) {
            if (us.getContrasenaUsuario().equalsIgnoreCase(password) && us.getCorreo().equalsIgnoreCase(correo)) {
                usuario = us;
            }
        }
        return usuario;
    }

}
