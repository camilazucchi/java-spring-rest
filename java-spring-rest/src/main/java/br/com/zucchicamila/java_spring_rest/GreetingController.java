package br.com.zucchicamila.java_spring_rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // Método responsável por devolver o endpoint Greeting
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "world") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }

}
