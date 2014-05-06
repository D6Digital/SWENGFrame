package videoModule;

/**
 * @author Josh Drake
 */
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;



import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import presentation.Video;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.NativeLibrary;

public class VideoPlayer {

	protected static JButton playButton, stopButton, closeButton;
	protected static EmbeddedMediaPlayer mediaPlayer;
	protected static JPanel vidControlPanel;
	protected static JPanel vidpanel;
	protected static JPanel masterPanel;
	protected static JFrame frame;
	protected static JSlider bar;
	protected static ImageIcon img, img2;
	protected static PlayerControlsPanel ControlPanel;
	protected static JLabel pausedLabel;
	

	public VideoPlayer(Video video) {
		
	
		NativeLibrary.addSearchPath(
                RuntimeUtil.getLibVlcLibraryName(), "resources/lib/vlc-2.1.3"
            );
		
	    int xcoord = video.getX_coord();
	    final int ycoord = video.getY_coord();
	    int start = video.getStart();
	    int end = video.getStart() + video.getDuration();
	    int layer = video.getLayer();
	    String file = video.getFile();
	    int width = video.getWidth();
	    final int height = video.getHeight();
	    int length = video.getLength();

		masterPanel = new JPanel();
		masterPanel.setBounds(0, 0, video.getWidth(), video.getHeight());
		
		System.out.println("xcoord = " + xcoord);
		System.out.println("ycoord = " + ycoord);
		System.out.println("start = " + start);
		System.out.println("end = " + end);
		System.out.println("layer = " + layer);
		System.out.println("file = " + file);
		System.out.println("Width = " + width);
		System.out.println("Height = " + height);
		System.out.println("Length = " + length);
		
		frame = new JFrame("Video Player");
		vidpanel = new JPanel();
		img = new ImageIcon("resources/buttons/pauseText.png");
	    img2 = new ImageIcon("resources/buttons/StoppedText.png");
	    pausedLabel = new JLabel(img);
	    Canvas canvas = new Canvas();    
		
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	    
	    MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
	    CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(canvas);
	    mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
	    mediaPlayer.setVideoSurface(videoSurface);
	    canvas.setBounds(0, 0, 720, 276);
	    
	    ControlPanel = new PlayerControlsPanel(mediaPlayer);
	    
	    // disables default VLCJ action listeners
	    mediaPlayer.setEnableMouseInputHandling(false);
	    mediaPlayer.setEnableKeyInputHandling(false);
	    
	    
	    ControlPanel.setVisible(false);
	    ControlPanel.setOpaque(false);
	        
	    vidpanel.add(canvas);
	    
	    
	    
	    masterPanel.add(ControlPanel, BorderLayout.SOUTH);
	    masterPanel.add(vidpanel, BorderLayout.CENTER);    
	    //masterPanel.add(vidpanel);    
	    //frame.pack();
	    //frame.setVisible(true);
		
	   // mediaPlayer.playMedia("resources/resources/video/"+file);
	   // mediaPlayer.playMedia("resources/video/video/"+file, ":start-time="+start, ":stop-time="+end);
	    System.out.println(masterPanel.getLocation().x);
	    System.out.println(masterPanel.getLocation().y);
	    
	    canvas.addMouseListener(new java.awt.event.MouseAdapter() {  
	    	@Override
		    public void mousePressed(MouseEvent e) {
		    	if (mediaPlayer.isPlaying()){
		 	       mediaPlayer.pause();
		 	       ControlPanel.setVisible(false);
		 	       vidpanel.setVisible(false);
		 	       frame.add(pausedLabel, BorderLayout.CENTER);		 	      
		    	}
		    	else{
		    	mediaPlayer.play();
		    		}		 		 	       
		 	    }	    	
	    	});
	    
	    masterPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter(){
	    	@Override
	    	public void mouseMoved(MouseEvent e1){
	    		int xCoordinate = e1.getX();
	    		int yCoordinate = e1.getY();
	    		
	    		//System.out.println(xCoordinate + "," + yCoordinate);
	    		System.out.println("---------listener------------");
	    		System.out.println(yCoordinate);
	    		System.out.println((ycoord + height)- (height*0.2));
	    		System.out.println("---------END listener------------");
	    		
	    		if (yCoordinate > ((ycoord + height)- (height*0.2))){
	    			//if(!ControlPanel.isVisible()) {
	    			    ControlPanel.setVisible(true);
	    			//}
	    		}
	    		else {
                    //if(ControlPanel.isVisible()) {
                        ControlPanel.setVisible(false);
                    //}
	    		}
	    	}
	    });
	    

	    
	    ControlPanel.addMouseListener(new java.awt.event.MouseAdapter() {   
	    	@Override
	    	public void mouseExited(java.awt.event.MouseEvent evt) {        
	    		ControlPanel.setVisible(false);  
	    		}
	    	});
        
        ControlPanel.addMouseListener(new java.awt.event.MouseAdapter() {   
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {        
                ControlPanel.setVisible(true);  
                }
            });
	      

	 pausedLabel.addMouseListener(new java.awt.event.MouseAdapter() {   
		 	@Override
		    public void mousePressed(MouseEvent e) {
		    	frame.remove(pausedLabel);
		    	vidpanel.setVisible(true);
		    	mediaPlayer.play();
		    	PlayerControlsPanel.setPlayButton();		    	
		    	}	    	
	    	});
	}
	
	public void setupListeners(int controlPanelYLocation) {
	    
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


    public JPanel getPanel() {
        return masterPanel;
    }

//	public static void main(String[] args) {
//		
//		Video Video = new Video(3, 5, 30, 50, 8, "monstersinc_high.mpg", 4, 76, 89);
//        new VideoPlayer(Video);
//                                     
//
//	}
	
 


}
