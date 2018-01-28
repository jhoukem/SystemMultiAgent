package wator;

import java.awt.Color;

import core.model.Cell;
import core.model.Environment;

public class PreyAgent extends WatorAgent{


	public PreyAgent(Environment grid) {
		super(grid);
		color = Color.YELLOW;
		timeToLive = 15;
		timeToBreed = 6;
	}

	@Override
	public void decide() {
		if(environment.isToDelete(this)){
			return;
		}
		color = Color.GREEN;
		updateRandomDirection();
		Cell destination = environment.getCell(getNextX(), getNextY());

		if(destination != null && destination.isEmpty()) {	

			if(canBreed()){
				breedTimeCounter = 0;
				// Create a new agent to its current position.
				PreyAgent breed = new PreyAgent(environment);
				breed.setPosition(x, y);
				environment.addLater(breed);
			}
			setPosition(getNextX(), getNextY());
		}
		
	}

}
