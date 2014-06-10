package videoModule;

import java.awt.Component;
import javax.swing.AbstractButton;
import javax.swing.JButton;

/**
 * Class that produces a button with a given title and colour.
 *
 */
public class VideoPainter {
	/**  
	 * This method creates a JButton with the given title and colour
	 * @param filename
	 * @return videoButton
	 */
	public static JButton ProduceButton(String filename){
		//Create and setup new JButton
		JButton videoButton = new JButton(filename);
		videoButton.setVerticalTextPosition(AbstractButton.CENTER);
		videoButton.setHorizontalTextPosition(AbstractButton.CENTER);
		videoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		return videoButton;
	}
	
}
