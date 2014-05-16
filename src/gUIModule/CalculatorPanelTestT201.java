package gUIModule;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorPanelTestT201 {

    CalculatorPanel calculatorPanel;
    JFrame frame; 
    
    @Before
    public void setUp() throws Exception {
        calculatorPanel = new CalculatorPanel();
        frame = new JFrame("Calc Test");
        frame.add(calculatorPanel);
        frame.setVisible(true);
        frame.validate();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        boolean x = false;
        do {
            
        }
        while(x);
        System.err.println("T201 TEST NOT IMPLEMENTED WITH AUTOMATION");
        System.err.println("FAIL ISSUE#24, ISSUE#25, ISSUE#26");
        System.err.println("PASS ONCE RESOLVED");
    }

}
