package ar.com.laboratory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Datos personales de una persona")
@Data
@AllArgsConstructor
public class DatosPersonalesResponse {
    @Schema(description = "Nombre de la persona", example = "Maximiliano")
    private String nombre;

    @Schema(description = "Nombre de la apellido", example = "Soria")
    private String apellido;
}