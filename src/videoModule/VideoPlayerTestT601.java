package videoModule;

import static org.junit.Assert.*;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.InputEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import presentation.Video;

/**
 * A test to check that the video player module works correctly. NOTE:
 * No longer working automation due to addition of mouseAdapter to
 * constructor of videoplayer. Test needs rewriting for iteration 3.
 * 
 * @author Joshua Lant
 */
public class VideoPlayerTestT601 {
    Robot robot;
    Integer SLEEPMS = 2000;
    VideoPlayer videoPlayer, videoPlayer2;
    Video video, video2;
    JFrame frame;
    JPanel panel;
    String file = "resources/video/video/monstersinc_high.mpg";
    String notAFile = "this/path/is/bullshit.mpg";

    Integer x_coord = 300;
    Integer y_coord = 0;
    Integer start = 10;
    Integer duration = 60;
    Integer layer = 1;  
    int width = 300;
    int height = 302;

    @Before
    public void setUp() throws Exception {
        robot = new Robot();
        video = new Video(0, 0, 30, 45, 0, file, 300, 300);
        video2 = new Video(x_coord, y_coord, start, duration, layer, notAFile, width, height);
        videoPlayer = new VideoPlayer(video, null);
        videoPlayer2 = new VideoPlayer(video2, null);
        frame = new JFrame();
        frame.setSize(600, 600);
        frame.setPreferredSize(new Dimension(1000,600));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws InterruptedException {
        Thread.sleep(SLEEPMS);

        // Step 1. Confirm you can instantiate multiple video players within a single slide.
        frame.setLayout(null);
        videoPlayer.setLocation(video.getX_coord(), video.getY_coord());
        videoPlayer2.setLocation(video2.getX_coord(), video2.getY_coord());
        frame.add(videoPlayer);
        frame.add(videoPlayer2);
        frame.pack();
        frame.setVisible(true);
        Thread.sleep(SLEEPMS);

        /*COMMENT OUT TO RUN TEST...
        Boolean x = true;
        do {}
        while(x);*/

        // Step 2. Ensure that when you supply a correct video path it will load the video correctly.
        robot.mouseMove(video.getWidth()/2, video.getHeight()-10);
        robot.mousePress(InputEvent.BUTTON1_MASK );
        robot.mouseRelease(InputEvent.BUTTON1_MASK );

        Thread.sleep(SLEEPMS);

        Boolean playingYet;
        Integer slept = 0; 
        do {
            playingYet = videoPlayer.mediaPlayer.isPlaying();
            Thread.sleep(20);
            slept = slept + 20;
        }
        while(!playingYet && (slept <= 5000));

        assertTrue("The video is not yet playing.",
                playingYet);

        Thread.sleep(SLEEPMS);

        // Step 3. Ensure that when you supply an incorrect video path it will load nothing.
        robot.mouseMove(video2.getX_coord() + video2.getWidth()/2, video2.getHeight()-10);
        robot.mousePress(InputEvent.BUTTON1_MASK );
        robot.mouseRelease(InputEvent.BUTTON1_MASK );

        playingYet = false;
        slept = 0; 
        do {
            playingYet = videoPlayer2.mediaPlayer.isPlaying();
            Thread.sleep(20);
            slept = slept + 20;
        }
        while(!playingYet && (slept <= 5000));

        assertFalse("The video says it is playing even when not.",
                playingYet);

        // Step 4. Confirm that the rest of the parameters of "Video" object are correct.
        assertEquals("XCoord was incorrect in the video",
                video2.getX_coord(), x_coord);       
        assertEquals("YCoord was incorrect in the video",
                video2.getY_coord(), y_coord); 
        assertEquals("Start was incorrect in the video",
                video2.getStart(), start); 
        assertEquals("duration was incorrect in the video",
                video2.getDuration(), duration);       
        assertEquals("layer was incorrect in the video",
                video2.getLayer(), layer);       
        assertEquals("file was incorrect in the video",
                video2.getFile(), notAFile); 
        assertEquals("width was incorrect in the video",
                video2.getWidth(), width); 
        assertEquals("height was incorrect in the video",
                video2.getHeight(), height); 
        assertEquals("XCoord was incorrect in the video",
                video2.getX_coord(), (Integer) videoPlayer2.getX());       
        assertEquals("YCoord was incorrect in the video",
                video2.getY_coord(), (Integer) videoPlayer2.getY()); 
        assertEquals("width was incorrect in the video",
                video2.getWidth(), videoPlayer2.getWidth()); 
        assertEquals("height was incorrect in the video",
                video2.getHeight(), videoPlayer2.getHeight()); 


        // Confirm that the fast forward button works correctly.
        long initTime = videoPlayer.mediaPlayer.getTime();
        robot.mouseMove((video.getWidth()*3)/4, video.getHeight()-10);
        robot.mousePress(InputEvent.BUTTON1_MASK );
        robot.mouseRelease(InputEvent.BUTTON1_MASK );
        Thread.sleep(SLEEPMS);
        long newTime = videoPlayer.mediaPlayer.getTime();


        if(newTime < initTime + 4000) {
            fail("The fast forward button did not work correctly.");
        }
        Thread.sleep(SLEEPMS);

        // Confirm that the rewind button works correctly.
        initTime = videoPlayer.mediaPlayer.getTime();
        robot.mouseMove((video.getWidth()*1)/5, video.getHeight()-10);
        robot.mousePress(InputEvent.BUTTON1_MASK );
        robot.mouseRelease(InputEvent.BUTTON1_MASK );
        Thread.sleep(SLEEPMS);
        newTime = videoPlayer.mediaPlayer.getTime();

        if(newTime > initTime - 4000) {
            fail("The rewind button did not work correctly.");
        }
        Thread.sleep(SLEEPMS);

        // Confirm that the pause button works correctly.
        robot.mouseMove((video.getWidth())/2, video.getHeight()-10);
        robot.mousePress(InputEvent.BUTTON1_MASK );
        robot.mouseRelease(InputEvent.BUTTON1_MASK );
        Thread.sleep(SLEEPMS);
        initTime = videoPlayer.mediaPlayer.getTime();
        Thread.sleep(SLEEPMS);
        newTime = videoPlayer.mediaPlayer.getTime();

        if(newTime != initTime) {
            fail("The pause button did not work correctly.");
        }
        Thread.sleep(SLEEPMS);

        // Confirm that the stop button works correctly.
        robot.mouseMove((video.getWidth())/2, video.getHeight()-10);
        robot.mousePress(InputEvent.BUTTON1_MASK );
        robot.mouseRelease(InputEvent.BUTTON1_MASK );
        Thread.sleep(SLEEPMS);

        robot.mouseMove((video.getWidth())/3, video.getHeight()-10);
        robot.mousePress(InputEvent.BUTTON1_MASK );
        robot.mouseRelease(InputEvent.BUTTON1_MASK );
        Thread.sleep(SLEEPMS);

        playingYet = true;
        slept = 0; 
        do {
            playingYet = videoPlayer2.mediaPlayer.isPlaying();
            Thread.sleep(20);
            slept = slept + 20;
        }
        while(playingYet && (slept <= 5000));

        assertFalse("The video was not properly stopped.",
                playingYet);
    }

}
