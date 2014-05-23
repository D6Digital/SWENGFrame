package gUIModule;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import presentation.Slide;

public class MainMenuPanel extends JPanel{
	
	JButton button = new JButton("Start");
	JLabel background;
	JLabel logoLabel;
	JLayeredPane layers = new JLayeredPane();
	JTextField description = new JTextField();
	DefaultListModel systemListModel = new DefaultListModel<String>();
	JList systemList = new JList(systemListModel);
	JScrollPane systemScroll = new JScrollPane();
	DefaultListModel bookListModel = new DefaultListModel<String>();
	JList bookList = new JList(bookListModel);
	JScrollPane bookScroll = new JScrollPane();

	
	public MainMenuPanel(int width, int height) {
		
		setLayout(null);
		layers.setLayout(null);
		layers.setBounds(0, 0, width, height);
		System.out.println("height="+height + "width="+width);
		
		//start button
		button.setBounds(width-100, height-40, 100, 40);
		
		//background
		BufferedImage backgroundImage;
		try{
			backgroundImage = ImageIO.read(new File("resources/buttons/Background.png"));
			Image scaledBackground = backgroundImage.getScaledInstance(width,height,java.awt.Image.SCALE_SMOOTH);
			background = new JLabel(new ImageIcon(scaledBackground));
			background.setBounds(0, 0, width, height);
		}catch(IOException e2){
			e2.printStackTrace();
		}
		
		//logo
		BufferedImage logoImage;
		try{
			logoImage = ImageIO.read(new File("resources/buttons/sideLogo.png"));
			Image scaledBackground = logoImage.getScaledInstance(150,height-(int)(height*0.1),java.awt.Image.SCALE_SMOOTH);
			logoLabel = new JLabel(new ImageIcon(scaledBackground));
			logoLabel.setBounds(0, (int)(height*0.1), 150,(height-(int)(height*0.1)));
		}catch(IOException e2){
			e2.printStackTrace();
		}
		
		//Descriptions
		description.setBounds(160, 0, width-250, 100);
		description.setText("This is a description!!!!!!!!");
		description.setEditable(false);
		
		//Labels
		JLabel systemLabel = new JLabel("Choose A System:");
		systemLabel.setBounds(160, 140, 120, 50);
		JLabel bookLabel = new JLabel("Choose A Book:");
		bookLabel.setBounds(300, 140, 120, 50);
		
		//scrollpanes
		setUpScrollPanes();
		
		//adding	
		add(bookScroll);
		add(systemScroll);
		add(bookLabel);
		add(systemLabel);
		add(button);
		add(description);
		add(logoLabel);
		add(background);
		//add(layers);
	}

	private void setUpScrollPanes() {
		//scrollpanes

				systemListModel.clear();
				systemList.removeAll();

				
//				//Cycle through all slides in the contents list and creates a JButton for each 
//				for (Slide currentSlide : contentsSlideList) {
//					listModel.addElement(currentSlide.getSlideID() + ". " + currentSlide.getDescriptor());
//				}
				systemListModel.add(0, new String("hi there"));
				systemListModel.add(1, new String("bye there"));
				systemListModel.add(2, new String("hello there"));
				systemListModel.add(3, new String("goodbye there"));
				
				systemScroll.setViewportView(systemList);
				systemScroll.setBounds(160, 200, 100, 300);
				
				bookListModel.clear();
				bookList.removeAll();

				
//				//Cycle through all slides in the contents list and creates a JButton for each 
//				for (Slide currentSlide : contentsSlideList) {
//					listModel.addElement(currentSlide.getSlideID() + ". " + currentSlide.getDescriptor());
//				}
				bookListModel.add(0, new String("hi there"));
				bookListModel.add(1, new String("bye there"));
				bookListModel.add(2, new String("hello there"));
				bookListModel.add(3, new String("goodbye there"));
				
				bookScroll.setViewportView(bookList);
				bookScroll.setBounds(300, 200, 100, 300);
				
		
	}

	public JButton getButton(){
		return button;
	}
	
}
