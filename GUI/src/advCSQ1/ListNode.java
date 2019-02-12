


public class ListNode<T> {
	T value;
	ListNode<T> next;
	public ListNode(T val, ListNode<T> next){
		value = val;
		this.next = next;
	}
	public T getValue() {
		return value;
	}
	public ListNode<T> getNext(){
		return next;
	}
	public void setNext(ListNode<T> other) {
		next = other;
	}
	public void setValue(T val) {
		value = val;
	}
}
