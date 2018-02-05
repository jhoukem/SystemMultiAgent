package particules.model;

import java.awt.Color;

import core.model.Agent;

public class ParticuleAgentSwapColor extends ParticuleAgent{

	public ParticuleAgentSwapColor(ParticulesEnviroment environment) {
		super(environment);
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	protected void collideWith(Agent<?> collider) {
		if(getColor() == Color.GRAY){
			setColor(Color.RED);
		} 
		collider.setColor(getColor());
		swapVelocity(collider);
	}

}
