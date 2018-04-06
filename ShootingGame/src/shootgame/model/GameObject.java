package shootgame.model;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class GameObject {
	protected BufferedImage img;
	protected Point point;
	protected int width = 10;
	protected int height = 10;
	protected int xSpeed;
	protected int ySpeed;
	//
	
	public GameObject(BufferedImage img, int x, int y, int width, int height, int xSpeed, int ySpeed) {
		super();
		this.img = img;
		this.point = new Point(x, y);
		this.width = width;
		this.height = height;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public Point getPoint() {
		return point;
	}
	
	public void moveX(int distance) {
		point.x += distance;
	}
	
	public void moveY(int distance) {
		point.y += distance;
	}

	public void setLocation(Point point) {
		this.point.setLocation(point);
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Rectangle getBounds()
	{
		return new Rectangle(point.x-width/2, point.y-height/2, width, height);
	}

	public int getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
}
