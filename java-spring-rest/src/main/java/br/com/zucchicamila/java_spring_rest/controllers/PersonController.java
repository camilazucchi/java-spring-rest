package br.com.zucchicamila.java_spring_rest.controllers;

import br.com.zucchicamila.java_spring_rest.data.vo.v1.PersonVO;
import br.com.zucchicamila.java_spring_rest.data.vo.v2.PersonVOV2;
import br.com.zucchicamila.java_spring_rest.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person/v1")
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public PersonVO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<PersonVO> findAll() {
        return service.findAll();
    }

    @PostMapping
    public PersonVO create(@RequestBody PersonVO person) {
        return service.create(person);
    }

    @PostMapping(path = "/v2")
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
        return service.createV2(person);
    }

    @PutMapping(value = "/{id}")
    public PersonVO update(@PathVariable Long id, @RequestBody PersonVO person) {
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
    }

}