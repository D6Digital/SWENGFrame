package musicPlayerModule;

import static org.junit.Assert.*;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



/**
 * T001- A test to check that the music player interface is returned correctly.
 * @author Joshua Lant
 *
 */
public class MusButtonTestT001 {
    private static final long SLEEPMS = 2000;
    static JButton 
    stopButton, playButton, pauseButton, nextButton, previousButton, openPlaylistButton = new JButton();
    static JSlider volumeSlider = new JSlider();
    static JFrame mainFrame = new JFrame();
    static JPanel mainPanel = new JPanel();
    StandAloneMusicPlayer  musicPlayer;
    static String currentFilePath = "M:\\Year 2\\Engineering for Hearing and Voice\\Lab 1- Week 3\\Audio Samples";
    static String vlcLibraryPath = "resources/lib/vlc-2.1.3";
    
    @Before
    public void setUp() throws Exception {
        musicPlayer = new StandAloneMusicPlayer(vlcLibraryPath, currentFilePath);
        stopButton = new JButton();
        pauseButton = new JButton();
        playButton = new JButton();
        nextButton = new JButton();
        previousButton = new JButton();
        openPlaylistButton = new JButton();
        volumeSlider = new JSlider();
        mainPanel.add(stopButton);
        mainPanel.add(pauseButton);
        mainPanel.add(playButton);
        mainPanel.add(nextButton);
        mainPanel.add(previousButton);
        mainPanel.add(openPlaylistButton);
        mainPanel.add(volumeSlider);
        mainFrame.setSize(200, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        mainFrame.validate(); 
    }

    @SuppressWarnings("deprecation")
    @Test
    public void step1to2() {

        // Step 1. A correctly labelled play button is retrieved from  class StandAloneMusicPlayer.        
        assertEquals("The play button was not retrieved correctly",
                true, playButton.isDisplayable());
        assertEquals("The play button was not retrieved correctly",
                true, playButton.isEnabled());
        assertEquals("The play button was not retrieved correctly, it was: " + playButton.getLabel(),
                "Play", playButton.getLabel());
        
        // Step 1. A correctly labelled stop button is retrieved from  class StandAloneMusicPlayer.
        assertEquals("The stop button was not retrieved correctly",
                true, stopButton.isDisplayable());
        assertEquals("The stop button was not retrieved correctly",
                true, stopButton.isEnabled());
        assertEquals("The stop button was not retrieved correctly, it was: " + stopButton.getLabel(),
                "Stop", stopButton.getLabel());
        
        // Step 1. A correctly labelled pause button is retrieved from  class StandAloneMusicPlayer.
        assertEquals("The pause button was not retrieved correctly",
                true, pauseButton.isDisplayable());
        assertEquals("The pause button was not retrieved correctly",
                true, pauseButton.isEnabled());
        assertEquals("The pause button was not retrieved correctly, it was: " + pauseButton.getLabel(),
                "Pause", pauseButton.getLabel());
        
        // Step 1. A correctly labelled next button is retrieved from  class StandAloneMusicPlayer.
        assertEquals("The next button was not retrieved correctly",
                true, nextButton.isDisplayable());
        assertEquals("The next button was not retrieved correctly",
                true, nextButton.isEnabled());
        assertEquals("The next button was not retrieved correctly, it was: " + nextButton.getLabel(),
                "Next", nextButton.getLabel());
        
        // Step 1. A correctly labelled previous button is retrieved from  class StandAloneMusicPlayer.
        assertEquals("The previous button was not retrieved correctly",
                true, previousButton.isDisplayable());
        assertEquals("The previous button was not retrieved correctly",
                true, previousButton.isEnabled());
        assertEquals("The previous button was not retrieved correctly, it was: " + previousButton.getLabel(),
                "Previous", previousButton.getLabel());
        
        // Step 1. A correctly labelled open playlist button is retrieved from  class StandAloneMusicPlayer.
        assertEquals("The openPlaylist button was not retrieved correctly",
                true, openPlaylistButton.isDisplayable());
        assertEquals("The openPlaylist button was not retrieved correctly",
                true, openPlaylistButton.isEnabled());
        assertEquals("The openPlaylist button was not retrieved correctly, it was: " + openPlaylistButton.getLabel(),
                "Open Playlist", openPlaylistButton.getLabel());
        
        // Step 2. A correctly labelled play button is retrieved from  class StandAloneMusicPlayer.
        assertEquals("The volumeSlider was not retrieved correctly",
                true, volumeSlider.isDisplayable());
        assertEquals("The volumeSlider was not retrieved correctly",
                true, volumeSlider.isEnabled());
        assertEquals("The max volume for volumeSlider was not correct, it was: " + volumeSlider.getMaximum(),
                100, volumeSlider.getMaximum());
        assertEquals("The min volume for volumeSlider was not correct, it was: " + volumeSlider.getMinimum(),
                0, volumeSlider.getMinimum());
    }
    
    @Test
    public void steps3toX() throws InterruptedException {
        
        playButton.doClick();
        Thread.sleep(SLEEPMS);
        assertEquals("The play button does not cause an item in the list to begin playing.", true, musicPlayer.mediaPlayer.isPlaying());
        
        // Step 4. When the user presses pause, the media will stop playing.
        Thread.sleep(SLEEPMS);
        assertTrue(musicPlayer.mediaPlayer.isPlaying());
        pauseButton.doClick();
        Thread.sleep(SLEEPMS);
        float x1 = musicPlayer.mediaPlayer.getPosition();
        Thread.sleep(SLEEPMS);
        float x2 = musicPlayer.mediaPlayer.getPosition();
        assertEquals("The pause button does not cause an item in the list to stop playing.", true, x1 == x2);
        
        // Step 5. When the user presses play again, the media will begin playing once again.
        pauseButton.doClick();
        Thread.sleep(SLEEPMS);
        x1 = musicPlayer.mediaPlayer.getPosition();
        assertEquals("The pause button does not cause an item in the list to stop playing.", true, x1 != x2);
        
        // Step 6. When the user presses stop, and then play again, the media will begin from the start of the same media item.
        stopButton.doClick();
        Thread.sleep(SLEEPMS);
        playButton.doClick();
        x1 = musicPlayer.mediaPlayer.getPosition();
        boolean areEqual = x1 == 0.0;
        assertEquals("The stop button does not set the position of the media back to zero, it was: " + x1, true, areEqual);
        
        // Step 7. Check when the audio begins the volume is at 100%
        assertEquals("The volume of the slider does not default to 100%",         
                volumeSlider.getValue(), 100);
        Thread.sleep(SLEEPMS);
        
        // Step 8. Pressing the open playlist button displays the playlist frame. When closed the audio remains playing.
        openPlaylistButton.doClick();
    }

    @After
    public void tearDown() throws Exception {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.dispose();
    }
}
