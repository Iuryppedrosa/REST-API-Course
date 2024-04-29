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
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonMapper personMapper;

    public List<PersonVO> findAll(){
        List<PersonVO> listAqui = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
        for(PersonVO person : listAqui){
            person.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
        }
        return listAqui;
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

    public PersonVO delete(Long key) {
        personRepository.findById(key).map(p1 -> {
            personRepository.delete(p1);
            return p1;
        });
        return null;
    }
}
