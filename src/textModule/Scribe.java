package textModule;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import presentation.Text;
import presentation.TextContent;
import presentation.TextContent.ScriptTypeDef;


/**
 * 
 * @author samPick
 *
 */
public class Scribe extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MouseAdapter textHandListener;
	private MouseAdapter textbBranchListener;
	private Font font;
	private Text textObject;
	public JTextPane textPane;
	private double scaleFactor = 1;

	/**
	 * produces a JPanel containing text from the text object
	 * @param text
	 * @param textSizeScale 
	 */
	public Scribe(Text text, MouseAdapter listener, double textSizeScale) {
		
		textObject = text;
		this.scaleFactor = textSizeScale;
		
		this.setOpaque(false);
		
		font = new Font(textObject.getFile(), Font.PLAIN, 12);
		if(font.getFamily().equals("Dialog")){
		// create a font object for a user defined font
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			for (String name : ge.getAvailableFontFamilyNames()) {
				System.out.println(name);
			}
			try {
			    font = Font.createFont(Font.TRUETYPE_FONT, new File(textObject.getFile()));
			    font = font.deriveFont(Font.PLAIN,textObject.getSize());
	
				ge.registerFont(font);
			} catch (FontFormatException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		setLayout(new BorderLayout());
		setupHandListener();
		setupBranchListener();
		
		
		
		// Create the JTextPane
		textPane = createTextPane();
		textPane.setVisible(false);
		textPane.setBackground(new Color(255,255,255,0));
		textPane.setEditable(false);
		textPane.addMouseMotionListener(listener);
		textPane.addMouseListener(listener);
		textPane.setMaximumSize(new Dimension(text.getXend()-text.getX_coord(),text.getYend()-text.getY_coord()));
		
		add(textPane);
		textPane.setVisible(true);
		
	}

	/**
	 * produces a JPanel containing text from the text object
	 * @param text
	 * @param textSizeScale 
	 */
	public Scribe(Text text) {
		
		textObject = text;
		
		this.setOpaque(false);
		
		font = new Font(textObject.getFile(), Font.PLAIN, 12);
		if(font.getFamily().equals("Dialog")){
		// create a font object for a user defined font
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			// Use the commented lines below to see the available fonts
			/*for (String name : ge.getAvailableFontFamilyNames()) {
				System.out.println(name);
			}*/
			try {
			    font = Font.createFont(Font.TRUETYPE_FONT, new File(textObject.getFile()));
			    font = font.deriveFont(Font.PLAIN,(float) (textObject.getSize()*scaleFactor));
	
				ge.registerFont(font);
			} catch (FontFormatException | IOException e1) {
				e1.printStackTrace();
			}
		}

		setLayout(new BorderLayout());
		
		// Create the JTextPane
		textPane = createTextPane();
		textPane.setEditable(false);
		
		textPane.setBackground(new Color(255,255,255,0));
		//textPane.addMouseListener(this);
		textPane.addMouseMotionListener(textHandListener);
		textPane.addMouseListener(textbBranchListener);
		
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
		//System.out.println(textObject.getText().size());
		for (TextContent text : textObject.getText()) {
			
			newStyle = doc.getStyle("defaultStyle");
			
			StyleConstants.setBold(newStyle, text.isBold());
			StyleConstants.setItalic(newStyle, text.isItalic());
			StyleConstants.setUnderline(newStyle, text.isUnderlined());
			StyleConstants.setLineSpacing(newStyle, 0);
			doc.setParagraphAttributes(0, text.getTextString().length(), newStyle,true);
			
			if(text.getScriptType() == ScriptTypeDef.superScript){
				StyleConstants.setSuperscript(newStyle, true);
				//StyleConstants.setForeground(newStyle, Color.RED);
			}
			else{ 
				if(text.getScriptType() == ScriptTypeDef.subScript){
					StyleConstants.setSubscript(newStyle, true);
				}
			}
			
			if(text.isHyperlink())
			{
				newStyle.addAttribute(HTML.Attribute.HREF, "www.google.com");
				StyleConstants.setForeground(newStyle, Color.BLUE);
				StyleConstants.setUnderline(newStyle, true);
				try{
					doc.insertString(doc.getLength(), "", doc.getStyle("newStyle"));
				} catch (BadLocationException ble) {
					System.err.println("could't insert text into JTextPane");
				}
			}
			
			if(text.getBranch() != null && text.getBranch() != -1)
			{
				newStyle.addAttribute(HTML.Attribute.LINK, text.getBranch());
			}
			else
			{
				newStyle.addAttribute(HTML.Attribute.LINK, -1);
			}
			
			if(text.getChapterBranch() != null && text.getChapterBranch() != -1)
			{
				newStyle.addAttribute(HTML.Attribute.TARGET, text.getChapterBranch());
			}
			else
			{
				newStyle.addAttribute(HTML.Attribute.TARGET, -1);
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
		StyleConstants.setFontSize(defaultStyle, (int) (textObject.getSize()*scaleFactor));
		StyleConstants.setForeground(defaultStyle,textObject.getColourObject());
		
	}
	
	
	/**
	 * Used to text the text module but will later be removed.
	 * Eventually just the constructor will be used which produces the JPanel
	 */
	public static void showAndDisplayGUI()
	{
		TextContent myText1 = new TextContent();
		myText1.setBold(true);
		myText1.setTextString("\u2022\tthis is bold \n\n");
		
		
		TextContent myText2 = new TextContent();
		myText2.setItalic(true);
		myText2.setTextString("\t\u25E6\tThis is not ");
		
		
		TextContent myText3 = new TextContent();
		myText3.setScriptType(ScriptTypeDef.subScript);
		myText3.setTextString("This is Subscript \n");
		
		TextContent myText4 = new TextContent();
		myText4.setHyperlink(true);
		myText4.setTextString("TO GOOGLE");
		
		ArrayList<TextContent> textContents = new ArrayList<TextContent>(0);;
		textContents.add(myText1);
		textContents.add(myText2);
		textContents.add(myText3);
		textContents.add(myText4);
		Text exampleText = new Text(0, 0, 0, 0, 0, "github resources/ChineseTakeaway.ttf", textContents, "#670067", 30,0);
		
		
		JFrame frame = new JFrame();
		frame.setSize(640, 400);
		frame.setLayout(new BorderLayout());
		Scribe shakespeare = new Scribe(exampleText);
		frame.add(shakespeare);
		frame.setVisible(true);
	}
	
	

	/**
	 * Creates a listener for text so that when the user clicks on a section of text which has a branch
	 * attribute it will cause an action such as going to a web page
	 */
	private void setupBranchListener() {
		textbBranchListener = new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e){
		
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
				Integer branch = (Integer) a.getAttribute(HTML.Attribute.LINK);
				
				if (branch != null && branch != -1){
					
				}
			}
		}
		
		
		}
	};
	}

	/**
	 * Creates a listener for text so that when the users mouse is over text with a branch attribute
	 * the cursor changes to a hand cursor indicating it can be clicked
	 */
	private void setupHandListener() {
		textbBranchListener = new MouseAdapter() {

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
						Integer branch = (Integer) a.getAttribute(HTML.Attribute.LINK);
						if (href != null || (branch != null && branch !=-1)){
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
			
		};
	}
				
}
	

	
	

