package musicPlayerModule;

import java.io.IOException;

import javax.swing.JFrame;

import uk.co.caprica.vlcj.test.basic.PlayerControlsPanel;


public class EmbeddedAudioHigherModuleExample {
    static JFrame frame = new JFrame(); 
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
  
       EmbeddedAudioPlayer player = new EmbeddedAudioPlayer();
        
       frame.add(player.getPanel()); 
       frame.setName("cunt frame");
       frame.setVisible(true);
       
        player.playMedia("M:\\Year 2\\Java Labs\\VLCAudioTest\\Playlist\\03 - Evil.mp3");
    }
    
    
}
