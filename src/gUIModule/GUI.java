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

import main.Overall;
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
import videoModule.VideoPlayer;
import gUIModule.UtilitiesPanel;
import gUIModule.DicePanel;
import gUIModule.CalculatorPanel;

/**
 * 
 * @author Andrew Walter
 *
 */
public class GUI extends JFrame implements ComponentListener, KeyListener{

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
	static Integer currentVisibleSlideID;
	public SlidePanel slidePanel;

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
	JPanel contentsTab = new JPanel();
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
	static Cursor blankCursor;
	Cursor swordCursor;
	Cursor branchSwordCursor;
	protected Timer cursorTimer;
	private MouseAdapter genericListener, layersMouseListener;
	int newWidth;
	int newHeight;
	private int bookLayout = 1;
	private MouseAdapter mainMenuMouseListener;
	private ActionListener cursorTimerTask;
	private ActionListener resizingTimerTask;
	private ActionListener openBookListener;
	private ActionListener previousSlideListener;
	private ActionListener nextSlideListener;
	
	
	

	

	/**
	 * Create The main JFrame and populate with the Main Menu User interface.
	 * Also setup the listeners and timers to be used by members of this class and
	 * the mouse motion listeners for child components of this class.
	 * @return 
	 */
	public GUI(String panelType) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.black);
		setLayout(null);
		
		setupCursors();
		setupGenericMouseMotionListener();
		
		slideWidth = 720;
		slideHeight = 540;

		//set up the frame and initialise to a default size then use specific frame insets once visible
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
		
		mainMenuPanel = new MainMenuPanel(720, 540, genericListener);
		mainMenuPanel.setBounds(0,0, 720, 540);
		
		setupActionListeners();
		
		JButton buttonFromMainMenu = mainMenuPanel.getButton();
		buttonFromMainMenu.addMouseMotionListener(genericListener);
		buttonFromMainMenu.addActionListener(openBookListener);
		
		addComponentListener(this);
		previousSlideButton.addActionListener(previousSlideListener);
		nextSlideButton.addActionListener(nextSlideListener);
		layers.addMouseMotionListener(layersMouseListener);
		mainMenuPanel.addMouseMotionListener(mainMenuMouseListener);
		
		setupTimers();
		
		layers.setVisible(false);
		add(mainMenuPanel);
		mainMenuShowing=true;
		revalidate();
		repaint();

	}
	
	
	/**
	 * Instatiates the cursor and resizing timers which use methods defined in the
	 * setupActionListeners() method
	 */
	private void setupTimers() {
		int delay = 3000; // delay of 3 seconds after which the cursor changes
		
		cursorTimer = new Timer(delay,cursorTimerTask);
		cursorTimer.setInitialDelay(delay);
		cursorTimer.setRepeats(false);

		resizingTimer = new Timer(delay,resizingTimerTask);
		// 100ms before allowing the resizing
		resizingTimer.setInitialDelay(100);	
		resizingTimer.setRepeats(false);	
		
	}

	/**
	 * sets up all the action listeners used for the main menu GUI
	 */
	private void setupActionListeners() {
		
		
		openBookListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainMenuShowing=false;
				//mainMenuPanel.setVisible(false);
				//mainMenuPanel.setToLoadScreen();
				//mainMenuPanel.setVisible(true);
				
				frame.requestFocusInWindow();
				layers.setVisible(true);
				String chosenBook = mainMenuPanel.getChosenBook();
				if(chosenBook!=null)
				{
					setVisible(false);
					XMLParser parser = new XMLParser(chosenBook);
					collection = parser.getCollection();
					slideList = collection.get(0);
		            System.out.println("book = "+chosenBook);
					bookMainPanelSetUp();
					mainMenuPanel.setVisible(false);
				}
			}
		};
		

		
		
		previousSlideListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.requestFocusInWindow();
				showPreviousSlide();
			}
		};
				
		
		nextSlideListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.requestFocusInWindow();
				showNextSlide();
			}
		};
		
		
		cursorTimerTask =	new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					Toolkit toolkit = Toolkit.getDefaultToolkit();
				  Image image = toolkit.getImage("resources/buttons/blankCursor.png");
				  java.awt.Point point = new java.awt.Point(frame.getX(), frame.getY());
				  layers.setCursor (blankCursor);
				  mainMenuPanel.setCursor(blankCursor);
				 
			}
		};
			
		resizingTimerTask = new ActionListener() {
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
			
		layersMouseListener =	new MouseAdapter(){
			@Override
			public void mouseMoved(MouseEvent e1){
				layers.setCursor(swordCursor);
				borderListenerProcess(e1,false,false,false);
				mouseMovedOnSlide();
				
			}
		};
		
		
		mainMenuMouseListener = new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
			mainMenuPanel.setCursor(swordCursor);
			if(cursorTimer.isRunning()){
				cursorTimer.stop();
			}
			cursorTimer.start();
			}
		};
		
		
		
	}


	/**
	 * Create the custom cursors; blank, sword and branching sword cursors.
	 * set the cursor on the frame and the layers panel to be sword cursor by default.
	 */
	private void setupCursors() {
		
		//set up cursor
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("resources/buttons/blankCursor.png");
		java.awt.Point point = new java.awt.Point(frame.getX(), frame.getY());
		blankCursor = toolkit.createCustomCursor(image , point, "img");
		
		 

		image = toolkit.getImage("resources/buttons/swordCursor.png");
		swordCursor = toolkit.createCustomCursor(image , point, "img");
		image = toolkit.getImage("resources/buttons/branchSwordCursor.png");
		branchSwordCursor = toolkit.createCustomCursor(image , point, "img");
		
		// sword cursor is the default cursor
		layers.setCursor (swordCursor);
		setCursor(swordCursor);
		
	}

	/**
	 * If the mouse moves stop and start the cursorTimer again so the mouse
	 * must not move for the cursorTimer's delay before disappearing
	 */
	private void mouseMovedOnSlide() {
		if(cursorTimer.isRunning()){
			cursorTimer.stop();
		}
		cursorTimer.start();
	}

	/**
	 *This internal class produces a timer which makes the tabs invisible 
	 *after a certain delay
	 */
	public class tabTimer extends TimerTask{
		
		public void run(){
			nextSlideButton.setVisible(false);
			previousSlideButton.setVisible(false);
			utilitiesTab.setVisible(false);
			contentsTab.setVisible(false);
			nextTab.setVisible(false);
			previousTab.setVisible(false);
			tabBool =true;
			this.cancel(); 
		}
	}


	




