package shootgame.model.enemy;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import shootgame.model.Game;
import shootgame.model.GameObject;
import shootgame.model.Observee;
import shootgame.model.Observer;
import shootgame.model.image.AfterImage;
import shootgame.model.image.GameImage;
import shootgame.model.launcher.Launcher;

public class Enemy extends GameObject implements Observer, Observee{
	private int nextX;
	private int hp;
	private int score = 100;
	private boolean autoMove = false;
	protected List<Observer> observers = new LinkedList<>();
	private Game game;

	public Enemy(Game game, BufferedImage img, int x, int y, int width, int height, int xSpeed, int ySpeed, boolean autoMove, int hp) {
		super(img, x, y, width, height, xSpeed, ySpeed);
		this.game = game;
		this.nextX = 600;
		this.autoMove = autoMove;
		this.hp = hp;
		addObserver(game.getSpaceShip());
		addObserver(game);
		synchronized(Game.getInstance().getLauncherList()) {
			for(Launcher launcher : Game.getInstance().getLauncherList()){
				launcher.addObserver(this);
			}
		}
	}

	public void autoMove()
	{
		if(autoMove==true)
		{
			if(point.x < nextX)
				xSpeed = 3;
			else
				xSpeed = -3;
			
			if(point.x >= nextX-10 && point.x <= nextX+10)
				nextX = (int)(Math.random()*Game.WIDTH); // 800�� ���� �����̱⶧���� ��� �� ���������Ѵ�.
		}
		
		moveX(xSpeed);
		moveY(ySpeed);
		notifyObserver();
	}

	public void hitCheck(Launcher launcher) {
		// ��ü�� ����Ʈ���� ���� �Ǿ Observer�� ��ϵ��ֱ� ������ ����Ʈ�� ������ ������ �Ѿ˵��� 
		// hitCheck�� ȣ���ϰԵǼ� ����������� �Ѿ��� �°Ե� �׷��Ƿ� ���Ե��� �������� return
		if(!this.game.getEnemyList().contains(this))
			return;
		
		if(launcher.getBounds().intersects(this.getBounds())) {
			this.hp -= launcher.getDamage();
			if(hp <= 0)
				die();
			launcher.collision();
		}
	}
	
	public void die() {
		this.game.plusPoint(score);
		this.game.removeEnemy(this);
		this.game.getAfterImageList().add(new AfterImage(point, System.currentTimeMillis(), width, height, 25, GameImage.getInstance().getExplosionArray()));
	}

	@Override
	public void update(Object arg) {
		hitCheck((Launcher)arg);

	}

	@Override
	public void addObserver(Observer o) {
		synchronized(observers) {
			observers.add(o);
		}
	}

	@Override
	public void removeObserver(Observer o) {
		synchronized(observers) {
			observers.remove(o);
		}
	}

	@Override
	public void notifyObserver() {
		synchronized(observers) {
			for(Observer ob : observers)
				ob.update(this);
		}

	}
}
