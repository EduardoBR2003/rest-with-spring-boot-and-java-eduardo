package br.com.eduardo.services;

import br.com.eduardo.data.dto.PersonDTO;
import br.com.eduardo.exception.ResourceNotFoundException;
import br.com.eduardo.mapper.ObjectMapper;
import br.com.eduardo.model.Person;
import br.com.eduardo.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static br.com.eduardo.mapper.ObjectMapper.parseListObjects;
import static br.com.eduardo.mapper.ObjectMapper.parseObject;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    PersonRepository repository;

    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    public List<PersonDTO> findAll() {

        logger.info("Finding all people!");

        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Creating one person!");

        var entity = parseObject(person, Person.class);


        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Updating one person!");

        Person entity = repository.findById(person.getId())
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        Person entity =repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }

    public PersonDTO findById(Long id) {
        logger.info("Fiding one person!");

        var entity = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this ID!"));

        return parseObject(entity, PersonDTO.class);
    }

}
