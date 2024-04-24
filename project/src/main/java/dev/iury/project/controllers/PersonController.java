package dev.iury.project.controllers;

import java.util.List;
import java.util.Optional;

import dev.iury.project.dataVO.PersonVO;
import dev.iury.project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping
    public List<PersonVO> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<PersonVO> findById(@PathVariable(value = "id") Long id) {
        return Optional.ofNullable(service.findById(id));
    }

    @PostMapping()
    public PersonVO create(@RequestBody PersonVO person) {
        return service.create(person);
    }

    @PutMapping("/{id}")
    public PersonVO update(@RequestBody PersonVO person, @PathVariable Long id){
        return service.update(person, id);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
