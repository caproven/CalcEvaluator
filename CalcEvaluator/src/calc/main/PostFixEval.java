package calc.main;

import java.util.Scanner;
import java.util.Stack;

/**
 * Evaluator for post-fix expressions.
 * @author caproven
 */
public class PostFixEval {
    
    /**
     * Starts the program, prompting for a post-fix expression.
     * @param args Command line args, unused
     */
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        
        System.out.println("Enter a space-delimited post-fix notation expression to evaluate: ");
        System.out.println(eval(console.nextLine()));
        
        console.close();
    }
    
    /**
     * Evaluates the given post-fix expression, given as a string.
     * @param postfix String containing the post-fix expression to evaluate
     * @return Numeric evaluation of the given input, or 0 if nothing entered
     */
    public static double eval(String postfix) {
        double val = 0;
        
        Stack<Double> s = new Stack<>();
        Scanner lineScan = new Scanner(postfix);
        
        while (lineScan.hasNext()) {
            
            if (lineScan.hasNextDouble()) { // has numeric value
                s.push(lineScan.nextDouble());
            } else { // has operator
                double b = s.pop();
                double a = s.pop();
                
                String op = lineScan.next();
                switch (op) {
                case "+": s.push(a + b); break;
                case "-": s.push(a - b); break;
                case "*": s.push(a * b); break;
                case "/": s.push(a / b); break;
                case "^": s.push(Math.pow(a, b)); break;
                default:
                    System.out.println("Unsupported operator: " + op);
                    System.exit(0);
                }
            }
            
        }
        
        lineScan.close();
        
        if (s.size() == 1) {
            val = s.pop();
        }
        
        return val;
    }
    
}
