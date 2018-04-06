package shootgame.model;

import java.util.List;

public class AfterImageProcess implements Runnable {

	private Game game;
	
	public AfterImageProcess(Game game) {
		super();
		this.game = game;
	}

	@Override
	public void run() {
		
		List<AfterImage> afterImageList = game.getAfterImageList();
		
		while(true)
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				return;
			}
			for(int i=0; i<afterImageList.size(); i++)
			{
				AfterImage aimg = afterImageList.get(i);
				if(System.currentTimeMillis()-aimg.getLastRenewTime()>aimg.getGap())
				{
					aimg.setLastRenewTime(System.currentTimeMillis());
					
					if(aimg.nextIndex()>GameImage.explosionImageNumber)
						afterImageList.remove(aimg);
					else
						aimg.plusIndex();
				}
			}
		}
	}
}
