package gUIModule;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bookModule.Book;
import bookModule.BookList;
import bookModule.BookXMLParser;

import presentation.Presentation;
import presentation.Slide;
import presentation.XMLParser;
import systemModule.GameSystem;
import systemModule.SystemCollection;
import systemModule.SystemXMLParser;

public class MainMenuPanel extends JPanel{
	
	JButton openBookButton = new JButton();
	JButton openShopButton = new JButton();
	JLabel background;
	JLabel logoLabel;
	JLabel systemLabel;
	JLabel bookLabel;
	JLabel imageLabel = new JLabel();
	JLayeredPane layers = new JLayeredPane();
	JTextArea description = new JTextArea();
	JLabel descriptionTitle = new JLabel();
	DefaultListModel systemListModel = new DefaultListModel<String>();
	JList systemList = new JList(systemListModel);
	JScrollPane systemScroll = new JScrollPane();
	DefaultListModel bookListModel = new DefaultListModel<String>();
	JList bookList = new JList(bookListModel);
	JScrollPane bookScroll = new JScrollPane();
	private BookList listOfBooks;
	private SystemCollection listOfSystems;
	private String chosenBook;
	private String systemDescription;
	private String currentSystemName;
	private String currentBookName;
	private ArrayList<Book> bookListForContents;
	
	public MainMenuPanel(int width, int height) {
		
		setLayout(null);
		layers.setLayout(null);
		layers.setBounds(0, 0, width, height);
		System.out.println("height="+height + "width="+width);
		
		SystemXMLParser systemParser = new SystemXMLParser("bin/systemlist.xml");
		listOfSystems = systemParser.getSystem();
		
		//start button
		openBookButton.setBounds(width-120, 250, 110, 50);
		openShopButton.setBounds(width-120, 370, 110, 50);
		
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
			logoImage = ImageIO.read(new File("resources/buttons/LogoWithBackground.png"));
			Image scaledBackground = logoImage.getScaledInstance(100,height,java.awt.Image.SCALE_SMOOTH);
			logoLabel = new JLabel(new ImageIcon(scaledBackground));
			logoLabel.setBounds(0, 0, 100,height);
		}catch(IOException e2){
			e2.printStackTrace();
		}
		
		BufferedImage bookButtonImage;
		try{
			bookButtonImage = ImageIO.read(new File("resources/buttons/OpenBookButton.png"));
			Image scaledButton = bookButtonImage.getScaledInstance(110,50,java.awt.Image.SCALE_SMOOTH);
			openBookButton.setIcon(new ImageIcon(scaledButton));
		}catch (IOException ex){
			
		}
		
		BufferedImage shopButtonImage;
		try{
			shopButtonImage = ImageIO.read(new File("resources/buttons/OpenShopButton.png"));
			Image scaledButton = shopButtonImage.getScaledInstance(110,50,java.awt.Image.SCALE_SMOOTH);
			openShopButton.setIcon(new ImageIcon(scaledButton));
		}catch (IOException ex){
			
		}
		 
//    	BufferedImage bookImage;
// 		try{
// 			bookImage = ImageIO.read(new File("resources/buttons/eclipsephase.jpg"));
// 			Image scaledBackground = bookImage.getScaledInstance(150,300,java.awt.Image.SCALE_SMOOTH);
// 			imageLabel = new JLabel(new ImageIcon(scaledBackground));
// 			imageLabel.setBounds(440, 200, 150, 300);
// 		}catch(IOException e2){
// 			e2.printStackTrace();
// 		}
 		
		//Descriptions
		descriptionTitle.setBounds(130, 10, width-250, 30);
		descriptionTitle.setText("System Description:");
		descriptionTitle.setFont(new Font("Papyrus", Font.BOLD, 18));
		description.setEditable(false);
		description.setBounds(130, 40, width-250, 100);
		description.setFont(new Font("Papyrus", Font.PLAIN, 14));
		description.setText("Welcome to the Grimoire interactive rulebook! Please choose a system and "+ "\n"+ "then a book. We hope you enjoy your journey!"+ "\n"+ ""+ "\n"+ "Much Love, D6 Digital");
		description.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		//Labels
		systemLabel = new JLabel("Choose A "+ "\n"+ "System:");
		systemLabel.setFont(new Font("Papyrus", Font.BOLD, 16));
		systemLabel.setBounds(110, 140, 180, 50);
		bookLabel = new JLabel("Choose A"+ "\n"+ " Book:");
		bookLabel.setFont(new Font("Papyrus", Font.BOLD, 16));
		bookLabel.setBounds(280, 140, 170, 50);
		
