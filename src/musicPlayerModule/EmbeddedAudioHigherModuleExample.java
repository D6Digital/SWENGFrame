package musicPlayerModule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class EmbeddedAudioHigherModuleExample {
    static JFrame frame = new JFrame(); 
    static JLabel label = new JLabel();
    static JPanel panel = new JPanel();
    static EmbeddedAudioPlayer player = new EmbeddedAudioPlayer();
    static JButton stop, play, pause, loop;
    static JSlider volume;
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        
      
        
       frame.add(player.getPanel()); 
       frame.setTitle("twat frame");
       frame.setVisible(true);
       panel.add(label);
       frame.add(panel);

       
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
                    
                    player.prepareMedia("C:\\Users\\Public\\Music\\Sample Music\\Kalimba.mp3", 30, 40);
                    player.play();
//                    //player.playMedia("C:\\xtemp\\Neil_Landstrumm_22_02_14_HOG_21st_birthday.mp3");
//                    player.pauseMedia();
//                    System.out.println("are we here?");
//                    try {
//                        Thread.sleep(3);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                    
//                    player.setStartTime(30);
//                    player.setEndTime(45);
//                    
//                    
//                    player.play();
//                    //player.playMedia("C:\\Users\\Public\\Music\\Sample Music\\Kalimba.mp3");
//                    //player.playMedia("M:\\Year 2\\Engineering for Hearing and Voice\\Lab 1- Week 3\\Audio Samples\\aI Light rising.wav");
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
       
       panel.add(play);
       panel.add(pause);
       panel.add(stop);
       panel.add(loop);
       volume.setBounds(0, 100, 100, 10);
       panel.add(volume);
       
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
                    panel.repaint();
                }  
        }
    };
    
    
}
