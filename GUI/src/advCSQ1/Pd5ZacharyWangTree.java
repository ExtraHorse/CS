

/**
* Name: Zachary Wang	
* Period: 5
* 
* Purpose of the Program: Basic methods involving binary trees
* Date Submitted: 1/21/19
* 
* What I learned: Binary Tree methods are similar in structure to those of linked lists, which can be expected since a tree has many similarities with linked lists. The difficulty arises when methods are written
* that requires comparing and processing both sides of a tree, such as the max or min methods.
* 
* How I feel about this lab: The max and min methods were a lot harder than I expected them to be, largely due to the added level of complexity by making the nodes have strings, which are harder to work with
* than integers or other primitive types when comparing.
* 	
* What I wonder: How do these methods change when we are working with a k-nary tree?
* Credits:
* Students whom I helped (to what extent): 
*/

import java.util.*; //for the queue interface

public class Pd5ZacharyWangTree {
	public static void main(String[] args) {
		String s = "XCOMPUTERSCIENCE";

		TreeNode root = new TreeNode("" + s.charAt(1), null, null);

		// The root node has index 1
		// The second level nodes' indices: 2, 3
		// The third level nodes' indices: 4,5,6,7
		// Idea: based on the index of the node, log (pos) of base 2 calculates the
		// level of the
		// node: root (index 1) on level 1. Node with index 2 or 3 is on level 2
		for (int pos = 2; pos < s.length(); pos++)
			insert(root, "" + s.charAt(pos), pos, (int) (1 + Math.log(pos) / Math.log(2)));

		// NOTE: The following 3 lines are supposed to further show you how insert
		// works. You
		// uncomment them and see how the tree looks like with these 3 additional nodes
		insert(root, "A", 17, 5);
		insert(root, "B", 18, 5);
		insert(root, "C", 37, 6); // B's right child

		// display the tree sideway; see the sample output at the end of this file
		display(root, 0);

		System.out.print("\nPreorder: ");
		preorderTraverse(root);
		System.out.print("\nInorder: ");
		inorderTraverse(root);
		System.out.print("\nPostorder: ");
		postorderTraverse(root);

		System.out.println("\n\nNodes = " + countNodes(root));
		System.out.println("Leaves = " + countLeaves(root));
		System.out.println("Grandparents = " + countGrandParentNodes(root)); // count the number grandparent nodes
		System.out.println("Only childs = " + countSingleChildNodes(root)); // count the number of nodes that has only 1
																			// child

		// System.out.println("\nDepth = " + depth(root));
		System.out.println("Height = " + height(root));

		System.out.println("Min = " + min(root));
		System.out.println("Max = " + max(root));

		System.out.println("\nBy Level: ");
		displayLevelOrder(root); // level by level display of the nodes (starts from left to right for nodes on
									// the same level)

	} // end of main

	// insert a new node in the tree based on the node's level
	public static void insert(TreeNode t, String s, int pos, int level) {
		TreeNode p = t;
		for (int k = level - 2; k > 0; k--)
			// then 1 << 4 will insert four 0-bits at the right of 1 (binary representation
			// of 1 is 1.
			// 1 << 4 results in 10000 (in binary)
			if ((pos & (1 << k)) == 0)
				p = p.getLeft(); // the '&' operator is the same as && except it will evaluate the right expression even if the first is false.
			else // We did not learn this in AP CS A! :
				p = p.getRight(); // What does this do? Answer this question first.

		if ((pos & 1) == 0)
			p.setLeft(new TreeNode(s, null, null));
		else
			p.setRight(new TreeNode(s, null, null));
	} // end of insert

	/*****************************************************************************************************
	 * postcondition: display the tree sideway
	 *****************************************************************************************************/
	public static void display(TreeNode t, int level) {
		if (t == null)
			return;
		display(t.getRight(), level + 1); // recurse right

		for (int k = 0; k < level; k++)
			System.out.print("\t");
		System.out.println(t.getValue());

		display(t.getLeft(), level + 1); // recurse left
	} // end of display

	public static void preorderTraverse(TreeNode t) {
		if (t != null) {
			System.out.print(t.getValue());
			preorderTraverse(t.getLeft());
			preorderTraverse(t.getRight());
		}
	}

	public static void inorderTraverse(TreeNode t) {
		if (t != null) {
			inorderTraverse(t.getLeft());
			System.out.print(t.getValue());
			inorderTraverse(t.getRight());
		}
	}

	public static void postorderTraverse(TreeNode t) {
		if (t != null) {
			postorderTraverse(t.getLeft());
			postorderTraverse(t.getRight());
			System.out.print(t.getValue());
		}
	}

	public static int countNodes(TreeNode t) {
		if (t == null)
			return 0;
		return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
	}

