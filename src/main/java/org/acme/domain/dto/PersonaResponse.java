package org.acme.domain.dto;

import javax.xml.bind.annotation.XmlRootElement;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@RegisterForReflection
public class PersonaResponse implements Serializable {
    private String nombre;
    private String apellido;
    private Integer edad;
}
