package wator;

import model.Agent;
import model.Grid;

public abstract class WatorAgent extends Agent{

	private static final int LIFE_TIME_DEFAULT_COUNTER = 15;
	protected int breedTimeCounter;
	protected int liveTimeCounter;
	
	public WatorAgent(Grid grid) {
		super(grid);
		breedTimeCounter = 0;
		liveTimeCounter = 0;
	}
	
	@Override
	public void update() {
		breedTimeCounter++;
		liveTimeCounter++;
		
		if(liveTimeCounter % LIFE_TIME_DEFAULT_COUNTER == 0){
			grid.addAgentToRemove(this);
		}
	}
	

}
