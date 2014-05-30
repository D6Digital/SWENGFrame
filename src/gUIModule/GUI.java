package gUIModule;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTML;

import bookModule.Book;
import bookModule.BookXMLParser;

import musicPlayerModule.StandAloneMusicPlayer;
import presentation.Collection;
import presentation.Point;
import presentation.Presentation;
import presentation.Shapes;
import presentation.Slide;
import presentation.Text;
import presentation.Video;
import presentation.XMLParser;
import presentation.slideMediaObject;
import src.Overall;
import videoModule.VideoPlayer;
import gUIModule.UtilitiesPanel;
import gUIModule.DicePanel;
import gUIModule.CalculatorPanel;

/**
 * 
 * @author Andrew Walter
 *
 */
public class GUI extends JFrame implements WindowStateListener, ComponentListener, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame = new JFrame();
	Container selectionPane;
	Container menuPane;
	Container bookPane;
	Container videoPane;
	Container audioPane;
	Container utilitiesPane;
	Container dicePane;
	Container calculatorPane;
	Container audioPlayerPane;
	private Presentation slideList;
	private Presentation bigSlideList;
	static Integer currentVisibleSlideID;
	public SlidePanel slidePanel;
	private Dimension availableScreenSize;

	JButton nextSlideButton = new JButton();
	JButton previousSlideButton = new JButton();
	int borderSize = 20;
	static int utilitiesWidth = 150;
	static int contentsWidth = 200;
	UtilitiesPanel utilities;
	
	//ContentsPanel contents = new ContentsPanel(null, null, null);
	ContentsPanel contentsPanel;
	//JPanel contents = new JPanel();
	JLayeredPane layers = new JLayeredPane();
	boolean utilitiesShowing = false;
	boolean contentsShowing = false;
	boolean nextButtonShowing = false;
	boolean previousButtonShowing = false;
	boolean tabBool = true;
	boolean fullScreen = false;
	JLabel leftBorderLabel = new JLabel();
	JLabel rightBorderLabel = new JLabel();
	JLabel topBorderLabel;
	int slideWidth;
	int slideHeight;


	Insets insets;
	private double scaleFactorY = 1;
	private double scaleFactorX = 1;
	
	JButton maximiseRestoreButton = new JButton();
	
	JPanel utilitiesTab = new JPanel();
	JPanel contentsTab;
	JPanel nextTab = new JPanel();
	JPanel previousTab = new JPanel();
	MainMenuPanel mainMenuPanel;

    private boolean screenSizeMaximised = false;

	private MouseAdapter textBranchListener;
	private MouseAdapter objectBranchListener;

	private MouseAdapter videoListener;

	private Collection collection;

	private int chapterID=0;
	private boolean mainMenuShowing=true;
	private Timer resizingTimer;
	Cursor blankCursor;
	Cursor swordCursor;
	Cursor branchSwordCursor;
	protected Timer cursorTimer;
	
	/**
	 * Create a simple JFrame and then populate it with specified JPanel type
	 * @return 
	 */
	private void setCurrentSlideID(Integer newSlideID) {
		this.currentVisibleSlideID = newSlideID;
	}

	private Integer getCurrentSlideID() {
		return currentVisibleSlideID;
	} 

	public Slide showNextSlide() {
		if(slideList.get(slidePanel.currentSlide.getSlideID()).getLastSlide()==false){
			int nextSlideID	 = slidePanel.currentSlide.getSlideID() + 1;
			Slide nextSlide = slideList.get(nextSlideID);
			slidePanel.refreshSlide(nextSlide);
			previousSlideButton.setBorderPainted(true);
			System.out.println("Next slide = " + nextSlide.getLastSlide());
			if(slidePanel.currentSlide.getLastSlide()==true){
				nextSlideButton.setBorderPainted(false);

			}
		}
		return null;
	}

	public Slide showPreviousSlide() {
		if(slidePanel.currentSlide.getSlideID() > 0){

			nextSlideButton.setBorderPainted(true);

			if (slidePanel.currentSlide.getSlideID() ==1){

				previousSlideButton.setBorderPainted(false);

			}
			int previousSlideID = slidePanel.currentSlide.getSlideID() - 1;
			Slide previousSlide = slideList.get(previousSlideID);
			slidePanel.refreshSlide(previousSlide);
		}	
		return null;
	}

	public GUI(String panelType) {

	    //this.setResizable(false);
		//size of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		//height of the task bar
		
//		XMLParser parser = new XMLParser("github resources/dynamDom.xml");	
//		collection = parser.getCollection();
//		slideList = collection.get(chapterID);
		
		
		//set up cursor
		 Toolkit toolkit = Toolkit.getDefaultToolkit();
		 Image image = toolkit.getImage("resources/buttons/blankCursor.png");
		 java.awt.Point point = new java.awt.Point(frame.getX(), frame.getY());
		 blankCursor = toolkit.createCustomCursor(image , point, "img");
		 frame.setCursor(blankCursor);
		 layers.setCursor (swordCursor);
		 

		  image = toolkit.getImage("resources/buttons/swordCursor.png");
		  swordCursor = toolkit.createCustomCursor(image , point, "img");
		  image = toolkit.getImage("resources/buttons/branchSwordCursor.png");
		  branchSwordCursor = toolkit.createCustomCursor(image , point, "img");

		setLayout(null);
		slideWidth = 720;
		slideHeight = 540;

		//set up jframe and initialise to a default size then use specific frame insets once visible
		setSize(720+8+8,
				540+30+8);
		setVisible(true);	
		insets = this.getInsets();
		setTitle("Grimoire");
		setSize(720+insets.left+insets.right,
				540+insets.top+insets.bottom);
		
		setLayout(null);
		frame=this;
		frame.addKeyListener(this);
		
		mainMenuPanel = new MainMenuPanel(720, 540);
		mainMenuPanel.setBounds(0,0, 720, 540);
		JButton buttonFromMainMenu = mainMenuPanel.getButton();
		layers.setVisible(false);
		add(mainMenuPanel);
		mainMenuShowing=true;
		revalidate();
		repaint();
		buttonFromMainMenu.addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						mainMenuShowing=false;
						frame.requestFocusInWindow();
						layers.setVisible(true);
						String chosenBook = mainMenuPanel.getChosenBook();
						XMLParser parser = new XMLParser(chosenBook);	
						collection = parser.getCollection();
						slideList = collection.get(0);
			            System.out.println("book = "+chosenBook);
			            mainMenuPanel.setVisible(false);
						bookMainPanelSetUp();
					}
				});
		
		

		addWindowStateListener(this);
		addComponentListener(this);

		
		previousSlideButton.addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						frame.requestFocusInWindow();
						showPreviousSlide();
						
						
					}
				});
		
		ActionListener taskPerformer =	new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					Toolkit toolkit = Toolkit.getDefaultToolkit();
				  Image image = toolkit.getImage("resources/buttons/blankCursor.png");
				  java.awt.Point point = new java.awt.Point(frame.getX(), frame.getY());
				  layers.setCursor (blankCursor);
				  mainMenuPanel.setCursor(blankCursor);
			}};
		cursorTimer = new Timer(3000,taskPerformer);
		cursorTimer.setInitialDelay(3000);
		cursorTimer.setRepeats(false);

		layers.addMouseMotionListener(new java.awt.event.MouseAdapter(){
			@Override
			public void mouseMoved(MouseEvent e1){
				borderListenerProcess(e1,false,false,false);
				mouseMovedOnSlide();
				
			}
		});
		
		mainMenuPanel.addMouseMotionListener(new java.awt.event.MouseAdapter() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
			mainMenuPanel.setCursor(swordCursor);
			if(cursorTimer.isRunning()){
				cursorTimer.stop();
			}
			cursorTimer.start();
			}
			
		});
		
		
		int delay = 100; // 100ms before allowing the resizing
		taskPerformer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(slidePanel!=null && !mainMenuShowing){
					resizeMainPanel();
				}
				if(mainMenuShowing){
					resizeMainMenu();
				}
			}
		};
		resizingTimer = new Timer(delay,taskPerformer);
		resizingTimer.setInitialDelay(100);
		resizingTimer.setRepeats(false);

	}
	
	private void mouseMovedOnSlide() {
		layers.setCursor(swordCursor);
		if(cursorTimer.isRunning()){
			cursorTimer.stop();
		}
		cursorTimer.start();
	}

	public class tabTimer extends TimerTask{
		
		public void run(){
			utilitiesTab.setVisible(false);
			contentsTab.setVisible(false);
			nextTab.setVisible(false);
			previousTab.setVisible(false);
			tabBool =true;
			this.cancel(); 
		}
	}


	

	
