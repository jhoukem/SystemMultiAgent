package core.utils;

public class FPSManager {

	// The time the last frame was called. Useful to calculate the current StateTime.
	private long lastFrame = 0;
	// The stateTime allow me to trigger some event a certain point in time.
	private float stateTime = 0;
	
	public boolean canUpdate(float time){
		return stateTime > time;
	}
	
	public void updateTimer(){
		stateTime += getDelta();
	}
	
	public void resetTimer(){
		stateTime = 0;
	}
	
	/**
	 * Calculate the time between now and the previous frame call (if called every frame).
	 * 
	 * @return the time between now and the previous frame call.
	 */
	private float getDelta() {
		long delta = System.currentTimeMillis() - lastFrame;
		lastFrame = System.currentTimeMillis();
		return (float)delta;
	}
	
	public float getStateTime() {
		return stateTime;
	}

	public int getTimeToSleep(float time) {
		return Math.max(0, (int)(time - stateTime));
	}
}