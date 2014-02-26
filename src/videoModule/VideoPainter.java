/**
 * 
 */
package videoModule;

import java.awt.Color;
import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.JButton;


/**
 * @author Robert
 *
 */
public class VideoPainter {

	/**
	 * 
	 */
	public VideoPainter() {
		// TODO Auto-generated constructor stub
	}
	
	/**  
	 * This method creates a JButton with the given title and colour
	 * @author Sam L 
	 * @param filename
	 * @param colour
	 * @return videoButton
	 */
	public static JButton ProduceButton(String filename, Color colour){
		
		//Create and setup new JButton
		JButton videoButton = new JButton(filename);
		videoButton.setVerticalTextPosition(AbstractButton.CENTER);
		videoButton.setHorizontalTextPosition(AbstractButton.CENTER);
		videoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		videoButton.setBackground(colour);

		return videoButton;
		
	}
	
}