/*public Presentation reScale(Presentation slideList, double scaleFactorX, double scaleFactorY) {
		
		
		for(Slide slide: slideList.getSlideList()){
			for(presentation.Image image: slide.getImageList()) {
				image.setX_coord((int)((double)image.getX_coord()*scaleFactorX));
				image.setY_coord((int)((double)image.getY_coord()*scaleFactorY));
				image.setWidth((int)((double)image.getWidth()*scaleFactorX));
				image.setHeight((int)((double)image.getHeight()*scaleFactorY));
			}
			for(Video video: slide.getVideoList()) {
				video.setX_coord((int)((double)video.getX_coord()*scaleFactorX));
				video.setY_coord((int)((double)video.getY_coord()*scaleFactorY));
				video.setWidth((int)((double)video.getWidth()*scaleFactorX));
				video.setHeight((int)((double)video.getHeight()*scaleFactorY));
				
			}
			for(Shapes shape: slide.getShapeList()) {
				shape.setWidth((int)((double)shape.getWidth()*scaleFactorX));
				shape.setHeight((int)((double)shape.getHeight()*scaleFactorY));
				for(Point point: shape.getPointList()) {
					point.setX((int)((double)point.getX()*scaleFactorX));
					point.setY((int)((double)point.getY()*scaleFactorY));
				}
			}
			for(Text text : slide.getTextList()) {
				text.setX_coord((int)((double)text.getX_coord()*scaleFactorX));
				text.setY_coord((int)((double)text.getY_coord()*scaleFactorY));
				text.setXend((int)((double)text.getXend()*scaleFactorX));
				text.setYend((int)((double)text.getYend()*scaleFactorY));
				text.setSize(((int)((double)text.getSize()*scaleFactorX)+(int)((double)text.getSize()*scaleFactorX))/2);
			}
		}
		
		slideList.setWidth((int)((double)this.getWidth()*scaleFactorX));
		slideList.setHeight((int)((double)this.getHeight()*scaleFactorY));
		
		// TODO Auto-generated method stub
		return slideList;
	}*/

