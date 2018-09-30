package advCSQ1;

public class Pd5ZacharyWangSinglyLinkedPolynomial implements Polynomial {
	class ListNode<Term> {
		private Term value;
		private ListNode<Term> next;

		public ListNode(Term t, ListNode<Term> n) {
			value = t;
			next = n;
		}

		public Term getValue() {return value;}
		public ListNode<Term> getNext() {return next;}
		public void setValue(Term newTerm) {value = newTerm;}
		public void setNext(ListNode<Term> n) {next = n;}
	} // end of ListNode<Term>

	private static class Term {
		private double exponent;
		private double coeff;

		public Term(double c, double e) {
			exponent = e;
			coeff = c;
		}
		public double getExponent() {return exponent;}
		public double getCoeff() {return coeff;}
		public void setExponent(double x) {exponent = x;}
		public void setCoeff(double x) {coeff = x;}
		public boolean equals(Term other) {
			return this.coeff == other.getCoeff() && this.exponent == other.getExponent();
		}
		public String toString() {
			return coeff + "x^" + exponent;
		}
	} // Term

	private ListNode<Term> head;

	public Pd5ZacharyWangSinglyLinkedPolynomial(double[] terms) {
		head = new ListNode<Term>(null, null);
		ListNode<Term> t = head;
		for (int i = 0; i < terms.length - 1; i += 2) {
			t.setNext(new ListNode<Term>(new Term(terms[i], terms[i + 1]), null));
			t = t.getNext();
		}
	}

	public Pd5ZacharyWangSinglyLinkedPolynomial(ListNode<Term> x) {
		head = new ListNode<Term>(null, null);
		ListNode<Term> t = head;
		for (; x != null; x = x.getNext()) {
			t.setNext(new ListNode<Term>(x.getValue(), null));
			t = t.getNext();
		}
	}

	// copy constructor
	public Pd5ZacharyWangSinglyLinkedPolynomial(Polynomial p) {
		head = new ListNode<Term>(null, null);
		ListNode<Term> t = head;
		ListNode<Term> c = ((Pd5ZacharyWangSinglyLinkedPolynomial)p).getHead().getNext();
		for (; c != null; c = c.getNext()) {
			t.setNext(new ListNode<Term>(c.getValue(), null));
			t = t.getNext();
		}
	}

	//returns the dummy node
	public ListNode<Term> getHead() {
		return head;
	}

	//alters the node after the dummy node
	public void setHead(ListNode<Term> node) {
		head.setNext(node);
	}

	/**
	 * pre-condition: this is not null post-condition: returns a double that is the
	 * answer if parameter x is plugged in as x in the polynomial
	 **/
	public double evaluate(double x) {
		double sum = 0;
		for(ListNode<Term> t = head.getNext(); t != null; t = t.getNext()) 
			sum += Math.pow(x, t.getValue().getExponent()) * t.getValue().getCoeff();
		return sum;
	}

	/**
	 * pre-condition: this and other are not null post-condition: returns a
	 * Pd5ZacharyWangSinglyLinkedPolynomial that is the sum of this and other and is
	 * in highest to lowest exponent order
	 **/
	public Polynomial plus(Polynomial other) {
		ListNode<Term> newHead = new ListNode<Term>(null,null), t = newHead;
		ListNode<Term> thisList = reverse(head.getNext());
		ListNode<Term> otherList = reverse(((Pd5ZacharyWangSinglyLinkedPolynomial)other).getHead().getNext());
		/*
		 * Since I have the terms in each polynomial ordered from lowest to greatest, I start from the low degrees and work my way up.
		 * There are a few cases to consider, so the giant if-else tree is to determine which term needs to be added. This allows
		 * me to account for polynomials that have a coefficient of zero in certain powers.
		 */
		while(thisList != null || otherList != null) {
			Term next;
			if(thisList != null && otherList != null) {
				if(thisList.getValue().getExponent() == otherList.getValue().getExponent()) {
					next = new Term(thisList.getValue().getCoeff() + otherList.getValue().getCoeff(), thisList.getValue().getExponent());
					thisList = thisList.getNext();
					otherList = otherList.getNext();
				} else if (thisList.getValue().getExponent() < otherList.getValue().getExponent()) {
					next = thisList.getValue();
					thisList = thisList.getNext();
				} else {
					next = otherList.getValue();
					otherList = otherList.getNext();
				}
			} else if(thisList == null) {
				next = otherList.getValue();
				otherList = otherList.getNext();
			} else {
				next = thisList.getValue();
				thisList = thisList.getNext();
			}
			t.setNext(new ListNode<Term>(next,null));
			t = t.getNext();
		}
		return new Pd5ZacharyWangSinglyLinkedPolynomial(reverse(newHead.getNext()));
	}

