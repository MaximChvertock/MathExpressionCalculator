package by.chvertock.calculator;

// commit with .gitignore111111111   last

final class Operators {
    private Operators() {
        /*NOP*/
    }

     static final String OPEN_BRACE_NOTATION = "(";
     static final String CLOSE_BRACE_NOTATION = ")";

     static final String ADD_NOTATION = "+";
     static final String SUBTRACT_NOTATION = "-";

     static final String MULTIPLY_NOTATION = "*";
     static final String DIVIDE_NOTATION = "/";

     static final String DEGREE_NOTATION = "^";


//     static final String INC_NOTATION = "inc"; // just for test

     static final String SQRT_NOTATION = "sqrt";

     static final String UNARY_MINUS_NOTATION = "u-";
//     static final String UNARY_PLUS_NOTATION = "u+";


    private static final String operators[] = { // elements ordered by operator priority
            OPEN_BRACE_NOTATION,
            CLOSE_BRACE_NOTATION,

            ADD_NOTATION,
            SUBTRACT_NOTATION,

            MULTIPLY_NOTATION,
            DIVIDE_NOTATION,

            DEGREE_NOTATION,

            SQRT_NOTATION,

//            INC_NOTATION, // just for test

//            UNARY_PLUS_NOTATION,
            UNARY_MINUS_NOTATION,
    };

    private static final String delimiters =
            ADD_NOTATION +
                    SUBTRACT_NOTATION +
                    MULTIPLY_NOTATION +
                    DIVIDE_NOTATION +
                    OPEN_BRACE_NOTATION +
                    CLOSE_BRACE_NOTATION +
                    DEGREE_NOTATION;


     static boolean isOperator(String check) {
        for (String operator : operators) {
            if (operator.equals(check)) {
                return true;
            }
        }
        return false;
    }

     static int getPriority(String check) {
        int priority = 0;
        for (String operator : operators) {
            if (operator.equals(check)) {
                return priority;
            }
            ++priority;
        }

        throw new RuntimeException("Unknown operator '" + check + "'");
    }

     static String getUnaryOperatorNotation(String operator) {
        String result = operator;
        switch (operator) {
//            case ADD_NOTATION:
//                result = UNARY_PLUS_NOTATION;
//                break;
            case SUBTRACT_NOTATION:
                result = UNARY_MINUS_NOTATION;
                break;

        }

        return result;
    }

     static String getDelimiters() {
        return delimiters;
    }
}
