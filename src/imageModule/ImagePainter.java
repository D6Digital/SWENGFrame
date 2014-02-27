package imageModule;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePainter {

	
	/**
	 * ProduceImage adds an image to a JLabel, which allows it to be added to a JPanel. 
	 * The size of the JLabel is set to the dimensions of the image. String file is the 
	 * filepath of the image. String description gives a small description of the image.
	 * 
	 * @author Sam L
	 * @return
	 */
	public JLabel ProduceImage(String file, String description){
				
		//Creates image icon to be use in the JLabel
		ImageIcon image = createImageIcon(file, description);
		
		//Gets dimensions info to use to set size of the JLabel
		int height = image.getIconHeight();
		int width = image.getIconWidth();
	
		//Creates JLabel with image on it
		JLabel imageLabel = new JLabel(image, JLabel.CENTER);
		
		//Set size of JLabel to fit image
		imageLabel.setPreferredSize(new Dimension(width,height));
		
		//Set JLable to opaque so it is visible
		imageLabel.setOpaque(true);
		
		//Shows the description of the image when cursor is hovered over image
		imageLabel.setToolTipText(description);
		
		//Test method which creates a JFrame to display the JLabel produced        
        showImageTest(imageLabel);
		
		return imageLabel;
	}

	//This is a test method which displays the JLabel produced  
	private void showImageTest(JLabel image) {
				
		JFrame imageTestFrame = new JFrame("Image Test");
		JPanel imageTestPanel = new JPanel();
	
		imageTestFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		imageTestFrame.add(imageTestPanel);
		imageTestPanel.add(image);
		
		imageTestFrame.pack();
        imageTestFrame.setVisible(true);
        		
	}

	// Returns an ImageIcon, or null if the path was invalid. 
    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = ImagePainter.class.getResource(path);
        
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
	
	
	
}