	/**
	 * pre-condition: this and other are not null post-condition: returns a
	 * Pd5ZacharyWangSinglyLinkedPolynomial that is the difference of this and other
	 * and is in highest to lowest exponent order
	 **/
	public Polynomial minus(Polynomial other) {
		ListNode<Term> newHead = new ListNode<Term>(null,null), t = newHead;
		ListNode<Term> thisList = reverse(head.getNext());
		ListNode<Term> otherList = reverse(((Pd5ZacharyWangSinglyLinkedPolynomial)other).getHead().getNext());
		while(thisList != null || otherList != null) {
			Term next;
			if(thisList != null && otherList != null) {
				if(thisList.getValue().getExponent() == otherList.getValue().getExponent()) {
					next = new Term(thisList.getValue().getCoeff() - otherList.getValue().getCoeff(), thisList.getValue().getExponent());
					thisList = thisList.getNext();
					otherList = otherList.getNext();
				} else if (thisList.getValue().getExponent() < otherList.getValue().getExponent()) {
					next = thisList.getValue();
					thisList = thisList.getNext();
				} else {
					next = otherList.getValue();
					otherList = otherList.getNext();
				}
			} else if(thisList == null) {
				next = otherList.getValue();
				otherList = otherList.getNext();
			} else {
				next = thisList.getValue();
				thisList = thisList.getNext();
			}
			t.setNext(new ListNode<Term>(next,null));
			t = t.getNext();
		}
		return new Pd5ZacharyWangSinglyLinkedPolynomial(reverse(newHead.getNext()));
	}

	/**
	 * pre-condition: this is not null post-condition: returns a
	 * Pd5ZacharyWangSinglyLinkedPolynomial that is the derivative of this and is in
	 * highest to lowest exponent order
	 **/
	public Polynomial derivative() {
		ListNode<Term> newHead = new ListNode<Term>(null,null), tHead = head.getNext();
		for(ListNode<Term> t = newHead; tHead.getNext() != null; t = t.getNext(), tHead = tHead.getNext()) {
			Term temp = tHead.getValue();
			t.setNext(new ListNode<Term>(new Term(temp.getCoeff() * temp.getExponent(), temp.getExponent() - 1), null));
		}
		return new Pd5ZacharyWangSinglyLinkedPolynomial(newHead.getNext());
	}

	/**
	 * pre-condition: this and other are not null post-condition: return a new
	 * Pd5ZacharyWangSinglyLinkedPolynomial in highest to lowest degree order that
	 * is the product of this and other
	 **/
	public Polynomial multiply(Polynomial other) {
		//recreate distributive property by using nested for-loop to multiple each term in one polynomial by each term in the other, 
		//while constantly compounding them into the final polynomial
		Polynomial product = new Pd5ZacharyWangSinglyLinkedPolynomial(new ListNode<Term>(new Term(0,0),null));
		for(ListNode<Term> p1 = head.getNext(); p1 != null; p1 = p1.getNext()) {
			ListNode<Term> temp = new ListNode<Term>(null,null);
			for(ListNode<Term> p2 = ((Pd5ZacharyWangSinglyLinkedPolynomial)other).getHead().getNext(), t = temp; p2 != null; p2 = p2.getNext(), t = t.getNext()) {
				double coeff = p1.getValue().getCoeff() * p2.getValue().getCoeff(), power = p1.getValue().getExponent() + p2.getValue().getExponent();
				t.setNext(new ListNode<Term>(new Term(coeff, power),null));
			}
			product = product.plus(new Pd5ZacharyWangSinglyLinkedPolynomial(temp.getNext()));
		}
		return product;
	}

	/**
	 * pre-condition: head and node aren't null post-condition: node is removed from
	 * the list passed
	 **/
	public void remove(ListNode<Term> head, ListNode<Term> node) {
		ListNode<Term> t = head;
		for(; t.getNext() != node; t = t.getNext());
		t.setNext(t.getNext().getNext());
	}

