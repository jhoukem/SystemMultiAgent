package particles.main;

import core.model.MultiAgentSystem;
import core.view.SMAFrame;
import particles.model.ParticlesEnviroment;
import particles.utils.ParticlesParameters;

public class ParticleSimulationLauncher {

	public static void main(String[] args) {

		if(args.length < 1){
			System.err.println("You need to supply the properties file name.");
			return;
		}
		
		ParticlesParameters parameters = new ParticlesParameters(args[0]);
		ParticlesEnviroment environment = new ParticlesEnviroment(parameters);
		
		MultiAgentSystem multiAgentSystem = new MultiAgentSystem(environment, parameters);
		new SMAFrame(multiAgentSystem, parameters);
		multiAgentSystem.run();
	}
}
