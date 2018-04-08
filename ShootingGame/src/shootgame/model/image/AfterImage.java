package shootgame.model.image;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class AfterImage {
	private Point point;
	private long lastRenewTime;
	private int width, height;
	private BufferedImage[] imgArray;
	private int index = 0;
	private int gap;
	
	public AfterImage(Point point, long lastRenewTime, int width, int height, int gap, BufferedImage[] imgArray) {
		super();
		this.point = point;
		this.lastRenewTime = lastRenewTime;
		this.width = width;
		this.height = height;
		this.imgArray = imgArray;
		this.index = 0;
		this.gap = gap;
	}

	public int getGap() {
		return gap;
	}

	public void setGap(int gap) {
		this.gap = gap;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public long getLastRenewTime() {
		return lastRenewTime;
	}

	public void setLastRenewTime(long lastRenewTime) {
		this.lastRenewTime = lastRenewTime;
	}

	public BufferedImage[] getImgArray() {
		return imgArray;
	}

	public void setImgArray(BufferedImage[] imgArray) {
		this.imgArray = imgArray;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void plusIndex() {
		this.index++;
	}

	public int nextIndex() {
		return index+1;
		
	}
	
}
