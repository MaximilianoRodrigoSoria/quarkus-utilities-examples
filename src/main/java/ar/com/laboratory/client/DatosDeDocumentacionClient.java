package ar.com.laboratory.client;

import ar.com.laboratory.domain.model.DatosDeDocumentacionResponse;
import ar.com.laboratory.domain.model.DatosPersonalesResponse;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DatosDeDocumentacionClient {

    public DatosDeDocumentacionResponse getDocumentacion(String id) {
        return new DatosDeDocumentacionResponse("34080910");
    }
}