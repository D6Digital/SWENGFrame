package musicPlayerModule;

import static org.junit.Assert.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * T002- A test to check that the embedded music player works correctly.
 * @author Joshua Lant
 */
public class EmbeddedMusTestT002 {
        private static final long SLEEPMS = 2000;
        static JFrame mainFrame = new JFrame();
        static JPanel mainPanel = new JPanel();
        EmbeddedAudioPlayer  musicPlayer;
        static String mediaPath = "C:\\Users\\Public\\Music\\Sample Music\\Kalimba.mp3";
        static String serverPath = "http://uybc.org.uk/d6digital/johntherevelator.mp3";
        static String shortTrackPath = "M:\\Year 2\\Ex11-ILovePhyicalModellingOriginal.wav";
        //static String shortTrackPath = "M:\\Year 2\\Engineering for Hearing and Voice\\Lab 1- Week 3\\Audio Samples\\7.b pit.wav";
        static String vlcLibraryPath = "resources\\lib\\vlc-2.1.3";  
        int timeout;
        
        @Before
        public void setUp() throws Exception {
            JFrame frame = new JFrame(); 
            musicPlayer = new EmbeddedAudioPlayer(vlcLibraryPath);
            frame.add(musicPlayer.getPanel()); 
            frame.setTitle("Audio Example");
            frame.setSize(640, 480);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        }

