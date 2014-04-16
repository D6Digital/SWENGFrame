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
    public void teststeps1to3() {
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
        assertEquals("cannot get layer",
                slide.getLayer(), layer);
        assertEquals("cannot get branch",
                slide.getBranch(), branch);
        assertEquals("cannot get branch",
                slide.getFile(), file);
        
        
        // Step 3. All parameters should be settable.
        x_coord = 2152415;
        y_coord = 3215;
        start = 325432543;
        duration =765431;
        layer = 65426;
        branch = 75217433;
        file = "swengyswengyswengsweng.madeupfileextention";
        
        slide.setX_coord(x_coord);
        slide.setY_coord(y_coord);
        slide.setStart(start);
        slide.setDuration(duration);
        slide.setLayer(layer);
        slide.setBranch(branch);
        slide.setFile(file);
        
        assertEquals("cannot get x coordinate",
                slide.getX_coord(), x_coord);
        assertEquals("cannot get y coordinate",
                slide.getY_coord(), y_coord);
        assertEquals("cannot get start",
                slide.getStart(), start);
        assertEquals("cannot get duration",
                slide.getDuration(), duration);
        assertEquals("cannot get layer",
                slide.getLayer(), layer);
        assertEquals("cannot get branch",
                slide.getBranch(), branch);
        assertEquals("cannot get branch",
                slide.getFile(), file);
        
    }
    
    @Test
    public void teststeps4to6() {
//        // Step 4. Instantiate the SlideObject class, supplying null values for each of the input parameters.
//        SlideObject slide = new SlideObject(null, null, null, null, null, null, null);
//    
//        // Step 5. All parameters should be gettable.
//        assertEquals("cannot get x coordinate",
//                slide.getX_coord(), null);
//        assertEquals("cannot get y coordinate",
//                slide.getY_coord(), null);
//        assertEquals("cannot get start",
//                slide.getStart(), null);
//        assertEquals("cannot get duration",
//                slide.getDuration(), null);
//        assertEquals("cannot get layer",
//                slide.getLayer(), null);
//        assertEquals("cannot get branch",
//                slide.getBranch(), null);
//        assertEquals("cannot get branch",
//                slide.getFile(), null);
//        
//        
//        // Step 6. All parameters should be settable.
//        x_coord = 9876543;
//        y_coord = 143254;
//        start = 5432;
//        duration =4236;
//        layer = 732344;
//        branch = 222222222;
//        file = "nulllllfldsfsfdsfdsfdsaf";
//        
//        slide.setX_coord(x_coord);
//        slide.setY_coord(y_coord);
//        slide.setStart(start);
//        slide.setDuration(duration);
//        slide.setLayer(layer);
//        slide.setBranch(branch);
//        slide.setFile(file);
//        
//        assertEquals("cannot get x coordinate",
//                slide.getX_coord(), x_coord);
//        assertEquals("cannot get y coordinate",
//                slide.getY_coord(), y_coord);
//        assertEquals("cannot get start",
//                slide.getStart(), start);
//        assertEquals("cannot get duration",
//                slide.getDuration(), duration);
//        assertEquals("cannot get layer",
//                slide.getLayer(), layer);
//        assertEquals("cannot get branch",
//                slide.getBranch(), branch);
//        assertEquals("cannot get branch",
//                slide.getFile(), file);
    }
}
