package src;

import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

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
	 * Using the information in slide add the media components to the slide panel 
	 * 
	 * @param newSlide
	 */
	public void setupSlide(Slide newSlide){
		//TODO Go through all media components of the slide and add them using there stored information
		// Also set the ID, name and current slide fields
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
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//private JPanel addShape(Shapes shape){
		
		// TODO make a JPanel to represent the size and location of the shape
		// Eventually Use the bought in module to improve this method
	//	return null;
	//}
	
	private JLabel addImage(Image image){
	
		// TODO make a JLabel containing an image
		// Eventually Use the bought in module to improve this method
		return null;
	}
	
	
	private JPanel addVideo(Video video){
		
		// TODO instantiate the video player when available and the video player controls
		// Start paused by default
		
		return null;
	}
	
	//private EmbeddedMusicPlayer addSound(Sound sound){
		
		// TODO instantiate the embedded slide music player when available
		// Start paused by default
		
	//	return null;
	//}
	
	private JPanel addText(Text text){
		
		// TODO use the Scribe class when available to add the text to a JPanel
		
		return null;
	}
	
	
	
	

}