    @Test
    public void test() throws InterruptedException {
        
        // Step 1. User can play media items immediately by supplying filepath and filename.
        musicPlayer.playMedia(mediaPath);
        Thread.sleep(SLEEPMS*2);
        assertEquals("Audio is not currently playing from file, although it should be...",
                true, musicPlayer.mediaPlayer.isPlaying());
        musicPlayer.stopMedia();
        Thread.sleep(SLEEPMS);
        
        // Step 2. User is able to play media items from a web server.
        musicPlayer.playMedia(serverPath);
        Thread.sleep(SLEEPMS*2);
        assertEquals("Audio is not currently playing from server, although it should be...",
                true, musicPlayer.mediaPlayer.isPlaying());
        musicPlayer.stopMedia();
        Thread.sleep(SLEEPMS);
        
        // Step 3. User is able to pause media items and play again from same position.
        musicPlayer.playMedia(mediaPath);
        Thread.sleep(SLEEPMS);
        String initialPosition = musicPlayer.getCurrentPosition();
        musicPlayer.pauseMedia();
        Thread.sleep(SLEEPMS);
        String futurePosition = musicPlayer.getCurrentPosition();
        assertEquals("Audio is not currently paused, although it should be...",
                initialPosition, futurePosition);
        musicPlayer.playMedia();
        initialPosition = musicPlayer.getCurrentPosition();
        assertEquals("When restarting paused audio, it did not begin from position it was paused in.",
                initialPosition, futurePosition);
        Thread.sleep(SLEEPMS);
        
        // Step 4. User is able to stop the media, then play again from the beginning.
        musicPlayer.stopMedia();
        Thread.sleep(SLEEPMS);
        musicPlayer.playMedia();
        //Thread.sleep(SLEEPMS);
        initialPosition = musicPlayer.getCurrentPosition();
        assertEquals("When restarting stopped audio, it did not begin from beginning of file.",
                initialPosition, "0:0");
        Thread.sleep(SLEEPMS);
        
        // Step 5. User is able to retrieve the time through playing the media, time remaining and total play time of the media.
        Thread.sleep(SLEEPMS);
        initialPosition = musicPlayer.getCurrentPosition();
        String concatPosition = musicPlayer.getCurrentPositionMinutes() + ":" + musicPlayer.getCurrentPositionSeconds();
        assertEquals("cannot retrieve current position through track in minutes, seconds and/or total",
                initialPosition, concatPosition);
        assertNotNull("Could not retrieve the total track length",
                musicPlayer.getTrackLength());
        Thread.sleep(SLEEPMS);
        assertEquals("The time remaining was not correct",
                musicPlayer.getTotalLengthInSeconds() - musicPlayer.getCurrentPositionMinutes()*60 - musicPlayer.getCurrentPositionSeconds(),
                musicPlayer.getSecondsRemaining());
        
        // Step 6. User is able to set the volume for media between 0 & 100%. Volume changes are immediate.
        musicPlayer.setVolumePercentage(0);
        Thread.sleep(100);
        assertEquals("Volume was not able to be set to 0",
                0, musicPlayer.mediaPlayer.getVolume());
        Thread.sleep(SLEEPMS);
        musicPlayer.setVolumePercentage(100);
        Thread.sleep(100);
        assertEquals("Volume was not able to be set to 0",
                100, musicPlayer.mediaPlayer.getVolume());
        Thread.sleep(SLEEPMS);
        musicPlayer.setVolumePercentage(-1);
        Thread.sleep(100);
        assertFalse("Volume could be set below zero", 
                -1 == musicPlayer.mediaPlayer.getVolume());
        Thread.sleep(SLEEPMS);
        musicPlayer.setVolumePercentage(101);
        Thread.sleep(100);
        assertFalse("Volume could be set above 101, it was set to:" + musicPlayer.mediaPlayer.getVolume(), 
                101 == musicPlayer.mediaPlayer.getVolume());
        
        
        // Step 7. User is able to select start and stop times for the media, ie begin 20 seconds in, end 5 before end.
        // Test whether individual methods can be used for setting start and end times.
        musicPlayer.stopMedia();
        Thread.sleep(SLEEPMS);
        musicPlayer.setStartTime(10);
        musicPlayer.setEndTime(15);
        Thread.sleep(SLEEPMS);
        musicPlayer.playMedia();
        
        timeout = 0;
        do {
            Thread.sleep(20);
            timeout = timeout + 20;
        }
        while(!musicPlayer.mediaPlayer.isPlaying() && timeout <= 4000);
        assertEquals("The media did not start from the correct position",
                musicPlayer.getCurrentPosition(), "0:10");
        
        timeout = 0;
        do {
            Thread.sleep(100);
            timeout = timeout + 100;
        }
        while((musicPlayer.getCurrentPosition() != "0:15") && timeout <= 10000);
        Thread.sleep(1000);
        assertEquals("media still playing after it should have been stopped",
                false, musicPlayer.mediaPlayer.isPlaying());
        
        
        // Test whether the prepare media method can be used for setting start and end times.
        musicPlayer.stopMedia();
        Thread.sleep(SLEEPMS);
        musicPlayer.prepareMedia(mediaPath, 10, 15);
        musicPlayer.playMedia();
        
        int timeout = 0;
        do {
            Thread.sleep(20);
            timeout = timeout + 20;
        }
        while(!musicPlayer.mediaPlayer.isPlaying() && timeout <= 2000);
        assertEquals("The media did not start from the correct position",
                musicPlayer.getCurrentPosition(), "0:10");
        
        timeout = 0;
        do {
            Thread.sleep(100);
            timeout = timeout + 100;
        }
        while((musicPlayer.getCurrentPosition() != "0:15") && timeout <= 10000);
        Thread.sleep(1000);
        assertEquals("media still playing after it should have been stopped",
                false, musicPlayer.mediaPlayer.isPlaying());
        
        // Step 8. The user is able to select whether or not the media will loop and play again once it reaches the end. 
        
        // Check whether the user can set looping true and then start any media item and it will loop.
        musicPlayer.stopMedia();
        Thread.sleep(SLEEPMS);
        musicPlayer.setLooping(true);
        musicPlayer.playMedia(shortTrackPath);
        
        timeout = 0;
        do {
            Thread.sleep(20);
            timeout = timeout + 20;
        }
        while(!musicPlayer.mediaPlayer.isPlaying() && timeout <= 2000);
        
        timeout = 0;
        do {
            Thread.sleep(20);
            timeout = timeout + 20;
        }
        while(timeout <= musicPlayer.getTotalLengthInSeconds()*1000);
        
        Thread.sleep(SLEEPMS);
        
        assertEquals("The media is not looping, as media is no longer playing",
                true, musicPlayer.mediaPlayer.isPlaying());
        
        System.out.println("HERE1");
        
        // Check whether the preparemedia method can be used to set looping to be true and the audio will loop.
        musicPlayer.stopMedia();
        Thread.sleep(SLEEPMS);
        musicPlayer.prepareMedia(shortTrackPath, 0, 0, true);
        musicPlayer.playMedia();
  
        timeout = 0;
        do {
            Thread.sleep(20);
            timeout = timeout + 20;
        }
        while(!musicPlayer.mediaPlayer.isPlaying() && timeout <= 2000);
        
        timeout = 0;
        do {
            Thread.sleep(20);
            timeout = timeout + 20;
        }
        while(timeout <= musicPlayer.getTotalLengthInSeconds()*1000);

        Thread.sleep(SLEEPMS);
        
        assertEquals("The media is not looping, as media is no longer playing",
                true, musicPlayer.mediaPlayer.isPlaying());
        
        System.out.println("TEST COMPLETE!");
    }


    @After
    public void tearDown() throws Exception {
    }

}
