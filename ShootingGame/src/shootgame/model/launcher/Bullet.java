package shootgame.model.launcher;

import java.awt.image.BufferedImage;

import shootgame.model.Game;
import shootgame.model.SpaceShip;

public class Bullet extends Launcher{

	public Bullet(BufferedImage img, int x, int y, int width, int height, int xSpeed, int ySpeed, SpaceShip owner) {
		super(img, x, y, width, height, xSpeed, ySpeed, owner.getWeapon().getDamage(), owner);
	}
}
