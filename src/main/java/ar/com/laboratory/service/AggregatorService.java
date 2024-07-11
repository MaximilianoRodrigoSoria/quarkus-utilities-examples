package ar.com.laboratory.service;

import ar.com.laboratory.client.DatosDeContactoClient;
import ar.com.laboratory.client.DatosDeDocumentacionClient;
import ar.com.laboratory.client.DatosPersonalesClient;
import ar.com.laboratory.config.exception.IncompleteFlowException;
import ar.com.laboratory.domain.model.DatosDeContactoResponse;
import ar.com.laboratory.domain.model.DatosDeDocumentacionResponse;
import ar.com.laboratory.domain.model.DatosPersonalesResponse;
import ar.com.laboratory.domain.model.FullResponse;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;


@ApplicationScoped
public class AggregatorService {
    @Inject
    DatosPersonalesClient datosPersonalesClient;

    @Inject
    DatosDeDocumentacionClient datosDeDocumentacionClient;

    @Inject
    DatosDeContactoClient datosDeContactoClient;

    public CompletableFuture<DatosPersonalesResponse> llamarADatosPersonalesService() {
        return CompletableFuture.supplyAsync(() -> {
            return datosPersonalesClient.getInfoPersonal("1");
        });
    }

    public CompletableFuture<DatosDeDocumentacionResponse> llamarADatosDeDocumentacionService() {
        return CompletableFuture.supplyAsync(() -> {
            return datosDeDocumentacionClient.getDocumentacion("1");
        });
    }

    public CompletableFuture<DatosDeContactoResponse> llamarADatosDeContactoService() {
        return CompletableFuture.supplyAsync(() -> {
            return datosDeContactoClient.getContacto("1");
        });
    }

    public CompletableFuture<FullResponse> aggregateResponses() {

        CompletableFuture<DatosPersonalesResponse> datosPersonalesFuture = llamarADatosPersonalesService();
        CompletableFuture<DatosDeContactoResponse> datosDeContactoFuture = llamarADatosDeContactoService();
        CompletableFuture<DatosDeDocumentacionResponse> datosDeDocumentacionFuture = llamarADatosDeDocumentacionService();

        return CompletableFuture.allOf(datosPersonalesFuture, datosDeContactoFuture, datosDeDocumentacionFuture)
                .thenApplyAsync(voidd -> {
                    try {
                        DatosPersonalesResponse datosPersonalesResponse = datosPersonalesFuture.join();
                        DatosDeContactoResponse datosDeContactoResponse = datosDeContactoFuture.join();
                        DatosDeDocumentacionResponse datosDeDocumentacionResponse = datosDeDocumentacionFuture.join();
                        if (Objects.isNull(datosPersonalesResponse) || Objects.isNull(datosDeContactoResponse)|| Objects.isNull(datosDeDocumentacionResponse)) {
                            throw new IncompleteFlowException("One or more services did not return a response");
                        }
                        return new FullResponse(datosPersonalesResponse, datosDeContactoResponse,datosDeDocumentacionResponse);
                    } catch (Exception e) {
                        throw new IncompleteFlowException(e.getMessage());
                    }
                });
    }
}
