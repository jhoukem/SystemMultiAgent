package hunter.model;

import java.awt.Color;

import core.model.Cell;
import core.model.Environment;
import hunter.utils.HunterParameters;

public class HunterEnvironment extends Environment {

	private HunterParameters parameters;
	private AvatarAgent playerAvatar;
	private Color backgroundColor = new Color(205,133,63);
	private boolean gameOver;
	
	public HunterEnvironment(HunterParameters parameters) {
		super(parameters);
		this.parameters = parameters;
		loadAgents();
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

	public HunterParameters getParameters() {
		return parameters;
	}
	
	public AvatarAgent getPlayerAvatar() {
		return playerAvatar;
	}

	public void setGameOver(boolean isOver) {
		this.gameOver = isOver;
	}
	
	@Override
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public Cell getCellPathToAvatar(HunterAgent agent) {
		
		return null;
	}

}
