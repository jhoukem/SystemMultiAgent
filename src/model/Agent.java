package model;

import java.awt.Color;

public class Agent {

	private int x,y, velocityX, velocityY;
	private Grid grid;
	private Color color;
	
	public Agent(Grid grid) {
		this.grid = grid;
		color = Color.GRAY;
		initializeRandomPositionAndVelocity();
	}

	private void initializeRandomPositionAndVelocity() {

		do {
			x = SMA.rd.nextInt(grid.getSize());
			y = SMA.rd.nextInt(grid.getSize());
		} while(!grid.isEmpty(x, y));

		setPosition(x, y);

		do{
			// Force the velocity to be != than 0 on at least 1 axis.
			velocityX = SMA.rd.nextInt(2);
			velocityY = SMA.rd.nextInt(2);
		} while(velocityX == 0 || velocityY == 0);
		
	}

	public void update(){


	}

	public void decide(){
		color = Color.BLUE;

		handleCollision();
		
		if(canMove()){
			move();
		}
	}



	private boolean canMove() {
		// Next position inbound.
		if(getNextX() >= 0 && getNextX() < grid.getSize() && getNextY() >= 0 && getNextY() < grid.getSize()){
			// Next position free.
			if(grid.isEmpty(getNextX(), getNextY())){
				return true;
			}
		}
		return false;
	}

	private void move() {

		// Remove itself from the current position.
		grid.setAgentToPos(null, x, y);

		// Update the position.
		x = getNextX();
		y = getNextY();
		
		// Set itself to the next position.
		grid.setAgentToPos(this, x, y);

	}

	private void handleCollision() {

		// If any collision has been detected.
		boolean collide = false;

		// Check for grid collision and apply the correct velocity.
		if(getNextX() >= grid.getSize() || getNextX() < 0){
			collide = true;
			velocityX = -velocityX;
		}

		if(getNextY() >= grid.getSize() || getNextY() < 0){
			collide = true;
			velocityY = -velocityY;
		}

		if(!grid.isEmpty(getNextX(), getNextY())){
			collide = true;
			Agent collider = grid.getAgentToPos(getNextX(), getNextY());
			collider.setColor(Color.RED);
			swapVelocity(collider);
		}

		if(collide){
			color = Color.RED;
		}
	}

	private int getNextX() {
		return x + velocityX;
	}

	private int getNextY() {
		return y + velocityY;
	}


	private void swapVelocity(Agent collider) {
		int colliderVelocityX = collider.getVelocityX();
		int colliderVelocityY = collider.getVelocityY();

		collider.setVelocityX(this.velocityX);
		collider.setVelocityY(this.velocityY);

		this.setVelocityX(colliderVelocityX);
		this.setVelocityY(colliderVelocityY);
	}

	public int getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}

	public int getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setPosition(int x2, int y2) {
		// Remove itself from any previous position.
		if(grid.getAgentToPos(x, y) == this){
			grid.setAgentToPos(null, x, y);
		}
		x = x2;
		y = y2;
		// Set itself back in the current position.
		grid.setAgentToPos(this, x, y);
	}

}
