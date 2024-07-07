package org.acme.controller;

import org.acme.config.exception.CustomConflictException;
import org.acme.domain.dto.PersonaDTO;
import org.acme.domain.dto.PersonaResponse;
import org.acme.service.PersonaService;
import org.acme.util.JsonHandler;
import org.acme.util.XmlHandler;
import org.acme.util.annotations.CommonLogging;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/api/v1/example")
public class ExampleResource {

    @Inject
    JsonHandler jsonHandler;

    @Inject
    XmlHandler xmlHandler;

    @Inject
    PersonaService personaService;

    @ConfigProperty(name = "greeting")
    private String greeting;

    private static final Logger LOGGER = Logger.getLogger(ExampleResource.class.getName());

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() throws Exception {
        PersonaDTO persona = getPersona("Juan", "Perez", 30);
        String json = jsonHandler.toJson(persona);
        LOGGER.info("Persona to JSON: " + json);
        return json;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/custom/{name}")
    public String customHello(@PathParam("name") String name) {
        return greeting + " " + name + " how are you doing?";
    }


    @CommonLogging
    public PersonaDTO getPersona(String nombre, String apellido, Integer edad) {
        return new PersonaDTO(nombre, apellido, edad);
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/xml")
    public String getXml() throws Exception {
        PersonaDTO personaDTO = new PersonaDTO("Juan", "Perez", 30);
        String xml = xmlHandler.toXml(personaDTO);
        LOGGER.info("Persona to XML: " + xml);
        return xml;
    }
    @GET
    @Path("/exception")
    @Produces(MediaType.TEXT_PLAIN)
    @CommonLogging
    public String exception() {
        if (true) {
            throw new CustomConflictException("Este es un mensaje de error personalizado 2");
        }
        return "Hello, World!";
    }

    @GET
    @Path("/persona/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersona(@PathParam("id") Long id) {
            PersonaResponse persona = personaService.read(id);
            return Response.ok(persona).build();

    }

}