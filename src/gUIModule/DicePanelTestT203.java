package gUIModule;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A test to check that the Dice panel is in working order. Produces
 * a frame upon which the dice panel sits, the testing is performed
 * with no integration in system.
 *
 */
public class DicePanelTestT203 {

    DicePanel dicePanel;
    JFrame frame;
    
    @Before
    public void setUp() throws Exception {
        frame = new JFrame("Dice Test");
        frame.setSize(400, 600);
        frame.setVisible(true);
        dicePanel = new DicePanel(400, 600, null);
        
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws InterruptedException {
        frame.add(dicePanel);
        frame.validate();
        
        // Step 1. The user should be able to select the number of dice to roll from 1-100.
        for(int i = 0; i < 100; i++) {
        int j = i + 1;
        dicePanel.diceQuantity.setSelectedIndex(i);
        assertEquals("Dice values were not 1-100...",
                j, dicePanel.diceQuantity.getSelectedItem());
        
        }
        
        // Step 2. User should be able to select the number of dice types they wish to roll.
        dicePanel.multiDice.doClick();
        assertTrue("cannot have multiple dice types...",
             dicePanel.multiDiceEnabled);
        dicePanel.secondDiceType.setSelectedIndex(0);
        dicePanel.diceType.setSelectedIndex(1);
        assertTrue("cannot have multiple dice types...",
             dicePanel.secondDiceType != dicePanel.diceType);   
        
        // Step 3. The types of dice that the user can roll should be D2, D4, D6, D10, D20, D100.
        Boolean d2, d3, d4, d6, d8, d10, d12, d20, d100;
        d2 = false;
        d3 = false;
        d4 = false;
        d6 = false;
        d8 = false;
        d10 = false;
        d12 = false;
        d20 = false;
        d100 = false;
         
        int length = dicePanel.diceTypes.length;
        for(int i = 0; i < length; i++) {
            if(dicePanel.diceTypes[i] == "d2") {
                d2 = true;
                continue;
            }
            if(dicePanel.diceTypes[i] == "d3") {
                d3 = true;
                continue;
            }
            else if(dicePanel.diceTypes[i] == "d4") {
                d4 = true;
                continue;
            }
            else if(dicePanel.diceTypes[i] == "d6") {
                d6 = true;
                continue;
            }
            else if(dicePanel.diceTypes[i] == "d8") {
                d8 = true;
                continue;
            }
            else if(dicePanel.diceTypes[i] == "d10") {
                d10 = true;
                continue;
            }
            else if(dicePanel.diceTypes[i] == "d12") {
                d12 = true;
                continue;
            }
            else if(dicePanel.diceTypes[i] == "d20") {
                d20 = true;
                continue;
            }
            else if(dicePanel.diceTypes[i] == "d100") {
                d100 = true;
                continue;
            }
            else {
                fail("A dice type was found that was not in the agreed types:" + dicePanel.diceTypes[i]);
            }
        }
        
        assertTrue("d2 was not in list", d2);
        assertTrue("d3 was not in list", d3);
        assertTrue("d4 was not in list", d4);
        assertTrue("d6 was not in list", d6);
        assertTrue("d8 was not in list", d8);
        assertTrue("d10 was not in list", d10);
        assertTrue("d12 was not in list", d12);
        assertTrue("d20 was not in list", d20);
        assertTrue("d100 was not in list", d100);

        // Step 4. Visual inspection. Perform calculation.
        System.err.println("TEST T203 STEP 4 SUCCESSFUL, VISUAL INSPECTION, NO AUTOMATION REQUIRED...");
        
        
    }

}
