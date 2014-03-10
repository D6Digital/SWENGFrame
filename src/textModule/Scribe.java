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
import java.util.ArrayList;
import java.util.Iterator;

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

import slideModule.Text;
import slideModule.TextContent;
import slideModule.TextContent.ScriptTypeDef;

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
	
	private Font font;
	private Text textObject;

	/**
	 * produces a JPanel containing text from the text object
	 * @param text
	 */
	public Scribe(Text text) {
		
		textObject = text;
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			
			
		    font = Font.createFont(Font.TRUETYPE_FONT, new File(textObject.getFile()));
		    font = font.deriveFont(Font.PLAIN,textObject.getSize());

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
		
		
		Style newStyle = doc.addStyle("newStyle", doc.getStyle("defaultStyle"));
		
		
		
		for (TextContent text : textObject.getText()) {
			
			newStyle = doc.getStyle("defaultStyle");
			
			StyleConstants.setBold(newStyle, text.isBold());
			StyleConstants.setItalic(newStyle, text.isItalic());
			StyleConstants.setUnderline(newStyle, text.isUnderlined());
			
			if(text.getScriptType() == ScriptTypeDef.superScript){
				StyleConstants.setSuperscript(newStyle, true);
				StyleConstants.setForeground(newStyle, Color.RED);
			}
			else{ 
				if(text.getScriptType() == ScriptTypeDef.subScript){
					StyleConstants.setSubscript(newStyle, true);
				}
			}
			
			if(text.isNewLine())
			{
				try{
					doc.insertString(doc.getLength(), "\n", doc.getStyle("newStyle"));
				} catch (BadLocationException ble) {
					System.err.println("could't insert text into JTextPane");
				}
			}
			
			try{
				doc.insertString(doc.getLength(), text.getTextString(), newStyle);
			} catch (BadLocationException ble) {
				System.err.println("could't insert text into JTextPane");
			}
		}
		
		
		
		return textPane;
	}

	/**
	 * adds a default style
	 * @param doc
	 */
	private void addStylesToDocument(StyledDocument doc) {
		
		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		
		Style defaultStyle = doc.addStyle("defaultStyle", def);
		StyleConstants.setFontFamily(defaultStyle,font.getFamily());
		StyleConstants.setFontSize(defaultStyle, textObject.getSize());
		//StyleConstants.setFontSize(myStyle, 60);
		//StyleConstants.setBold(myStyle, true);
		StyleConstants.setForeground(defaultStyle,textObject.getColourObject());
		
	}
	
	public static void showAndDisplayGUI()
	{
		TextContent myText1 = new TextContent();
		myText1.setBold(true);
		myText1.setTextString("this is bold \n");
		
		
		TextContent myText2 = new TextContent();
		myText2.setItalic(true);
		myText2.setTextString("This is not ");
		
		
		TextContent myText3 = new TextContent();
		myText3.setScriptType(ScriptTypeDef.superScript);
		myText3.setTextString("This is Super duper");
		
		ArrayList<TextContent> textContents = new ArrayList<TextContent>(0);;
		textContents.add(myText1);
		textContents.add(myText2);
		textContents.add(myText3);
		Text exampleText = new Text(0, 0, 0, 0, 0, "space age.ttf", textContents, "#FFA0FF", 30);
		
		
		System.out.println(System.getProperty("user.dir"));
		JFrame frame = new JFrame();
		frame.setSize(320, 240);
		frame.setLayout(new BorderLayout());
		Scribe shakespeare = new Scribe(exampleText);
		frame.add(shakespeare);
		JLabel fontLabel = new JLabel(shakespeare.font.getFamily());
		fontLabel.setFont(shakespeare.font);
		//frame.add(fontLabel);
		frame.setVisible(true);
	}
	
	
	
	
}
