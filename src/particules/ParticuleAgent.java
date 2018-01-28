package particules;

import java.awt.Color;

import core.model.Agent;
import core.model.Cell;
import core.model.Environment;

public class ParticuleAgent extends Agent{

	int colorCounter = 0;
	int colorCounterDelay = 5;
	
	public ParticuleAgent(Environment grid) {
		super(grid);
		color = Color.GRAY;
	}

	@Override
	public void update(){
		
		colorCounter++;

		// Set back the default color.
		if(colorCounter > colorCounterDelay){
			colorCounter = 0;
			color = Color.GRAY;
		}
		
	}

	@Override
	public void decide(){
		
		Cell destination = environment.getCell(getNextX(), getNextY());

		// Out of bound.
		if(destination == null) {	
			color = Color.BLUE;
			bounce();
			destination = environment.getCell(getNextX(), getNextY());
			// Blocked particle.
			if(destination == null){
				return;
			}
			if(destination.isEmpty()){
				setPosition(destination.getX(), destination.getY());
			}
		} else if(destination.isEmpty()) {
			setPosition(destination.getX(), destination.getY());
		} else {
			Agent collider = destination.getAgent();
			swapVelocity(collider);
			setColor(Color.RED);
			collider.setColor(color);
		}
		
	}

	private void bounce() {
		if(x == 0 || x == environment.getWidth() - 1){
			velocityX = -velocityX;
		}
		if(y == 0 || y == environment.getHeight() - 1){
			velocityY = -velocityY;
		}
	}

	private void swapVelocity(Agent collider) {
		int colliderVelocityX = collider.getVelocityX();
		int colliderVelocityY = collider.getVelocityY();

		collider.setVelocityX(velocityX);
		collider.setVelocityY(velocityY);

		this.setVelocityX(colliderVelocityX);
		this.setVelocityY(colliderVelocityY);
	}

}
