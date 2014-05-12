package gUIModule;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel{

	
	
	public ControlPanel(){
		super();
		JButton nextSlide = new JButton("Next");
		JButton previousSlide = new JButton("Previous");
		setLayout(null);
		
		nextSlide.setVerticalTextPosition(AbstractButton.CENTER);
		nextSlide.setHorizontalTextPosition(AbstractButton.CENTER);
		nextSlide.setAlignmentX(Component.CENTER_ALIGNMENT);
		nextSlide.setBounds(0, 0, 100, 30);
		add(nextSlide);

		previousSlide.setVerticalTextPosition(AbstractButton.CENTER);
		previousSlide.setHorizontalTextPosition(AbstractButton.CENTER);
		previousSlide.setAlignmentX(Component.CENTER_ALIGNMENT);
		previousSlide.setBounds(900, 0, 100, 30);
		add(previousSlide);
	}
}
