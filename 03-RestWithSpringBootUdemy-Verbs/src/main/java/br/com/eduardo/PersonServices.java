package br.com.eduardo;

import br.com.eduardo.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    public Person create(Person person) {
        logger.info("Creating one person!");

        return person;
    }

    public Person update(Person person) {
        logger.info("Updating one person!");

        return person;
    }

    public void delete(String id) {
        logger.info("Deleting one person!");
    }

    public Person findById(String id) {
        logger.info("Fiding one person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Eduardo");
        person.setLastName("Rodrigues");
        person.setAddress("Itacarambi - MG - Brasil");
        person.setGender("Male");
        return person;
    }

    public Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Firstname "+i);
        person.setLastName("Lastname "+i);
        person.setAddress("Some address in Brasil");
        person.setGender("Male");
        return person;
    }
}
