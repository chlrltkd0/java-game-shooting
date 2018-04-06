package shootgame.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class GameImage {

	public static GameImage theInstance = new GameImage();
	
	public static final int explosionImageNumber = 12;
	
	private Map<String, BufferedImage> imgMap = new HashMap<>();
	private BufferedImage[] explosionArray;

	
	private GameImage()
	{
		try {
			explosionArray = new BufferedImage[12];
			imgMap.put("raser", ImageIO.read(new File("raser.png")));
			imgMap.put("enemy", ImageIO.read(new File("enemy1.png")));
			imgMap.put("enemy2", ImageIO.read(new File("enemy2.png")));
			imgMap.put("bullet", ImageIO.read(new File("bullet.png")));
			imgMap.put("spaceShip", ImageIO.read(new File("spaceShip.png")));
			imgMap.put("guidedMissile", ImageIO.read(new File("guidedMissile.png")));
			imgMap.put("nuclearMissile", ImageIO.read(new File("nuclearMissile.png")));
			imgMap.put("singleplay", ImageIO.read(new File("singleplay.png")));
			imgMap.put("multiplay", ImageIO.read(new File("multiplay.png")));
			imgMap.put("storage", ImageIO.read(new File("storage.png")));
			imgMap.put("arrow", ImageIO.read(new File("arrow.png")));
			imgMap.put("spaceBackground", ImageIO.read(new File("spaceBackground.png")));
			imgMap.put("itemStorage", ImageIO.read(new File("itemStorage.png")));
			
			explosionArray[0] = ImageIO.read(new File("explo1.png"));			
			explosionArray[1] = ImageIO.read(new File("explo2.png"));			
			explosionArray[2] = ImageIO.read(new File("explo3.png"));			
			explosionArray[3] = ImageIO.read(new File("explo4.png"));			
			explosionArray[4] = ImageIO.read(new File("explo5.png"));			
			explosionArray[5] = ImageIO.read(new File("explo6.png"));			
			explosionArray[6] = ImageIO.read(new File("explo7.png"));
			explosionArray[7] = ImageIO.read(new File("explo8.png"));			
			explosionArray[8] = ImageIO.read(new File("explo9.png"));			
			explosionArray[9] = ImageIO.read(new File("explo10.png"));
			explosionArray[10] = ImageIO.read(new File("explo11.png"));			
			explosionArray[11] = ImageIO.read(new File("explo12.png"));			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage[] getExplosionArray() {
		return explosionArray;
	}

	public static GameImage getInstance() {
		return theInstance;
	}

	public Map<String, BufferedImage> getImgMap() {
		return imgMap;
	}

	public void setImgMap(Map<String, BufferedImage> imgMap) {
		this.imgMap = imgMap;
	}
}
