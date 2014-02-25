/**
 * 
 */
package slideModule;

/**
 * @author Robert
 *
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
	 * @return the x_coord
	 */
	public int getX_coord() {
		return x_coord;
	}

	/**
	 * @return the y_coord
	 */
	public int getY_coord() {
		return y_coord;
	}
	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}
}
