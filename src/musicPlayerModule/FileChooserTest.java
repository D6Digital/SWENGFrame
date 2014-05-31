package musicPlayerModule;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.co.caprica.vlcj.medialist.MediaList;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.list.MediaListPlayer;
import uk.co.caprica.vlcj.player.list.MediaListPlayerMode;

public class FileChooserTest {
    String vlcLibraryPath;
    DefaultListModel listModel = new DefaultListModel<String>();
    JFrame mainFrame = new JFrame("mainFrame");
    //JFrame f = new JFrame("vlcj embedded media list player test");
    JFrame playlistFrame = new JFrame("playlistFrame");
    JPanel playPanel = new JPanel();
    JList playContentsJList = new JList(listModel);
    static JLabel timeLabel = new JLabel();
    static JSlider timeSlider = new JSlider();
    static JPanel fullPanel = new JPanel();

    private String currentTime = "";
    private String newTime = "";

    Container contentPane;
    JScrollPane scrollPane = new JScrollPane();
    EmbeddedMediaPlayer mediaPlayer;
    MediaList mediaList;
    MediaListPlayer mediaListPlayer;
    private  final long serialVersionUID = 1L;
    int currentPlayIndex = 0;
    Boolean newIndex = false;
    String currentFilePath;
    String newFilePath;
    FileChooser fileChooser = new FileChooser(newFilePath);
    Boolean isPaused = false;
    protected boolean threadKilled = false;

    protected boolean changingTimeByHand;
    protected boolean changingSelectedPlaylistByHand;
    //private boolean playlistLocked = true;
    
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
       
        
        frame.setSize(300, 300);
        frame.setLayout(null);
        panel.setLayout(null);
        //chooser.setBackground(new Color(203, 123, 172));
        
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
        do{
            
        }
        while(x);
    }

    private void createList(ArrayList<String> nameList) {
        listModel.clear();

        for(String listItem : nameList){
            listModel.addElement(listItem);
        }
        playContentsJList.setLayout(null);
        playContentsJList.setBounds(0, 0, 100, 100);
        scrollPane.setViewportView(playContentsJList);
        scrollPane.setLayout(null);
        scrollPane.setBounds(0, 0, 100, 100);
        playPanel.setLayout(null);
        playPanel.setBounds(0, 0, 100, 100);
        playPanel.add(scrollPane);
        playlistFrame.pack();
        // Add listener which plays a piece of media whenever the user chooses it in the JList.
        playContentsJList.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                changingSelectedPlaylistByHand = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                changingSelectedPlaylistByHand = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseClicked(MouseEvent e) {}
        });

        playContentsJList.addListSelectionListener(new ListSelectionListener() {  
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if(changingSelectedPlaylistByHand) {
                    //chooseFromPlaylist();
                }
            }
        });
    }
    
    private JPanel setupGUI(ArrayList<String> files) {
        JPanel playlistChooserPanel = new JPanel();

        playlistChooserPanel.setLayout(null);
        playlistChooserPanel.setBounds(0, 0, 100, 100);
        
        playlistChooserPanel.add(fileChooser.openDialog());
        createList(files);

        contentPane = playlistFrame.getContentPane();
        //contentPane.setLayout(null);

        //mediaPlayer = openMediaPlayer();

        fullPanel.setLayout(null);
        fullPanel.setBounds(0, 0, 100, 100);
        
        fullPanel.add(playlistChooserPanel);
        fullPanel.add(playPanel);
        return fullPanel;

        //        playlistFrame.add(playlistChooserPanel, BorderLayout.NORTH);
        //        playlistFrame.add(playPanel, BorderLayout.CENTER);
        //        playlistFrame.setSize(200, 300);
        //        playlistFrame.pack();
        //        playlistFrame.setVisible(true);
        //        playlistFrame.validate(); 

    }
    
    private void chooseNewPlaylist() {
        if(!LockedPlaylistValueAccess.lockedPlaylist) {


            newFilePath = fileChooser.getNewFilePath();
            String[] options = {};

            // Only change the playlist if the new path is different from the old one.
            if((currentFilePath != newFilePath)  && (newFilePath != null)) {
                mediaPlayer.stop();
                mediaListPlayer.stop();


                ArrayList<String> files = getFilenames(newFilePath);
                createList(files);
                currentFilePath = newFilePath;
                System.out.println("size is " + mediaList.size());

                // Remove all media items from the list.
                //          for(int i = mediaList.size(); i >= 0; i--) {
                //              mediaList.removeMedia(i);
                //          }
                mediaList.clear();


                // Add the new items to the playlist.
                for(String filename : files) {
                    mediaList.addMedia(newFilePath + "\\" + filename, options); 
                    System.out.println("" + filename);
                }
                mediaListPlayer.setMediaList(mediaList);
                mediaListPlayer.setMode(MediaListPlayerMode.LOOP);
                mediaListPlayer.setMediaPlayer(mediaPlayer);

                mediaPlayer.release();
                mainFrame.dispose();
                mainFrame.setVisible(false);
                //mediaPlayer = openMediaPlayer();
                currentPlayIndex = 0;
                playContentsJList.repaint();
            }
        }
    }

    private static ArrayList<String> getFilenames(String newFilePath) {
        File folder = new File(newFilePath);
        File[] shit = folder.listFiles();
        ArrayList<String> returnList = new ArrayList();

        if(!newFilePath.equals("")) {
            for(int i = 0; i < shit.length; i ++) {
                returnList.add(shit[i].getName());
            }
        }
        return returnList;
    }
    
}
