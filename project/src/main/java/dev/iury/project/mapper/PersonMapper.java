//package dev.iury.project.mapper;
//
//
//import dev.iury.project.dataVO.PersonVO2;
//import dev.iury.project.model.Person;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//public class PersonMapper {
//    public PersonVO2 parsePersonVO2toPerson(Person person) {
//        PersonVO2 personVO2 = new PersonVO2();
//        personVO2.setFirstName(person.getFirstName());
//        personVO2.setLastName(person.getLastName());
//        personVO2.setAddress(person.getAddress());
//        personVO2.setGender(person.getGender());
//        personVO2.setDate(new Date());
//        return personVO2;
//    }
//
//    public Person parsePersontoPersonVO2(PersonVO2 personv2) {
//        Person person = new Person();
//        person.setFirstName(person.getFirstName());
//        person.setLastName(person.getLastName());
//        person.setAddress(person.getAddress());
//        person.setGender(person.getGender());
//        return person;
//    }
//}
