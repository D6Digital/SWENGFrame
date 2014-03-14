package gUIModule;

import java.util.ArrayList;

import javax.swing.JFrame;

import presentation.Presentation;
import presentation.Slide;


public class Player {

	public Player(Presentation presentation) {
		
		
		SlidePanel slidePanel = new SlidePanel();
		
		
		slidePanel.setupSlide(presentation.get(0));
		JFrame testFrame = new JFrame("TEST");
		testFrame.setBounds(0, 0, presentation.getWidth(), presentation.getHeight());
		slidePanel.setVisibility(true);
		testFrame.add(slidePanel);
		
		testFrame.setVisible(true);
		
	}

	public void play() {
		// TODO Auto-generated method stub
		
	}

}
