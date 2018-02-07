package hunter.model;

import java.awt.Color;
import java.util.ArrayList;

import core.model.Cell;

public class HunterAgent extends HunterSimulationAgent {

	private final static ArrayList<Cell> neighbors = new ArrayList<Cell>();

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

		ArrayList<Cell> cellNeighbors = environment.getNeighbors(environment.getCell(x, y));

		Cell destination = null;

		destination = getCellToAgent(cellNeighbors, environment.getPlayerAvatar().isInvulnerable());

		if(destination != null){

			if(destination.isEmpty()){
				moveToPosition(destination.getX(), destination.getY());
			} else if(destination.getAgent() instanceof AvatarAgent){
				eatAvatar(destination);
			}
		}
	}

	private Cell getCellToAgent(ArrayList<Cell> cellNeighbors, boolean furtherCell) {
		
		if(environment.getPathMap().isEmpty()){
			return null;
		}
		neighbors.clear();

		for(Cell cell : cellNeighbors){
			if(cell == null || cell.getAgent() instanceof WallAgent || environment.getPathMap().get(cell) == null){
				continue;
			}
			
			int neighborsDistance = environment.getPathMap().get(cell);
			int currentDistance = environment.getPathMap().get(environment.getCell(x, y));
			if( (furtherCell && (neighborsDistance > currentDistance)) || (!furtherCell && (neighborsDistance < currentDistance)) ){
				neighbors.add(cell);
			}
		}

		if(neighbors.isEmpty()){
			return null;
		} else {
			return neighbors.get(environment.random.nextInt(neighbors.size()));
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
