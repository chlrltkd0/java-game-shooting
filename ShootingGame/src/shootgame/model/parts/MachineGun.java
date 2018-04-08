package shootgame.model.parts;

import java.awt.image.BufferedImage;

import shootgame.model.Game;
import shootgame.model.GameImage;
import shootgame.model.SpaceShip;
import shootgame.model.launcher.Bullet;

public class MachineGun extends Weapon{

	private SpaceShip spaceShip;
	private Thread thread1;
	
	public SpaceShip getSpaceShip() {
		return spaceShip;
	}

	public void setSpaceShip(SpaceShip spaceShip) {
		this.spaceShip = spaceShip;
	}

	public MachineGun(BufferedImage img, String name, int damage) {
		super(img, name, damage);
		// TODO Auto-generated constructor stub
	}
	
	public void startAttack() {
		AttackThread athread = new AttackThread();
		thread1 = new Thread(athread);
		thread1.start();
	}
	
	public void stopAttack() {
		thread1.interrupt();
	}
	
	public void shootBullet()
	{
		Bullet bullet1 = new Bullet(GameImage.getInstance().getImgMap().get("bullet"), spaceShip.getPoint().x, spaceShip.getPoint().y, 10, 20, 0, -10, spaceShip);
		Bullet bullet2 = new Bullet(GameImage.getInstance().getImgMap().get("bullet"), spaceShip.getPoint().x+spaceShip.getWidth()-10, spaceShip.getPoint().y, 10, 20, 0, -10, spaceShip);
		Game.getInstance().addLauncher(bullet1);
		Game.getInstance().addLauncher(bullet2);
	}
	
	class AttackThread implements Runnable {

		@Override
		public void run() {
			while(true)
			{
				shootBullet();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

}
