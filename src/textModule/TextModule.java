package textModule;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTML;





/**
 * The contracted module for TRiBE which will accept their text objects to produce a transparent text JPanel
 * @author samPick
 *
 */
public class TextModule extends JPanel implements MouseListener, MouseMotionListener{

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
	public TextModule(Text text) {
		
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
		textPane.addMouseListener(this);
		textPane.addMouseMotionListener(this);
		
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
		int i = 0;
		while(textObject.getBody(i) != null )
		{
			TextBody text;
			text = textObject.getBody(i);
			i++;
			
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
	 * adds a default style
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
		myText1.setText("\u2022\tthis is bold \n\n");
		
		
		TextBody myText2 = new TextBody();
		myText2.setItalic(true);
		myText2.setText("\t\u25E6\tThis is not ");
		
		
		TextBody myText3 = new TextBody();
		myText3.setText("This is not Subscript \n");
		
		TextBody myText4 = new TextBody();
		myText4.setText("NOT TO GOOGLE");
		
		ArrayList<TextBody> textContents = new ArrayList<TextBody>(0);;
		textContents.add(myText1);
		textContents.add(myText2);
		textContents.add(myText3);
		textContents.add(myText4);
		Text exampleText = new Text();
		
		
		JFrame frame = new JFrame();
		frame.setSize(640, 400);
		frame.setLayout(new BorderLayout());
		TextModule shakespeare = new TextModule(exampleText);
		frame.add(shakespeare);
		frame.setVisible(true);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
		JTextPane textPane = (JTextPane) e.getSource();
		Point pt = new Point(e.getX(), e.getY());
		int pos = textPane.viewToModel(pt);
	
		
		if (pos >= 0)
		{
			StyledDocument doc = textPane.getStyledDocument();
			if (doc instanceof StyledDocument){
				StyledDocument hdoc = (StyledDocument) doc;
				Element el = hdoc.getCharacterElement(pos);
				AttributeSet a = el.getAttributes();
				String href = (String) a.getAttribute(HTML.Attribute.HREF);
				
				if (href != null){
					try{                            
						java.net.URI uri = new java.net.URI( href );
						desktop.browse( uri );
                       }
					catch ( Exception ev ){
						System.err.println( ev.getMessage() );
                       }
                }           
			}
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Cursor handCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
		
		JTextPane textPane = (JTextPane) e.getSource();
		Point pt = new Point(e.getX(), e.getY());
		int pos = textPane.viewToModel(pt);
		
		if (pos >= 0)
		{
			StyledDocument doc = textPane.getStyledDocument();
			
			if (doc instanceof StyledDocument){
				StyledDocument hdoc = (StyledDocument) doc;
				Element el = hdoc.getCharacterElement(pos);
				AttributeSet a = el.getAttributes();
				String href = (String) a.getAttribute(HTML.Attribute.HREF);
				
				if (href != null){
					if(getCursor() != handCursor){
						textPane.setCursor(handCursor);
					}
				}
				else{
					textPane.setCursor(defaultCursor);
				}
             }           
		}
	}
		
}
	

	
	

