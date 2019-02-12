

/***********************************************************************
Name: Zachary Wang     
Period: 5
Date: 12/15/18
What I Learned: Stacks are a powerful organizational tool, and this lab shows how effective algorithims can be used to turn complex tasks into simple tasks that can be evaluated by stack.        
Credit (person who helped me): none
Student(s) whom I helped (to what extent):none
************************************************************************/
import java.util.*;

public class Pd5ZacharyWangInfixtoPostfix {
	public static void main(String[] args) {
		System.out.println("Enter an infix expression, single digits");
		System.out.println("such as 1+2*3 or (1+2)*3");
		Scanner keyboard = new Scanner(System.in); // (3*(4+5)-2)/5
		String s = keyboard.next();
		while (!s.equals("-1")) {
			String postfixExp = trans(s);
			System.out.println(s + " --> " + postfixExp + " --> ");
			System.out.println(s + " --> " + postfixExp + " --> " +
			eval(postfixExp) + "\n");
			s = keyboard.next();
		}
	} // end of main

	//pre: x contains a correct postfix expression of only digits and the four operators + - * /
	//post: the correct evaluation is returned
	public static int eval(String x) {
		Stack<Integer> wack = new Stack<Integer>();
		for (int i = 0; i < x.length(); i++) {
			char c = x.charAt(i);
			if (Character.isDigit(c))//see if it is a digit
				wack.push(c - '0');//convert to int
			else {
				int val1 = wack.pop(),val2 = wack.pop();
				switch (c) {//a neat java feature i learned while doing robotics code, that code involved choosing between multiple paths with were defined with 'l', 'm', and 'r' for left right middle,
				case '+':
					wack.push(val2 + val1);
					break;
				case '-':
					wack.push(val2 - val1);
					break;
				case '/':
					wack.push(val2 / val1);
					break;
				case '*':
					wack.push(val2 * val1);
					break;
				}
			}
		}
		return wack.pop();
	}

	//pre: x is a correct infix expression with only single digit numbers and the four operators + - * /
	//post: the correct postfix expression is returned
	public static String trans(String x) {
		Stack<Character> wack = new Stack<Character>();
		String s = "";
		for (int i = 0; i < x.length(); i++) {
			char c = x.charAt(i);
			if (c == '(')//check left parenthesis, push if so
				wack.push(c);
			else if (c == ')') {//check if right parenthesis, pop until left parenthesis is reached
				while (wack.peek() != '(')
					s += wack.pop();
				wack.pop();
			} else if (c == '+' || c == '-' || c == '*' || c == '/') {//if operator
				while (!wack.isEmpty() && wack.peek() != '(' && !precedence(c, wack.peek()))//follow rules for operator
					s += wack.pop();
				wack.push(c);
			} else
				s += c;
		}
		while (!wack.isEmpty())//clear stack
			s += wack.pop();
		return s;
	} // end of trans

	public static boolean precedence(char c1, char c2) {//determine precedence for operators
		if (c1 == '*' || c1 == '/' || c1 == '%')
			return c2 == '+' || c2 == '-';
		return false;
	}
} // end of Infix_Shell