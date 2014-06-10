package musicPlayerModule;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
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
import uk.co.caprica.vlcj.player.list.MediaListPlayerMode;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.medialist.MediaList;

/**
 * @author Josh Lant
 * This class sets up a stand-alone music player with playlist searching feature, provided by FileChooser() class.
 * Methods provided return buttons and sliders to interface with music player. Player works in its own thread
 * enabling the user to perform other tasks simultaneously with music play.
 */
/**
 * @author jl1132
 *
 */
/**
 * @author jl1132
 *
 */
/**
 * @author jl1132
 *
 */
public class StandAloneMusicPlayer { 
    String vlcLibraryPath;
    DefaultListModel<String> listModel = new DefaultListModel<String>();
    JFrame mainFrame = new JFrame("mainFrame");
    JFrame playlistFrame = new JFrame("playlistFrame");
    JPanel playPanel = new JPanel();
    JList<String> playContentsJList = new JList<String>(listModel);
    JLabel timeLabel = new JLabel();
    JSlider timeSlider = new JSlider();
    JPanel fullPanel = new JPanel();

    private String currentTime = "";
    private String newTime = "";

    Container contentPane;
    JScrollPane scrollPane = new JScrollPane();
    EmbeddedMediaPlayer mediaPlayer;
    MediaList mediaList;
    MediaListPlayer mediaListPlayer;
    int currentPlayIndex = 0;
    Boolean newIndex = false;
    String currentFilePath;
    String newFilePath;
    FileChooser fileChooser = new FileChooser(newFilePath);
    Boolean isPaused = false;
    int widthOfPanel;
    protected boolean threadKilled = false;
    JPanel scrollPanel;

    protected boolean changingTimeByHand;
    protected boolean changingSelectedPlaylistByHand;

    boolean initialLockedValue = true;

    String playImage = "resources/buttons/play.png";
    String pauseImage = "resources/buttons/pause.png";
    String stopImage = "resources/buttons/stop.png";
    String nextImage = "resources/buttons/fastforward.png";
    String previousImage = "resources/buttons/rewind.png";
    String lockImage = "resources/buttons/lockText.png";
    String unlockImage = "resources/buttons/unlockText.png";
    String choosePlaylistImage = "/resources/buttons/openList";
    int heightOfLockIcon;
    int widthOfPlayButton;
    int heightOfPlayButton;
    int playlistIconHeight;

    private boolean areWeUnlocked;

    private MouseMotionListener genericMouseMotionListener;

    private int fontSize = 12;

    /**
     * Constructor for StandAloneMusicPlayer() class.
     * @param vlcLibraryPathConstructor, full file path to vlc player version to be used. note need for 
     * "\\" directory separation instead of single "\" to deal with Java escape character issues.
     * @param currentFilePathConstructor, full file path to initial playlist folder. note need for 
     * "\\" directory separation instead of single "\" to deal with Java escape character issues.
     */
    public StandAloneMusicPlayer(String vlcLibraryPathConstructor, String currentFilePathConstructor) {
        LockedPlaylistValueAccess.lockedPlaylist = initialLockedValue;
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
     * Constructor for StandAloneMusicPlayer() class. To be used when the VLCJ library has already
     * been loaded earlier in the program, external to the module.
     * @param currentFilePathConstructor, full file path to initial playlist folder. note need for 
     * "\\" directory separation instead of single "\" to deal with Java escape character issues.
     */
    public StandAloneMusicPlayer(String currentFilePathConstructor) {
        LockedPlaylistValueAccess.lockedPlaylist = initialLockedValue;
        currentFilePath = currentFilePathConstructor;
        newFilePath = currentFilePath;
        ArrayList<String> files = getFilenames(currentFilePath);
        setupGUI(files);
        mainFrame.repaint();
        musicThread.start();
    }

    /**
     * Constructor for StandAloneMusicPlayer() class. To be used when the VLCJ library has already
     * been loaded earlier in the program and there is no default path to load into the player.
     */
    public StandAloneMusicPlayer(MouseAdapter genericListener) {
        LockedPlaylistValueAccess.lockedPlaylist = initialLockedValue;
        currentFilePath = "";
        newFilePath = currentFilePath;
        this.genericMouseMotionListener = genericListener;
        fileChooser.getButton().addMouseMotionListener(genericListener);

        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),vlcLibraryPath);
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

