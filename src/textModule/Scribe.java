package textModule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
	Font font;

	public Scribe() {
		
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			//font = Font.createFont(Font.TRUETYPE_FONT, new File("M:\\JAVA\\SWENG\\SWENGFrame\\src\\textModule\\ChineseTakeaway.ttf"));
			//font.deriveFont(Font.PLAIN,24);
			//InputStream myStream = new BufferedInputStream(new FileInputStream("C:\\xtemp\\NINJAS.TTF"));
			
			
		    font = Font.createFont(Font.TRUETYPE_FONT, new File("ChineseTakeaway.ttf"));
		    font = font.deriveFont(Font.PLAIN,50);

			ge.registerFont(font);
		} catch (FontFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		
		
		
		
		
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
		StyleConstants.setFontFamily(myStyle,"Chinese Takeaway");
		StyleConstants.setFontSize(myStyle, 60);
		StyleConstants.setBold(myStyle, true);
		StyleConstants.setForeground(myStyle, Color.CYAN);
		
	}
	
	public static void showAndDisplayGUI()
	{
		System.out.println(System.getProperty("user.dir"));
		JFrame frame = new JFrame();
		frame.setSize(320, 240);
		frame.setLayout(new BorderLayout());
		Scribe scribeTest = new Scribe();
		frame.add(scribeTest);
		JLabel fontLabel = new JLabel(scribeTest.font.getFamily());
		fontLabel.setFont(scribeTest.font);
		//frame.add(fontLabel);
		frame.setVisible(true);
	}
	
	
	
	
}
