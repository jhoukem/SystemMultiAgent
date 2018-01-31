package particules.utils;

import core.utils.Parameters;

public class ParticulesParameters extends Parameters {

	protected int colorCounterDelay;
	
	public ParticulesParameters(String paramPath){
		super(paramPath);
	}

	protected void setParams() {
		super.setParams();
		try{
			colorCounterDelay = Integer.parseInt(properties.getProperty("colorCounterDelay"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			colorCounterDelay = 0;
		}
	}

	public int getColorCounterDelay() {
		return colorCounterDelay;
	}

	protected void setDefaultParams() {
		super.setDefaultParams();
	}

}