private void setupTextListener() {
	textBranchListener = new MouseAdapter() {
		

		@Override
		public void mouseClicked(MouseEvent e) {
			java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
			JTextPane textPane = (JTextPane) e.getSource();
			if(textPane.getParent().getParent().getParent().getMousePosition()!=null){
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
								slidePanel.refreshSlide(slideList.get(branch));
							}
						}
					}
				}
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			
			
			Cursor handCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
			Cursor defaultCursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
			
			JTextPane textPane = (JTextPane) e.getSource();
			if(textPane.getParent().getParent().getParent().getMousePosition()!=null){
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
							if(getCursor() != branchSwordCursor){
								textPane.setCursor(branchSwordCursor);
							}
						}
						else{
							textPane.setCursor(swordCursor);
						}
						
		             }           
				}
				borderListenerProcess(e,false,true,false);
				mouseMovedOnSlide();
			}
		}
		
	};
}

private void setupObjectListener() {
	objectBranchListener = new MouseAdapter() {
		
		@Override
		public void mouseClicked(MouseEvent e){
					
					//Returns the object that triggered the action listener and casts it to
					//a slideObject
					slideMediaObject eventSource = (slideMediaObject) e.getSource();
					if(eventSource != null){
						if(eventSource.getParent().getMousePosition()!=null){
							//Get the branch value assigned to the object of type slideObject
							Integer branch = eventSource.getBranch();
							if (branch != null && branch != -1){
								slidePanel.refreshSlide(slideList.get(branch));
								//branch to slide specified by the object
							}
						}
					}
					
				}
		@Override
		public void mouseMoved(MouseEvent e) {
			borderListenerProcess(e,true,false,false);
			mouseMovedOnSlide();
			layers.setCursor(branchSwordCursor);
		}
			
		
	};
}

