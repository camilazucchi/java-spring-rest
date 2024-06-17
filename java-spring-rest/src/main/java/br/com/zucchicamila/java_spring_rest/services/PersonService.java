package br.com.zucchicamila.java_spring_rest.services;

import br.com.zucchicamila.java_spring_rest.exceptions.ResourceNotFoundException;
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

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person findById(Long id) {

        logger.info("Finding one person...");

        return repository.findById(id)
                .orElseThrow(() -> {
                    logger.warning("Person with ID " + id + " was not found.");
                    return new ResourceNotFoundException("Person with ID " + id + " was not found.");
                });

    }

    public List<Person> findAll() {

        logger.info("Finding all...");

        return repository.findAll();

    }

    public Person create(Person person) {

        logger.info("Creating a person...");
        return repository.save(person);

    }

    public Person update(Person person) {

        logger.info("Updating a person...");

        var entity = repository.findById(person.getId())
                .orElseThrow(() -> {
                    logger.warning("Person with ID " + person.getId() + " was not found.");
                    return new ResourceNotFoundException("Person with ID " + person.getId() + " was not found.");
                });

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);

    }

    public void delete(Long id) {

        logger.info("Deleting a person...");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        repository.delete(entity);

    }

}
