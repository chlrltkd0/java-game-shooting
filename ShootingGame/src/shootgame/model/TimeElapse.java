package shootgame.model;

public class TimeElapse implements Runnable {
	
	private Game game;
	
	public TimeElapse(Game game) {
		super();
		this.game = game;
	}

	@Override
	public void run() {
		
		while(true)
		{
			this.game.timeElapse();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				return;
			}
			
		}
	}

}
