package shootgame.model.launcher;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import shootgame.model.Game;
import shootgame.model.GameObject;
import shootgame.model.Observee;
import shootgame.model.Observer;
import shootgame.model.enemy.Enemy;

public class Launcher extends GameObject implements Observee{
	
	protected List<Observer> observers = new LinkedList<>();
	protected GameObject owner;
	protected int damage;
	
	public Launcher(BufferedImage img, int x, int y, int width, int height, int xSpeed, int ySpeed, int damage, GameObject owner) {
		super(img, x, y, width, height, xSpeed, ySpeed);
		this.damage = damage;
		this.owner = owner;
		this.observers.add(Game.getInstance());
		
		synchronized(Game.getInstance().getEnemyList()) {
			for(Enemy enemy : Game.getInstance().getEnemyList())
				addObserver(enemy);
		}
	}
	
	public void collision() {
		Game.getInstance().removeLauncher(this);
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
		synchronized(observers){
			observers.add(o);
		}
	}

	@Override
	public void removeObserver(Observer o) {
		synchronized(observers){
			observers.remove(o);
		}
	}

	@Override
	public void notifyObserver() {
		synchronized(observers){
			for(Observer ob : observers)
				ob.update(this);
		}

	}
}
