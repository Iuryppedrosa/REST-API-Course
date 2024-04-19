package dev.iury.project.controllers;

import dev.iury.project.model.Person;
import dev.iury.project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/{id}")
    public Optional<Person> findById(@PathVariable Long id){
        return personService.findById(id);
    }

    @GetMapping
    public List<Person> findAll(){
        return personService.findAll();
    }

    @PutMapping("/{id}")
    public Person update(@RequestBody Person person, @PathVariable Long id){
        return personService.newPerson(person, id);
    }

    @PostMapping
    public Person create(@RequestBody Person person){
        return personService.create(person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        personService.delete(id);
    }
}