        ArrayList<String> files = new ArrayList<String>(0);
        setupGUI(files);
        mainFrame.repaint();
        musicThread.start();
    }

    /**
     * Initialise music player thread and go into infinite loop of instructions while thread intact.
     */
    Thread musicThread = new Thread() {
        public void run() {
            while (!threadKilled ) {
                musicPlayerLoop();    
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /**
     * Releases the video player and kills the thread that loops to check for changes.
     * NON recoverable, after method is called StandAloneMusicPlayer must be re-instantiated.
     */
    public void killThread() {
        this.threadKilled = true;
        mediaPlayer.release();
        mediaList.release();
        mediaListPlayer.release();
    }


    /**
     * Perform loop of instructions from Thread.
     */
    private void musicPlayerLoop() {
        if(!LockedPlaylistValueAccess.lockedPlaylist) {
            chooseNewPlaylist();
            updateTime();
            updateVisibleSelectedPlaylist();
        } 
    }


    /**
     * Changes the selected value of the playlist which is visibly selected.
     */
    @SuppressWarnings("static-access")
    private void updateVisibleSelectedPlaylist() {
        if(!LockedPlaylistValueAccess.lockedPlaylist) {
            String[] splitStringTotal = null;
            String[] splitStringCurrent = null;
            splitStringTotal = getTrackLength().split(":");
            splitStringCurrent = getCurrentPosition().split(":");

            if(splitStringTotal[0].equals(splitStringCurrent[0]) 
                    && ((Integer.parseInt(splitStringTotal[1]) -1) == (Integer.parseInt(splitStringCurrent[1])))) {
                try {
                    musicThread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                newIndex = true;
                mediaPlayer.stop();
                mediaListPlayer.playNext();
            }

            if(newIndex) {
                playContentsJList.setSelectedIndex(playContentsJList.getSelectedIndex() + 1);
                playContentsJList.repaint();
                newIndex = false;
            } 
        }
    }

    /**
     * Polled every few hundred milliseconds, updates the time slider so
     * it moves with the track.
     */
    private void updateTime() {
        newTime = this.getCurrentPosition() + "/" + this.getTrackLength();

        if(!(currentTime.equals(newTime))) {
            timeLabel.setText(newTime);
            timeSlider.setValue((int) (mediaPlayer.getPosition()*1000));
            timeSlider.repaint();
            fullPanel.repaint();
            currentTime = newTime;
        } 
    }


    /**
     * Retrieves the new filepath for the new playlist, then fills an arraylist of strings with the names
     * of each of the files, and adds them to the media list after the old list has been removed.
     */
    private void chooseNewPlaylist() {
        if(!LockedPlaylistValueAccess.lockedPlaylist) {
            newFilePath = fileChooser.getNewFilePath();
            String[] options = {};

            // Only change the playlist if the new path is different from the old one.
            if((currentFilePath != newFilePath)  && (newFilePath != null)) {
                mediaPlayer.stop();
                mediaListPlayer.stop();
                ArrayList<String> files = getFilenames(newFilePath);
                createList(files);
                currentFilePath = newFilePath;
                mediaList.clear();

                // Add the new items to the playlist.
                for(String filename : files) {
                    mediaList.addMedia(newFilePath + "\\" + filename, options);	
                }

                mediaListPlayer.setMediaList(mediaList);
                mediaListPlayer.setMode(MediaListPlayerMode.LOOP);
                mediaListPlayer.setMediaPlayer(mediaPlayer);

                mediaPlayer.release();
                mainFrame.dispose();
                mainFrame.setVisible(false);
                mediaPlayer = openMediaPlayer();
                currentPlayIndex = 0;
                playContentsJList.repaint();
            }
        }
    }

    /**
     * Plays the selected file from the JList. Called whenever the user chooses an item from the list.
     */
    private void chooseFromPlaylist() {
        if(!LockedPlaylistValueAccess.lockedPlaylist) {
            mediaListPlayer.playItem(playContentsJList.getSelectedIndex());
        }
    }

    /**
     * Stop the media playback.
     */
    private void stopMedia() {
        if(!LockedPlaylistValueAccess.lockedPlaylist) {
            mediaListPlayer.stop();
        }	
    }

    /**
     * Sets up the action listeners for all the buttons which control the playlist. 
     * @param buttonName, JButton giving the button to add the listener to.
     * @param playerMethodCaseName, name for the case statement to call the method action for each listener,
     * should be LOWERCASE entirely...
     * @return Return the button which was passed in.
     */
    private JButton setupListenerAndAction(final JButton buttonName, final String playerMethodCaseName) {
        buttonName.addMouseMotionListener(genericMouseMotionListener);
        buttonName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                switch (playerMethodCaseName) {
                case "stop":            stopMedia();                                                        break;
                case "pause":           pauseMedia();                                                       break;
                case "play":            playMedia();                                                        break;
                case "next":            nextMedia();                                                        break;
                case "previous":        previousMedia();                                                    break;
                case "openplaylist":    openPlaylist();                                                     break;
                case "lockplaylist":    
                    lockedAndPreventingPlayerUse(buttonName, !LockedPlaylistValueAccess.lockedPlaylist);    break;
                default:                                                                                    break;
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
        sliderName.addMouseMotionListener(genericMouseMotionListener);
        sliderName.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent arg0) {
                switch (playerMethodCaseName) {
                case "volume":                                  break;
                case "time":       changingTimeByHand = false;  break;
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                switch (playerMethodCaseName) {
                case "volume":                                  break;
                case "time":       changingTimeByHand = true;   break;
                } 
            }

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

        });

        sliderName.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent arg0) {
                if(!sliderName.getValueIsAdjusting()) {
                    switch (playerMethodCaseName) {
                    case "volume":      
                        adjustVolume(sliderName.getValue()); 
                        break;
                    case "time":        
                        if(changingTimeByHand) {
                            adjustTime(sliderName.getValue()); 
                        }
                        break;
                    }
                }
            }  
        });
    }

    /**
     * Changes the time value when the user drags the slider.
     * @param valueInt
     */
    private void adjustTime(int valueInt) {
        float valueFloat = ((float) valueInt)/1000;
        float totalTimeSeconds = mediaPlayer.getLength()/1000;
        float timeToGoTo = totalTimeSeconds - (totalTimeSeconds*(1 - valueFloat));
        mediaPlayer.setTime(((long) timeToGoTo)*1000);
    }


    /**
     * Retrieves the whole control panel as a JPanel, containing everything
     * the user requires for setting up the audio player. 
     * @param widthOfPanel- width you would like the panel to be
     * @param heightOfSlide- height you qould like the panel to be
     * @return
     */
    public JPanel getFullControlPanel(int widthOfPanel, int heightOfSlide) {
        this.widthOfPanel=widthOfPanel;
        fullPanel.setLayout(null);
        fullPanel.setBackground(new Color(15526830));
        fullPanel.setBounds(3,0,widthOfPanel,heightOfSlide);
        fullPanel.add(getLockPlaylistButton(widthOfPanel, heightOfSlide));
        fullPanel.add(getPlayButton(widthOfPanel, heightOfSlide));
        fullPanel.add(getPauseButton(widthOfPanel, heightOfSlide));
        fullPanel.add(getStopButton(widthOfPanel, heightOfSlide));
        fullPanel.add(getVolumeSlider(widthOfPanel, heightOfSlide));
        fullPanel.add(getVolumeLabel(widthOfPanel, heightOfSlide));
        fullPanel.add(getNextButton(widthOfPanel, heightOfSlide));
        fullPanel.add(getPreviousButton(widthOfPanel, heightOfSlide));
        fullPanel.add(getTimeLabel(widthOfPanel, heightOfSlide));
        fullPanel.add(getScrollSlider(widthOfPanel, heightOfSlide));

        JButton button = fileChooser.getButton();

        button.setBounds(
                (int) (widthOfPanel/2) - (int) fileChooser.getButtonWidth()/2,
                (int) (heightOfSlide*0.025*14) + heightOfLockIcon + heightOfPlayButton,
                fileChooser.getButtonWidth(),
                fileChooser.getButtonHeight());
        playlistIconHeight = button.getIcon().getIconHeight();

        fullPanel.add(getScrollPaneAsPanel(widthOfPanel, heightOfSlide));
        fullPanel.add(button);

        return fullPanel;
    }

    /**
     * returns the volume label, that simply reads "Volume"
     * @param widthOfPanel
     * @param heightOfSlide
     * @return
     */
    private JLabel getVolumeLabel(int widthOfPanel, int heightOfSlide) {
        JLabel label = new JLabel("Volume");
        label.setFont(new Font("Papyrus", Font.BOLD, fontSize));
        label.setLayout(null);
        label.setBounds(
                (int) (widthOfPanel*0.385) + 20,
                (int) (heightOfSlide*0.025*5) + heightOfLockIcon + heightOfPlayButton,
                (int) (widthOfPanel*0.3),
                (int) (heightOfSlide*0.075));

        return label;
    }

    /**
     * Gets the button which unlocks and locks the playlist selection button.
     * @param widthOfPanel
     * @param heightOfSlide
     * @return
     */
    private JButton getLockPlaylistButton(int widthOfPanel, int heightOfSlide) {
        areWeUnlocked = false;
        JButton button = new JButton();
        setUpButtonImage(button, unlockImage, 100, 50);
        button.setBorderPainted(false); 
        button.setContentAreaFilled(false); 
        button.setFocusPainted(false); 
        button.setOpaque(false);

        button.setBounds(
                (int) (widthOfPanel/2) - (int) button.getIcon().getIconWidth()/2,
                (int) (heightOfSlide*0.025),
                100,
                50);

        setupListenerAndAction(button, "lockplaylist");
        heightOfLockIcon = 50;
        return button;      
    }

    /**
     * Gets the label which displays the time through and track length of the selected audio.
     * @param widthOfPanel
     * @param heightOfSlide
     * @return
     */
    private JLabel getTimeLabel(int widthOfPanel, int heightOfSlide) {
        timeLabel.setText(this.getCurrentPosition() + "/" + this.getTrackLength());
        timeLabel.setFont(new Font("Papyrus", Font.BOLD, fontSize));

        timeLabel.setBounds(
                (int) (widthOfPanel*0.4) + 18,
                (int) (heightOfSlide*0.025*10) + heightOfLockIcon + heightOfPlayButton,
                (int) (widthOfPanel*0.2),
                (int) (heightOfSlide*0.1));
        return timeLabel;
    }

    /**
     * Gets the JButton to stop media being played back and return play position to beginning of track.
     * @param heightOfSlide 
     * @param widthOfPanel 
     * @return JButton
     */
    private JButton getStopButton(int widthOfPanel, int heightOfSlide) {
        ImageIcon stop = new ImageIcon(stopImage);
        JButton button = new JButton(stop);
        button.setBorderPainted(false); 
        button.setContentAreaFilled(false); 
        button.setFocusPainted(false); 
        button.setOpaque(false);
        button.setBounds(
                (int) (widthOfPanel*0.048) + widthOfPlayButton*2 + 25,
                (int) (heightOfSlide*0.038) + heightOfLockIcon,
                stop.getIconWidth(),
                stop.getIconHeight());
        setupListenerAndAction(button, "stop");      
        return button;       
    }

    /**
     * Gets the JButton to pause media playback, retaining play position.
     * @param heightOfSlide 
     * @param widthOfPanel 
     * @return JButton
     */
    private JButton getPauseButton(int widthOfPanel, int heightOfSlide) {
        ImageIcon pause = new ImageIcon(pauseImage);
        JButton button = new JButton(pause);
        button.setBorderPainted(false); 
        button.setContentAreaFilled(false); 
        button.setFocusPainted(false); 
        button.setOpaque(false);
        button.setBounds(
                (int) (widthOfPanel*0.025*2) + widthOfPlayButton + 25,
                (int) (heightOfSlide*0.025*2) + heightOfLockIcon,
                pause.getIconWidth(),
                pause.getIconHeight());
        setupListenerAndAction(button, "pause");      
        return button;      
    }

    /**
     * Gets the JButton to play media, plays the current selected track, if it is paused or stopped.
     * @param heightOfSlide 
     * @param widthOfPanel 
     * @return JButton
     */
    private JButton getPlayButton(int widthOfPanel, int heightOfSlide) {
        ImageIcon play = new ImageIcon(playImage);
        JButton button = new JButton(play);
        button.setBorderPainted(false); 
        button.setContentAreaFilled(false); 
        button.setFocusPainted(false); 
        button.setOpaque(false);
        button.setBounds(
                (int) (widthOfPanel*0.025) + 25,
                (int) (heightOfSlide*0.025*2) + heightOfLockIcon,
                play.getIconWidth(),
                play.getIconHeight());
        setupListenerAndAction(button, "play");  
        widthOfPlayButton = play.getIconWidth();
        heightOfPlayButton = play.getIconHeight();
        return button;     
    }

    /**
     * Gets the JButton that plays the next media in the playlist, loops round if at end of playlist.
     * @param heightOfSlide 
     * @param widthOfPanel 
     * @return JButton
     */
    private JButton getNextButton(int widthOfPanel, int heightOfSlide) {   
        ImageIcon next = new ImageIcon(nextImage);
        JButton button = new JButton(next);
        button.setBorderPainted(false); 
        button.setContentAreaFilled(false); 
        button.setFocusPainted(false); 
        button.setOpaque(false);

        button.setBounds(
                (int) (widthOfPanel*0.025)  + (int) (next.getIconWidth()*4.5) + 25,
                (int) (heightOfSlide*0.025*2) + heightOfLockIcon,
                next.getIconWidth(),
                next.getIconHeight());

        setupListenerAndAction(button, "play");  
        widthOfPlayButton = next.getIconWidth();
        heightOfPlayButton = next.getIconHeight();  
        setupListenerAndAction(button, "next");

        return button;     
    }

    /**
     * Gets the JButton that plays the previous media in the playlist, loops back round if at beginning of playlist.
     * @param heightOfSlide 
     * @param widthOfPanel 
     * @return JButton
     */
    private JButton getPreviousButton(int widthOfPanel, int heightOfSlide) {
        ImageIcon previous = new ImageIcon(previousImage);
        JButton button = new JButton(previous);
        button.setBorderPainted(false); 
        button.setContentAreaFilled(false); 
        button.setFocusPainted(false); 
        button.setOpaque(false);

        button.setBounds(
                (int) (widthOfPanel*0.025)  + (int) (previous.getIconWidth()*3.5) + 25,
                (int) (heightOfSlide*0.025*2) + heightOfLockIcon,
                previous.getIconWidth(),
                previous.getIconHeight());

        setupListenerAndAction(button, "play");  

        widthOfPlayButton = previous.getIconWidth();
        heightOfPlayButton = previous.getIconHeight();  

        setupListenerAndAction(button, "next");      
        return button;        
    }

    /**
     * Returns the JSlider which controls the volume of playback, valued from 0-100.
     * @param heightOfSlide 
     * @param widthOfPanel 
     * @return JSlider
     */
    private JSlider getVolumeSlider(int widthOfPanel, int heightOfSlide) {
        JSlider slider = new JSlider();
        slider.setOpaque(false);
        slider.setBounds(
                (int) (widthOfPanel*0.025),
                ((int) (heightOfSlide*0.025*2)) + heightOfLockIcon + heightOfPlayButton,
                (int) (widthOfPanel*0.95),
                (int) (heightOfSlide*0.1));

        slider.setMinimum(0);
        slider.setMaximum(100);
        slider.setValue(100);
        slider.validate();

        setupListenerAndAction(slider, "volume");

        return slider;
    }

    /**
     * Gets the slider which can be used to scroll through an audio track.
     * @param widthOfPanel
     * @param heightOfSlide
     * @return
     */
    private JSlider getScrollSlider(int widthOfPanel, int heightOfSlide) {
        timeSlider.setBounds(
                (int) (widthOfPanel*0.025),
                (int) (heightOfSlide*0.025*7) + heightOfLockIcon + heightOfPlayButton,
                (int) (widthOfPanel*0.95),
                (int) (heightOfSlide*0.1));

        timeSlider.setOpaque(false);
        timeSlider.setMinimum(0);
        timeSlider.setMaximum(1000);
        setupListenerAndAction(timeSlider, "time");

        return timeSlider;
    }

    /**
     * Pauses media if it is playing, plays media if it is paused.
     */
    private void pauseMedia() {
        if(!LockedPlaylistValueAccess.lockedPlaylist) { 
            mediaPlayer.pause();
        }
    }

    /**
     * Does what it says on the tin.
     */
    private void playMedia() {
        if(!LockedPlaylistValueAccess.lockedPlaylist) { 
            mediaPlayer.play();
            if(isPaused == true) {
                isPaused = false;
            }
        }
    }

    /**
     * Does what it says on the tin.
     */
    private void nextMedia() {
        if(!LockedPlaylistValueAccess.lockedPlaylist) { 
            mediaListPlayer.playNext();
            playContentsJList.setSelectedIndex(playContentsJList.getSelectedIndex() + 1);
        }
    }

    /**
     * Does what it says on the tin.
     */
    private void previousMedia() {
        if(!LockedPlaylistValueAccess.lockedPlaylist) { 
            mediaListPlayer.playPrevious();
            playContentsJList.setSelectedIndex(playContentsJList.getSelectedIndex() - 1);
        }
    }

    /**
     * Takes the value from 0-100 for the percentage volume playback, then adjusts the volume of the 
     * MediaPlayer accordingly.
     * @param volumePercent
     */
    private void adjustVolume(int volumePercent) {
        if(!LockedPlaylistValueAccess.lockedPlaylist) { 
            mediaPlayer.setVolume(volumePercent);
        }
    }

    /**
     * Does what it says on the tin.
     */
    private void openPlaylist() {
        if(!LockedPlaylistValueAccess.lockedPlaylist) { 
            playlistFrame.setVisible(true);
        }
    }

    /**
     * returns boolean of is the playlist locked off or not.
     * @return
     */
    public boolean isPlaylistLocked() {
        return LockedPlaylistValueAccess.lockedPlaylist;
    }

    /**
     * Sets the unlocking and locking of the playlist selection.
     * Changes the button icon and sets the new value of lockedPlaylist.
     * @param button
     * @param trueOrFalse
     */
    private void lockedAndPreventingPlayerUse(JButton button, boolean trueOrFalse) {
        if(!areWeUnlocked) {
            setUpButtonImage(button, lockImage, 100, 50);
            areWeUnlocked = true;
        }
        else if(areWeUnlocked) {
            setUpButtonImage(button, unlockImage, 100, 50);
            areWeUnlocked = false;
        }
        mediaPlayer.stop();
        LockedPlaylistValueAccess.lockedPlaylist = trueOrFalse;
        fileChooser.lockTheOpenButton(trueOrFalse);
        button.repaint();
    }

    /**
     * gets the current position through the track in MM:SS format.
     * @return
     */
    public String getCurrentPosition() {
        long totalLength = getTotalLengthInSeconds();
        float currentPosition = mediaPlayer.getPosition();
        float position = currentPosition*totalLength;
        int minutes = (int) (position/60);
        int seconds = (int) (position % 60);
        return minutes + ":" + seconds;
    }

    /**
     * gets the total track length in seconds.
     * @return
     */
    public long getTotalLengthInSeconds() {
        return mediaPlayer.getLength()/1000;
    }

    /**
     * gets the total track length as string in format MM:SS
     * @return
     */
    public String getTrackLength() {
        long seconds = mediaPlayer.getLength()/1000 % 60;
        int minutes = (int) (mediaPlayer.getLength()/1000/60);
        return minutes + ":" + seconds;
    }


    /**
     * sets up the GUI for the playlist, adding the browse button and playlist to a panel, fills
     * the list with the values it gets from the selected playlist folder, then it is added to a JFrame. 
     * @param files, The arraylist of strings containing the filenames of all files in the current playlist.
     */
    private void setupGUI(ArrayList<String> files) {
        JPanel playlistChooserPanel = new JPanel();
        playlistChooserPanel.setLayout(null);
        playlistChooserPanel.setBounds(0, 0, 0, 0); 
        playlistChooserPanel.add(fileChooser.openDialog());

        createList(files);

        contentPane = playlistFrame.getContentPane();
        contentPane.setLayout(null);

        mediaPlayer = openMediaPlayer();

        fullPanel.setLayout(null);
        fullPanel.setBounds(0, 0, 100, 100);
        fullPanel.add(playlistChooserPanel);
        fullPanel.add(playPanel);
    }

    /**
     * sets up and gets the scroll pane which houses the playlist which has been
     * chosen by the file chooser.
     * @param widthOfPanel
     * @param heightOfSlide
     * @return
     */
    public JPanel getScrollPaneAsPanel(int widthOfPanel, int heightOfSlide) {
        scrollPanel = new JPanel();
        scrollPanel.setOpaque(false);
        scrollPanel.setLayout(null);
        scrollPanel.addKeyListener(JFrame.getFrames()[0].getKeyListeners()[0]);

        scrollPanel.setBounds(
                (int) (widthOfPanel*0.025),
                (int) (heightOfSlide*0.025*14) + heightOfLockIcon + heightOfPlayButton + playlistIconHeight,
                (int) (widthOfPanel*0.95),
                (int) (heightOfSlide*0.2));

        scrollPane.setViewportView(playContentsJList);

        scrollPane.setBounds(
                0, 
                0, 
                (int) (widthOfPanel*0.95),
                (int) (heightOfSlide*0.2));

        scrollPanel.add(scrollPane);
        playContentsJList.setFont(new Font("Papyrus", Font.BOLD, fontSize));

        // Add listener which plays a piece of media whenever the user chooses it in the JList.
        playContentsJList.addMouseMotionListener(genericMouseMotionListener);
        playContentsJList.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                changingSelectedPlaylistByHand = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                changingSelectedPlaylistByHand = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseClicked(MouseEvent e) {}
        });

        playContentsJList.addListSelectionListener(new ListSelectionListener() {  
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if(changingSelectedPlaylistByHand) {
                    chooseFromPlaylist();
                }
            }
        });

        return scrollPanel; 
    }

    /**
     * Clears the existing list of filenames, then adds each element in the parameter nameList into
     * the listModel, which is then placed on the JPanel.
     * @param nameList, arraylist of strings containing the names of each of file to be added to the playlist.
     */
    private void createList(ArrayList<String> nameList) {
        listModel.clear();

        for(String listItem : nameList){
            listModel.addElement(listItem);
        }

        scrollPane.repaint();
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
        ArrayList<String> returnList = new ArrayList<String>();

        if(!newFilePath.equals("")) {
            for(int i = 0; i < shit.length; i ++) {
                returnList.add(shit[i].getName());
            }
        }
        return returnList;
    }

    /**
     * Sets up the media player with the initial playlist, adds it to Panels and frames, ready for play.
     * @return EmbeddedMediaPlayer.
     */
    private EmbeddedMediaPlayer openMediaPlayer() {
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        final EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        Canvas canvas = new Canvas();
        canvas.setBackground(Color.black);
        canvas.addKeyListener(JFrame.getFrames()[0].getKeyListeners()[0]);
        CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(canvas);
        EmbeddedMediaPlayer mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
        mediaPlayer.setVideoSurface(videoSurface);
        mediaListPlayer = mediaPlayerFactory.newMediaListPlayer();
        mediaListPlayer.setMediaPlayer(mediaPlayer);

        JPanel cp = new JPanel();
        cp.setBackground(Color.black);
        cp.setLayout(new BorderLayout());
        cp.add(canvas, BorderLayout.CENTER);

        cp.setBounds(new Rectangle(0, 0, 0, 0));
        fullPanel.add(cp);

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

    /**
     * Sets up the scaled image on the given JButton
     * @param button- Jbutton to attach image to.
     * @param image- path to the image to use
     * @param width
     * @param height
     */
    private void setUpButtonImage(JButton button, String image, int width, int height){
        BufferedImage choosePageButtonImage;
        try{
            choosePageButtonImage = ImageIO.read(new File(image));
            Image scaledButton = choosePageButtonImage.getScaledInstance(width,height,java.awt.Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledButton));
        }catch (IOException ex){

        }
    }

    /**
     * Sets the dimensions of the scroll panel and the containing full panel which is to be returned
     * and added to fullControlPanel.
     * @param heightOfSlide
     */
    public void setDimension(int heightOfSlide){
        fullPanel.setBounds(3,0,widthOfPanel,heightOfSlide);
        scrollPanel.setBounds(
                (int) (widthOfPanel*0.025),
                350,
                (int) (widthOfPanel*0.95-10),
                (int) (heightOfSlide-350-(heightOfSlide*0.025)));
        scrollPane.setBounds(0, 0, (int) (widthOfPanel*0.95-10),
                (int) (heightOfSlide-360-(heightOfSlide*0.025)));
    }

}
