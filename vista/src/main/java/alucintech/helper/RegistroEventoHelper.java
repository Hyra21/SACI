/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.helper;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import alucintech.entidad.Usuarios;
import alucintech.entidad.Evento;
import alucintech.entidad.Facultad;
import alucintech.integracion.ServiceFacadeLocator;
import alucintech.entidad.Identificaadministrador;
import java.util.List;

/**
 *
 * @author 980014102
 */
public class RegistroEventoHelper implements Serializable {

    public void RegistroEvento(Evento evento) {

        ServiceFacadeLocator.getInstanceFacadeEvento().RegistrarEvento(evento);
    }

    public int[] validarEvento(Evento evento) {
        return ServiceFacadeLocator.getInstanceFacadeEvento().validarEvento(evento);
    }

    public Facultad identificarFacultad(String nombreFacultad) {
        return ServiceFacadeLocator.getInstanceFacadeFacultad().identificarFacultad(nombreFacultad);
    }

    public Identificaadministrador identificar(String correo) {
        return ServiceFacadeLocator.getInstanceFacadeIdentificaAdministrador().IdentificarAdmin(correo);
    }

    public List<Evento> Consulta() {
        return ServiceFacadeLocator.getInstanceFacadeEvento().ConsultaEvento();
    }

    public List<Facultad> obtenerFacultades(){
        return ServiceFacadeLocator.getInstanceFacadeFacultad().obtenerFacultades();
    }
}
