package wator;

import java.awt.Color;

import model.Grid;
import model.SMA;

public class PreyAgent extends WatorAgent{


	private static final int TIME_TO_BREED = 15;

	public PreyAgent(Grid grid) {
		super(grid);
		breedTimeCounter = 0;
		color = Color.YELLOW;
	}

	@Override
	public void decide() {

		updateRandomDirection();

		if(canMove()){
			if(canBreed()){
				// Create a new agent to its current position.
				PreyAgent breed = new PreyAgent(grid);
				breed.setPosition(x, y);
				grid.addLater(breed);
			}
			// Move to the next position.
			setPosition(getNextX(), getNextY());
		}
	}

	private boolean canMove() {
		// Next position inbound.
		if(getNextX() >= 0 && getNextX() < grid.getWidth() && getNextY() >= 0 && getNextY() < grid.getHeight()){
			// Next position free.
			if(grid.isEmpty(getNextX(), getNextY())){
				return true;
			}
		}
		return false;
	}

	private boolean canBreed() {
		return breedTimeCounter % TIME_TO_BREED == 0;
	}

	private void updateRandomDirection() {

		do{
			// Force the velocity to be != than 0 on at least 1 axis.
			velocityX = SMA.rd.nextBoolean() ? SMA.rd.nextBoolean() ? 1 : 0 : -1;
			velocityY = SMA.rd.nextBoolean() ? SMA.rd.nextBoolean() ? 1 : 0 : -1;
		} while(velocityX == 0 && velocityY == 0);

	}


}
