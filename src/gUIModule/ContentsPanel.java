package gUIModule;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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
	private ArrayList<Presentation> contentsChapterList;
	SlidePanel slidePanel1;
	SlidePanel slidePanel2;
	JButton mainMenuButton = new JButton();
	JButton changeListButton = new JButton();
	JLabel systemLabel = new JLabel();
	JLabel bookLabel = new JLabel();
	JLabel pageLabel = new JLabel();
	JLabel title = new JLabel();
	DefaultListModel<String> listModel = new DefaultListModel<String>();
	JList<String> contentsList = new JList<String>(listModel);
	JLabel background= new JLabel();
	Boolean pageListShowing=true;
	BufferedImage chooseButtonImage;
	JScrollPane contents = new JScrollPane();
	int slideHeight,slideWidth,bookLayout;

	/**
	 * Creates the entire Contents panel to meet the UI design specification
	 * @param bookLayout 
	 * @param slide1
	 * @param slide2
	 */
	public ContentsPanel(ArrayList<Slide> contentSlideList, ArrayList<Presentation> contentChapterList, int width, int slideWidth, int slideHeight, String currentSystem, String currentBook, int bookLayout) {
		super();
		this.setLayout(null);
		this.setVisible(false);
		this.slideHeight=slideHeight;
		this.slideWidth=width;
		this.bookLayout = bookLayout;

		this.contentsSlideList = contentSlideList;
		this.contentsChapterList = contentChapterList;
		//slidePanel1 = slide1;
		//slidePanel2 = slide2;
		mainMenuButton.setBounds((width/2)-55, (int)(slideHeight*0.1), 110, 50);

		changeListButton.setBounds((width/2)-55, (int)(slideHeight*0.21), 110, 50);

		setUpLabels(width, slideHeight, currentSystem, currentBook);

		addLabels();


		final JScrollPane contents = createScrollPane(contentsSlideList);
		contents.setBounds(10,(int)(slideHeight*0.5),width-20,(int)(slideHeight*0.5)-10);
		this.add(contents);

		this.add(background);
		setUpButtonImage(mainMenuButton,"MainMenuButton.png");
		setUpButtonImage(changeListButton,"ChooseChapterButton.png");

		setUpListener(contents);

	}

	/**
	 * sets up the action listeners for the contents panel
	 * @param contents
	 */
	private void setUpListener(final JScrollPane contents) {
		changeListButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(pageListShowing==true){
					setUpButtonImage(changeListButton,"ChangePageButton.png");
					pageListShowing=false;

					listModel.clear();
					contentsList.removeAll();
					for (Presentation currentPresentation : contentsChapterList) {
						listModel.addElement(currentPresentation.getTitle());
						pageLabel.setText("Choose a chapter:");
					}
					contents.setViewportView(contentsList);
				}		
				else{
					setUpButtonImage(changeListButton,"ChooseChapterButton.png");
					pageListShowing=true;

					listModel.clear();
					contentsList.removeAll();

					for (Slide currentSlide : contentsSlideList) {
						listModel.addElement(currentSlide.getSlideID() + ". " + currentSlide.getDescriptor());
						pageLabel.setText("Choose a page:");
					}
					contents.setViewportView(contentsList);
				}

			}
		});
	}

	/**
	 * Adds the labels to the panel
	 */
	private void addLabels() {
		this.add(title);
		this.add(changeListButton);
		this.add(mainMenuButton);
		this.add(pageLabel);
		this.add(systemLabel);
		this.add(bookLabel);
	}

	/**
	 * Sets up all the labels for the contents panel
	 * @param width
	 * @param slideHeight
	 * @param currentSystem
	 * @param currentBook
	 */
	private void setUpLabels(int width, int slideHeight, String currentSystem,
			String currentBook) {
		systemLabel.setBounds((width/2)-70,(int)(slideHeight*0.34),140,40);
		bookLabel.setBounds((width/2)-70,(int)(slideHeight*0.39),140,40);
		pageLabel.setBounds((width/2)-70,(int)(slideHeight*0.44),140,40);
		systemLabel.setText("System: "+currentSystem);
		bookLabel.setText("Book: "+currentBook);
		pageLabel.setText("Choose a page:");
		systemLabel.setFont(new Font("Papyrus", Font.PLAIN, 12));
		bookLabel.setFont(new Font("Papyrus", Font.PLAIN, 12));
		pageLabel.setFont(new Font("Papyrus", Font.PLAIN, 12));
		title.setBounds((width/2)-40, 10, 80,30);
		setUpLabelImage(title, "ContentsLabel.png",80,30);
		background.setBounds(0, 0, width, slideHeight);
		setUpLabelImage(background, "ContentsBackground.png",width,slideHeight);
	}

	/**
	 * @param contentsSlideList
	 */
	public void refreshContents(ArrayList<Slide> contentsSlideList) {
		setUpButtonImage(changeListButton,"ChooseChapterButton.png");
		pageListShowing=true;

		listModel.clear();
		contentsList.removeAll();

		for (Slide currentSlide : contentsSlideList) {
			listModel.addElement(currentSlide.getSlideID() + ". " + currentSlide.getDescriptor());
			pageLabel.setText("Choose a page:");
		}
		contents.setViewportView(contentsList);

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
		contentsList.setFont(new Font("Papyrus", Font.PLAIN, 12));
		listModel.clear();
		contentsList.removeAll();


		//Cycle through all slides in the contents list and creates a JButton for each 
		for (Slide currentSlide : contentsSlideList) {
			listModel.addElement(currentSlide.getSlideID() + ". " + currentSlide.getDescriptor());
		}
		contents.setViewportView(contentsList);


		return contents;	
	}
	/**
	 * Method to set an image as the background to a button, the size is set within
	 * @param button
	 * @param image
	 */
	private void setUpButtonImage(JButton button, String image){
		BufferedImage choosePageButtonImage;
		try{
			if(bookLayout == 1){
				choosePageButtonImage = ImageIO.read(new File("resources/buttons/"+image));
			}
			else{
				choosePageButtonImage = ImageIO.read(new File("resources/buttons" +bookLayout + "/"+image));
			}
			Image scaledButton = choosePageButtonImage.getScaledInstance(110,50,java.awt.Image.SCALE_SMOOTH);
			button.setIcon(new ImageIcon(scaledButton));
		}catch (IOException ex){

		}
	}
	/**
	 * Method to set an image as the background to a label, the size is passed in
	 * @param label
	 * @param image
	 * @param width
	 * @param height
	 */
	private void setUpLabelImage(JLabel label, String image, int width, int height){
		BufferedImage titleImage;
		try{
			if(bookLayout == 1){
				titleImage = ImageIO.read(new File("resources/buttons/"+image));
			}
			else{
				titleImage = ImageIO.read(new File("resources/buttons" +bookLayout + "/"+image));
			}
			Image scaledButton = titleImage.getScaledInstance(width,height,java.awt.Image.SCALE_SMOOTH);
			label.setIcon(new ImageIcon(scaledButton));


		}catch (IOException ex){

		}
	}
	/**
	 * @return the contents list
	 */
	public JList<String> getContentsList() {
		return contentsList;
	}

	/**
	 * @param contentsList the contents list
	 */
	public void setContentsList(JList<String> contentsList) {
		this.contentsList = contentsList;
	}

	/**
	 * @return the main menu button
	 */
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
	}


	/**
	 * @return true if page list is showing
	 */
	public boolean getPageListShowing() {
		return pageListShowing;
	}


	/**
	 * @return change between page and chapter list
	 */
	public JButton getChangeButton() {
		return changeListButton;
	}


	/**
	 * @param slideList the list of slides
	 */
	public void setSlideList(Presentation slideList) {
		this.contentsSlideList=slideList.getSlideList();	
	}


	public void setScrollList(Presentation slideList) {
		this.contentsSlideList=slideList.getSlideList();
	}
	/**
	 * @param slideHeight set the height of the slide
	 */
	public void setDimensions(int slideHeight) {
		this.slideHeight = slideHeight;
		setUpLabelImage(background, "ContentsBackground.png",slideWidth,slideHeight);
		background.setBounds(0, 0, slideWidth, slideHeight);
		contents.setBounds(10,280,slideWidth-20,slideHeight-290);
	}
}
