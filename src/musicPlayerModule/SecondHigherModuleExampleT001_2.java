package musicPlayerModule;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;


public class SecondHigherModuleExampleT001_2 {
    static JFrame mainFrame = new JFrame();
    static JPanel mainPanel = new JPanel();
    static JLabel label = new JLabel();
    
    static String currentFilePath = "M:\\Year 2\\Engineering for Hearing and Voice\\Lab 1- Week 3\\Audio Samples";
    //static String vlcLibraryPath = "M:\\Year 2\\VLC\\vlc-2.0.1";
    static String vlcLibraryPath = "M:\\Year 2\\Java Labs\\SWENGFrame\\resources\\lib\\vlc-2.1.3";
    static StandAloneMusicPlayer  musicPlayer;
    static JButton killButton = new JButton("KILL");
    


    

    
    
    public static void main(String[] args) throws IOException {
       // StandAloneMusicPlayer  musicPlayer = new StandAloneMusicPlayer(vlcLibraryPath, currentFilePath);
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),vlcLibraryPath);
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        
        MouseAdapter adapter = null;
        musicPlayer = new StandAloneMusicPlayer(adapter);
        mainFrame.setLayout(null);
        mainPanel.setLayout(null);
        
        mainPanel.add(musicPlayer.getFullControlPanel(300, 400));
        //mainPanel.add(killButton);
        mainPanel.setBounds(0, 0, 300, 400);
        //new Color(0);
        //mainPanel.setBackground(Color.WHITE);
        mainPanel.repaint();
        
        JLabel label = new JLabel("VOLUME");
        label.setBounds(0, 0, 50,50);
        mainPanel.add(label);
        
        killButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                musicPlayer.killThread();
                System.out.println("KILLED");
                
            }
        });
        
        mainFrame.setSize(300, 400);
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
