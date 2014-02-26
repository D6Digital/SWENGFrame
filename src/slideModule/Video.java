package slideModule;

/**
 * 
 * @author Josh Drake
 *
 */
public class Video extends SlideObject {

	private int width;
	private int height;
	private int length;
	
	
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
	 * @param length
	 */
	public Video(int x_coord, int y_coord, int start, int end, int layer,
			String file, int x_coord2, int y_coord2, String file2, int width,
			int height, int length) {
		super(x_coord, y_coord, start, end, layer, file);
		x_coord = x_coord2;
		y_coord = y_coord2;
		file = file2;
		this.width = width;
		this.height = height;
		this.length = length;
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

	/**
	 * 
	 * @return the length
	 */
	public int getLength(){
		return length;
	}
}
