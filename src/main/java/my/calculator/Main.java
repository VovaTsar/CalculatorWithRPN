package my.calculator;

import my.calculator.algorithm.impl.ReversePolishNotationAlgorithm;

public class Main {
    public static void main(String[] args) {
        ReversePolishNotationAlgorithm algorithm = new ReversePolishNotationAlgorithm();
        Calculator calculator = new Calculator(algorithm);
        String expression = "-((5+5)*2)";
        System.out.println(calculator.calculate(expression));
    }
}
