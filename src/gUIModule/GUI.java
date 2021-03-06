package gUIModule;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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

import musicPlayerModule.EmbeddedAudioPlayer;

import presentation.Collection;
import presentation.Presentation;
import presentation.Slide;
import presentation.XMLParser;
import presentation.slideMediaObject;
import videoModule.VideoPlayer;

/**
 * 
 * @author Andrew Walter
 * @author samPick
 * @author joshDrake
 *
 */
public class GUI extends JFrame implements ComponentListener, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame = new JFrame();
	Container selectionPane, menuPane,bookPane,videoPane,audioPane,utilitiesPane,dicePane,calculatorPane,audioPlayerPane;
	private Presentation slideList;
	private SlidePanel slidePanel;
	private Collection collection;
	
	
	int borderSize = 20;
	static int utilitiesWidth = 150;
	static int contentsWidth = 200;
	private int bookLayout = 1;
	private int slideWidth, slideHeight, newWidth, newHeight;
	private double scaleFactorY = 1;
	private double scaleFactorX = 1;
	Insets insets;
	
	
	MainMenuPanel mainMenuPanel;
	UtilitiesPanel utilities;
	ContentsPanel contentsPanel;

	JLayeredPane layers = new JLayeredPane();
	private boolean utilitiesShowing = false;
	private boolean contentsShowing = false;
	private boolean nextButtonShowing = false;
	private boolean previousButtonShowing = false;
	private boolean tabBool = true;
	boolean fullScreen = false;
	private boolean mainMenuShowing=true;
	
	JLabel leftBorderLabel = new JLabel();
	JLabel rightBorderLabel = new JLabel();
	JLabel topBorderLabel;
	
	JButton nextSlideButton = new JButton();
	JButton previousSlideButton = new JButton();

	JPanel contentsTab = new JPanel();
	JPanel utilitiesTab = new JPanel();
	JPanel nextTab = new JPanel();
	JPanel previousTab = new JPanel();
	

	private Timer resizingTimer,cursorTimer;
	Cursor swordCursor,branchSwordCursor;
	static Cursor blankCursor;
	private MouseAdapter genericListener, layersMouseListener, textBranchListener, objectBranchListener,mainMenuMouseListener, videoListener;
	private ActionListener cursorTimerTask, resizingTimerTask, openBookListener, previousSlideListener, nextSlideListener;
	private EmbeddedAudioPlayer audioPlayer;
	private String vlcLibraryPath = "resources/lib/vlc-2.1.3";


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
		
		audioPlayer = new EmbeddedAudioPlayer(vlcLibraryPath);

	}


	/**
	 * Instantiates the cursor and resizing timers which use methods defined in the
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

				frame.requestFocusInWindow();
				layers.setVisible(true);
				String chosenBook = mainMenuPanel.getChosenBook();
				if(chosenBook!=null)
				{
					setVisible(false);
					XMLParser parser = new XMLParser(chosenBook);
					collection = parser.getCollection();
					slideList = collection.get(0);
					//System.out.println("book = "+chosenBook);
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
		
		slidePanel = new SlidePanel(audioPlayer);
		utilities = new UtilitiesPanel(utilitiesWidth, width, height,genericListener,bookLayout);
		contentsPanel = new ContentsPanel(slideList.getSlideList(),collection.getPresentationList(), contentsWidth, slideWidth,540, mainMenuPanel.getCurrentSystem(), mainMenuPanel.getCurrentBook(), bookLayout);

		setupTabs(width,height);
		setupSlideButtons(width,height);
		setupUtilities(width,height);
		setupContents(width,height);
		setupSlidePanel(width,height);


		//Adding to layered pane
		layering();

		bookPane.add(layers);
		bookPane.setVisible(true);
		resizeMainPanel();
		setVisible(true);
		repaint();
		
		

	}


	/**
	 * Adding to layered pane
	 */
	private void layering() {
		layers.add(utilities,0);
		layers.add(contentsPanel,0);
		layers.add(nextSlideButton,1);
		layers.add(previousSlideButton,1);
		layers.add(slidePanel,10);
	}


	private void setupSlidePanel(int width, int height) {

		slidePanel.loadPresentation(slideList);
		slidePanel.setupListeners(textBranchListener, objectBranchListener,videoListener);
		slidePanel.setupSlide(slideList.get(0));
		slidePanel.setScalingFactors(scaleFactorX, scaleFactorY);
		slidePanel.setBounds(0, 0, width, height);	

	}


	/**
	 * set up the contents panel
	 * @param width
	 * @param height
	 */
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
						removeAllComponentsInContainer(slidePanel);
						slidePanel.stopPlaying();
						utilities.stopPlaying();
						scaleFactorX = (double)(getSize().width-insets.left-insets.right)/(double)720;
						scaleFactorY = (double)(getSize().height-insets.top-insets.bottom)/(double)540;
						mainMenuPanel.setBounds(0, 0, getSize().width-insets.left-insets.right, getSize().height-insets.top-insets.bottom);
						mainMenuPanel.resizeMainMenu(scaleFactorX, scaleFactorY);
						layers.setVisible(false);
						mainMenuPanel.setVisible(true);
					}
				});


		final JList<String> contentsList = contentsPanel.getContentsList();
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


	/**
	 * setup the utilities panel
	 * @param width
	 * @param height
	 */
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
				utilities.setDimensions(newWidth-utilitiesWidth , newHeight);
				utilities.validate();
				utilities.repaint();

			}
		});

		addListenersButtons(buttons);

	}


	/**
	 * Add actionListeners to buttons
	 * @param buttons
	 */
	private void addListenersButtons(ArrayList<JButton> buttons) {
		for(final JButton button : buttons) {
			button.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					frame.requestFocusInWindow();
					utilities.setDimensions( (int) (720*scaleFactorX)-150 , (int) (540*scaleFactorY));
					utilities.setUtilityVisible(button);
					utilitiesWidth = utilities.getWidth();
					utilities.validate();
					utilities.repaint();
				}

			});

		}
	}


	/**
	 * removes a panel
	 * @param panel
	 */
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
		layers.add(utilitiesTab,2);

		addTab(contentsTab, "resources/buttons/contentsTab.png", 15, 100, width, height);
		layers.add(contentsTab,3);
		
		addTab(nextTab, "resources/buttons/nextTab.png", 80, 15, width, height);
		layers.add(nextTab,4);
		
		addTab(previousTab, "resources/buttons/previousTab.png", 90, 15, width, height);
		layers.add(previousTab,5);
	}


