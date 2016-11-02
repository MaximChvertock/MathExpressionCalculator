import by.chvertock.calculator.MathExpressionCalculator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mad on 8/25/16.
 */
public class TestMain {
    public static void main(String[] args) {

        String expression = "a+b*c";

        Map<String, Double> variables = new HashMap<>();
        variables.put("a", 1.0);
        variables.put("b", 2.0);
        variables.put("c", 3.0);

        MathExpressionCalculator calculator = new MathExpressionCalculator();
        calculator.calculate(expression, variables);

        if (calculator.hasErrors()) {
            System.out.println(calculator.getOutput());
        } else {
            System.out.println(calculator.getResult());
        }
    }
}
