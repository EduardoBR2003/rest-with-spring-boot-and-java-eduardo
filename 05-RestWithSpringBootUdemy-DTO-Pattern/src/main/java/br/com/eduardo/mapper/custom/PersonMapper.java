package br.com.eduardo.mapper.custom;

import br.com.eduardo.data.dto.v2.PersonDTOV2;
import br.com.eduardo.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonDTOV2 convertEntityToDTO(Person person) {
        PersonDTOV2 personDTO = new PersonDTOV2();
        personDTO.setId(person.getId());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        personDTO.setBirthDay(new Date());
        personDTO.setAddress(person.getAddress());
        personDTO.setGender(person.getGender());

        return personDTO;
    }

    public Person convertDTOToEntity(PersonDTOV2 personDTO) {
        Person entity = new Person();

        entity.setFirstName(personDTO.getFirstName());
        entity.setLastName(personDTO.getLastName());
//        entity.setBirthDay(personDTO.getBirthDay());
        entity.setAddress(personDTO.getAddress());
        entity.setGender(personDTO.getGender());

        return entity;
    }

}