private void setupVideoListener() {
	videoListener = new MouseAdapter() {
	    	@Override
	    	public void mouseMoved(MouseEvent e1){
	    		Canvas videoCanvas = (Canvas) e1.getSource();
	    		VideoPlayer videoPlayer = (VideoPlayer) videoCanvas.getParent().getParent();
	    		//int xCoordinate = e1.getX();
	    		int yCoordinate = e1.getY();
	    		
	    		if(!videoPlayer.isPlaying())
	    		{
	    			videoPlayer.ControlPanel.setVisible(true);
	    		}
	    		else
	    		{
	    			if (yCoordinate > ((videoPlayer.overlayPanel.getHeight())- 80)){
		    			//if(!ControlPanel.isVisible()) {
		    			    videoPlayer.ControlPanel.setVisible(true);
		    			//}
		    		}
	    			else
	    			{
	    			videoPlayer.ControlPanel.setVisible(false);
	    			}
	    		}
	    		
	    		borderListenerProcess(e1,false,false,true);
	    		mouseMovedOnSlide();
	    	}
	    	
	    
	};
}

public void bookMainPanelSetUp(){
	
		int width = getSize().width-(insets.left+insets.right);
		int height = getSize().height-(insets.top-insets.bottom);
		scaleFactorX = (double)(getSize().width-(insets.left+insets.right))/(double)720;
		scaleFactorY = (double)(getSize().height-(insets.top-insets.bottom))/(double)540;

		this.setSize(new Dimension(getWidth(),
				getHeight()));
		
		bookPane = getContentPane();
		bookPane.setBounds(0, 0, width, height);

		layers.setLayout(null);
		layers.setBounds(0,0,width, height);
		
		//set up listeners for objects on the slide panel
		setupObjectListener();
		setupTextListener();
		setupVideoListener();



		//set up tabs
		//utilities tab
		utilitiesTab.setBounds(width-15,(height/2)-55,15,110);
		BufferedImage utilitiesTabImage;
		try{
			utilitiesTabImage = ImageIO.read(new File("resources/buttons/utilitiesTab.png"));
			Image scaledUTab = utilitiesTabImage.getScaledInstance(15, 110, java.awt.Image.SCALE_SMOOTH);
			JLabel uTabLabel = new JLabel(new ImageIcon(scaledUTab));
			uTabLabel.setBounds(0, 0, 15, 120);
			uTabLabel.setOpaque(false);
			utilitiesTab.removeAll();
			utilitiesTab.add(uTabLabel);
		}catch (IOException ex){
			
		}
		utilitiesTab.setOpaque(false);
		utilitiesTab.setVisible(false);
		
		//contents tab
		if(contentsTab == null)
		{
			contentsTab = new JPanel();
			contentsTab.setBounds(0,(height/2)-60,15,120);
			BufferedImage contentsTabImage;
			try{
				contentsTabImage = ImageIO.read(new File("resources/buttons/contentsTab.png"));
				Image scaledCTab = contentsTabImage.getScaledInstance(15, 100, java.awt.Image.SCALE_SMOOTH);
				JLabel cTabLabel = new JLabel(new ImageIcon(scaledCTab));
				cTabLabel.setBounds(0, 0, 15, 120);
				cTabLabel.setOpaque(false);
				contentsTab.removeAll();
				contentsTab.add(cTabLabel);
			}catch (IOException ex){
				
			}
			contentsTab.setOpaque(false);
			contentsTab.setVisible(false);
		}
		
		//next tab
		nextTab.setBounds(width-90,(height)-20,90,20);
		BufferedImage nextTabImage;
		try{
			nextTabImage = ImageIO.read(new File("resources/buttons/nextTab.png"));
			Image scaledNTab = nextTabImage.getScaledInstance(80, 15, java.awt.Image.SCALE_SMOOTH);
			JLabel nTabLabel = new JLabel(new ImageIcon(scaledNTab));
			nTabLabel.setBounds(0, 0, 80, 15);
			nTabLabel.setOpaque(false);
			nextTab.removeAll();
			nextTab.add(nTabLabel);
		}catch (IOException ex){
			
		}
		nextTab.setOpaque(false);
		nextTab.setVisible(false);
		
		//previous tab
		previousTab.setBounds(0,(height)-20,100,20);
		BufferedImage previousTabImage;
		try{
			previousTabImage = ImageIO.read(new File("resources/buttons/previousTab.png"));
			Image scaledPTab = previousTabImage.getScaledInstance(90, 15, java.awt.Image.SCALE_SMOOTH);
			JLabel pTabLabel = new JLabel(new ImageIcon(scaledPTab));
			pTabLabel.setBounds(0, 0, 90, 15);
			pTabLabel.setOpaque(false);
			previousTab.removeAll();
			previousTab.add(pTabLabel);
		}catch (IOException ex){
			
		}
		previousTab.setOpaque(false);
		previousTab.setVisible(false);
		
		
		//set up buttons
		//previous button
		BufferedImage previousSlideImage;
		try{
			previousSlideImage = ImageIO.read(new File("resources/buttons/Previous.png"));
			Image scaledPButton = previousSlideImage.getScaledInstance(150,50,java.awt.Image.SCALE_SMOOTH);
			previousSlideButton.setIcon(new ImageIcon(scaledPButton));
		}catch (IOException ex){
			
		}
		previousSlideButton.setBounds(10,height-60,150,50);
		previousSlideButton.setVisible(false);
		
		//next button
		BufferedImage nextSlideImage;
		try{
			nextSlideImage = ImageIO.read(new File("resources/buttons/Next.png"));
			Image scaledNButton = nextSlideImage.getScaledInstance(150,50,java.awt.Image.SCALE_SMOOTH);
			nextSlideButton.setIcon(new ImageIcon(scaledNButton));
		}catch (IOException ex){
			
		}
		nextSlideButton.setBounds(width-160,height-60,150,50);
		nextSlideButton.setVisible(false);
		
		
		slidePanel = new SlidePanel();
		
		
		//set up utilities
		
        utilities = new UtilitiesPanel(utilitiesWidth, width, height);
        utilitiesWidth = utilities.getWidth();
		utilities.setLocation(width-utilitiesWidth, 0);
		utilities.setBounds(width-utilitiesWidth, 0, utilitiesWidth, height);
		utilities.setBackground(Color.GRAY);
		utilities.setVisible(false);
		
		ArrayList<JButton> buttons = utilities.getButtons();
		final JButton backButton = utilities.getBackButton();
		
		backButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	frame.requestFocusInWindow();
            	utilities.setDimensions( (int) (720*scaleFactorX)-150 , (int) (540*scaleFactorY));
                utilities.setUtilityVisible(backButton);
                utilitiesWidth = utilities.getWidth();
                //utilities.setBounds(720-utilitiesWidth, 0, utilitiesWidth, 540);
                //utilitiesWidth = 500;
                System.out.println(utilities.getWidth());
                utilities.validate();
                utilities.repaint();
                
            }
        });
		
		for(final JButton button : buttons) {
		    button.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                	frame.requestFocusInWindow();
                	utilities.setDimensions( (int) (720*scaleFactorX)-150 , (int) (540*scaleFactorY));
                    utilities.setUtilityVisible(button);
                    utilitiesWidth = utilities.getWidth();
                    //utilities.setBounds(720-utilitiesWidth, 0, utilitiesWidth, 540);
                    //utilitiesWidth = 500;
                    System.out.println(utilities.getWidth());
                    utilities.validate();
                    utilities.repaint();
                }
		        
		    });
		    
		}
		
		


		// TODO: CHANGES MADE HERE BY JOSHUA LANT, NO ACTUAL TODO, JUST REFERENCE POINT
		//set up contents
		//ArrayList<Book> bookList = mainMenuPanel.getBookList();
		contentsPanel = new ContentsPanel(slideList.getSlideList(),collection.getPresentationList(), contentsWidth, slideWidth,540, mainMenuPanel.getCurrentSystem(), mainMenuPanel.getCurrentBook());
		contentsPanel.setBounds(0, 0, contentsWidth, height);
		
		contentsPanel.setPreferredSize(new Dimension(contentsWidth, height));
		//contents.add(contentsPanel);
		contentsPanel.repaint();
        contentsPanel.setVisible(false);
		//contents.setBounds(0, 0, contentsWidth, 540);
		//contents.setBackground(Color.GRAY);
		//contents.repaint();
		//contents.setVisible(false);
		
		JButton mainMenuButton = contentsPanel.getMainMenuButton();
		mainMenuButton.addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						mainMenuShowing = true;
						scaleFactorX = (double)(getSize().width-insets.left-insets.right)/(double)720;
						scaleFactorY = (double)(getSize().height-insets.top-insets.bottom)/(double)540;
						mainMenuPanel.setBounds(0, 0, getSize().width-insets.left-insets.right, getSize().height-insets.top-insets.bottom);
						mainMenuPanel.resizeMainMenu(scaleFactorX, scaleFactorY);
						System.out.println("Main Menu Pressed");
						layers.setVisible(false);
						mainMenuPanel.setVisible(true);
					}
				});
		
		final JList contentsList = contentsPanel.getContentsList();
		
	     contentsPanel.getContentsList().addMouseListener(new MouseListener() {
	            
	            @Override
	            public void mouseReleased(MouseEvent e) {}
	            
	            @Override
	            public void mousePressed(MouseEvent e) {}
	            
	            @Override
	            public void mouseExited(MouseEvent e) { }
	            
	            @Override
	            public void mouseEntered(MouseEvent e) {}
	            
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	frame.requestFocusInWindow();
	            	if(contentsPanel.getPageListShowing()==true){
	                if(e.getClickCount() == 2) {            
	                    slidePanel.refreshSlide(slideList.getSlideList().get(contentsList.getSelectedIndex()));
	                    contentsList.clearSelection();  
	                }
	            	}else{
	            		 slideList = collection.get(contentsList.getSelectedIndex());
	            		 slidePanel.refreshSlide(slideList.getSlideList().get(0));
	            		 
	            	}
	            }
	        });
	     JButton changeButton = contentsPanel.getChangeButton();
	     changeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.requestFocusInWindow();
				contentsPanel.setScrollList(slideList);
			}
		});
		


		//Adding to layered pane
		layers.add(utilities,0);
		layers.add(contentsPanel,1);
		layers.add(nextSlideButton,2);
		layers.add(previousSlideButton,3);
		layers.add(utilitiesTab,4);
		layers.add(contentsTab,5);
		layers.add(nextTab,6);
		layers.add(previousTab,7);
		layers.add(slidePanel,8);
		
		//set up slide
		
		slidePanel.loadPresentation(slideList);
		slidePanel.setupListeners(textBranchListener, objectBranchListener,videoListener);
		slidePanel.setupSlide(slideList.get(0));
		currentVisibleSlideID = 0;
	    slidePanel.setScalingFactors(scaleFactorX, scaleFactorY);
	    slidePanel.setBounds(0, 0, width, height);	
		

		bookPane.add(layers);
		bookPane.setVisible(true);
		this.repaint();
		this.setVisible(true);
		this.setSize(new Dimension(width+insets.left+insets.right,
				height+ insets.top+insets.bottom));
		


	nextSlideButton.addActionListener(
			new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					frame.requestFocusInWindow();
					showNextSlide();

				}
			});
}
	
