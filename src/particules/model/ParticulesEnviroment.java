package particules.model;

import core.model.Environment;
import particules.utils.ParticulesParameters;

public class ParticulesEnviroment extends Environment{

	private ParticulesParameters parameters;
	
	public ParticulesEnviroment(ParticulesParameters parameters) {
		super(parameters);
		this.parameters = parameters;
		loadAgents();
	}

	@Override
	protected void loadAgents() {
		for(int i = 0; i < agentCount; i++){
			ParticuleAgent agent = new ParticuleAgent(this);
			agent.initializeRandomPositionAndVelocity();
			add(agent);
		}
	}

	public ParticulesParameters getParameters() {
		return parameters;
	}

}
