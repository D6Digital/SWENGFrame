/**
 * 
 */
package graphicsModule;

import java.awt.Color;
import javax.swing.JPanel;

/**
 * @author Robert
 *
 */
public class GraphicsPainter {

	/**
	 * 
	 */
	public GraphicsPainter() {
		// TODO Auto-generated constructor stub
	}
	
	/**  
	 * producePanel returns a JPanel of select width, height and colour
	 * @author Chris S 
	 * @param width 
	 * @param height 
	 * @param colour 
	 * @return graphicPanel 
	 */
	public static JPanel producePanel(int width, int height, Color colour){
		
		//Create and setup new JPanel
		JPanel graphicPanel = new JPanel();
		
		graphicPanel.setSize(width, height);
		graphicPanel.setBackground(colour);
		
		return graphicPanel;		
	}
	
	

}
