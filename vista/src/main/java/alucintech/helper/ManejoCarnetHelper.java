
package alucintech.helper;

import alucintech.entidad.Carnet;
import alucintech.integracion.ServiceFacadeLocator;
import java.io.Serializable;
import java.util.List;


public class ManejoCarnetHelper implements Serializable {

   
    public List<Carnet> consultarCarnet() {
        return ServiceFacadeLocator.getInstanceFacadeCarnet().consultarCarnet();
    }
}

