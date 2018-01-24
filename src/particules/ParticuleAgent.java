package particules;

import java.awt.Color;

import model.Agent;
import model.Grid;

public class ParticuleAgent extends Agent{


	public ParticuleAgent(Grid grid) {
		super(grid);
	}

	@Override
	public void update(){}

	@Override
	public void decide(){

		handleCollision();

		if(canMove()){
			setPosition(getNextX(), getNextY());
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

	private void handleCollision() {

		// Check for grid collision and apply the correct velocity.
		if(!grid.isTorus()){

			if(getNextX() >= grid.getWidth() || getNextX() < 0){
				velocityX = -velocityX;
				color = Color.BLUE;
				if(isTrace()){
					logInfos();
				}
			}

			if(getNextY() >= grid.getHeight() || getNextY() < 0){
				velocityY = -velocityY;
				color = Color.BLUE;
				if(isTrace()){
					logInfos();
				}
			}
		}

		if(!grid.isEmpty(getNextX(), getNextY())){
			Agent collider = grid.getAgentToPos(getNextX(), getNextY());
			swapVelocity(collider);
			setColor(Color.RED);
			collider.setColor(color);
			if(isTrace()){
				logInfos();
				collider.logInfos();
			}
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

}
