package hunter.model;

import java.awt.Color;

import core.model.Cell;

public class AvatarAgent extends HunterSimulationAgent {

	private static AvatarAgent avatar;
	
	private AvatarAgent(HunterEnvironment environment) {
		super(environment);
		setColor(Color.BLUE);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void decide() {
		
		Cell destination = environment.getCell(getNextX(), getNextY());
		
		if(destination != null && destination.isEmpty()){
			moveToPosition(destination.getX(), destination.getY());
			updatePathMap();
		} else {
			setVelocity(0, 0);
		}
	}
	
	private void updatePathMap() {
		
		
	}

	public static AvatarAgent getAvatarInstance(HunterEnvironment environment){
		
		if(avatar == null){
			avatar = new AvatarAgent(environment);
		}
		return avatar;
	}
	

}
