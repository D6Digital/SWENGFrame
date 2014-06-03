package automatedTestPackage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


import bookModule.*;
import graphicsModule.*;
import gUIModule.*;
import imageModule.*;
import main.*;
import musicPlayerModule.*;
import presentation.*;
import textModule.*;
import videoModule.*;

@RunWith(Suite.class)
@SuiteClasses({
    // Book module
    // TODO:
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
    // src package
    // TODO:
    // text module
    // TODO:
    // Video Module
    VideoPlayerTestT601.class
})

public class AllTests {
   
}
