package particules.main;

import core.model.MultiAgentSystem;
import core.view.SMAFrame;
import particules.model.ParticulesEnviroment;
import particules.utils.ParticulesParameters;

public class ParticuleSimulationLauncher {

	public static void main(String[] args) {

		if(args.length < 1){
			System.err.println("You need to supply the properties file name.");
			return;
		}
		
		ParticulesParameters parameters = new ParticulesParameters(args[0]);
		ParticulesEnviroment environment = new ParticulesEnviroment(parameters);
		
		MultiAgentSystem multiAgentSystem = new MultiAgentSystem(environment, parameters);
		new SMAFrame(multiAgentSystem, parameters);
		multiAgentSystem.run();
	}
}
