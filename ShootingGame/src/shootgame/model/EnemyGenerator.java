package shootgame.model;

public class EnemyGenerator implements Runnable{
	
	private Game game;
	
	public EnemyGenerator(Game game) {
		super();
		this.game = game;
	}

	@Override
	public void run() {
		while(true) {

			try {
				for(int i=0; i<6; i++)
					for(int j=0; j<6; j++)
						game.getEnemyList().add(new Enemy(game, GameImage.getInstance().getImgMap().get("enemy"), 50+i*150, 50+j*80, 50, 50, 0, 0, false, 50));

				Thread.sleep(10000);


				for(int i=0; i<6; i++)
					for(int j=0; j<6; j++)
						game.getEnemyList().add(new Enemy(game, GameImage.getInstance().getImgMap().get("enemy"), 50+i*150, j*80-500, 50, 50, 0, 1, false, 50));

				Thread.sleep(10000);


				game.getEnemyList().add(new Enemy(game, GameImage.getInstance().getImgMap().get("enemy2"), 200, 300, 400, 225, 3, 0, true, 2000));


				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				return;
			}
		}
	}
}
