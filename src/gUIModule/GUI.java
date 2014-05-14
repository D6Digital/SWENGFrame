package gUIModule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

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
	private Presentation slideList;
	private Presentation bigSlideList;
	private Integer currentVisibleSlideID;
	private SlidePanel slidePanel = new SlidePanel();
	private Dimension availableScreenSize;

	JButton nextSlideButton = new JButton();
	JButton previousSlideButton = new JButton();
	int borderSize = 20;
	int utilitiesWidth = 200;
	int contentsWidth = 150;
	UtilitiesPanel utilities = new UtilitiesPanel();
	//ContentsPanel contents = new ContentsPanel(null, null, null);
	JLayeredPane layers = new JLayeredPane();
	boolean utilitiesShowing = false;
	boolean contentsShowing = false;
	JLabel leftBorderLabel = new JLabel();
	JLabel rightBorderLabel = new JLabel();
	JLabel topBorderLabel;

	Insets insets;
	private double scaleFactorY = 1;
	private double scaleFactorX = 1;

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
			XMLParser parser = new XMLParser("src/dynamDom.xml");	
			slideList = parser.getSlides();
			XMLParser parser2 = new XMLParser("src/dynamDom.xml");
			bigSlideList = parser2.getSlides();
			System.out.println("HOOOOOO");
			setLayout(null);

			//set up jframe
			insets = this.getInsets();
			setTitle("Grimoire");
			setSize(slideList.getWidth()+borderSize+borderSize+insets.left+insets.right,
					slideList.getHeight() + borderSize*2+insets.top+insets.bottom);
			setVisible(true);			
			bookPane = getContentPane();




			layers.setLayout(null);

			layers.setBounds(0,0,slideList.getWidth()+borderSize+borderSize+borderSize, slideList.getHeight() + borderSize*2+insets.top+insets.bottom);

			System.out.println("size="+(slideList.getWidth()+borderSize+borderSize)+","+(slideList.getHeight()+borderSize+borderSize));		

			//set up slide
			bookPane.setBounds(borderSize, borderSize, slideList.getWidth()+100, slideList.getHeight()+borderSize+borderSize);

			slidePanel.loadPresentation(slideList);
			slidePanel.setupSlide(slideList.get(0));
			currentVisibleSlideID = 0;
			slidePanel.setBounds(borderSize, borderSize, slideList.getWidth(), slideList.getHeight());
			layers.add(slidePanel,1);	

			//set up utilities
			utilities.setBounds(slideList.getWidth()+borderSize-utilitiesWidth, borderSize, utilitiesWidth, slideList.getHeight());
			utilities.setBackground(Color.BLACK);
			utilities.setVisible(false);
			layers.add(utilities,0);

			//set up contents
			//contents.setBounds(borderSize, borderSize, contentsWidth, slideList.getHeight());
			//contents.setBackground(Color.BLACK);
			//contents.setVisible(false);
			//layers.add(contents,0);

			//set up buttons
			try {
				Image previousSlideImage = ImageIO.read(new File("resources/buttons/PreviousSlide.png"));
				previousSlideButton.setIcon(new ImageIcon(previousSlideImage));
			} catch (IOException ex) {
			}
			previousSlideButton.setBounds(0, slideList.getHeight()+borderSize, (slideList.getWidth()+borderSize+borderSize)/2, borderSize);
			layers.add(previousSlideButton,1);
			try {
				Image nextSlideImage = ImageIO.read(new File("resources/buttons/NextSlide.png"));
				nextSlideButton.setIcon(new ImageIcon(nextSlideImage));
			} catch (IOException ex) {
			}
			nextSlideButton.setBounds((slideList.getWidth()+borderSize+borderSize)/2, slideList.getHeight()+borderSize,(slideList.getWidth()+utilitiesWidth+borderSize+borderSize)/2, borderSize);
			layers.add(nextSlideButton,1);
			previousSlideButton.setBorderPainted(false);				
			//previousSlideButton.setEnabled(false);

			//Drop down

			//borders
			BufferedImage topBorderImage;
			try {
				topBorderImage = ImageIO.read(new File("resources/buttons/Border.png"));
				topBorderLabel = new JLabel(new ImageIcon(topBorderImage));
				topBorderLabel.setBounds(0,0,slideList.getWidth()+borderSize+borderSize,borderSize);
				layers.add(topBorderLabel,1);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			BufferedImage sideBorderImage;
			try {
				sideBorderImage = ImageIO.read(new File("resources/buttons/BorderSide.png"));
				leftBorderLabel = new JLabel(new ImageIcon(sideBorderImage));
				leftBorderLabel.setBounds(0,borderSize,borderSize,slideList.getHeight());
				layers.add(leftBorderLabel,1);
				rightBorderLabel = new JLabel(new ImageIcon(sideBorderImage));
				rightBorderLabel.setBounds(slideList.getWidth()+borderSize,borderSize,borderSize,slideList.getHeight());
				layers.add(rightBorderLabel,1);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}


			bookPane.add(layers);
			bookPane.setVisible(true);
			this.setVisible(true);
			insets = this.getInsets();
			this.setPreferredSize(new Dimension(slideList.getWidth()+borderSize+borderSize+insets.left+insets.right,
					slideList.getHeight() + borderSize*2+insets.top+insets.bottom));
			this.pack();

			System.out.println(availableScreenSize);
			scaleFactorX = (double)(availableScreenSize.width-(borderSize*2))/(double)slideList.getWidth();
			scaleFactorY = (double)(availableScreenSize.height-(borderSize*2)-insets.top)/(double)slideList.getHeight();
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

		rightBorderLabel.addMouseListener(new java.awt.event.MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e){
				utilities.setVisible(true);
				System.out.println("Mouse detected in right border");
				utilitiesShowing = true;
			}


		});

		leftBorderLabel.addMouseListener(new java.awt.event.MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e){
				//contents.setVisible(true);
				System.out.println("Mouse detected in left border");
				contentsShowing = true;
			}


		});
		layers.addMouseMotionListener(new java.awt.event.MouseMotionAdapter(){
			@Override
			public void mouseMoved(MouseEvent e1){
				int xCoordinate = e1.getX();
				int yCoordinate = e1.getY();

				if(utilitiesShowing==true){
					if((xCoordinate>(slideList.getWidth()+borderSize-utilitiesWidth))&(xCoordinate<(slideList.getWidth()+borderSize+borderSize))){
						if((yCoordinate>borderSize)&(yCoordinate<(slideList.getHeight()+borderSize))){

						}else{
							utilities.setVisible(false);
						}
					}else{
						utilities.setVisible(false);
					}
				}else{
					utilitiesShowing = false;
				}

				if (contentsShowing==true){

				}
			}
		});

	}

	@Override
	public void windowStateChanged(WindowEvent e) {
		if(e.getNewState() == Frame.MAXIMIZED_BOTH)
		{
			System.err.println("MAXIMIZED");
			slidePanel.loadPresentation(bigSlideList);
			slidePanel.refreshSlide(bigSlideList.get(slidePanel.currentSlide.getSlideID()));
			slidePanel.setBounds(borderSize, borderSize, bigSlideList.getWidth(), bigSlideList.getHeight());
			layers.setBounds(0,0,bigSlideList.getWidth()+borderSize+borderSize+borderSize, bigSlideList.getHeight() + borderSize*2+insets.top+insets.bottom);
			previousSlideButton.setBounds(0, bigSlideList.getHeight()+borderSize, (bigSlideList.getWidth()+borderSize+borderSize)/2, borderSize);
			previousSlideButton.repaint();
			nextSlideButton.setBounds((bigSlideList.getWidth()+borderSize)/2, bigSlideList.getHeight()+borderSize,(bigSlideList.getWidth()+utilitiesWidth+borderSize+borderSize)/2, borderSize);
			nextSlideButton.repaint();
			utilities.setBounds(bigSlideList.getWidth()+borderSize-utilitiesWidth, borderSize, utilitiesWidth, bigSlideList.getHeight());
			rightBorderLabel.setBounds(bigSlideList.getWidth()+borderSize,borderSize,borderSize,bigSlideList.getHeight());
			leftBorderLabel.setBounds(0,borderSize,borderSize,bigSlideList.getHeight());
			topBorderLabel.setBounds(0,0,bigSlideList.getWidth()+borderSize+borderSize,borderSize);
		}
		else
		{
			if(e.getNewState() == Frame.NORMAL)
			{
				System.err.println("NORMAL");
				slidePanel.loadPresentation(slideList);
				slidePanel.refreshSlide(slideList.get(slidePanel.currentSlide.getSlideID()));
				slidePanel.setBounds(borderSize, borderSize, slideList.getWidth(), slideList.getHeight());
				layers.setBounds(0,0,slideList.getWidth()+borderSize+borderSize+borderSize, slideList.getHeight() + borderSize*2+insets.top+insets.bottom);
				previousSlideButton.setBounds(0, slideList.getHeight()+borderSize, (slideList.getWidth()+borderSize+borderSize)/2, borderSize);
				nextSlideButton.setBounds((slideList.getWidth()+borderSize+borderSize)/2, slideList.getHeight()+borderSize,(slideList.getWidth()+utilitiesWidth+borderSize+borderSize)/2, borderSize);
				utilities.setBounds(slideList.getWidth()+borderSize-utilitiesWidth, borderSize, utilitiesWidth, slideList.getHeight());
				rightBorderLabel.setBounds(slideList.getWidth()+borderSize,borderSize,borderSize,slideList.getHeight());
				leftBorderLabel.setBounds(0,borderSize,borderSize,slideList.getHeight());
				topBorderLabel.setBounds(0,0,slideList.getWidth()+borderSize+borderSize,borderSize);
				
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
				text.setSize((int)((double)text.getSize()*scaleFactorX));
			}
		}
		
		slideList.setWidth((int)((double)this.getWidth()*scaleFactorX));
		slideList.setHeight((int)((double)this.getHeight()*scaleFactorY));
		
		// TODO Auto-generated method stub
		return slideList;
	}

	

	
}
