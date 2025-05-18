package br.com.eduardo.services;

import br.com.eduardo.exception.ResourceNotFoundException;
import br.com.eduardo.model.Person;
import br.com.eduardo.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    PersonRepository repository;

    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    public List<Person> findAll() {

        logger.info("Finding all people!");

        return repository.findAll();
    }

    public Person create(Person person) {
        logger.info("Creating one person!");

        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating one person!");

        Person entity = repository.findById(person.getId())
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id) {
        Person entity =repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }

    public Person findById(Long id) {
        logger.info("Fiding one person!");

        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));
    }

}
