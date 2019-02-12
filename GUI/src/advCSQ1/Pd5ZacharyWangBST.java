

/*****************************************************************************************************************
NAME: Zachary Wang
PERIOD: 5
DUE DATE: 1/30/19

PURPOSE: Practice with the basic methods find and insert for a binary tree.

WHAT I LEARNED: Simply having the tree organized in a certain order makes it highly efficient, with O(log n) for searching, inserting, and deleting.
	The downside is that when the list itself is being created, it has a particularly bad "worse case", with n items requiring n comparisons. I also noted
	how the small to large traversal followed the same process as the inorder traversal, which makes sense because the order of items in a tree will always be left, then center, then right.

HOW I FEEL ABOUT THIS LAB: I think these methods were simple enough to write, by virtue of the structure itself. min and max for example are very easy, and even finding items is a simple matter
	with recursion. I am excited to attempt deletion and other methods.
            
CREDITS (BE SPECIFIC: FRIENDS, PEERS, ONLINE WEBSITE): 

****************************************************************************************************************/
import java.util.Scanner;

/****************************************************************
 * Practice with a Binary Search Tree. Uses TreeNode. Prompt the user for an
 * input string. Build a Binary Search Tree using Comparables. Display it as a
 * sideways tree (take the code from the Tree Lab). Prompt the user for a target
 * and search the tree for it. Display the tree's minimum and maximum values.
 * Print the data in order from smallest to largest.
 *****************************************************************/
public class Pd5ZacharyWangBST {

	public static void main(String[] args)
   {
	   Scanner s = new Scanner(System.in);
	   System.out.println("Enter the string you would like to create the tree with(must not be empty): ");
       String input = s.nextLine();
       TreeNode t = new TreeNode(input.substring(0, 1));
       for(int i = 1; i < input.length(); i++)
    	   insert(t, input.substring(i, i+1));
       display(t , 10);
       System.out.println("enter a character to find:");
       String target = s.nextLine();
       if(find(t, target))
    	   System.out.println("Found!");
       else
    	   System.out.println("Not found.");
       System.out.println("Min value is: " + min(t));
       System.out.println("Max value is: " + max(t));
       System.out.println("Small to large traversal: ");
       smallToLarge(t);
       System.out.println();
       main(args);
  }

	/****************************************************************
	 * Recursive algorithm to build a BST: if the node is null, insert the new node.
	 * Else, if the item is less, set the left node and recur to the left. Else, if
	 * the item is greater, set the right node and recur to the right.
	 *****************************************************************/
	//pre: t is a correctly built BST with String values, or is simply null
	//post: t will have the a node with value s added in the appropriate location
	public static void insert(TreeNode t, String s) {
		if(t == null)
			t = new TreeNode(s);
		if(s.compareTo(t.getValue().toString()) > 0) {//if item > t's value, go right
			if(t.getRight() == null)
				t.setRight(new TreeNode(s));//insert to the right
			else
				insert(t.getRight(), s);//keep going right if needed
		} else {
			if(t.getLeft() == null)
				t.setLeft(new TreeNode(s));
			else
				insert(t.getLeft(), s);
		}
	}

	public static void display(TreeNode t, int level) {
		if (t == null)
			return;

		display(t.getRight(), level + 1); // recurse right

		for (int k = 0; k < level; k++)
			System.out.print("\t");
		System.out.println(t.getValue());

		display(t.getLeft(), level + 1); // recurse left
	}

	/***************************************************************
	 * Iterative algorithm: create a temporary pointer p at the root. While p is not
	 * null, if the p's value equals the target, return true. If the target is less
	 * than the p's value, go left, otherwise go right. If the target is not found,
	 * return false.
	 * 
	 * Find the target. Recursive algorithm: If the tree is empty, return false. If
	 * the target is less than the current node value, return the left subtree. If
	 * the target is greater, return the right subtree. Otherwise, return true. .
	 ****************************************************************/
	//pre: t is a correctly built bst with comparable values, or is null
	//post: return true if object is found, false if not
	public static boolean find(TreeNode t, Comparable x) {
		while(t != null) {
			if(((Comparable) t.getValue()).equals(x))//return true when the value is found
				return true;
			if(((Comparable) t.getValue()).compareTo(x) < 0)//determine which way to continue searching
				t = t.getRight();
			else
				t = t.getLeft();
		}
		return false;
	}

	/***************************************************************
	 * starting at the root, return the min value in the BST. Use iteration. Hint:
	 * look at several BSTs. Where are the min values always located?
	 ***************************************************************/
	//pre: t is a correctly built bst, or is null
	//post: lowest object is returned
	public static Object min(TreeNode t) {
		if(t == null)
			return null;
		while(t.getLeft() != null)
			t = t.getLeft();
		return t.getValue();
	}

	/*****************************************************************
	 * starting at the root, return the max value in the BST. Use recursion!
	 *****************************************************************/
	//pre: t is a correctly built bst, or is null
	//post: highest object is returned
	public static Object max(TreeNode t) {
		if(t == null)
			return null;
		if(t.getRight() == null)
			return t.getValue();
		return max(t.getRight());
	}

	//pre: t is a correctly built bst, or is null
	//post: prints every value in ascending order
	public static void smallToLarge(TreeNode t) {//same as inOrder traversal
		if (t != null) {
			smallToLarge(t.getLeft());
			System.out.print(t.getValue());
			smallToLarge(t.getRight());
		}
	}
} // Example output:
/*
Enter the string you would like to create the tree with(must not be empty): a
										a
enter a character to find:
a
Found!
Min value is: a
Max value is: a
Small to large traversal: 
a
 
Enter the string you would like to create the tree with(must not be empty): 
aab
											b
										a
											a
enter a character to find:
b
Found!
Min value is: a
Max value is: b
Small to large traversal: 
aab
Enter the string you would like to create the tree with(must not be empty): 
abc
												c
											b
										a
enter a character to find:
c
Found!
Min value is: a
Max value is: c
Small to large traversal: 
abc
Enter the string you would like to create the tree with(must not be empty): 
american
												r
													n
											m
													i
												e
													c
										a
											a
enter a character to find:
r
Found!
Min value is: a
Max value is: r
Small to large traversal: 
aaceimnr
Enter the string you would like to create the tree with(must not be empty): 
computerscience
													u
														t
																s
															r
												p
											o
													n
												m
														i
													e
														e
															e
										c
											c
												c
enter a character to find:
s
Found!
Min value is: c
Max value is: u
Small to large traversal: 
ccceeeimnoprstu
Enter the string you would like to create the tree with(must not be empty): 
aaaaaaa
										a
											a
												a
													a
														a
															a
																a
enter a character to find:
b
Not found.
Min value is: a
Max value is: a
Small to large traversal: 
aaaaaaa
 * */
