package gUIModule;


import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import musicPlayerModule.EmbeddedAudioPlayer;


import presentation.Image;
import presentation.Shapes;
import presentation.Slide;
import presentation.SlideObject;
import presentation.slideMediaObject;
import presentation.Sound;
import presentation.Text;
import presentation.Video;

import graphicsModule.GraphicsPainter;
import imageModule.ImagePainter;
import videoModule.VideoPainter;
import textModule.Scribe;



/**
 * 
 * @author Sam Pick
 * @author Ruba Balanehru 
 * @author Chris Sainsbury
 * @author Andrew Walter
 *
 */
public class SlidePanel extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int slideID;
	String slideName;
	Slide currentSlide;
	
	EmbeddedAudioPlayer audioPlayer;
	
	
	
	/**
	 * Create a panel ready to have all the necessary slide media added
	 */
	public SlidePanel() {
		super();
		
		audioPlayer = new EmbeddedAudioPlayer();
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
	   for(Sound sound: soundList) {
           addSound(sound);
       }    
	   for(Image image: imageList) {
	        addImage(image);
	   }
       for(Video video: videoList) {
           addVideo(video);
       }
       for(Shapes shape: shapeList) {
           addShape(shape);
       }
       
       for(Text text : textList) {
            addText(text);
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
	public void mouseClicked(MouseEvent e) {
		
		//Returns the object that triggered the action listener and casts it to
		//a slideObject
		slideMediaObject eventSource = (slideMediaObject) e.getSource();
		
		//Get the branch value assigned to the object of type slideObject
		int branch = eventSource.getBranch();
		if (branch != 0){
			//branch to slide specified by the object
		}
	}
	
//	/**
//	 * Manage the user interaction with media objects on the slide
//	 * Find where the action came from and call the appropriate method
//	 * 
//	 * For example a particular JButton which has a reference to the next slide
//	 */
//	public void mouseClicked(MouseEvent e){
//		//get the x & y possition where the mouse was clicked.
//		int x_ClickCoord = e.getX();
//		int y_ClickCoord = e.getY();
//		
//		//TODO look to see if there is an object at those coords.
//		//not sure what to replace unknownSlideObject with.
//		int x_ObjectCoord = unknownSlideObject.getX_coord();
//		int y_ObjectCoord = unknownSlideObject.getY_coord();
//		
//		//TODO see if the object has a branch value attached.
//		//getBranch() is not a property of SlideObject() only the objects that extend it.
//		int ObjectBranch = unknownSlideObject.getBranch();
//		
//		//TODO branch based on attached value.
//		
//	}
	
	/**
	 * 
	 * @param shape
	 */
	private void addShape(Shapes shape){
		// Eventually Use the bought-in module to improve this method
		JPanel shapePanel = GraphicsPainter.producePanel(shape.getWidth(), shape.getHeight(), shape.getFillColourObject());
		
        
		slideMediaObject shapeObject = new slideMediaObject(shape.getBranch());
        shapeObject.addMouseListener(shapeObject);
		
        shapeObject.add(shapePanel);
        shapeObject.setBounds(shape.getX_coord(), shape.getY_coord(), shape.getWidth(), shape.getHeight());
        this.add(shapeObject);
	}

	/**
	 * 
	 * @param image
	 */
	private void addImage(Image image){
		// Eventually Use the bought-in module to improve this method
		JLabel imageLabel = ImagePainter.produceImage(image.getFile());
		
        
		slideMediaObject imageObject = new slideMediaObject(image.getBranch());
		imageObject.addMouseListener(imageObject);
		
		imageObject.add(imageLabel);
		imageObject.setBounds(image.getX_coord(), image.getY_coord(), image.getWidth(), image.getHeight());
		this.add(imageObject);
	}
	
	
	
	/**
	 * 
	 * @param video
	 */
	private void addVideo(Video video){
		
		// TODO Replace with the embedded video player when available
		// Start paused by default
		
		JButton videoPanel = VideoPainter.ProduceButton(video.getFile());
		
		videoPanel.setBounds(video.getX_coord(), video.getY_coord(), video.getWidth(), video.getHeight());
        this.add(videoPanel);
	}
	
	
	/**
	 * 
	 * @param sound
	 */
	private void addSound(Sound sound){
		
		// TODO Replace with the embedded slide music player when available
		// Start paused by default
		JPanel audioPanel = audioPlayer.getPanel();
		//setComponentZOrder(audioPanel, 0);
		this.add(audioPanel);
		
		//JButton soundButton = VideoPainter.ProduceButton(sound.getFile());
        
		//soundButton.setLocation(sound.getX_coord(), sound.getY_coord());
		
	}
	
	public void playSounds(){
		ArrayList<Sound> soundList = currentSlide.getSoundList();
		Sound sound = soundList.get(0);
		audioPlayer.prepareMedia(sound.getFile(), sound.getStart());
		audioPlayer.play();
	}
	
	
	/**
	 * 
	 * @param text
	 */
	private void addText(Text text){
		// TODO use .setBounds to define panel size when Text.java has updated
		JPanel textPanel = new Scribe(text);
		
		slideMediaObject textObject = new slideMediaObject(text.getBranch());
		textObject.addMouseListener(textObject);
		
		textObject.add(textPanel);
		textObject.setBounds(text.getX_coord(), text.getY_coord(), text.getXend(), text.getYend());
		this.add(textObject);
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	
	
}
