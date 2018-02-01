package core.model;

import java.awt.Color;

import core.utils.Logger;

public abstract class Agent {

	private final static int velocities[] = {-1, 0, 1};

	public final int id;
	protected int x,y, velocityX, velocityY;
	protected Environment environment;
	private Color color;

	public Agent(Environment grid) {
		this.id = grid.getNextAgentId();
		this.environment = grid;
		setColor(Color.WHITE);
	}

	public abstract void update();
	public abstract void decide();

	public void initializeRandomPositionAndVelocity() {

		do {
			x = environment.random.nextInt(environment.getWidth());
			y = environment.random.nextInt(environment.getHeight());
		} while(!environment.isCellEmpty(x, y));

		initPosition(x, y);
		updateRandomDirection();
	}

	protected void updateRandomDirection() {

		do{
			// Force the velocity to be != than 0 on at least 1 axis.
			velocityX = velocities[environment.random.nextInt(velocities.length)];
			velocityY = velocities[environment.random.nextInt(velocities.length)];
		} while(velocityX == 0 && velocityY == 0);

	}

	public void logInfos() {
		Logger.log("Agent;"+ id +";"+ x +";"+ y +";"+ velocityX +";"+ velocityY);

	}

	public void invertVeclocity() {
		velocityX = -velocityX;
		velocityY = -velocityY;
	}

	public void initPosition(int nextX, int nextY) {

		// Set itself to the given position.
		Cell destination = environment.getCell(nextX, nextY);
		if(destination.getAgent() != null){
			System.err.println("Error: initial destination is not empty:\n"+destination.getAgent());
			System.exit(-1);
		}
		destination.setAgent(this);
	}

	public void moveToPosition(int nextX, int nextY) {


		// Remove itself from any previous position.
		Cell currentCell = environment.getCell(x, y);
		if(currentCell.getAgent() != this){
			System.err.println("Error: the currentCell is not occupied by the correct agent:\n"+this);
			System.err.println("other agent:\n"+currentCell.getAgent());
			System.exit(-1);
		}
		currentCell.setAgent(null);

		// Set itself to the given position.
		Cell destination = environment.getCell(nextX, nextY);
		if(destination.getAgent() != null){
			System.err.println("Error: the destination is not empty:\n"+destination.getAgent());
			System.exit(-1);
		}
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

	@Override
	public String toString() {
		return "Agent n"+id+" ["+x+","+y+"], velX="+velocityX+" velY="+velocityY;
	}
}
