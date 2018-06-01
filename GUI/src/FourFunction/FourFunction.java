package FourFunction;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class FourFunction extends JPanel {
	private NumberButton[][] numbers;
	private NumberButton zero;
	private JPanel buttons;
	private JButton plus, minus, mult, div, enter;
	private JTextField disp;
	
	public FourFunction() {
		setLayout(new BorderLayout());
		disp = new JTextField(400);
		add(disp, BorderLayout.NORTH);
		buttons = new JPanel();
		buttons.setLayout(new GridLayout(4,4));
		numbers = new NumberButton[3][4];
		JButton[] operators = {new JButton("+"), new JButton("-"), new JButton("*"), new JButton("/"), new JButton("."), new JButton("0"), new JButton("+")};
		int count = 1;
		int operatorIndex = 0;
		for(int r = 0; r < numbers.length; r++) {
			for(int c = 0; c < numbers.length; c++) {
				numbers[r][c] = new NumberButton(count);
				count++;
				buttons.add(numbers[r][c]);
			}
			buttons.add(operators[operatorIndex]);
			operatorIndex++;
		}
		add(buttons, BorderLayout.CENTER);
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("panel02");
		frame.setSize(400, 450);
		frame.setLocation(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new FourFunction());
		frame.setVisible(true);
	}

}
