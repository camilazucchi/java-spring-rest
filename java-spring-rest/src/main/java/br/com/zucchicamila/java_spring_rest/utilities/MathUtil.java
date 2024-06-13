package br.com.zucchicamila.java_spring_rest.utilities;

import br.com.zucchicamila.java_spring_rest.exceptions.UnsupportedMathOperationException;

public class MathUtil {

    private static final String NUMERIC_REGEX = "[-+]?\\d*\\.?\\d+";

    private MathUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static boolean isNumeric(Double number) {

        if (number == null) {
            return false;
        }

        return String.valueOf(number).matches(NUMERIC_REGEX);
    }

    public static void validateNumeric(Double... numbers) {
        for (Double number : numbers) {
            if (!isNumeric(number)) {
                throw new UnsupportedMathOperationException("Please set a numeric value.");
            }
        }
    }

    public static void validateDivisionByZero(Double number) {
        if (number == 0) {
            throw new UnsupportedMathOperationException("Division by zero is not allowed!");
        }
    }

    public static void validateNonNegative(Double number) {
        if (number < 0) {
            throw new UnsupportedMathOperationException("Square root of a negative number is not defined!");
        }
    }

}
