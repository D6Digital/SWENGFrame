package gUIModule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import slideModule.Slide;
import slideModule.SlidePanel;

/**
 * 
 * @author Sam Pick
 *
 */
public class ContentsPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Slide> contentsSlideList;
	SlidePanel slidePanel1;
	SlidePanel slidePanel2;
	
	
	/**
	 * Creates the entire Contents panel to meet the UI design specification
	 * @param slide1
	 * @param slide2
	 */
	public ContentsPanel(SlidePanel slide1, SlidePanel slide2, ArrayList<Slide> contentSlideList) {
		super();
		contentsSlideList = contentSlideList;
		slidePanel1 = slide1;
		slidePanel2 = slide2;
		
		JScrollPane contents = createScrollPane(contentsSlideList);
		
		this.add(contents);
		// TODO add a title JLabel and ensure the panel is ready
	}
	
	
	/**
	 * 
	 * Produces a scrolling pane which contains all the slides that should be in the contents
	 * in the form of JButtons with necessary slide ID or slide name information.
	 * 
	 * All these JButtons should have an action listener.
	 * 
	 * @param contentsSlideList
	 * @return the contents scroll pane
	 */
	private JScrollPane createScrollPane(ArrayList<Slide> contentsSlideList) {
		JScrollPane contents = new JScrollPane();
		// TODO go through the slide list and add JButtons for each one
		return contents;
	}
	
	
	
	/**
	 * Check the which button is released and then change slidePanel
	 * to display the chosen slide from the contents list
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO manage button presses
		
	}

	/**
	 * 
	 * @return the current content list
	 */
	public ArrayList<Slide> getContentsSlideList() {
		return contentsSlideList;
	}

	/**
	 * set the content list to the appropriate slides
	 * @param contentsSlideList
	 */
	public void setContentsSlideList(ArrayList<Slide> contentsSlideList) {
		this.contentsSlideList = contentsSlideList;
	}}
