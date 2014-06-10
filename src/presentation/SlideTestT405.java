package presentation;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * T405, a test to check that the slide objects attributes can be retrieved and set properly...
 * @author Joshua Lant
 *
 */
public class SlideTestT405 {
    private ArrayList<SlideObject> objectList = new ArrayList<SlideObject>(0);
    private ArrayList<Image> imageList = new ArrayList<Image>(0);
    private ArrayList<Text> textList = new ArrayList<Text>(0);
    private ArrayList<Video> videoList = new ArrayList<Video>(0);
    private ArrayList<Sound> soundList = new ArrayList<Sound>(0);
    private ArrayList<Shapes> shapeList = new ArrayList<Shapes>(0);
    private Integer slideID;
    private String slideName;
    //private Integer duration2 = null;
    private Boolean isItLastSlide = false;
    
    private Integer x_coord = 123145;
    private Integer y_coord = 876543;
    private Integer start = 383838;
    private Integer duration = 9328;
    private Integer layer = 3;
    private String file = "WAHEEEYTESTING";
    private Integer branch = 808;
    private int width = 432;
    private Integer height = 4729999;
    private ArrayList<TextContent> text = new ArrayList<TextContent>(0);
    private TextContent textContent = new TextContent();
    private String colour = "HEXVALUE";
    private int size = 6000000;
    //private int length = 51372098;
    private Boolean loop = true;
    private Integer numberOfPoints =  63;
    private String fillColor = "ANOTHERHEX";
    private String lineColor = "SEXYHEXY";
    private ArrayList<Point> pointList = new ArrayList<Point>(0);;
    private Point point = new Point();
    
    private SlideObject slideObject = new SlideObject(x_coord, y_coord, start, duration, layer, file, branch);
    private Image image = new Image(x_coord, y_coord, start, duration, layer, file, width, height, branch);
    private Text textObject; 
    private Video video = new Video(x_coord, y_coord, start, duration, layer, file, width, height);
    private Sound sound = new Sound(start, duration, file, loop,0);   
    private Shapes shapes; 
    
    @Before
    public void setUp() throws Exception {
        textContent.setTextString("HOHOHOHOOHOHOH");
        text.add(textContent);
        textObject = new Text(x_coord, y_coord, start, duration, layer, file, text, colour, size, branch);
        
        
        point.setX(x_coord);
        pointList.add(point);
        shapes = new Shapes(start, duration, layer, file, numberOfPoints, width, height, fillColor, lineColor, pointList, branch);
        
        objectList.add(slideObject);
        imageList.add(image);
        textList.add(textObject);
        videoList.add(video);
        soundList.add(sound);
        shapeList.add(shapes);  
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSteps1and2() {
        // Step 1. Instantiate the slide object supplying values to the constructor.
        Slide slide = new Slide(
                objectList, imageList, textList, videoList, 
                soundList, shapeList, slideID, slideName, duration, isItLastSlide);
        
        // Step 2. Confirm that the constructor works correctly in setting the parameters.
        assertEquals("the SlideObject was not set correctly.",
                slide.getObjectList().get(0).getFile(), file);
        assertEquals("the ImageList was not set correctly.",
                slide.getImageList().get(0).getLayer(), layer);
        assertEquals("the TextList was not set correctly.",
                slide.getTextList().get(0).getSize(), size);
        assertEquals("the Sound List was not set correctly.",
                slide.getSoundList().get(0).getDuration(), duration);
        assertEquals("the Shape List was not set correctly.",
                slide.getShapeList().get(0).getLineColor(), lineColor);
        assertEquals("the Duration was not set correctly.",
                slide.getDuration(), duration);
        assertEquals("the last slide boolean was not set correctly.",
                slide.getLastSlide(), isItLastSlide); 
    }
    
    @Test
    public void testSteps3and4() {
        
        // Step 3. Instantiate the slide object with empty constructor.
        Slide slide = new Slide();
        slide.setDuration(duration);
        slide.setLastSlide(isItLastSlide);
        
        // Step 4. Use getters and setters to ensure that every parameter works correctly.
        slide.addObject(slideObject);
        slide.setImageList(imageList);
        slide.setTextList(textList);
        slide.setVideoList(videoList);
        slide.setSoundList(soundList);
        slide.setShapeList(shapeList);
        
        assertEquals("the SlideObject was not set correctly.",
                slide.getObjectList().get(0).getBranch(), branch);
        assertEquals("the ImageList was not set correctly.",
                slide.getImageList().get(0).getDuration(), duration);
        assertEquals("the TextList was not set correctly.",
                slide.getTextList().get(0).getColour(), colour);
        assertEquals("the videoList was not set correctly.",
                slide.getVideoList().get(0).getWidth(), width);
        assertEquals("the Sound List was not set correctly.",
                slide.getSoundList().get(0).getLoop(), loop);
        assertEquals("the Shape List was not set correctly.",
                slide.getShapeList().get(0).getFillColor(), fillColor);
        assertEquals("the Duration was not set correctly.",
                slide.getDuration(), duration);
        assertEquals("the last slide boolean was not set correctly.",
                slide.getLastSlide(), isItLastSlide);  
    }

}