	/**
	 * pre-condition: head is not null post-condition: return head of the reversed
	 * list
	 **/
	public ListNode<Term> reverse(ListNode<Term> head) {
		//deep copy list to avoid issues with changing the original
		ListNode<Term> newHead = new ListNode<Term>(null, null), t = newHead;
		for(;head != null; head = head.getNext(),t = t.getNext())
			t.setNext(new ListNode<Term>(head.getValue(),null));
		ListNode<Term> prev = null, next = null, current = newHead.getNext();
        while (current != null) { 
            next = current.getNext(); 
            current.setNext(prev); 
            prev = current; 
            current = next; 
        } 
        return prev; 
	}

	@Override
	public boolean equals(Object other) {
		return (((Pd5ZacharyWangSinglyLinkedPolynomial)other).minus(this)).toString().length() == 0;
	}

	public String toString() {
		String poly = "";
		for (ListNode<Term> t = head.getNext(); t != null; t = t.getNext()) {
			double c = t.getValue().getCoeff();
			if (!(c == 0)) {
				if (c < 0)// add negative if needed
					poly += " - ";
				else if (t != head.getNext() && c >= 0)
					poly += " + ";
				if (t.getNext() == null && t != head.getNext())// exclude x term if last
					poly += Math.abs(c);
				else
					poly += Math.abs(c) + "x^" + t.getValue().getExponent();
			}
		}
		return poly;
	}

	public static void main(String[] args) {
		
		double[] arr = { 4, 3, 3, 2, 1, 0 };
		Polynomial p1 = new Pd5ZacharyWangSinglyLinkedPolynomial(arr); // 4x^3 + 3x^2 + 1
		System.out.println("p1(x) =     " + p1);

		double[] arr2 = { -5, 1, -2, 0 };
		Polynomial p2 = new Pd5ZacharyWangSinglyLinkedPolynomial(arr2); // - 5x – 2
		System.out.println("p2(x) =     " + p2); // p2(x) = - 5x^1 – 2
		System.out.println("p1 and p2 are the same: " + p1.equals(p2));

		double[] arr3 = { -4, 1 };
		Polynomial p3 = new Pd5ZacharyWangSinglyLinkedPolynomial(arr3); // coeff, exp = -4x
		System.out.println("p3(x) =     " + p3);

		Polynomial p = p1.plus(p2).plus(p2); // 4x^3 + 3x^2 - 10x – 3
		System.out.println("p1(x) =     " + p1);
		System.out.println("p2(x) =     " + p2);
		System.out.println("p(x)  =     " + p); // p(x) = 4x^3 + 3x^2 - 10x^1 – 3

		Polynomial p4 = p.minus(p3); // 4x^3 + 3x^2 - 6x^1 – 3 <====
		System.out.println("p4(x) =     " + p4);

		Polynomial p5 = p4.derivative(); // 12x^2 + 6x^1 - 6 <====
		System.out.println("p5(x) =     " + p5);

		Polynomial clone = new Pd5ZacharyWangSinglyLinkedPolynomial(p5);
		System.out.println("clone(x) =     " + clone);
		System.out.println("p5 and clone are the same: " + p5.equals(clone));

		Polynomial clone2 = p5;
		System.out.println("clone2(x) =    " + clone2);
		System.out.println("p5 and clone 2 are the same: " + p5.equals(clone2));

		Polynomial product = p1.multiply(p2);
		System.out.println("product of p1(x) and p2(x) is     " + product);

		System.out.println("p5(0) = " + p5.evaluate(0));
		System.out.println("p5(1) = " + p5.evaluate(1));
		
		/*
		 * Printout:
		 * p1(x) =     4.0x^3.0 + 3.0x^2.0 + 1.0
p2(x) =      - 5.0x^1.0 - 2.0
p1 and p2 are the same: false
p3(x) =      - 4.0x^1.0
p1(x) =     4.0x^3.0 + 3.0x^2.0 + 1.0
p2(x) =      - 5.0x^1.0 - 2.0
p(x)  =     4.0x^3.0 + 3.0x^2.0 - 10.0x^1.0 - 3.0
p4(x) =     4.0x^3.0 + 3.0x^2.0 - 6.0x^1.0 - 3.0
p5(x) =     12.0x^2.0 + 6.0x^1.0 - 6.0
clone(x) =     12.0x^2.0 + 6.0x^1.0 - 6.0
p5 and clone are the same: true
clone2(x) =    12.0x^2.0 + 6.0x^1.0 - 6.0
p5 and clone 2 are the same: true
product of p1(x) and p2(x) is      - 20.0x^4.0 - 23.0x^3.0 - 6.0x^2.0 - 5.0x^1.0 - 2.0
p5(0) = -6.0
p5(1) = 12.0

		 */
	}
}
