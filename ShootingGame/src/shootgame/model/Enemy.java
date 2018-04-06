package shootgame.model;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Enemy extends GameObject implements Observer, Observee{
	private int nextX;
	private int hp;
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
		for(int i=0; i<Game.getInstance().getLauncherList().size(); i++)
		{
			Game.getInstance().getLauncherList().get(i).addObserver(this);
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
				nextX = (int)(Math.random()*800); // 800은 맵의 넓이이기때문에 어떻게 잘 만들어줘야한다.
		}
		
		moveX(xSpeed);
		moveY(ySpeed);
		notifyObserver();
	}

	public void hitCheck(Launcher launcher) {
		// 객체가 리스트에서 삭제 되어도 Observer에 등록되있기 때문에 리스트에 있을때 생성된 총알들은 
		// hitCheck를 호출하게되서 사라진곳에도 총알이 맞게됨 그러므로 포함되지 않을경우는 return
		if(!this.game.getEnemyList().contains(this))
			return;
		
		if(launcher.getBounds().intersects(this.getBounds())) {
			this.hp -= launcher.getDamage();
			if(hp < 0)
			{
				this.game.getEnemyList().remove(this);
				this.game.getAfterImageList().add(new AfterImage(point, System.currentTimeMillis(), width, height, 25, GameImage.getInstance().getExplosionArray()));
			}

			launcher.collision();
		}
	}

	@Override
	public void update(Object arg) {
		hitCheck((Launcher)arg);

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
