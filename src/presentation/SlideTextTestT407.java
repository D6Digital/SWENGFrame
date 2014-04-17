package presentation;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * T407, a test to check whether the getters and setters for the Text object work correctly.
 * @author Joshua Lant
 *
 */
public class SlideTextTestT407 {

    int x_coord, y_coord, start, duration, layer, size, branch;
    String file, colour, textString;
    ArrayList<TextContent> text;

    @Before
    public void setUp() throws Exception {
        x_coord = 543126;
        y_coord = 87656542;
        start = 543161;
        duration = 6754;
        layer = 1543;
        size = 7533;
        branch = 564327;
        file = "gfewgewe";
        colour = "wwwwwwwwwwwwwwwwwwwwww";
        text = new ArrayList<TextContent>(0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test1to2() {
        // Step 1. instantiate Text with all parameters in constructor.
        Text text2 = new Text(x_coord, y_coord, start, duration, layer, file, text, colour, size, branch);
    
        // Step 2. Check to see if getters and constructor works correctly.
        assertEquals("x_coord constructor or getter not working correctly.",
                x_coord, text2.getX_coord());
        assertEquals("y_coord constructor or getter not working correctly.",
                y_coord, text2.getY_coord());
        assertEquals("start constructor or getter not working correctly.",
                start, text2.getStart());
        assertEquals("duration constructor or getter not working correctly.",
                duration, text2.getDuration());
        assertEquals("layer constructor or getter not working correctly.",
                layer, text2.getLayer());
        assertEquals("file constructor or getter not working correctly.",
                file, text2.getFile());
        assertEquals("textContent constructor or getter not working correctly.",
                text, text2.getText());
        assertEquals("colour constructor or getter not working correctly.",
                colour, text2.getColour());
        assertEquals("size constructor or getter not working correctly.",
                size, text2.getSize());
        assertEquals("branch constructor or getter not working correctly.",
                branch, text2.getBranch());       
    }
    
    @Test
    public void test3to4() {
        // Step 3. instantiate Text with nothing in the constructor.
        Text text2 = new Text();
        
        x_coord = 546;
        y_coord = 876;
        start = 5431;
        duration = 674;
        layer = 143;
        size = 753;
        branch = 5327;
        file = "gfeewe";
        colour = "wwwwwwwwfcxsww";
        
        textString = "fewfww";
        text = new ArrayList<TextContent>(0);
        TextContent textContent = new TextContent();
        textContent.setTextString(textString);
        text.add(textContent);
        
        // Step 4. Check to see that setters and getters work correctly.
        text2.setX_coord(x_coord);
        text2.setY_coord(y_coord);
        text2.setStart(start);
        text2.setDuration(duration);
        text2.setLayer(layer);
        text2.setSize(size);
        text2.setBranch(branch);
        text2.setFile(file);
        text2.setText(text);
        text2.setColour(colour);

        assertEquals("x_coord constructor or getter not working correctly.",
                x_coord, text2.getX_coord());
        assertEquals("y_coord constructor or getter not working correctly.",
                y_coord, text2.getY_coord());
        assertEquals("start constructor or getter not working correctly.",
                start, text2.getStart());
        assertEquals("duration constructor or getter not working correctly.",
                duration, text2.getDuration());
        assertEquals("layer constructor or getter not working correctly.",
                layer, text2.getLayer());
        assertEquals("file constructor or getter not working correctly.",
                file, text2.getFile());
        assertEquals("textContent constructor or getter not working correctly.",
                text, text2.getText());
        assertEquals("textContent constructor or getter not working correctly.",
                textString, text2.getText().get(0).getTextString());
        assertEquals("colour constructor or getter not working correctly.",
                colour, text2.getColour());
        assertEquals("size constructor or getter not working correctly.",
                size, text2.getSize());
        assertEquals("branch constructor or getter not working correctly.",
                branch, text2.getBranch());   
        
    }

}
