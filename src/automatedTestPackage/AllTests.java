package automatedTestPackage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import graphicsModule.*;
import gUIModule.*;
import imageModule.*;
import musicPlayerModule.*;
import presentation.*;
import videoModule.*;

@RunWith(Suite.class)
@SuiteClasses({
    // Book module
    
    // graphics module 
    GraphicsTestT101.class,
    // gui module
    CalculatorPanelTestT201.class,
    DicePanelTestT203.class,
    // Image module
    ImagePainterTestT301INTERNAL.class,
    // music player module
    EmbeddedMusTestT002.class,
    MusButtonTestT001.class,
    // Presentation Module
    SlideObjectAttributesTestT401.class,
    SlidePresentationTestT403.class,
    SlideShapeTestT404.class,
    SlideSoundTestT406.class,
    SlideTextContentTestT408.class,
    SlideTextTestT407.class,
    SlideVideoTestT409.class,

    VideoPlayerTestT601.class
})

/**
 * 
 * @author Joshua Lant
 *
 */
public class AllTests {
   
}
