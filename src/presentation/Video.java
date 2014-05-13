
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
	 * @param x_coord
	 * @param y_coord
	 * @param start
	 * @param end
	 * @param layer
	 * @param file
	 * @param width
	 * @param height
	 * @param length
	 */

	public Video() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Video(int x_coord, int y_coord, int start, int duration, int layer,
			String file, int width, int height) {
		super(x_coord, y_coord, start, duration, layer, file, 0);
		this.width = width;
		this.height = height;
	}


	/**
	 * 
	 * @return the width
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * 
	 *
	 */
	public void setWidth(int width){
		this.width = width;
	}

	/**
	 * 
	 * @return the height
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * 
	 * 
	 */
	public void setHeight(int height){
		this.height = height;
	}

	/**
	 * @return the looping
	 */
	public boolean isLooping() {
		return looping;
	}
	/**
	 * @param looping the looping to set
	 */
	public void setLooping(boolean looping) {
		this.looping = looping;
	}
	/**
	 * @return the playtime
	 */
	public int getPlaytime() {
		return playtime;
	}
	/**
	 * @param playtime the playtime to set
	 */
	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}

}
