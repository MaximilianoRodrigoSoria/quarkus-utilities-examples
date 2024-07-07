package ar.com.laboratory.service;

import ar.com.laboratory.config.exception.PersonaNotFoundException;
import ar.com.laboratory.domain.dto.PersonaDTO;
import ar.com.laboratory.domain.dto.PersonaResponse;
import ar.com.laboratory.domain.entity.Persona;
import ar.com.laboratory.domain.mappers.PersonaMapper;
import ar.com.laboratory.domain.mappers.PersonaResponseMapper;
import ar.com.laboratory.domain.repositories.PersonaRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
@Slf4j
public class PersonaService implements AbstractService<PersonaDTO, PersonaResponse, Long>{


    @Inject
    PersonaRepository personaRepository;

    @Inject
    PersonaResponseMapper mapperResponse;

    PersonaMapper mapper;
    @Override
    public List<PersonaResponse> readAll() {
        return personaRepository.findAll().stream().map(mapperResponse::toResponse).collect(Collectors.toList());
    }

    @Override
    public  PersonaResponse created(PersonaDTO request) {
        Persona persona = mapper.toEntity(request);
        return mapperResponse.toResponse(personaRepository.save(persona));
    }

@Override
public PersonaResponse read(Long id){
    var persona = personaRepository.findById(id);
    if(Objects.isNull(persona)){
        log.error("Persona no encontrada con id: {}", id);
        throw new PersonaNotFoundException("Persona no encontrada");
    }else {
        return mapperResponse.toResponse(persona);
    }
}

    @Override
    public PersonaResponse update(PersonaDTO request) {
        Persona persona = personaRepository.findByNombreAndApellido(request.getNombre(), request.getApellido()).orElseThrow(() -> new PersonaNotFoundException("Persona no encontrada"));
        persona.setNombre(request.getNombre());
        persona.setEdad(request.getEdad());
        persona.setApellido(request.getApellido());
        return mapperResponse.toResponse(personaRepository.save(persona));
    }

    @Override
    public void delete(Long id) {
            this.read(id);
            personaRepository.delete(id);
    }
}
