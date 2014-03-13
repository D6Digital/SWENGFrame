package slideModule;
/**
 * @author Josh Drake
 * @author Robert Mills
 * @author Andrew Walter
 */

import java.awt.Color;
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
	 * 
	 */
	public void setNumberOfPoints(int numberOfPoints){
		this.numberOfPoints = numberOfPoints;
	}
	
	/**	
	 * 	
	 * @return the width
	 */	public int getWidth(){		return width;	}
	
	/**	
	 * 	
	 * 
	 */
	public void setWidth(int width){
		this.width = width;
	}		/**	 * 	 * @return the height	 */	public int getHeight(){		return height;	}
	
	/**
	 * 
	 * @return the height
	 */
	public void setHeight(int height){
		this.height = height;
	}
	
	/**
	 * 
	 * @return fillColor
	 */
	public String getFillColor(){
		return fillColor;
	}
	
	/**
	 * 
	 * 
	 */
	public void setFillColor(String fillColour){
		this.fillColor = fillColour;
	}
	
	/**
	 * @param converts hex colours to RGB and returns a type Color
	 */
	public Color getFillColourObject() {
		int[] RGB = {0, 0, 0};
		Color colourReturn;
		if (fillColor.charAt(0) == '#'){
			String colourHex = fillColor.substring(1,7);
			RGB[0] = Integer.parseInt(colourHex.substring(0,2), 16);
			RGB[1] = Integer.parseInt(colourHex.substring(2,4), 16);
			RGB[2] = Integer.parseInt(colourHex.substring(4,6), 16);
		}
		colourReturn = new Color(RGB[0], RGB[1], RGB[2]);
		return colourReturn;
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
	 * 
	 */
	public void setLineColor(String lineColour){
		this.lineColor = lineColour;
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
	 * 
	 */
	public void setBranch(int branch){
		this.branch = branch;
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

	/**
	 * @param arg0
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean addPoint(Point arg0) {
		return pointList.add(arg0);
	}

	/**
	 * @return
	 * @see java.util.ArrayList#size()
	 */
	public int sizePointList() {
		return pointList.size();
	}
}