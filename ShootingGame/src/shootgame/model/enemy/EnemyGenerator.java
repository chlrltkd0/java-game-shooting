package shootgame.model.enemy;

import shootgame.model.Game;
import shootgame.model.image.GameImage;

public class EnemyGenerator implements Runnable{
	
	private Game game;
	
	public EnemyGenerator(Game game) {
		super();
		this.game = game;
	}

	@Override
	public void run() {
		int enemyHp = 50;
		int enemySpeed;
		int delay = 5000;
		while(true) {

			try {
				for(int i=0; i<6; i++)
					for(int j=0; j<6; j++)
						game.addEnemy(new Enemy(game, GameImage.getInstance().getImgMap().get("enemy"), 50+i*150, 50+j*80-500, 50, 50, 0, 2, false, enemyHp));

				Thread.sleep(delay);


				for(int i=0; i<6; i++)
					for(int j=0; j<6; j++)
						game.addEnemy(new Enemy(game, GameImage.getInstance().getImgMap().get("enemy"), 125+i*150, j*80-500, 50, 50, 3, 2, true, enemyHp));

				Thread.sleep(delay);


				game.addEnemy(new Enemy(game, GameImage.getInstance().getImgMap().get("enemy2"), 200, 100, 400, 225, 3, 0, true, 2000));
				Thread.sleep(delay);
				
				delay -= 100;
				enemyHp += 10;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				return;
			}
		}
	}
}
