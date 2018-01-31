package wator.model;

import core.model.Agent;

public abstract class WatorAgent extends Agent{

	protected int timeToBreed;
	protected int timeToLive;
	protected int timeToBreedDefault;
	
	public WatorAgent(WatorEnvironment environment) {
		super(environment);
	}
	
	@Override
	public void update() {
		
		
		timeToLive--;
		timeToBreed--;
		
		if(timeToLive <= 0){
			environment.remove(this);
		}
	}
	
	protected void breedOnCurrentPosition() {
		timeToBreed = timeToBreedDefault;
		// Create a new agent to its current position.
		WatorAgent breed = getBreedAgent();
		breed.setPosition(x, y);
		environment.add(breed);
	}
	
	protected abstract WatorAgent getBreedAgent();

	protected boolean canBreed() {
		return timeToBreed <= 0;
	}

}
