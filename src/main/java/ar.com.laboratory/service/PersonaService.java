package ar.com.laboratory.service;

import ar.com.laboratory.domain.dto.PersonaDTO;
import ar.com.laboratory.domain.dto.PersonaResponse;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PersonaService implements AbstractService<PersonaResponse, PersonaDTO, Long>{


    @Override
    public List<PersonaDTO> readAll() {
        return null;
    }

    @Override
    public PersonaDTO created(PersonaResponse request) {
        return null;
    }

    @Override
    public PersonaResponse read(Long aLong) {
        return new PersonaResponse("Juan", "Perez", 30);
    }

    @Override
    public PersonaResponse update(PersonaDTO request) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
