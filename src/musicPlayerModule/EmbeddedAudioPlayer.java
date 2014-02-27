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
    static String vlcLibraryPath = "M:\\Year 2\\VLC\\vlc-2.0.1";
    
    static JFrame mainFrame = new JFrame("mainFrame");
    static JFrame playlistFrame = new JFrame("playlistFrame");
    static JPanel playPanel = new JPanel();
    static Container contentPane;
    static EmbeddedMediaPlayer mediaPlayer;
    private static final long serialVersionUID = 1L;
    
    static String incomingChangeMessage = "";
    static String mediaPath;
    
    
    public EmbeddedAudioPlayer() {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),vlcLibraryPath);
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        
        setupGUI();
        mainFrame.repaint();
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
       mediaPlayer.stop();
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
    
    public void pauseMedia() {
        incomingChangeMessage = "pause";
    }
    
    private void setupGUI() {
        JPanel playlistChooserPanel = new JPanel();

        contentPane = playlistFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        mediaPlayer = openMediaPlayer();
        playlistFrame.add(playlistChooserPanel, BorderLayout.NORTH);
        playlistFrame.add(playPanel, BorderLayout.CENTER);
        playlistFrame.setSize(200, 300);
        playlistFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playlistFrame.pack();
        playlistFrame.setVisible(true);
        playlistFrame.validate(); 

    }
    
    private static EmbeddedMediaPlayer openMediaPlayer() {
        final EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        final EmbeddedMediaPlayer mediaPlayer = mediaPlayerComponent.getMediaPlayer();

        mainFrame.setContentPane(mediaPlayerComponent);
        contentPane.add(mediaPlayerComponent, BorderLayout.EAST);
        
        return mediaPlayer;
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
    }
    
    
}

