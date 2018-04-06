package shootgame.model;

import java.util.LinkedList;
import java.util.List;

public class Game implements Observer {
	public static final int WIDTH = 870;
	public static final int HEIGHT = 900;
	private static Game theInstance = new Game();

	
	private SpaceShip spaceShip;
	private List<Enemy> enemyList;// = new LinkedList<Enemy>();
	private List<Launcher> launcherList;// = new LinkedList<Launcher>();
	private List<AfterImage> afterImageList;// = new LinkedList<AfterImage>();
	private Boolean isGameEnd = true;
	
	
	private Thread egThread;
	private Thread aipthread;
	private Thread tethread;
	

	private Game() {
		super();
//		gameStart();
	}
	
	public static Game getInstance() {
		return theInstance;
	}

	public void setGameEnd(Boolean isGameEnd) {
		this.isGameEnd = isGameEnd;
	}

	
	public void gameStart() {
		spaceShip.getBody().setHp(100);
		isGameEnd = false;
		enemyList = new LinkedList<Enemy>();
		launcherList = new LinkedList<Launcher>();
		afterImageList = new LinkedList<AfterImage>();

		// this를 다 세부적인것으로 바꿔도된다
		tethread = new Thread(new TimeElapse(this));
		tethread.start();
		
		egThread = new Thread(new EnemyGenerator(this));
		egThread.start();

		//잔상 처리 스레드
		aipthread = new Thread(new AfterImageProcess(this));
		aipthread.start();
		
		spaceShip.getWeapon().startAttack();
	}
	
	public void gameStop() {
		System.out.println("사망");
		egThread.interrupt();
		aipthread.interrupt();
		tethread.interrupt();
		spaceShip.getWeapon().stopAttack();
		isGameEnd = true;
//		enemyList = null;
//		launcherList = null;
//		afterImageList = null;
	}

	public List<AfterImage> getAfterImageList() {
		return afterImageList;
	}

	public void setAfterImageList(List<AfterImage> afterImage) {
		this.afterImageList = afterImage;
	}

	public List<Launcher> getLauncherList() {
		return launcherList;
	}

	public void setLauncherList(LinkedList<Launcher> launcherList) {
		this.launcherList = launcherList;
	}

	public void timeElapse()
	{
		for(int i=0; i<enemyList.size(); i++)
			enemyList.get(i).autoMove();

		for(int i=0; i<launcherList.size(); i++)
			launcherList.get(i).autoMove();
		spaceShip.autoMove();
		//게임끝났나 확인하는 루틴
		
		System.out.println(this.getSpaceShip().getBody().getHp());
		if(this.getSpaceShip().getBody().getHp()<0)
			gameStop();
	}

	public List<Enemy> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(List<Enemy> enemyList) {
		this.enemyList = enemyList;
	}

	public SpaceShip getSpaceShip() {
		return spaceShip;
	}

	public void setSpaceShip(SpaceShip spaceShip) {
		this.spaceShip = spaceShip;
		this.spaceShip.setGame(this);
	}

	@Override
	public void update(Object obs) {
		outLauncherCheck((Launcher)obs);

	}

	private void outLauncherCheck(Launcher launcher) {
		if(launcher.getPoint().y-launcher.getHeight()/2<0)
			launcherList.remove(launcher);
	}

	public boolean getGameEnd() {
		return isGameEnd;
	}

}
