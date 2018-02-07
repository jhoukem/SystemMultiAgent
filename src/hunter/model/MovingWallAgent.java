package hunter.model;

import core.model.Cell;

public class MovingWallAgent extends WallAgent{


	private int moveTimer;
	private int timeToExist;
	private boolean shouldBreed = false;

	public MovingWallAgent(HunterEnvironment environment) {
		super(environment);
		timeToExist = 1000;
		moveTimer = 500;
	}

	@Override
	public void update() {
		super.update();


		if(shouldBeRemoved()){
			environment.getCell(x, y).setAgent(null);
			environment.remove(this);
		} else {
			if(shouldBreed){
				timeToExist--;
			}
		}
	}


	@Override
	public void decide() {
		super.decide();
		if(environment.getTick() % moveTimer != 0){
			return;
		}


		int lastX = x;
		int lastY = y;
		if(tryToMoveRandomly()){
			if(shouldBreed){
				MovingWallAgent movingWall = new MovingWallAgent(environment);
				movingWall.initPosition(lastX, lastY);
				environment.add(movingWall);
			}
		}
	}


	private boolean tryToMoveRandomly() {
		updateRandomDirection();

		Cell destination = environment.getCell(getNextX(), getNextY());

		if(destination != null & destination.isEmpty()){
			moveToPosition(destination.getX(), destination.getY());
			return true;
		}

		return false;
	}

	private boolean shouldBeRemoved() {
		return timeToExist <= 0;
	}

}
