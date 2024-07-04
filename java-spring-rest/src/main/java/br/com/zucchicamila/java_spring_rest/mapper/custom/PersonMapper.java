package br.com.zucchicamila.java_spring_rest.mapper.custom;

import br.com.zucchicamila.java_spring_rest.data.vo.v1.PersonVO;
import br.com.zucchicamila.java_spring_rest.data.vo.v2.PersonVOV2;
import br.com.zucchicamila.java_spring_rest.models.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    // Este método converte uma entidade "Person" em um objeto "PersonVOV2":
    public PersonVOV2 convertEntityToVO(Person person) {
        PersonVOV2 vov2 = new PersonVOV2();
        vov2.setId(person.getPersonId());
        vov2.setFirstName(person.getFirstName());
        vov2.setLastName(person.getLastName());
        vov2.setAddress(person.getAddress());
        vov2.setGender(person.getGender());
        vov2.setBirthDay(new Date());
        return vov2;
    }

    public Person convertVOToEntity(PersonVO personVO) {
        Person person = new Person();
        person.setPersonId(personVO.getPersonId());
        person.setFirstName(personVO.getFirstName());
        person.setLastName(personVO.getLastName());
        person.setAddress(personVO.getAddress());
        person.setGender(personVO.getGender());
        return person;
    }

    // Este método converte um objeto "PersonVOV2" em uma entidade "Person":
    public Person convertVOToEntity(PersonVOV2 personVOV2) {
        Person person = new Person();
        person.setPersonId(personVOV2.getId());
        person.setFirstName(personVOV2.getFirstName());
        person.setLastName(personVOV2.getLastName());
        person.setAddress(personVOV2.getAddress());
        person.setGender(personVOV2.getGender());
        return person;
    }


}
