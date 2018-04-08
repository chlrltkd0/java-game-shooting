package shootgame.model.launcher;

import java.awt.Point;
import java.awt.image.BufferedImage;

import shootgame.model.Game;
import shootgame.model.GameObject;
import shootgame.model.image.AfterImage;
import shootgame.model.image.GameImage;

public class NuclearMissile extends Launcher {

	private final static int DAMAGE = 100;
	
	public NuclearMissile(BufferedImage img, int x, int y, int width, int height, int xSpeed, int ySpeed, GameObject owner) {
		super(img, x, y, width, height, xSpeed, ySpeed, DAMAGE, owner);
	}
	
	@Override
	public void collision()
	{
		this.width = 500;
		this.height = 500;
		this.ySpeed = -100;
		this.img = null;
		Game.getInstance().getAfterImageList().add(new AfterImage(new Point(point), System.currentTimeMillis(), width, height, 50, GameImage.getInstance().getExplosionArray()));
	}
	
}
