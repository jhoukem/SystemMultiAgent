package wator;

import java.awt.Color;

import core.model.Cell;
import core.model.Environment;

public class PredatorAgent extends WatorAgent{

	public PredatorAgent(Environment grid) {
		super(grid);
		color = Color.PINK;
		timeToLive = 10;
		timeToBreed = 10;
	}

	@Override
	public void decide() {
		color = Color.RED;

		PreyAgent prey = getPreyAround();

		if(prey != null){
			eat(prey);
		} else {
			updateRandomDirection();
			Cell destination = environment.getCell(getNextX(), getNextY());
			if(destination != null && destination.isEmpty()) {
				
				if(canBreed()){
					breedTimeCounter = 0;
					// Create a new agent to its current position.
					PredatorAgent breed = new PredatorAgent(environment);
					breed.setPosition(x, y);
					environment.addLater(breed);
				}
				// Move to the next position.
				setPosition(getNextX(), getNextY());
			}
		}
	}

	private void eat(PreyAgent prey) {
		environment.addAgentToRemove(prey);
		setPosition(prey.getX(), prey.getY());
		// Stay alive longer.
		liveTimeCounter -= 3;
	}

	private PreyAgent getPreyAround() {

		for(int i = y-1; i <= y+1; i++){
			for(int j = x-1; j <= x+1; j++){
				if(i == y && j == x){
					continue;
				}
				Cell cell = environment.getCell(j, i);
				if(cell != null && cell.getAgent() instanceof PreyAgent){
					return (PreyAgent) cell.getAgent();
				}
			}
		}
		return null;
	}
}
