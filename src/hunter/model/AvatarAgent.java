package hunter.model;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import core.model.Cell;

public class AvatarAgent extends HunterSimulationAgent {

	private static AvatarAgent avatar;
	private Queue<Cell> frontier = new LinkedList<Cell>();

	private int invulnerableCounter = 0;

	private AvatarAgent(HunterEnvironment environment) {
		super(environment);
		setColor(Color.BLUE);
		speed = environment.getParameters().getAvatarSpeed();
	}


	@Override
	public void update() {

		// Should not be updated if its pending for deletion or if the tick does not match the avatar speed.
		if(environment.isPendingForDeletion(this) || environment.getTick() % speed != 0){
			return;
		}
		
		if(invulnerableCounter > 0){
			invulnerableCounter--;
			if(invulnerableCounter == 0){
				environment.decreaseWinnerApparitionCounter();
			}
		}

	}

	@Override
	public void decide() {

		// Should not be updated if its pending for deletion or if the tick does not match the avatar speed.
		if(environment.isPendingForDeletion(this) || environment.getTick() % speed != 0){
			return;
		}

		Cell destination = environment.getCell(getNextX(), getNextY());

		if(destination != null){

			if(destination.isEmpty()){
				moveToPosition(destination.getX(), destination.getY());
				updatePathMap();
			} else if(destination.getAgent() instanceof DefenderAgent){
				environment.remove(destination.getAgent());
				destination.setAgent(null);
				invulnerableCounter = environment.getParameters().getInvulnerabilityTime();
				moveToPosition(destination.getX(), destination.getY());
				updatePathMap();
			} else if(destination.getAgent() instanceof WinnerAgent){
				environment.remove(destination.getAgent());
				destination.setAgent(null);
				moveToPosition(destination.getX(), destination.getY());
				setColor(Color.WHITE);
				environment.setStopSimulation(true);
				updatePathMap();
			}
			else{
				setVelocity(0, 0);
			}
		} else {
			setVelocity(0, 0);
		}
	}

	private void updatePathMap() {

		HashMap<Cell, Cell> pathMap = environment.getPathMap();
		pathMap.clear();
		frontier.clear();

		Cell avatarCell = environment.getCell(x, y);
		// Add the avatar's cell as the starting node.
		frontier.add(avatarCell);
		// No more cell to follow when we are on the avatar's cell.
		pathMap.put(avatarCell, null);

		while(!frontier.isEmpty()){
			Cell current = frontier.poll();

			for(Cell cell : environment.getNeighbors(current)){
				if(cell == null || cell.getAgent() instanceof WallAgent){
					continue;
				}
				if(!pathMap.containsKey(cell)){
					frontier.add(cell);
					pathMap.put(cell, current);
				}
			}
		}
	}

	public static AvatarAgent getAvatarInstance(HunterEnvironment environment){

		if(avatar == null){
			avatar = new AvatarAgent(environment);
		}
		return avatar;
	}
}
