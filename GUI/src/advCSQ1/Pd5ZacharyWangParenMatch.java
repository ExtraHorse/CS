

/***********************************************************************
Name:Zachary Wang
Period:5
Date:12/13/18
What I Learned:Stacks can also help to keep track of expressions that radiate outward, such as grouping symbols in this case.       
Credit (person who helped me):Arman for reminding me about checking for a mismatch in the number of left and right symbols
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

	// precondition: s is not null
	// postcondition: returns true if grouping symbols match, false if they do not
	public static boolean check(String s) {
		Stack<Integer> wack = new Stack<Integer>();
		for(int i = 0; i < s.length(); i++) {
			String c = s.substring(i, i + 1);//go through every character of the string
			int leftIndex = left.indexOf(c), rightIndex = right.indexOf(c);//find the corresponding index in left and right for the grouping symbol
			if(leftIndex != -1)//if left grouper, push
				wack.push(leftIndex);
			else if(rightIndex != -1) {//if right grouper, check the stack. if the stack is empty, then there must a right group symbol without a left
				if(wack.isEmpty())//indicating false
					return false;
				else if(wack.pop() != rightIndex)//check if they are corresponding symbols. if they are not, return false
					return false;
			}
		}
		return(wack.isEmpty());//if all symbols match and the stack is empty (no left over left symbols), return true
	}
}/*Test cases:
Enter an expression with grouping symbols,
such as (2+3)-[5*(6+1)]IndexMals
(2+3)-[5*(6+1)]
(2+3)-[5*(6+1)] is good.

(2+3)-[5*(6+1)]]
No, no, no.  Bad.  (2+3)-[5*(6+1)]]

(2+3)-[5*(6+1)
No, no, no.  Bad.  (2+3)-[5*(6+1)

(2+3)-[5*(6+1))
No, no, no.  Bad.  (2+3)-[5*(6+1))

(2+3)-[5*(6+1)]
(2+3)-[5*(6+1)] is good.

2
2 is good.

(
No, no, no.  Bad.  (

)
No, no, no.  Bad.  )

(2+3)-[5*(6+1)}
No, no, no.  Bad.  (2+3)-[5*(6+1)}

<2+3>-{5*(6+1)}
<2+3>-{5*(6+1)} is good.*/
