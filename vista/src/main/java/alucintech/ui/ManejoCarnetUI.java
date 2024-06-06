package alucintech.ui;

import alucintech.entidad.Carnet;
import alucintech.helper.ManejoCarnetHelper;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "ManejoCarnetUI")
@SessionScoped
public class ManejoCarnetUI implements Serializable {
    
    private Carnet carnet;
    private ManejoCarnetHelper manejoCarnetHelper;
    private List<Carnet> listaCarnet;

    // Constructor
    public ManejoCarnetUI() {
        manejoCarnetHelper = new ManejoCarnetHelper();
    }

    @PostConstruct
    public void init() {
        carnet = new Carnet();
        actualizarListaCarnet(); // Inicializa la lista de carnets
    }

    public Carnet getCarnet() {
        return carnet;
    }

    public void setCarnet(Carnet carnet) {
        this.carnet = carnet;
    }

    public List<Carnet> getListaCarnets() {
        return listaCarnet;
    }

    public void setListaCarnet(List<Carnet> listaCarnet) {
        this.listaCarnet = listaCarnet;
    }

    public void actualizarListaCarnet() {
        listaCarnet = manejoCarnetHelper.consultarCarnet();
    }
}
