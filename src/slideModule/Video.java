
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
	 * @param width
	 * @param height
	 * @param length
	 */
	public Video(int x_coord, int y_coord, int start, int end, int layer,
			String file, int width, int height, int length) {
		super(x_coord, y_coord, start, end, layer, file);
		this.width = width;
		this.height = height;
		this.length = length;
	}

	public Video() {
		super();
		// TODO Auto-generated constructor stub
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
