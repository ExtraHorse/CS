

public class Pd5ZacharyWangArrayBasedPoly implements Pd5ZacharyWangPolynomial {

	double[] c;

	public Pd5ZacharyWangArrayBasedPoly(double[] c) {
		this.c = c;
	}

	public Pd5ZacharyWangArrayBasedPoly(int coefficient, int exponent) {
		double[] cNew = new double[exponent + 1];
		cNew[exponent] = coefficient;
		for (int i = 0; i < exponent; i++)
			cNew[i] = 0;
		c = cNew;
	}

	public int degree() {
		return c.length - 1;
	}

	public double coefficient(int exponent) {
		return c[exponent];
	}

	public double evaluate(double x) {
		double total = 0;
		for (int i = 0; i < c.length; i++)
			total += c[i] * Math.pow(x, i);
		return total;
	}

	public Pd5ZacharyWangPolynomial add(Pd5ZacharyWangPolynomial other) {
		Pd5ZacharyWangArrayBasedPoly greater;
		if (degree() > other.degree())
			greater = this;
		else
			greater = (Pd5ZacharyWangArrayBasedPoly)other;
		double[] cNew = new double[greater.degree() + 1];
		int counter;
		for (counter = 0; counter <= Math.min(degree(), other.degree()); counter++) {
			cNew[counter] = coefficient(counter) + other.coefficient(counter);
		}
		for (int i = counter; i <= greater.degree(); i++)
			cNew[i] = greater.coefficient(i);
		return new Pd5ZacharyWangArrayBasedPoly(cNew);
	}

	public Pd5ZacharyWangPolynomial subtract(Pd5ZacharyWangPolynomial other) {
		Pd5ZacharyWangArrayBasedPoly greater;
		if (degree() > other.degree())
			greater = this;
		else
			greater = (Pd5ZacharyWangArrayBasedPoly)other;
		double[] cNew = new double[greater.degree() + 1];
		int counter;
		for (counter = 0; counter <= Math.min(degree(), other.degree()); counter++) {
			cNew[counter] = coefficient(counter) - other.coefficient(counter);
		}
		for (int i = counter; i <= greater.degree(); i++)
			cNew[i] = greater.coefficient(i);
		return new Pd5ZacharyWangArrayBasedPoly(cNew);
	}

	public Pd5ZacharyWangPolynomial getDerivative() {
		double[] cNew = new double[degree()];
		for (int i = cNew.length - 1; i >= 0; i--)
			cNew[i] = c[i + 1] * (i + 1);
		return new Pd5ZacharyWangArrayBasedPoly(cNew);
	}

	public String toString() {
		String poly = "";
		for (int i = c.length - 1; i >= 0; i--) {
			if (!(c[i] == 0)) {
				if (c[i] < 0)//add negative if needed
					poly += " - ";
				else if (i < c.length - 1 && c[i] >= 0)
					poly += " + ";
				if(i == 0)//exclude x term if last
					poly += Math.abs(c[i]);
				else
					poly += Math.abs(c[i]) + "x^" + i;
			}
		}
		return poly;
	}
}
