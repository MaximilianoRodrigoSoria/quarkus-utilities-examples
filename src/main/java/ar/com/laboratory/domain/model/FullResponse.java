package ar.com.laboratory.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@AllArgsConstructor
@Data
@Schema(description = "Todos los datos de una persona")
public class FullResponse {
    private DatosPersonalesResponse datosPersonales;
    private DatosDeContactoResponse datosDeContacto;
    private DatosDeDocumentacionResponse datosDeDocumentacion;
}
