package ar.com.laboratory.domain.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@RegisterForReflection
@Schema(description = "Representa los datos de una persona")
public class PersonaDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    @Schema(description = "Es el nombre de una persona", example = "Juan")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 50, message = "El apellido no puede tener más de 50 caracteres")
    @Schema(description = "Es el apellido de una persona", example = "Perez")
    private String apellido;

    @NotNull(message = "La edad no puede ser nula")
    @Min(value = 0, message = "La edad no puede ser menor que 0")
    @Max(value = 120, message = "La edad no puede ser mayor que 120")
    @Schema(description = "Es la edad de una persona", example = "30")
    private Integer edad;

    public PersonaDTO() {
    }

    public PersonaDTO(String nombre, String apellido, Integer edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "PersonaDTO{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                '}';
    }
}