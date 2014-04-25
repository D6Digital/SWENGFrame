package musicPlayerModule;

import java.awt.Canvas;

import javax.swing.JPanel;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

/**
 * A class which can be used to play pieces of audio headlessly.
 * Providing methods to prepare and play audio, with various options.
 * JPanel must be retrieved using getPanel() and plaed on a frame
 * before the audio player features can be utilised...
 * NOTE: In order to prevent unnecessary CPU usage when the 
 * use of EmbeddedAudioPlayer is no longer required it is strongly
 * advised that the threadKill() method is called. This method
 * will release the player, destroy the thread and return true if
 * the thread has been correctly destroyed.
 * @author Joshua Lant
 *
 */
public class EmbeddedAudioPlayer {
    //String vlcLibraryPath = "..\\..\\resources\\lib\\vlc-2.0.1";
    String vlcLibraryPath = "resources\\lib\\vlc-2.1.3";
    //static JFrame mainFrame = new JFrame("mainFrame");
    //static JFrame playlistFrame = new JFrame("playlistFrame");
    //static JPanel playPanel = new JPanel();
    //static Container contentPane;
    EmbeddedMediaPlayer mediaPlayer;
    JPanel returnPanel = new JPanel();
    String incomingChangeMessage = "";
    //int endTimeSeconds;
    private Boolean loopingGlobal = false;
    private Boolean startedAllreadyGlobal = false;
    private Boolean isPausedGlobal = false;
    private String playingPathGlobal;
    private Integer startTimeGlobal, endTimeGlobal = null;
    private Integer pauseTime = 0;
    

    /**
     * Constructor for headless media player. Used to generate the media player with
     * vlc previously loaded outside the EmbeddedAudioPlayer class.
     */
    public EmbeddedAudioPlayer() {
        
        Canvas canvas = new Canvas();
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(canvas);
        mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
        mediaPlayer.setVideoSurface(videoSurface);
        //JPanel returnPanel = new JPanel();
        returnPanel.add(canvas); 
        returnPanel.setBounds(0, 0, 0, 0);
        //mainFrame.repaint();
        musicThread.start();
    }
    
    /**
     * Constructor for media player. Used to load vlc from supplied library path
     * and then generate media player.
     */
    public EmbeddedAudioPlayer(String vlcLibraryPath) {
        this.vlcLibraryPath = vlcLibraryPath;
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),vlcLibraryPath);
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        
        Canvas canvas = new Canvas();
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(canvas);
        mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
        mediaPlayer.setVideoSurface(videoSurface);
        //JPanel returnPanel = new JPanel();
        canvas.setBounds(0,0,0,0);
        returnPanel.add(canvas); 
        returnPanel.setBounds(0, 0, 0, 0);
        //mainFrame.repaint();
        musicThread.start();
    }
    
    Thread musicThread = new Thread("Socket") {
        public void run() {
                while (true) {
                    musicPlayerLoop();                      
            }  
        }
    };
   
    /**
     * destroys the thread and releases the media player. If new audio is required
     * after this a new EmbeddedAudioPlayer must be instantiated.
     * @return true if the thread has been destroyed, otherwise returns false.
     */
    public boolean threadKill() {
        musicThread.stop();
        musicThread.destroy();
        mediaPlayer.release();
        return(!musicThread.isAlive());
    }
    
    /**
     * Stops the media, and when play resumes it will be from the beginning
     * of the file, or at the point where start time was defined (if supplied)
     */
    public void stopMedia() {       
        startedAllreadyGlobal = false;
        startTimeGlobal = 0;
        isPausedGlobal = false;
        endTimeGlobal = 0;
        mediaPlayer.stop();
    }
    
    /**
     * Plays a new piece of media, from the start of the track.
     * @param mediaPathAndFileName - path to the file to play.
     */
    public void playMedia(String mediaPathAndFileName) {
        playingPathGlobal = mediaPathAndFileName;
        isPausedGlobal = false;
        playMedia(); 
    }
    
    /**
     * Play whatever media has been prepared. (old values are kept for start and end times.)
     * If media is paused currently then media will begin playing from point in track 
     * at which it was paused.
     */
    public void playMedia() {
       if(isPausedGlobal) {
           mediaPlayer.playMedia(playingPathGlobal, ":start-time=" + pauseTime, ":stop-time=" + endTimeGlobal);
       }
       else {
           mediaPlayer.playMedia(playingPathGlobal, ":start-time=" + startTimeGlobal, ":stop-time=" + endTimeGlobal);
       }
       isPausedGlobal = false;
       setStartedAllready();
    }
    
