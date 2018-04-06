package shootgame.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import shootgame.model.AfterImage;
import shootgame.model.Enemy;
import shootgame.model.Game;
import shootgame.model.GameImage;
import shootgame.model.Launcher;
import shootgame.model.SpaceShip;

public class GamingPanel extends JPanel{

	private SpaceShip spaceShip;
	
	private Game game;
	private int currentView = 0;
	private int selected = 0;

	public GamingPanel()
	{
		this.game = Game.getInstance();
		this.setBackground(Color.BLACK);
		this.addKeyListener(new MyKeyListener());
		setSize(Game.WIDTH, Game.HEIGHT);
		setFocusable(true);
		
		spaceShip = new SpaceShip(GameImage.getInstance().getImgMap().get("spaceShip"), 200, 800, 50, 50);
		game.setSpaceShip(spaceShip);
		spaceShip.init();

		PaintThread paintThread = new PaintThread();
		Thread thread1 = new Thread(paintThread);
		thread1.start();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(GameImage.getInstance().getImgMap().get("spaceBackground"), 0, 100, null);
		if(currentView==0){
			g.drawImage(GameImage.getInstance().getImgMap().get("singleplay"), Game.WIDTH/4, 100, null);
			g.drawImage(GameImage.getInstance().getImgMap().get("multiplay"), Game.WIDTH/4, 300, null);
			g.drawImage(GameImage.getInstance().getImgMap().get("storage"), Game.WIDTH/4, 500, null);
			g.drawImage(GameImage.getInstance().getImgMap().get("arrow"), Game.WIDTH/4-100, selected*200+150, null);
		} else if(currentView==1) {
			try {
				//우주선 그리기
				g.drawImage(game.getSpaceShip().getImg(), game.getSpaceShip().getPoint().x, game.getSpaceShip().getPoint().y
						, game.getSpaceShip().getWidth(), game.getSpaceShip().getHeight(), null);
				//총알 그리기
				for(Launcher launcher : game.getLauncherList())
				{
					g.drawImage(launcher.getImg(), launcher.getPoint().x-launcher.getWidth()/2, launcher.getPoint().y-launcher.getHeight()/2
							, launcher.getWidth(), launcher.getHeight(), null);
				}

				//적팀 그리기
				for(Enemy enemy : game.getEnemyList())
					g.drawImage(enemy.getImg(), enemy.getPoint().x-enemy.getWidth()/2, enemy.getPoint().y-enemy.getHeight()/2
							, enemy.getWidth(), enemy.getHeight(), null);

				//잔상 그리기
				for(AfterImage img : game.getAfterImageList())
				{
					g.drawImage(img.getImgArray()[img.getIndex()], img.getPoint().x-img.getWidth()/2, img.getPoint().y-img.getHeight()/2
							, img.getWidth(), img.getHeight(), null);
				}
			} catch(Exception e) {

			}
		} else if(currentView==2) {
			g.drawImage(GameImage.getInstance().getImgMap().get("itemStorage"), 0, 100, null);
			g.drawImage(game.getSpaceShip().getImg(), 90, 220, 400, 300, null);
		}
	}

	public class PaintThread implements Runnable {

		@Override
		public void run() {
			while(true) {
				if(game.getGameEnd()==true)
					currentView = 0;
				repaint();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
		}
	}
	public class MyKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(currentView==0){

			} else if(currentView==1) {
				int speed = game.getSpaceShip().getBooster().getSpeed();
				if(e.getKeyCode()==KeyEvent.VK_UP)
					game.getSpaceShip().setySpeed(-speed);
				else if(e.getKeyCode()==KeyEvent.VK_DOWN)
					game.getSpaceShip().setySpeed(speed);
				else if(e.getKeyCode()==KeyEvent.VK_LEFT)
					game.getSpaceShip().setxSpeed(-speed);
				else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
					game.getSpaceShip().setxSpeed(speed);
				else if(e.getKeyCode()==KeyEvent.VK_SPACE)
					game.getSpaceShip().LaunchGuidedMissile();
				else if(e.getKeyCode()==KeyEvent.VK_CONTROL)
					game.getSpaceShip().LaunchNuclearMissile();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(currentView==0){
				if(e.getKeyCode()==KeyEvent.VK_UP) {
					if(selected>0)
						selected--;
				} else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					if(selected<2)
						selected++;
				} else if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					if(selected==0){
						System.out.println("실행중");
						spaceShip.init();
						game.gameStart();
						currentView = 1;
					} else if(selected==1) {
						
					} else if(selected==2) {
						currentView = 2;
					}
				}
			} else if(currentView==1) {
				int speed = game.getSpaceShip().getBooster().getSpeed();
				if(e.getKeyCode()==KeyEvent.VK_W || e.getKeyCode()==KeyEvent.VK_UP)
				{
					if(game.getSpaceShip().getySpeed()==-speed)
						game.getSpaceShip().setySpeed(0);
				}
				else if(e.getKeyCode()==KeyEvent.VK_S || e.getKeyCode()==KeyEvent.VK_DOWN)
				{
					if(game.getSpaceShip().getySpeed()==speed)
						game.getSpaceShip().setySpeed(0);
				}

				else if(e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_LEFT)
				{
					if(game.getSpaceShip().getxSpeed()==-speed)
						game.getSpaceShip().setxSpeed(0);
				}
				else if(e.getKeyCode()==KeyEvent.VK_D || e.getKeyCode()==KeyEvent.VK_RIGHT)
				{
					if(game.getSpaceShip().getxSpeed()==speed)
						game.getSpaceShip().setxSpeed(0);
				} 
			}
			
		}
	}
}
