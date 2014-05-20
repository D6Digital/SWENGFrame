package gUIModule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;

import musicPlayerModule.StandAloneMusicPlayer;

import presentation.Point;
import presentation.Presentation;
import presentation.Shapes;
import presentation.Slide;
import presentation.Text;
import presentation.Video;
import presentation.XMLParser;
import src.Overall;
import gUIModule.UtilitiesPanel;
import gUIModule.DicePanel;
import gUIModule.CalculatorPanel;

/**
 * 
 * @author Andrew Walter
 *
 */
public class GUI extends JFrame implements WindowStateListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	public static SlidePanel slidePanel = new SlidePanel();
	private Dimension availableScreenSize;

	JButton nextSlideButton = new JButton();
	JButton previousSlideButton = new JButton();
	int borderSize = 20;
	static int utilitiesWidth = 200;
	int contentsWidth = 150;
	UtilitiesPanel utilities = new UtilitiesPanel();
	JPanel topPanel = new JPanel();
	//ContentsPanel contents = new ContentsPanel(null, null, null);
	JPanel contents = new JPanel();
	JLayeredPane layers = new JLayeredPane();
	boolean utilitiesShowing = false;
	boolean contentsShowing = false;
	boolean topPanelShowing = false;
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
	
	JPanel utilitiesTab = new JPanel();
	JPanel contentsTab = new JPanel();
	JPanel nextTab = new JPanel();
	JPanel previousTab = new JPanel();

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

		//size of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		//height of the task bar
		Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
		int taskBarSize = scnMax.bottom;

		availableScreenSize = new Dimension(screenSize.width,screenSize.height-taskBarSize);

		addWindowStateListener(this);

		switch (panelType) {
		case "bookSelectionPanel":
			setTitle("Grimoire");
			setSize(1000 , 500);
			setVisible(true);

			selectionPane = getContentPane();
			selectionPane.setLayout(new BorderLayout());
			//BookPanel();
			break;
		case "mainMenuPanel":
			setTitle("Grimoire");
			setSize(1000, 500);
			setVisible(true);

			menuPane = getContentPane();
			menuPane.setLayout(new BorderLayout());
			//MenuPanel();
			break;
		case "bookMainPanel":
			//get slides
			XMLParser parser = new XMLParser("github resources/dynamDom.xml");	
			slideList = parser.getSlides();
			XMLParser parser2 = new XMLParser("github resources/dynamDom.xml");
			bigSlideList = parser2.getSlides();
			System.out.println("HOOOOOO");
			setLayout(null);
			slideWidth = slideList.getWidth();
			slideHeight = slideList.getHeight();

			//set up jframe
			insets = this.getInsets();
			setTitle("Grimoire");
			setSize(slideList.getWidth()+insets.left+insets.right,
					slideList.getHeight()+insets.top+insets.bottom);
			setVisible(true);	
			setLayout(null);
			bookPane = getContentPane();
			bookPane.setBounds(0, 0, slideList.getWidth(), slideList.getHeight());

			layers.setLayout(null);
			layers.setBounds(0,0,slideList.getWidth(), slideList.getHeight()+insets.top+insets.bottom);

			//set up slide
			slidePanel.loadPresentation(slideList);
			slidePanel.setupSlide(slideList.get(0));
			currentVisibleSlideID = 0;
			slidePanel.setBounds(0, 0, slideList.getWidth(), slideList.getHeight());	

			//set up tabs
			//utilities tab
			utilitiesTab.setBounds(slideList.getWidth()-15,(slideList.getHeight()/2)-60,15,120);
			BufferedImage utilitiesTabImage;
			try{
				utilitiesTabImage = ImageIO.read(new File("resources/buttons/utilitiesTab.png"));
				Image scaledUTab = utilitiesTabImage.getScaledInstance(15, 100, java.awt.Image.SCALE_SMOOTH);
				JLabel uTabLabel = new JLabel(new ImageIcon(scaledUTab));
				uTabLabel.setBounds(0, 0, 15, 120);
				uTabLabel.setOpaque(false);
				utilitiesTab.add(uTabLabel);
			}catch (IOException ex){
				
			}
			utilitiesTab.setOpaque(false);
			utilitiesTab.setVisible(false);
			
			//contents tab
			contentsTab.setBounds(0,(slideList.getHeight()/2)-60,15,120);
			BufferedImage contentsTabImage;
			try{
				contentsTabImage = ImageIO.read(new File("resources/buttons/contentsTab.png"));
				Image scaledCTab = contentsTabImage.getScaledInstance(15, 100, java.awt.Image.SCALE_SMOOTH);
				JLabel cTabLabel = new JLabel(new ImageIcon(scaledCTab));
				cTabLabel.setBounds(0, 0, 15, 120);
				cTabLabel.setOpaque(false);
				contentsTab.add(cTabLabel);
			}catch (IOException ex){
				
			}
			contentsTab.setOpaque(false);
			contentsTab.setVisible(false);
			
			//next tab
			nextTab.setBounds(slideList.getWidth()-90,(slideList.getHeight())-20,90,20);
			BufferedImage nextTabImage;
			try{
				nextTabImage = ImageIO.read(new File("resources/buttons/nextTab.png"));
				Image scaledNTab = nextTabImage.getScaledInstance(80, 15, java.awt.Image.SCALE_SMOOTH);
				JLabel nTabLabel = new JLabel(new ImageIcon(scaledNTab));
				nTabLabel.setBounds(0, 0, 80, 15);
				nTabLabel.setOpaque(false);
				nextTab.add(nTabLabel);
			}catch (IOException ex){
				
			}
			nextTab.setOpaque(false);
			nextTab.setVisible(false);
			
			//previous tab
			previousTab.setBounds(0,(slideList.getHeight())-20,100,20);
			BufferedImage previousTabImage;
			try{
				previousTabImage = ImageIO.read(new File("resources/buttons/previousTab.png"));
				Image scaledPTab = previousTabImage.getScaledInstance(90, 15, java.awt.Image.SCALE_SMOOTH);
				JLabel pTabLabel = new JLabel(new ImageIcon(scaledPTab));
				pTabLabel.setBounds(0, 0, 90, 15);
				pTabLabel.setOpaque(false);
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
			previousSlideButton.setBounds(10,slideList.getHeight()-60,150,50);
			previousSlideButton.setVisible(false);
			
			//next button
			BufferedImage nextSlideImage;
			try{
				nextSlideImage = ImageIO.read(new File("resources/buttons/Next.png"));
				Image scaledNButton = nextSlideImage.getScaledInstance(150,50,java.awt.Image.SCALE_SMOOTH);
				nextSlideButton.setIcon(new ImageIcon(scaledNButton));
			}catch (IOException ex){
				
			}
			nextSlideButton.setBounds(slideList.getWidth()-160,slideList.getHeight()-60,150,50);
			nextSlideButton.setVisible(false);
			
			//set up utilities
			utilities.setBounds(slideList.getWidth()-utilitiesWidth, 0, utilitiesWidth, slideList.getHeight());
			utilities.setBackground(Color.GRAY);
			utilities.setVisible(false);


			// TODO: CHANGES MADE HERE BY JOSHUA LANT
			//set up contents
			ContentsPanel contentsPanel = new ContentsPanel(slideList.getSlideList());
			contentsPanel.setBounds(30, 30, contentsWidth, slideList.getHeight());
			
			contentsPanel.setPreferredSize(new Dimension(contentsWidth, slideList.getHeight()));
			contents.add(contentsPanel);
			contentsPanel.repaint();
	        contentsPanel.setVisible(true);
			contents.setBounds(0, 0, contentsWidth, slideList.getHeight());
			contents.setBackground(Color.GRAY);
			contents.repaint();
			contents.setVisible(false);
			
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
		                if(e.getClickCount() == 2) {            
		                    slidePanel.refreshSlide(slideList.getSlideList().get(contentsList.getSelectedIndex()));  
		                    contentsList.clearSelection();  
		                }
		            }
		        });

			//Drop down
			topPanel.setBounds((slideList.getWidth()/2)-150, 0, 300, 200);
			topPanel.setVisible(false);
			topPanel.setLayout(null);
			topPanel.setOpaque(false);
			BufferedImage titleImage;
			try{
				titleImage = ImageIO.read(new File("resources/buttons/Logo.png"));
				Image scaledTitle = titleImage.getScaledInstance(300,200,java.awt.Image.SCALE_SMOOTH);
				JLabel titleLabel = new JLabel(new ImageIcon(scaledTitle));
				titleLabel.setBounds(0, 0, 300, 200);
				topPanel.add(titleLabel);
			}catch(IOException e2){
				e2.printStackTrace();
			}

			//Adding to layered pane
			layers.add(utilities,0);
			layers.add(contents,1);
			layers.add(topPanel,2);
			layers.add(nextSlideButton,3);
			layers.add(previousSlideButton,4);
			layers.add(utilitiesTab,5);
			layers.add(contentsTab,6);
			layers.add(nextTab,7);
			layers.add(previousTab,8);
			layers.add(slidePanel,9);
			

			bookPane.add(layers);
			bookPane.setVisible(true);
			this.setVisible(true);
			insets = this.getInsets();
			this.setPreferredSize(new Dimension(slideList.getWidth()+insets.left+insets.right,
					slideList.getHeight() +insets.top+insets.bottom));
			this.pack();

			System.out.println(availableScreenSize);
			scaleFactorX = (double)(availableScreenSize.width)/(double)slideList.getWidth();
			scaleFactorY = (double)(availableScreenSize.height-insets.top)/(double)slideList.getHeight();
			System.out.println("scale X: " + scaleFactorX + "scale Y: " + scaleFactorY);
			
			
			bigSlideList = reScale(bigSlideList,scaleFactorX,scaleFactorY);

			break;
		case "videoDisplayPanel":
			setTitle("Video Guide");
			setSize(1000, 500);
			setVisible(true);

			videoPane = getContentPane();
			videoPane.setLayout(new BorderLayout());
			//VideoPanel();
			break;
		case "audioMenuPanel":
			setTitle("Music");
			setSize(200, 300);
			setVisible(true);

			audioPane = getContentPane();
			audioPane.setLayout(new BorderLayout());
			StandAloneMusicPlayer standAloneMusicPlayer = new StandAloneMusicPlayer();
			audioPane.add(standAloneMusicPlayer.getFullControlPanel(), BorderLayout.CENTER);
			//AudioPanel();
			break;
		case "utilitiesSelectionPanel":
			setTitle("Utilities");
			setSize(200, 225);
			setVisible(true);

			utilitiesPane = getContentPane();
			utilitiesPane.setLayout(new BorderLayout());

			UtilitiesPanel utilitiesSelectionPanel = new UtilitiesPanel();
			utilitiesPane.add(utilitiesSelectionPanel, BorderLayout.CENTER);
			break;
		case "diceRollerPanel":
			setTitle("Dice Roller");
			setSize(405, 640);
			setVisible(true);

			dicePane = getContentPane();
			dicePane.setLayout(new BorderLayout());

			DicePanel diceRollerPanel = new DicePanel();
			dicePane.add(diceRollerPanel, BorderLayout.CENTER);
			break;
		case "calculatorPanel":
			setTitle("Combat Modifier Calculator");
			setSize(400, 600);
			setVisible(true);

			calculatorPane = getContentPane();
			calculatorPane.setLayout(new BorderLayout());

			CalculatorPanel calculatorPanel = new CalculatorPanel();
			calculatorPane.add(calculatorPanel, BorderLayout.CENTER);
			break;
		default:                     
			//???DefaultPanel()???
			break;
		};
		nextSlideButton.addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {

						showNextSlide();

					}
				});

		previousSlideButton.addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {

						showPreviousSlide();
					}
				});

		layers.addMouseMotionListener(new java.awt.event.MouseMotionAdapter(){
			@Override
			public void mouseMoved(MouseEvent e1){
				int xCoordinate = e1.getX();
				int yCoordinate = e1.getY();
				
				System.out.println("x="+xCoordinate+"y="+yCoordinate);
				
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
				if(yCoordinate<borderSize){
					topPanel.setVisible(true);
					topPanelShowing = true;
					utilitiesTab.setVisible(false);
					contentsTab.setVisible(false);
					nextTab.setVisible(false);
					previousTab.setVisible(false);
				}
				if(yCoordinate>100){
					if(topPanelShowing==true){
						topPanel.setVisible(false);
						topPanelShowing=false;
					}
				}
				if(xCoordinate<borderSize){
					contents.setVisible(true);
					contents.repaint();
					contentsShowing=true;
					utilitiesTab.setVisible(false);
					contentsTab.setVisible(false);
					nextTab.setVisible(false);
					previousTab.setVisible(false);
					previousSlideButton.setVisible(false);
				}
				if(xCoordinate>contentsWidth){
					if(contentsShowing==true){
						contents.setVisible(false);
						contentsShowing=false;
					}
				}
				if((xCoordinate>slideWidth/2)&(yCoordinate>(slideHeight-borderSize))){
					if(utilitiesShowing==false){
						nextSlideButton.setVisible(true);
						nextButtonShowing=true;
						utilitiesTab.setVisible(false);
						contentsTab.setVisible(false);
						nextTab.setVisible(false);
						previousTab.setVisible(false);
					}
				}
				if((xCoordinate<slideWidth/2)|(yCoordinate<(slideHeight-100))){
					if(nextButtonShowing==true){
						nextSlideButton.setVisible(false);
						nextButtonShowing=false;
					}
				}
				if((xCoordinate<slideWidth/2)&(yCoordinate>(slideHeight-borderSize))){
					if(contentsShowing==false){
						previousSlideButton.setVisible(true);
						previousButtonShowing=true;
						utilitiesTab.setVisible(false);
						contentsTab.setVisible(false);
						nextTab.setVisible(false);
						previousTab.setVisible(false);
					}
				}
				if((xCoordinate>slideWidth/2)|(yCoordinate<(slideHeight-100))){
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
					Timer timer = new Timer(true);
					tabTimer t = new tabTimer();
					timer.schedule(t,2000);
					tabBool = false;
					}
				}
				
			}
		});

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

	@Override
	public void windowStateChanged(WindowEvent e) {
		if(e.getNewState() == Frame.MAXIMIZED_BOTH)
		{
			System.err.println("MAXIMIZED");
			slidePanel.loadPresentation(bigSlideList);
			slidePanel.refreshSlide(bigSlideList.get(slidePanel.currentSlide.getSlideID()));
			slidePanel.setBounds(0, 0, bigSlideList.getWidth(), bigSlideList.getHeight());
			layers.setBounds(0,0,bigSlideList.getWidth(), bigSlideList.getHeight()+insets.top+insets.bottom);
			previousSlideButton.setBounds(10,bigSlideList.getHeight()-120,150,50);
			previousSlideButton.repaint();
			nextSlideButton.setBounds(bigSlideList.getWidth()-190,bigSlideList.getHeight()-120,150,50);
			nextSlideButton.repaint();
			utilities.setBounds(bigSlideList.getWidth()-utilitiesWidth, 0, utilitiesWidth, bigSlideList.getHeight());
			topPanel.setBounds((bigSlideList.getWidth()/2)-150, 0, 300, 200);
			utilitiesTab.setBounds(bigSlideList.getWidth()-45,(bigSlideList.getHeight()/2)-120,15,120);
			contentsTab.setBounds(0,(bigSlideList.getHeight()/2)-120,15,120);
			nextTab.setBounds(bigSlideList.getWidth()-120,(bigSlideList.getHeight())-80,90,20);
			previousTab.setBounds(0,(bigSlideList.getHeight())-80,100,20);
			contents.setBounds(0, 0, contentsWidth, bigSlideList.getHeight());
			slideWidth = bigSlideList.getWidth()-30;
			slideHeight = bigSlideList.getHeight()-60;
			fullScreen=true;
		}
		else
		{
			if(e.getNewState() == Frame.NORMAL)
			{
				System.err.println("NORMAL");
				slidePanel.loadPresentation(slideList);
				slidePanel.refreshSlide(slideList.get(slidePanel.currentSlide.getSlideID()));
				slidePanel.setBounds(0, 0, slideList.getWidth(), slideList.getHeight());
				layers.setBounds(0,0,slideList.getWidth(), slideList.getHeight()+insets.top+insets.bottom);
				previousSlideButton.setBounds(10,slideList.getHeight()-60,150,50);
				nextSlideButton.setBounds(slideList.getWidth()-160,slideList.getHeight()-60,150,50);
				utilities.setBounds(slideList.getWidth()-utilitiesWidth, 0, utilitiesWidth, slideList.getHeight());
				topPanel.setBounds((slideList.getWidth()/2)-150, 0, 300, 200);
				utilitiesTab.setBounds(slideList.getWidth()-15,(slideList.getHeight()/2)-60,15,120);
				contentsTab.setBounds(0,(slideList.getHeight()/2)-60,15,120);
				nextTab.setBounds(slideList.getWidth()-90,(slideList.getHeight())-20,90,20);
				previousTab.setBounds(0,(slideList.getHeight())-20,100,20);
				contents.setBounds(0, 0, contentsWidth, slideList.getHeight());
				slideWidth = slideList.getWidth();
				slideHeight = slideList.getHeight();
				fullScreen=false;
				
			}
		}
		
	}
	
public Presentation reScale(Presentation slideList, double scaleFactorX, double scaleFactorY) {
		
		
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
	}

	

	
}
