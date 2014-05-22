package gUIModule;


import graphicsModule.GraphicsPainter;
import imageModule.ImagePainter;


import java.awt.Color;
import java.awt.Cursor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import java.awt.Dimension;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTML;


import Graphics.graphicsObject;

import Images.ImagePanel;
import Images.TImage;

import musicPlayerModule.EmbeddedAudioPlayer;
import musicPlayerModule.LockedPlaylistValueAccess;
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
import videoModule.VideoPlayer;



/**
 * 
 * @author Sam Pick
 * @author Ruba Balanehru 
 * @author Chris Sainsbury
 * @author Andrew Walter
 *
 */
public class SlidePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int slideID;
	String slideName;
	Slide currentSlide;
	Presentation presentation;
	Timer theTimer;
	
	private MouseAdapter textBranchListener;
	private MouseAdapter branchListener;
	ArrayList<slideMediaObject> mediaObjects;
	
	EmbeddedAudioPlayer audioPlayer;
	VideoPlayer videoPlayer;

	private String vlcLibraryPath = "resources/lib/vlc-2.1.3";
	
	JLayeredPane layeredPane;

    private boolean playlistLocked = true;

	
	
	
	
	/**
	 * Create a panel ready to have all the necessary slide media added
	 */
	public SlidePanel() {
		super();
		
		
		
		mediaObjects = new ArrayList<slideMediaObject>();
		
		audioPlayer = new EmbeddedAudioPlayer(vlcLibraryPath );
		// set layout manager to null so media components can be added to their specific co-ordinates
		setLayout(null);
		
		//setupTextListener();
		//setupBranchListener();
		// By default the panel is invisible until the player chooses to display it
		setVisibility(false);
		
		// TODO ensure this panel is ready to be displayed when necessary
	}
	
	

	public void loadPresentation(Presentation presentation) {
		this.presentation = presentation;
		this.setBackground(presentation.getBackgroundColourObject());
		

	    
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
	    
	    layeredPane = new JLayeredPane();
	    //layeredPane.setPreferredSize(new Dimension(presentation.getWidth(),presentation.getHeight()));
	    layeredPane.setBounds(0, 0, presentation.getWidth(), presentation.getHeight());
	    layeredPane.setLayout(null);
	    add(layeredPane);

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
	    
       
       int delay = 100; // 1000ms or 1 second timer
       ActionListener taskPerformer= new ActionListener() {
		int count = 0;
		@Override
		public void actionPerformed(ActionEvent e) {
		    if(audioPlayer.isPlaying() && !LockedPlaylistValueAccess.lockedPlaylist) {
		        audioPlayer.stopMedia();
		    }
			for(Image image: currentSlide.getImageList()) {
				if(image.getStart() == count)
				{
					addImage(image);
					
				}
		   }
	       for(Video video: currentSlide.getVideoList()) {
	    	   if(video.getPlaytime() == count)
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
	    	   if(sound.getObjectStartTime() == count+1 || (sound.getStart() == 0 && count == 0))
	    	   {
	    		   audioPlayer.prepareMedia(sound.getFile(), sound.getStart());
	    	   }
	    	   if(sound.getObjectStartTime() == count)
	    	   {
	    	       if(LockedPlaylistValueAccess.lockedPlaylist) {
	    		   audioPlayer.playMedia();
	    	       }
	    	   }
	    	   if(sound.getDuration()+sound.getObjectStartTime() == count && count != 0)
	    	   {
	    	       if(LockedPlaylistValueAccess.lockedPlaylist) {
	    		   audioPlayer.stopMedia();
	    	       }
	    	   }
	       }
	       for(slideMediaObject object: mediaObjects){
	    	   if(object.getFinishTime() == count && count != 0){
	    		   layeredPane.remove(object);
	    		   getParent().repaint();
	    	   }
	    	   
	       }
		count ++;
		getParent().repaint();
		}
       };
       theTimer = new Timer(delay, taskPerformer);
       theTimer.setInitialDelay(2);
       theTimer.start();
       
       
       this.setVisible(true);
	}
	
	
	/**
	 * The SlidePanel should be cleared so it becomes an empty SlidePanel
	 * 
	 * After Clearing the panel setupSlide method above should be called to show a new slide
	 */
	public void clearSlide(){
		mediaObjects.clear();
		this.removeAll();
	
	
	}
	
	public void refreshSlide(Slide newSlide){
		this.theTimer.stop();
		clearSlide();
		this.setupSlide(newSlide);
		if(audioPlayer != null)
		{
			this.audioPlayer.stopMedia();
		}
		if(videoPlayer != null)
		{
			this.videoPlayer.stopMedia();
		}
		//this.repaint();
		
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
	/*public void mouseClicked(MouseEvent e) {
		
		//Returns the object that triggered the action listener and casts it to
		//a slideObject
		slideMediaObject eventSource = (slideMediaObject) e.getSource();
		if(eventSource != null){
			//Get the branch value assigned to the object of type slideObject
			Integer branch = eventSource.getBranch();
			if (branch != null && branch != -1){
				this.refreshSlide(presentation.getSlideList().get(branch));
				//branch to slide specified by the object
			}
		}
		
	}*/
	

	
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
		
		//System.out.printf("Adding Shape%n");
		
		graphicsObject graphic = new graphicsObject(shape.getLineColor(), shape.getFillColor());
		
		//System.out.printf("Set Graphic%n");
		
		graphic.setTotalPoints(shape.getNumberOfPoints());
		
		if(shape.getPointList().size() > 1){
			//System.out.printf("Point Shape: %d%n", shape.getPointList().size());
			for(int i=0; i<(shape.getNumberOfPoints()); i++){
				
				pointX = shape.getPoint(i).getX();
				pointY = shape.getPoint(i).getY();
				//System.out.printf("Reading Point %d, x %d, y %d%n", i, pointX, pointY);
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
			for(int i=0; i<(shape.getNumberOfPoints()); i++){
				
				pointX = shape.getPoint(i).getX() - lowX;
				pointY = shape.getPoint(i).getY() - lowY;
				//System.out.printf("Setting Point %d, x %d, y %d%n", i, pointX, pointY);
				graphic.setPoint (i+1, pointX, pointY);
			}
		}
		else{
			//System.out.printf("Regular %d Side Shape%n", shape.getNumberOfPoints());
			graphic.setWidth(shape.getWidth());
			graphic.setHeight(shape.getHeight());
			graphic.setPoint(1, shape.getWidth()/2, shape.getHeight()/2);
			graphic.setIsRegularShape(true);
			lowX = shape.getPoint(0).getX() - (shape.getWidth()/2);
			lowY = shape.getPoint(0).getX() - (shape.getHeight()/2);
			highX = lowX + shape.getWidth();
			highY = lowY + shape.getHeight();
		}
				
		//System.out.printf("Fill Colour %s, Line Colour %s%n", shape.getFillColor(), shape.getLineColor());
		
		boundWidth = highX - lowX;
		if (boundWidth == 0) boundWidth = 1;
		boundHeight = highY - lowY;
		if (boundHeight == 0) boundHeight = 1;
		
		//System.out.printf("Shape lowX %d, Shape lowY %d%n", lowX, lowY);
		//System.out.printf("Shape Width %d, Shape Height %d%n", boundWidth, boundHeight);
				
		slideMediaObject shapeObject = new slideMediaObject(shape.getBranch(),shape.getDuration(),shape.getStart());
        shapeObject.addMouseListener(branchListener);
        shapeObject.addMouseMotionListener(branchListener);
        
        graphic.setBounds(0, 0, boundWidth+1, boundHeight+1);
		shapeObject.add(graphic);
        
        //System.out.printf("Added Shape%n");
     
        // The x and y of a shape needs to be derived from the leftmost x and highest y co-ordinate in the point array 
        shapeObject.setBounds(lowX, lowY, boundWidth +1, boundHeight +1);
       
        mediaObjects.add(shapeObject);
        layeredPane.add(shapeObject,shape.getLayer());
        //this.repaint();
        
        //System.out.printf("Added Media Object%n");
	}

	/**
	 * 
	 * @param image
	 */
	private void addImage(Image image){
		// Eventually Use the bought-in module to improve this method
		
		TImage im = new TImage(image.getFile(),0,0);
		im.setWidth(image.getWidth());
		im.setHeight(image.getHeight());
				
		ImagePanel imagePanel = new ImagePanel(im);
		imagePanel.setOpaque(false);
		imagePanel.setBounds(0,0, image.getWidth(), image.getHeight());
		
		slideMediaObject imageObject = new slideMediaObject(image.getBranch(),image.getDuration(),image.getStart());
		imageObject.addMouseListener(branchListener);
		imageObject.addMouseMotionListener(branchListener);
		
		imageObject.add(imagePanel);

		imageObject.setBounds(image.getX_coord(),image.getY_coord(), image.getWidth(), image.getHeight());
		imageObject.setVisible(true);
		
		
		mediaObjects.add(imageObject);
		layeredPane.add(imageObject,image.getLayer());
		//this.repaint();
	}
	
	
	
	/**
	 * 
	 * @param video
	 */
	private void addVideo(Video video){
		
		// TODO Replace with the embedded video player when available
		// Start paused by default
		videoPlayer = new VideoPlayer(video);
        //this.add(videoPlayer);
        layeredPane.add(videoPlayer,video.getLayer());
       // this.repaint();
	}
	
	
	/**
	 * The sounds are not added to the slidePanel, instead the audioPanel which allows the sound to play is 
	 * added to the slidePanel, then the sounds are played from the timers method
	 */
	private void addSound(){
	    
	    if(LockedPlaylistValueAccess.lockedPlaylist) {
            System.out.println(LockedPlaylistValueAccess.lockedPlaylist + " are we locked off?");
		
		// Start paused by default
		JPanel audioPanel = audioPlayer.getPanel();
		this.add(audioPanel);
	    }
		//JButton soundButton = VideoPainter.ProduceButton(sound.getFile());
        
		//soundButton.setLocation(sound.getX_coord(), sound.getY_coord());
		
	}
	
	/**
	 * This method was used to test the embedded music player and will not be used 
	 * as the functionality will be inside a timers method
	 */
	public void playSounds(){
	    if(LockedPlaylistValueAccess.lockedPlaylist) {
	        System.out.println(LockedPlaylistValueAccess.lockedPlaylist + " are we locked off?");
    		ArrayList<Sound> soundList = currentSlide.getSoundList();
    		if(!soundList.isEmpty())
    		{
    		Sound sound = soundList.get(0);
    		audioPlayer.prepareMedia(sound.getFile(), sound.getStart());
    		audioPlayer.playMedia();
    		}
	    }
	}
	
	public void setPlaylistLocked(boolean trueFalse) {
	    this.playlistLocked = trueFalse;
	}

    public boolean getPlaylistLocked() {
        return playlistLocked;
    }
	
	
	/**
	 * 
	 * @param text
	 */
	private void addText(Text text){
		
		JPanel textPanel = new Scribe(text,textBranchListener);
		textPanel.setBounds(0,0, text.getXend()-text.getX_coord(), text.getYend()-text.getY_coord());
		
		
		slideMediaObject textObject = new slideMediaObject(-1,text.getDuration(),text.getStart());
		textObject.add(textPanel);
		textObject.setBounds(text.getX_coord(), text.getY_coord(), text.getXend()-text.getX_coord(), text.getYend()-text.getY_coord());
		//this.add(textPanel);
		layeredPane.add(textObject, text.getLayer());
		
		mediaObjects.add(textObject);
		//this.repaint();
		//getParent().repaint();
		
	}

	/**
	 * Create a mouse listener to branch from sections of text
	 */
	private void setupTextListener() {
		textBranchListener = new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
				JTextPane textPane = (JTextPane) e.getSource();
				if(textPane != null)
				{
					java.awt.Point pt = new java.awt.Point(e.getX(), e.getY());
					int pos = textPane.viewToModel(pt);
				
					
					if (pos >= 0)
					{
						StyledDocument doc = textPane.getStyledDocument();
						if (doc instanceof StyledDocument){
							StyledDocument hdoc = (StyledDocument) doc;
							Element el = hdoc.getCharacterElement(pos);
							AttributeSet a = el.getAttributes();
							String href = (String) a.getAttribute(HTML.Attribute.HREF);
							
							if (href != null){
								try{                            
									java.net.URI uri = new java.net.URI( href );
									desktop.browse( uri );
			                       }
								catch ( Exception ev ){
									System.err.println( ev.getMessage() );
			                       }
								
			                }
							Integer branch = (Integer) a.getAttribute(HTML.Attribute.LINK);
							
							if (branch != null && branch != -1){
								refreshSlide(presentation.getSlideList().get(branch));
							}
						}
					}
				}
			}
			
		};
		
	}
	
	private void setupBranchListener() {
		branchListener = new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e){
						
						//Returns the object that triggered the action listener and casts it to
						//a slideObject
						slideMediaObject eventSource = (slideMediaObject) e.getSource();
						if(eventSource != null){
							//Get the branch value assigned to the object of type slideObject
							Integer branch = eventSource.getBranch();
							if (branch != null && branch != -1){
								refreshSlide(presentation.getSlideList().get(branch));
								//branch to slide specified by the object
							}
						}
						
					}
			@Override
			public void mouseMoved(MouseEvent e) {
				Cursor handCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
				
				JTextPane textPane = (JTextPane) e.getSource();
				java.awt.Point pt = new java.awt.Point(e.getX(), e.getY());
				int pos = textPane.viewToModel(pt);
				
				if (pos >= 0)
				{
					StyledDocument doc = textPane.getStyledDocument();
					
					if (doc instanceof StyledDocument){
						StyledDocument hdoc = (StyledDocument) doc;
						Element el = hdoc.getCharacterElement(pos);
						AttributeSet a = el.getAttributes();
						String href = (String) a.getAttribute(HTML.Attribute.HREF);
						Integer branch = (Integer) a.getAttribute(HTML.Attribute.LINK);
						if (href != null || (branch != null && branch !=-1)){
							if(getCursor() != handCursor){
								textPane.setCursor(handCursor);
							}
						}
						else{
							textPane.setCursor(defaultCursor);
						}
						
		             }           
				}
			}
				
			
		};
	}
	
	/**
	 * Set the listeners to be used by slidepanel objects to predefined listeners allowing
	 * compatibility with the hierarchy of classes
	 * @param textListener
	 * @param objectListener
	 */
	public void setupListeners(MouseAdapter textListener, MouseAdapter objectListener){
		this.branchListener = objectListener;
		this.textBranchListener = textListener;
		
	}

	
	
}
