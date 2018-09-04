package FourFunction;
import java.awt.*;

import javax.swing.*;

public class NumberButton extends JButton {
	private int val;
	public NumberButton(int v) {
		super(v + "");
		val = v;
		setMinimumSize(new Dimension(0,0));
	}
	
	public int getVal() {return val; }
}
