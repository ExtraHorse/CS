package advCSQ1;

/*            
* Period: 5
* Name of the Lab: Linked List
* Purpose of the Program: Manipulate Linked Lists
* Due Date: 9/23/18
* Date Submitted: 9/23/18
* What I learned: Linked list manipulation largely involves handling pointers effectively, knowing how to work with them
* and manage multiple of them is key to complex manipulations of linked lists
* How I feel about this lab: It was a little tricky working out issues with pointers, as sometimes you can be causing yourself
* problems without actually realizing it
* What I wonder: What is a doubly linked list? 
* Credits:  
* Students whom I helped (to what extent): I helped Aditya occasionally with the logic of his split node method
* 
* Has both extra credit methods
* 
*/
import java.util.NoSuchElementException;
import java.util.Scanner;

import advCSQ1.Pd5ZacharyWangSinglyLinkedPolynomial.ListNode;

import java.util.ArrayList;

public class Pd5ZacharyWangListNodeLinkedListLab {

	private static class ListNode<E> {
		private E value;
		private ListNode<E> next;

		public ListNode(E initValue, ListNode<E> initNext) {
			value = initValue;
			next = initNext;
		}

		public E getValue() {
			return value;
		}

		public ListNode<E> getNext() {
			return next;
		}

		public void setValue(E theNewValue) {
			value = theNewValue;
		}

		public void setNext(ListNode<E> theNewNext) {
			next = theNewNext;
		}

	} // end of ListNode

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ListNode<Integer> h = new ListNode(5, null);
		h = new ListNode(4, h);
		h = new ListNode(3, h);
		h = new ListNode(2, h);
		h = new ListNode(1, h);

		char option;
		do {
			option = menu();
			if (option == 'a') {
				System.out.println("list: ");
				printLinkedList(h);
			} else if (option == 'b') {
				System.out.println("The List has atleast two element?");
				System.out.println(hasTwo(h));
			} else if (option == 'c') {
				System.out.print("The size of the array is: ");
				System.out.println(size(h));
			} else if (option == 'd') {
				h = removeFirst(h);
				System.out.print("New list is: ");
				printLinkedList(h);
			} else if (option == 'e') {
				System.out.println("Enter number: ");
				int num = input.nextInt();
				h = add(h, new Integer(num));
				System.out.println("New list is: ");
				printLinkedList(h);
			} else if (option == 'f') {
				h = reverseList(h);
				System.out.println("Reverse is: ");
				printLinkedList(h);
			} else if (option == 'g') {
				h = rotate(h);
				System.out.println("rotated array is: ");
				printLinkedList(h);
			} else if (option == 'h') {
				printLinkedList(h);
				System.out.println("\nmiddle node is: " + middleNode(h).getValue());
			}

			else if (option == 'i') {
				h = removeLast(h);
				System.out.println("New list is: ");
				printLinkedList(h);
			}

			else if (option == 'j') {
				System.out.println("Please enter the value you want to remove");
				int v = input.nextInt();
				h = remove(h, v);
				System.out.println("New list is: ");
				printLinkedList(h);
			}

			else if (option == 'k') {
				Split(h);
			}

		} while (option != 'z');
	} // end of main

	public static void printLinkedList(ListNode<Integer> head) {
		for (; head != null; head = head.getNext())
			System.out.println(head.getValue());
	}

	public static boolean hasTwo(ListNode<Integer> head) {
		return (head != null && head.getNext() != null);
	}

	public static int size(ListNode<Integer> head) {
		int count;
		for (count = 0; head != null; head = head.getNext(), count++)
			;
		return count;
	}

	public static ListNode<Integer> removeFirst(ListNode<Integer> head) {
		if (head == null)
			return null;
		return head.getNext();
	}

	public static ListNode<Integer> removeLast(ListNode<Integer> head) {
		if(head == null)
			return head;
		if (head.getNext() == null)
			return null;
		ListNode<Integer> temp = head;
		for (; temp.getNext().getNext() != null; temp = temp.getNext())
			;
		temp.setNext(null);
		return head;
	}

	public static ListNode<Integer> remove(ListNode<Integer> head, Integer value) {
		if (head == null)
			return null;
		if (head.getValue() == value)
			head = head.getNext();
		ListNode<Integer> prev = head;
		ListNode<Integer> temp = head.getNext();
		for (; temp != null; temp = temp.getNext(), prev = prev.getNext()) {
			if (temp.getValue().equals(value))
				prev.setNext(temp.getNext());
		}
		return head;
	}

	public static ListNode<Integer> add(ListNode<Integer> head, Integer value) {
		if (head == null)
			head = new ListNode<Integer>(value, null);
		ListNode<Integer> temp = head;
		for (; temp.getNext() != null; temp = temp.getNext())
			;
		temp.setNext(new ListNode<Integer>(value, null));
		return head;
	}

	public static ListNode<Integer> reverseList(ListNode<Integer> head) {// This might be cheating
		ListNode<Integer> prev = null, next = null, current = head;
        while (current != null) { 
            next = current.getNext(); 
            current.setNext(prev); 
            prev = current; 
            current = next; 
        } 
        head = prev; 
        return head; 
	}

	public static ListNode<Integer> rotate(ListNode<Integer> head) {
		if (head == null)
			return head;
		if (head.getNext() == null)
			return head;
		int v = head.getValue();
		head = head.getNext();
		ListNode<Integer> temp = head;
		for (; temp.getNext() != null; temp = temp.getNext());
		temp.setNext(new ListNode<Integer>(v, null));
		return head;
	}

	public static ListNode<Integer> middleNode(ListNode<Integer> head) {
		if(head == null)
			return head;
		int mid = size(head)/ 2;
		ListNode<Integer> temp = head;
		for (; mid > 0; mid--, temp = temp.getNext());
		return temp;
	}

	public static void Split(ListNode<Integer> head) {
		ListNode<Integer> oddPrev = new ListNode<Integer>(head.getValue(), null);
		ListNode<Integer> evenPrev = null;
		if (head.getNext() == null) {
			System.out.println("Odd list: " + head.getValue());
			return;
		} else
			evenPrev = new ListNode<Integer>(head.getNext().getValue(), null);
		ListNode<Integer> evens = evenPrev;
		ListNode<Integer> odds = oddPrev;// create two fresh headers that will store the working lists
		boolean alternator = true;
		for (ListNode<Integer> temp = head.getNext().getNext(); temp != null; temp = temp.getNext()) {
			if (alternator) {
				oddPrev.setNext(new ListNode<Integer>(temp.getValue(), null));
				oddPrev = oddPrev.getNext();
			} else {
				evenPrev.setNext(new ListNode<Integer>(temp.getValue(), null));
				evenPrev = evenPrev.getNext();
			}
			alternator = !alternator;
		} // assign values alternating
		System.out.println("Odd List:");
		printLinkedList(odds);
		System.out.println("Evens List:");
		printLinkedList(evens);
	}

	public static char menu() {
		Scanner input = new Scanner(System.in);
		System.out.println("\n====> What would you like to do?");
		System.out.println("a) Print list");
		System.out.println("b) Check if list has at least two nodes");
		System.out.println("c) Get size of the list");
		System.out.println("d) Remove first node");
		System.out.println("e) Add a node");
		System.out.println("f) Reverse");
		System.out.println("g) Rotate");
		System.out.println("h) Get middle node");
		System.out.println("i) Remove last node");
		System.out.println("j) Remove node with value");
		System.out.println("k) Split List");
		System.out.println("z) Quit?");
		String choice = input.next();
		return choice.charAt(0);
	} // end of menu
}