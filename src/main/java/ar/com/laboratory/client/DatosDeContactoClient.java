package ar.com.laboratory.client;

import ar.com.laboratory.domain.model.DatosDeContactoResponse;
import ar.com.laboratory.domain.model.DatosPersonalesResponse;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DatosDeContactoClient {

    public DatosDeContactoResponse getContacto(String id) {
        return new DatosDeContactoResponse("maximilianorodrigosoria@gmail.com","1127043256");
    }
}