package br.com.zucchicamila.java_spring_rest.repositories;

import br.com.zucchicamila.java_spring_rest.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}