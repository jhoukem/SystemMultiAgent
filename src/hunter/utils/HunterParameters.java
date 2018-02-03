package hunter.utils;

import core.utils.Parameters;

public class HunterParameters extends Parameters {

	protected int avatarSpeed;
	protected int hunterSpeed;
	protected int defenderLife;
	protected float wallPercent;
	
	
	public HunterParameters(String paramPath){
		super(paramPath);
	}

	protected void setParams() {
		super.setParams();
		
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
			defenderLife = Integer.parseInt(properties.getProperty("defenderLife"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			defenderLife = 2;
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
		defenderLife = 5;
	}
	
	public int getAvatarSpeed() {
		return avatarSpeed;
	}

	public int getHunterSpeed() {
		return hunterSpeed;
	}

	public int getDefenderLife() {
		return defenderLife;
	}

	public float getWallPercent() {
		return wallPercent;
	}

}
