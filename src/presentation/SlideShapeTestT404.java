package presentation;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * T404, a test to determine whether the getters and setters are working correctly, whether they are all present and whether
 * the constructor sets all parameters properly.
 * @author jl1132
 *
 */
public class SlideShapeTestT404 {
    int numberOfPoints, width, height;
    Integer x_coord, y_coord, start, duration, layer, branch;
    String fillColor, lineColor;
    String file;
    ArrayList<Point> pointList = new ArrayList<Point>(0);
    Point point = new Point();
    
    @Before
    public void setUp() throws Exception {
        numberOfPoints = 315235423;
        width = 10000000;
        height = 2;
        branch = 2335;
        
        x_coord = 235436;
        y_coord = 2379852;
        start = 543;
        duration = 142316;
        layer = 4891;
        
        fillColor = "fewsfdwssgds";
        lineColor = "219043fnfjfd";
        
        pointList.add(point);
        
        
    }

    @Test
    public void testSteps1to2() {
        // Step 1. instantiate class with all parameters containing values.
        Shapes shapes = new Shapes(start, duration, layer, file, numberOfPoints, width, height, fillColor, lineColor, pointList, branch);

        // Step 2. test the getters to see if they work.
        assertEquals("start constructor or setter not working correctly",
                start, shapes.getStart());
        assertEquals("duration constructor or setter not working correctly",
                duration, shapes.getDuration());
        assertEquals("layer constructor or setter not working correctly",
                layer, shapes.getLayer());
        assertEquals("file constructor or setter not working correctly",
                file, shapes.getFile());
        assertEquals("numberofpoints constructor or setter not working correctly",
                numberOfPoints, shapes.getNumberOfPoints());
        assertEquals("width constructor or setter not working correctly",
                width, shapes.getWidth());
        assertEquals("height constructor or setter not working correctly",
                height, shapes.getHeight());
        assertEquals("fillcolor constructor or setter not working correctly",
                fillColor, shapes.getFillColor());        
        assertEquals("linecolor constructor or setter not working correctly",
                lineColor, shapes.getLineColor());
        assertEquals("pointlist constructor or setter not working correctly",
                pointList, shapes.getPointList());
        assertEquals("pointlist constructor or setter not working correctly",
                point, shapes.getPoint(0));
        assertEquals("branch constructor or setter not working correctly",
                branch, shapes.getBranch());
        
        
        shapes.setStart(start);
        shapes.setDuration(duration);
        shapes.setLayer(layer);
        shapes.setFile(file);
        shapes.setNumberOfPoints(numberOfPoints);
        shapes.setWidth(width);
        shapes.setHeight(height);
        shapes.setFillColor(fillColor);
        shapes.setLineColor(lineColor);
        shapes.setBranch(branch);
        /**
         *  Does not exist, needs to be created, issue #13
         *  shapes.setPointList(pointList);
         */   
    }

//    @Test
//    public void testSteps3to4() {
//        // Step 3. Instantiate class using empty constructor.
//        Shapes shapes = new Shapes();
//        
//        // Step 4. use setters to ensure you can set every parameter.
//        shapes.setX_coord(x_coord);
//        shapes.setY_coord(y_coord);
//        shapes.setStart(start);
//        shapes.setDuration(duration);
//        shapes.setLayer(layer);
//        shapes.setFile(file);
//        shapes.setNumberOfPoints(numberOfPoints);
//        shapes.setWidth(width);
//        shapes.setHeight(height);
//        shapes.setFillColor(fillColor);
//        shapes.setLineColor(lineColor);
//        shapes.setBranch(branch);
//        shapes.setPointList(pointList);
// 
//        
//        assertEquals("xcoord constructor or setter not working correctly",
//                x_coord, shapes.getX_coord());
//        assertEquals("ycoord constructor or setter not working correctly",
//                y_coord, shapes.getY_coord());
//        assertEquals("start constructor or setter not working correctly",
//                start, shapes.getStart());
//        assertEquals("duration constructor or setter not working correctly",
//                duration, shapes.getDuration());
//        assertEquals("layer constructor or setter not working correctly",
//                layer, shapes.getLayer());
//        assertEquals("file constructor or setter not working correctly",
//                file, shapes.getFile());
//        assertEquals("numberofpoints constructor or setter not working correctly",
//                numberOfPoints, shapes.getNumberOfPoints());
//        assertEquals("width constructor or setter not working correctly",
//                width, shapes.getWidth());
//        assertEquals("height constructor or setter not working correctly",
//                height, shapes.getHeight());
//        assertEquals("fillcolor constructor or setter not working correctly",
//                fillColor, shapes.getFillColor());        
//        assertEquals("linecolor constructor or setter not working correctly",
//                lineColor, shapes.getLineColor());
//        assertEquals("pointlist constructor or setter not working correctly",
//                pointList, shapes.getPointList());
//        assertEquals("pointlist constructor or setter not working correctly",
//                point, shapes.getPoint(0));
//        assertEquals("branch constructor or setter not working correctly",
//                branch, shapes.getBranch());
//        
//    }
    
    @After
    public void tearDown() throws Exception {
    }
    
}
