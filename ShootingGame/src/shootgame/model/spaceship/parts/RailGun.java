package shootgame.model.spaceship.parts;

import java.awt.image.BufferedImage;

import shootgame.model.Game;
import shootgame.model.image.GameImage;
import shootgame.model.launcher.Raser;

public class RailGun extends Weapon {

	private Raser raser;
	
	public RailGun(BufferedImage img, String name, int damage) {
		super(img, name, damage);
		// TODO Auto-generated constructor stub
	}
	
	public void startAttack()
	{
		this.raser = new Raser(GameImage.getInstance().getImgMap().get("raser"), this.spaceShip);
		Game.getInstance().addLauncher(raser);
	}

	public void stopAttack()
	{
		Game.getInstance().removeLauncher(raser);
	}
}
