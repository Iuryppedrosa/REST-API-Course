package unittests.mockito;
import dev.iury.project.Exceptions.RiqueredObjectsNullException;
import dev.iury.project.dataVO.PersonVO;
import dev.iury.project.model.Person;
import dev.iury.project.repository.PersonRepository;
import dev.iury.project.service.PersonServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import unittests.mocks.MockPerson;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUpMocks() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Person> entityList = input.mockEntityList();
        when(repository.findAll()).thenReturn(entityList);


        var people = service.findAll();
        assertNotNull(people);
        assertEquals(14, people.size());

        var person1 = people.get(1);
        assertNotNull(person1);
        assertNotNull(person1.getKey());
        assertNotNull(person1.getLinks());
        System.out.println(person1.toString());

        assertTrue(person1.toString().contains("[</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", person1.getAddress());
        assertEquals("First Name Test1", person1.getFirstName());
        assertEquals("Last Name Test1", person1.getLastName());
        assertEquals("Female", person1.getGender());

        var person2 = people.get(4);
        assertNotNull(person2);
        assertNotNull(person2.getKey());
        assertNotNull(person2.getLinks());
        System.out.println(person2.toString());

        assertTrue(person2.toString().contains("[</api/person/v1/4>;rel=\"self\"]"));
        assertEquals("Addres Test4", person2.getAddress());
        assertEquals("First Name Test4", person2.getFirstName());
        assertEquals("Last Name Test4", person2.getLastName());
        assertEquals("Male", person2.getGender());

        var person7 = people.get(7);
        assertNotNull(person7);
        assertNotNull(person7.getKey());
        assertNotNull(person7.getLinks());
        System.out.println(person7.toString());

        assertTrue(person7.toString().contains("[</api/person/v1/7>;rel=\"self\"]"));
        assertEquals("Addres Test7", person7.getAddress());
        assertEquals("First Name Test7", person7.getFirstName());
        assertEquals("Last Name Test7", person7.getLastName());
        assertEquals("Female", person7.getGender());
    }

    @Test
    void findById() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        System.out.println(result.toString());
        assertTrue(result.toString().contains("[</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }



    @Test
    void create() {
        Person entity = input.mockEntity(1);
        Person persisted = entity;
        persisted.setId(1L);
        PersonVO vo = input.mockVO(1);
        vo.setKey(1l);

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        System.out.println(result.toString());
        assertTrue(result.toString().contains("[</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void update() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);


        Person persisted = entity;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1l);

        when(repository.save(entity)).thenReturn(persisted);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.update(vo, 1L);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        System.out.println(result.toString());
        assertTrue(result.toString().contains("[</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void delete() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        var result = service.delete(1L);
        assertNull(result);
    }

    @Test
    void createNull() {
        Exception exception = assertThrows(RiqueredObjectsNullException.class, () -> service.create(null));
        String expectedMessage = "Required objects are null and is not possible to continue.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void updateNull() {
        Exception exception = assertThrows(RiqueredObjectsNullException.class, () -> service.update(null, 1L));
        String expectedMessage = "Required objects are null and is not possible to continue.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}