/**
 * 
 */
package slideModule;

/**
 * @author Robert Mills
 * @author Josh Drake
 * @author Andrew Walter
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
	 * @param width
	 * @param height
	 */
	public Image(int x_coord, int y_coord, int start, int end, int layer,
			String file, int width, int height) {
		super(x_coord, y_coord, start, end, layer, file);
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
	
}
