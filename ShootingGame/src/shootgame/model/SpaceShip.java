package shootgame.model;

import java.awt.image.BufferedImage;

import shootgame.model.launcher.Bullet;
import shootgame.model.launcher.GuidedMissile;
import shootgame.model.launcher.NuclearMissile;
import shootgame.model.parts.*;

public class SpaceShip extends GameObject implements Observer{
	
	private Body body;
	private Wing wing;
	private Booster booster;
	private Weapon weapon;
	private int gmCount = 100;
	private int nmCount = 5;
	
	public SpaceShip(BufferedImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height, 0, 0);
	}
	
	public int getHP() {
		return body.getHp();
	}
	
	public void init()
	{
		this.setBody(new Body(100));
		this.setBooster(new Booster(10));
		
		setWeapon(new MachineGun(GameImage.getInstance().getImgMap().get("bullet"), "머신건", 20));
//		setWeapon(new RailGun(GameImage.getInstance().getImgMap().get("bullet"), "레일건", 20));
	}
	
	public int getGmCount() {
		return gmCount;
	}

	public void setGmCount(int gmCount) {
		this.gmCount = gmCount;
	}

	public void plusGmCount() {
		this.gmCount++;
	}
	
	public int getNmCount() {
		return nmCount;
	}

	public void setNmCount(int nmCount) {
		this.nmCount = nmCount;
	}
	
	public void plusNmCount() {
		this.nmCount++;
	}

	public void LaunchGuidedMissile() {
		if(gmCount>0) {
			gmCount--;
			GuidedMissile guidedMissile = new GuidedMissile(GameImage.getInstance().getImgMap().get("guidedMissile"), point.x + 20, point.y, 50, 60, 0, -6, this);
			Game.getInstance().addLauncher(guidedMissile);
		}
	}
	

	public void LaunchNuclearMissile() {
		if(nmCount>0) {
			nmCount--;
			NuclearMissile nuclearMissile = new NuclearMissile(GameImage.getInstance().getImgMap().get("nuclearMissile"), point.x + 20, point.y, 70, 70, 0, -4, this);
			Game.getInstance().addLauncher(nuclearMissile);
		}
	}
	
	public void autoMove() {
		int nextX = point.x + xSpeed;
		int nextY = point.y + ySpeed;
		if(nextX >= 0 && nextX <=Game.WIDTH-this.width)
			point.x = nextX;
		if(nextY >= 0 && nextY <=Game.HEIGHT-this.height)
			point.y = nextY;
		
	}
	
	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public Wing getWing() {
		return wing;
	}

	public void setWing(Wing wing) {
		this.wing = wing;
	}

	public Booster getBooster() {
		return booster;
	}

	public void setBooster(Booster booster) {
		this.booster = booster;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
		this.weapon.setSpaceShip(this);
	}

	@Override
	public void update(Object obs) {
		collisionEnemy((Enemy)obs);
	}

	private void collisionEnemy(Enemy obs) {
		if(this.getBounds().intersects(obs.getBounds()))
		{
			this.getBody().minusHp(10);
			obs.die();
		}
	}
}