private void borderListenerProcess(MouseEvent e1,Boolean isObject,Boolean isText, Boolean isVideo){
	
	int xCoordinate = e1.getX();
	int yCoordinate = e1.getY();
	
	boolean stopListening = false;
	
	if(isObject)
	{
	slideMediaObject eventSource = (slideMediaObject) e1.getSource();
	if(eventSource.getParent().getMousePosition()!=null){
		xCoordinate = eventSource.getParent().getMousePosition().x;
		yCoordinate = eventSource.getParent().getMousePosition().y;
		}
		else
		{
			stopListening = true;
		}
	}
	else{
		if(isText){
		JTextPane textPane = (JTextPane) e1.getSource();
		if(textPane.getParent().getParent().getParent().getMousePosition()!=null){
			xCoordinate = textPane.getParent().getParent().getParent().getMousePosition().x;
			yCoordinate = textPane.getParent().getParent().getParent().getMousePosition().y;
			}
			else
			{
				stopListening = true;
			}
		}
		else{
			if(isVideo){
				Canvas videoCanvas = (Canvas) e1.getSource();
	    		VideoPlayer videoPlayer = (VideoPlayer) videoCanvas.getParent().getParent();
				if(videoPlayer.getParent().getParent().getParent().getMousePosition()!=null){
					xCoordinate = videoPlayer.getParent().getParent().getParent().getMousePosition().x;
					yCoordinate = videoPlayer.getParent().getParent().getParent().getMousePosition().y;
					}
					else
					{
						stopListening = true;
					}
				
	    		
			}
		}
	}
	
	if(!stopListening){
	
	slideWidth = this.getWidth()-insets.left-insets.right;
	slideHeight = this.getHeight()-insets.top-insets.bottom;
	//System.out.println("x="+xCoordinate+"y="+yCoordinate);
	//System.out.println("width="+slideWidth+"height="+slideHeight);
	
	if (xCoordinate>(slideWidth-borderSize)){
		utilities.setVisible(true);
		utilitiesShowing=true;
		utilitiesTab.setVisible(false);
		contentsTab.setVisible(false);
		nextTab.setVisible(false);
		previousTab.setVisible(false);
		nextSlideButton.setVisible(false);
	}
	if (xCoordinate<(slideWidth-utilitiesWidth)){
		if(utilitiesShowing==true){
			utilities.setVisible(false);
			utilitiesShowing=false;
		}
	}
	if(xCoordinate<borderSize){
		contentsPanel.setVisible(true);
		contentsPanel.repaint();
		contentsShowing=true;
		utilitiesTab.setVisible(false);
		contentsTab.setVisible(false);
		nextTab.setVisible(false);
		previousTab.setVisible(false);
		previousSlideButton.setVisible(false);
	}
	if(xCoordinate>contentsWidth){
		if(contentsShowing==true){
			contentsPanel.setVisible(false);
			contentsShowing=false;
		}
	}
	if((xCoordinate>(slideWidth)/2)&(yCoordinate>(slideHeight-borderSize))){
		if(utilitiesShowing==false){
			nextSlideButton.setVisible(true);
			nextButtonShowing=true;
			utilitiesTab.setVisible(false);
			contentsTab.setVisible(false);
			nextTab.setVisible(false);
			previousTab.setVisible(false);
		}
	}
	if((xCoordinate<(slideWidth)/2)|(yCoordinate<(slideHeight-50))){
		if(nextButtonShowing==true){
			nextSlideButton.setVisible(false);
			nextButtonShowing=false;
		}
	}
	if((xCoordinate<(slideWidth)/2)&(yCoordinate>(slideHeight-borderSize))){
		if(contentsShowing==false){
			previousSlideButton.setVisible(true);
			previousButtonShowing=true;
			utilitiesTab.setVisible(false);
			contentsTab.setVisible(false);
			nextTab.setVisible(false);
			previousTab.setVisible(false);
		}
	}
	if((xCoordinate>(slideWidth)/2)|(yCoordinate<(slideHeight-50))){
		if(previousButtonShowing==true){
			previousSlideButton.setVisible(false);
			previousButtonShowing=false;
		}
	}
	if((xCoordinate>50)&(xCoordinate<(slideWidth-50))&(yCoordinate>50)&(yCoordinate<(slideHeight-50))){
		utilitiesTab.setVisible(true);
		contentsTab.setVisible(true);
		if(nextSlideButton.isVisible()==false){
			nextTab.setVisible(true);
		}
		if(previousSlideButton.isVisible()==false){
			previousTab.setVisible(true);
		}
		if(tabBool==true){
		java.util.Timer timer = new java.util.Timer(true);
		tabTimer t = new tabTimer();
		timer.schedule(t,2000);
		tabBool = false;
		}
	}
	
	
	}
	
}

