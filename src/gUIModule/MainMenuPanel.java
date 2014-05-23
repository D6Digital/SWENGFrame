package gUIModule;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
	
	JButton openBookButton = new JButton("Open Book");
	JButton openShopButton = new JButton("Open Shop");
	JLabel background;
	JLabel logoLabel;
	JLabel imageLabel;
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
		openBookButton.setBounds(width-110, 300, 100, 40);
		openShopButton.setBounds(width-110, 360, 100, 40);
		
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
		BufferedImage bookImage;
		try{
			bookImage = ImageIO.read(new File("resources/buttons/eclipsephase.jpg"));
			Image scaledBackground = bookImage.getScaledInstance(150,300,java.awt.Image.SCALE_SMOOTH);
			imageLabel = new JLabel(new ImageIcon(scaledBackground));
			imageLabel.setBounds(440, 200, 150, 300);
		}catch(IOException e2){
			e2.printStackTrace();
		}
		
		
		//scrollpanes
		setUpScrollPanes();
		
		//adding	
		add(imageLabel);
		add(bookScroll);
		add(systemScroll);
		add(bookLabel);
		add(systemLabel);
		add(openShopButton);
		add(openBookButton);
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
				
				openShopButton.addActionListener(
						new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								try{

									String command = "C:\\Program Files\\Internet Explorer\\IEXPLORE.EXE https://www.amazon.co.uk//" ;

									Process link = Runtime.getRuntime().exec(command); 
									}
									catch(Exception ex){
									System.out.println("cannot execute command. " +ex); 
									}
									


							}
						});
		
	}

	public JButton getButton(){
		return openBookButton;
	}
	
	
	
}
