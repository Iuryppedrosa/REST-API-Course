package dev.iury.project.service;

import dev.iury.project.Exceptions.ResourceNotFoundException;
import dev.iury.project.dataVO.PersonVO;
import dev.iury.project.mapper.DozerMapper;
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

//    @Autowired
//    PersonMapper personMapper;

    public PersonVO create(PersonVO person){
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);

        return vo;
    }

    public List<PersonVO> findAll(){
        List<PersonVO> listAqui = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
        for(PersonVO person : listAqui){
            return listAqui;
        }
        return listAqui;
    }

    public Optional<PersonVO> findById(Long id){
        var entity = Optional.ofNullable(personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id")));
        return Optional.ofNullable(DozerMapper.parseObject(entity, PersonVO.class));
    }


    public PersonVO delete(Long id) {
        personRepository.findById(id).map(p1 -> {
            personRepository.delete(p1);
            return p1;
        });
        return null;
    }

    public PersonVO newPersonVO(PersonVO person, Long id) {
        var entity = DozerMapper.parseObject(person, Person.class);

        personRepository.findById(id).map(existingEntity -> {
            existingEntity.setFirstName(entity.getFirstName());
            existingEntity.setAddress(entity.getAddress());
            existingEntity.setGender(entity.getGender());
            existingEntity.setLastName(entity.getLastName());
            return personRepository.save(existingEntity);
        }).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        var savedEntity = personRepository.save(entity);
        return DozerMapper.parseObject(savedEntity, PersonVO.class);
    }

    private void updatePersonVO(Person existingEntity, PersonVO person){
        existingEntity.setFirstName(person.getFirstName());
        existingEntity.setAddress(person.getAddress());
        existingEntity.setGender(person.getGender());
        existingEntity.setLastName(person.getLastName());
    }

//    public PersonVO2 createV2(PersonVO2 personVO2) {
//        var entity = personMapper.parsePersontoPersonVO2(personVO2);
//        var vo = personMapper.parsePersonVO2toPerson(personRepository.save(entity));
//
//        return vo;
//    }
}
