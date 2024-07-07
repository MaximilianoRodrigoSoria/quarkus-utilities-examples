package org.acme.ultil;

import org.acme.domain.dto.PersonaResponse;
import org.acme.util.XmlHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XmlHandlerTest {

    private XmlHandler xmlHandler;

    @BeforeEach
    void setUp() {
        xmlHandler = new XmlHandler();
    }

    @Test
    @DisplayName("toXml returns valid xml string for PersonaResponse object")
    void toXmlReturnsValidXmlStringForPersonaResponse() throws Exception {
        //Assemble
        ExampleResponse exampleResponse = DataUtil.EXAMPLE_RESPONSE;

        //Act
        String result = xmlHandler.toXml(exampleResponse);

        //Assert
        Assertions.assertTrue(result.contains("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"));
        Assertions.assertTrue(result.contains("<exampleResponse>"));
        Assertions.assertTrue(result.contains("<apellido>Perez</apellido>"));
        Assertions.assertTrue(result.contains("<nombre>Juan</nombre>"));
        Assertions.assertTrue(result.contains("<edad>30</edad>"));
        Assertions.assertTrue(result.contains("</exampleResponse>"));
    }

    @Test
    @DisplayName("toXml throws Exception for non-JAXB-annotated object")
    void toXmlThrowsExceptionForNonJAXBAnnotatedObject() {
        // Assemble
        Object nonJAXBAnnotatedObject = new Object();

        // Act and Assert
        Exception exception = assertThrows(Exception.class, () -> {
            xmlHandler.toXml(nonJAXBAnnotatedObject);
        });

        String expectedMessage = "Could not parse object of type " + nonJAXBAnnotatedObject.getClass().getName() + " to XML";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }



}