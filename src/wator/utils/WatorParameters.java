package wator.utils;

import core.utils.Parameters;

public class WatorParameters extends Parameters {

	protected float preyPercentage;
	protected int preyLivingTime;
	protected int preyBreedTime;
	protected int predatorLivingTime;
	protected int predatorBreedTime;
	protected int predatorStarvingTime;
	
	
	public WatorParameters(String paramPath){
		super(paramPath);
	}

	protected void setParams() {
		super.setParams();
		
		try{
			preyPercentage = Float.parseFloat(properties.getProperty("preyPercentage"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			preyPercentage = 0.7f;
		}
		
		try{
			preyLivingTime = Integer.parseInt(properties.getProperty("preyLivingTime"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			preyLivingTime = 15;
		}
		
		try{
			preyBreedTime = Integer.parseInt(properties.getProperty("preyBreedTime"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			preyBreedTime = 5;
		}
		
		try{
			predatorLivingTime = Integer.parseInt(properties.getProperty("predatorLivingTime"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			predatorLivingTime = 10;
		}
		
		try{
			predatorBreedTime = Integer.parseInt(properties.getProperty("predatorBreedTime"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			predatorBreedTime = 7;
		}
		
		try{
			predatorStarvingTime = Integer.parseInt(properties.getProperty("predatorStarvingTime"));
		} catch(NumberFormatException e){
			e.printStackTrace();
			predatorStarvingTime = 3;
		}
	}
	
	public float getPreyPercentage() {
		return preyPercentage;
	}

	public int getPreyLivingTime() {
		return preyLivingTime;
	}

	public int getPreyBreedTime() {
		return preyBreedTime;
	}

	public int getPredatorLivingTime() {
		return predatorLivingTime;
	}

	public int getPredatorBreedTime() {
		return predatorBreedTime;
	}

	public int getPredatorStarvingTime() {
		return predatorStarvingTime;
	}

	@Override
	protected void setDefaultParams() {
		super.setDefaultParams();
		preyLivingTime = 15;
		preyBreedTime = 5;
		predatorLivingTime = 10;
		predatorBreedTime = 7;
		predatorStarvingTime = 3;
		preyPercentage = 0.7f;
	}

}
