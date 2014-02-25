package gUIModule;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.Spring;

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
	/**
	 * Create a simple JFrame and then populate it with specified JPanel type
	 * @return 
	 */

	public GUI(String panelType, String windowName, int width, int height) {
		setTitle(windowName);
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
				DicePanel diceRollerPanel = new DicePanel();
				contentPane.add(diceRollerPanel, BorderLayout.CENTER);
				break;
			case "calculatorPanel":        
				CalculatorPanel calculatorPanel = new CalculatorPanel();
				contentPane.add(calculatorPanel, BorderLayout.CENTER);
				break;
			default:                     
				//???DefaultPanel()???
				break;
		};
	}
}