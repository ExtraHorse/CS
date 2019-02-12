

public interface Pd5ZacharyWangPolynomial {
	int degree();
	double coefficient(int exponent);
	double evaluate(double x);
	Pd5ZacharyWangPolynomial add(Pd5ZacharyWangPolynomial other);
	Pd5ZacharyWangPolynomial subtract(Pd5ZacharyWangPolynomial other);
	Pd5ZacharyWangPolynomial getDerivative();
}
