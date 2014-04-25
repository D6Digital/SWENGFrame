package textModule;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

/**
 * This is a simple example of using the scribe module with some
 * hard-coded text objects
 * 
 * This is the fully working version of D6Digital_Scribe and there will be updates if bugs arise
 * 
 */
public class D6Digital_ScribeExample {
	
	public static void main(String[] args) {
		
		
		
		TextBody myText1 = new TextBody();
		myText1.setBold(true);
		myText1.setText("\u2022\tThis is bold! \n\n");
		
		
		TextBody myText2 = new TextBody();
		myText2.setItalic(true);
		myText2.setText("\t\u25E6\tWhere art thou?  \n\n");
		
		
		TextBody myText3 = new TextBody();
		myText3.setUnderlined(true);
		myText3.setText("\n\nThis is underlined");
		
		Text exampleText = new Text();
		exampleText.setBody(myText1);
		exampleText.setBody(myText2);
		exampleText.setBody(myText3);
		
		exampleText.setFontSize(30);
		exampleText.setFontColor("#5555FF");
		exampleText.setFont("Arial");
		
		JFrame frame = new JFrame("Scribe example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 400);
		frame.setLayout(new BorderLayout());
		
		
		// The most important two lines, which create the JPanel containing the text (D6Digital_Scribe shakespeare)
		// then the panel is simply added to a JFrame, or it could be another JPanel, Pane etc
		D6Digital_Scribe shakespeare = new D6Digital_Scribe(exampleText);
		frame.add(shakespeare);
		
		
		frame.setVisible(true);
	}

}
