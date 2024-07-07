package ar.com.laboratory.domain.dto;

import javax.xml.bind.annotation.XmlRootElement;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@RegisterForReflection
@Schema(description = "Representa los datos de una persona")
public class PersonaResponse implements Serializable {
    @Schema(description = "Es el nombre de una persona", example = "Juan")
    private String nombre;
    @Schema(description = "Es el apellido de una persona", example = "Perez")
    private String apellido;
    @Schema(description = "Es la edad de una persona", example = "30")
    private Integer edad;
}
