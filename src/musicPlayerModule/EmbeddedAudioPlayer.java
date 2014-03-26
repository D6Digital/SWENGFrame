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

public class EmbeddedAudioPlayer {
    String vlcLibraryPath = "..\\..\\resources\\lib\\vlc-2.0.1";
    
    //static JFrame mainFrame = new JFrame("mainFrame");
    //static JFrame playlistFrame = new JFrame("playlistFrame");
    //static JPanel playPanel = new JPanel();
    //static Container contentPane;
    EmbeddedMediaPlayer mediaPlayer;
    JPanel returnPanel = new JPanel();
    String incomingChangeMessage = "";
    String mediaPath;
    int endTimeSeconds;
    
    
    
    public EmbeddedAudioPlayer() {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),vlcLibraryPath);
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        
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
    
    Thread musicThread = new Thread("Socket") {
        public void run() {
                while (true) {
                    musicPlayerLoop();                      
            }  
        }
    };
    
    private void internalStopMedia() {
        mediaPlayer.stop();
    }
    
    /**
     * Does what it says on the tin.
     */
    private void internalPlayMedia() {
    	if(mediaPlayer.isPlaying()==true){
    		mediaPlayer.pause();
    	}
    	//mediaPlayer.startMedia(mediaPath);
       mediaPlayer.playMedia(mediaPath);
    }
    
    private void internalPauseMedia() {
        mediaPlayer.pause();
    }
    
    public void stopMedia() {
        incomingChangeMessage = "stop";
    }
    
    public void playMedia(String mediaPathAndFileName) {
        mediaPath = mediaPathAndFileName;
        incomingChangeMessage = "play";
    }
    
    public void play() {
       mediaPlayer.play();
    }
    
    /**
     * Pauses the media, time remains at point where it was paused.
     */
    public void pauseMedia() {
        incomingChangeMessage = "pause";
    }
    
    
    public JPanel getPanel() {
        return returnPanel;
    }
    
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
    
    public void setStartTime(int startTimeSeconds) {
        mediaPlayer.setTime(startTimeSeconds*1000);
    }
    
    public void setEndTime(int endTimeSeconds) {
        this.endTimeSeconds = endTimeSeconds;
    }
    
    public void setLooping(Boolean loopTrueFalse) {
        mediaPlayer.setRepeat(loopTrueFalse);
    }

    public Boolean getLooping() {
        return mediaPlayer.getRepeat();
    }
    
    public void setVolumePercentage(int percentage) {
        mediaPlayer.pause();
        mediaPlayer.setVolume(percentage);
        mediaPlayer.play();
    }
    
    private void musicPlayerLoop() {
        switch(incomingChangeMessage) {
            case "stop":        
                internalStopMedia();
                incomingChangeMessage = "";
                break;
            case "pause":
                internalPauseMedia();
                incomingChangeMessage = "";
                break;
            case "play":
                internalPlayMedia();
                incomingChangeMessage = "";
                break;
        default: break;
        }
        
        if(endTimeSeconds != 0) {
            if (getCurrentPositionSeconds() == endTimeSeconds) {
                mediaPlayer.stop();
                endTimeSeconds = 0;
            }
        }
        
    }

    /**
     * Constructor for media player.
     * @param vlcLibraryPath- The path to the vlc library on system. 
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

    /**
     * Play whatever media has been prepared. (plays from beginning of file)
     */
    public void playMedia() {
       mediaPlayer.play();
    }

    /**     
     * Prepares media for playback, simply call playMedia(void) method after calling this method to 
     * playback files with altered start and end times.
     * @param filePathURL - path or URL and filename to media
     * @param startTimeSeconds
     */
    public void prepareMedia(String filePathURL, int startTimeSeconds) {
        mediaPlayer.prepareMedia(filePathURL);
        mediaPlayer.play();
        int x = 1;
        do {
            x++;
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while(!mediaPlayer.isPlaying() && (x < 1000));
        mediaPlayer.pause();
        setEndTime((int) getTotalLengthInSeconds());
        setStartTime(startTimeSeconds);
    }

    /**
     *  Prepares media for playback, simply call playMedia(void) method after calling this method to 
     * playback files with altered start and end times.
     * @param filePathURL - path or URL and filename to media
     * @param startTimeSeconds
     * @param endTimeSeconds
     */
    public void prepareMedia(String filePathURL, int startTimeSeconds, int endTimeSeconds) {
        mediaPlayer.prepareMedia(filePathURL);
        mediaPlayer.play();
        int x = 1;
        do {
            x++;
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while(!mediaPlayer.isPlaying() && (x < 1000));
        mediaPlayer.pause();
        setEndTime(endTimeSeconds);
        setStartTime(startTimeSeconds);
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
        mediaPlayer.prepareMedia(filePathURL);
        mediaPlayer.play();
        int x = 1;
        do {
            x++;
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while(!mediaPlayer.isPlaying() && (x < 1000));
        mediaPlayer.pause();
        System.out.println("here");
        
        setEndTime(endTimeSeconds);
        setStartTime(startTimeSeconds);
        setLooping(looping);
    }
    
    
}

