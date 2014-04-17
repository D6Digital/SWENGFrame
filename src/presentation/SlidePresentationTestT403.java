package presentation;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * T403, A test to prove all getters and setters work correctly for the presentation class.
 * @author Joshua Lant
 *
 */
public class SlidePresentationTestT403 {
    ArrayList<Slide> slideList = new ArrayList<Slide>(0);
    ArrayList<Slide> slideListRetrieve;
    String author, comment, version, backgroundColour, fontColour, font, lineColour, fillColour;
    int width, height, fontSize;
    int size;
    
    @Before
    public void setUp() throws Exception {
        author = "dweahtewh";
        comment = "e4ghgfdasjh";
        version = "g43hywhrew";
        backgroundColour = "gbrfeinoj32e";
        fontColour = "t42gwgb";
        font = "grerewyr3";
        lineColour = "vdsha";
        fillColour = "r32tgbdws";
        width = 21342;
        height = 69291785;
        fontSize = 2135;
        
        size = 10000;
        
        for(int i = 0; i < size; i ++) {
            Slide slide = new Slide();
            slideList.add(slide);
        }
        
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        // Step 1. Confirm that getters and setters are provided for all attributes...
        Presentation presentation = new Presentation();
        
        presentation.setAuthor(author);
        presentation.setComment(comment);
        presentation.setVersion(version);
        presentation.setBackgroundColour(backgroundColour);
        presentation.setFontColour(fontColour);
        presentation.setFont(font);
        presentation.setLineColour(lineColour);
        presentation.setFillColour(fillColour);
        presentation.setWidth(width);
        presentation.setHeight(height);
        presentation.setFontSize(fontSize);
        
        presentation.setSlideList(slideList);
        
        assertEquals("cannot get or set author properly",
                author, presentation.getAuthor());
        assertEquals("cannot get or set comment properly",
                comment, presentation.getComment());
        assertEquals("cannot get or set version properly",
                version, presentation.getVersion());
        assertEquals("cannot get or set background colour properly",
                backgroundColour, presentation.getBackgroundColour());
        assertEquals("cannot get or set font colour properly",
                fontColour, presentation.getFontColour());
        assertEquals("cannot get or set font properly",
                font, presentation.getFont());
        assertEquals("cannot get or set line colour properly",
                lineColour, presentation.getLineColour());
        assertEquals("cannot get or set fill colour properly",
                fillColour, presentation.getFillColour());
        assertEquals("cannot get or set width properly",
                width, presentation.getWidth());
        assertEquals("cannot get or set height properly",
                height, presentation.getHeight());
        assertEquals("cannot get or set font size properly",
                fontSize, presentation.getFontSize());
        
        assertEquals("cannot get slide list properly",
                size, presentation.getSlideList().size());
        
        
        // Step 2. Confirm that you can retrieve any slide object from a large list of slides by providing an integer index.
        assertNotNull("Could not retrieve slide from list of slides",  presentation.get(5004));
       
        
        // Step 3. Confirm that you can add and remove slides from the list of slides.
        Slide slide = new Slide();
        presentation.add(slide);
        assertEquals("cannot get slide list properly",
                size + 1, presentation.getSlideList().size());
        
        // TODO: complete test once question, #12 has been answered. possible test rewrite...
        
        
        
    }

}
