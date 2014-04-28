package videoModule;

import static org.junit.Assert.*;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import presentation.Video;

public class VideoPlayerTestT601 {

    int SLEEPMS = 2000;
    VideoPlayer videoPlayer;
    Video video;
    JFrame frame;
    JPanel panel;
    String file = "monstersinc_high.mpg";
    
    @Before
    public void setUp() throws Exception {
        
        video = new Video(50, 90, 10, 10, 0, file, 300, 301, -10000000);
        videoPlayer = new VideoPlayer(video);
        frame = new JFrame();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws InterruptedException {
       Thread.sleep(SLEEPMS);
       panel = videoPlayer.getPanel();
       frame.add(panel, BorderLayout.CENTER);
       frame.setBounds(0, 0, 600, 480);
       frame.setVisible(true);
       Thread.sleep(SLEEPMS*10);
       
    }

}
