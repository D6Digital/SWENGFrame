package imageModule;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import Images.TImage;
import Images.ImagePanel;

/**
 * Test of image module functionality
 * 
 * @author Marius Jankauskas
 *
 */
public class ImageModuleExample 
{
	
	/**
	 * Main function. Displays an image pulled from the web on a JFrame
	 * @param args Command line arguments. Unused
	 */
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		/* Resize the frame to fill the window */
		GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice().setFullScreenWindow(frame);
		
		/* Configure an image */
		TImage im = new TImage(
			"http://img2.wikia.nocookie.net/__cb20131113183509/someordinaryga"
			+ "mers/images/3/3e/Dark-knight-rises-batman-logo.png", 250, 250);
		im.setHeight(200);
		im.setWidth(500);
		
		/* Produce the image panel */
		ImagePanel imP = new ImagePanel(im);
		imP.setSize(new Dimension(200, 200));
		imP.setBounds(800, 100, 250, 250);
		
		/* Add the image panel to the slide */
		frame.add(imP);
			
		frame.repaint();
		frame.validate();
	}
}
