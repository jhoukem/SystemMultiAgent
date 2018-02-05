package particules.utils;

import core.utils.Parameters;

public class ParticulesParameters extends Parameters {

	protected int colorCounterDelay;
	protected String agentType;
	
	public ParticulesParameters(String paramPath){
		super(paramPath);
	}

	protected void setParams() {
		super.setParams();
		try{
			colorCounterDelay = Integer.parseInt(properties.getProperty("colorCounterDelay"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			colorCounterDelay = 1;
		}
		
		agentType = properties.getProperty("agentType");
		if(agentType == null){
			agentType = "default";
		}
		
	}

	public int getColorCounterDelay() {
		return colorCounterDelay;
	}

	public String getAgentType() {
		return agentType;
	}
	
	protected void setDefaultParams() {
		super.setDefaultParams();
		colorCounterDelay = 1;
		agentType = "default";
	}

}
