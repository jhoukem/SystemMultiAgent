package particles.model;

import java.awt.Color;

import core.model.Agent;
import core.model.Cell;

public class ParticleAgent extends Agent<ParticlesEnviroment>{

	// The counter for the color to change back to default.
	private int colorCounter = 0;
	// The delay (in ticks) before the color is changed back to default).
	private int colorCounterDelay;

	public ParticleAgent(ParticlesEnviroment environment) {
		super(environment);
		this.colorCounterDelay = environment.getParameters().getColorCounterDelay();
		setColor(Color.GRAY);
	}

	@Override
	public void setColor(Color color) {
		super.setColor(color);
		colorCounter = 0;
	}

	@Override
	public void update(){

		// Check the counter and increment it at the same time and set back the default color if necessary.
		if(colorCounter++ > colorCounterDelay){
			colorCounter = 0;
			setColor(Color.GRAY);
		}
	}

	@Override
	public void decide(){

		Cell destination = environment.getCell(getNextX(), getNextY());

		// Out of bound.
		if(destination == null) {	
			setColor(Color.BLUE);
			bounce();
			destination = environment.getCell(getNextX(), getNextY());
			// If the particle is not blocked.
			if(destination != null && destination.isEmpty()){
				moveToPosition(destination.getX(), destination.getY());
			}
		} else if(destination.isEmpty()) {
			moveToPosition(destination.getX(), destination.getY());
		} else {
			collideWith(destination.getAgent());
		}

	}

	protected void collideWith(Agent<?> collider) {
		swapVelocity(collider);
		setColor(Color.RED);
		collider.setColor(getColor());
	}

	private void bounce() {
		if(x == 0 || x == environment.getWidth() - 1){
			velocityX = -velocityX;
		}
		if(y == 0 || y == environment.getHeight() - 1){
			velocityY = -velocityY;
		}
	}

	protected void swapVelocity(Agent<?> collider) {
		int colliderVelocityX = collider.getVelocityX();
		int colliderVelocityY = collider.getVelocityY();

		collider.setVelocityX(velocityX);
		collider.setVelocityY(velocityY);

		this.setVelocityX(colliderVelocityX);
		this.setVelocityY(colliderVelocityY);
	}

}
