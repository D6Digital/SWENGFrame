package musicPlayerModule;

import java.io.IOException;

import javax.swing.JFrame;

import uk.co.caprica.vlcj.test.basic.PlayerControlsPanel;


public class EmbeddedAudioHigherModuleExample {
    static JFrame frame = new JFrame(); 
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
  
       EmbeddedAudioPlayer player = new EmbeddedAudioPlayer();
        
       frame.add(player.getPanel()); 
       frame.setTitle("twat frame");
       frame.setVisible(true);
       
       
        player.playMedia("C:\\Users\\Public\\Music\\Sample Music\\Kalimba.mp3");
    }
    
    
}
