/**
 * 
 */
package slideModule;

/**
 * @author Robert Mills
 * @version 1.0
 * @since 1.0
 */
public class SlideObject {
	private int x_coord;
	private int y_coord;
	private String file;

	
	/**
	 * 
	 * @param x_coord
	 * @param y_coord
	 * @param file
	 */
	public SlideObject(int x_coord, int y_coord, String file) {
		super();
		this.x_coord = x_coord;
		this.y_coord = y_coord;
		this.file = file;
	}
	/**
	 * Sets the x coordinate of the slide object.
	 * <p>
	 * For music files this will be ignored
	 * @param x_coord the x-coordinate
	 */
	public void setX_coord(int x_coord) {
		this.x_coord = x_coord;
	}
	/**
	 * Sets the y coordinate of the slide object
	 * <p>
	 * For music files this will be ignored
	 * @param y_coord the y-coordinate
	 */
	public void setY_coord(int y_coord) {
		this.y_coord = y_coord;
	}
	/**
	 * Sets a file name for an object.
	 * The file it represents will be as follows
	 * <ul>
	 * <li> Text: the font file
	 * <li> Image: the image file
	 * <li> Audio: the sound file
	 * <li> Video: the video file
	 * <li> Graphics: do not care
	 * </ul>
	 * @param file the file name including any change from current file directory
	 */
	public void setFile(String file) {
		this.file = file;
	}
	/**
	 * Retrieves the x coordinate of the slide object
	 * @return the x_coord
	 */
	public int getX_coord() {
		return x_coord;
	}

	/**
	 * Retrieves the y-coordinate of the slide object
	 * @return the y_coord
	 */
	public int getY_coord() {
		return y_coord;
	}
	/**
	 * Retrieves the associated filename ofr the slide object
	 * @return the file
	 */
	public String getFile() {
		return file;
	}
}