/**
 * This method checks if the current slide is the last and if not branches to the next slide
 */
public void showNextSlide() {
	if(slideList.get(slidePanel.currentSlide.getSlideID()).getLastSlide()==false){
		int nextSlideID	 = slidePanel.currentSlide.getSlideID() + 1;
		Slide nextSlide = slideList.get(nextSlideID);
		slidePanel.refreshSlide(nextSlide);
		
		previousSlideButton.setBorderPainted(true);
		if(slidePanel.currentSlide.getLastSlide()==true){
			nextSlideButton.setBorderPainted(false);
		}
	}
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
		BufferedImage TabImage;
		try{
			if(tab.getComponentCount() == 0){
				TabImage = ImageIO.read(new File(tabImagePath));
				Image scaledUTab = TabImage.getScaledInstance(tabWidth, tabHeight, java.awt.Image.SCALE_SMOOTH);
				JLabel uTabLabel = new JLabel(new ImageIcon(scaledUTab));
				uTabLabel.setBounds(0, 0, tabWidth, tabHeight);
				uTabLabel.setOpaque(false);
				tab.add(uTabLabel);
			}
		}catch (IOException ex){

		}
		tab.setOpaque(false);
		tab.setVisible(false);

	}




	/**
	 * Creates a listener for text so that when the user clicks on a section of text which has a branch
	 * attribute it will cause an action such as going to a specific slide
	 * 
	 * Also creates a listener for text so that when the users mouse is over text with a branch attribute
	 * the cursor changes to a customised cursor indicating it can be clicked
	 */
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



/**
 * Creates a listener for objects, including images and shapes, so that when the user clicks 
 * on them the slide will be changed to the slide with an id of the branch number
 * 
 * Also creates a listener for objects so that when the users mouse hovers on an object with a branch attribute
 * the cursor changes to a customised cursor indicating it can be clicked
 */
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

/**
 * Creates a listener to be used by the video player which uses the Y-coordinate to
 * decide if the control panel is shown.
 */
