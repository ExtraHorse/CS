package advCSQ1;

public class Pd5ZacharyWangDLL <E>{
	/***********************************************************************************************************************************************
	 * Name: Zachary Wang
	 * Period: 5
	 * Name of the Lab: DLL
	 * Purpose of the Program: implement basic methods to manipulate doubly linked lists.
	 * Due Date: 10/3/18
	 * Date Submitted:10/3/18 
	 * What I learned: Doubly linked lists, as long as you are careful to relink the list properly, are often much easier and faster to work with than circular or singly linked lists.
	 * How I feel about this lab: Much easier than the SLL polynomial lab
	 * What I wonder: 
	 * Student(s) who helped me (to what extent): 
	 * Student(s) whom I helped (to what extent):
	 *************************************************************************************************************************************************/
	// private static nested class
	private class DLNode<E> {
		private E value;
		private DLNode prev;
		private DLNode next;

		public DLNode(E arg, DLNode<E> p, DLNode<E> n) {
			value = arg;
			prev = p;
			next = n;
		}

		public DLNode() {
			value = null;
			next = this;
			prev = this;
		}

		public void setValue(E arg) {
			value = arg;
		}

		public void setNext(DLNode<E> arg) {
			next = arg;
		}

		public void setPrev(DLNode<E> arg) {
			prev = arg;
		}

		public DLNode<E> getNext() {
			return next;
		}

		public DLNode<E> getPrev() {
			return prev;
		}

		public E getValue() {
			return value;
		}
	} // end of DLNode

	// *********************************************************************************************
	// DLL class

	private int size;
	private DLNode<E> head = new DLNode<E>(); // dummy node--very useful--simplifies the code

	public int size() {
		int count = 0;
		for(DLNode<E> t = head; t.getNext() != head; t = t.getNext(), count++);
		return count;
	}

	/*
	 * appends obj to end of list; increases size;
	 * 
	 * @return true
	 */
	public boolean add(E obj) {
		DLNode<E> t = head;
		for(; t.getNext() != head; t = t.getNext());
		t.setNext(new DLNode<E>(obj,t,head));
		head.setPrev(t);
		return true;
	}

	/*
	 * inserts obj at position index. increments size.
	 */
	public void add(int index, E obj) {
		DLNode<E> t = head;
		for(int i = 0; i != index; t = t.getNext(), i++);
		t.setNext(new DLNode<E>(obj,t,t.getNext()));
		t.getNext().getNext().setPrev(t.getNext());
	}

	/*
	 * return obj at position index.
	 */
	public E get(int index) {
		DLNode<E> t = head.getNext();
		for(int i = 0; i != index; t = t.getNext(), i++);
		return t.getValue();
	}

	/*
	 * replaces obj at position index.
	 */
	public void set(int index, E obj) {
		DLNode<E> t = head;
		for(int i = 0; i != index; t = t.getNext(), i++);
		t.setValue(obj);
	}

	/*
	 * removes the node from position index. decrements size.
	 * 
	 * @return the object at position index.
	 */
	public E remove(int index) {
		DLNode<E> t = head;
		for(int i = 0; i != index; t = t.getNext(), i++);
		E val = t.getNext().getValue();
		t.setNext(t.getNext().getNext());
		t.getNext().setPrev(t);
		return val;
	}

	/*
	 * inserts obj at front of list; increases size;
	 */
	public void addFirst(E obj) {
		head.setNext(new DLNode<E>(obj, head, head.getNext()));
		head.getNext().getNext().setPrev(head.getNext());
	}

	/*
	 * appends obj to end of list; increases size;
	 */
	public void addLast(E obj) {
		DLNode<E> t = head;
		for(; t.getNext() != head; t = t.getNext());
		t.setNext(new DLNode<E>(obj,t,head));
		head.setPrev(t);
	}

	public E getFirst() {
		return head.getNext().getValue();
	}

	public E getLast() {
		return head.getPrev().getValue();
	}

	public E removeFirst() {
		E val = head.getNext().getValue();
		head.setNext(head.getNext().getNext());
		head.getNext().setPrev(head);
		return val;
	}

	public E removeLast() {
		E val = head.getPrev().getValue();
		head.setPrev(head.getPrev().getPrev());
		head.getPrev().setNext(head);
		return val;
		
	}

	public String toString() {
		String s = "";
		for(DLNode<E> t = head.getNext(); t != head; t = t.getNext())
			s += t.getValue().toString() + '\n';
		return s;
	}

	public static void main(String args[]) {
		Pd5ZacharyWangDLL<String> list = new Pd5ZacharyWangDLL<String>();

		list.addLast("Apple");
		list.addLast("Banana");
		list.addLast("Cucumber");
		list.add("Dumpling");
		list.add("Escargot");
		System.out.println(list);
		System.out.println("Size: " + list.size());
		Object obj = list.remove(3);
		System.out.println(list);
		System.out.println("Size: " + list.size());
		System.out.println("Removed " + obj);
		System.out.println("Add at 3:   ");
		list.add(3, "Cheese");
		System.out.println(list);
		System.out.println("Get values at 1 and first: " + list.get(1) + " and " + list.getFirst());
		System.out.println("No change: \n" + list);
		System.out.println(list.removeFirst() + " is now removed!");
		System.out.println(list);
		System.out.println("Add first:  ");
		list.addFirst("Anchovie");
		System.out.println(list);
		System.out.println("Size: " + list.size());
		System.out.println("Set the second:  ");
		list.set(2, "Bread");
		System.out.println(list);
	}
}/*Apple
Banana
Cucumber
Dumpling
Escargot

Size: 5
Apple
Banana
Cucumber
Escargot

Size: 4
Removed Dumpling
Add at 3:   
Apple
Banana
Cucumber
Cheese
Escargot

Get values at 1 and first: Banana and Apple
No change: 
Apple
Banana
Cucumber
Cheese
Escargot

Apple is now removed!
Banana
Cucumber
Cheese
Escargot

Add first:  
Anchovie
Banana
Cucumber
Cheese
Escargot

Size: 5
Set the second:  
Anchovie
Bread
Cucumber
Cheese
Escargot*/
