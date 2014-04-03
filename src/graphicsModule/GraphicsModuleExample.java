package graphicsModule;

import javax.swing.JFrame;
import Graphics.graphicsObject;

/**
 * Simple demonstration class of graphics module functionality
 * 
 * @author Sam Nicholson
 *
 */
@SuppressWarnings("serial")
public class GraphicsModuleExample extends JFrame
{
	/**
	 * Main function. Instantiates the sample class.
	 * 
	 * @param args Command line arguments. Unused
	 */
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		GraphicsModuleExample testframe = new GraphicsModuleExample();
	}

	/**
	 * Constructor. Creates a JFrame and renders a basic shape onto it
	 */
	public GraphicsModuleExample()
	{
		JFrame frame = new JFrame("This is a slideshow");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		
		/* Create a 5 point shape */
		graphicsObject Graphics1 = new graphicsObject("#ffffff", "#ffffff");
		Graphics1.setTotalPoints(5);
		Graphics1.setPoint(1, 300, 300);
		Graphics1.setPoint(2, 200, 50);
		Graphics1.setPoint(3, 100, 150);
		Graphics1.setPoint(4, 50, 400);
		Graphics1.setPoint(5, 250, 400);
		Graphics1.setFillColor("#524354");
		Graphics1.setLineColor("#f44555");
		frame.add(Graphics1);

	}

}
