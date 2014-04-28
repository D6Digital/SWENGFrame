package gUIModule;


import graphicsModule.GraphicsPainter;
import imageModule.ImagePainter;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


import Graphics.graphicsObject;

import Images.ImagePanel;
import Images.TImage;

import musicPlayerModule.EmbeddedAudioPlayer;
import presentation.Image;

import presentation.Point;

import presentation.Presentation;

import presentation.Shapes;
import presentation.Slide;
import presentation.Sound;
import presentation.Text;
import presentation.Video;
import presentation.slideMediaObject;
import textModule.Scribe;
import videoModule.VideoPainter;



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
	Presentation presentation;
	Timer theTimer;
	
	EmbeddedAudioPlayer audioPlayer;

	private String vlcLibraryPath = "resources/lib/vlc-2.1.3";
	
	
	
	/**
	 * Create a panel ready to have all the necessary slide media added
	 */
	public SlidePanel() {
		super();
		
		
		audioPlayer = new EmbeddedAudioPlayer(vlcLibraryPath );
		// set layout manager to null so media components can be added to their specific co-ordinates
		this.setLayout(null);
		
		// By default the panel is invisible until the player chooses to display it
		this.setVisibility(false);
		
		// TODO ensure this panel is ready to be displayed when necessary
	}
	
	public void loadPresentation(Presentation presentation) {
		this.presentation = presentation;
		this.setBackground(Color.ORANGE);
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
	    
	   
        addSound();
       /*   
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
       }*/
	    
       
       int delay = 1000; // 1000ms or 1 second timer
       ActionListener taskPerformer= new ActionListener() {
		int count = 0;
		@Override
		public void actionPerformed(ActionEvent e) {
			for(Image image: currentSlide.getImageList()) {
				if(image.getStart() == count)
				{
					addImage(image);
				}
		   }
	       for(Video video: currentSlide.getVideoList()) {
	    	   if(video.getStart() == count)
				{
					addVideo(video);
				}
	       }
	       for(Shapes shape: currentSlide.getShapeList()) {
	    	   if(shape.getStart() == count)
				{
					addShape(shape);
				}
	       }
	       for(Text text : currentSlide.getTextList()) {
	    	   if(text.getStart() == count)
				{
					addText(text);
				}
	       }
	       for(Sound sound : currentSlide.getSoundList()) {
	    	   audioPlayer.prepareMedia(sound.getFile(), sound.getStart());
	   		   audioPlayer.playMedia();
	       }
		count ++;
		
		}
       };
       theTimer = new Timer(delay, taskPerformer);
       theTimer.start();
       
       this.setVisible(true);
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
		this.repaint();
		
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
			this.refreshSlide(presentation.getSlideList().get(branch));
			//branch to slide specified by the object
		}
	}
	

	
	/**
	 * 
	 * @param shape
	 */
	private void addShape(Shapes shape){
		int pointX = 0;
		int pointY = 0;
		int highX = 0;
		int lowX = 0; 
		int highY = 0;
		int lowY = 0;
		int boundWidth = 0;
		int boundHeight = 0;
		
		graphicsObject graphic = new graphicsObject(shape.getLineColor(), shape.getFillColor());
		
		graphic.setTotalPoints(shape.getNumberOfPoints());
		
		if(shape.getPointList().size() > 1){			
			for(int i=0; i<(shape.getNumberOfPoints()); i++){
				pointX = shape.getPoint(i).getX();
				pointY = shape.getPoint(i).getY();
				graphic.setPoint (i+1, pointX, pointY);
				if (i==0){
					highX = pointX;
					lowX = pointX;
					highY = pointY;
					lowY = pointY;
				}
				else{
					if (pointX > highX) highX = pointX;
					if (pointX < lowX) lowX = pointX;
					if (pointY > highY) highY = pointY;
					if (pointY < lowY) lowY = pointY;
				}
			}
		}
		else{
			graphic.setWidth(shape.getWidth());
			graphic.setHeight(shape.getHeight());
			graphic.setPoint(1, shape.getX_coord(), shape.getY_coord());
			graphic.setIsRegularShape(true);
			lowX = shape.getX_coord() - (shape.getWidth()/2);
			lowY = shape.getY_coord() - (shape.getHeight()/2);
			highX = shape.getX_coord() + (shape.getWidth()/2);
			highY = shape.getY_coord() + (shape.getHeight()/2);
		}
		
		boundWidth = highX - lowX;
		if (boundWidth == 0) boundWidth = 1;
		boundHeight = highY - lowY;
		if (boundHeight == 0) boundHeight = 1;
				
		slideMediaObject shapeObject = new slideMediaObject(shape.getBranch());
        shapeObject.addMouseListener(shapeObject);
		
        shapeObject.add(graphic);
     
        // The x and y of a shape needs to be derived from the leftmost x and highest y co-ordinate in the point array 
        shapeObject.setBounds(lowX, lowY, boundWidth, boundHeight);
        this.add(shapeObject);
	}

	/**
	 * 
	 * @param image
	 */
	private void addImage(Image image){
		// Eventually Use the bought-in module to improve this method
		
		TImage im = new TImage(image.getFile(),0,0);
		
//		JLabel imageLabel = ImagePainter.produceImage(image.getFile());
		
		ImagePanel imagePanel = new ImagePanel(im);
		//imagePanel.setSize(new Dimension(image.getWidth(), image.getHeight()));
		imagePanel.setBounds(0,0, image.getWidth(), image.getHeight());
		
		slideMediaObject imageObject = new slideMediaObject(image.getBranch());
		imageObject.addMouseListener(this);
		
		imageObject.add(imagePanel);
		//imageObject.setSize(new Dimension(image.getWidth(), image.getHeight()));
		imageObject.setBounds(image.getX_coord(),image.getY_coord(), image.getWidth(), image.getHeight());
		imageObject.setVisible(true);
		
		this.add(imageObject);
		this.repaint();
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
        this.repaint();
	}
	
	
	/**
	 * The sounds are not added to the slidePanel, instead the audioPanel which allows the sound to play is 
	 * added to the slidePanel, then the sounds are played from the timers method
	 */
	private void addSound(){
		
		
		// Start paused by default
		JPanel audioPanel = audioPlayer.getPanel();
		this.add(audioPanel);
		
		//JButton soundButton = VideoPainter.ProduceButton(sound.getFile());
        
		//soundButton.setLocation(sound.getX_coord(), sound.getY_coord());
		
	}
	
	/**
	 * This method was used to test the embedded music player and will not be used 
	 * as the functionality will be inside a timers method
	 */
	public void playSounds(){
		ArrayList<Sound> soundList = currentSlide.getSoundList();
		if(!soundList.isEmpty())
		{
		Sound sound = soundList.get(0);
		audioPlayer.prepareMedia(sound.getFile(), sound.getStart());
		audioPlayer.playMedia();
		}
	}
	
	
	/**
	 * 
	 * @param text
	 */
	private void addText(Text text){
		// TODO use .setBounds to define panel size when Text.java has updated
		JPanel textPanel = new Scribe(text);
		textPanel.setBounds(text.getX_coord(), text.getY_coord(), text.getXend(), text.getYend());
		this.add(textPanel);
		this.repaint();
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
