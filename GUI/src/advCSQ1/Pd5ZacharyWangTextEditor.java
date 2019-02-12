

import java.util.*;


/***********************************************************************
Name: Zachary Wang
Period: 5
Date: 12/11    
What I Learned: The stack method is very effective for cumulative processes, since the
most recent items can be added or removed easily.     
Credit (person who helped me): none
Student(s) whom I helped (to what extent): Matthew Wydra, reminded him that to check if 
a stack has elements remaining, == null does not work but isEmpty() does.
************************************************************************/   
public class Pd5ZacharyWangTextEditor {
	public static void main(String[] args) {
		Stack<Character> s = new Stack<Character>();
		Scanner console = new Scanner(System.in);
		System.out.println("Enter a line of text:");
		String input = console.nextLine();
		for (int i = 0; i < input.length(); i++) {//goes through each character
			char c = input.charAt(i);
			if (!s.isEmpty()) {//if items are in the list
				if (c == '-')//remove last if -
					s.pop();
				else if (c == '$')
					while (!s.isEmpty())//remove all if $
						s.pop();
				else
					s.push(c);//otherwise add it
			} else
				s.push(c);
		}
		System.out.println("Here is the line you entered: ");
		printStack(s);
		System.out.println("Again? (y/n)");//repeat if y
		if(console.nextLine().equals("y"))
			main(args);
	}

	//pre: s is not null
	//post: s has contents printed, s is also empty
	public static void printStack(Stack<Character> s) {//reverses and prints stack
		String output = "";
		while (!s.isEmpty())
			output = s.pop() + output;
		System.out.println(output);
	}
}/* Example output:
Enter a line of text:
 
Here is the line you entered: 
 
Again? (y/n)
y
Enter a line of text:
q
Here is the line you entered: 
q
Again? (y/n)
y
Enter a line of text:
-
Here is the line you entered: 
-
Again? (y/n)
y
Enter a line of text:
$
Here is the line you entered: 
$
Again? (y/n)
y
Enter a line of text:
--$$
Here is the line you entered: 

Again? (y/n)
y
Enter a line of text:
dog
Here is the line you entered: 
dog
Again? (y/n)
y
Enter a line of text:
Ca-noe$Ra3-fx-t
Here is the line you entered: 
Raft
Again? (y/n)
y
Enter a line of text:
AP$$-Compp-utee-r Sic--cei--ience
Here is the line you entered: 
Computer Science
Again? (y/n)
n
*/
