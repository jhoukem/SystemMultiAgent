package wator.model;

import java.awt.Color;
import java.util.ArrayList;

import core.model.Cell;

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
		}
	}

	@Override
	public void decide() {

		PreyAgent prey = getRandomPreyAround();

		if(prey != null){
			if(canBreed()){
				breedOnCurrentPosition();
			}
			eat(prey);
		} else {
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
	}

	@Override
	protected WatorAgent getBreedAgent() {
		return new PredatorAgent((WatorEnvironment)environment);
	}

	private void eat(PreyAgent prey) {
		environment.remove(prey);
		setPosition(prey.getX(), prey.getY());

		// Stay alive longer.
		starvingTime = starvingTimeDefault;
//		timeToLive += 3;
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

}
