package by.chvertock.calculator;

import java.util.ArrayDeque;
import java.util.Deque;

final class InversePolishNotationCalculator {

    private final Deque<Double> stack = new ArrayDeque<>();

    private int position;

    private String output = "OK";

    String getOutput() {
        return output;
    }

    int getPosition() {
        return position;
    }

    Double calculate(Deque<String> notation) {
        for (String item : notation) {
            if (Operators.isOperator(item)) {

                processOperator(item);

            } else {
                try {
                    Double value = Double.valueOf(item);

                    stack.add(value);
                } catch (NumberFormatException ex) {
                    output = errorAtPos() + "Value '" + item + "' is not a number";
                    throw new RuntimeException(output);
                }
            }

            ++position;
        }

        output = "Result is " + stack.getLast();

        return stack.getLast();
    }

    private void processOperator(String operator) {

        switch (operator) {
            case Operators.ADD_NOTATION:
                calculateAdd();
                break;
            case Operators.SUBTRACT_NOTATION:
                calculateSubtract();
                break;
            case Operators.MULTIPLY_NOTATION:
                calculateMultiply();
                break;
            case Operators.DIVIDE_NOTATION:
                calculateDivide();
                break;

            case Operators.DEGREE_NOTATION:
                calculateDegree();
                break;

//            case Operators.INC_NOTATION: // just for test
//                Double arg = stack.pollLast();
//                stack.add(++arg);
//                break;


            case Operators.SQRT_NOTATION:
                calculateSqrt();
                break;

            case Operators.UNARY_MINUS_NOTATION:
                calculateUnaryMinus();
                break;
            default:
                output = errorAtPos() + "Unknown operator '" + operator + "'";
                throw new RuntimeException(output);
        }
    }

    private void calculateSqrt() {
        try {
            Double argument = stack.pollLast();
            if (argument < 0) {
                throw new ArithmeticException();
            }
            Double result = Math.sqrt(argument);
            stack.add(result);
        } catch (ArithmeticException ex) {
            output = errorAtPos() + "Sqrt from negative number";
            throw new RuntimeException(output);
        } catch (Exception ex) {
            output = errorAtPos() + "Syntax error";
            throw new RuntimeException(output);
        }
    }

    private void calculateUnaryMinus() {
        try {
            Double right = stack.pollLast();
            Double result = -right;
            stack.add(result);
        } catch (Exception ex) {
            output = errorAtPos() + "Syntax error";
            throw new RuntimeException(output);
        }
    }

    private void calculateDegree() {
        try {
            Double exponent = stack.pollLast();
            Double base = stack.pollLast();
            Double result = Math.pow(base, exponent);
            stack.add(result);
        } catch (Exception ex) {
            output = errorAtPos() + "Syntax error";
            throw new RuntimeException(output);
        }
    }

    private void calculateDivide() {
        try {
            Double right = stack.pollLast();
            if (right == 0) {
                throw new ArithmeticException();
            }
            Double left = stack.pollLast();
            Double result = left / right;
            stack.add(result);
        } catch (ArithmeticException ex) {
            output = errorAtPos() + "Divide by zero";
            throw new RuntimeException(output);
        } catch (Exception ex) {
            output = errorAtPos() + "Syntax error";
            throw new RuntimeException(output);
        }
    }

    private void calculateMultiply() {
        try {
            Double right = stack.pollLast();
            Double left = stack.pollLast();
            Double result = left * right;
            stack.add(result);
        } catch (Exception ex) {
            output = errorAtPos() + "Syntax error";
            throw new RuntimeException(output);
        }
    }

    private void calculateSubtract() {
        try {
            Double right = stack.pollLast();
            Double left = stack.pollLast();
            Double result = left - right;
            stack.add(result);
        } catch (Exception ex) {
            output = errorAtPos() + "Syntax error";
            throw new RuntimeException(output);
        }
    }

    private void calculateAdd() {
        try {
            Double right = stack.pollLast();
            Double left = stack.pollLast();
            Double result = left + right;
            stack.add(result);
        } catch (Exception ex) {
            output = errorAtPos() + "Syntax error";
            throw new RuntimeException(output);
        }
    }

    private String errorAtPos() {
        return "[Error at " + position + "]";
    }
}
