
//Name:      Date:
import java.util.*;

/***********************************
 * Represents a binary expression tree. The BXT can build itself from a
 * postorder expression. It can evaluate and print itself. It also prints an
 * inorder string and a preorder string.
 ************************************/
public class Pd5ZacharyWangBXT {
	private int count;
	private TreeNode root;

	public Pd5ZacharyWangBXT() {
		count = 0;
		root = null;
	}

	public Pd5ZacharyWangBXT(Object obj) {
		count = 1;
		root = new TreeNode(obj, null, null);
	}

	/***********************
	 * Builds a BXT from a postfix expression. Uses a helper stack of TreeNodes.
	 ****************************/
	public void buildTree(String str) {
		Stack<TreeNode> s = new Stack<TreeNode>();
		for(int i = 0; i < str.length(); i++) 
			s.push(new TreeNode(str.charAt(i)));
		
			
				
				
	}

	public double evaluateTree() {
		return evaluateNode(root);
	}

	private double evaluateNode(TreeNode root) // recursive
	{
		if("+-*/".indexOf(str.charAt(i)) != 1) 
			s.push(new TreeNode(computeTerm(str.substring(i, i + 1), (double)s.pop().getValue(), (double)s.pop().getValue())));
		else
			s.push(new TreeNode(str.charAt(i)));
		// enter code here
		return 0;
	}

	private double computeTerm(String s, double a, double b) {
		// enter code here
		return 0;
	}

	private boolean isOperator(String s) {
		// enter code here
		return false;
	}
	// display() from TreeLab

	// inorder traverse

	// preorder traverse
}
