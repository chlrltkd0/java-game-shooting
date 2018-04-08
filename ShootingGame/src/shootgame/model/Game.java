package shootgame.model;

import java.util.LinkedList;
import java.util.List;

import shootgame.model.launcher.Launcher;

public class Game implements Observer {
	public static final int WIDTH = 870;
	public static final int HEIGHT = 900;
	private static Game theInstance = new Game();

	private int round = 1;
	private int score = 0;

	private SpaceShip spaceShip;                                                                                                                                                                                                                                                                                              
	private List<Enemy> enemyList; // = new LinkedList<Enemy>();
	private List<Launcher> launcherList; // = new LinkedList<Launcher>();
	private List<AfterImage> afterImageList; // = new LinkedList<AfterImage>();
	private Boolean isGameEnd = true;
	
	private Thread egThread;
	private Thread aipthread;
	private Thread tethread;

	private Game() {
		super();
//		gameStart();
	}
	
	public void plusPoint(int score) {
		int nextScore = this.score + score;
		if(nextScore/1000 != this.score/1000)
			spaceShip.plusNmCount();
		else if(nextScore/100 != this.score/100)
			spaceShip.plusGmCount();
		this.score = nextScore;
	}
	
	public static Game getInstance() {
		return theInstance;
	}
	public void setGameEnd(Boolean isGameEnd) {
		this.isGameEnd = isGameEnd;
	}
	
	public void addEnemy(Enemy enemy) {
		synchronized(enemyList) {
			enemyList.add(enemy);
		}
	}
	public void addLauncher(Launcher launcher) {
		synchronized(launcherList) {
			launcherList.add(launcher);
		}
		
	}
	public void removeEnemy(Enemy enemy) {
		synchronized(enemyList) {
			enemyList.remove(enemy);
		}
	}
	public void removeLauncher(Launcher launcher) {
		synchronized(launcherList) {
			launcherList.remove(launcher);
		}
	}
	
	public void gameStart() {
		spaceShip = new SpaceShip(GameImage.getInstance().getImgMap().get("spaceShip"), 200, 800, 50, 50);
		spaceShip.init();
		
		isGameEnd = false;
		enemyList = new LinkedList<Enemy>();
		launcherList = new LinkedList<Launcher>();
		afterImageList = new LinkedList<AfterImage>();
		score = 0;

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
		synchronized(enemyList) {
			for(int i=0; i<enemyList.size(); i++)
				enemyList.get(i).autoMove();
		}

		synchronized(launcherList) {
			for(int i=0; i<launcherList.size(); i++)
				launcherList.get(i).autoMove();
		}
		spaceShip.autoMove();
		//게임끝났나 확인하는 루틴
		
		if(this.getSpaceShip().getBody().getHp()<=0)
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
	}

	public int getScore() {
		return score;
	}

	@Override
	public void update(Object obs) {
		if(obs instanceof Launcher) {
			outLauncherCheck((Launcher)obs);
		} else {
			outEnemyCheck((Enemy)obs);
		}
	}

	private void outEnemyCheck(Enemy enemy) {
		if(enemy.getPoint().y>900) {
			synchronized(enemyList) {
				enemyList.remove(enemy);
			}
		}
	}
	private void outLauncherCheck(Launcher launcher) {
		if(launcher.getPoint().y-launcher.getHeight()/2<0) {
			synchronized(launcherList) {
				launcherList.remove(launcher);
			}
		}
	}

	public boolean getGameEnd() {
		return isGameEnd;
	}

}
