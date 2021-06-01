package model;

public class Player {
	private double x;
	private double y;
	
	public Player(double posX, double posY) {
		x = posX;
		y = posY;
	}
	
	public void moveUp() {
		y = y - 5;
	}
	
	public void moveDown() {
		y = y + 5;
	}
	
	public void moveLeft() {
		x = x - 5;
	}
	
	public void moveRigth() {
		x = x + 5;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	
}
