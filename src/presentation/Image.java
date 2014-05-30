/**
 * 
 */
package presentation;

/**
 * @author Robert Mills
 * @author Josh Drake
 * @author Andrew Walter
 *
 */
public class Image extends SlideObject {
	
	private int width;
	private int height;
	private Integer chapterBranch;

	
	/**
	 * @param x_coord
	 * @param y_coord
	 * @param start
	 * @param duration
	 * @param layer
	 * @param file
	 * @param width
	 * @param height
	 * @param branch
	 */
	public Image(int x_coord, int y_coord, int start, int duration, int layer,
			String file, int width, int height, int branch) {
		super(x_coord, y_coord, start, duration, layer, file, branch);
		this.width = width;
		this.height = height;
	}
	
	public Image() {
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

	public void setChapterBranch(int chapterBranch) {
		this.chapterBranch = chapterBranch;
		
	}

	public Integer getChapterBranch() {
		return chapterBranch;
	}
	
}
