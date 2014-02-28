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

	public GUI(String panelType) {
		
		switch (panelType) {
			case "bookSelectionPanel":
				setTitle("Grimoire");
				setSize(1000, 500);
				setVisible(true);
				
				contentPane = getContentPane();
				contentPane.setLayout(new BorderLayout());
				//BookPanel();
				break;
			case "mainMenuPanel":
				setTitle("Grimoire");
				setSize(1000, 500);
				setVisible(true);
				
				contentPane = getContentPane();
				contentPane.setLayout(new BorderLayout());
				//MenuPanel();
				break;
			case "bookMainPanel":
				setTitle("Girmoire");
				setSize(1000, 500);
				setVisible(true);
				
				contentPane = getContentPane();
				contentPane.setLayout(new BorderLayout());
				//ContentsPanel();
				//ControlPanel();
				break;
			case "videoDisplayPanel":
				setTitle("Video Guide");
				setSize(1000, 500);
				setVisible(true);
				
				contentPane = getContentPane();
				contentPane.setLayout(new BorderLayout());
				//VideoPanel();
				break;
			case "audioMenuPanel":
				setTitle("Music");
				setSize(200, 300);
				setVisible(true);
				
				contentPane = getContentPane();
				contentPane.setLayout(new BorderLayout());
				//AudioPanel();
				break;
			case "utilitiesSelectionPanel":
				setTitle("Utilities");
				setSize(200, 225);
				setVisible(true);
				
				contentPane = getContentPane();
				contentPane.setLayout(new BorderLayout());
				
				UtilitiesPanel utilitiesSelectionPanel = new UtilitiesPanel();
				contentPane.add(utilitiesSelectionPanel, BorderLayout.CENTER);
				break;
			case "diceRollerPanel":
				setTitle("Dice Roller");
				setSize(400, 300);
				setVisible(true);
				
				contentPane = getContentPane();
				contentPane.setLayout(new BorderLayout());
				
				DicePanel diceRollerPanel = new DicePanel();
				contentPane.add(diceRollerPanel, BorderLayout.CENTER);
				break;
			case "calculatorPanel":
				setTitle("Mod. Calculator");
				setSize(200, 300);
				setVisible(true);
				
				contentPane = getContentPane();
				contentPane.setLayout(new BorderLayout());
				CalculatorPanel calculatorPanel = new CalculatorPanel();
				
				contentPane.add(calculatorPanel, BorderLayout.CENTER);
				break;
			default:                     
				//???DefaultPanel()???
				break;
		};
	}
}