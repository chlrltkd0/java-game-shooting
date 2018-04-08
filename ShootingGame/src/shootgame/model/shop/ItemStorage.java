package shootgame.model.shop;

import java.util.ArrayList;
import java.util.List;

import shootgame.model.spaceship.SpaceShip;
import shootgame.model.spaceship.parts.Equipment;

public class ItemStorage {
	private SpaceShip spaceShip;
	private List<Equipment> equipList = new ArrayList<Equipment>();
	private int numberOfNMissile;
	private int numberOfGMissile;
	
	public ItemStorage(SpaceShip spaceShip, List<Equipment> equipList, int numberOfNMissile, int numberOfGMissile) {
		super();
		this.spaceShip = spaceShip;
		this.equipList = equipList;
		this.numberOfNMissile = numberOfNMissile;
		this.numberOfGMissile = numberOfGMissile;
	}
}
