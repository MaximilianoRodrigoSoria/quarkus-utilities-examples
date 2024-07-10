package ar.com.laboratory.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@AllArgsConstructor
@Schema(description = "Datos de contacto de una persona")
public class DatosDeContactoResponse {
    @Schema(description = "Email de una persona", example = "maximilianorodrigosoria@gmail.com")
    private String email;
    @Schema(description = "Telefono de una persona", example = "1122333444")
    private String telefono;
}
