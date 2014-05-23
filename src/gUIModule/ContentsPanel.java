package gUIModule;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import presentation.Presentation;
import presentation.Slide;


/**
 * 
 * @author Sam Pick
 * @author Sam L
 */
public class ContentsPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Slide> contentsSlideList;
	SlidePanel slidePanel1;
	SlidePanel slidePanel2;
	JButton mainMenuButton = new JButton("Main Menu");
	JLabel systemLabel = new JLabel();
	JLabel bookLabel = new JLabel();
	DefaultListModel listModel = new DefaultListModel<String>();
	JList contentsList = new JList(listModel);
	JLabel background;
	
	
	/**
	 * Creates the entire Contents panel to meet the UI design specification
	 * @param slide1
	 * @param slide2
	 */
	public ContentsPanel(ArrayList<Slide> contentSlideList) {
		super();
		this.setLayout(null);
		contentsSlideList = contentSlideList;
		//slidePanel1 = slide1;
		//slidePanel2 = slide2;
		mainMenuButton.setBounds(25, 50, 140, 50);
		systemLabel.setBounds(10,120,130,40);
		bookLabel.setBounds(10,190,130,40);
		systemLabel.setText("System:");
		bookLabel.setText("Book:");
		this.add(mainMenuButton);
		this.add(systemLabel);
		this.add(bookLabel);
		
		JScrollPane contents = createScrollPane(contentsSlideList);
		contents.setBounds(0,260,220,200);
		this.add(contents);
		
		BufferedImage backgroundImage;
		try{
			backgroundImage = ImageIO.read(new File("resources/buttons/Background.png"));
			Image scaledBackground = backgroundImage.getScaledInstance( GUI.contentsWidth, 260,java.awt.Image.SCALE_SMOOTH);
			background = new JLabel(new ImageIcon(scaledBackground));
			background.setBounds(0, 0, GUI.contentsWidth, 260);
			this.add(background);
		}catch(IOException e2){
			e2.printStackTrace();
		}
		BufferedImage mainMenuButtonImage;
		try{
			mainMenuButtonImage = ImageIO.read(new File("resources/buttons/MainMenuButton.png"));
			Image scaledButton = mainMenuButtonImage.getScaledInstance(130,50,java.awt.Image.SCALE_SMOOTH);
			mainMenuButton.setIcon(new ImageIcon(scaledButton));
		}catch (IOException ex){
			
		}
		// TODO add a title JLabel and ensure the panel is ready
	}
	
	
	/**
	 * 
	 * Produces a scrolling pane which contains all the slides that should be in the contents
	 * in the form of JButtons with necessary slide ID or slide name information.
	 * 
	 * All these JButtons should have an action listener.
	 * 
	 * @param contentsSlideList
	 * @return the contents scroll pane
	 */
	private JScrollPane createScrollPane(ArrayList<Slide> contentsSlideList) {
		JScrollPane contents = new JScrollPane();
		
		listModel.clear();
		contentsList.removeAll();

		
		//Cycle through all slides in the contents list and creates a JButton for each 
		for (Slide currentSlide : contentsSlideList) {
			listModel.addElement(currentSlide.getSlideID() + ". " + currentSlide.getDescriptor());
		}
		contents.setViewportView(contentsList);
		
		
		return contents;

//		for (int i = 0; i < contentsSlideList.size(); i++) {
//			final Slide currentSlide = contentsSlideList.get(i);
//			final String currentTitle = (String) contentsSlideList.get(i).getSlideName();
//			JButton currentButton = new JButton(currentTitle);
//			currentButton.setVerticalTextPosition(AbstractButton.CENTER);
//			currentButton.setHorizontalTextPosition(AbstractButton.CENTER);
//			currentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//			currentButton.setToolTipText("Go to " + currentTitle);	
//			
//			//Action listener will display the relevant slide when a button is pressed
//	        currentButton.addActionListener(new ActionListener() {
//	        	 
//	            public void actionPerformed(ActionEvent e)
//	            {
//	                //Execute when button is pressed
//	                System.out.println("You chose " + currentTitle);
//	                SlidePanel newSlide = new SlidePanel();
//	                newSlide.refreshSlide(currentSlide);
//	                
//	            }
//	        });
	        
		//Add to contents JScrollPane	
//			contents.add(currentButton);
//		}
//		
//		return contents;
		
		//Set up background image
				
	}
	
	public JList getContentsList() {
	    return contentsList;
	}
	
	public void setContentsList(JList contentsList) {
	    this.contentsList = contentsList;
	}
	
	public JButton getMainMenuButton(){
		return mainMenuButton;
	}
	/**
	 * Check the which button is released and then change slidePanel
	 * to display the chosen slide from the contents list
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO manage button presses
		
	}

	/**
	 * 
	 * @return the current content list
	 */
	public ArrayList<Slide> getContentsSlideList() {
		return contentsSlideList;
	}

	/**
	 * set the content list to the appropriate slides
	 * @param contentsSlideList
	 */
	public void setContentsSlideList(ArrayList<Slide> contentsSlideList) {
		this.contentsSlideList = contentsSlideList;
	}}
