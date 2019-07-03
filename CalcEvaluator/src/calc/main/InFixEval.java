package calc.main;

import java.util.Scanner;

/**
 * Evaluator for in-fix expressions.
 * @author caproven
 */
public class InFixEval {
    
    /**
     * Starts the program, prompting for an in-fix expression.
     * @param args Command line args, unused
     */
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        
        System.out.println("Enter a space-delimited in-fix notation expression to evaluate: ");
        System.out.println(eval(console.nextLine()));
        
        console.close();
    }
    
    /**
     * Evaluates the given in-fix expression, given as a string.
     * @param infix String containing the in-fix expression to evaluate
     * @return Numeric evaluation of the given input, or 0 if nothing entered
     */
    public static double eval(String infix) {
        Scanner lineScan = new Scanner(infix);
 
        double val = evalHelper(lineScan);
        
        lineScan.close();
        
        return val;
    }
    
    /**
     * Recursive helper method for eval() to allow for parentheses processing. Evaluates up until
     * either the end of the input or the next close parenthesis ')'.
     * @param lineScan Common scanner used to parse the input string
     * @return Numeric evaluation of the given input, or 0 if nothing entered
     */
    private static double evalHelper(Scanner lineScan) {
        StringBuilder postfix = new StringBuilder();
        OperatorList opList = new OperatorList();
        
        // fill stack with the post-fix notation
        while (lineScan.hasNext()) {
            
            if (lineScan.hasNextDouble()) { // has numeric value
                postfix.append(lineScan.next() + " "); // trailing whitespace at end shouldn't mess up PostFixEval
            } else { // has operator
                
                String op = lineScan.next();
                Integer opWeight = opList.getOpWeight(op);
                if (opWeight == null) {
                    System.out.println("Unsupported operator: " + op);
                    System.exit(0);
                }
                Integer highestOpWeight = opList.getHighestOpWeight();
                
                // handle parentheses (recursion)
                if (opWeight == OperatorList.OPEN_PAREN) {
                    // evaluate contents of parentheses then add to post-fix as if reading a double
                    postfix.append(String.valueOf(evalHelper(lineScan)) + " ");
                } else if (opWeight == OperatorList.CLOSE_PAREN) {
                    // return current evaluation (exit parse loop & eval current post-fix)
                    break;
                }
                
                // handle operator rules for in-fix -> post-fix
                else if (highestOpWeight != null) {
                    // Rule 1 - op higher than list max, add to list
                    if (opWeight > highestOpWeight) {
                        opList.addOp(op);
                    }
                    // Rule 2 - op lower than list max,
                    //          IF list contains ops lower than new op, remove only higher ops from list
                    //          ELSE purge entire list
                    //          ..then add new op to list
                    else if (opWeight < highestOpWeight) {
                        if (opList.size() > 0 && opList.getOpWeight(opList.get(0)) < opWeight) {
                            while (opList.getHighestOpWeight() > opWeight) { // remove any ops greater than new op
                                postfix.append(opList.remove(opList.size() - 1) + " ");
                            }
                        } else {
                            while (opList.size() > 0) { // purge any ops from opList
                                postfix.append(opList.remove(opList.size() - 1) + " ");
                            }
                        }
                        opList.addOp(op);
                    }
                    // Rule 4 - op = rank of list max, remove last list op then add new to list
                    else {
                        postfix.append(opList.remove(opList.size() - 1) + " ");
                        opList.addOp(op);
                    }
                } else {
                    // Rule 5 - empty list, append to list
                    opList.addOp(op);
                }
                
            }
            
        }
        
        // Rule 3 - done parsing, purge list
        while (opList.size() > 0) {
            postfix.append(opList.remove(opList.size() - 1) + " ");
        }
        
        System.out.println("Post-fix: " + postfix.toString());
        return PostFixEval.eval(postfix.toString());
    }

}
