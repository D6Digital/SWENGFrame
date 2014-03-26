package musicPlayerModule;

import javax.swing.JFrame;
// import EmbeddedAudioPlayer    needed by TRiBE


/**
 * @author samPick
 * 
 * A simple example of using the embedded audio player with a number of different methods
 * provided by the module.
 * 
 * This is known to work with vlc-2.0.1 so far!
 * 
 * Newer versions of vlc may have a few method changes so we may need to have a few 
 * different method names in the embedded audio player.
 */
public class EmbeddedAudioPlayerExample {

	String vlcLibraryPath = "..\\..\\resources\\lib\\vlc-2.0.1";
	
	public static void main(String[] args) throws InterruptedException {
		
		JFrame frame = new JFrame(); 
	    EmbeddedAudioPlayer player = new EmbeddedAudioPlayer();
		frame.add(player.getPanel()); 
	    frame.setTitle("Audio Example");
	    frame.setSize(640, 480);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    
	    
	    
	    // simply setup and play media form an URL
	    player.playMedia("C:\\Users\\Public\\Music\\Sample Music\\Kalimba.mp3");
	    
	    Thread.sleep(5000);
	    
	    player.pauseMedia();
	    
	    Thread.sleep(5000);
	    
	    // set a start and end time for the music then start playing
	    player.prepareMedia("C:\\Users\\Public\\Music\\Sample Music\\Kalimba.mp3", 50, 60);
	    player.play();
	    
	    Thread.sleep(15000);
	    
	    // set a start time then wait 5 seconds before playing and set looping true
	    player.prepareMedia("C:\\Users\\Public\\Music\\Sample Music\\Kalimba.mp3", 55);
	    player.setLooping(true);
		
	    Thread.sleep(5000);
	    
	    player.play();
	    
	}
	
}
