package br.com.zucchicamila.java_spring_rest.services;

import br.com.zucchicamila.java_spring_rest.controllers.PersonController;
import br.com.zucchicamila.java_spring_rest.data.vo.v1.PersonVO;
import br.com.zucchicamila.java_spring_rest.data.vo.v2.PersonVOV2;
import br.com.zucchicamila.java_spring_rest.exceptions.ResourceNotFoundException;
import br.com.zucchicamila.java_spring_rest.mapper.MapperUtil;
import br.com.zucchicamila.java_spring_rest.mapper.custom.PersonMapper;
import br.com.zucchicamila.java_spring_rest.models.Person;
import br.com.zucchicamila.java_spring_rest.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

        Person entity = repository.findById(id)
                .orElseThrow(() -> {
                    logger.warning("Person with ID " + id + " was not found.");
                    return new ResourceNotFoundException("Person with ID " + id + " was not found.");
                });
        // Convertemos a entidade para VO:
        PersonVO entityVO = MapperUtil.parseObject(entity, PersonVO.class);
        /* Essa linha de código é típica em um ambiente Spring Boot ao usar o Spring HATEOAS para adicionar links
         * HATEOAS aos recursos de uma API REST.
         * 1. "vo.add()": o método "add" é chamado para adicionar um link ao objeto "vo".
         * 2. "linkTo()": é um método estático do Spring HATEOAS que ajuda a criar links. Esse método gera um link
         * para o recurso com base nas informações fornecidas.
         * 3. "methodOn(PersonController.class).findById(id)": "methodOn" é um método estático que simula a chamada
         * ao método do controlador ("PersonController").
         * "PersonController.class" especifica a classe do controlador que contém o método.
         * "findById(id)" especifica o método dentro do controlador que estamos referenciando, passando "id" como
         * argumento.
         * "withSelfRel()": é um método que adiciona uma relação (rel) "self" ao link, indicando que o link se refere
         * ao próprio recurso. Essa relação "self" é usada para denotar que o link aponta para o próprio recurso. */
        entityVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return entityVO;

    }

    public List<PersonVO> findAll() {

        logger.info("Finding all...");

        List<PersonVO> personList = MapperUtil.parseListObjects(repository.findAll(), PersonVO.class);
        personList.forEach(person -> person.add(linkTo(methodOn(PersonController.class).findById(person.getPersonId())).withSelfRel()));
        return personList;

    }

    public PersonVO create(PersonVO person) {

        logger.info("Creating a person...");

        // Converte PersonVO para Person
        Person entity = MapperUtil.parseObject(person, Person.class);
        // Salva a entidade no banco de dados para gerar o ID
        entity = repository.save(entity);
        // Converte Person para PersonVO
        PersonVO entityVO = MapperUtil.parseObject(entity, PersonVO.class);
        // Atribui o personId ao entityVO
        entityVO.setPersonId(entity.getPersonId());
        // Adiciona o link HATEOAS com o ID correto
        entityVO.add(linkTo(methodOn(PersonController.class).findById(entityVO.getPersonId())).withSelfRel());
        return entityVO;

    }

    public PersonVOV2 createV2(PersonVOV2 person) {

        logger.info("Creating a person with V2...");

        var entity = mapper.convertVOToEntity(person);
        return mapper.convertEntityToVO(repository.save(entity));

    }

    public PersonVO update(PersonVO person) {

        logger.info("Updating a person...");

        Person entity = repository.findById(person.getPersonId())
                .orElseThrow(() -> {
                    logger.warning("Person with ID " + person.getPersonId() + " was not found.");
                    return new ResourceNotFoundException("Person with ID " + person.getPersonId() + " was not found.");
                });

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonVO entityVO = MapperUtil.parseObject(entity, PersonVO.class);
        entityVO.add(linkTo(methodOn(PersonController.class).findById(entityVO.getPersonId())).withSelfRel());
        return entityVO;

    }

    public void delete(Long id) {

        logger.info("Deleting a person...");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        repository.delete(entity);

    }

}
