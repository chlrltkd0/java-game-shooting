package shootgame.model.spaceship.parts;

public class Body {
	private int hp;

	public Body(int hp) {
		super();
		this.hp = hp;
	}

	public int getHp() {
		return hp;
	}

	public void minusHp(int hp) {
		this.hp -= hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
		
	}
	
}
