package presentation;
/**
 * @author Josh Drake
 * @author Robert Mills
 * @author Andrew Walter
 */

import java.awt.Color;
import java.util.ArrayList;

public class Shapes {

	private int numberOfPoints, width, height, start, duration, layer, branch;
	private String fillColor, lineColor, file;
	private ArrayList<Point> pointList = new ArrayList<Point>(0);
	private Integer chapterBranch = null;
	/**
	 * @param x_coord
	 * @param y_coord
	 * @param start
	 * @param duration
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
	public Shapes(int start, int duration, int layer,
			String file, int numberOfPoints, int width, int height,
			String fillColor, String lineColor, ArrayList<Point> pointList, int branch) {
		//super(x_coord, y_coord, start, duration, layer, file, branch);
		this.start = start;
		this.duration = duration;
		this.layer = layer;
		this.file = file;
		this.branch = branch;
	    this.numberOfPoints = numberOfPoints;
		this.width = width;
		this.height = height;
		this.fillColor = fillColor;
		this.lineColor = lineColor;
		this.pointList = pointList;
	}
	
	/**
	 * 
	 */
	public Shapes() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return numberOfPoints
	 */
	public int getNumberOfPoints(){
		return numberOfPoints;
	}
	
	
	/**
	 * @param numberOfPoints the number of points hich make up the shape
	 */
	public void setNumberOfPoints(int numberOfPoints){
		this.numberOfPoints = numberOfPoints;
	}
	
	/**	
	 * @return the width of the shape
	 */
	public int getWidth(){
		return width;
	}

	/**
	 * @param width the width of the shape
	 */
	public void setWidth(int width){
		this.width = width;
	}
	
	/**
	 * @return the height of the shape
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * @param height the height of the shape 
	 */
	public void setHeight(int height){
		this.height = height;
	}
	
	/**
	 * @return the fill colour of the shape (defaults to presentation default)
	 */
	public String getFillColor(){
		return fillColor;
	}

	/**
	 * @param fillColour the fill colour of the shape (defaults to presentation default)
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
	 * @return lineColor of the shape
	 */
	public String getLineColor(){
		return lineColor;
	}

	/**
	 * @param lineColour the colour of the lines
	 */
	public void setLineColor(String lineColour){
		this.lineColor = lineColour;
	}
	
	/**
	 * @param index
	 * @return the point at a given index
	 * @see java.util.ArrayList#get(int)
	 */
	public Point getPoint(int index){
		return pointList.get(index);
	}

	/**
	 * @param arg0 a point on teh shape
	 * @return true if successful
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean addPoint(Point arg0) {
		return pointList.add(arg0);
	}

	/**
	 * @return the number of points
	 * @see java.util.ArrayList#size()
	 */
	public int sizePointList() {
		return pointList.size();
	}

    /**
     * @return the array of points
     */
    public ArrayList<Point> getPointList() {
        return pointList;
    }

    /**
     * @param pointList allows a list of points to be imported
     */
    public void setPointList(ArrayList<Point> pointList) {
        this.pointList = pointList;        
    }

    /**
     * Gets the time into the slide at which the object occurs
     * @return start
     */
    public Integer getStart() {
        return start;
    }


    /**
     * Set the time into the slide at which the object occurs
     * @param start 
     */
    public void setStart(Integer start) {
        this.start = start;
    }
    
    /**
     * Gets the time into the slide when the object disappears
     * @return end
     */
    public Integer getDuration() {
        return duration;
    }


    /**
     * Sets the time into the slide when the object disappears 
     * @param end the end to set
     */
    public void setDuration(Integer end) {
        this.duration = end;
    }

    /**
     * @return the layer
     */
    public Integer getLayer() {
        return layer;
    }


    /**
     * @param layer the layer to set
     */
    public void setLayer(Integer layer) {
        this.layer = layer;
    }
    
    public void setFile(String file) {
        this.file = file;
    }
    
    public String getFile() {
        return file;
    }
    
    /**
     * 
     * @return branch
     */
    public Integer getBranch(){
        return branch;
    }
    
    /**
     * 
     * 
     */
    public void setBranch(Integer branch){
        this.branch = branch;
    }

	public void setChapterBranch(int chapterBranch) {
		this.chapterBranch = chapterBranch;
		
	}

	public Integer getChapterBranch() {
		return chapterBranch;
	}
    
    
}
