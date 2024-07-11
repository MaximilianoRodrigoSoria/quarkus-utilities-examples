package ar.com.laboratory.client;

import ar.com.laboratory.domain.model.DatosPersonalesResponse;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DatosPersonalesClient {

    public DatosPersonalesResponse getInfoPersonal(String id) {
        return new DatosPersonalesResponse("Maximiliano", "Soria");
    }
}