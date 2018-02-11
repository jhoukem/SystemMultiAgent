package wator.model;

import java.awt.Color;

import core.model.Agent;
import core.model.Environment;
import core.utils.Logger;
import wator.utils.WatorParameters;

public class WatorEnvironment extends Environment{

	private WatorParameters parameters;
	private Color backgroundColor = new Color(28,107,160);
	private int predatorCount = 0;
	private int preyCount = 0;
	
	public WatorEnvironment(WatorParameters parameters) {
		super(parameters);
		this.parameters = parameters;
		loadAgents();
	}

	@Override
	protected void loadAgents() {

		for(int i = 0; i < agentCount; i++){
			WatorAgent agent;

			if(random.nextFloat() < parameters.getPreyPercentage()){
				agent = new PreyAgent(this);
			} else {
				agent = new PredatorAgent(this);
			}
			agent.initializeRandomPositionAndVelocity();
			add(agent);
		}
	}

	@Override
	public void logInfos() {
		super.logInfos();
		preyCount = 0;
		predatorCount = 0;
		for(Agent<?> agent : agents){
			if(agent instanceof PreyAgent){
				preyCount++;
			} else {
				predatorCount++;
			}
		}
		
		Logger.log(predatorCount+";");
		Logger.logn(preyCount);
	}
	
	public WatorParameters getParameters() {
		return parameters;
	}
	
	@Override
	public Color getBackgroundColor() {
		return backgroundColor;
	}
}
