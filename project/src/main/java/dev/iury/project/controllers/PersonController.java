package dev.iury.project.controllers;

import dev.iury.project.dataVO.PersonVO;
//import dev.iury.project.dataVO.PersonVO2;
import dev.iury.project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/{id}")
    public Optional<PersonVO> findById(@PathVariable Long id){
        return personService.findById(id);
    }

    @GetMapping
    public List<PersonVO> findAll(){
        return personService.findAll();
    }

    @PutMapping("/{id}")
    public PersonVO update(@RequestBody PersonVO person, @PathVariable Long id){
        return personService.newPersonVO(person, id);
    }

    @PostMapping
    public PersonVO create(@RequestBody PersonVO person){
        return personService.create(person);
    }
//
//    @PostMapping("/v2")
//    public PersonVO2 createV2(@RequestBody PersonVO2 person){
//        return personService.createV2(person);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
