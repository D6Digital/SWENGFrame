/**
 * 
 */
package slideModule;

/**
 * @author Robert Mills
 * @author Josh Drake
 *
 */
public class Image extends SlideObject {
	
	private int width;
	private int height;


	/**
	 * @param x_coord
	 * @param y_coord
	 * @param start
	 * @param end
	 * @param layer
	 * @param file
	 * @param x_coord2
	 * @param y_coord2
	 * @param file2
	 * @param width
	 * @param height
	 */
	public Image(int x_coord, int y_coord, int start, int end, int layer,
			String file, int x_coord2, int y_coord2, String file2, int width,
			int height) {
		super(x_coord, y_coord, start, end, layer, file);
		x_coord = x_coord2;
		y_coord = y_coord2;
		file = file2;
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
	 * @return the height
	 */
	public int getHeight(){
		return height;
	}
	
}
