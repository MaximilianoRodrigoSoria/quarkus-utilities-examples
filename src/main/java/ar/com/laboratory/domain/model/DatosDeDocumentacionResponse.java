package ar.com.laboratory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@AllArgsConstructor
@Schema(description = "Documentacion de una persona")
public class DatosDeDocumentacionResponse {
    @Schema(description = "DNI de la persona", example = "33444555")
    private String dni;
}
