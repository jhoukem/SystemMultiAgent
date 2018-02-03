package hunter.view;

import java.awt.event.KeyEvent;

import core.model.MultiAgentSystem;
import core.view.GridView;
import hunter.model.AvatarAgent;
import hunter.utils.HunterParameters;

public class HunterGridView extends GridView {

	private AvatarAgent playerAvatar;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 233699172433700350L;

	public HunterGridView(MultiAgentSystem multiAgentSystem, HunterParameters parameters, AvatarAgent avatar) {
		super(multiAgentSystem, parameters);
		playerAvatar = avatar;
	}

	@Override
	public void keyPressed(KeyEvent key) {

		switch(key.getKeyCode()){
		case KeyEvent.VK_RIGHT: playerAvatar.setVelocity(1, 0); break;
		case KeyEvent.VK_DOWN: playerAvatar.setVelocity(0, 1); break;
		case KeyEvent.VK_LEFT: playerAvatar.setVelocity(-1, 0); break;
		case KeyEvent.VK_UP: playerAvatar.setVelocity(0, -1); break;
		}
	}

}
