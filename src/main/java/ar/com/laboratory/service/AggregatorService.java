package ar.com.laboratory.service;

import ar.com.laboratory.config.exception.IncompleteFlowException;
import ar.com.laboratory.domain.model.DatosDeContactoResponse;
import ar.com.laboratory.domain.model.DatosDeDocumentacionResponse;
import ar.com.laboratory.domain.model.DatosPersonalesResponse;
import ar.com.laboratory.domain.model.FullResponse;

import javax.enterprise.context.ApplicationScoped;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;


@ApplicationScoped
public class AggregatorService {
    public CompletableFuture<DatosPersonalesResponse> llamarADatosPersonalesService() {
        // Lógica para llamar al servicio 1
        return CompletableFuture.supplyAsync(() -> {
            // Simulación de llamada al servicio
            DatosPersonalesResponse datosPersonales = new DatosPersonalesResponse("Maximiliano", "Soria");
            return datosPersonales;
        });
    }

    public CompletableFuture<DatosDeDocumentacionResponse> llamarADatosDeDocumentacionService() {
        // Lógica para llamar al servicio 2
        return CompletableFuture.supplyAsync(() -> {
            // Simulación de llamada al servicio
            DatosDeDocumentacionResponse datosDeDocumentacion = new DatosDeDocumentacionResponse("34080910");
            return datosDeDocumentacion;
        });
    }

    public CompletableFuture<DatosDeContactoResponse> llamarADatosDeContactoService() {
        // Lógica para llamar al servicio 3
        return CompletableFuture.supplyAsync(() -> {
            // Simulación de llamada al servicio
            DatosDeContactoResponse datosDeContacto = new DatosDeContactoResponse("maximilianorodrigosoria@gmail.com","1127043256");
            return datosDeContacto;
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
