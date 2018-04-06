package shootgame.model;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Launcher extends GameObject implements Observee{
	
	protected List<Observer> observers = new LinkedList<>();
	protected GameObject owner;
	protected int damage;
	
	public Launcher(BufferedImage img, int x, int y, int width, int height, int xSpeed, int ySpeed, int damage, GameObject owner) {
		super(img, x, y, width, height, xSpeed, ySpeed);
		this.damage = damage;
		this.owner = owner;
		this.observers.add(Game.getInstance());
		for(int i=0; i<Game.getInstance().getEnemyList().size(); i++)
		{
			this.observers.add(Game.getInstance().getEnemyList().get(i));
		}
	}
	
	public void collision() {
		Game.getInstance().getLauncherList().remove(this);
	}
	
	public void autoMove() {
		moveY(ySpeed);  
		moveX(xSpeed);  
		notifyObserver();
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObserver() {
		for(Observer ob : observers)
		{
			ob.update(this);
		}
	}
}
