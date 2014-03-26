package textModule;

import java.awt.Dimension;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import presentation.Text;
import presentation.TextContent;
import presentation.TextContent.ScriptTypeDef;
//import Scribe    ( required for Tribe )

/**
 * Test of the Scribe class
 * 
 * @Author Sam Pick
 */


public class ScribeExample {

	
/**
 * This is a simple example of using the scribe module with some
 * hard-coded text objects
 * 
 * This is the first version of scribe and there will be updates
 * 
 * @param args
 */
public static void main(String[] args) {
	
	TextContent myText1 = new TextContent();
	myText1.setBold(true);
	myText1.setTextString("\u2022\tThis is bold \n\n");
	
	
	TextContent myText2 = new TextContent();
	myText2.setItalic(true);
	myText2.setTextString("\t\u25E6\tThis is not ");
	
	
	TextContent myText3 = new TextContent();
	myText3.setScriptType(ScriptTypeDef.subScript);
	myText3.setTextString("This is Subscript \n");
	
	ArrayList<TextContent> textContents = new ArrayList<TextContent>(0);;
	textContents.add(myText1);
	textContents.add(myText2);
	textContents.add(myText3);
	Text exampleText = new Text(0, 0, 0, 0, 0, "Times New Roman", textContents, "#670067", 30,0);
	
	JLabel background = imageModule.ImagePainter.produceImage("/images/logo.jpg");
	background.setBounds(0,0,500,150);
	
	JLayeredPane LPane = new JLayeredPane();
	LPane.setBounds(50,50,600,150);
	LPane.setLayout(null);
	
	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setPreferredSize(new Dimension(640,480));
	frame.setLayout(null);
	Scribe shakespeare = new Scribe(exampleText);
	shakespeare.setOpaque(false);
	
	shakespeare.setBounds(0,0,500,150);
	LPane.add(shakespeare, 1);
	LPane.add(background, 2);
	frame.add(LPane);
	frame.pack();
	frame.setVisible(true);
	
	
}

	
	
}
