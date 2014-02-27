package slideModule;
/**
 * @author Josh Drake
 */

import java.util.ArrayList;

public class Shapes extends SlideObject {

	private int numberOfPoints;
	private int width;
	private int height;
	private String fillColor;
	private String lineColor;
	private int branch;
	private ArrayList<Point> pointList = new ArrayList<Point>(0);
	/**
	 * @param x_coord
	 * @param y_coord
	 * @param start
	 * @param end
	 * @param layer
	 * @param file
	 * @param numberOfPoints
	 * @param width
	 * @param height
	 * @param fillColor
	 * @param lineColor
	 * @param branch
	 * @param pointList
	 */
	public Shapes(int x_coord, int y_coord, int start, int end, int layer,
			String file, int numberOfPoints, int width, int height,
			String fillColor, String lineColor, int branch,
			ArrayList<Point> pointList) {
		super(x_coord, y_coord, start, end, layer, file);
		this.numberOfPoints = numberOfPoints;
		this.width = width;
		this.height = height;
		this.fillColor = fillColor;
		this.lineColor = lineColor;
		this.branch = branch;
		this.pointList = pointList;
	}
	
	/**
	 * 
	 * @return numberOfPoints
	 */
	public int getNumberOfPoints(){
		return numberOfPoints;
	}
	
	/**	
	 * 	
	 * @return the width
	 */	public int getWidth(){		return width;	}		/**	 * 	 * @return the height	 */	public int getHeight(){		return height;	}
	
	/**
	 * 
	 * @return fillColor
	 */
	public String getFillColor(){
		return fillColor;
	}
	
	/**
	 * 
	 * @return lineColor
	 */
	public String getLineColor(){
		return lineColor;
	}
	
	/**
	 * 
	 * @return branch
	 */
	public int getBranch(){
		return branch;
	}
	
	/**
	 * 
	 * @param index
	 * @return the point at a given index
	 * @see java.util.ArrayList#get(int)
	 */
	public Point getPoint(int index){
		return pointList.get(index);
	}
}