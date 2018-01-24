package model;

import java.awt.Color;

import utils.Logger;

public abstract class Agent {

	protected int id;
	protected int x,y, velocityX, velocityY;
	protected Grid grid;
	protected Color color;
	private boolean isTrace;

	public Agent(Grid grid) {
		this.id = grid.getNextAgentId();
		this.grid = grid;
		color = Color.WHITE;
	}

	public abstract void update();
	public abstract void decide();

	public void initializeRandomPositionAndVelocity() {

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

	public void logInfos() {
		Logger.log("Agent;"+ id +";"+ x +";"+ y +";"+ velocityX +";"+ velocityY);

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

	protected int getNextX() {
		int nextX = x + velocityX;

		if(grid.isTorus()){
			if(nextX > 0){
				nextX = nextX % grid.getWidth();
			} else {
				nextX = grid.getWidth() - 1;
			}
		}

		return nextX;
	}

	protected int getNextY() {
		
		
//		index % max_size + max_size) % max_size ???
		int nextY = y + velocityY;

		if(grid.isTorus()){
			if(nextY > 0){
				nextY = nextY % grid.getHeight();
			} else {
				nextY = grid.getHeight() - 1;
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

	public boolean isTrace(){
		return isTrace;
	}

	public void setTrace(boolean trace) {
		isTrace = trace;
	}

}
