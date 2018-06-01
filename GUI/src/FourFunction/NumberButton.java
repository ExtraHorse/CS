package FourFunction;
import javax.swing.*;

public class NumberButton extends JButton {
	private int val;
	public NumberButton(int v) {
		super(v + "");
		val = v;
	}
}
