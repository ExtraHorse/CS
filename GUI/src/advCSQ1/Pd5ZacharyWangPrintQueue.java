

/***********************************************************************
Name:Zachary Wang
Period:5
Date:12/21/18
What I Learned:Queues are helpful in situations where order is important, since queues are FIFO.    
Credit (person who helped me):Justin Krisanda, for notifying me that queues have a toString() method that works 
Student(s) whom I helped (to what extent):none
************************************************************************/

import java.io.*;
import java.util.*;

public class Pd5ZacharyWangPrintQueue {
	private static Scanner in;
	private static Queue<String> q;
	private static int jobNumber = 100;

	public static void main(String[] args) throws Exception {
		q = new LinkedList<String>();
		Queue<String> temp = q;
		in = new Scanner(System.in);
		String prompt = "Add, Print, Delete, eXit:  ";
		System.out.print(prompt);
		String str = in.next();
		while (!str.equals("X")) {
			if (str.equals("A"))
				add();
			else if (str.equals("P"))
				printJob();
			else if (str.equals("D"))
				delete();
			else
				System.out.println("   invalid command");
			printQueue();
			System.out.print(prompt);
			str = in.next();
		}
		in.close();
	}

	//pre: q is correctly instantiated as a string queue
	//post: a string with the job number is added to the queue, the job number is incremented
	public static void add() {
		q.add(jobNumber++ + "");
	}

	//pre: q is correctly instantiated as a string queue
	//post: earliest added job is printed and removed from queue, unless queue is empty, in which the user is notified that there are no jobs to print
	public static void printJob() {
		if(q.isEmpty())
			System.out.println("Error--no jobs to print");
		else
			System.out.println(q.remove());
	}

	//pre:q is correctly instantiated as a string queue, user types a valid integer for the program to remove from the queue
	//post: the job is deleted if it is found in the queue, otherwise the user is notified of its absence
	public static void delete() {
		System.out.println("Enter Job Number:");
		int n = in.nextInt();//prompt for number
		if(q.remove(n + ""))//attempt removal, notify user depending on success or failure
			System.out.println("Deleted Job " + n);
		else
			System.out.println("Error--job does not exist");
	}

	public static void printQueue() {
		System.out.println(q.toString());
	}
}/* Example output:
Add, Print, Delete, eXit:  A
[100]
Add, Print, Delete, eXit:  D
Enter Job Number:
100
Deleted Job 100
[]
Add, Print, Delete, eXit:  A
[101]
Add, Print, Delete, eXit:  A
[101, 102]
Add, Print, Delete, eXit:  A
[101, 102, 103]
Add, Print, Delete, eXit:  A
[101, 102, 103, 104]
Add, Print, Delete, eXit:  D
Enter Job Number:
99
Error--job does not exist
[101, 102, 103, 104]
Add, Print, Delete, eXit:  P
101
[102, 103, 104]
Add, Print, Delete, eXit:  P
102
[103, 104]
Add, Print, Delete, eXit:  P
103
[104]
Add, Print, Delete, eXit:  P
104
[]
Add, Print, Delete, eXit:  P
Error--no jobs to print
[]
Add, Print, Delete, eXit:  D
Enter Job Number:
100
Error--job does not exist
[]
Add, Print, Delete, eXit:  */
