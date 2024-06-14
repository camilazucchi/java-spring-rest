package br.com.zucchicamila.java_spring_rest.services;

import br.com.zucchicamila.java_spring_rest.models.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    /* "AtomicLong" é uma classe que fornece uma forma atômica (thread-safe) de trabalhar com um valor longo.
     * Isso significa que operações de leitura e escrita no valor são atômicas, ou seja, são realizadas de maneira
     * segura em um ambiente de múltiplas threads.
     * Nesse caso, "counter" é usado para gerar um ID único para cada nova pessoa criada. */
    private Logger logger = Logger.getLogger(PersonService.class.getName());
    /* "Logger" é uma classe usada para registrar mensagens de log. O "Logger" ajuda a registrar informações sobre
     * a execução da aplicação, como mensagens de erro, avisos, informações e depuração. */

    public Person findById(Long id) {

        logger.info("Finding one person...");

        Person person = new Person();
        // Simula o ID da pessoa:
        person.setId(counter.incrementAndGet());
        person.setFirstName("Alice");
        person.setLastName("Padilha");
        person.setAddress("Florianópolis - Santa Catarina - Brasil");
        person.setGender("Female");
        return person;
    }

    public List<Person> findAll() {
        logger.info("Finding all...");
        // Declaramos uma listagem de pessoas:
        List<Person> persons = new ArrayList<>();
        // Iteramos em um array que vai de zero à oito:
        for (int i = 0; i < 8; i++) {
            // Mocka uma pessoa para cada item dessa lista:
            Person person = mockPerson(i);
            // Adiciona essa pessoa na nossa lista:
            persons.add(person);
        }
        return persons;
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        // Simula o ID da pessoa:
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name " + i);
        person.setLastName("Last name: " + i);
        person.setAddress("Some address in Brasil: " + i);
        person.setGender("Female");
        return person;
    }


}