/**
 * setup the main interface which displays the book as a series of slideshows/chapters
 */
public void bookMainPanelSetUp(){
	
	int width = getSize().width-(insets.left+insets.right);
	int height = getSize().height-(insets.top-insets.bottom);
	scaleFactorX = (double)(getSize().width-(insets.left+insets.right))/(double)720;
	scaleFactorY = (double)(getSize().height-(insets.top-insets.bottom))/(double)540;

	frame.requestFocusInWindow();
	
	bookPane = getContentPane();
	bookPane.setBounds(0, 0, width, height);

	layers.setLayout(null);
	layers.setBounds(0,0,width, height);
	
	//set up listeners for objects on the slide panel
	setupObjectListener();
	setupTextListener();
	setupVideoListener();

	// Testing another book layout
	if(collection.get(0).getTitle().equals("Sunward : The Inner System")){
		bookLayout  = 2;
	}
	else{
		bookLayout = 1;
	}
	
	removePanel(slidePanel);
	removePanel(utilities);
	removePanel(contentsPanel);
	
	slidePanel = new SlidePanel();
	utilities = new UtilitiesPanel(utilitiesWidth, width, height,genericListener,bookLayout);
	contentsPanel = new ContentsPanel(slideList.getSlideList(),collection.getPresentationList(), contentsWidth, slideWidth,540, mainMenuPanel.getCurrentSystem(), mainMenuPanel.getCurrentBook(), bookLayout);
	
	setupTabs(width,height);
	setupSlideButtons(width,height);
	setupUtilities(width,height);
	setupContents(width,height);
	setupSlidePanel(width,height);
	
	
	//Adding to layered pane
	layers.add(utilities,0);
	layers.add(contentsPanel,0);
	layers.add(nextSlideButton,1);
	layers.add(previousSlideButton,1);
	layers.add(slidePanel,5);
	
	bookPane.add(layers);
	bookPane.setVisible(true);
	resizeMainPanel();
	setVisible(true);
	repaint();
		
}


