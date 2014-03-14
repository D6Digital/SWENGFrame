package gUIModule;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import gUIModule.UtilitiesPanel;
import gUIModule.DicePanel;
import gUIModule.CalculatorPanel;

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
	
	Container selectionPane;
	Container menuPane;
	Container bookPane;
	Container videoPane;
	Container audioPane;
	Container utilitiesPane;
	Container dicePane;
	Container calculatorPane;
	
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
				
				selectionPane = getContentPane();
				selectionPane.setLayout(new BorderLayout());
				//BookPanel();
				break;
			case "mainMenuPanel":
				setTitle("Grimoire");
				setSize(1000, 500);
				setVisible(true);
				
				menuPane = getContentPane();
				menuPane.setLayout(new BorderLayout());
				//MenuPanel();
				break;
			case "bookMainPanel":
				setTitle("Girmoire");
				setSize(1000, 500);
				setVisible(true);
				
				bookPane = getContentPane();
				bookPane.setLayout(new BorderLayout());
				
				//ContentsPanel contentsPanel = new ContentsPanel(/*TODO fill inputs*/);
				//bookPane.add(contentsPanel, BorderLayout.CENTER);
				//ControlPanel();
				break;
			case "videoDisplayPanel":
				setTitle("Video Guide");
				setSize(1000, 500);
				setVisible(true);
				
				videoPane = getContentPane();
				videoPane.setLayout(new BorderLayout());
				//VideoPanel();
				break;
			case "audioMenuPanel":
				setTitle("Music");
				setSize(200, 300);
				setVisible(true);
				
				audioPane = getContentPane();
				audioPane.setLayout(new BorderLayout());
				//AudioPanel();
				break;
			case "utilitiesSelectionPanel":
				setTitle("Utilities");
				setSize(200, 225);
				setVisible(true);
				
				utilitiesPane = getContentPane();
				utilitiesPane.setLayout(new BorderLayout());
				
				UtilitiesPanel utilitiesSelectionPanel = new UtilitiesPanel();
				utilitiesPane.add(utilitiesSelectionPanel, BorderLayout.CENTER);
				break;
			case "diceRollerPanel":
				setTitle("Dice Roller");
				setSize(405, 640);
				setVisible(true);
				
				dicePane = getContentPane();
				dicePane.setLayout(new BorderLayout());
				
				DicePanel diceRollerPanel = new DicePanel();
				dicePane.add(diceRollerPanel, BorderLayout.CENTER);
				break;
			case "calculatorPanel":
				setTitle("Combat Modifier Calculator");
				setSize(400, 600);
				setVisible(true);
				
				calculatorPane = getContentPane();
				calculatorPane.setLayout(new BorderLayout());
				
				CalculatorPanel calculatorPanel = new CalculatorPanel();
				calculatorPane.add(calculatorPanel, BorderLayout.CENTER);
				break;
			default:                     
				//???DefaultPanel()???
				break;
		};
	}
}