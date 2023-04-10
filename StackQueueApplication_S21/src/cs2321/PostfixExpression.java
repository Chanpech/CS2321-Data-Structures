package cs2321;
/**
 * @author Chanpech Hoeng
 */
public class PostfixExpression {
	
	/**
	 * Evaluate a postfix expression. 
	 * Postfix expression notation has operands first, following by the operations.
	 * For example:
	 *    13 5 *           is same as 13 * 5 
	 *    4 20 5 + * 6 -   is same as 4 * (20 + 5) - 6  
	 *    
	 * In this homework, expression in the argument only contains
	 *     integer, +, -, *, / and a space between every number and operation. 
	 * You may assume the result will be integer as well. 
	 * 
	 * @param exp The postfix expression
	 * @return the result of the expression
	 */
	public static int evaluate(String exp) {
		//TO DO: implement this function with the help of Stack
		
		/* IMPOTANT NOTE:  
		 * Since the argument exp is a string, you need to parse the string expression first to get  
		 * operands and operators first. Because we knew there is a space between the operands and operators,
		 * you can use the function string.split(" ") to return an array of tokens in exp. 
		 */
		DLLStack<Integer> s = new DLLStack<Integer>();
		String[] tokens = new String[exp.length()];
		for(int i = 0; i < exp.length(); i++) {
			tokens = exp.split(" ");
		}
		
		for(int r = 0; r < tokens.length; r++) {//Iterates through the length.
			if(tokens[r].equals("+")) {
				int operand2 = s.pop();
				int operand1 = s.pop();
				s.push(operand1 + operand2);
			}else if(tokens[r].equals("-")) {//subtraction
				int operand2 = s.pop();
				int operand1 = s.pop();
				s.push(operand1 - operand2);
			}else if(tokens[r].equals("*")) {//multiplication
				int operand2 = s.pop();
				int operand1 = s.pop();
				s.push(operand1 * operand2);
			}else if(tokens[r].equals("/")) {//division
				int operand2 = s.pop();
				int operand1 = s.pop();
				s.push(operand1 / operand2);
			}else {
				s.push(Integer.parseInt(tokens[r]));//If number convert to tokens
			}
		}
		
		return s.top();
	}			
	
}
