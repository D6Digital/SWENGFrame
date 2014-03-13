/**
 * 
 */
package textModule;


import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import presentation.Text;
import presentation.TextContent;
import presentation.TextContent.ScriptTypeDef;



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
		Text exampleText = new Text(0, 0, 0, 0, 0, "resources/ChineseTakeaway.ttf", textContents, "#670067", 30);
		
		
		JFrame frame = new JFrame();
		frame.setSize(640, 400);
		frame.setLayout(new BorderLayout());
		Scribe shakespeare = new Scribe(exampleText);
		frame.add(shakespeare);
		frame.setVisible(true);
	}
}
