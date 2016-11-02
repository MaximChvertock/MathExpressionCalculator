package by.chvertock.calculator;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public final class MathExpressionCalculator {

    private int position;
    private String output;
    private Double result;
    private boolean hasErrors = true;


    public void calculate(String expression) {
        calculate(expression, new HashMap<String, Double>());
    }

    public void calculate(String expression, Map<String, Double> variables) {

        if (expression == null || expression.isEmpty()) {
            output = "Expression is empty";
            return;
        }

        Deque<String> sequence = SequenceBuilder.build(expression);

        InversePolishNotationConverter converter = new InversePolishNotationConverter(variables);


        Deque<String> inversePolishNotation;
        try {
            inversePolishNotation = converter.convert(sequence);
        } catch (Exception e) {
            position = converter.getPosition();
            output = converter.getOutput();

            return;
        }

        InversePolishNotationCalculator calculator = new InversePolishNotationCalculator();

        try {
            result = calculator.calculate(inversePolishNotation);
        } catch (Exception e) {
            position = calculator.getPosition();
            output = calculator.getOutput();

            return;
        }

        hasErrors = false;
    }

    public int getPosition() {
        return position;
    }

    public String getOutput() {
        return output;
    }

    public Double getResult() {
        return result;
    }

    public boolean hasErrors() {
        return hasErrors;
    }
}
