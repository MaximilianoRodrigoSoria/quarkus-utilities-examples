package org.acme.ultil;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

public class DataUtil {

   public static final ExampleResponse EXAMPLE_RESPONSE = new ExampleResponse("Juan", "Perez", 30);

   public static final String EXAMPLE_RESPONSE_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
           "<exampleResponse>\n" +
           "    <apellido>Perez</apellido>\n" +
           "    <nombre>Juan</nombre>\n" +
           "    <edad>30</edad>\n" +
           "</exampleResponse>";

}


@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@RegisterForReflection
class ExampleResponse implements Serializable {
    private String nombre;
    private String apellido;
    private Integer edad;
}