//    public void play() {
//       mediaPlayer.play();
//       setStartedAllready();
//       isPausedGlobal = false;
//    }
    
    /**
     * Pauses the media, time remains at point where it was paused.
     * This method cannot be used to initiate playback. once paused
     * the playMedia() method must be called, at which point the audio will
     * be unpaused.
     */
    public void pauseMedia() {
        if(!isPausedGlobal) {
            isPausedGlobal = true;
            mediaPlayer.pause();
            pauseTime = (int) this.getCurrentPositionSeconds();
        }
    }
    
    /**
     * Get the panel upon which the media player is seated. Panel
     * must be retrieved and placed upon a frame for media to playback.
     * otherwise vlcj exceptions will be thrown.
     * @return JPanel containing media player.
     */
    public JPanel getPanel() {
        return returnPanel;
    }
    
    /**
     * Get the length of the track in milliseconds.
     * @return long track length
     */
    public long getLengthMS() {
        return mediaPlayer.getLength()/1000;
    }
    
    
    public String getTrackLength() {
        long seconds = mediaPlayer.getLength()/1000 % 60;
        int minutes = (int) (mediaPlayer.getLength()/1000/60);
        return minutes + ":" + seconds;
    }
    
    public long getSeconds() {
        return mediaPlayer.getLength()/1000 % 60;
    }
    
    public int getMinutes() {
        return (int) (mediaPlayer.getLength()/1000/60);
    }
   
    public long getTotalLengthInSeconds() {
        return mediaPlayer.getLength()/1000;
    }
    
    public String getCurrentPosition() {
        long totalLength = getTotalLengthInSeconds();
        float currentPosition = mediaPlayer.getPosition();
        float position = currentPosition*totalLength;
        int minutes = (int) (position/60);
        int seconds = (int) (position % 60);
        return minutes + ":" + seconds;
    }
    
    public int getCurrentPositionMinutes() {
        long totalLength = getTotalLengthInSeconds();
        float currentPosition = mediaPlayer.getPosition();
        float position = currentPosition*totalLength;
        return (int) (position/60);
    }
    
    public long getCurrentPositionSeconds() {
        long totalLength = getTotalLengthInSeconds();
        float currentPosition = mediaPlayer.getPosition();
        float position = currentPosition*totalLength;
        return (long) (position % 60);
    }
    
    /**
     * Set the start time in seconds of the audio playback.
     * @param startTimeSeconds
     */
    public void setStartTime(int startTimeSeconds) {
        mediaPlayer.setTime(startTimeSeconds*1000);
        startTimeGlobal = startTimeSeconds;
    }
    
    /**
     * set the stop time in seconds of the audio playback.
     * @param endTimeSeconds
     */
    public void setEndTime(int endTimeSeconds) {
        //this.endTimeSeconds = endTimeSeconds;
        endTimeGlobal = endTimeSeconds;
    }
    
    public long getSecondsRemaining() {
        return this.getTotalLengthInSeconds() - this.getCurrentPositionMinutes()*60 - this.getCurrentPositionSeconds();
    }
    
    public String getTimeRemaining() {
        long seconds = getSecondsRemaining() % 60;
        int minutes = (int) (getSecondsRemaining()/60);
        return minutes + ":" + seconds;
    }
    
    /**
     * @param loopTrueFalse- true for looping, false for play once.
     */
    public void setLooping(Boolean loopTrueFalse) {
        loopingGlobal = loopTrueFalse;
        //mediaPlayer.setRepeat(loopTrueFalse);
    }

    public Boolean getLooping() {
        return loopingGlobal;
    }
    
    /**
     * Set the volume between 0 and 100. Values above or below
     * will not be accepted and the old value of volume will remain.
     * @param percentage
     */
    public void setVolumePercentage(int percentage) {
         //mediaPlayer.pause();
        if((percentage <= 100) && (percentage >= 0)) {
            mediaPlayer.setVolume(percentage);
        }
        else {
            System.err.println("Cannot supply a volume below 0 or above 100");
        }
        //mediaPlayer.play();
    }
    
    private void musicPlayerLoop() {

        if(!mediaPlayer.isPlaying() && loopingGlobal && startedAllreadyGlobal && !isPausedGlobal) {
            mediaPlayer.playMedia(playingPathGlobal, ":start-time=" + startTimeGlobal, ":stop-time=" + endTimeGlobal);
            setStartedAllready();
        }
        
    }

    /**     
     * Prepares media for playback, simply call playMedia(void) method after calling this method to 
     * playback files with altered start and end times.
     * @param filePathURL - path or URL and filename to media
     * @param startTimeSeconds
     */
    public void prepareMedia(String filePathURL, int startTimeSeconds) {
        mediaPlayer.prepareMedia(filePathURL, ":start-time=" + startTimeSeconds);
        playingPathGlobal = filePathURL;
        startTimeGlobal = startTimeSeconds;
        endTimeGlobal = 0;
        loopingGlobal = false;
        isPausedGlobal = false;
        startedAllreadyGlobal = false;
    } 

    /**
     *  Prepares media for playback, simply call playMedia(void) method after calling this method to 
     * playback files with altered start and end times.
     * @param filePathURL - path or URL and filename to media
     * @param startTimeSeconds
     * @param endTimeSeconds
     */
    public void prepareMedia(String filePathURL, int startTimeSeconds, int endTimeSeconds) {
        mediaPlayer.prepareMedia(filePathURL, ":start-time=" + startTimeSeconds, ":stop-time=" + endTimeSeconds);
        playingPathGlobal = filePathURL;
        startTimeGlobal = startTimeSeconds;
        endTimeGlobal = endTimeSeconds;
        loopingGlobal = false;
        isPausedGlobal = false;
        startedAllreadyGlobal = false;
    } 

    /**
     * Prepares media for playback, simply call playMedia(void) method after calling this method to 
     * playback files with altered start and end times.
     * @param filePathURL - path or URL and filename to media
     * @param startTimeSeconds 
     * @param endTimeSeconds
     * @param looping
     */
    public void prepareMedia(String filePathURL, int startTimeSeconds, int endTimeSeconds, Boolean looping) {
        mediaPlayer.prepareMedia(filePathURL, ":start-time=" + startTimeSeconds, ":stop-time=" + endTimeSeconds);
        loopingGlobal = looping;
        playingPathGlobal = filePathURL;
        isPausedGlobal = false;
        if(startTimeSeconds == 0){
            startTimeGlobal = null;
        }
        else {
            startTimeGlobal = startTimeSeconds;
        }
        if(endTimeSeconds == 0) {
            endTimeGlobal = null;
        }
        else {
            endTimeGlobal = endTimeSeconds;
        }
        startedAllreadyGlobal = false;
    }
    
    /**
     * Prepares media for playback, simply call playMedia(void) method after calling this method to 
     * playback files with altered start time and duration.
     * @param filePathURL - path or URL and filename to media
     * @param startTimeSeconds 
     * @param durationSeconds
     * @param looping
     */
    public void prepareMediaWithDuration(String filePathURL, int startTimeSeconds, int durationSeconds, Boolean looping) {
        mediaPlayer.prepareMedia(filePathURL, ":start-time=" + startTimeSeconds, ":stop-time=" + (startTimeSeconds + durationSeconds));
        loopingGlobal = looping;
        playingPathGlobal = filePathURL;
        isPausedGlobal = false;
        if(startTimeSeconds == 0){
            startTimeGlobal = null;
        }
        else {
            startTimeGlobal = startTimeSeconds;
        }
        if((startTimeSeconds + durationSeconds) == 0) {
            endTimeGlobal = null;
        }
        else {
            endTimeGlobal = (startTimeSeconds + durationSeconds);
        }
        startedAllreadyGlobal = false;
    }
    
    private void setStartedAllready() {
        int x = 0;
        Boolean mediaStarted = false;
        do {
            mediaStarted = mediaPlayer.isPlaying();
            if(mediaStarted) {
                startedAllreadyGlobal = true;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            x = x + 200;
        }
        while(x < 5000 && mediaStarted == false);
        if(!mediaStarted) {
            System.err.println("Media not started after 5 seconds,");
        }
    }


    
    
}

