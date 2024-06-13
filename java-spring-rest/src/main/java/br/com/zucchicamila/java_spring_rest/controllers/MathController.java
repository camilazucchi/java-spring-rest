package br.com.zucchicamila.java_spring_rest.controllers;

import br.com.zucchicamila.java_spring_rest.utilities.MathOperations;
import br.com.zucchicamila.java_spring_rest.utilities.MathUtil;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathController {

    private final MathOperations mathOperations = new MathOperations();

    // Soma:
    @GetMapping(value = "/sum/{numberOne}/{numberTwo}")
    public Double sumNumbers(@PathVariable(value = "numberOne") Double numberOne,
                             @PathVariable(value = "numberTwo") Double numberTwo) {
        MathUtil.validateNumeric(numberOne, numberTwo);
        return mathOperations.sum(numberOne, numberTwo);
    }

    // Subtração:
    @GetMapping(value = "/subtract/{numberOne}/{numberTwo}")
    public Double subtractNumbers(@PathVariable(value = "numberOne") Double numberOne,
                                  @PathVariable(value = "numberTwo") Double numberTwo) {
        MathUtil.validateNumeric(numberOne, numberTwo);
        return mathOperations.subtraction(numberOne, numberTwo);
    }

    // Multiplicação:
    @GetMapping(value = "/multiply/{numberOne}/{numberTwo}")
    public Double multiplyNumbers(@PathVariable(value = "numberOne") Double numberOne,
                                  @PathVariable(value = "numberTwo") Double numberTwo) {
        MathUtil.validateNumeric(numberOne, numberTwo);
        return mathOperations.multiplication(numberOne, numberTwo);
    }

    // Divisão:
    @GetMapping(value = "/divide/{numberOne}/{numberTwo}")
    public Double divideNumbers(@PathVariable(value = "numberOne") Double numberOne,
                                @PathVariable(value = "numberTwo") Double numberTwo) {
        MathUtil.validateNumeric(numberOne, numberTwo);
        MathUtil.validateDivisionByZero(numberTwo);
        return mathOperations.division(numberOne, numberTwo);
    }

    // Média:
    @GetMapping(value = "/average/{numberOne}/{numberTwo}")
    public Double calculateAverage(@PathVariable(value = "numberOne") Double numberOne,
                                   @PathVariable(value = "numberTwo") Double numberTwo) {
        MathUtil.validateNumeric(numberOne, numberTwo);
        return mathOperations.mean(numberOne, numberTwo);
    }

    @GetMapping(value = "/sqrt/{number}")
    public Double calculateSquareRoot(@PathVariable(value = "number") Double number) {
        MathUtil.validateNumeric(number);
        MathUtil.validateNonNegative(number);
        return mathOperations.squareRoot(number);
    }

}