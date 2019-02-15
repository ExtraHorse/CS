package advCSQ1.Semester2Labs;

/*****************************************************************************************************************
NAME:  Zachary Wang    
PERIOD: 5
DUE DATE: 2/12/2019

PURPOSE: Implement basic methods for an expression tree.

WHAT I LEARNED: Using recursion and similar methods for evaluating a prefix or postfix script (i.e. using a stack and popping and evaluating when an op is encountered),
a BXT functions remarkably similar to an expression in regular form.
          
CREDITS (BE SPECIFIC: FRIENDS, PEERS, ONLINE WEBSITES, ETC.): 

****************************************************************************************************************/
import java.util.*;

/***********************************
Represents a binary expression tree.
The BXT can build itself from a postorder expression.  It can
evaluate and print itself. It also prints an inorder string and a preorder string.  
************************************/

/*******************
 * Driver for a binary expression tree class. Input: a postfix string, each
 * token separated by a space.
 **********************/
public class Pd5ZacharyWangBXT_Shell {
	public static void main(String[] args) {
		Pd5ZacharyWangBXT tree = new Pd5ZacharyWangBXT();
		Scanner sc = new Scanner(System.in);
		System.out.print("Input postfix string: ");
		String s = sc.nextLine(); // 14 -5 / , 3 4 5 + * , 2 3 + 5 / 4 5 - *
		tree.buildTree(s);
		tree.display();
		System.out.print("Infix order:  ");
		tree.inorderTraverse();
		System.out.print("\nPrefix order:  ");
		tree.preorderTraverse();
		System.out.print("\nEvaluates to " + tree.evaluateTree());
	}
}/*Example output:

Input postfix string:  2 3 + 5 / 4 5 - *
			2
		+
			3
	/
		5
*
		4
	-
		5
Infix order:  5-4*5/3+2
Prefix order:  *-54/5+32
Evaluates to 1.0

Input postfix string: 1 4 - 5 /
		1
	-
		4
/
	5
Infix order:  5/4-1
Prefix order:  /5-41
Evaluates to 1.6666666666666667

Input postfix string: 3 4 5 + *
	3
*
		4
	+
		5
Infix order:  5+4*3
Prefix order:  *+543
Evaluates to 27.0

*/

