import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BattleshipGame extends JPanel {
	private JPanel enemy;
	private JPanel player;
	
	public BattleshipGame() {
		enemy = new BattleShip();
		player = new PlayerPanel();
		setLayout(new GridLayout(2, 1));
		add(enemy);
		add(player);
	}
	public static void main(String[] args) {
		JFrame enemyframe = new JFrame("Battleship!");
		enemyframe.setSize(800, 1000);
		enemyframe.setLocation(200, 0);
		enemyframe.setContentPane(new BattleshipGame());
		enemyframe.setVisible(true);
	}
}
