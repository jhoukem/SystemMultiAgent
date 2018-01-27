package wator;

import core.model.Agent;
import core.model.Environment;

public abstract class WatorAgent extends Agent{

	protected int breedTimeCounter;
	protected int liveTimeCounter;
	
	protected int timeToBreed = 10;
	protected int timeToLive = 13;
	
	public WatorAgent(Environment grid) {
		super(grid);
		breedTimeCounter = 0;
		liveTimeCounter = 0;
	}
	
	@Override
	public void update() {
		breedTimeCounter++;
		liveTimeCounter++;
		
		if(liveTimeCounter >= timeToLive){
			environment.addAgentToRemove(this);
		}
	}
	
	protected boolean canBreed() {
		return breedTimeCounter >= timeToBreed;
	}

}
