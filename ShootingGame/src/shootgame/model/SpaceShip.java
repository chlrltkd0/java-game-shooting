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
	private Game game;
	
	public SpaceShip(BufferedImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height, 0, 0);


	}
	
	public void init()
	{
		this.setBody(new Body(100));
		this.setBooster(new Booster(10));
		
		setWeapon(new MachineGun(GameImage.getInstance().getImgMap().get("bullet"), "머신건", 20));
//		setWeapon(new RailGun(GameImage.getInstance().getImgMap().get("bullet"), "레일건", 20));
	}
	
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void LaunchGuidedMissile() {
		GuidedMissile guidedMissile = new GuidedMissile(GameImage.getInstance().getImgMap().get("guidedMissile"), point.x + 20, point.y, 50, 60, 0, -6, this);
		this.game.getLauncherList().add(guidedMissile);
	}
	

	public void LaunchNuclearMissile() {
		NuclearMissile nuclearMissile = new NuclearMissile(GameImage.getInstance().getImgMap().get("nuclearMissile"), point.x + 20, point.y, 70, 70, 0, -4, this);
		this.game.getLauncherList().add(nuclearMissile);
	}
	
	public void autoMove() {
		point.x += xSpeed;
		point.y += ySpeed;
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
			if(getBody().getHp()<0)
				this.game.getSpaceShip().setImg(null);
		}
	}
}
