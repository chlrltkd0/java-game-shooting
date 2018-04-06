package shootgame.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import shootgame.model.Game;

public class GameFrame extends JFrame {
	
	private GamingPanel gamingPanel;
	
	public GameFrame() {
		gamingPanel = new GamingPanel();
		
		this.setSize(Game.WIDTH, Game.WIDTH+30);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(gamingPanel);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		GameFrame gf = new GameFrame();

	}
}
