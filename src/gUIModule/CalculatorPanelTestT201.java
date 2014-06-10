package gUIModule;

import javax.swing.JFrame;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A test to check that the calculator panel is in working order.
 * This test has not been automated and simply provides a frame with
 * calculator panel on, for lone testing of the module without integration
 * into system.
 *
 */
public class CalculatorPanelTestT201 {

    CalculatorPanel calculatorPanel;
    JFrame frame; 
    
    @Before
    public void setUp() throws Exception {
        calculatorPanel = new CalculatorPanel(300, 500, null);
        frame = new JFrame("Calc Test");
        frame.setLayout(null);
        frame.setSize(400, 800);
        frame.add(calculatorPanel);
        frame.setVisible(true);
        frame.validate();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        boolean x = true;
        do {
            
        }
        while(x);
        System.err.println("T201 TEST NOT IMPLEMENTED WITH AUTOMATION");
        System.err.println("FAIL ISSUE#24, ISSUE#25, ISSUE#26");
        System.err.println("PASS ONCE RESOLVED");
    }

}
