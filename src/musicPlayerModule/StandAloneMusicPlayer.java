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
import javax.swing.ImageIcon;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
import uk.co.caprica.vlcj.player.list.MediaListPlayer;
import uk.co.caprica.vlcj.player.list.MediaListPlayerEventAdapter;
import uk.co.caprica.vlcj.player.list.MediaListPlayerMode;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.test.basic.PlayerControlsPanel;
import uk.co.caprica.vlcj.test.list.TestMediaListEmbeddedPlayer;

import uk.co.caprica.vlcj.player.list.MediaList;
//import uk.co.caprica.vlcj.medialist.MediaList;

/**
 * @author Josh Lant
 * This class sets up a stand-alone music player with playlist searching feature, provided by FileChooser() class.
 * Methods provided return buttons and sliders to interface with music player. Player works in its own thread
 * enabling the user to perform other tasks simultaneously with music play.
 */
public class StandAloneMusicPlayer {
    static String vlcLibraryPath;
	static DefaultListModel listModel = new DefaultListModel<String>();
    static JFrame mainFrame = new JFrame("mainFrame");
    static JFrame playlistFrame = new JFrame("playlistFrame");
    static JPanel playPanel = new JPanel();
    static JList playContents = new JList(listModel);
    static Container contentPane;
    static EmbeddedMediaPlayer mediaPlayer;
    static MediaList mediaList;
    static MediaListPlayer mediaListPlayer;
    private static final long serialVersionUID = 1L;
    static String currentPlayIndex;
    static String currentFilePath;
    static String newFilePath;
    static FileChooser fileChooser = new FileChooser(newFilePath);
    static Boolean isPaused = false;

