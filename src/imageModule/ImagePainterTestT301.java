package imageModule;

import static org.junit.Assert.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A test to check that the ImagePainter class works correctly.
 * TODO: Complete test writing once issue #9 has been resolved.
 * @author Joshua Lant.
 */
public class ImagePainterTestT301 {

    ImagePainter image;
    JFrame frame;
    JPanel panel;
    JLabel label;
    
    @Before
    public void setUp() throws Exception {
        
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        image = new ImagePainter();

        // Step 1. When supplied an incorrect filepath, an error message is retrieved.
//        try {
//        image.produceImage("C:\\this\\path\\does\\not\\exist.jpg");
//        }
//        catch(NullPointerException e) {
//            fail("Null Pointer Exception thrown, this should not happen");
//        }
        
        // Step 2. confirm that when a correct filepath is supplied, a JLabel is returned.
        label = image.produceImage("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg");
        if(label == null) {
            fail("A JLabel was not returned from the method produce image...");
        }
        
        // Step 3. The JLabel has dimensions equal to the dimensions of the image provided.
        
        
    }

}
