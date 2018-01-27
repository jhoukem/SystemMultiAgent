package core.model;

import java.awt.Color;

import core.utils.Logger;

public abstract class Agent {

	protected int id;
	protected int x,y, velocityX, velocityY;
	protected Environment environment;
	protected Color color;
	private boolean isTrace;

	public Agent(Environment grid) {
		this.id = grid.getNextAgentId();
		this.environment = grid;
		color = Color.WHITE;
	}

	public abstract void update();
	public abstract void decide();

	public void initializeRandomPositionAndVelocity() {

		do {
			x = SMA.rd.nextInt(environment.getWidth());
			y = SMA.rd.nextInt(environment.getHeight());
		} while(!environment.isEmpty(x, y));

		setPosition(x, y);
		updateRandomDirection();
	}

	protected void updateRandomDirection() {

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

		Cell currentCell = environment.getCell(x, y);

		// Remove itself from any previous position.
		if(currentCell.getAgent() == this){
			currentCell.setAgent(null);
		}

		// Set itself back in the current position.
		Cell destination = environment.getCell(x2, y2);
		destination.setAgent(this);
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	protected int getNextX() {
		return x + velocityX;
	}

	protected int getNextY() {
		return y + velocityY;
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
	
	@Override
	public String toString() {
		return "Agent n"+id+" ["+x+","+y+"], velX="+velocityX+" velY="+velocityY;
	}
}
