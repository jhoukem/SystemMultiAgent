package wator;

import java.awt.Color;
import java.util.ArrayList;

import core.model.Cell;
import core.model.Environment;
import core.model.SMA;

public class PredatorAgent extends WatorAgent{

	ArrayList<PreyAgent> preyAround = new ArrayList<PreyAgent>();
	
	public PredatorAgent(Environment grid) {
		super(grid);
		color = Color.PINK;
		timeToLive = 10;
		timeToBreed = 10;
	}

	@Override
	public void decide() {
		color = Color.RED;

		PreyAgent prey = getRandomPreyAround();

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

	private PreyAgent getRandomPreyAround() {

		// Clear the previous iteration.
		preyAround.clear();

		for(int i = y-1; i <= y+1; i++){
			for(int j = x-1; j <= x+1; j++){
				if(i == y && j == x){
					continue;
				}
				Cell cell = environment.getCell(j, i);
				if(cell != null && cell.getAgent() instanceof PreyAgent){
					preyAround.add((PreyAgent) cell.getAgent());
				}
			}
		}
		
		if(preyAround.isEmpty()){
			return null;
		} else {
			return preyAround.get(SMA.rd.nextInt(preyAround.size()));
		}
	}
}