private void setupSlidePanel(int width, int height) {
	
	slidePanel.loadPresentation(slideList);
	slidePanel.setupListeners(textBranchListener, objectBranchListener,videoListener);
	slidePanel.setupSlide(slideList.get(0));
    slidePanel.setScalingFactors(scaleFactorX, scaleFactorY);
    slidePanel.setBounds(0, 0, width, height);	

}


private void setupContents(int width, int height) {
	//set up contents
	contentsPanel.setBounds(0, 0, contentsWidth, height);
	contentsPanel.setPreferredSize(new Dimension(contentsWidth, height));
	contentsPanel.repaint();
    contentsPanel.setVisible(false);
	
	JButton mainMenuButton = contentsPanel.getMainMenuButton();
	mainMenuButton.addMouseMotionListener(genericListener);
	mainMenuButton.addActionListener(
			new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					mainMenuShowing = true;
					slidePanel.stopPlaying();
					utilities.stopPlaying();
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
	contentsPanel.getContentsList().addMouseMotionListener(genericListener);
     contentsPanel.getContentsList().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	frame.requestFocusInWindow();
            	if(contentsPanel.getPageListShowing()==true){
                if(e.getClickCount() == 1) {            
                    slidePanel.refreshSlide(slideList.getSlideList().get(contentsList.getSelectedIndex()));
                    contentsList.clearSelection();  
                }
            	}else{
            		 slideList = collection.get(contentsList.getSelectedIndex());
            		 slidePanel.loadPresentation(slideList);
            		 slidePanel.refreshSlide(slideList.getSlideList().get(0));
            		 
            	}
            }
        });
     JButton changeButton = contentsPanel.getChangeButton();
     changeButton.addMouseMotionListener(genericListener);
     changeButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			frame.requestFocusInWindow();
			contentsPanel.setScrollList(slideList);
		}
	});
	
}


private void setupUtilities(int width, int height) {
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
            utilities.setDimensions(newWidth-utilitiesWidth , newHeight);
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
	
}


private void removePanel(JPanel panel) {
	Boolean AlreadyExists = true;
	try{
		if(panel.getParent() != layers)
		{
			AlreadyExists = false;
		}
	}
	catch(NullPointerException e){
		AlreadyExists = false;
	}
	
	
	if(AlreadyExists)
	{
		layers.remove(panel);
		panel = null;
	}
	
}


/**
 * setup a previous slide and a next slide button which can be used 
 * to branch as the name implies
 * @param width
 * @param height
 */
private void setupSlideButtons(int width,int height) {
	//set up buttons
	//previous button
	BufferedImage previousSlideImage;
	try{
		if(collection.get(0).getTitle().equals("Sunward : The Inner System")){
			previousSlideImage = ImageIO.read(new File("resources/buttons2/Previous.png"));
		}
		else
		{
			previousSlideImage = ImageIO.read(new File("resources/buttons/Previous.png"));
		}
		Image scaledPButton = previousSlideImage.getScaledInstance(150,50,java.awt.Image.SCALE_SMOOTH);
		previousSlideButton.setIcon(new ImageIcon(scaledPButton));
	}catch (IOException ex){
		
	}
	previousSlideButton.setBounds(10,height-60,150,50);
	previousSlideButton.setVisible(false);
	previousSlideButton.addMouseMotionListener(genericListener);
	
	//next button
	BufferedImage nextSlideImage;
	try{
		if(collection.get(0).getTitle().equals("Sunward : The Inner System")){
			nextSlideImage = ImageIO.read(new File("resources/buttons2/Next.png"));
		}
		else
		{
			nextSlideImage = ImageIO.read(new File("resources/buttons/Next.png"));
		}
		Image scaledNButton = nextSlideImage.getScaledInstance(150,50,java.awt.Image.SCALE_SMOOTH);
		nextSlideButton.setIcon(new ImageIcon(scaledNButton));
	}catch (IOException ex){
		
	}
	nextSlideButton.setBounds(width-160,height-60,150,50);
	nextSlideButton.setVisible(false);
	nextSlideButton.addMouseMotionListener(genericListener);
	
}

