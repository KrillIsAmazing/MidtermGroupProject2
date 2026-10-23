import java.util.Stack;
public class PostfixExpression {
    private String expression;
    public PostfixExpression() {
        this.expression = null;
    }
    public PostfixExpression(String expression) { //return false if the expression is invalid (contains letters)
        this.expression = expression;
    }

    public boolean setExpression(String expression) { //return false if the expression is invalid (contains letters)
        if(expression.matches(".*[a-z].*")) {
            return false;
        } else {
            this.expression = expression;
            return true;
        }
    }

    public String getExpression() {
        return expression;
    }

    public String translateExpression() {
        String[] terms = expression.split(" ");
        Stack<String> operandStack = new Stack<>();

        System.out.printf("%-6s %-10s %s%n", "Step", "Token", "Expression Stack");
        System.out.println("------------------------------------------------");

        for(int x = 0; x < terms.length; x++) {
            if(!isOperator(terms[x])) {
                operandStack.push(terms[x]);
            } else {
                String operand2 = operandStack.pop();
                String operand1 = operandStack.pop();
                String tempTerm = operand1 + " " + terms[x] + " " + operand2;
                operandStack.push(tempTerm);
            }
            System.out.printf("%-6d %-10s %s%n", x + 1, terms[x], operandStack.toString());
        }

        return operandStack.pop();
    }

    private boolean isOperator(String ch){
        return ch.equals("*") || ch.equals("/") || ch.equals("^") || ch.equals("+") || ch.equals("-");
    }
}
