/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alucintech.helper;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import alucintech.entidad.Evento;
import alucintech.entidad.Facultad;
import alucintech.integracion.ServiceFacadeLocator;
import alucintech.entidad.Identificaadministrador;
import java.util.List;
/**
 *
 * @author 980014102
 */
public class ConsultaEventoHelper implements Serializable{
    public List<Evento> listaEventos(){
        return  ServiceFacadeLocator.getInstanceFacadeEvento().ConsultaEvento();
    }
}
