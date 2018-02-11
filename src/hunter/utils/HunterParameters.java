package hunter.utils;

import core.utils.Parameters;

public class HunterParameters extends Parameters {

	protected int avatarSpeed;
	protected int hunterSpeed;
	protected int defenderLifeTime;
	protected int invulnerabilityTime;
	protected float wallPercent;
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
		} catch(NumberFormatException e){
			e.printStackTrace();
			defenderLifeTime = (gridWidth * gridHeight)/2;
		}
		
		try{
			invulnerabilityTime = Integer.parseInt(properties.getProperty("invulnerabilityTime"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			invulnerabilityTime = 10;
		}
		
		try{
			wallPercent = Float.parseFloat(properties.getProperty("wallPercent"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			wallPercent = 0.2f;
		}
	}
	
	@Override
	protected void setDefaultParams() {
		super.setDefaultParams();
		avatarSpeed = 1;
		hunterSpeed = 2;
		wallPercent = 0.2f;
		defenderLifeTime = (gridWidth * gridHeight)/2;
		invulnerabilityTime = 10;
		showDijkstra = false;
		movingWall = true;
		agentCount = 5;
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

	public float getWallPercent() {
		return wallPercent;
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

}
