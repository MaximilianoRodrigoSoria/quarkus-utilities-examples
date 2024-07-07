package ar.com.laboratory.client;

import ar.com.laboratory.config.exception.MaxRetriesException;
import ar.com.laboratory.domain.model.TelefonoResponse;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.WebApplicationException;

@Path("/telefonos/info")
@RegisterRestClient
public interface TelefonoClient {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 3, delay = 1000, retryOn = {WebApplicationException.class}, abortOn = {MaxRetriesException.class})
    TelefonoResponse getTelefonoInfo(@PathParam("id") int id) throws MaxRetriesException;
}