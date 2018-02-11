package wator.model;

import java.awt.Color;

import core.utils.Logger;

public class PreyAgent extends WatorAgent {

	public PreyAgent(WatorEnvironment environment) {
		super(environment);
		setColor(Color.YELLOW);
		timeToLive = environment.getParameters().getPreyLivingTime();
		timeToBreedDefault = environment.getParameters().getPreyBreedTime();
		timeToBreed = timeToBreedDefault;
	}

	@Override
	public void update() {
		// Should not be updated if its pending for deletion.
		if(environment.isPendingForDeletion(this)){
			return;
		}

		// After the first update the prey is now mature.
		setColor(Color.GREEN);
		super.update();
	}

	@Override
	public void decide() {

		// Should not be updated if its pending for deletion.
		if(environment.isPendingForDeletion(this)){
			return;
		}

		if(environment.getCell(x, y).getAgent() != this){
			System.err.println("Error: the current cell is the grid does not correspond to this agent"+this);
		}

		tryToMoveRandomly();
	}

	@Override
	protected WatorAgent getBreedAgent() {
		return new PreyAgent(environment);
	}

	@Override
	public String toString() {
		return "Prey"+super.toString()+" timeToLive="+timeToLive;
	}
	
	public void logInfos() {
		Logger.logn("PreyAgent bird;");
	}
}
