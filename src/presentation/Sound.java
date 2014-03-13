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
	 * @param end
	 * @param layer
	 * @param file
	 * @param loop
	 */
	public Sound(int start, int end,
			String file, boolean loop) {
		super(0, 0, start, end, 0, file);
		this.loop = loop;
	}
	
	public boolean getLoop(){
		return loop;
	}
	
	public void setLoop(boolean loop){
		this.loop = loop;
	}
}
