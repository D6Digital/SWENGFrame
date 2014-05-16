package presentation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * T406, a test to check that the sound object parameters are all working correctly.
 * @author Joshua Lant
 *
 */
public class SlideSoundTestT406 {
    Boolean loop;
    Integer start, duration;
    String file;

    @Before
    public void setUp() throws Exception {
        loop = true;
        start = 125432643;
        duration = 6432;
        file = "fewfewfewfewfewfewfewfewfewfew";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testStep1to2() {
        // Step 1. instantiate a sound object supplying all parameters to constructor.
        Sound sound = new Sound(start, duration, file, loop,0);
        
        // Step 2. check that getters return all correct values from the input parameters.
        assertEquals("start getter or constructor not working properly.",
                start, sound.getStart());
        assertEquals("duration getter or constructor not working properly.",
                duration, sound.getDuration());
        assertEquals("loop getter or constructor not working properly.",
                loop, sound.getLoop());
        assertEquals("file getter or constructor not working properly.",
                file, sound.getFile());
       
        
    }
    
    @Test
    public void testStep3to4() {
        // Step 3. instantiate a sound object supplying nothing to constructor.
        Sound sound = new Sound();
        
        loop = false;
        start = 12643;
        duration = 64343242;
        file = "fewfgdsgfdsgfdsgfdw";
        
        // Step 4. Check that all setters and getter work correctly.
        sound.setStart(start);
        sound.setDuration(duration);
        sound.setLoop(loop);
        sound.setFile(file);
      
        assertEquals("start getter or setter not working properly.",
                start, sound.getStart());
        assertEquals("duration getter or setter not working properly.",
                duration, sound.getDuration());
        assertEquals("loop getter or setter not working properly.",
                loop, sound.getLoop());
        assertEquals("file getter or setter not working properly.",
                file, sound.getFile()); 
    }

}
