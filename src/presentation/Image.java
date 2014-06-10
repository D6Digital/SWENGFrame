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
	
	/**
	 * 
	 */
	public Image() {
	}

	/**
	 * 
	 * @return the width of the image
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * @param width the desired width of the image
	 */
	public void setWidth(int width){
		this.width = width;
	}
	
	/**
	 * @return the height of the image
	 */
	public int getHeight(){
		return height;
	}
	
	
	/**
	 * @param height desired height of the image
	 */
	public void setHeight(int height){
		this.height = height;
	}

	/**
	 * @param chapterBranch
	 */
	public void setChapterBranch(int chapterBranch) {
		this.chapterBranch = chapterBranch;
		
	}

	/**
	 * @return get the slide number to branch to
	 */
	public Integer getChapterBranch() {
		return chapterBranch;
	}
	
}
