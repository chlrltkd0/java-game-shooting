package shootgame.model.parts;

import java.awt.image.BufferedImage;

import shootgame.model.Game;
import shootgame.model.GameImage;
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
		Game.getInstance().getLauncherList().add(raser);
	}

	public void stopAttack()
	{
		Game.getInstance().getLauncherList().remove(raser);
	}
}
