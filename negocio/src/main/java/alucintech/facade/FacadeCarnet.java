
package alucintech.facade;

import alucintech.delegate.DelegateCarnet;
import alucintech.entidad.Carnet;
import java.util.List;
public class FacadeCarnet {
    
    private final DelegateCarnet delegateCarnet;

    public FacadeCarnet() {
        this.delegateCarnet = new DelegateCarnet();
    }
      public List<Carnet> consultarCarnet() {
        return delegateCarnet.consultarCarnet();
    }

    
}
