import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BattleshipGame extends JPanel {
	private BattleShip enemy;
	private PlayerPanel player;
	
	public BattleshipGame() {
		player = new PlayerPanel();
		enemy = new BattleShip(player);
		setLayout(new GridLayout(2, 1));
		add(enemy);
		add(player);
	}
	public static void main(String[] args) {
		JFrame enemyframe = new JFrame("Battleship!");
		enemyframe.setSize(500, 1000);
		enemyframe.setLocation(200, 0);
		enemyframe.setContentPane(new BattleshipGame());
		enemyframe.setVisible(true);
		enemyframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
