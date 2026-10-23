import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int choice = 0;
        String inputedExpression;

        while (choice < 1 || choice > 2) {
            System.out.print("1. Infix Expression\n2.Postfix Expression\nSelect what type of expression to translate: ");
            choice = Integer.parseInt(input.nextLine());
            if (choice < 1 || choice > 2) {
                System.out.println("Input error, please choose a valid input (1-2)");
            }
        }


        switch (choice) {
            case 1:
                System.out.println("Input your expression(NO SPACES): ");
                inputedExpression = input.nextLine();
                InfixExpression infixExp = new InfixExpression(inputedExpression);
                infixExp.translateExpression();
                break;
            case 2:
                System.out.println("Input your expression(SPACE BETWEEN EACH OPERAND/OPERATOR): ");
                inputedExpression = input.nextLine();
                PostfixExpression postfixExp = new PostfixExpression(inputedExpression);
                postfixExp.translateExpression();
                break;
        }
    }
}
