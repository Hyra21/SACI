package alucintech.delegate;


import alucintech.entidad.Carnet;
import alucintech.integracion.ServiceLocator;
import alucintech.integracion.ServiceLocator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class DelegateCarnet {
    public List<Carnet> consultarCarnet() {
        return ServiceLocator.getInstanceCarnetDAO().findAll();
    }
}
