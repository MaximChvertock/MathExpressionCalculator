package by.chvertock.calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

final class InversePolishNotationConverter {

    private final Deque<String> stack = new ArrayDeque<>();
    private final Deque<String> result = new ArrayDeque<>();

    private Map<String, Double> variables = new HashMap<>();

    private boolean isUnaryFlag = true;


    private int position;

    private String output = "OK";

    public InversePolishNotationConverter() {
    }

    public InversePolishNotationConverter(Map<String, Double> variables) {
        this.variables = variables;
    }

    String getOutput() {
        return output;
    }

    int getPosition() {
        return position;
    }

    Deque<String> convert(final Deque<String> sequence) {
        for (String item : sequence) {
            if (Operators.isOperator(item)) {

                String currentOperator = item;
                if (isUnaryFlag) {
                    currentOperator = Operators.getUnaryOperatorNotation(item);
                }

                processOperator(currentOperator);
            } else {

                // TODO: refactor it

                Double value;

                try {
                    value = Double.valueOf(item);
                } catch (NumberFormatException e) {
                    value = variables.get(item);
                }

                if (value == null) {
                    output(errorAtPos() + "Unknown variable '" + item + "'");

                    throw new RuntimeException(getOutput());
                }

//                System.out.println("" + value);

                result.add("" + value);

                isUnaryFlag = false;
            }

            ++position;
        }

        while (!stack.isEmpty()) {
            String fromStack = stack.pollLast();

            if (Operators.OPEN_BRACE_NOTATION.equals(fromStack)) {

                output = errorAtPos() + "Closed brace is missing";
                throw new RuntimeException(output);
            }

            result.add(fromStack);
        }

        return result;
    }

    private void output(String message) {
        output = message;
    }

    private void processOperator(String operator) {

        if (processOpenBrace(operator)) {
            return;
        }

        if (processCloseBrace(operator)) {
            return;
        }

        processByPriority(operator);
    }

    private boolean processOpenBrace(String operator) {
        if (Operators.OPEN_BRACE_NOTATION.equals(operator)) {
            addOperatorToStack(operator);

            return true;
        }

        return false;
    }

    private boolean processCloseBrace(String operator) {
        if (Operators.CLOSE_BRACE_NOTATION.equals(operator)) {
            while (!stack.isEmpty()) {
                String fromStack = stack.pollLast();

                if (Operators.OPEN_BRACE_NOTATION.equals(fromStack)) {
                    return true;
                }

                result.add(fromStack);

                isUnaryFlag = false; // after closed brace
            }

            output = errorAtPos() + "Closed brace without opened brace";
            throw new RuntimeException(output);
        }

        return false;
    }

    private void processByPriority(String operator) {
        while (!stack.isEmpty()) {
            String fromStack = stack.getLast();

            if (Operators.getPriority(fromStack) >= Operators.getPriority(operator)) {
                stack.removeLast();
                result.add(fromStack);
            } else {

                addOperatorToStack(operator);
                return;
            }
        }
        addOperatorToStack(operator);
    }

    private void addOperatorToStack(String operator) {
        stack.add(operator);
        isUnaryFlag = true;
    }

    private String errorAtPos() {
        return "[Error at " + position + "]";
    }
}
