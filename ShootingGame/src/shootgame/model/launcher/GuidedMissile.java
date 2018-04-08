package shootgame.model.launcher;

import java.awt.image.BufferedImage;

import shootgame.model.Game;
import shootgame.model.GameObject;
import shootgame.model.image.AfterImage;
import shootgame.model.image.GameImage;

public class GuidedMissile extends Launcher {

	private final static int DAMAGE = 50;
	private GameObject target = null;
	
	public GuidedMissile(BufferedImage img, int x, int y, int width, int height, int xSpeed, int ySpeed, GameObject owner) {
		super(img, x, y, width, height, xSpeed, ySpeed, DAMAGE, owner);
		if(Game.getInstance().getEnemyList().size()!=0) {
			int randomIndex =(int)(Math.random() * Game.getInstance().getEnemyList().size());
			target = Game.getInstance().getEnemyList().get(randomIndex);
		}
	}
	
	@Override
	public void autoMove()
	{
		if(Game.getInstance().getEnemyList().size()!=0)
		{
			if(!Game.getInstance().getEnemyList().contains(target))
			{
				int randomIndex =(int)(Math.random() * Game.getInstance().getEnemyList().size());
				if(Game.getInstance().getEnemyList().size()!=0)
					target = Game.getInstance().getEnemyList().get(randomIndex);
			}
			if(target.getPoint().x - 20 < this.point.x && target.getPoint().x + 20 > this.point.x)
				xSpeed = 0;
			else if(target.getPoint().x+20 > this.point.x)
				xSpeed = 5;
			else 
				xSpeed = -5;
		} else {
			xSpeed = 0;
		}

		moveX(xSpeed);
		moveY(ySpeed);
		notifyObserver();
	}
	
	@Override
	public void collision()
	{
		Game.getInstance().removeLauncher(this);
		Game.getInstance().getAfterImageList().add(new AfterImage(point, System.currentTimeMillis(), width, height, 25, GameImage.getInstance().getExplosionArray()));
	}
}
