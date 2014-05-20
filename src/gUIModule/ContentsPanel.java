package gUIModule;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import presentation.Presentation;
import presentation.Slide;


/**
 * 
 * @author Sam Pick
 * @author Sam L
 */
public class ContentsPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Slide> contentsSlideList;
	SlidePanel slidePanel1;
	SlidePanel slidePanel2;
	JButton mainMenuButton = new JButton("Main Menu");
	JLabel systemLabel = new JLabel();
	JLabel bookLabel = new JLabel();
	DefaultListModel listModel = new DefaultListModel<String>();
	JList contentsList = new JList(listModel);
	
	
	/**
	 * Creates the entire Contents panel to meet the UI design specification
	 * @param slide1
	 * @param slide2
	 */
	public ContentsPanel(ArrayList<Slide> contentSlideList) {
		super();
		this.setLayout(null);
		contentsSlideList = contentSlideList;
		//slidePanel1 = slide1;
		//slidePanel2 = slide2;
		mainMenuButton.setBounds(25, 50, 100, 40);
		systemLabel.setBounds(10,120,130,40);
		bookLabel.setBounds(10,190,130,40);
		systemLabel.setText("System:");
		bookLabel.setText("Book:");
		this.add(mainMenuButton);
		this.add(systemLabel);
		this.add(bookLabel);
		
		JScrollPane contents = createScrollPane(contentsSlideList);
		contents.setBounds(0,260,220,200);
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
		
		listModel.clear();
		contentsList.removeAll();

		
		//Cycle through all slides in the contents list and creates a JButton for each 
		for (Slide currentSlide : contentsSlideList) {
			listModel.addElement(currentSlide.getSlideID() + ". " + currentSlide.getDescriptor());
		}
		contents.setViewportView(contentsList);
		
		contentsList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {	}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					int x = contentsList.getSelectedIndex();
					contentsList.clearSelection();
					System.out.println("NEW SELECTION MADE: " + x);
				}
			}
		});
		
		return contents;

//		for (int i = 0; i < contentsSlideList.size(); i++) {
//			final Slide currentSlide = contentsSlideList.get(i);
//			final String currentTitle = (String) contentsSlideList.get(i).getSlideName();
//			JButton currentButton = new JButton(currentTitle);
//			currentButton.setVerticalTextPosition(AbstractButton.CENTER);
//			currentButton.setHorizontalTextPosition(AbstractButton.CENTER);
//			currentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//			currentButton.setToolTipText("Go to " + currentTitle);	
//			
//			//Action listener will display the relevant slide when a button is pressed
//	        currentButton.addActionListener(new ActionListener() {
//	        	 
//	            public void actionPerformed(ActionEvent e)
//	            {
//	                //Execute when button is pressed
//	                System.out.println("You chose " + currentTitle);
//	                SlidePanel newSlide = new SlidePanel();
//	                newSlide.refreshSlide(currentSlide);
//	                
//	            }
//	        });
	        
		//Add to contents JScrollPane	
//			contents.add(currentButton);
//		}
//		
//		return contents;
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
