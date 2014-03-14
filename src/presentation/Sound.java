package presentation;

/**
 * 
 * @author Josh Drake
 * @author Andrew Walter
 *
 */
public class Sound extends SlideObject{

	private boolean loop;

	/**
	 * @param x_coord
	 * @param y_coord
	 * @param start
	 * @param duration
	 * @param layer
	 * @param file
	 * @param loop
	 */
	public Sound(int start, int duration,
			String file, boolean loop) {
		super(0, 0, start, duration, 0, file, 0);
		this.loop = loop;
	}
	
	public Sound() {
		// TODO Auto-generated constructor stub
	}

	public boolean getLoop(){
		return loop;
	}
	
	public void setLoop(boolean loop){
		this.loop = loop;
	}
}
