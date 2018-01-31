package wator.model;

import java.awt.Color;

import core.model.Environment;
import wator.utils.WatorParameters;

public class WatorEnvironment extends Environment{

	private WatorParameters parameters;
	private Color backgroundColor = new Color(28,107,160);
	
	
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

	public WatorParameters getParameters() {
		return parameters;
	}
	
	@Override
	public Color getBackgroundColor() {
		return backgroundColor;
	}

}
