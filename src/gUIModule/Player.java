package gUIModule;

import java.util.ArrayList;

import javax.swing.JFrame;

import musicPlayerModule.EmbeddedAudioPlayer;

import presentation.Presentation;
import presentation.Slide;


public class Player {

	public Player(Presentation presentation) {
		
		JFrame testFrame = new JFrame("TEST");
		testFrame.setBounds(0, 0, presentation.getWidth(), presentation.getHeight());
		SlidePanel slidePanel = new SlidePanel();
		
		slidePanel.setupSlide(presentation.get(0));
		slidePanel.setVisibility(true);
		
		testFrame.add(slidePanel);		
		testFrame.setVisible(true);
		
		slidePanel.playSounds();
		
		testFrame.revalidate();
		
		
		
		
		
		
		
	}

	public void play() {
		// TODO Auto-generated method stub
		
	}

}
