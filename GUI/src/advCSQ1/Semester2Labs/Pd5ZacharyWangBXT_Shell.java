

/*****************************************************************************************************************
NAME:      
PERIOD:
DUE DATE: 

PURPOSE:    

WHAT I LEARNED:    
          
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
}
