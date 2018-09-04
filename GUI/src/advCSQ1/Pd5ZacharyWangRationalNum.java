package advCSQ1;
import java.util.ArrayList;

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
		ArrayList<Integer> numeratorFactorization = createFactorization(numerator);
		ArrayList<Integer> denominatorFactorization = createFactorization(denominator);
		ArrayList<Integer> temp = new ArrayList<Integer>(numeratorFactorization);
		numeratorFactorization.removeAll(denominatorFactorization);
		denominatorFactorization.removeAll(temp);
		numerator = factToInt(numeratorFactorization);
		denominator = factToInt(denominatorFactorization);
	}
	
	private int factToInt(ArrayList<Integer> list) {
		int n = 1;
		for(int x : list)
			n *= x;
		return n;
	}
	
	private ArrayList<Integer> createFactorization(int n) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		while(n > 1) {
			if(n % 2 == 0) 
				list.add(2);
			else if(n % 3 == 0)
				list.add(3);
			else
				list.add(n);
			n /= list.get(list.size() - 1);
		}
		return list;
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
	
	public Pd5ZacharyWangRationalNum multiple(Pd5ZacharyWangRationalNum other) {
		int newNumerator = numerator  * other.getNumerator();
		int newDenominator = denominator * other.getDenominator();
		return new Pd5ZacharyWangRationalNum(newNumerator, newDenominator);
	}
	
	public Pd5ZacharyWangRationalNum divide(Pd5ZacharyWangRationalNum other) {
		int newNumerator = numerator  * other.getDenominator();
		int newDenominator = denominator * other.getNumerator();
		return new Pd5ZacharyWangRationalNum(newNumerator, newDenominator);
	}
}
