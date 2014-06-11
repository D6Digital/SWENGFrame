package videoModule;

import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import presentation.Video;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import com.sun.jna.NativeLibrary;

/**
 * Video player module. Uses the PlayerControlPanel and EmbeddedMediaPlayer
 * in order to create a video player inside a JPanel which can be placed 
 * upon a slide in the presentation.
 * @author Josh Drake
 * @author samPick
 */
public class VideoPlayer extends JPanel{
    
    private static final long serialVersionUID = 1L;
    JButton playButton, stopButton, closeButton;
    EmbeddedMediaPlayer mediaPlayer;
    JPanel vidControlPanel, vidpanel, masterPanel;
	public JPanel overlayPanel;
    JSlider bar;
    ImageIcon img;
    public PlayerControlsPanel ControlPanel;
    JLabel pausedLabel;
    int xcoord, ycoord, start, end, width, height;
    String file;
    private Canvas canvas;
    private Timer controlPanelTimer;

    /**
     * Constructor for Video Player, must provide a mouseAdapter to setup the
     * listeners for the player with the rest of the presentation, as well as
     * a video file to be used for attributing a given video to a video player.
     * @param video
     * @param videoListener
     */
    public VideoPlayer(Video video, MouseAdapter videoListener) {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "resources/lib/vlc-2.1.3");

        // set all parameters to base themselves upon information from video object.
        xcoord = video.getX_coord();
        ycoord = video.getY_coord();
        start = video.getStart();
        end = video.getStart() + video.getDuration();
        file = video.getFile();
        width = video.getWidth();
        height = video.getHeight();

        // set bounds of complete panel
        this.setLayout(null);
        this.setBounds(video.getX_coord(), video.getY_coord(), video.getWidth(), video.getHeight());

        setupVidpanel();

        // gives invisible panel over top of video to allow pausing by clicking on video.
        setupOverlayPanel();
        
        canvas = new Canvas();
        canvas.setBackground(Color.BLACK);

        // Initialise and setup the video player.
        setupMediaPlayer();
        
        canvas.setBounds(0, 0, width, height);

        // Get the control panel to be associated with this video player.
        setupControlPanel();

        // Setup the listeners for the features of the control panel and
        // their relation to the video playback.
        ActionListener taskPerformer =	new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlPanel.setVisible(false);

            }};
            
            // Create new timer
            controlPanelTimer = new Timer(2500,taskPerformer);
            controlPanelTimer.setInitialDelay(2500);
            controlPanelTimer.setRepeats(false);

            mediaPlayer.setEnableMouseInputHandling(false);
            mediaPlayer.setEnableKeyInputHandling(false);

            ControlPanel.setVisible(false);

            vidpanel.add(canvas);

            this.add(ControlPanel);
            this.add(vidpanel);    

            mediaPlayer.prepareMedia(file, ":start-time="+start, ":stop-time="+end);

            // Setup the listener that plays the player and sets pause icon when play is clicked.
            addCanvasListener(videoListener);

            

            // setup listeners for making control panel appear and disappear when not hovered over.
            controlPanelListeners();

            // setup listener for pausing or playing whenever user presses on video overlay.
            addOverlayPanelListener();
    }

	/**
	 * setup listener for pausing or playing whenever user presses on video overlay.
	 */
	private void addOverlayPanelListener() {
		overlayPanel.addMouseListener(new java.awt.event.MouseAdapter() {   
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        remove(overlayPanel);
		        vidpanel.setVisible(true);
		        mediaPlayer.play();	
		        ControlPanel.setPauseButton();
		        startTimer();
		        repaint();
		    }	    	
		});
	}

	/**
	 * setup listeners for making control panel appear and disappear when not hovered over.
	 */
	private void controlPanelListeners() {
		ControlPanel.addMouseListener(new java.awt.event.MouseAdapter() {   
		    @Override
		    public void mouseExited(java.awt.event.MouseEvent evt) {        
		        ControlPanel.setVisible(false); 
		        startTimer();
		    }
		});
		ControlPanel.addMouseListener(new java.awt.event.MouseAdapter() {   
		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent evt) {        
		        ControlPanel.setVisible(true); 
		        startTimer();
		    }
		});
	}

	/**
	 * Setup the listener that plays the player and sets pause icon when play is clicked
	 * @param videoListener 
	 */
	private void addCanvasListener(MouseMotionListener videoListener) {
		canvas.addKeyListener(JFrame.getFrames()[0].getKeyListeners()[0]);
		canvas.addMouseListener(new java.awt.event.MouseAdapter() {  
		    @Override
		    public void mousePressed(MouseEvent e) {
		        if (mediaPlayer.isPlaying()){
		            mediaPlayer.pause();
		            ControlPanel.setPlayButton();
		            add(overlayPanel);	
		            startTimer();
		        }
		        else{
		            mediaPlayer.play();
		            ControlPanel.setPauseButton();
		        }		 		 	       
		    }	    	
		});
		canvas.addMouseMotionListener(videoListener);
	}

	/**
	 * Sets up the panel for the controls
	 */
	private void setupControlPanel() {
		ControlPanel = new PlayerControlsPanel(mediaPlayer);
        ControlPanel.setBounds(0, height-80, width, 80);
        ControlPanel.setBackground(Color.black);
        ControlPanel.setOpaque(true);
	}

	/**
	 * Initialise and setup the video player.
	 */
	private void setupMediaPlayer() {
		MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(canvas);
        mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
        mediaPlayer.setVideoSurface(videoSurface);
	}

	/**
	 * Gives invisible panel over top of video to allow pausing by clicking on video.
	 */
	private void setupOverlayPanel() {
		overlayPanel = new JPanel();
        overlayPanel.setLayout(null);
        overlayPanel.setOpaque(false);
        overlayPanel.setBounds(0, 0, width, height);
	}

	/**
	 * sets up the panel for the visdeo to be played in
	 */
	private void setupVidpanel() {
		vidpanel = new JPanel();
        vidpanel.setLayout(null);
        vidpanel.setBounds(0, 0, width, height);
        img = new ImageIcon("resources/buttons/pauseText.png");
	}
    
    /**
     * Stops the media playback
     */
    public void stopMedia(){
        mediaPlayer.stop();
    }

    /**
     * return true if media is playing.
     * @return
     */
    public Boolean isPlaying(){
        return mediaPlayer.isPlaying();
    }

    /**
     * resizes the video by the scaling factor x in width, y in height.
     * @param scalingFactorX
     * @param scalingFactorY
     */
    public void resizeVideo(double scalingFactorX, double scalingFactorY) {
        this.setBounds(
                (int) (xcoord*scalingFactorX), 
                (int) (ycoord*scalingFactorY), 
                (int) (width*scalingFactorX), 
                (int) (height*scalingFactorY));
        ControlPanel.setBounds(
                0, 
                (int) (height*scalingFactorY)-80, 
                (int) (width*scalingFactorX), 
                80);
        vidpanel.setBounds(
                0, 
                0, 
                (int) (width*scalingFactorX), 
                (int) (height*scalingFactorY));
        canvas.setBounds(
                0,
                0, 
                (int) (width*scalingFactorX), 
                (int) (height*scalingFactorY));
        overlayPanel.setBounds(
                0, 
                0, 
                (int) (width*scalingFactorX), 
                (int) (height*scalingFactorY));
        this.repaint();
    }

    /**
     * Starts the timer to poll for changes in the control panel.
     */
    public void startTimer() {
        if(controlPanelTimer.isRunning()){
            controlPanelTimer.stop();
        }
        controlPanelTimer.start();
    }

}
