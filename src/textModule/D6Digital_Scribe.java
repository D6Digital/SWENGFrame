package textModule;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;






/**
 * The contracted module for TRiBE which will accept their text objects to produce a transparent text JPanel
 * @author samPick
 *
 */
public class D6Digital_Scribe extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Font font;
	private Text textObject;

	/**
	 * produces a JPanel containing text from the Text object which is not editable
	 * and is transparent by default. when resized so that lines of text are longer than
	 * the width the text will wrap onto the next line, text at the bottom will be invisible 
	 * if the height of the panel is too small to contain the text.
	 * @param text
	 */
	public D6Digital_Scribe(Text text) {
		
		this.setOpaque(false);
		
		textObject = text;
		
		
		
		
		font = new Font(textObject.getFont(), Font.PLAIN, 12);
		if(font.getFamily().equals("Dialog")){
		// create a font object for a user defined font
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			for (String name : ge.getAvailableFontFamilyNames()) {
				System.out.println(name);
			}
			try {
			    font = Font.createFont(Font.TRUETYPE_FONT, new File(textObject.getFont()));
			    font = font.deriveFont(Font.PLAIN,textObject.getFontSize());
	
				ge.registerFont(font);
			} catch (FontFormatException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		setLayout(new BorderLayout());
		
		// Create the JTextPane
		JTextPane textPane = createTextPane();
		textPane.setEditable(false);
		
		add(textPane);
		
	}
	
	/**
	 * Uses the text contents to add sections of similarly formatted text
	 * @return The text pane
	 */
	private JTextPane createTextPane() {
		
		TransparentTextPane textPane = new TransparentTextPane();
		StyledDocument doc = textPane.getStyledDocument();
		addStylesToDocument(doc);
		
		Style newStyle = doc.addStyle("newStyle", doc.getStyle("defaultStyle"));
		for(TextBody text : textObject.getTextBody())
		{
			
			newStyle = doc.getStyle("defaultStyle");
	
			StyleConstants.setBold(newStyle, text.getBold());
			StyleConstants.setItalic(newStyle, text.getItalic());
			StyleConstants.setUnderline(newStyle, text.getUnderlined());
			
			
			
			try{
				doc.insertString(doc.getLength(), text.getText(), newStyle);
			} catch (BadLocationException ble) {
				System.err.println("could't insert text into JTextPane");
			}
		}
		
		return textPane;
	}

	/**
	 * adds the default style to the document which uses the font, size and colour in Text
	 * @param doc
	 */
	private void addStylesToDocument(StyledDocument doc) {
		
		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		
		Style defaultStyle = doc.addStyle("defaultStyle", def);
		StyleConstants.setFontFamily(defaultStyle,font.getFamily());
		StyleConstants.setFontSize(defaultStyle, textObject.getFontSize());
		
		int[] RGB = {0, 0, 0};
		
		Color colourReturn;
		if (textObject.getFontColor().charAt(0) == '#'){
			String colourHex = textObject.getFontColor().substring(1,7);
			RGB[0] = Integer.parseInt(colourHex.substring(0,2), 16);
			RGB[1] = Integer.parseInt(colourHex.substring(2,4), 16);
			RGB[2] = Integer.parseInt(colourHex.substring(4,6), 16);
		}
		colourReturn = new Color(RGB[0], RGB[1], RGB[2]);
		
		StyleConstants.setForeground(defaultStyle,colourReturn);
		
	}
	
	
	/**
	 * Used to text the text module but will later be removed.
	 * Eventually just the constructor will be used which produces the JPanel
	 */
	public static void showAndDisplayGUI()
	{
		TextBody myText1 = new TextBody();
		myText1.setBold(true);
		myText1.setText("\u2022\tThis is bold! \n\n");
		
		
		TextBody myText2 = new TextBody();
		myText2.setItalic(true);
		myText2.setText("\t\u25E6\tI would challenge you to a battle of wits, but I see you are unarmed! \n\n");
		
		
		TextBody myText3 = new TextBody();
		myText3.setUnderlined(true);
		myText3.setText("\n\nThis is underlined");
		
		Text exampleText = new Text();
		exampleText.setBody(myText1);
		exampleText.setBody(myText2);
		exampleText.setBody(myText3);
		
		exampleText.setFontSize(30);
		exampleText.setFontColor("#5555FF");
		
		JFrame frame = new JFrame();
		frame.setSize(640, 400);
		frame.setLayout(new BorderLayout());
		D6Digital_Scribe shakespeare = new D6Digital_Scribe(exampleText);
		shakespeare.setBackground(new Color(0,200,100));
		frame.add(shakespeare);
		frame.setVisible(true);
	}


}

