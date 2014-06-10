package imageModule;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Images.ImagePanel;
import Images.TImage;

/**
 * A test to check that the ImagePainter class works correctly.
 * TODO: Complete test writing once issue #9 has been resolved.
 * @author Joshua Lant.
 */
public class ImagePainterTestT302 {

    ImagePainter image;
    JFrame frame = new JFrame();
    JFrame frame2 = new JFrame();
    JLabel label;
    ImagePanel imagePanel, imagePanel2;
    TImage tImage, tImage2;
    String notAFile = "C:\\this\\path\\does\\not\\exist.jpg";
   // String file = "src/XMLBits/char1jovian_spy.jpg";
    //String file = "C:\\Users\\Public\\Public Pictures\\Sample Pictures\\Desert.jpg";
    String file = "resources/buttons/Background.png";
    
    int x_coord = 50;
    int y_coord = 30;
    int start = 0;
    int duration = 9;
    int layer = 0;
    int width = 500;
    int height = 500;
    int branch = 0;
    
    
    @Before
    public void setUp() throws Exception {
        
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws InterruptedException {
        tImage = new TImage(file, 0, 0);
        tImage2 = new TImage(notAFile, 0, 0);
        
        imagePanel = new ImagePanel(tImage);
        System.out.println("1");
        imagePanel2 = new ImagePanel(tImage2);
        System.out.println("2");
        imagePanel.setOpaque(false);
        imagePanel2.setOpaque(false);
        
       // imagePanel.setLayout(null);
        //imagePanel2.setLayout(null);
        
        imagePanel.setBounds(0,0, width, height);
        imagePanel2.setBounds(0,0, width, height);
        
        imagePanel.setWidth(width);
        imagePanel.setHeight(height);
        imagePanel2.setWidth(width);
        imagePanel2.setHeight(height);
        
        imagePanel.draw();
        imagePanel2.draw();
        
        frame.setLayout(null);
        frame.setBounds(0, 0, 500, 500);
        frame.add(imagePanel);
        frame.validate();
        frame.setVisible(true);
        
        frame2.setLayout(null);
        frame2.setBounds(0, 0, 500, 500);
        frame2.add(imagePanel2);
        frame2.validate();
        frame2.setVisible(true);
        
        Thread.sleep(10000);
    }

}
