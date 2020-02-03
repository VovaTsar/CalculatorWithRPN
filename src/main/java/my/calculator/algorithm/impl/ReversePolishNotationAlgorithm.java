package my.calculator.algorithm.impl;

import my.calculator.algorithm.Algorithm;
import my.calculator.algorithm.PriorityOfOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class ReversePolishNotationAlgorithm implements Algorithm {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReversePolishNotationAlgorithm.class);
    private PriorityOfOperator priorityOfOperator = new PriorityOfOperator();

    public double startCalculating(String expression) {
        LOGGER.debug("Call startCalculating method ");
        String prepared = preparingNegativeExpression(expression);
        String rpn = expressionToAlgorithm(prepared);
        return algorithmToAnswer(rpn);
    }

    private String expressionToAlgorithm(String expression) {
        LOGGER.debug("Call expressionToAlgorithm method ");
        StringBuilder current = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        int priority;
        for (int i = 0; i < expression.length(); i++) {
            priority = priorityOfOperator.getPriority(expression.charAt(i));
            if (priority == 0) {
                current.append(expression.charAt(i));
                LOGGER.debug("Priority = 0 add number to stringBuilder ");
            }
            if (priority == 1) {
                stack.push(expression.charAt(i));
                LOGGER.debug("Priority = 1 add closing bracket to stack ");
            }
            if (priority > 1) {
                current.append(' ');

                while (!stack.empty()) {
                    if (priorityOfOperator.getPriority(stack.peek()) >= priority) {
                        current.append(stack.pop());
                    } else break;
                }
                stack.push(expression.charAt(i));
            }
            if (priority == -1) {
                while (priorityOfOperator.getPriority(stack.peek()) != 1) {
                    current.append(stack.pop());
                }
                stack.pop();
            }

        }
        while (!stack.empty()) {
            current.append(stack.pop());
        }
        return current.toString();
    }

    private String preparingNegativeExpression(String expression) {
        LOGGER.debug("Call preparingNegativeExpression method ");
        StringBuilder preparedExpression = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char symbol = expression.charAt(i);
            if (symbol == '-') {
                if (i == 0) {
                    preparedExpression.append('0');
                } else if (expression.charAt(i - 1) == '(')
                    preparedExpression.append('0');
            }
            preparedExpression.append(symbol);
        }
        return preparedExpression.toString();
    }

    private double algorithmToAnswer(String expression) {
        LOGGER.debug("Call algorithmToAnswer method ");
        StringBuilder operand = new StringBuilder();
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == ' ')
                continue;
            if (priorityOfOperator.getPriority(expression.charAt(i)) == 0) {
                while (expression.charAt(i) != ' ' && priorityOfOperator.getPriority(expression.charAt(i)) == 0) {
                    LOGGER.debug("Add number to stringBuilder ");
                    operand.append(expression.charAt(i++));
                    if (i == expression.length()) break;
                }
                stack.push(Double.parseDouble(operand.toString()));
                operand = new StringBuilder();
            }
            if (priorityOfOperator.getPriority(expression.charAt(i)) > 1) {
                calculation(expression, stack, i);
            }
        }
        return stack.pop();
    }

    private void calculation(String expression, Stack<Double> stack, int element) {
        LOGGER.debug("Call calculation method ");
        double a = stack.pop();
        double b = stack.pop();
        if (expression.charAt(element) == '+') {
            LOGGER.debug("Add b + a");
            stack.push(b + a);
        }
        if (expression.charAt(element) == '-') {
            LOGGER.debug("Subtract b - a");
            stack.push(b - a);
        }
        if (expression.charAt(element) == '*') {
            LOGGER.debug("Multiply b * a");
            stack.push(b * a);
        }
        if (expression.charAt(element) == '/') {
            LOGGER.debug("Divide b / a");
            stack.push(b / a);
        }
    }


}
