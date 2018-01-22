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
			x = SMA.rd.nextInt(grid.getWidth());
			y = SMA.rd.nextInt(grid.getHeight());
		} while(!grid.isEmpty(x, y));

		setPosition(x, y);

		do{
			// Force the velocity to be != than 0 on at least 1 axis.
			velocityX = SMA.rd.nextBoolean() ? SMA.rd.nextBoolean() ? 1 : 0 : -1;
			velocityY = SMA.rd.nextBoolean() ? SMA.rd.nextBoolean() ? 1 : 0 : -1;
		} while(velocityX == 0 && velocityY == 0);

	}

	public void update(){


	}

	public void decide(){

		handleCollision();

		if(canMove()){
			move();
		}
	}



	private boolean canMove() {
		// Next position inbound.
		if(getNextX() >= 0 && getNextX() < grid.getWidth() && getNextY() >= 0 && getNextY() < grid.getHeight()){
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

		// Check for grid collision and apply the correct velocity.
		if(!grid.isTorique()){

			if(getNextX() >= grid.getWidth() || getNextX() < 0){
				velocityX = -velocityX;
				color = Color.BLUE;
			}

			if(getNextY() >= grid.getHeight() || getNextY() < 0){
				velocityY = -velocityY;
				color = Color.BLUE;
			}
		}

		if(!grid.isEmpty(getNextX(), getNextY())){
			Agent collider = grid.getAgentToPos(getNextX(), getNextY());
			swapVelocity(collider);
			if(color == Color.GRAY)
				setColor(Color.RED);
			collider.setColor(color);
		}

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

	private void swapVelocity(Agent collider) {
		int colliderVelocityX = collider.getVelocityX();
		int colliderVelocityY = collider.getVelocityY();

		collider.setVelocityX(this.velocityX);
		collider.setVelocityY(this.velocityY);

		this.setVelocityX(colliderVelocityX);
		this.setVelocityY(colliderVelocityY);
	}

	private int getNextX() {
		int nextX = x + velocityX;
		if(grid.isTorique()){

			if(nextX >= grid.getWidth()){
				return 0;			
			} 
			else if (nextX < 0){
				return grid.getWidth() - 1;	
			}
		}
		return nextX;
	}

	private int getNextY() {
		int nextY = y + velocityY;

		if(grid.isTorique()){
			if(nextY >= grid.getHeight()){
				return 0;			
			} 
			else if (nextY < 0){
				return grid.getHeight() - 1;	
			}
		}
		return nextY;
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

}
