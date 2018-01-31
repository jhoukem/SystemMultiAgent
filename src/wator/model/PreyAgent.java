package wator.model;

import java.awt.Color;

import core.model.Cell;

public class PreyAgent extends WatorAgent{


	public PreyAgent(WatorEnvironment environment) {
		super(environment);
		setColor(Color.YELLOW);
		timeToLive = environment.getParameters().getPreyLivingTime();
		timeToBreedDefault = environment.getParameters().getPreyBreedTime();
		timeToBreed = timeToBreedDefault;
	}

	@Override
	public void update() {
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
		}

		updateRandomDirection();
		Cell destination = environment.getCell(getNextX(), getNextY());

		if(destination != null && destination.isEmpty()) {	
			if(canBreed()){
				breedOnCurrentPosition();
			}
			// Move to the next position.
			setPosition(destination.getX(), destination.getY());
		}
	}

	@Override
	protected WatorAgent getBreedAgent() {
		return new PreyAgent((WatorEnvironment) environment);
	}

}
