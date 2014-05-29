package gUIModule;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import presentation.Slide;

public class ContentsPanelTest {
	
	ArrayList<Slide> slideList = new ArrayList<>();
	int slideListSize = 20;
	JFrame frame = new JFrame();
	
	@Before
	public void before() {
		
		for(int i = 0; i < slideListSize; i++) {
			Slide slide = new Slide();
			slide.setSlideID(i);
			slide.setDescriptor("DESCRIPTION: " + i);
			slideList.add(slide);
		}
		
		
	}
	
	@Test
	public void test() {
		
		ContentsPanel contentsPanel = new ContentsPanel(slideList, null, 300, 500, 500, "SYSTEM", "BOOK");
		
		
		frame.setSize(400, 400);
		frame.add(contentsPanel);
		frame.setVisible(true);
		
		boolean x = true;
		do{
			
		}
		while(x);
		
	}

}
