package videoModule;

/**
 * @author Josh Drake
 * @author samPick
 */
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;



import java.awt.Canvas;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;

import presentation.Video;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.NativeLibrary;

public class VideoPlayer extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	JButton playButton, stopButton, closeButton;
	 EmbeddedMediaPlayer mediaPlayer;
	 JPanel vidControlPanel;
	 JPanel vidpanel;
	 JPanel masterPanel;
	 public JPanel overlayPanel;
	 JSlider bar;
	 ImageIcon img;
	 public PlayerControlsPanel ControlPanel;
	 JLabel pausedLabel;
	 int xcoord;
	 int ycoord;
	 int start;
	 int end;
	 String file;
	 int width;
	 int height;


	private Canvas canvas;


	private Timer controlPanelTimer;
	 

	public VideoPlayer(Video video, MouseAdapter videoListener) {
		
	
		NativeLibrary.addSearchPath(
                RuntimeUtil.getLibVlcLibraryName(), "resources/lib/vlc-2.1.3"
            );
		
	    xcoord = video.getX_coord();
	    ycoord = video.getY_coord();
	    start = video.getStart();
	    end = video.getStart() + video.getDuration();
	    file = video.getFile();
	    width = video.getWidth();
	    height = video.getHeight();

		this.setLayout(null);
		this.setBounds(video.getX_coord(), video.getY_coord(), video.getWidth(), video.getHeight());
		
		System.out.println("xcoord = " + xcoord);
		System.out.println("ycoord = " + ycoord);
		System.out.println("start = " + start);
		System.out.println("end = " + end);
		System.out.println("file = " + file);
		System.out.println("Width = " + width);
		System.out.println("Height = " + height);
		
		//frame = new JFrame("Video Player");
		
		vidpanel = new JPanel();
		vidpanel.setLayout(null);
		vidpanel.setBounds(0, 0, width, height);
		img = new ImageIcon("resources/buttons/pauseText.png");
	    //img2 = new ImageIcon("resources/buttons/StoppedText.png");
	    
	    overlayPanel = new JPanel();
	    overlayPanel.setLayout(null);
	    overlayPanel.setOpaque(false);
	    overlayPanel.setBounds(0, 0, width, height);
	    //pausedLabel = new JLabel(img);
	    //pausedLabel.setOpaque(true);
	    //pausedLabel.setBounds(width/2, height/2, 50, 50);
	    //overlayPanel.add(pausedLabel);
	    canvas = new Canvas();
	    canvas.setBackground(Color.BLACK);
		
		//frame.setLayout(new BorderLayout());
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	    
	    MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
	    CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(canvas);
	    mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
	    mediaPlayer.setVideoSurface(videoSurface);
	    canvas.setBounds(0, 0, width, height);
	    
	    ControlPanel = new PlayerControlsPanel(mediaPlayer);
	    //ControlPanel.setLayout(null);
	    ControlPanel.setBounds(0, height-80, width, 80);
	    ControlPanel.setBackground(Color.black);
	    ControlPanel.setOpaque(true);
	    
	    ActionListener taskPerformer =	new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControlPanel.setVisible(false);
				 
			}};
		controlPanelTimer = new Timer(2500,taskPerformer);
		controlPanelTimer.setInitialDelay(2500);
		controlPanelTimer.setRepeats(false);
	    
	    // disables default VLCJ action listeners
	    mediaPlayer.setEnableMouseInputHandling(false);
	    mediaPlayer.setEnableKeyInputHandling(false);
	    
	    
	    ControlPanel.setVisible(false);
	        
	    vidpanel.add(canvas);
	    
	    
	    
	    this.add(ControlPanel);
	    this.add(vidpanel);    
	    //this.add(vidpanel);    
	    //frame.pack();
	    //frame.setVisible(true);
		
	   // mediaPlayer.playMedia("resources/resources/video/"+file);
	   // mediaPlayer.playMedia("resources/video/video/"+file, ":start-time="+start, ":stop-time="+end);
	    mediaPlayer.prepareMedia(file, ":start-time="+start, ":stop-time="+end);
	    System.out.println(this.getLocation().x);
	    System.out.println(this.getLocation().y);
	    
	    canvas.addMouseListener(new java.awt.event.MouseAdapter() {  
	    	@Override
		    public void mousePressed(MouseEvent e) {
		    	if (mediaPlayer.isPlaying()){
		 	       mediaPlayer.pause();
		 	       ControlPanel.setPlayButton();
		 	       add(overlayPanel);	
		 	       startTimer();
		 	       //repaint();
		    	}
		    	else{
		    	mediaPlayer.play();
		    	ControlPanel.setPauseButton();
		    		}		 		 	       
		 	    }	    	
	    	});
	    
	    /*canvas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter(){
	    	@Override
	    	public void mouseMoved(MouseEvent e1){
	    		VideoPlayer videoPlayer = (VideoPlayer) e1.getSource();
	    		int xCoordinate = e1.getX();
	    		int yCoordinate = e1.getY();
	    		
	    		//System.out.println(xCoordinate + "," + yCoordinate);
	    		//System.out.println("---------listener------------");
	    		//System.out.println(yCoordinate);
	    		//System.out.println((height)- 80);
	    		//System.out.println("---------END listener------------");
	    		
	    		if(!videoPlayer.isPlaying())
	    		{
	    			videoPlayer.ControlPanel.setVisible(true);
	    		}
	    		else
	    		{
	    			if (yCoordinate > ((height)- 80)){
		    			//if(!ControlPanel.isVisible()) {
		    			    videoPlayer.ControlPanel.setVisible(true);
		    			//}
		    		}
	    			else
	    			{
	    			videoPlayer.ControlPanel.setVisible(false);
	    			}
	    		}
	    		
	    		
	    		//else {
                    //if(ControlPanel.isVisible()) {
                        //ControlPanel.setVisible(false);
                    //}
	    		//}
	    	}
	    });*/
	    
	    
	    canvas.addMouseMotionListener(videoListener);

	    
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
	
	public void setupListeners(int controlPanelYLocation) {
	    
	}
	
	public void stopMedia(){
		mediaPlayer.stop();
	}
	
	public Boolean isPlaying(){
		return mediaPlayer.isPlaying();
	}

	public void resizeVideo(double scalingFactorX, double scalingFactorY) {
		
		this.setBounds((int) (xcoord*scalingFactorX), (int) (ycoord*scalingFactorY), (int) (width*scalingFactorX), (int) (height*scalingFactorY));
		ControlPanel.setBounds(0, (int) (height*scalingFactorY)-80, (int) (width*scalingFactorX), 80);
		vidpanel.setBounds(0, 0, (int) (width*scalingFactorX), (int) (height*scalingFactorY));
		canvas.setBounds(0, 0, (int) (width*scalingFactorX), (int) (height*scalingFactorY));
		overlayPanel.setBounds(0, 0, (int) (width*scalingFactorX), (int) (height*scalingFactorY));
		this.repaint();
		
	}

	public void startTimer() {
		if(controlPanelTimer.isRunning()){
			controlPanelTimer.stop();
		}
		controlPanelTimer.start();
		
	}
	
	/**
	 * Shows pause label when pause button is pressed
	 */
//	public static void setPause(){
//		if (mediaPlayer.isPlaying()){
//		 ControlPanel.setVisible(false);
//	     vidpanel.setVisible(false);
//	     frame.add(pausedLabel, BorderLayout.CENTER);
//		}
//		else{
//			ControlPanel.setVisible(true);
//			frame.remove(pausedLabel);
//		     vidpanel.setVisible(true);
//		     
//		}
//	}


    /*public JPanel getPanel() {
        return masterPanel;
    }*/

//	public static void main(String[] args) {
//		
//		Video Video = new Video(3, 5, 30, 50, 8, "monstersinc_high.mpg", 4, 76, 89);
//        new VideoPlayer(Video);
//                                     
//
//	}
	
 


}
