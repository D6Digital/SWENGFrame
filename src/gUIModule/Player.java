package gUIModule;

import javax.swing.JFrame;

import presentation.Presentation;


public class Player {

	public Player(Presentation presentation) {
		
		JFrame testFrame = new JFrame(presentation.getTitle());
		testFrame.setBounds(0, 0, presentation.getWidth(), presentation.getHeight());
		SlidePanel slidePanel = new SlidePanel();
		
		slidePanel.setupSlide(presentation.get(0));
		slidePanel.loadPresentation(presentation);
		
		testFrame.add(slidePanel);	
		testFrame.setVisible(true);
		
		
		testFrame.revalidate();
		
		
		
		
		
		
		
	}

	public void play() {
		// TODO Auto-generated method stub
		
	}

}
