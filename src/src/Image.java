/**
 * 
 */
package src;

/**
 * @author Robert, Josh Drake
 *
 */
public class Image extends SlideObject {
	private int x_coord;
	private int y_coord;
	private String file;
	private int width;
	private int height;

	/**
	 * 
	 * @param x_coord
	 * @param y_coord
	 * @param file
	 * @param width
	 * @param height
	 */
	public Image(int x_coord, int y_coord, String file, int width, int height) {
		super(x_coord, y_coord, file);
		this.width = width;
		this.height = height;
		
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

	/**
	 * 
	 * @return the width
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * 
	 * @return the height
	 */
	public int getHeight(){
		return height;
	}
	
}
