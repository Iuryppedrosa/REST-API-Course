package dev.iury.project.service;
import dev.iury.project.Exceptions.ResourceNotFoundException;
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
            return listAqui;
        }
        return listAqui;
    }

    public PersonVO findById(Long id){
        var entity = personRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person){
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;
    }

    public PersonVOV2 createv2(PersonVOV2 person){
        var entity = personMapper.convertVov2ToEntity(person);
        var vo = personMapper.convertEntityToVoV2(personRepository.save(entity));
        return vo;
    }

    public PersonVO update(PersonVO person, Long id) {
        var entity = personRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        entity.setLastName(person.getLastName());

        var savedEntity = personRepository.save(entity);
        return DozerMapper.parseObject(savedEntity, PersonVO.class);
    }

    public PersonVO delete(Long id) {
        personRepository.findById(id).map(p1 -> {
            personRepository.delete(p1);
            return p1;
        });
        return null;
    }
}