private void resizeMainMenu() {
	
	scaleFactorX = (double)(getSize().width-insets.left-insets.right)/(double)720;
	scaleFactorY = (double)(getSize().height-insets.top-insets.bottom)/(double)540;
	mainMenuPanel.setBounds(0, 0, getSize().width-insets.left-insets.right, getSize().height-insets.top-insets.bottom);
	mainMenuPanel.resizeMainMenu(scaleFactorX, scaleFactorY);
	
}

private void resizeMainPanel() {
	
	scaleFactorX = (double)(getSize().width-insets.left-insets.right)/(double)720;
	scaleFactorY = (double)(getSize().height-insets.top-insets.bottom)/(double)540;
	int newWidth = (int) (slideList.getWidth()*scaleFactorX);
	int newHeight = (int) (slideList.getHeight()*scaleFactorY);
    slidePanel.setScalingFactors(scaleFactorX, scaleFactorY);
    slidePanel.setBounds(0, 0, newWidth, newHeight);
    slidePanel.resizeSlide();
    slidePanel.repaint();
    
    layers.setBounds(0,0,newWidth, newHeight+insets.top+insets.bottom);
    previousSlideButton.setBounds(10,newHeight-60,150,50);
    previousSlideButton.repaint();
    nextSlideButton.setBounds(newWidth-170,newHeight-60,150,50);
    nextSlideButton.repaint();
    utilities.setBounds(newWidth-utilitiesWidth, 0, utilitiesWidth, newHeight);
    utilities.setDimensions( newWidth-utilitiesWidth , newHeight);
    utilitiesTab.setBounds(newWidth-15,(newHeight/2)-60,15,120);
    contentsTab.setBounds(0,(newHeight/2)-60,15,120);
    nextTab.setBounds(newWidth-120,(newHeight)-20,90,20);
    previousTab.setBounds(0,(newHeight)-20,100,20);
    contentsPanel.setBounds(0, 0, contentsWidth, newHeight);
    
}


@Override
public void windowStateChanged(WindowEvent e) {
	/*frame.requestFocusInWindow();
	System.err.println("RESIZED");
	if(slidePanel!=null && !mainMenuShowing){
		resizeMainPanel();
	}
	if(mainMenuShowing){
		resizeMainMenu();
	}*/
}

@Override
public void componentResized(ComponentEvent e) {
	if(resizingTimer.isRunning())
	{
		resizingTimer.stop();
		resizingTimer.start();
	}
	else
	{
		resizingTimer.start();
	}
	
	
}

@Override
public void componentHidden(ComponentEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void componentMoved(ComponentEvent e) {
	// TODO Auto-generated method stub
	
}



@Override
public void componentShown(ComponentEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyPressed(KeyEvent e) {
	if(e.getKeyCode()== KeyEvent.VK_LEFT){
		System.out.println("Left Button Pressed");
		if(mainMenuShowing==false){
		showPreviousSlide();
		}
	}
	if(e.getKeyCode()== KeyEvent.VK_RIGHT){
		System.out.println("Right Button Pressed");	
		if(mainMenuShowing==false){
		showNextSlide();
		}
	}
	
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}




}
