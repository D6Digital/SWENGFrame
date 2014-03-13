package musicPlayerModule;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.ListModel;
import javax.swing.Painter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.test.basic.PlayerControlsPanel;

public class EmbeddedAudioPlayer {
    static String vlcLibraryPath = "M:\\JAVA\\resources\\video\\VLC\\vlc-2.0.1";
    
    //static JFrame mainFrame = new JFrame("mainFrame");
    //static JFrame playlistFrame = new JFrame("playlistFrame");
    //static JPanel playPanel = new JPanel();
    //static Container contentPane;
    EmbeddedMediaPlayer mediaPlayer;
    private static final long serialVersionUID = 1L;
    JPanel returnPanel = new JPanel();
    static String incomingChangeMessage = "";
    static String mediaPath;
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
        mediaPlayer.setPosition(startTimeSeconds);
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
    
    
}

