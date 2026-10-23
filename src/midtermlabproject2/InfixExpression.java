
import java.util.Stack;
public class InfixExpression {
    private String expression;
    public InfixExpression() {
        this.expression = null;
    }
    public InfixExpression(String expression) {
        this.expression = expression;
    }

    public boolean setExpression(String expression) { //return false if the expression is invalid (contains letters or a space)
        if(expression.contains(" ") || expression.matches(".*[a-z].*")) {
            return false;
        } else {
            this.expression = expression;
            return true;
        }
    }

    public String getExpression() {
        return expression;
    }

    public String translateExpression() { //returns a string of the infix expression translated to postfix
        Stack<String> operatorStack = new Stack<>();
        String translated = "";
        String[] terms = expression.split("(?=[+*/^()\\-])|(?<=[+*/^()\\-])");

        System.out.printf("%-6s %-10s %-24s %s%n", "Step", "Token", "Operator Stack", "Postfix Expression");
        System.out.println("--------------------------------------------------------------------------");

        int step = 0;
        for(int x = 0; x < terms.length; x++) {
            String token = terms[x];
            if (!isOperator(token) && !token.equals("(") && !token.equals(")")) {
                translated += token + " ";
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while(!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    translated += operatorStack.pop() + " ";
                }
                if (!operatorStack.isEmpty()) operatorStack.pop();
            } else {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")
                        && getPrecedence(operatorStack.peek()) >= getPrecedence(token)) {
                    translated += operatorStack.pop() + " ";
                }
                operatorStack.push(token);
            }
            System.out.printf("%-6d %-10s %-24s %s%n", ++step, token, operatorStack.toString(), translated.trim());
        }

        while (!operatorStack.isEmpty()) {
            translated += operatorStack.pop() + " ";
            System.out.printf("%-6d %-10s %-24s %s%n", ++step, "(pop)", operatorStack.toString(), translated.trim());
        }

        return translated.trim();
    }

    private boolean isOperator(String ch){
        return ch.equals("*") || ch.equals("/") || ch.equals("^") || ch.equals("+") || ch.equals("-");
    }

    private static int getPrecedence(String Operator) {
        switch (Operator) {
            case "+":
                return 1;
            case "-":
                return 1;
            case "*":
                return 2;
            case "/":
                return 2;
            case "^":
                return 3;
        }
        return -1;
    }

}
