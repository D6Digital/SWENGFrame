
package slideModule;

/**
 * 
 * @author Josh Drake
 * @author Andrew Walter
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

	public Video() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Video(int x_coord, int y_coord, int start, int end, int layer,
			String file, int width, int height, int length) {
		super(x_coord, y_coord, start, end, layer, file);
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
	 * 
	 * @return the length
	 */
	public int getLength(){
		return length;
	}
	
	/**
	 * 
	 *
	 */
	public void setLength(int length){
		this.length = length;
	}

}
