package videoModule;

import static org.junit.Assert.*;

import java.awt.BorderLayout;
import java.awt.Dimension;

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
    String file = "resources/video/video/monstersinc_high.mpg";
    
    @Before
    public void setUp() throws Exception {
        
        video = new Video(50, 60, 0, 10, 0, file, 300, 301, -10000000);
        videoPlayer = new VideoPlayer(video);
        frame = new JFrame();
        frame.setSize(600, 600);
        frame.setPreferredSize(new Dimension(600,600));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws InterruptedException {
       Thread.sleep(SLEEPMS);
       
       //panel.setLayout(null);
       //videoPlayer.setLocation(video.getX_coord(), video.getY_coord());
       frame.setBounds(0, 0, 600, 600);
       frame.add(videoPlayer);
       System.out.println(videoPlayer.getLocation());
       frame.pack();
       frame.setVisible(true);
       Thread.sleep(SLEEPMS*10);
       
    }

}
