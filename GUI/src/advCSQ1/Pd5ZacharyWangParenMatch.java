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

public class Pd5ZacharyWangParenMatch {
	public static final String left = "([{<";
	public static final String right = ")]}>";

	public static void main(String[] args) {
		System.out.println("Enter an expression with grouping symbols,");
		System.out.println("such as (2+3)-[5*(6+1)]IndexMals");
		Scanner keyboard = new Scanner(System.in);
		String s = keyboard.nextLine();
		while (!s.equals("-1")) {
			boolean flag = check(s);
			if (flag)
				System.out.println(s + " is good.");
			else
				System.out.println("No, no, no.  Bad.  " + s);
			System.out.println();
			s = keyboard.next();
		}
	}

	// precondition:
	// postcondition:
	public static boolean check(String s) {
		Stack<String> wack = new Stack<String>();
		for(int i = 0; i < s.length(); i++) {
			String c = s.substring(i, i + 1);
			int leftIndex = left.indexOf(c), rightIndex = right.indexOf(c);
			System.out.println(c);
			if(leftIndex != -1)
				wack.push(c);
			else if(rightIndex != -1) 
				if(!wack.isEmpty())
					if(!wack.pop().equals(right.substring(rightIndex, rightIndex + 1)))
						return false;
		}
		return true;
	}
}
