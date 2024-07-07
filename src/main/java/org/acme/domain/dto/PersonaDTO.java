package org.acme.domain.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Schema(description = "Representa los datos de una persona")
public class PersonaDTO {
    @Schema(description = "Es el nombre de una persona", example = "Juan")
    private String nombre;
    @Schema(description = "Es el apellido de una persona", example = "Perez")
    private String apellido;
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
