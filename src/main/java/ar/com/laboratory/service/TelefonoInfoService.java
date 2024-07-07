package ar.com.laboratory.service;

import ar.com.laboratory.client.TelefonoClient;
import ar.com.laboratory.config.exception.MaxRetriesException;
import ar.com.laboratory.domain.model.TelefonoResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TelefonoInfoService {

    @Inject
    @RestClient
    TelefonoClient telefonoClient;

    public TelefonoResponse getTelefonoInfo(int id) throws MaxRetriesException {
        return telefonoClient.getTelefonoInfo(id);
    }
}