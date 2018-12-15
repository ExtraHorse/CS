package advCSQ1;

/***********************************************************************
Name:      
Period:
Date:    
What I Learned:            
Credit (person who helped me): 
Student(s) whom I helped (to what extent):
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
			// System.out.println(s + " --> " + postfixExp + " --> " +
			// Postfix.eval(postfixExp) + "\n");
			s = keyboard.next();
		}
	} // end of main
	
	public static int eval(String x){
		return 1;
	}

	public static String trans(String x) {
		Stack<Character> wack = new Stack<Character>();
		String s = "";
		for(int i = 0; i < x.length(); i++) {
			char c = x.charAt(i);
			if(c == '(')
				wack.push(c);
			else if(c == ')') {
				while(wack.peek() != '(')
					s += wack.pop();
				wack.pop();
			}
			else if(c == '+' || c == '-' || c == '*' || c == '/') {
				while(!wack.isEmpty() && wack.peek() != '(' && !precedence(c, wack.peek()))
					s += wack.pop();
				wack.push(c);
			}
			else
				s += c;
		}
		while(!wack.isEmpty())
			s += wack.pop();
		return s;
	} // end of trans
	
	public static boolean precedence(char c1, char c2) {
		if(c1 == '*' || c1 == '/' || c1 == '%')
			return c2 == '+' || c2 == '-';
		return false;
	}
} // end of Infix_Shell