/**
 * sets up 4 tabs for the user interface; contents,utilities,next and previous
 * which are setup to be visible at the edges of the window
 * @param width
 * @param height
 */
private void setupTabs(int width,int height) {
	//utilities tab
	addTab(utilitiesTab, "resources/buttons/utilitiesTab.png", 15, 110, width, height);
	addTab(contentsTab, "resources/buttons/contentsTab.png", 15, 100, width, height);
	addTab(nextTab, "resources/buttons/nextTab.png", 80, 15, width, height);
	addTab(previousTab, "resources/buttons/previousTab.png", 90, 15, width, height);
}


/**
 * Adds an image label to the tab and sets up the tabs dimensions
 * @param tab
 * @param tabImagePath
 * @param tabWidth
 * @param tabHeight
 * @param width
 * @param height
 */
private void addTab(JPanel tab, String tabImagePath, int tabWidth, int tabHeight, int width, int height) {
	
	tab.setBounds(width-tabWidth,(height/2)-(tabHeight/2),tabWidth,tabHeight);
	BufferedImage utilitiesTabImage;
	try{
		utilitiesTabImage = ImageIO.read(new File(tabImagePath));
		Image scaledUTab = utilitiesTabImage.getScaledInstance(tabWidth, tabHeight, java.awt.Image.SCALE_SMOOTH);
		JLabel uTabLabel = new JLabel(new ImageIcon(scaledUTab));
		uTabLabel.setBounds(0, 0, tabWidth, tabHeight);
		uTabLabel.setOpaque(false);
		tab.add(uTabLabel);
	}catch (IOException ex){
		
	}
	tab.setOpaque(false);
	tab.setVisible(false);

	layers.add(tab,4);
	
}


