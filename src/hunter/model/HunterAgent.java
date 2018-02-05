package hunter.model;

import java.awt.Color;

import core.model.Cell;

public class HunterAgent extends HunterSimulationAgent {

	public HunterAgent(HunterEnvironment environment) {
		super(environment);
		setColor(Color.RED);
		speed = environment.getParameters().getHunterSpeed();
	}

	@Override
	public void decide() {
		if(environment.getTick() % speed != 0){
			return;
		}

		Cell destination = environment.getCellPathToAvatarFrom(x, y);

		if(destination != null){

			if(destination.isEmpty()){
				moveToPosition(destination.getX(), destination.getY());
			} else if(destination.getAgent() instanceof AvatarAgent){
				eatAvatar(destination);
			}
		}
	}

	private void eatAvatar(Cell avatarCell) {
		this.setColor(Color.YELLOW);
		environment.remove(avatarCell.getAgent());
		avatarCell.setAgent(null);
		moveToPosition(avatarCell.getX(), avatarCell.getY());
		environment.setStopSimulation(true);
	}

}
