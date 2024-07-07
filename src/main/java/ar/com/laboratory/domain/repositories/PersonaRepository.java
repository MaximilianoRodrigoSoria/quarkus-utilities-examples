package ar.com.laboratory.domain.repositories;

import ar.com.laboratory.domain.entity.Persona;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PersonaRepository {

    @Inject
    EntityManager entityManager;

    public List<Persona> findAll() {
        TypedQuery<Persona> query = entityManager.createQuery("SELECT p FROM Persona p", Persona.class);
        return query.getResultList();
    }

    public Persona findById(Long id) {
        return entityManager.find(Persona.class, id);
    }

    @Transactional
    public Persona save(Persona persona) {
        entityManager.persist(persona);
        return persona;
    }

    @Transactional
    public Persona update(Persona persona) {
        entityManager.merge(persona);
        return persona;
    }

    @Transactional
    public void delete(Long id) {
        Persona persona = findById(id);
        if (persona != null) {
            entityManager.remove(persona);
        }
    }
}
