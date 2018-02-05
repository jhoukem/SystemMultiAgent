package hunter.view;

import java.awt.event.KeyEvent;

import core.model.MultiAgentSystem;
import core.view.GridView;
import hunter.model.AvatarAgent;
import hunter.model.HunterEnvironment;

public class HunterGridView extends GridView {

	private AvatarAgent playerAvatar;
	protected HunterEnvironment environment;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 233699172433700350L;

	public HunterGridView(MultiAgentSystem multiAgentSystem, HunterEnvironment environment) {
		super(multiAgentSystem, environment.getParameters());
		this.environment = environment;
		this.playerAvatar = environment.getPlayerAvatar();
	}

	@Override
	public void keyPressed(KeyEvent key) {
		System.out.println("Pressed !");
		switch(key.getKeyCode()){
		case KeyEvent.VK_ESCAPE: environment.reset(); break;
		case KeyEvent.VK_SPACE: multiAgentSystem.pause(); break;
		case KeyEvent.VK_RIGHT: playerAvatar.setVelocity(1, 0); break;
		case KeyEvent.VK_DOWN: playerAvatar.setVelocity(0, 1); break;
		case KeyEvent.VK_LEFT: playerAvatar.setVelocity(-1, 0); break;
		case KeyEvent.VK_UP: playerAvatar.setVelocity(0, -1); break;
		case KeyEvent.VK_A: environment.decreaseHunterSpeed(); break;
		case KeyEvent.VK_Z: environment.increaseHunterSpeed(); break;
		case KeyEvent.VK_O: playerAvatar.decreaseSpeed(); break;
		case KeyEvent.VK_P: playerAvatar.increaseSpeed(); break;
		case KeyEvent.VK_W: multiAgentSystem.decreaseSleepTime(); break;
		case KeyEvent.VK_X: multiAgentSystem.increaseSleepTime(); break;
		}
	}

}
