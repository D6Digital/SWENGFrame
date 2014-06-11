package musicPlayerModule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

/**
 * Sets up basic higher module to provide test for working of standalone audio player.
 * Note, test needs rewrite. No longer running due to addition of adapter for mouse
 * in StandAloneAudioPlayer.
 *@author Joshua Lant
 */
public class SecondHigherModuleExampleT001_2 {
    static JFrame mainFrame = new JFrame();
    static JPanel mainPanel = new JPanel();
    static JLabel label = new JLabel();
    static String currentFilePath = "M:\\Year 2\\Engineering for Hearing and Voice\\Lab 1- Week 3\\Audio Samples";
    static String vlcLibraryPath = "M:\\Year 2\\Java Labs\\SWENGFrame\\resources\\lib\\vlc-2.1.3";
    static StandAloneMusicPlayer  musicPlayer;
    static JButton killButton = new JButton("KILL");

    public static void main(String[] args) throws IOException {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),vlcLibraryPath);
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        
        musicPlayer = new StandAloneMusicPlayer(currentFilePath);

        mainFrame.setLayout(null);
        mainPanel.setLayout(null);  
        mainPanel.add(musicPlayer.getFullControlPanel(300, 400));
        mainPanel.setBounds(0, 0, 300, 400);
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
        mainFrame.validate();;
    }
}
