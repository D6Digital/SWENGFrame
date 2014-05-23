package gUIModule;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel{
	
	JButton button = new JButton("Start");
	JLabel background;
	JLabel logoLabel;
	JLayeredPane layers = new JLayeredPane();
	public MainMenuPanel(int width, int height) {
		
		setLayout(null);
		layers.setLayout(null);
		layers.setBounds(0, 0, width, height);
		System.out.println("height="+height + "width="+width);
		
		button.setBounds(20, 20, 100, 40);
		
		
		BufferedImage backgroundImage;
		try{
			backgroundImage = ImageIO.read(new File("resources/buttons/Background.png"));
			Image scaledBackground = backgroundImage.getScaledInstance(width,height,java.awt.Image.SCALE_SMOOTH);
			background = new JLabel(new ImageIcon(scaledBackground));
			background.setBounds(0, 0, width, height);
		}catch(IOException e2){
			e2.printStackTrace();
		}
		
		BufferedImage logoImage;
		try{
			logoImage = ImageIO.read(new File("resources/buttons/sideLogo.png"));
			Image scaledBackground = logoImage.getScaledInstance(150,height-(int)(height*0.1),java.awt.Image.SCALE_SMOOTH);
			logoLabel = new JLabel(new ImageIcon(scaledBackground));
			logoLabel.setBounds(0, (int)(height*0.1), 150,(height-(int)(height*0.1)));
		}catch(IOException e2){
			e2.printStackTrace();
		}
		
		layers.add(logoLabel,2);
		layers.add(background,1);
		layers.add(button,0);
		add(layers);
	}

	public JButton getButton(){
		return button;
	}
	
}
