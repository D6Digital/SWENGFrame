package musicPlayerModule;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;


public class HigherModuleExample {
    static JButton stopButton = new JButton();
    static JButton pauseButton = new JButton();
    static JButton playButton = new JButton();
    static JButton nextButton = new JButton();
    static JButton previousButton = new JButton();
    static JButton openPlaylistButton = new JButton();
    static JSlider volumeSlider = new JSlider();
    static JFrame mainFrame = new JFrame();
    static JPanel mainPanel = new JPanel();
    static JLabel label = new JLabel();
    static String currentFilePath = "M:\\Year 2\\Engineering for Hearing and Voice\\Lab 1- Week 3\\Audio Samples";
    //static String vlcLibraryPath = "M:\\Year 2\\VLC\\vlc-2.0.1";
    static String vlcLibraryPath = "M:\\Year 2\\Java Labs\\SWENGFrame\\resources\\lib\\vlc-2.1.3";
    static StandAloneMusicPlayer  musicPlayer = new StandAloneMusicPlayer(vlcLibraryPath, currentFilePath);
    
    public static void main(String[] args) throws IOException {
       // StandAloneMusicPlayer  musicPlayer = new StandAloneMusicPlayer(vlcLibraryPath, currentFilePath);
        //stopButton = musicPlayer.getStopButton();
        //pauseButton = musicPlayer.getPauseButton();
        //playButton = musicPlayer.getPlayButton();
        //nextButton = musicPlayer.getNextButton();
        //previousButton = musicPlayer.getPreviousButton();
       // openPlaylistButton = musicPlayer.getOpenPlaylistButton();
       // volumeSlider = musicPlayer.getVolumeSlider();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 400, 500);
        mainPanel.add(musicPlayer.getFullControlPanel(400, 500));
        
       // mainPanel.add(label);
        
       // mainPanel.add(stopButton);
       // mainFrame.add(mainPanel);
       // mainPanel.add(pauseButton);
       // mainFrame.add(mainPanel);
       // mainPanel.add(playButton);
       // mainFrame.add(mainPanel);
       // mainPanel.add(nextButton);
        //mainFrame.add(mainPanel);
       // mainPanel.add(previousButton);
       // mainPanel.add(openPlaylistButton);
       // mainPanel.add(volumeSlider);
        mainFrame.setSize(400, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        mainFrame.validate();
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
                    label.setText(musicPlayer.getCurrentPosition() + "/" + musicPlayer.getTrackLength());               
                    mainPanel.repaint();
                }  
        }
    };
}
