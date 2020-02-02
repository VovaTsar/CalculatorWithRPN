package my.calculator.algorithm;

public class PriorityOfOperator {

    private static final int HIGHER_PRIORITY = 3;
    private static final int LESS_PRIORITY = 2;
    private static final int OPENING_LABEL = 1;
    private static final int CLOSING_LABEL = -1;
    private static final int NUMBER = 0;

    public int getPriority(char operator) {
        switch (operator) {
            case '*':
                return HIGHER_PRIORITY;
            case '/':
                return HIGHER_PRIORITY;
            case '+':
                return LESS_PRIORITY;
            case '-':
                return LESS_PRIORITY;
            case '(':
                return OPENING_LABEL;
            case ')':
                return CLOSING_LABEL;
            default:
                return NUMBER;
        }
    }
}
