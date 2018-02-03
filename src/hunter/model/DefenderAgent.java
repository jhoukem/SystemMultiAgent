package hunter.model;

import java.awt.Color;

public class DefenderAgent extends HunterSimulationAgent{

	private int lifeTime;
	
	public DefenderAgent(HunterEnvironment environment) {
		super(environment);
		this.lifeTime = environment.getParameters().getDefenderLifeTime();
		setColor(Color.GREEN);
	}

	@Override
	public void decide() {
		if(lifeTime-- <= 0){
			environment.getCell(x, y).setAgent(null);
			environment.remove(this);
		}
	}

}