    /**
     * Constructor for StandAloneMusicPlayer() class.
     * @param vlcLibraryPathConstructor, full file path to vlc player version to be used. note need for 
     * "\\" directory separation instead of single "\" to deal with Java escape character issues.
     * @param currentFilePathConstructor, full file path to initial playlist folder. note need for 
     * "\\" directory separation instead of single "\" to deal with Java escape character issues.
     */
    public StandAloneMusicPlayer(String vlcLibraryPathConstructor, String currentFilePathConstructor) {
        vlcLibraryPath = vlcLibraryPathConstructor;
        currentFilePath = currentFilePathConstructor;
        newFilePath = currentFilePath;
        
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),vlcLibraryPath);
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        ArrayList<String> files = getFilenames(currentFilePath);
        setupGUI(files);
        mainFrame.repaint();
        
        musicThread.start();
    }
    
    /**
     * Initialise music player thread and go into infinite loop of instructions while thread intact.
     */
    Thread musicThread = new Thread() {
        public void run() {
            while (true) {
                musicPlayerLoop();                      
            }
        }
    };
   
    /**
     * Perform loop of instructions from Thread.
     */
    private void musicPlayerLoop() {
        String newPlayIndex = (String) playContents.getSelectedValue();
        chooseNewPlaylist();
    }
    
    
    /**
     * Retrieves the new filepath for the new playlist, then fills an arraylist of strings with the names
     * of each of the files, and adds them to the media list after the old list has been removed.
     */
    private void chooseNewPlaylist() {
    	newFilePath = fileChooser.getNewFilePath();
    	String[] options = {};
    	
    	// Only change the playlist if the new path is different from the old one.
    	if((currentFilePath != newFilePath)  && (newFilePath != null)) {
    		ArrayList<String> files = getFilenames(newFilePath);
    		createList(files);
    		currentFilePath = newFilePath;
    		System.out.println("size is " + mediaList.size());
    		
    		// Remove all media items from the list.
    		for(int i = mediaList.size(); i >= 0; i--) {
    		    mediaList.removeMedia(i);
    		}
    		
    		// Add the new items to the playlist.
    		for(String filename : files) {
    		    mediaList.addMedia(newFilePath + "\\" + filename, options);	
    		}
    		mediaListPlayer.setMediaList(mediaList);
    	}
	}

    
	/**
	 * Plays the selected file from the JList. Called whenever the user chooses an item from the list.
	 */
	private static void chooseFromPlaylist() {
	    mediaListPlayer.playItem(playContents.getSelectedIndex());
	}
    
	/**
	 * Stop the media playback.
	 */
    private void stopMedia() {
    	mediaListPlayer.stop();
    }
    
    /**
     * Sets up the action listeners for all the buttons which control the playlist. 
     * @param buttonName, JButton giving the button to add the listener to.
     * @param playerMethodCaseName, name for the case statement to call the method action for each listener,
     * should be LOWERCASE entirely...
     * @return Return the button which was passed in.
     */
    private JButton setupListenerAndAction(JButton buttonName, final String playerMethodCaseName) {
        buttonName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                switch (playerMethodCaseName) {
                case "stop":            stopMedia();        break;
                case "pause":           pauseMedia();       break;
                case "play":            playMedia();        break;
                case "next":            nextMedia();        break;
                case "previous":        previousMedia();    break;
                case "openplaylist":    openPlaylist();     break;
                default: break;
                }
            }
        });       
        return buttonName; 
    }

    /**
     * Sets up the action listener for the JSlider which controls volume.
     * @param sliderName, JSlider object which is to have the listener applied.
     * @param playerMethodCaseName, name for the case statement to call the method action for each listener,
     * should be LOWERCASE entirely.
     */
    private void setupListenerAndAction(final JSlider sliderName, final String playerMethodCaseName) {
        sliderName.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent arg0) {
                adjustVolume(sliderName.getValue());
            }  
        });
    }

    /**
     * Gets the JButton to open the playlist window when it is closed.
     * @return JButton, opens the playlist.
     */
    public JButton getOpenPlaylistButton() {
        JButton button = new JButton("Open Playlist");
        setupListenerAndAction(button, "openplaylist");      
        return button;     
    }
    
    /**
     * Gets the JButton to stop media being played back and return play position to beginning of track.
     * @return JButton
     */
    public JButton getStopButton() {
        JButton button = new JButton("Stop");
        setupListenerAndAction(button, "stop");      
        return button;     
    }
    
    /**
     * Gets the JButton to pause media playback, retaining play position.
     * @return JButton
     */
    public JButton getPauseButton() {
        JButton button = new JButton("Pause");
        setupListenerAndAction(button, "pause");      
        return button;     
    }
    
    /**
     * Gets the JButton to play media, plays the current selected track, if it is paused or stopped.
     * @return JButton
     */
    public JButton getPlayButton() {
        JButton button = new JButton("Play");
        setupListenerAndAction(button, "play");      
        return button;     
    }
    
    /**
     * Gets the JButton that plays the next media in the playlist, loops round if at end of playlist.
     * @return JButton
     */
    public JButton getNextButton() {
        JButton button = new JButton("Next");
        setupListenerAndAction(button, "next");      
        return button;     
    }
    
    /**
     * Gets the JButton that plays the previous media in the playlist, loops back round if at beginning of playlist.
     * @return JButton
     */
    public JButton getPreviousButton() {
        JButton button = new JButton("Previous");
        setupListenerAndAction(button, "previous");      
        return button;     
    }
    
    /**
     * Returns the JSlider which controls the volume of playback, valued from 0-100.
     * @return JSlider
     */
    public JSlider getVolumeSlider() {
        JSlider slider = new JSlider();
        slider.setMinimum(0);
        slider.setMaximum(100);
        setupListenerAndAction(slider, "previous");
        return slider;
    }
    
    /**
     * Pauses media if it is playing, plays media if it is paused.
     */
    private void pauseMedia() {
        if(isPaused == false) {
            mediaPlayer.pause();
            isPaused = true;
        }
        else if(isPaused == true) {
            mediaPlayer.play();
            isPaused = false;
        }
    }
    
    /**
     * Does what it says on the tin.
     */
    private void playMedia() {
	   mediaPlayer.play();
    }
    
    /**
     * Does what it says on the tin.
     */
    private void nextMedia() {
    	mediaListPlayer.playNext();
    }
    
    /**
     * Does what it says on the tin.
     */
    private void previousMedia() {
        mediaListPlayer.playPrevious();
    }

    /**
     * Takes the value from 0-100 for the percentage volume playback, then adjusts the volume of the 
     * MediaPlayer accordingly.
     * @param volumePercent
     */
    private void adjustVolume(int volumePercent) {
        mediaPlayer.pause();
    	mediaPlayer.setVolume(volumePercent);
    	mediaPlayer.play(); 	
    }
    
    /**TODO implement
     */
    private void shufflePlaylist(Boolean onOff) {
    	
    }
    
    /**TODO implement
     */
    private void loopPlaylist(Boolean onOff) {
    	
    }
    
    /**
     * Does what it says on the tin.
     */
    private void openPlaylist() {
    	playlistFrame.setVisible(true);
    }
    
    /**
     * Does what it says on the tin.
     */
    private void closePlaylist() {
        playlistFrame.setVisible(false);
    }
   
    /**TODO implement
     */
    private void lockPlaylistWithPresentation() {
    	
    }
    
    /**TODO implement
     */
    private void unlockPlaylistFromPresentation() {
    	
    }

    /**
     * sets up the GUI for the playlist, adding the browse button and playlist to a panel, fills
     * the list with the values it gets from the selected playlist folder, then it is added to a JFrame. 
     * @param files, The arraylist of strings containing the filenames of all files in the current playlist.
     */
	private void setupGUI(ArrayList<String> files) {
    	JPanel playlistChooserPanel = new JPanel();
    	
    	playlistChooserPanel.add(fileChooser.openDialog());
        createList(files);
        
    	contentPane = playlistFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        mediaPlayer = openMediaPlayer();
        
        playlistFrame.add(playlistChooserPanel, BorderLayout.NORTH);
        playlistFrame.add(playPanel, BorderLayout.CENTER);
        playlistFrame.setSize(200, 300);
        playlistFrame.pack();
        playlistFrame.setVisible(true);
        playlistFrame.validate(); 

    }

	/**
	 * Clears the existing list of filenames, then adds each element in the parameter nameList into
	 * the listModel, which is then placed on the JPanel.
	 * @param nameList, arraylist of strings containing the names of each of file to be added to the playlist.
	 */
    private static void createList(ArrayList<String> nameList) {
    	listModel.clear();
    	
        for(String listItem : nameList){
        	listModel.addElement(listItem);
        }
        
        playPanel.add(playContents);
        playlistFrame.pack();
        // Add listener which plays a piece of media whenever the user chooses it in the JList.
        playContents.addListSelectionListener(new ListSelectionListener() {    
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                chooseFromPlaylist();
            }
        });
    }

    /**
     * Gets the filenames of every file in the path provided, path must be "\\" double backspaced to
     * cope with escape characters.
     * @param newFilePath, full path to directory containing files.
     * @return ArrayList<String> all files names in directory stored as strings.
     */
    private static ArrayList<String> getFilenames(String newFilePath) {
       File folder = new File(newFilePath);
       File[] shit = folder.listFiles();
       ArrayList<String> returnList = new ArrayList();
       
       for(int i = 0; i < shit.length; i ++) {
           returnList.add(shit[i].getName());
       }
   
       return returnList;
    }

    /**
     * Sets up the media player with the initial playlist, adds it to Panels and frames, ready for play.
     * @return EmbeddedMediaPlayer.
     */
    private static EmbeddedMediaPlayer openMediaPlayer() {
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        final EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        Canvas canvas = new Canvas();
        canvas.setBackground(Color.black);
        CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(canvas);
        EmbeddedMediaPlayer mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
        mediaPlayer.setVideoSurface(videoSurface);
        mediaListPlayer = mediaPlayerFactory.newMediaListPlayer();
        mediaListPlayer.setMediaPlayer(mediaPlayer);
               
        JPanel cp = new JPanel();
        cp.setBackground(Color.black);
        cp.setLayout(new BorderLayout());
        cp.add(canvas, BorderLayout.CENTER);
               
        JFrame f = new JFrame("vlcj embedded media list player test");
        f.setIconImage(new ImageIcon(TestMediaListEmbeddedPlayer.class.getResource("/icons/vlcj-logo.png")).getImage());
        f.setContentPane(cp);
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        mediaList = mediaPlayerFactory.newMediaList();
        String[] options = {};
        
        ArrayList<String> initialFilenames = getFilenames(newFilePath);
        
        for(String filename: initialFilenames) {
            mediaList.addMedia(newFilePath + "\\" + filename, options);
        }
       
        mediaListPlayer.setMediaList(mediaList);
        mediaListPlayer.setMode(MediaListPlayerMode.LOOP);
        mainFrame.setContentPane(mediaPlayerComponent);
        contentPane.add(mediaPlayerComponent, BorderLayout.EAST);
        
        return mediaPlayer;
    }
   
}
