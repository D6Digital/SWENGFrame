package gUIModule;



import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import slideModule.Image;
import slideModule.Slide;
import slideModule.Text;
import slideModule.Video;

/**
 * 
 * @author Sam Pick
 *
 */
public class SlidePanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int slideID;
	String slideName;
	Slide currentSlide;
	
	
	
	/**
	 * Create a panel ready to have all the necessary slide media added
	 */
	public SlidePanel() {
		super();
		
		// set layout manager to null so media components can be added to their specific co-ordinates
		this.setLayout(null);
		
		// By default the panel is invisible until the player chooses to display it
		this.setVisibility(false);
		
		// TODO ensure this panel is ready to be displayed when necessary
	}
	
	
	
	/**
	 * Using the information in Slide, add the media components to the slide panel 
	 * 
	 * @param newSlide
	 */
	public void setupSlide(Slide newSlide){
		//TODO Go through all media components of the slide and add them using there stored information
		// This should use the simple methods for adding media in this class
		// Also set the ID, name and current slide fields
	}
	
	
	
	/**
	 * The SlidePanel should be cleared so it becomes an empty SlidePanel
	 * 
	 * After Clearing the panel setupSlide method above should be called to show a new slide
	 */
	public void clearSlide(){
		// TODO Delete all objects on the SlidePanel
	}
	
	
	
	/**
	 * 
	 * @param visible
	 */
	public void setVisibility(boolean visible){
		this.setVisible(visible);
	}
	
	
	
	/**
	 * 
	 * @return true if the slide is visible
	 */
	public boolean getVisibility(){
		return this.isVisible();
	}

	
	
	/**
	 * Manage the user interaction with media objects on the slide
	 * Find where the action came from and call the appropriate method
	 * 
	 * For example a particular JButton which has a reference to the next slide
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	//private JPanel addShape(Shapes shape){
		
		// TODO make a JPanel to represent the size and location of the shape
		// set the position ( .setBounds ) to the video x,y etc
		// Eventually Use the bought-in module to improve this method
	//	return null;
	//}
	
	
	
	/**
	 * 
	 * @param image
	 * @return A JLabel which contains the image
	 */
	private JLabel addImage(Image image){
	
		// TODO make a JLabel containing an image and set the position ( .setBounds ) to the image x,y etc
		// Eventually Use the bought-in module to improve this method
		return null;
	}
	
	
	
	/**
	 * 
	 * @param video
	 * @return The JPanel that holds the video player
	 */
	private JPanel addVideo(Video video){
		
		// TODO instantiate the video player when available and the video player controls
		// set the position ( .setBounds ) to the video x,y etc
		// Start paused by default
		
		return null;
	}
	
	
	
	//private EmbeddedMusicPlayer addSound(Sound sound){
		
		// TODO instantiate the embedded slide music player when available
		// set the position ( .setBounds ) to the sound x,y etc
		// Start paused by default
		
	//	return null;
	//}
	
	
	
	/**
	 * 
	 * @param text
	 * @return The JPanel which contains the text
	 */
	private JPanel addText(Text text){
		
		// TODO use the Scribe class when available to add the text to a JPanel
		// set the position ( .setBounds ) to the text x,y etc
		
		return null;
	}
	
	
	
	

}
