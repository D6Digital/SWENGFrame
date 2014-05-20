package musicPlayerModule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;


public class SecondHigherModuleExample {
    static JFrame mainFrame = new JFrame();
    static JPanel mainPanel = new JPanel();
    static JLabel label = new JLabel();
    
    static String currentFilePath = "M:\\Year 2\\Engineering for Hearing and Voice\\Lab 1- Week 3\\Audio Samples";
    //static String vlcLibraryPath = "M:\\Year 2\\VLC\\vlc-2.0.1";
    static String vlcLibraryPath = "M:\\Year 2\\Java Labs\\SWENGFrame\\resources\\lib\\vlc-2.1.3";
    static StandAloneMusicPlayer  musicPlayer = new StandAloneMusicPlayer(vlcLibraryPath, currentFilePath);
    static JButton killButton = new JButton("KILL");
    
    
    public static void main(String[] args) throws IOException {
       // StandAloneMusicPlayer  musicPlayer = new StandAloneMusicPlayer(vlcLibraryPath, currentFilePath);
        
        
        mainPanel.add(musicPlayer.getFullControlPanel());
        mainPanel.add(killButton);
        killButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                musicPlayer.killThread();
                System.out.println("KILLED");
                
            }
        });
        
        mainFrame.setSize(200, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        mainFrame.validate();
        
        //musicThread.start();
       }
    
//    static Thread musicThread = new Thread("Socket") {
//        public void run() {
//                while (true) {
//                    try {
//                     Thread.sleep(100);
//                 } catch (InterruptedException e) {
//                     // TODO Auto-generated catch block
//                     e.printStackTrace();
//                 }
//                    //playLoop();
//                    label.setText(musicPlayer.getCurrentPosition() + "/" + musicPlayer.getTrackLength());               
//                    mainPanel.repaint();
//                }  
//        }
//    };
}
