package musicPlayerModule;

import java.io.IOException;

import uk.co.caprica.vlcj.test.basic.PlayerControlsPanel;


public class EmbeddedAudioHigherModuleExample {
   
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        EmbeddedAudioPlayer player = new EmbeddedAudioPlayer();
        player.playMedia("M:\\Year 2\\Java Labs\\VLCAudioTest\\Playlist\\03 - Evil.mp3");
    }
    
    
}
