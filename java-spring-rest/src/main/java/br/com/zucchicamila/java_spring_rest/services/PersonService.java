package br.com.zucchicamila.java_spring_rest.services;

import br.com.zucchicamila.java_spring_rest.data.vo.v1.PersonVO;
import br.com.zucchicamila.java_spring_rest.data.vo.v2.PersonVOV2;
import br.com.zucchicamila.java_spring_rest.exceptions.ResourceNotFoundException;
import br.com.zucchicamila.java_spring_rest.mapper.MapperUtil;
import br.com.zucchicamila.java_spring_rest.mapper.custom.PersonMapper;
import br.com.zucchicamila.java_spring_rest.models.Person;
import br.com.zucchicamila.java_spring_rest.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());
    /* "Logger" é uma classe usada para registrar mensagens de log. O "Logger" ajuda a registrar informações sobre
     * a execução da aplicação, como mensagens de erro, avisos, informações e depuração. */

    private final PersonRepository repository;
    private final PersonMapper mapper;

    @Autowired
    public PersonService(PersonRepository repository, PersonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PersonVO findById(Long id) {

        logger.info("Finding one person...");

        var entity = repository.findById(id)
                .orElseThrow(() -> {
                    logger.warning("Person with ID " + id + " was not found.");
                    return new ResourceNotFoundException("Person with ID " + id + " was not found.");
                });
        return MapperUtil.parseObject(entity, PersonVO.class);

    }

    public List<PersonVO> findAll() {

        logger.info("Finding all...");

        return MapperUtil.parseListObjects(repository.findAll(), PersonVO.class);

    }

    public PersonVO create(PersonVO person) {

        logger.info("Creating a person...");

        var entity = MapperUtil.parseObject(person, Person.class);
        return MapperUtil.parseObject(repository.save(entity), PersonVO.class);

    }

    public PersonVOV2 createV2(PersonVOV2 person) {

        logger.info("Creating a person with V2...");

        var entity = mapper.convertVOToEntity(person);
        return mapper.convertEntityToVO(repository.save(entity));

    }

    public PersonVO update(PersonVO person) {

        logger.info("Updating a person...");

        var entity = repository.findById(person.getPersonId())
                .orElseThrow(() -> {
                    logger.warning("Person with ID " + person.getPersonId() + " was not found.");
                    return new ResourceNotFoundException("Person with ID " + person.getPersonId() + " was not found.");
                });

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return MapperUtil.parseObject(repository.save(entity), PersonVO.class);

    }

    public void delete(Long id) {

        logger.info("Deleting a person...");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        repository.delete(entity);

    }

}
