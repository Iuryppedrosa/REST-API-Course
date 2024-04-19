package dev.iury.project.service;

import dev.iury.project.Exceptions.ResourceNotFoundException;
import dev.iury.project.model.Person;
import dev.iury.project.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PersonService {
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    public Person create(Person person){
        personRepository.save(person);
        return person;
    }

    public List<Person> findAll(){
        List<Person> listAqui = personRepository.findAll();
        for(Person person : listAqui){
            return listAqui;
        }
        return listAqui;
    }

    public Optional<Person> findById(Long id){
        return Optional.ofNullable(personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id")));
    }


    public Person delete(Long id) {
        personRepository.findById(id).map(p1 -> {
            personRepository.delete(p1);
            return p1;
        });
        return null;
    }

    public Person newPerson(Person person, Long id) {
        personRepository.findById(id).map(p1 -> {
            updatePerson(p1, person);
            return personRepository.save(p1);
        });
        return person;
    }
    private void updatePerson(Person p1, Person person){
        p1.setFirstName(person.getFirstName());
        p1.setAddress(person.getAddress());
        p1.setGender(person.getGender());
        p1.setLastName(person.getLastName());
    }
}