public Slide showNextSlide() {
	if(slideList.get(slidePanel.currentSlide.getSlideID()).getLastSlide()==false){
		int nextSlideID	 = slidePanel.currentSlide.getSlideID() + 1;
		System.out.println(nextSlideID);
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
							Integer chapterBranch = (Integer) a.getAttribute(HTML.Attribute.TARGET);;
							if (chapterBranch != null && chapterBranch != -1){
								if (chapterBranch > (collection.getPresentationList().size()-1) || chapterBranch < 0)
								{
									System.out.println("chapter branch: " + (chapterBranch+1) + " is out of range for this book");
								}
								else{
									// change chapter and branch
									
									if (branch > (collection.get(chapterBranch).getSlideList().size()-1) || branch < 0)
									{
										System.out.println("page branch: " + (branch+1) + " is out of range for this chapter");
										
									}
									else{
										slideList = collection.get(chapterBranch);
										slidePanel.loadPresentation(slideList);
										slidePanel.refreshSlide(slideList.getSlideList().get(branch));
										contentsPanel.refreshContents(slideList.getSlideList());
										frame.requestFocusInWindow();
									//branch to slide specified by the object
									}
				            		
								}
			            		
							}
							else
							{
								if (branch != null && branch != -1){
									if (branch > (slideList.getSlideList().size()-1) || branch < 0)
									{
										System.out.println("page branch: " + (branch+1) + " is out of range for this chapter");
									}
									else{
									slidePanel.refreshSlide(slideList.get(branch));
									frame.requestFocusInWindow();
									//branch to slide specified by the object
									}
								}
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
								layers.setCursor(branchSwordCursor);
							}
						}
						else{
							layers.setCursor(swordCursor);
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
							Integer chapterBranch = eventSource.getChapterBranch();
							if (chapterBranch != null && chapterBranch != -1){
								if (chapterBranch > (collection.getPresentationList().size()-1) || chapterBranch < 0)
								{
									System.out.println("chapter branch: " + (chapterBranch+1) + " is out of range for this book");
								}
								else{
									// change chapter and branch
									
									if (branch > (collection.get(chapterBranch).getSlideList().size()-1) || branch < 0)
									{
										System.out.println("page branch: " + (branch+1) + " is out of range for this chapter");
										
									}
									else{
										slideList = collection.get(chapterBranch);
										slidePanel.loadPresentation(slideList);
										slidePanel.refreshSlide(slideList.getSlideList().get(branch));
										contentsPanel.refreshContents(slideList.getSlideList());
										frame.requestFocusInWindow();
									//branch to slide specified by the object
									}
				            		
								}
			            		
							}
							else
							{
								if (branch != null && branch != -1){
									if (branch > (slideList.getSlideList().size()-1) || branch < 0)
									{
										System.out.println("page branch: " + (branch+1) + " is out of range for this chapter");
									}
									else{
									slidePanel.refreshSlide(slideList.get(branch));
									frame.requestFocusInWindow();
									//branch to slide specified by the object
									}
								}
							}
						}
					}
					
				}
		@Override
		public void mouseMoved(MouseEvent e) {
			borderListenerProcess(e,true,false,false);
			mouseMovedOnSlide();
			slideMediaObject eventSource = (slideMediaObject) e.getSource();
			if(eventSource != null){
				if(eventSource.getParent().getMousePosition()!=null){
					//Get the branch value assigned to the object of type slideObject
					Integer branch = eventSource.getBranch();
					if (branch != null && branch != -1){
						layers.setCursor(branchSwordCursor);
					}
					else
					{
						layers.setCursor(swordCursor);
					}
				}
			}
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
	    		
	    		videoPlayer.startTimer();
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
	    		layers.setCursor(swordCursor);
	    		borderListenerProcess(e1,false,false,true);
	    		mouseMovedOnSlide();
	    	}
	    	@Override
	    	public void mouseClicked(MouseEvent e1){
	    		frame.requestFocusInWindow();
	    	}
	    	
	    
	};
}

private void setupGenericMouseMotionListener() {
	genericListener = new MouseAdapter(){
		@Override
		public void mouseMoved(MouseEvent e1){
			mainMenuPanel.setCursor(swordCursor);
			layers.setCursor(swordCursor);
			mouseMovedOnSlide();
		}
	};
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
	if((xCoordinate>(slideWidth)/2)&(yCoordinate>(slideHeight-(borderSize*3)))){
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
	if((xCoordinate<(slideWidth)/2)&(yCoordinate>(slideHeight-(borderSize*3)))){
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
	frame.requestFocusInWindow();
	
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
	newWidth = (int) (slideList.getWidth()*scaleFactorX);
	newHeight = (int) (slideList.getHeight()*scaleFactorY);
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
    contentsPanel.setDimensions(newHeight);
    utilitiesTab.setBounds(newWidth-15,(newHeight/2)-60,15,120);
    contentsTab.setBounds(0,(newHeight/2)-60,15,120);
    nextTab.setBounds(newWidth-120,(newHeight)-20,90,20);
    previousTab.setBounds(0,(newHeight)-20,100,20);
    contentsPanel.setBounds(0, 0, contentsWidth, newHeight);
    utilities.dicePanel.setDimensions(newHeight);
    utilities.calculatorPanel.setDimensions(newHeight);
    utilities.standAloneMusicPlayer.setDimension(newHeight);
    
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

public void refreshContents() {
	
	contentsPanel.setVisible(true);
	contentsPanel.repaint();
	contentsShowing=true;
	utilitiesTab.setVisible(false);
	contentsTab.setVisible(false);
	nextTab.setVisible(false);
	previousTab.setVisible(false);
	previousSlideButton.setVisible(false);
}




}
