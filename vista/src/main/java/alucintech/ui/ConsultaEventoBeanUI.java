package alucintech.ui;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import alucintech.entidad.Evento;
import alucintech.helper.ConsultaEventoHelper;
import java.util.List;

/**
 *
 * @author Kevin
 */
@ManagedBean(name = "ConsultaEventoUI")
@SessionScoped
public class ConsultaEventoBeanUI implements Serializable{
    private ConsultaEventoHelper consultaEventoHelper;
    private Evento evento;
    private List<Evento> listaEventos;
    private ConsultaEventoBeanUI c;
    
    public ConsultaEventoBeanUI() throws IOException{
        consultaEventoHelper = new ConsultaEventoHelper();
    }
    
    /**
     * Metodo postconstructor todo lo que este dentro de este metodo
     * sera la primero que haga cuando cargue la pagina
     */
    @PostConstruct
    public void init(){
        evento = new Evento();
        listaEventos = consultaEventoHelper.listaEventos();
        if(listaEventos.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No hay eventos registrados", ""));
        }
    }
    
    /* getters y setters*/

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }
     
}
