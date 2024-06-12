package br.com.zucchicamila.java_spring_rest;

import br.com.zucchicamila.java_spring_rest.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathController {

    // Soma:
    @GetMapping(value = "/sum/{numberOne}/{numberTwo}")
    public Double sumNumbers(@PathVariable(value = "numberOne") String numberOne,
                             @PathVariable(value = "numberTwo") String numberTwo) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    // Subtração:
    @GetMapping(value = "/subtract/{numberOne}/{numberTwo}")
    public Double subtractNumbers(@PathVariable(value = "numberOne") String numberOne,
                                  @PathVariable(value = "numberTwo") String numberTwo) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    // Multiplicação:
    @GetMapping(value = "/multiply/{numberOne}/{numberTwo}")
    public Double multiplyNumbers(@PathVariable(value = "numberOne") String numberOne,
                                  @PathVariable(value = "numberTwo") String numberTwo) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    // Divisão:
    @GetMapping(value = "/divide/{numberOne}/{numberTwo}")
    public Double divideNumbers(@PathVariable(value = "numberOne") String numberOne,
                                @PathVariable(value = "numberTwo") String numberTwo) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        double num1 = convertToDouble(numberOne);
        double num2 = convertToDouble(numberTwo);

        if (num1 == 0 || num2 == 0) {
            throw new UnsupportedMathOperationException("Division by zero is not allowed!");
        }
        return num1 / num2;
    }

    // Média:
    @GetMapping(value = "/average/{numberOne}/{numberTwo}")
    public Double calculateAverage(@PathVariable(value = "numberOne") String numberOne,
                                   @PathVariable(value = "numberTwo") String numberTwo) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        double num1 = convertToDouble(numberOne);
        double num2 = convertToDouble(numberTwo);

        return (num1 + num2) / 2;
    }

    @GetMapping(value = "/sqrt/{number}")
    private Double calculateSquareRoot(@PathVariable(value = "number") String number) {
        if (!isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        double num = convertToDouble(number);

        if (num < 0) {
            throw new UnsupportedMathOperationException("Square root of a negative number is not defined!");
        }

        return Math.sqrt(num);
    }

    private Double convertToDouble(String stringNumber) {
        if (stringNumber == null) {
            return 0D;
        }
        // BR 10,25 - US 10.25
        // Toda vírgula será trocada por um ponto:
        String number = stringNumber.replaceAll(",", ".");
        if (isNumeric(number)) {
            return Double.parseDouble(number);
        }
        return 0D;
    }

    private boolean isNumeric(String stringNumber) {
        if (stringNumber == null) {
            return false;
        }
        String number = stringNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

}
