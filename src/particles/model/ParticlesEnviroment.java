package particles.model;

import core.model.Environment;
import particles.utils.ParticlesParameters;

public class ParticlesEnviroment extends Environment{

	private ParticlesParameters parameters;

	public ParticlesEnviroment(ParticlesParameters parameters) {
		super(parameters);
		this.parameters = parameters;
		loadAgents();
	}

	@Override
	protected void loadAgents() {
		for(int i = 0; i < agentCount; i++){
			
			ParticleAgent agent;
			
			switch (parameters.getAgentType()) {
			case "swap": agent = new ParticleAgentSwapColor(this); break;
			default: agent = new ParticleAgent(this); break;
			}
			agent.initializeRandomPositionAndVelocity();
			add(agent);
		}
	}
	
	@Override
	public void logInfos() {
		super.logInfos();
	}

	public ParticlesParameters getParameters() {
		return parameters;
	}

}
