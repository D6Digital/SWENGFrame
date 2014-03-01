package videoModule;

/**
 * @author Josh Drake
 */
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;



import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.NativeLibrary;

public class VideoPlayer extends JPanel{

	protected static JButton playButton, stopButton, closeButton;
	protected static EmbeddedMediaPlayer mediaPlayer;
	protected static JPanel vidControlPanel;
	protected static JPanel vidpanel;
	protected static JFrame frame;
	protected static JSlider bar;
	protected static PlayerControlsPanel ControlPanel;
	
	
	public VideoPlayer() {
	
		NativeLibrary.addSearchPath(
                RuntimeUtil.getLibVlcLibraryName(), "resources/resources/lib/vlc-2.0.1"
            );
		
		frame = new JFrame("Video Player");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    vidpanel = new JPanel();
	   
	    
	    
	    Canvas canvas = new Canvas();
	    MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
	    CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(canvas);
	    mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
	    mediaPlayer.setVideoSurface(videoSurface);
	    canvas.setBounds(0, 0, 720, 276);
	
	    
	    ControlPanel = new PlayerControlsPanel(mediaPlayer);
	        
	    vidpanel.add(canvas);
	    
	  //  ControlPanel.setVisible(false);
     
	    frame.add(vidpanel, BorderLayout.CENTER);
	    frame.add(ControlPanel, BorderLayout.SOUTH);
	    
	    frame.pack();
	    frame.setVisible(true);
		
	    mediaPlayer.playMedia("resources/resources/video/avengers.mp4");
	    
//	    frame.addMouseListener(new java.awt.event.MouseAdapter() {   
//	    	public void mouseEntered(java.awt.event.MouseEvent evt) {        
//	    		ControlPanel.setVisible(true);  
//	    		}    
//	    	public void mouseExited(java.awt.event.MouseEvent evt) {        
//	    		ControlPanel.setVisible(false);  
//	    		}
//	    	});
}
	
	public static void main(String[] args) {
	
               new VideoPlayer();
                                     

	}
	
		
	
	

}
