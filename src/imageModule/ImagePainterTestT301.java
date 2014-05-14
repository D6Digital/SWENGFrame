package imageModule;

import static org.junit.Assert.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import presentation.Image;
import presentation.slideMediaObject;

import Images.ImagePanel;
import Images.TImage;

/**
 * A test to check that the ImagePainter class works correctly.
 * TODO: Complete test writing once issue #9 has been resolved.
 * @author Joshua Lant.
 */
public class ImagePainterTestT301 {

    ImagePainter image;
    JFrame frame = new JFrame();
    JFrame frame2 = new JFrame();
    JLabel label;
    ImagePanel imagePanel, imagePanel2;
    TImage tImage, tImage2;
    String notAFile = "C:\\this\\path\\does\\not\\exist.jpg";
   // String file = "src/XMLBits/char1jovian_spy.jpg";
    String file = "C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg";
    
    int x_coord = 50;
    int y_coord = 30;
    int start = 10;
    int duration = 9;
    int layer = 7;
    int width = 200;
    int height = 100;
    int branch = 0;
    
    
    @Before
    public void setUp() throws Exception {
        
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws InterruptedException {
        Image image = new Image(x_coord, y_coord, start, duration, layer, file, width, height, branch);
        Image image2 = new Image(x_coord, y_coord, start, duration, layer, notAFile, width, height, branch);
        //panel = new JPanel();
        tImage = new TImage(file, image.getWidth(), image.getHeight());
        tImage2 = new TImage(notAFile, image2.getWidth(), image2.getHeight());

        
        imagePanel = new ImagePanel(tImage);
        System.out.println("HERE");
        imagePanel2 = new ImagePanel(tImage2);
        System.out.println("HERE");
        
        imagePanel.setBounds(0,0, image.getWidth(), image.getHeight());
        imagePanel2.setBounds(0,0, image2.getWidth(), image2.getHeight());

        
        imagePanel = new ImagePanel(tImage);
        imagePanel2 = new ImagePanel(tImage2);

        slideMediaObject imageObject = new slideMediaObject(image.getBranch());
        slideMediaObject imageObject2 = new slideMediaObject(image2.getBranch());
        
        imageObject.add(imagePanel);
        imageObject2.add(imagePanel2);

        imageObject.setBounds(image.getX_coord(),image.getY_coord(), image.getWidth(), image.getHeight());
        imageObject.setVisible(true);
        imageObject2.setBounds(image2.getX_coord(),image2.getY_coord(), image2.getWidth(), image2.getHeight());
        imageObject2.setVisible(true);
        
        
        frame.add(imagePanel);
        frame.setVisible(true);
        frame2.add(imagePanel2);
        frame2.setVisible(true);
        
        Thread.sleep(10000);
        
        // Step 1. When supplied an incorrect filepath, an error message is retrieved.
//        try {
//            
//            
//        image.produceImage(notAFile );
//        }
//        catch(NullPointerException e) {
//            fail("Null Pointer Exception thrown, this should not happen");
//        }
//        
//        // Step 2. confirm that when a correct filepath is supplied, a JLabel is returned.
//        label = image.produceImage("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg");
//        if(label == null) {
//            fail("A JLabel was not returned from the method produce image...");
//        }
        
        // Step 3. The JLabel has dimensions equal to the dimensions of the image provided.
        
        
    }

}
