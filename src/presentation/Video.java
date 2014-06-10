
package presentation;

/**
 * 
 * @author Josh Drake
 * @author Andrew Walter
 *
 */
public class Video extends SlideObject {

	private int width;
	private int height;
	private boolean looping;
	private int playtime;


	
	/**
	 * 
	 */
	public Video() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param x_coord
	 * @param y_coord
	 * @param start
	 * @param duration
	 * @param layer
	 * @param file
	 * @param width
	 * @param height
	 */
	public Video(int x_coord, int y_coord, int start, int duration, int layer,
			String file, int width, int height) {
		super(x_coord, y_coord, start, duration, layer, file, 0);
		this.width = width;
		this.height = height;
	}


	/**
	 * @return the width of the video
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * @param width the width of the video
	 */
	public void setWidth(int width){
		this.width = width;
	}

	/**
	 * @return the height of the video
	 */
	public int getHeight(){
		return height;
	}

	/**
	 * @param height the height of the video
	 */
	public void setHeight(int height){
		this.height = height;
	}

	/**
	 * @return true if looping
	 */
	public boolean isLooping() {
		return looping;
	}
	/**
	 * @param looping true if looping
	 */
	public void setLooping(boolean looping) {
		this.looping = looping;
	}
	/**
	 * @return the start time within the video
	 */
	public int getPlaytime() {
		return playtime;
	}
	/**
	 * @param playtime the start time within the video
	 */
	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}

}
