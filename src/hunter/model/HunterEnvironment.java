package hunter.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import core.model.Agent;
import core.model.Cell;
import core.model.Environment;
import hunter.utils.HunterParameters;

public class HunterEnvironment extends Environment {

	private HunterParameters parameters;
	private AvatarAgent playerAvatar;
	private Color backgroundColor = new Color(205,133,63);

	private final ArrayList<Cell> neighbors = new ArrayList<Cell>();
	private final HashMap<Cell, Cell> cameFrom = new HashMap<Cell, Cell>();
	private int winnerApparitionCounter = 4;

	private int defenderApparitionCounter;


	public HunterEnvironment(HunterParameters parameters) {
		super(parameters);
		this.parameters = parameters;
		resetDefenderApparitionCounter();
		loadAgents();
	}


	public void resetDefenderApparitionCounter(){
		defenderApparitionCounter = 250 + random.nextInt(251);
	}

	@Override
	protected void loadAgents() {

		playerAvatar = AvatarAgent.getAvatarInstance(this);
		playerAvatar.initPosition(getWidth()/2, getHeight()/2);
		add(playerAvatar);

		for(int i = 0; i < agentCount; i++){
			HunterAgent agent = new HunterAgent(this);
			agent.initializeRandomPositionAndVelocity();
			add(agent);
		}

		int wallCount = (int)(parameters.getWallPercent() * (getWidth()*getHeight()));

		for(int i = 0; i < wallCount; i++){
			WallAgent agent = new WallAgent(this);
			agent.initializeRandomPositionAndVelocity();
			add(agent);
		}

	}

	@Override
	public void processPendingOperations() {
		updateGameplay();
		super.processPendingOperations();
	}

	private void updateGameplay() {

		if(winnerApparitionCounter == 0){
			winnerApparitionCounter = -1;
			WinnerAgent winnerAgent = new WinnerAgent(this);
			winnerAgent.initializeRandomPositionAndVelocity();
			add(winnerAgent);
		} else {

			if(tick % defenderApparitionCounter == 0){
				resetDefenderApparitionCounter();

				// Create the new defender agent.
				DefenderAgent defAgent = new DefenderAgent(this);
				defAgent.initializeRandomPositionAndVelocity();
				add(defAgent);
			}
		}
	}

	public ArrayList<Cell> getNeighbors(Cell cell) {
		neighbors.clear();

		neighbors.add(getCell(cell.getX()+1, cell.getY()));
		neighbors.add(getCell(cell.getX()-1, cell.getY()));
		neighbors.add(getCell(cell.getX(), cell.getY()+1));
		neighbors.add(getCell(cell.getX(), cell.getY()-1));

		return neighbors;
	}

	public void increaseHunterSpeed() {
		for(Agent<?> agent: agents){
			if(agent instanceof HunterAgent){
				HunterAgent hunterAgent = (HunterAgent) agent;
				hunterAgent.increaseSpeed();
			}
		}
	}

	public void decreaseHunterSpeed() {
		for(Agent<?> agent: agents){
			if(agent instanceof HunterAgent){
				HunterAgent hunterAgent = (HunterAgent) agent;
				hunterAgent.decreaseSpeed();
			}
		}
	}

	public void reset() {
		setStopSimulation(false);

		agents.clear();
		cameFrom.clear();
		playerAvatar.setVelocity(0, 0);
		resetDefenderApparitionCounter();
		winnerApparitionCounter = 4;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j].setAgent(null);
			}
		}

		loadAgents();
	}

	public HunterParameters getParameters() {
		return parameters;
	}

	public AvatarAgent getPlayerAvatar() {
		return playerAvatar;
	}

	@Override
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public Cell getCellPathToAvatarFrom(int x, int y) {
		return cameFrom.get(getCell(x, y));
	}

	public int getWinnerApparitionCounter() {
		return winnerApparitionCounter;
	}

	public void setWinnerApparitionCounter(int winnerApparitionCounter) {
		this.winnerApparitionCounter = winnerApparitionCounter;
	}

	public int getDefenderApparitionCounter() {
		return defenderApparitionCounter;
	}

	public void setDefenderApparitionCounter(int defenderApparitionCounter) {
		this.defenderApparitionCounter = defenderApparitionCounter;
	}

	public HashMap<Cell, Cell> getPathMap() {
		return cameFrom;
	}

	public void decreaseWinnerApparitionCounter() {
		if(winnerApparitionCounter > 0){
			winnerApparitionCounter --;
		}
	}
}