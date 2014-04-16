package presentation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SlideObjectAttributesTestT401 {
    int x_coord, y_coord, start, duration, layer, branch;
    String file;
    
    @Before
    public void setUp() throws Exception {
        x_coord = 432;
        y_coord = 3214;
        start = 21;
        duration = 34214;
        layer = 54;
        branch = 84;
        file = "thisisafile.fileyfile";
        
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        // Step 1. Instantiate the SlideObject class, supplying non-null values for each of the input parameters.
        SlideObject slide = new SlideObject(x_coord, y_coord, start, duration, layer, file, branch);
        
        // Step 2. All parameters should be gettable.
        assertEquals("cannot get x coordinate",
                slide.getX_coord(), x_coord);
        assertEquals("cannot get y coordinate",
                slide.getY_coord(), y_coord);
        assertEquals("cannot get start",
                slide.getStart(), start);
        assertEquals("cannot get duration",
                slide.getDuration(), duration);
        
    }

}
