/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.helper;

import alucintech.entidad.Evento;
import alucintech.entidad.Facultad;
import alucintech.entidad.Identificaadministrador;
import alucintech.integracion.ServiceFacadeLocator;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 980014102
 */
public class ManejoEventoHelper implements Serializable {

    public Evento eventoSeleccionado(int id){
        return ServiceFacadeLocator.getInstanceFacadeEvento().eventoSeleccionado(id);
    }
    public void modificarEvento(Evento evento) {
        ServiceFacadeLocator.getInstanceFacadeEvento().ModificarEvento(evento);
    }

    public int[] validarEvento(Evento evento) {
        return ServiceFacadeLocator.getInstanceFacadeEvento().validarEvento(evento);
    }

    public Facultad identificarFacultad(String nombreFacultad) {
        return ServiceFacadeLocator.getInstanceFacadeFacultad().identificarFacultad(nombreFacultad);
    }

    public List<Evento> Consulta() {
        return ServiceFacadeLocator.getInstanceFacadeEvento().ConsultaEvento();
    }

    public Identificaadministrador identificar(String correo) {
        return ServiceFacadeLocator.getInstanceFacadeIdentificaAdministrador().IdentificarAdmin(correo);
    }
    
    public List<Evento> listaEventoAdmin(Identificaadministrador admin){
        return ServiceFacadeLocator.getInstanceFacadeEvento().listaEventoAdmin(admin);
    }
    
    public void eliminarEvento(Evento evento){
        ServiceFacadeLocator.getInstanceFacadeEvento().eliminarEvento(evento);
    }
}
