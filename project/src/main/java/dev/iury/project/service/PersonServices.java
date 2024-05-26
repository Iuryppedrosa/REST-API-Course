package dev.iury.project.service;
import dev.iury.project.Exceptions.ResourceNotFoundException;
import dev.iury.project.Exceptions.RiqueredObjectsNullException;
import dev.iury.project.controllers.PersonController;
import dev.iury.project.dataVO.PersonVO;
import dev.iury.project.dataVO2.PersonVOV2;
import dev.iury.project.mapper.DozerMapper;
import dev.iury.project.mapper.custom.PersonMapper;
import dev.iury.project.model.Person;
import dev.iury.project.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonMapper personMapper;

    public Page<PersonVO> findAll(Pageable pageable){
        var personPage = personRepository.findAll(pageable);
        var personVosPage = personPage.map(p -> DozerMapper.parseObject(p, PersonVO.class));

        personVosPage.map(p ->
                p.add(linkTo(methodOn(PersonController.class)
                        .findById(p.getKey()))
                        .withSelfRel()));
        return personVosPage;
    }

    public PersonVO findById(Long key){
        var entity = personRepository.findById(key).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id"));
        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(key)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person){
        if(person == null) throw new RiqueredObjectsNullException();
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVOV2 createv2(PersonVOV2 person){
        var entity = personMapper.convertVov2ToEntity(person);
        var vo = personMapper.convertEntityToVoV2(personRepository.save(entity));
        return vo;
    }

    public PersonVO update(PersonVO person, Long key) {
        if(person == null) throw new RiqueredObjectsNullException();
        var entity = personRepository.findById(key).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        entity.setLastName(person.getLastName());

        var savedEntity = personRepository.save(entity);
        var vo = DozerMapper.parseObject(savedEntity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(key)).withSelfRel());
        return vo;
    }

    @Transactional
    public PersonVO disablePerson(Long id) {

        logger.info("Disabling one person!");

        personRepository.disablePerson(id);

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO delete(Long key) {
        personRepository.findById(key).map(p1 -> {
            personRepository.delete(p1);
            return p1;
        });
        return null;
    }
}
