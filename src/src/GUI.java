package src;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

/**
 * 
 * @author Andrew Walter
 *
 */
public class GUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Container contentPane;
	String panelType = "utilitiesSelectionPanel";
	String windowName = "Utilities";
	int width = 200;
	int height = 250;
	
	/**
	 * Create a simple JFrame and then populate it with specified JPanel type
	 * string panelType, string windowName int width, int height
	 * @return 
	 */

	public GUI() {
		setTitle("Grimoire - " + windowName);
		setSize(width, height);
		setVisible(true);
		
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		switch (panelType) {
			case "bookSelectionPanel":   
				//BookPanel();
				break;
			case "mainMenuPanel":          
				//MenuPanel();
				break;
			case "bookMainPanel":          
				//ContentsPanel();
				//ControlPanel();
				break;
			case "videoDisplayPanel":      
				//VideoPanel();
				break;
			case "audioMenuPanel":         
				//AudioPanel();
				break;
			case "utilitiesSelectionPanel":
				UtilitiesPanel utilitiesSelectionPanel = new UtilitiesPanel();
				contentPane.add(utilitiesSelectionPanel, BorderLayout.CENTER);
				break;
			case "diceRollerPanel":        
				//DicePanel();
				break;
			case "calculatorPanel":        
				//CalculatorPanel();
				break;
			default:                     
				//???DefaultPanel()???
				break;
		};
	}
}