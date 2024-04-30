package dev.iury.project.mapper.custom;
import dev.iury.project.dataVO2.PersonVOV2;
import dev.iury.project.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookMapper {
    public PersonVOV2 convertEntityToVoV2(Person person){
        PersonVOV2 vov2 = new PersonVOV2();
        vov2.setAddress(person.getAddress());
        vov2.setFirstName(person.getFirstName());
        vov2.setLastName(person.getLastName());
        vov2.setGender(person.getGender());
        vov2.setId(person.getId());
        vov2.setBirthDay(new Date());

        return vov2;
    }

    public Person convertVov2ToEntity(PersonVOV2 vov2){
        Person personEntity = new Person();
        personEntity.setAddress(vov2.getAddress());
        personEntity.setFirstName(vov2.getFirstName());
        personEntity.setLastName(vov2.getLastName());
        personEntity.setGender(vov2.getGender());
        personEntity.setId(vov2.getId());

        return personEntity;
    }
}
