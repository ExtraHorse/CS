package advCSQ1.Semester2Labs;


//Name: Zachary Wang     Date: 2/12
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
	 * pre: str is a correct postfix expression
	 * post: root is a correct built BXT
	 ****************************/
	public void buildTree(String str) {
		Stack<TreeNode> s = new Stack<TreeNode>();
		StringTokenizer t = new StringTokenizer(str);
		while(t.hasMoreTokens()) {
			String next = t.nextToken();
			if(isOperator(next))
				s.push(new TreeNode(next, s.pop(), s.pop()));//pop two and add as leaf nodes
			else
				s.push(new TreeNode(next));//add a leaf node with operand
		}
		root = s.pop();
	}

	public double evaluateTree() {
		return evaluateNode(root);
	}

	//pre: root is a BXT tree, or operand leaf node
	//post: result of evaluating the node is returned
	private double evaluateNode(TreeNode root) // recursive
	{
		if(isOperator(root.getValue().toString()))//evaluate left and right if operator
			return computeTerm(root.getValue().toString(), evaluateNode(root.getRight()), evaluateNode(root.getLeft()));
		else
			return Double.parseDouble(root.getValue().toString());//return operand balue
	}

	//pre: s is an operator +, -, *, or /
	//post: result of a and b computed with operator s is returned
	private double computeTerm(String s, double a, double b) {
		if(s.equals("+"))
			return a + b;
		else if (s.equals("-"))
			return a - b;
		else if (s.equals("/"))
			return a / b;
		else 
			return a * b;
	}

	private boolean isOperator(String s) {
		return "+-*/".indexOf(s) != -1;
	}
	
	public void display() {
		displayHelper(root, 0);
	}
	
	private void displayHelper(TreeNode t, int level) {
		if (t == null)
			return;
		displayHelper(t.getRight(), level + 1); // recurse right
		for (int k = 0; k < level; k++)
			System.out.print("\t");
		System.out.println(t.getValue());
		displayHelper(t.getLeft(), level + 1); // recurse left
	} // end of display

	public void inorderTraverse() {
		inOrderHelper(root);
	}
	
	private void inOrderHelper(TreeNode t) {
		if (t != null) {
			inOrderHelper(t.getLeft());
			System.out.print(t.getValue());
			inOrderHelper(t.getRight());
		}
	}

	public void preorderTraverse() {
		preOrderHelper(root);
	}

	private void preOrderHelper(TreeNode t) {
		if (t != null) {
			System.out.print(t.getValue());
			preOrderHelper(t.getLeft());
			preOrderHelper(t.getRight());
		}
	}
}
