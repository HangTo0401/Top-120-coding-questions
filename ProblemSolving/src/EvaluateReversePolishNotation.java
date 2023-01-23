import java.util.Stack;

/**
 * 2. Evaluate Reverse Polish Notation
 * Level: Medium
 * You are given an array of string tokens that represents an arithmetic expression in a Reverse Polish Notation.
 * Evaluate the expression. Return an integer that represents the value of the expression.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 * For example:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 *
 * Note that:
 * The valid operators are '+', '-', '*', and '/'.
 * Each operand may be an integer or another expression.
 * The division between two integers always truncates toward zero.
 * There will not be any division by zero.
 * The input represents a valid arithmetic expression in a reverse polish notation.
 * The answer and all the intermediate calculations can be represented in a 32-bit integer.
 * */

public class EvaluateReversePolishNotation {

    /**
     * Solution 1 - Naive Approach
     * This problem can be solved by using a stack. We can loop through each element in the
     * given array. When it is a number, push it to the stack. When it is an operator, pop two
     * numbers from the stack, do the calculation, and push back the result.
     * The following is the code. However, this code contains compilation errors in leetcode. Why?
     * */
    public static int evalReversePolishNotationUsingNaiveApproach(String[] arr) {
        int returnValue = 0;
        String operators = "+-*/";
        Stack<String> stack = new Stack<>();

        for (String s : arr) {
            if (!operators.contains(s)) { // push to stack if it is a number
                stack.push(s);
            } else { // pop numbers from stack if it is an operator
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                switch (s) {
                    case "+":
                        stack.push(String.valueOf(a + b));
                        break;
                    case "-":
                        stack.push(String.valueOf(a - b));
                        break;
                    case "*":
                        stack.push(String.valueOf(a * b));
                        break;
                    case "/":
                        stack.push(String.valueOf(a / b));
                        break;
                }
            }
        }

        returnValue = Integer.valueOf(stack.pop());

        return returnValue;
    }


    /**
     * Solution 1 - Naive Approach
     * If you want to use switch statement, you can convert the above by using the following
     * code which use the index of a string +-/*
     * */
    public static int evalReversePolishNotationUsingIndexOf(String[] arr) {
        int returnValue = 0;
        String operators = "+-*/";
        Stack<String> stack = new Stack<>();

        for (String s : arr) {
            if (!operators.contains(s)) { // push to stack if it is a number
                stack.push(s);
            } else { // pop numbers from stack if it is an operator
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                int index = operators.indexOf(s);
                switch (index) {
                    case 0:
                        stack.push(String.valueOf(a + b));
                        break;
                    case 1:
                        stack.push(String.valueOf(a - b));
                        break;
                    case 2:
                        stack.push(String.valueOf(a * b));
                        break;
                    case 3:
                        stack.push(String.valueOf(a / b));
                        break;
                }
            }
        }

        returnValue = Integer.valueOf(stack.pop());

        return returnValue;
    }

    public static void main(String[] args) {
        String[] tokens = new String[] { "2", "1", "+", "3", "*" };

        // C1
        System.out.println(evalReversePolishNotationUsingNaiveApproach(tokens));

        // C2
        System.out.println(evalReversePolishNotationUsingIndexOf(tokens));
    }
}
