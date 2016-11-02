package by.chvertock.calculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public final class SequenceBuilder {
    private SequenceBuilder() {
        /*NOP*/
    }

    public static Deque<String> build(String expression) {
        Deque<String> result = new ArrayDeque<>();

        StringTokenizer tokenizer = new StringTokenizer(expression, Operators.getDelimiters(), true);

        while (tokenizer.hasMoreTokens()) {
            result.add(tokenizer.nextToken());
        }

        return result;
    }
}
