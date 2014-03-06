/**
 * 
 */
package textModule;


import javax.swing.JLabel;


/**
 * @author Robert Mills
 * @author Matt Winstanley
 *
 */
public class TextPainter {

	/**
	 * 
	 */
	public JLabel ProduceLabel(String text) {
		// create JLabel
		JLabel textLabel = new JLabel(text, JLabel.LEFT);
		
		//Set JLabel to opaque so it is visible
		textLabel.setOpaque(true);
        
        return textLabel;
	}
}
