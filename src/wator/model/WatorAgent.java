package wator.model;

import core.model.Agent;
import core.model.Cell;

public abstract class WatorAgent extends Agent<WatorEnvironment> {

	protected int timeToBreed;
	protected int timeToLive;
	protected int timeToBreedDefault;
	
	public WatorAgent(WatorEnvironment environment) {
		super(environment);
		
		if(environment.isTrace()){
//			logInfos();
		}
	}
	
	@Override
	public void update() {
		
		timeToLive--;
		timeToBreed--;
		
		if(timeToLive <= 0){
			environment.remove(this);
			environment.getCell(x, y).setAgent(null);
		}
	}
	
	protected void tryToMoveRandomly() {
		int lastX = x;
		int lastY = y;
		
		updateRandomDirection();
		Cell destination = environment.getCell(getNextX(), getNextY());

		if(destination != null && destination.isEmpty()) {
			
			// Move to the next position.
			moveToPosition(destination.getX(), destination.getY());

			if(canBreed()){
				breedOnPosition(lastX, lastY);
			}
		}
	}
	
	protected void breedOnPosition(int x, int y) {
		timeToBreed = timeToBreedDefault;
		// Create a new agent to its current position.
		WatorAgent breed = getBreedAgent();
		breed.initPosition(x, y);
		environment.add(breed);
	}
	
	protected abstract WatorAgent getBreedAgent();

	protected boolean canBreed() {
		return timeToBreed <= 0;
	}
}
