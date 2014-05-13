package graphicsModule;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Graphics.graphicsObject;

/**
 * T101- A test to check that the graphics painter module correctly.
 * @author Joshua Lant
 */
public class GraphicsTestT101 {
    final int SLEEPMS = 2000;
    JFrame frame;
    JPanel panel;
    Color color;
    graphicsObject graphicsObject, graphicsObject2, graphicsObject3, graphicsObject4, graphicsObject5, graphicsObject6;
    //GraphicsPainter painter;
    Random rand;
    private String fillColor = "#FADC2F";
    private String lineColor = "#00FAE4";

    
    @Before
    public void setUp() throws Exception {
        frame = new JFrame("testframe");
        color = new Color(125,78,92,196);
        frame.setSize(300,300);
        rand = new Random();
        panel = new JPanel();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws InterruptedException {
        graphicsObject = new graphicsObject(lineColor, fillColor);
        graphicsObject2 = new graphicsObject(lineColor, fillColor);
        graphicsObject3 = new graphicsObject(lineColor, fillColor);
        graphicsObject4 = new graphicsObject(lineColor, fillColor);
        graphicsObject5 = new graphicsObject(lineColor, fillColor);
        graphicsObject6 = new graphicsObject(lineColor, fillColor);
        
        
        graphicsObject.setTotalPoints(2);
        graphicsObject.setPoint(1, 150, 150);
        graphicsObject.setWidth(300);
        graphicsObject.setHeight(300);
        graphicsObject.setIsRegularShape(true);
        frame.add(graphicsObject);
        frame.setVisible(true);
        
        Thread.sleep(SLEEPMS);
        
        frame.remove(graphicsObject);
        frame.setVisible(false);
        graphicsObject.setTotalPoints(3);
        frame.add(graphicsObject);
        frame.setVisible(true);

        Thread.sleep(SLEEPMS);
        
        frame.setVisible(false); 
        frame.remove(graphicsObject);
        
        graphicsObject.setTotalPoints(4);
        graphicsObject.setPoint(1, 150, 150);
        graphicsObject.setWidth(300);
        graphicsObject.setHeight(300);
        graphicsObject.setIsRegularShape(true);
        frame.add(graphicsObject);
        frame.setVisible(true);
        
        Thread.sleep(SLEEPMS);
        
        frame.setVisible(false); 
        frame.remove(graphicsObject);
        
        graphicsObject.setTotalPoints(5);
        graphicsObject.setPoint(1, 150, 150);
        graphicsObject.setWidth(300);
        graphicsObject.setHeight(300);
        graphicsObject.setIsRegularShape(true);
        frame.add(graphicsObject);
        frame.setVisible(true);
        
        Thread.sleep(SLEEPMS);
        
        
        // Step 1. validate that the settings provided to the graphics painter are correct.
//        assertEquals("Red value was incorrect",
//                125, panel.getBackground().getRed());    
//        assertEquals("Green value was incorrect",
//                78, panel.getBackground().getGreen());    
//        assertEquals("Blue value was incorrect",
//                92, panel.getBackground().getBlue());  
//        assertEquals("Alpha value was incorrect",
//                196, panel.getBackground().getAlpha());  
    }

}
