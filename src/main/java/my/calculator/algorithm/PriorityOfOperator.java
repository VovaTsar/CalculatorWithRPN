package my.calculator.algorithm;

public class PriorityOfOperator {

    public int getPriority(char operator) {
        switch (operator) {
            case '*':
                return 3;
            case '/':
                return 3;
            case '+':
                return 2;
            case '-':
                return 2;
            case '(':
                return 1;
            case ')':
                return -1;
            default:
                return 0;
        }
    }
}
