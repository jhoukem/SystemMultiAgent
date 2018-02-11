package hunter.model;

import core.model.Cell;

public class MovingWallAgent extends WallAgent{


	private int moveTimer;

	public MovingWallAgent(HunterEnvironment environment) {
		super(environment);
		moveTimer = 300;
	}

	@Override
	public void decide() {
		super.decide();
		if(environment.getTick() % moveTimer != 0){
			return;
		}

		tryToMoveRandomly();
	}


	private boolean tryToMoveRandomly() {
		updateRandomDirection();

		Cell destination = environment.getCell(getNextX(), getNextY());

		if(destination != null && destination.isEmpty()){
			moveToPosition(destination.getX(), destination.getY());
			return true;
		}

		return false;
	}
}
