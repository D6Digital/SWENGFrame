package musicPlayerModule;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.co.caprica.vlcj.medialist.MediaList;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.list.MediaListPlayer;


/**
 * Provides a basic setup with frame and panel that allows
 * the user to test the file chooser on its own.
 * @author jl1132
 *
 */
public class FileChooserTest {
    String vlcLibraryPath;
    JFrame mainFrame = new JFrame("mainFrame");
    JFrame playlistFrame = new JFrame("playlistFrame");
    JPanel playPanel = new JPanel();
    static JLabel timeLabel = new JLabel();
    static JSlider timeSlider = new JSlider();
    static JPanel fullPanel = new JPanel();

    Container contentPane;
    JScrollPane scrollPane = new JScrollPane();
    EmbeddedMediaPlayer mediaPlayer;
    MediaList mediaList;
    MediaListPlayer mediaListPlayer;
    int currentPlayIndex = 0;
    Boolean newIndex = false;
    String currentFilePath;
    String newFilePath;
    FileChooser fileChooser = new FileChooser(newFilePath);
    Boolean isPaused = false;
    protected boolean threadKilled = false;

    protected boolean changingTimeByHand;
    protected boolean changingSelectedPlaylistByHand;

    String playImage = "resources/buttons/play.png";
    String pauseImage = "resources/buttons/pause.png";
    String stopImage = "resources/buttons/stop.png";
    String nextImage = "resources/buttons/fastforward.png";
    String previousImage = "resources/buttons/rewind.png";
    String lockImage = "resources/buttons/lockText.png";
    String unlockImage = "resources/buttons/unlockText.png";
    int heightOfLockIcon;
    int widthOfPlayButton;
    int heightOfPlayButton;

    String initialisedFilePath = "M:\\Year 2\\Engineering for Hearing and Voice\\Lab 1- Week 3\\Audio Samples";
    JFrame frame = new JFrame("CUNT");
    FileChooser chooser = new FileChooser(currentFilePath);
    boolean x = true;
    JPanel panel = new JPanel();

    @Before
    public void setUp() throws Exception {
        // Set up the frame and filechooser.
        frame.setSize(300, 300);
        frame.setLayout(null);
        panel.setLayout(null);

        JButton button = chooser.getButton();
        button.setBounds(0, 0, 100, 100);

        panel.add(button);
        panel.setBounds(0, 0, 300, 300);
        frame.add(panel);
        frame.validate();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        frame.setVisible(true);
        frame.repaint();
        // Infinite loop, while test is running allow user to navigate around the frame.
        do{}
        while(x);
    }

}
