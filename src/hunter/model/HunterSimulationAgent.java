package hunter.model;

import core.model.Agent;

public abstract class HunterSimulationAgent extends Agent<HunterEnvironment>{

	protected int speed;
	
	public HunterSimulationAgent(HunterEnvironment environment) {
		super(environment);
	}
	
	@Override
	public void update() {

	}
	
	public void decreaseSpeed() {
		speed *= 2;
	}

	public void increaseSpeed() {
		if(speed > 1){
			speed /= 2;
		}
	}
	
}
