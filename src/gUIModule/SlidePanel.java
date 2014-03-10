package gUIModule;


import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import slideModule.Image;
import slideModule.Shapes;
import slideModule.Slide;
import slideModule.Sound;
import slideModule.Text;
import slideModule.Video;

/**
 * 
 * @author Sam Pick
 * @author Ruba Balanehru 
 * @author Chris Sainsbury 
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
	    
	    currentSlide = newSlide;
	   
	    ArrayList<Image> imageList = currentSlide.getImageList();
	    ArrayList<Text> textList = currentSlide.getTextList();
	    ArrayList<Video> videoList = currentSlide.getVideoList();
	    ArrayList<Shapes> shapeList = currentSlide.getShapeList();
	    ArrayList<Sound> soundList = currentSlide.getSoundList();
	    
	    
	    currentSlide.getSlideID();
	    currentSlide.getSlideName();
	        
	    for(Image image: imageList) {
	        JLabel imageLabel = addImage(image);
	        this.add(imageLabel);
	    }
       for(Text text : textList) {
            JPanel textPanel = addText(text);
            this.add(textPanel);
        }
       for(Video video: videoList) {
           JPanel videoPanel = addVideo(video);
           this.add(videoPanel);
       }
       
       for(Shapes shape: shapeList) {
           JPanel shapePanel = addShape(shape);
           this.add(shapePanel);
       }
       for(Sound sound: soundList) {
           JButton soundButton = addSound(sound);
           this.add(soundButton);
       }
	    
	}
	
	
	/**
	 * The SlidePanel should be cleared so it becomes an empty SlidePanel
	 * 
	 * After Clearing the panel setupSlide method above should be called to show a new slide
	 */
	public void clearSlide(){
	
		this.removeAll();
	
	
	}
	
	public void refreshSlide(Slide newSlide){
		
		this.removeAll();
		this.setupSlide(newSlide);
		
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
		// TODO The branch options on Images and shapes should allow a new slide to be chosen
		// when they are clicked 
			
	}
	
	/**
	 * Manage the user interaction with media objects on the slide
	 * Find where the action came from and call the appropriate method
	 * 
	 * For example a particular JButton which has a reference to the next slide
	 */
	public void mouseClicked(MouseEvent e){
		//get the x & y possition where the mouse was clicked.
		int x_ClickCoord = e.getX();
		int y_ClickCoord = e.getY();
		
		//TODO look to see if there is an object at those coords.
		//not sure what to replace unknownSlideObject with.
		int x_ObjectCoord = unknownSlideObject.getX_coord();
		int y_ObjectCoord = unknownSlideObject.getY_coord();
		
		//TODO see if the object has a branch value attached.
		//getBranch() is not a property of SlideObject() only the objects that extend it.
		int ObjectBranch = unknownSlideObject.getBranch();
		
		//TODO branch based on attached value.
		
	}
	
	/**
	 * 
	 * @param shape
	 * @return JPanel with shape on 
	 */
	private JPanel addShape(Shapes shape){
		
		// TODO makes a JPanel to represent the size and location of the shape
		// set the position ( .setBounds ) to the shapes first x,y etc
		// Eventually Use the bought-in module to improve this method
		return null;
	}
	
	
	
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
	
	
	/**
	 * 
	 * @param sound
	 * @return The sound container, e.g JButton
	 */
	private JButton addSound(Sound sound){
		
		// TODO instantiate the embedded slide music player when available
		// set the position ( .setBounds ) to the sound x,y etc
		// Start paused by default
		
		return null;
	}
	
	
	
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
