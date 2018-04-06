package shootgame.model.launcher;

import java.awt.image.BufferedImage;

import shootgame.model.Game;
import shootgame.model.GameObject;
import shootgame.model.Launcher;

public class Raser extends Launcher {

	public static final int DAMAGE = 1;
	
	public Raser(BufferedImage img, GameObject owner) {
		super(img, owner.getPoint().x+owner.getWidth()/2, owner.getPoint().y/2, 5, owner.getPoint().y, 0, 0, DAMAGE, owner);
	}
	
	public void autoMove()
	{
		this.point.x = owner.getPoint().x+owner.getWidth()/2;
		this.point.y = owner.getPoint().y/2;
		this.height = owner.getPoint().y;
		notifyObserver();
	}
	
	@Override
	public void collision()
	{
		
	}
}