	public static int countLeaves(TreeNode t) {
		if (t == null)
			return 0;
		if (t.getLeft() == t.getRight())// only true when both are null
			return 1;
		return countLeaves(t.getLeft()) + countLeaves(t.getRight());
	}

	public static int countGrandParentNodes(TreeNode t) {
		if (t == null)
			return 0;
		if (height(t) >= 2)//check if node t is grandfather
			return 1 + countGrandParentNodes(t.getLeft()) + countGrandParentNodes(t.getRight());
		return 0;
	}

	public static int countSingleChildNodes(TreeNode t) {
		int n = 0;
		if (t == null)
			return n;
		if ((t.getLeft() != null && t.getRight() == null) || (t.getRight() != null && t.getLeft() == null))//check if single child node
			n++;
		return n + countSingleChildNodes(t.getLeft()) + countSingleChildNodes(t.getRight());
	}

	public static int depth(TreeNode root) {
		return -1;
	}

	public static int height(TreeNode t) {
		if (t == null)
			return -1;
		return 1 + Math.max(height(t.getLeft()), height(t.getRight()));
	}

	public static Object min(TreeNode t) {
		Object left = null, center = t.getValue(), right = null;
		if(t.getLeft() != null)
			left = min(t.getLeft());
		if(t.getRight() != null)
			right = min(t.getRight());//add values for left and right if possible
		if(left == null && right == null)
			return center;
		if(left != null && right != null) {
			if(left.toString().compareTo(right.toString()) <= 0 && left.toString().compareTo(center.toString()) <= 0)
				return left;
			else if(center.toString().compareTo(right.toString()) <= 0 && center.toString().compareTo(left.toString()) <= 0)
				return center;
			else
				return right;
		}//returns the minimum of left, right, or center if left and right are not null
		if(left == null) {//if 1 of left or right is null, returns min of that and center
			if(right.toString().compareTo(center.toString()) < 0)
				return right;
			else
				return center;
		}
		else
			if(left.toString().compareTo(center.toString()) < 0)
				return left;
			else
				return center;
	}

	public static Object max(TreeNode t) {
		Object left = null, center = t.getValue(), right = null;
		if(t.getLeft() != null)
			left = max(t.getLeft());
		if(t.getRight() != null)
			right = max(t.getRight());
		if(left == null && right == null)
			return center;
		if(left != null && right != null) {
			if(left.toString().compareTo(right.toString()) >= 0 && left.toString().compareTo(center.toString()) >= 0)
				return left;
			else if(center.toString().compareTo(right.toString()) >= 0 && center.toString().compareTo(left.toString()) >= 0)
				return center;
			else
				return right;
		}
		if(left == null) {
			if(right.toString().compareTo(center.toString()) > 0)
				return right;
			else
				return center;
		}
		else
			if(left.toString().compareTo(center.toString()) > 0)
				return left;
			else
				return center;
	}
	

	/*****************************************************************************************************
	 * This method is not recursive. Hint: Use a local queue to store the children
	 * of the current node.
	 *****************************************************************************************************/
	public static void displayLevelOrder(TreeNode t) {
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(t);
 		while(!q.isEmpty()) {
 			if(q.peek().getLeft() != null)
 				q.add(q.peek().getLeft());
 			if(q.peek().getRight() != null)
 				q.add(q.peek().getRight());
 			System.out.print(q.remove().getValue().toString());
 		}
	}
} // end of TreeLab_shell

/***************************************************
 * 
 * ----jGRASP exec: java Lab01
 * 
 * E E C M N T E C I U C O S C B P A R
 * 
 * Preorder: C O P R A S B C U C I M T E N E C E Inorder: R A P B C S O C U I C
 * E T N M C E E Postorder: A R C B S P C I U O E N T C E E M C
 * 
 * Nodes = 18 Leaves = 8 Grandparents = 5 Only childs = 3
 * 
 * Depth = 5 Height = 4 Min = A Max = U
 * 
 * By Level: COMPUTERSCIENCEABC
 * 
 *******************************************************/
/* TreeNode class for the AP Exams */

class TreeNode {
	private Object value;
	private TreeNode left, right;

	public TreeNode(Object initValue) {
		value = initValue;
		left = null;
		right = null;
	}

	public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight) {
		value = initValue;
		left = initLeft;
		right = initRight;
	}

	public Object getValue() {
		return value;
	}

	public TreeNode getLeft() {
		return left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setValue(Object theNewValue) {
		value = theNewValue;
	}

	public void setLeft(TreeNode theNewLeft) {
		left = theNewLeft;
	}

	public void setRight(TreeNode theNewRight) {
		right = theNewRight;
	}
}
