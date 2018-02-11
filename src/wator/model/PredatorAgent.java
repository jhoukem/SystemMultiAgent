package wator.model;

import java.awt.Color;
import java.util.ArrayList;

import core.model.Cell;
import core.utils.Logger;

public class PredatorAgent extends WatorAgent{

	private ArrayList<PreyAgent> preyAround = new ArrayList<PreyAgent>();

	private int starvingTime;
	private int starvingTimeDefault;

	public PredatorAgent(WatorEnvironment environment) {
		super(environment);
		setColor(Color.PINK);
		timeToLive = environment.getParameters().getPredatorLivingTime();
		timeToBreedDefault = environment.getParameters().getPredatorBreedTime();
		timeToBreed = timeToBreedDefault;
		starvingTimeDefault = environment.getParameters().getPredatorStarvingTime();
		starvingTime = starvingTimeDefault;
	}

	@Override
	public void update() {
		// After the first update the predator is now mature.
		setColor(Color.RED);

		super.update();
		starvingTime--;
		// Remove the predator if it is starving.
		if(starvingTime <= 0){
			environment.remove(this);
			environment.getCell(x, y).setAgent(null);
		}
	}

	@Override
	public void decide() {

		if(environment.getCell(x, y).getAgent() != this){
			System.err.println("Error: the current cell is the grid does not correspond to this agent"+this);
		}
		
		if(starvingTime > 1){
			tryToMoveRandomly();
			return;
		} 
		
		PreyAgent prey = getRandomPreyAround();

		if(prey != null){

			int lastX = x;
			int lastY = y;

			eat(prey);

			if(canBreed()){
				breedOnPosition(lastX, lastY);
			}
		} else {
			tryToMoveRandomly();
		}
	}

	@Override
	protected WatorAgent getBreedAgent() {
		return new PredatorAgent(environment);
	}

	private void eat(PreyAgent prey) {

		// Remove the prey from the simulation.
		environment.remove(prey);
		environment.getCell(prey.getX(), prey.getY()).setAgent(null);

		// Move to the last prey position.
		moveToPosition(prey.getX(), prey.getY());
		// Stay alive longer.
		starvingTime += starvingTimeDefault;
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
			return preyAround.get(environment.random.nextInt(preyAround.size()));
		}
	}

	@Override
	public String toString() {
		return "Predator"+super.toString();
	}

	public void logInfos() {
		Logger.logn("PredatorAgent bird;");
	}
}
