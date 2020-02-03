package my.calculator;

import my.calculator.algorithm.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(Calculator.class);
    private Algorithm algorithm;

    public Calculator(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public double calculate(String expression) {
        LOGGER.debug("Call calculate method");
        return algorithm.startCalculating(expression);
    }
}
