package shootgame.model.spaceship.parts;

import java.awt.image.BufferedImage;

import shootgame.model.spaceship.SpaceShip;

public abstract class Weapon extends Equipment {
	
	protected SpaceShip spaceShip;
	private String name;
	private int damage;
	
	public Weapon(BufferedImage img, String name, int damage) {
		super(img);
		this.name = name;
		this.damage = damage;
	}
	
	public abstract void startAttack();
	public abstract void stopAttack();
	
	public SpaceShip getSpaceShip() {
		return spaceShip;
	}

	public void setSpaceShip(SpaceShip spaceShip) {
		this.spaceShip = spaceShip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
}
