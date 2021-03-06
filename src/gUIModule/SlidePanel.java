package gUIModule;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import musicPlayerModule.EmbeddedAudioPlayer;
import musicPlayerModule.LockedPlaylistValueAccess;
import presentation.Image;
import presentation.Presentation;
import presentation.Shapes;
import presentation.Slide;
import presentation.Sound;
import presentation.Text;
import presentation.Video;
import presentation.slideMediaObject;
import textModule.Scribe;
import videoModule.VideoPlayer;
import Graphics.graphicsObject;
import Images.ImagePanel;
import Images.TImage;



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
	
	private Timer objectTimer, quickRepaintTimer;
	private Integer count;
	ActionListener repaintTask;

	private MouseAdapter textBranchListener, branchListener;
	
	ArrayList<slideMediaObject> mediaObjects;

	EmbeddedAudioPlayer audioPlayer;
	private String vlcLibraryPath = "resources/lib/vlc-2.1.3";

	JLayeredPane layeredPane;

	private boolean playlistLocked = true;

	private MouseAdapter videoListener;

	private double scalingFactorX = 1;
	private double scalingFactorY = 1;

	

	



	/**
	 * Create a panel ready to have all the necessary slide media added
	 */
	public SlidePanel() {
		super();


		mediaObjects = new ArrayList<slideMediaObject>();

		audioPlayer = new EmbeddedAudioPlayer(vlcLibraryPath);
		// set layout manager to null so media components can be added to their specific co-ordinates
		setLayout(null);

		// By default the panel is invisible until the player chooses to display it
		setVisibility(false);

		clearSlide();
	}

	public SlidePanel(EmbeddedAudioPlayer audioPlayer) {
		super();


		mediaObjects = new ArrayList<slideMediaObject>();

		this.audioPlayer = audioPlayer;
		// set layout manager to null so media components can be added to their specific co-ordinates
		setLayout(null);

		// By default the panel is invisible until the player chooses to display it
		setVisibility(false);

		clearSlide();
	}

	public void loadPresentation(Presentation presentation) {
		this.presentation = presentation;
		this.setBackground(presentation.getBackgroundColourObject());   
	}

	/**
	 * Using the information in Slide, add the media components to the slide panel 
	 * @param newSlide
	 */
	public void setupSlide(Slide newSlide){

		currentSlide = newSlide;
		slideID = currentSlide.getSlideID();
		slideName = currentSlide.getSlideName();

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, (int) (presentation.getWidth()*scalingFactorX), (int) (presentation.getHeight()*scalingFactorY));
		layeredPane.setLayout(null);


		addSound();

		repaintTask= new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getParent().getParent().getParent().getParent().getParent().repaint();
				quickRepaintTimer.stop();
			}
		};
		this.setVisible(true);

		int delay = 1000; // 1000ms or 1 second timer
		setCount(0);
		ActionListener taskPerformer= new ActionListener() {
			int count = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(audioPlayer.isPlaying() && !LockedPlaylistValueAccess.lockedPlaylist) {
					audioPlayer.stopMedia();
				}
				addComponents();
				count ++;
				setCount(count);

				if(count == 1){
					quickRepaintTimer = new Timer(1,repaintTask);
					quickRepaintTimer.start();
				}

				getParent().getParent().getParent().validate();
				getParent().getParent().getParent().repaint();
			}

		};
		objectTimer = new Timer(delay, taskPerformer);
		objectTimer.setInitialDelay(50);
		objectTimer.start();

		add(layeredPane);


	}


	/**
	 * The SlidePanel should be cleared so it becomes an empty SlidePanel
	 * 
	 * After Clearing the panel setupSlide method above should be called to show a new slide
	 */
	public void clearSlide(){
		mediaObjects.clear();
		if(layeredPane != null){
			layeredPane.removeAll();
		}
		this.removeAll();
		setCount(0);


	}

	/**
	 * Refreshes the slide using the information for the new slide
	 * @param newSlide the new slide to be shown
	 */
	public void refreshSlide(Slide newSlide){
		stopPlaying();
		setupSlide(newSlide);

	}


	/**
	 * stop playing video of sound at the correct time
	 */
	public void stopPlaying() {
		this.objectTimer.stop();
		for(Component component: layeredPane.getComponents()){
			if(component instanceof VideoPlayer){
				VideoPlayer videoPlayer = (VideoPlayer) component;
				videoPlayer.stopMedia();
			}
		}
		clearSlide();
		if(audioPlayer != null)
		{
			this.audioPlayer.stopMedia();
		}
	}
	

	/**
	 * @return the count for the addition of objects
	 */
	public Integer getCount() {
		return count;
	}



	/**
	 * @param count the count for the addition of objects
	 */
	public void setCount(Integer count) {
		this.count = count;
	}


	/**
	 * set the visibility of the slide
	 * @param visible
	 */
	public void setVisibility(boolean visible){
		this.setVisible(visible);
	}



	/**
	 * @return true if the slide is visible
	 */
	public boolean getVisibility(){
		return this.isVisible();
	}

	/**
	 * adds shapes to the slide
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

				pointX = (int) (shape.getPoint(i).getX()*scalingFactorX);
				pointY = (int) (shape.getPoint(i).getY()*scalingFactorY);

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

				pointX = (int) ((shape.getPoint(i).getX()*scalingFactorX) - lowX);
				pointY = (int) ((shape.getPoint(i).getY()*scalingFactorY) - lowY);

				graphic.setPoint (i+1, pointX, pointY);
			}
		}
		else{

			graphic.setWidth((int) (shape.getWidth()*scalingFactorX));
			graphic.setHeight((int) (shape.getHeight()*scalingFactorY));
			graphic.setPoint(1, (int) (shape.getWidth()*scalingFactorX/(double)2), (int) (shape.getHeight()*scalingFactorY/(double)2));
			graphic.setIsRegularShape(true);
			lowX = (int) (shape.getPoint(0).getX()*scalingFactorX - (shape.getWidth()*scalingFactorY/(double)2));
			lowY = (int) (shape.getPoint(0).getX()*scalingFactorX - (shape.getHeight()*scalingFactorY/(double)2));
			highX = (int) (lowX + shape.getWidth()*scalingFactorX);
			highY = (int) (lowY + shape.getHeight()*scalingFactorY);
		}

		boundWidth = highX - lowX;
		if (boundWidth == 0) boundWidth = 1;
		boundHeight = highY - lowY;
		if (boundHeight == 0) boundHeight = 1;

		slideMediaObject shapeObject = new slideMediaObject(shape.getBranch(),shape.getDuration(),shape.getStart(),shape.getChapterBranch());
		shapeObject.addMouseListener(branchListener);
		shapeObject.addMouseMotionListener(branchListener);

		graphic.setBounds(0, 0, boundWidth+1, boundHeight+1);
		shapeObject.add(graphic);

		// The x and y of a shape needs to be derived from the leftmost x and highest y co-ordinate in the point array 
		shapeObject.setBounds(lowX, lowY, boundWidth +1, boundHeight +1);

		mediaObjects.add(shapeObject);
		layeredPane.add(shapeObject,shape.getLayer());
	}

	/**
	 * Adds Images to the slide
	 * @param image
	 */
	private void addImage(Image image){
		// Eventually Use the bought-in module to improve this method

		TImage im = new TImage(image.getFile(),0,0);
		im.setWidth((int) (image.getWidth()*scalingFactorX));
		im.setHeight((int) (image.getHeight()*scalingFactorY));

		ImagePanel imagePanel = new ImagePanel(im);
		imagePanel.setOpaque(false);
		imagePanel.setBounds(0,0, (int) (image.getWidth()*scalingFactorX), (int) (image.getHeight()*scalingFactorY));

		slideMediaObject imageObject = new slideMediaObject(image.getBranch(),image.getDuration(),image.getStart(),image.getChapterBranch());
		imageObject.addMouseListener(branchListener);
		imageObject.addMouseMotionListener(branchListener);

		imageObject.add(imagePanel);

		imageObject.setBounds((int) (image.getX_coord()*scalingFactorX), (int) (image.getY_coord()*scalingFactorY), (int) (image.getWidth()*scalingFactorX), (int) (image.getHeight()*scalingFactorY));
		imageObject.setVisible(true);


		mediaObjects.add(imageObject);
		layeredPane.add(imageObject,image.getLayer());
		//this.repaint();
	}



	/**
	 * Adds Videos to the slide
	 * @param video
	 */
	private void addVideo(Video video){
		VideoPlayer videoPlayer;
		if(scalingFactorX!= 1 || scalingFactorY != 1){
			videoPlayer = new VideoPlayer(video,videoListener);
			videoPlayer.resizeVideo(scalingFactorX, scalingFactorY);
		}
		else{
			videoPlayer = new VideoPlayer(video,videoListener);
		}
		layeredPane.add(videoPlayer,video.getLayer());
	}


	/**
	 * The sounds are not added to the slidePanel, instead the audioPanel which allows the sound to play is 
	 * added to the slidePanel, then the sounds are played from the timers method
	 */
	private void addSound(){

		if(LockedPlaylistValueAccess.lockedPlaylist) {		
			// Start paused by default
			JPanel audioPanel = audioPlayer.getPanel();
			this.add(audioPanel);
		}
	}

	/**
	 * This method was used to test the embedded music player and will not be used 
	 * as the functionality will be inside a timers method
	 * @deprecated
	 */
	public void playSounds(){
		if(LockedPlaylistValueAccess.lockedPlaylist) {
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
	 * adds text to slide
	 * @param text
	 */
	private void addText(Text text){
		double textSizeScale;
		if(scalingFactorX > scalingFactorY)
		{
			textSizeScale = scalingFactorY;
		}
		else
		{
			textSizeScale = scalingFactorX;
		}

		JPanel textPanel = new Scribe(text,textBranchListener,textSizeScale);
		textPanel.setBounds(0,0, (int) (text.getXend()*scalingFactorX)-(int) (text.getX_coord()*scalingFactorX), (int) (text.getYend()*scalingFactorY)-(int) (text.getY_coord()*scalingFactorY));


		slideMediaObject textObject = new slideMediaObject(-1,text.getDuration(),text.getStart(),-1);
		textObject.add(textPanel);
		textObject.setText(true);
		textObject.setBounds((int) (text.getX_coord()*scalingFactorX), (int) (text.getY_coord()*scalingFactorY), (int) (text.getXend()*scalingFactorX)-(int) (text.getX_coord()*scalingFactorX), (int) (text.getYend()*scalingFactorY)-(int) (text.getY_coord()*scalingFactorY));
		//this.add(textPanel);
		layeredPane.add(textObject, text.getLayer());

		mediaObjects.add(textObject);

		//this.repaint();
		//getParent().repaint();

	}

	/**
	 * @return the current X scaling factor
	 */
	public double getScalingFactorX() {
		return scalingFactorX;
	}

	/**
	 * @return the current Y scaling factor
	 */
	public double getScalingFactorY() {
		return scalingFactorY;
	}


	/**
	 * set the scaling factor which increases the size of the objects drawn on the panel
	 * @param scalingFactor
	 */
	public void setScalingFactors(double scaleFactorX, double scaleFactorY) {
		this.scalingFactorX = scaleFactorX;
		this.scalingFactorY = scaleFactorY;
	}

	/**
	 * resize the slide panel to match the scaling factors provided.
	 * This is achieved by looping through all objects added to the slidepanel and
	 * changing there sizes or redrawing them altogether
	 */
	public void resizeSlide(){
		objectTimer.stop();
		layeredPane.setBounds(0,0,this.getSize().width,this.getSize().height);
		for(slideMediaObject object: mediaObjects){
			if(object.getStartTime() <=  count){
				layeredPane.remove(object);

			}

		}
		mediaObjects.clear();
		for(Image image: currentSlide.getImageList()) {
			if(image.getStart() <= count)
			{
				if(image.getDuration()>0)
				{
					if((image.getStart() + image.getDuration()) > count){
						addImage(image);
					}
				}
				else
				{
					addImage(image);
				}

			}
		}
		for(Shapes shape: currentSlide.getShapeList()) {
			if(shape.getStart() <= count)
			{
				if(shape.getDuration()>0)
				{
					if((shape.getStart() + shape.getDuration()) > count){
						addShape(shape);
					}
				}
				else
				{
					addShape(shape);
				}
			}
		}
		for(Text text : currentSlide.getTextList()) {
			if(text.getStart() <= count)
			{
				if(text.getDuration()>0)
				{
					if((text.getStart() + text.getDuration()) > count){
						addText(text);
					}
				}
				else
				{
					addText(text);
				}
			}
		}
		for(Component component: layeredPane.getComponents()){
			if(component instanceof VideoPlayer){
				VideoPlayer videoPlayer = (VideoPlayer) component;
				videoPlayer.resizeVideo(scalingFactorX,scalingFactorY);
			}
		}

		this.getParent().getParent().repaint();
		objectTimer.start();

	}

	/**
	 * Set the listeners to be used by slidepanel objects to predefined listeners allowing
	 * compatibility with the hierarchy of classes
	 * @param textListener
	 * @param objectListener
	 * @param videoListener 
	 */
	public void setupListeners(MouseAdapter textListener, MouseAdapter objectListener, MouseAdapter videoListener){
		this.branchListener = objectListener;
		this.textBranchListener = textListener;
		this.videoListener = videoListener;
	}

	public void setTextCursorsBlank() {
		for(slideMediaObject object: mediaObjects){
			if(object.getStartTime() <=  count){
				if(object.isText()){
					JPanel textPanel = (JPanel)object.getComponent(0);
					textPanel.getComponent(0).setCursor(GUI.blankCursor);
				}
			}
		}
	}

	/**
	 * populates the slide based on timers
	 */
	private void addComponents() {
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
			if(sound.getObjectStartTime() == count+1 || (sound.getObjectStartTime() == 0 && count == 0))
			{
				audioPlayer.prepareMediaWithDuration(sound.getFile(), sound.getStart(), sound.getDuration(), sound.getLoop());
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
			}
		}
	}









}
