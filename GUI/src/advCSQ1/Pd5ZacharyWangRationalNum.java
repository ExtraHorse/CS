package advCSQ1;

public class Pd5ZacharyWangRationalNum {
	private int numerator, denominator;
	public Pd5ZacharyWangRationalNum(int n, int d) {
		numerator = n;
		denominator = d;
		simplify();
	}
	
	public int getNumerator() {return numerator;}
	public int getDenominator() {return denominator;}
	
	private void simplify() {
		int factor;
		if(numerator > denominator) 
			factor = gcf(numerator, denominator);
		else
			factor = gcf(denominator, numerator);
		numerator /= factor;
		denominator /= factor;	
	}
	
	private int gcf(int n, int d) {
		if(n == 0 || d == 0)
			return n + d;
		return gcf(d, n%d);
	}
	
	public String toString() {
		simplify();
		return numerator + "/" + denominator;
	}
	
	public Pd5ZacharyWangRationalNum add(Pd5ZacharyWangRationalNum other) {
		int newNumerator = numerator * other.getDenominator() + denominator * other.getNumerator();
		int newDenominator = denominator * other.getDenominator();
		return new Pd5ZacharyWangRationalNum(newNumerator, newDenominator);
	}
	
	public Pd5ZacharyWangRationalNum subtract(Pd5ZacharyWangRationalNum other) {
		int newNumerator = numerator * other.getDenominator() - denominator * other.getNumerator();
		int newDenominator = denominator * other.getDenominator();
		return new Pd5ZacharyWangRationalNum(newNumerator, newDenominator);
	}
	
	public Pd5ZacharyWangRationalNum multiply(Pd5ZacharyWangRationalNum other) {
		int newNumerator = numerator  * other.getNumerator();
		int newDenominator = denominator * other.getDenominator();
		return new Pd5ZacharyWangRationalNum(newNumerator, newDenominator);
	}
	
	public Pd5ZacharyWangRationalNum divide(Pd5ZacharyWangRationalNum other) {
		int newNumerator = numerator  * other.getDenominator();
		int newDenominator = denominator * other.getNumerator();
		return new Pd5ZacharyWangRationalNum(newNumerator, newDenominator);
	}
	
	public boolean equals(Pd5ZacharyWangRationalNum other) {
		return numerator == other.numerator && denominator == other.denominator;
	}
}
