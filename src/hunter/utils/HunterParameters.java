package hunter.utils;

import core.utils.Parameters;

public class HunterParameters extends Parameters {

	protected int avatarSpeed;
	protected int hunterSpeed;
	protected int defenderLifeTime;
	protected int invulnerabilityTime;
	protected int mazeSegmentSize;
	protected float spacedAreaPercent;
	protected boolean showDijkstra;
	protected boolean movingWall;
	
	public HunterParameters(String paramPath){
		super(paramPath);
	}

	protected void setParams() {
		super.setParams();

		showDijkstra = Boolean.parseBoolean(properties.getProperty("showDijkstra"));
		movingWall = Boolean.parseBoolean(properties.getProperty("movingWall"));
		
		try{
			avatarSpeed = Integer.parseInt(properties.getProperty("avatarSpeed"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			avatarSpeed = 1;
		}
		
		try{
			hunterSpeed = Integer.parseInt(properties.getProperty("hunterSpeed"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			hunterSpeed = 2;
		}
		
		try{
			defenderLifeTime = Integer.parseInt(properties.getProperty("defenderLifeTime"));
			if(defenderLifeTime <= 0){
				defenderLifeTime = gridWidth * gridHeight;
			}
		} catch(NumberFormatException e){
			e.printStackTrace();
			defenderLifeTime = gridWidth * gridHeight;
		}
		
		try{
			invulnerabilityTime = Integer.parseInt(properties.getProperty("invulnerabilityTime"));
			if(invulnerabilityTime <= 0){
				invulnerabilityTime = (gridWidth + gridHeight)/2;
			}
		} catch(NumberFormatException e){
			e.printStackTrace();
			invulnerabilityTime = (gridWidth + gridHeight)/2;
		}
		
		try{
			spacedAreaPercent = Float.parseFloat(properties.getProperty("spacedAreaPercent"));
			spacedAreaPercent = Math.min(1f, spacedAreaPercent);
		} catch(NumberFormatException e){
			e.printStackTrace();
			spacedAreaPercent = 0.33f;
		}
		
		try{
			mazeSegmentSize = Integer.parseInt(properties.getProperty("mazeSegmentSize"));
			if(mazeSegmentSize < 2 ){
				System.err.println("Error the maze segment size must be >= 2");
				mazeSegmentSize = 2;
			}
		} catch(NumberFormatException e){
			e.printStackTrace();
			mazeSegmentSize = 2;
		}
		
		boolean defaultAgentCount = false;
		
		try{
			agentCount = Integer.parseInt(properties.getProperty("agentCount"));
			if(agentCount <= 0){
				defaultAgentCount = true;
			} else if(agentCount > gridWidth*gridHeight){
				defaultAgentCount = true;
				System.err.println("The number of agents is superior of the number of cell ( "+gridWidth * gridHeight+" agents max)");
			}
		} catch(NumberFormatException e){
			defaultAgentCount = true;
			e.printStackTrace();
		}
		
		if(defaultAgentCount){
			agentCount = (int) (gridWidth * gridHeight * 0.02f);
		}
	}
	

	@Override
	protected void setDefaultParams() {
		super.setDefaultParams();
		avatarSpeed = 1;
		hunterSpeed = 2;
		spacedAreaPercent = 0.33f;
		defenderLifeTime = (gridWidth * gridHeight);
		invulnerabilityTime = (gridWidth + gridHeight)/2;
		showDijkstra = false;
		movingWall = true;
		agentCount = 5;
		mazeSegmentSize = 2;
	}
	
	public int getAvatarSpeed() {
		return avatarSpeed;
	}

	public int getHunterSpeed() {
		return hunterSpeed;
	}

	public int getDefenderLifeTime() {
		return defenderLifeTime;
	}

	public float getSpacedAreaPercent() {
		return spacedAreaPercent;
	}

	public int getInvulnerabilityTime() {
		return invulnerabilityTime;
	}
	
	public boolean isShowDijkstra() {
		return showDijkstra;
	}
	
	public boolean isMovingWall(){
		return movingWall;
	}

	public int getMazeSegmentSize() {
		return mazeSegmentSize;
	}
}