		//scrollpanes
		setUpScrollPanes();
		
		imageLabel.setBounds(440, 200, 150, 300);
		imageLabel.setBackground(Color.LIGHT_GRAY);
		imageLabel.setOpaque(true);
		imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		
		//adding	
		add(imageLabel);
		add(bookScroll);
		add(systemScroll);
		add(bookLabel);
		add(systemLabel);
		add(openShopButton);
		add(openBookButton);
		add(descriptionTitle);
		add(description);
		add(logoLabel);
		add(background);
		//add(layers);
	}

	private void setUpScrollPanes() {
		//scrollpanes

				systemListModel.clear();
				systemList.removeAll();
				
				 for (GameSystem currentSystem : listOfSystems.getList()) {
						systemListModel.addElement(currentSystem.getName());
					}
							
				
				//Cycle through all slides in the contents list and creates a JButton for each 
				

				
				systemScroll.setViewportView(systemList);
				systemScroll.setBounds(130, 200, 120, 300);
				systemList.setFont(new Font("Papyrus", Font.PLAIN, 14));
				systemScroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				
				bookListModel.clear();
				bookList.removeAll();

				

				
				//Cycle through all slides in the contents list and creates a JButton for each 
				
				bookScroll.setViewportView(bookList);
				bookScroll.setBounds(290, 200, 120, 300);
				bookList.setFont(new Font("Papyrus", Font.PLAIN, 14));
				bookScroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

				
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
		
				 bookList.addMouseListener(new MouseListener() {
			         

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
			             chosenBook = listOfBooks.getBook(bookList.getSelectedIndex()).getFileName();
			             currentBookName = listOfBooks.getBook(bookList.getSelectedIndex()).getTitle();
			         }
			     });
				 
				 systemList.addMouseListener(new MouseListener() {
			         

						private String chosenSystem;


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
				        	chosenSystem = listOfSystems.get(systemList.getSelectedIndex()).getFilename();
				        	currentSystemName = listOfSystems.get(systemList.getSelectedIndex()).getName();
				    		BookXMLParser bookParser = new BookXMLParser(chosenSystem);	
				    		listOfBooks = bookParser.readBookXML(chosenSystem);
				        	bookListModel.clear();
							bookList.removeAll();
							bookListForContents = listOfBooks.getList();
				        	 for (Book currentBook : bookListForContents) {
									bookListModel.addElement(currentBook.getTitle());
								}
								
				        	 bookList.repaint();
				        	 systemDescription = listOfSystems.get(systemList.getSelectedIndex()).getDescription();
				        	 description.setText(null);
				        	 description.setText(systemDescription);
				        	 
				        	BufferedImage bookImage;
				     		try{
				     			bookImage = ImageIO.read(new File(listOfSystems.get(systemList.getSelectedIndex()).getLogoFileName()));
				     			Image scaledBackground = bookImage.getScaledInstance(150,300,java.awt.Image.SCALE_SMOOTH);
				     			imageLabel.setIcon((new ImageIcon(scaledBackground)));
				     		}catch(IOException e2){
				     			e2.printStackTrace();
				     		}
				     		imageLabel.repaint();
				         }
				        
				     });
				
	}
	
	/**
	 * 
	 */
	public void resizeMainMenu(double scaleFactorX, double scaleFactorY){
		layers.setBounds(0, 0, (int)(scaleFactorX * (double)getWidth()), (int)(scaleFactorY * (double)getHeight()));
		systemScroll.setBounds((int)(scaleFactorX * (double) 130), (int)(scaleFactorY * (double)200), (int)(scaleFactorX * (double)120), (int)(scaleFactorY * (double)300));
		bookScroll.setBounds((int)(scaleFactorX * (double)290), (int)(scaleFactorY * (double)200), (int)(scaleFactorX * (double)120), (int)(scaleFactorY * (double)300));
		description.setBounds((int)(scaleFactorX * (double)130), (int)(scaleFactorY * (double)40), getWidth() -(int)(scaleFactorX * (double)250), (int)(scaleFactorY * (double)100));
		descriptionTitle.setBounds((int)(scaleFactorX * (double)130),(int)(scaleFactorY * (double) 10), getWidth()-(int)(scaleFactorX * (double)250), (int)(scaleFactorY * (double)30));
		
		
		
		imageLabel.setBounds((int)(scaleFactorX * (double)440), (int)(scaleFactorY * (double)200), (int)(scaleFactorX * (double)150), (int)(scaleFactorY * (double)300));
		if(listOfBooks!=null){
			BufferedImage bookImage;
	 		try{
	 			bookImage = ImageIO.read(new File(listOfSystems.get(systemList.getSelectedIndex()).getLogoFileName()));
	 			Image scaledBackground = bookImage.getScaledInstance((int)(scaleFactorX * (double)150),(int)(scaleFactorY * (double)300),java.awt.Image.SCALE_SMOOTH);
	 			imageLabel.setIcon((new ImageIcon(scaledBackground)));
	 		}catch(IOException e2){
	 			e2.printStackTrace();
	 		}
	 		imageLabel.repaint();
		}
		
		
		systemLabel.setBounds((int)(scaleFactorX * (double)110), (int)(scaleFactorY * (double)140), (int)(scaleFactorX * (double)180), (int)(scaleFactorY * (double)50));
		bookLabel.setBounds((int)(scaleFactorX * (double)280 ), (int)(scaleFactorY * (double)140), (int)(scaleFactorX * (double)170 ), (int)(scaleFactorY * (double)50));
		openBookButton.setBounds(getWidth()-(int)(scaleFactorX * (double)120), (int)(scaleFactorY * (double)250), (int)(scaleFactorX * (double)110), (int)(scaleFactorY * (double)50));
		openShopButton.setBounds(getWidth()-(int)(scaleFactorX * (double)120), (int)(scaleFactorY * (double)370), (int)(scaleFactorX * (double)110), (int)(scaleFactorY * (double)50));
	
		
		this.remove(openBookButton);
		BufferedImage bookButtonImage;
		try{
			bookButtonImage = ImageIO.read(new File("resources/buttons/OpenBookButton.png"));
			Image scaledButton = bookButtonImage.getScaledInstance((int)(scaleFactorX * (double)110),(int)(scaleFactorY * (double)50),java.awt.Image.SCALE_SMOOTH);
			openBookButton.setIcon(new ImageIcon(scaledButton));
		}catch (IOException ex){
			
		}
		this.add(openBookButton);
		
		
		this.remove(openShopButton);
		BufferedImage shopButtonImage;
		try{
			shopButtonImage = ImageIO.read(new File("resources/buttons/OpenShopButton.png"));
			Image scaledButton = shopButtonImage.getScaledInstance((int)(scaleFactorX * (double)110),(int)(scaleFactorY * (double)50),java.awt.Image.SCALE_SMOOTH);
			openShopButton.setIcon(new ImageIcon(scaledButton));
		}catch (IOException ex){
			
		}
		this.add(openShopButton);
		
		//logo
				this.remove(logoLabel);
				BufferedImage logoImage;
				try{
					logoImage = ImageIO.read(new File("resources/buttons/LogoWithBackground.png"));
					Image scaledBackground = logoImage.getScaledInstance(100,getHeight(),java.awt.Image.SCALE_SMOOTH);
					logoLabel = new JLabel(new ImageIcon(scaledBackground));
					logoLabel.setBounds(0, 0, 100,getHeight());
				}catch(IOException e2){
					e2.printStackTrace();
				}
				this.add(logoLabel);
		
		//background
		BufferedImage backgroundImage;
		this.remove(background);
		try{
			backgroundImage = ImageIO.read(new File("resources/buttons/Background.png"));
			Image scaledBackground = backgroundImage.getScaledInstance(getWidth(),getHeight(),java.awt.Image.SCALE_SMOOTH);
			background = new JLabel(new ImageIcon(scaledBackground));
			background.setBounds(0, 0, getWidth(), getHeight());
		}catch(IOException e2){
			e2.printStackTrace();
		}
		this.add(background);
		
		System.out.println("Resized Main Menu");
		
		this.repaint();
		
	
	
	}

	public JButton getButton(){
		return openBookButton;
	}
	
	public String getChosenBook(){
		return chosenBook;
	}

	public String getCurrentSystem() {
		return currentSystemName;
	}

	public String getCurrentBook() {
		return currentBookName;
	}

	public ArrayList<Book> getBookList() {
		return bookListForContents;
	}
	
	
}
