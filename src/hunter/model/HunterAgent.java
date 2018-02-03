package hunter.model;

import java.awt.Color;

import core.model.Cell;

public class HunterAgent extends HunterSimulationAgent {

	public HunterAgent(HunterEnvironment environment) {
		super(environment);
		setColor(Color.RED);
	}

	@Override
	public void update() {

	}

	@Override
	public void decide() {

		Cell destination = environment.getCellPathToAvatar(this);

		if(destination != null){

			if(destination.isEmpty()){
				moveToPosition(destination.getX(), destination.getY());
			} else if(destination.getAgent() instanceof AvatarAgent){
				environment.setGameOver(true);
				moveToPosition(destination.getX(), destination.getY());
			}
		}
	}
	
}
