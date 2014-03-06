package textModule;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 * 
 * @author samPick
 *
 */
public class Scribe extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public Scribe() {
		
		setLayout(new BorderLayout());
		
		// Create the JTextPane
		JTextPane textPane = createTextPane();
		
		add(textPane);
		
	}
	
	/**
	 * 
	 * @return The text pane
	 */
	private JTextPane createTextPane() {
		
		JTextPane textPane = new JTextPane();
		StyledDocument doc = textPane.getStyledDocument();
		addStylesToDocument(doc);
		
		String theString = " Initial Scribe Test";
		
		
		
		
		
		
		try{
			doc.insertString(doc.getLength(), theString, doc.getStyle("myStyle"));
		} catch (BadLocationException ble) {
			System.err.println("could't insert text into JTextPane");
		}
		
		
		return textPane;
	}

	
	private void addStylesToDocument(StyledDocument doc) {
		
		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		
		Style myStyle = doc.addStyle("myStyle", def);
		StyleConstants.setFontFamily(myStyle,"SansSerif");
		StyleConstants.setFontSize(myStyle, 26);
		StyleConstants.setBold(myStyle, true);
		
	}
	
	public static void showAndDisplayGUI()
	{
		JFrame frame = new JFrame();
		frame.setSize(320, 240);
		Scribe scribeTest = new Scribe();
		frame.add(scribeTest);
		frame.setVisible(true);
	}
	
	
	
	
}