private void setupVideoListener() {
	videoListener = new MouseAdapter() {
	    	@Override
	    	public void mouseMoved(MouseEvent e1){
	    		Canvas videoCanvas = (Canvas) e1.getSource();
	    		VideoPlayer videoPlayer = (VideoPlayer) videoCanvas.getParent().getParent();
	    		int yCoordinate = e1.getY();
	    		
	    		videoPlayer.startTimer();
	    		if(!videoPlayer.isPlaying())
	    		{
	    			videoPlayer.ControlPanel.setVisible(true);
	    		}
	    		else
	    		{
	    			if (yCoordinate > ((videoPlayer.overlayPanel.getHeight())- 80)){
		    			   videoPlayer.ControlPanel.setVisible(true);
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

/**
 * This method creates a generic listener used by any visible objects that have mouse listeners
 * so that the custom cursors may be displayed correctly over all objects
 */
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

/**
 * This method is called for all the mouse motion listeners used on the main book panel.
 * When the mouse is in certain areas of the screen(near the edges) different panels are made visible.
 * This shows the contents panel at the left side, utilities at the right side and 
 * previous/next buttons at the bottom.
 * @param e1
 * @param isObject
 * @param isText
 * @param isVideo
 */
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

			if (xCoordinate>(slideWidth-borderSize)){
				setComponentsVisibility(utilities,true);
				utilitiesShowing=true;
				nextSlideButton.setVisible(false);
			}
			if (xCoordinate<(slideWidth-utilitiesWidth)){
				if(utilitiesShowing==true){
					utilities.setVisible(false);
					utilitiesShowing=false;
				}
			}
			if(xCoordinate<borderSize){
				setComponentsVisibility(contentsPanel,true);
				contentsPanel.repaint();
				contentsShowing=true;
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
					setComponentsVisibility(nextSlideButton,true);
					nextButtonShowing=true;
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
					setComponentsVisibility(previousSlideButton,true);
					previousButtonShowing=true;
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

private void setComponentsVisibility(Component component, boolean b) {
	component.setVisible(b);
	utilitiesTab.setVisible(false);
	contentsTab.setVisible(false);
	nextTab.setVisible(false);
	previousTab.setVisible(false);
	
}


/**
 * resizes the main menu using a scale factor derived from the standard resolution of the slides
 */
private void resizeMainMenu() {
	
	scaleFactorX = (double)(getSize().width-insets.left-insets.right)/(double)720;
	scaleFactorY = (double)(getSize().height-insets.top-insets.bottom)/(double)540;
	mainMenuPanel.setBounds(0, 0, getSize().width-insets.left-insets.right, getSize().height-insets.top-insets.bottom);
	mainMenuPanel.resizeMainMenu(scaleFactorX, scaleFactorY);
	
}


/**
 * resizes the main book panel using a scale factor derived from the standard resolution of the slides
 */
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

/**
 * redraws the contents panel and ensures the tabs are not visible
 */
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





/**
 * This method removes all the components a container 
 * @param container
 */
private void removeAllComponentsInContainer(Container container){
	for (Component component : container.getComponents()) {
		if(component instanceof Container){
			Container childContainer = (Container) component;
			removeAllComponents(childContainer);
			//System.out.println("removed all components on:" + childContainer.getClass());
		}
	}
}



/**
 * This method removes all the components from any container
 * @param container
 */
private void removeAllComponents(Container container){
	if(container.getComponentCount() != 0){
		for (Component component : container.getComponents()) {
			if(component instanceof Container){
				Container childContainer = (Container) component;
				if(childContainer.getComponentCount() != 0){
					removeAllComponents(childContainer);
				}
			//System.out.println("removed: " + component.getClass());
			removeAllListenersOnComponent(component);
			((Container) component).removeAll();
			component = null;
			}
		}
	}
	//System.out.println("removed: " + container.getClass());
	container = null;
}

/**
 * This method removes all the listeners from the slide panel to ensure resources are freed
 * @param component
 */
private void removeAllListenersOnComponent(Component component) {
	if(component instanceof slideMediaObject){
		slideMediaObject object = (slideMediaObject) component;
		for (MouseMotionListener listener : object.getMouseMotionListeners()) {
			object.removeMouseMotionListener(listener);
			//System.out.println("removed mouse motion listener from object");
			listener = null;
		}
		for (MouseListener listener : object.getMouseListeners()) {
			object.removeMouseListener(listener);
			//System.out.println("removed mouse listener from object");
			listener = null;
		}
	}
	if(component instanceof JButton){
		JButton button = (JButton) component;
		for (ActionListener listener : button.getActionListeners()) {
			button.removeActionListener(listener);
			//System.out.println("removed action listener from button");
			listener = null;
		}
		for (MouseMotionListener listener : button.getMouseMotionListeners()) {
			button.removeMouseMotionListener(listener);
		}
		for (MouseListener listener : button.getMouseListeners()) {
			button.removeMouseListener(listener);
		}
	}
	if(component instanceof JTextPane){
		JTextPane textPane = (JTextPane) component;
		for (MouseMotionListener listener : textPane.getMouseMotionListeners()) {
			textPane.removeMouseMotionListener(listener);
			//System.out.println("removed mouse motion listener from textPane");
			listener = null;
		}
		for (MouseListener listener : textPane.getMouseListeners()) {
			textPane.removeMouseListener(listener);
			//System.out.println("removed mouse listener from textPane");
			listener = null;
		}
	}
	
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
			if(mainMenuShowing==false){
				showPreviousSlide();
			}
		}
		if(e.getKeyCode()== KeyEvent.VK_RIGHT){
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
