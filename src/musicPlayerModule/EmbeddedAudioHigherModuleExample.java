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

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

/**
 * A Module which gives an example of how the embedded audio player works. This
 * example uses buttons for the user to control the player, although it is intended for 
 * head-less use.
 *
 */
public class EmbeddedAudioHigherModuleExample {
    static JFrame frame = new JFrame(); 
    static JLabel label = new JLabel();
    static JPanel panel = new JPanel();
    static EmbeddedAudioPlayer player; 
    static JButton stop, play, pause, loop;
    static JSlider volume;
    static String vlcLibraryPath = "resources\\lib\\vlc-2.1.3";


    /**
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),vlcLibraryPath);
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);


        player = new EmbeddedAudioPlayer(vlcLibraryPath);

        frame.add(player.getPanel()); 
        frame.setTitle("twat frame");
        frame.setVisible(true);
        panel.add(label);
        frame.add(panel);

        player.prepareMediaWithDuration("src/XMLBits/RunWithUs.mp3", 30, 5, false);

        play = new JButton("PLAY");
        stop = new JButton("STOP");
        pause = new JButton("PAUSE");
        loop = new JButton("NO LOOPING");
        volume = new JSlider();

        /**
         * setup play button listener
         */
        play.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        player.playMedia();

                    }
                });

        /**
         * setup stop button listener
         */
        stop.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        player.stopMedia();

                    }
                });

        /**
         * setup pause button listener
         */
        pause.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        player.pauseMedia();
                    }
                });

        /**
         * setup loop button listener
         */
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

        /**
         * setup volume slider listener
         */
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

    /**
     * start Thread which repaints the position of track, so user can
     * see which point audio begins and ends.
     */
    static Thread musicThread = new Thread("Socket") {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                label.setText(player.getCurrentPosition() + "/" + player.getTrackLength());               
                panel.repaint();
            }  
        }
    };


}
