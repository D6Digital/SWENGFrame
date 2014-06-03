package gUIModule;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import presentation.Slide;

import main.Overall;
import musicPlayerModule.EmbeddedAudioPlayer;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class ControlPanel extends JPanel{

    static JLabel label = new JLabel();
    static EmbeddedAudioPlayer player; 
    static JButton stop, play, pause, loop;
    static JSlider volume;
    static String vlcLibraryPath = "resources\\lib\\vlc-2.1.3";
    static JPanel musicPanel = new JPanel();
	JButton nextSlide = new JButton("Next");
	JButton previousSlide = new JButton("Previous");
    
	
	public ControlPanel(){
		super();
		setLayout(null);
		setOpaque(false);


		
		nextSlide.setVerticalTextPosition(AbstractButton.CENTER);
		nextSlide.setHorizontalTextPosition(AbstractButton.CENTER);
		nextSlide.setAlignmentX(Component.CENTER_ALIGNMENT);
		nextSlide.setBounds(640, 0, 100, 30);
		add(nextSlide);

		previousSlide.setVerticalTextPosition(AbstractButton.CENTER);
		previousSlide.setHorizontalTextPosition(AbstractButton.CENTER);
		previousSlide.setAlignmentX(Component.CENTER_ALIGNMENT);
		previousSlide.setBounds(0, 0, 100, 30);
		add(previousSlide);
		
		 MusicPlayer();
		 musicPanel.setBounds(100, 0, 540, 50);
		 add(musicPanel);
		 
		 nextSlide.addActionListener(
				 new ActionListener() {
		                
		                @Override
		                public void actionPerformed(ActionEvent arg0) {
		                   
//		                   int currSlideID = Overall.bookMain.getCurrentSlideID();
//		                   currSlideID ++;
//		                   Overall.bookMain.setCurrentSlideID(currSlideID);
//		                   
//		                   Slide currSlide = Overall.bookMain.getNextSlide();
		                   Overall.bookMain.showNextSlide();
		                }
		            });
		 previousSlide.addActionListener(
				 new ActionListener() {
		                
		                @Override
		                public void actionPerformed(ActionEvent arg0) {
		                   
//		                   int currSlideID = Overall.bookMain.getCurrentSlideID();
//		                   currSlideID ++;
//		                   Overall.bookMain.setCurrentSlideID(currSlideID);
//		                   
//		                   Slide currSlide = Overall.bookMain.getNextSlide();
		                   Overall.bookMain.showPreviousSlide();
		                }
		            });
	}
	
	public void MusicPlayer(){
	      
			musicPanel.setOpaque(false);
	       
	       NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),vlcLibraryPath);
	       Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
	       
	       player = new EmbeddedAudioPlayer(vlcLibraryPath);

	       player.prepareMediaWithDuration("src/XMLBits/RunWithUs.mp3", 30, 5, false);
	      // player.playMedia("C:\\Users\\Public\\Music\\Sample Music\\Kalimba.mp3");
	       
	       play = new JButton("PLAY");
	       stop = new JButton("STOP");
	       pause = new JButton("PAUSE");
	       loop = new JButton("NO LOOPING");
	       volume = new JSlider();
	       
	       play.addActionListener(
	               new ActionListener() {
	                
	                @Override
	                public void actionPerformed(ActionEvent arg0) {

	                    player.playMedia();

	                }
	            });
	       
	       stop.addActionListener(
	               new ActionListener() {
	                
	                @Override
	                public void actionPerformed(ActionEvent arg0) {
	                    player.stopMedia();
	                    
	                }
	            });
	       
	       pause.addActionListener(
	               new ActionListener() {
	                
	                @Override
	                public void actionPerformed(ActionEvent arg0) {
	                    player.pauseMedia();
	                    
	                }
	            });
	       
	       loop.addActionListener(
	               new ActionListener() {
	                
	                @Override
	                public void actionPerformed(ActionEvent arg0) {
	                    if(!player.getLooping()) {
	                    player.setLooping(true);
	                    loop.setText("LOOPING");
	                    }
	                    else if(player.getLooping()) {
	                    player.setLooping(false);
	                    loop.setText("NOT LOOPING");
	                    }
	                }
	            });
	       
	       volume.addChangeListener(new ChangeListener() {
	        
	        @Override
	        public void stateChanged(ChangeEvent arg0) {
	            player.setVolumePercentage(volume.getValue());
	            
	        }
	    });
	       
	       musicPanel.add(play);
	       musicPanel.add(pause);
	       musicPanel.add(stop);
	       musicPanel.add(loop);
	       volume.setBounds(0, 100, 100, 10);
	       musicPanel.add(volume);
	       
	       musicThread.start();
	       
	       
	       
	    }
	       
	    static Thread musicThread = new Thread("Socket") {
	        public void run() {
	                while (true) {
	                    try {
	                     Thread.sleep(100);
	                 } catch (InterruptedException e) {
	                     // TODO Auto-generated catch block
	                     e.printStackTrace();
	                 }
	                    //playLoop();
	                    label.setText(player.getCurrentPosition() + "/" + player.getTrackLength());               
	                   // panel.repaint();
	                }  
	        }
	    };
	    
		
		
	}

