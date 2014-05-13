package graphicsModule;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * T101- A test to check that the graphics painter module correctly.
 * @author Joshua Lant
 */
public class GraphicsTestT101INTERNALMODULE {
    JFrame frame;
    JPanel panel;
    Color color;
    GraphicsPainter painter;
    Random rand;

    
    @Before
    public void setUp() throws Exception {
        frame = new JFrame("testframe");
        color = new Color(125,78,92,196);
        frame.setSize(400,800);
        rand = new Random();
    }

    @After
    public void tearDown() throws Exception {
    }

    @SuppressWarnings("static-access")
    @Test
    public void test() throws InterruptedException {
        painter = new GraphicsPainter();
        panel = painter.producePanel(400, 800, color);
        frame.add(panel);
        frame.setVisible(true);

        // Step 1. validate that the settings provided to the graphics painter are correct.
        assertEquals("Red value was incorrect",
                125, panel.getBackground().getRed());    
        assertEquals("Green value was incorrect",
                78, panel.getBackground().getGreen());    
        assertEquals("Blue value was incorrect",
                92, panel.getBackground().getBlue());  
        assertEquals("Alpha value was incorrect",
                196, panel.getBackground().getAlpha());  
    }

}
