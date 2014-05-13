package presentation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * T409, a test to check that the Video objects getters, setters and
 * constructor are working correctly.
 * @author Joshua Lant
 */
public class SlideVideoTestT409 {
    int width, height;
	Integer x_coord, y_coord, start, duration, layer;
	String file;
	
	@Before
	public void setUp() throws Exception {
		x_coord = 321321;
		y_coord = 123456;
		start = 8543;
		duration = 4828;
		layer = 5918;
		width = 1939;
		height = 888;
		file = "fdsfdsfdsfsdwweerr3334r";
	}

	@Test
	public void testSteps1to2() {
		// Step 1. Instantiate video object supplying all values to constructor.
		Video video = new Video(x_coord, y_coord, start, duration, layer, file, width, height);
		
		// Step 2. confirm that the constructor and getters work properly.
		assertEquals("xcoord incorrect, problem with getter or constructor",
				x_coord, video.getX_coord());
		assertEquals("ycoord incorrect, problem with getter or constructor",
				y_coord, video.getY_coord());
		assertEquals("start incorrect, problem with getter or constructor",
				start, video.getStart());
		assertEquals("duration incorrect, problem with getter or constructor",
				duration, video.getDuration());
		assertEquals("layer incorrect, problem with getter or constructor",
				layer, video.getLayer());
		assertEquals("width incorrect, problem with getter or constructor",
				width, video.getWidth());
		assertEquals("height incorrect, problem with getter or constructor",
				height, video.getHeight());
		assertEquals("file incorrect, problem with getter or constructor",
				file, video.getFile());
	}
	
	@Test
	public void testSteps3to4() {
		// Step 3. Instantiate video with empty constructor.
		Video video = new Video();
		
		x_coord = 3121;
		y_coord = 1456;
		start = 43;
		duration = 48;
		layer = 18;
		width = 19;
		height = 8;
		file = "fdsfdsffsfdrrrrrrrrrrrrrrrr3334r";
		
		video.setX_coord(x_coord);
		video.setY_coord(y_coord);
		video.setStart(start);
		video.setDuration(duration);
		video.setLayer(layer);
		video.setWidth(width);
		video.setHeight(height);
		video.setFile(file);	
			
		// Step 4. confirm that the setters and getters work properly.
		assertEquals("xcoord incorrect, problem with getter or setter",
				x_coord, video.getX_coord());
		assertEquals("ycoord incorrect, problem with getter or setter",
				y_coord, video.getY_coord());
		assertEquals("start incorrect, problem with getter or setter",
				start, video.getStart());
		assertEquals("duration incorrect, problem with getter or setter",
				duration, video.getDuration());
		assertEquals("layer incorrect, problem with getter or setter",
				layer, video.getLayer());
		assertEquals("width incorrect, problem with getter or setter",
				width, video.getWidth());
		assertEquals("height incorrect, problem with getter or setter",
				height, video.getHeight());
		assertEquals("file incorrect, problem with getter or setter",
				file, video.getFile());
	}

}
