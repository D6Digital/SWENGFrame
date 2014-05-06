package videoModule;

/**
 * @author 
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import uk.co.caprica.vlcj.binding.LibVlcConst;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class PlayerControlsPanel extends JPanel {

  private static final long serialVersionUID = 1L;

  private static final int SKIP_TIME_MS = 10 * 1000;
  
  private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

  private final EmbeddedMediaPlayer mediaPlayer;
  
  private JLabel timeLabel;
  private JSlider positionSlider;
  private JLabel chapterLabel;
  private JLabel loopLabel = new JLabel("Loop Off");
  
  private JButton rewindButton;
  private JButton stopButton;
  protected static JButton playButton;
  private JButton fastForwardButton;
  private JSlider volumeSlider;
  
  private JButton fullScreenButton;
  
  private JButton loop;
  private boolean loopOn = false;
  
  private boolean mousePressedPlaying = false;
  
  protected static ImageIcon img2;
  protected static ImageIcon img3;

  public PlayerControlsPanel(EmbeddedMediaPlayer mediaPlayer) {
    this.mediaPlayer = mediaPlayer;
    
    createUI();
    
    executorService.scheduleAtFixedRate(new UpdateRunnable(mediaPlayer), 0L, 1L, TimeUnit.SECONDS);
  }
  
  private void createUI() {
    createControls();
    layoutControls();
    registerListeners();
  }
   
  private void createControls() {
    timeLabel = new JLabel("hh:mm:ss");
    
    positionSlider = new JSlider();
    positionSlider.setMinimum(0);
    positionSlider.setMaximum(1000);
    positionSlider.setValue(0);
    positionSlider.setToolTipText("Position");
    
    chapterLabel = new JLabel("00/00");

    rewindButton = new JButton();
    ImageIcon img = new ImageIcon("resources/buttons/rewind.png");
    rewindButton.setIcon(img);
    rewindButton.setToolTipText("Skip back");
    rewindButton.setPreferredSize(new Dimension(45, 45));

    
    stopButton = new JButton();
    ImageIcon img1 = new ImageIcon("resources/buttons/stop.png");
    stopButton.setIcon(img1);
    stopButton.setToolTipText("Stop");
    stopButton.setPreferredSize(new Dimension(45, 45));
    
    playButton = new JButton();
    img3 = new ImageIcon("resources/buttons/play.png");
    img2 = new ImageIcon("resources/buttons/pause.png");
    playButton.setIcon(img2);
    playButton.setToolTipText("Play");
    playButton.setPreferredSize(new Dimension(45, 45));
    
    fastForwardButton = new JButton();
    ImageIcon img4 = new ImageIcon("resources/buttons/fastforward.png");
    fastForwardButton.setIcon(img4);
    fastForwardButton.setToolTipText("Skip forward");
    fastForwardButton.setPreferredSize(new Dimension(45, 45));
    
    volumeSlider = new JSlider();
    volumeSlider.setOrientation(JSlider.HORIZONTAL);
    volumeSlider.setMinimum(LibVlcConst.MIN_VOLUME);
    volumeSlider.setMaximum(LibVlcConst.MAX_VOLUME);
    volumeSlider.setPreferredSize(new Dimension(100, 40));
    volumeSlider.setToolTipText("Change volume");
    

    fullScreenButton = new JButton();
    fullScreenButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/image.png")));
    fullScreenButton.setToolTipText("Toggle full-screen");
    
    loop = new JButton();
    ImageIcon img5 = new ImageIcon("resources/buttons/loop.png");
    loop.setIcon(img5);
    loop.setToolTipText("Loop video");
    loop.setPreferredSize(new Dimension(45, 45));
   
  }
  
  private void layoutControls() {
    setBorder(new EmptyBorder(4, 4, 4, 4));
    
    setLayout(new BorderLayout());

    JPanel positionPanel = new JPanel();
    positionPanel.setLayout(new GridLayout(1, 1));
    positionPanel.add(positionSlider);
    
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout(8, 0));
    
    topPanel.add(timeLabel, BorderLayout.WEST);
    topPanel.add(positionPanel, BorderLayout.CENTER);
    topPanel.add(chapterLabel, BorderLayout.EAST);
    
    add(topPanel, BorderLayout.NORTH);
    
    JPanel bottomPanel = new JPanel();
    
    bottomPanel.setLayout(new FlowLayout());
    
    bottomPanel.add(rewindButton);
    bottomPanel.add(stopButton);
    bottomPanel.add(playButton);
    bottomPanel.add(fastForwardButton);
    bottomPanel.add(loop);
    bottomPanel.add(loopLabel);   
    bottomPanel.add(volumeSlider);
    
    add(bottomPanel, BorderLayout.SOUTH);
  }
  
  /**
   * Broken out position setting, handles updating mediaPlayer
   */
  private void setSliderBasedPosition() {
    if(!mediaPlayer.isSeekable()) {
      return;
    }
    float positionValue = (float)positionSlider.getValue() / 1000.0f;
    // Avoid end of file freeze-up 
    if(positionValue > 0.99f) {
      positionValue = 0.99f;
    }
    mediaPlayer.setPosition(positionValue);
  }
  
  private void updateUIState() {
    if(!mediaPlayer.isPlaying()) {
      // Resume play or play a few frames then pause to show current position in video
      mediaPlayer.play();
      if(!mousePressedPlaying) {
          try {
            // Half a second probably gets an iframe
            Thread.sleep(500);  
          } 
          catch(InterruptedException e) {
            // Don't care if unblocked early
          }
          mediaPlayer.pause();
        }
    }      
    long time = mediaPlayer.getTime();
    int position = (int)(mediaPlayer.getPosition() * 1000.0f);
    int chapter = mediaPlayer.getChapter();
    int chapterCount = mediaPlayer.getChapterCount();
    updateTime(time);
    updatePosition(position);
    updateChapter(chapter, chapterCount);
  }

  private void skip(int skipTime) {
    // Only skip time if can handle time setting
    if(mediaPlayer.getLength() > 0) {
      mediaPlayer.skip(skipTime);
      updateUIState();
    }
  }
  
  private void registerListeners() {
    mediaPlayer.addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
      @Override
      public void playing(MediaPlayer mediaPlayer) {
        updateVolume(mediaPlayer.getVolume());
      }
    });
    
    positionSlider.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        if(mediaPlayer.isPlaying()) {
          mousePressedPlaying = true;
          mediaPlayer.pause();
        }
        else {
          mousePressedPlaying = false;
        }
        setSliderBasedPosition();
      }
      
      @Override
      public void mouseReleased(MouseEvent e) {
        setSliderBasedPosition();
        updateUIState();
      }
    });

    rewindButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        skip(-SKIP_TIME_MS);
      }
    });

    stopButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        mediaPlayer.stop();
        playButton.setIcon(img3);
      }
    });
    
    playButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
       if (playButton.getIcon()==img3){
    	  mediaPlayer.play();
    	  playButton.setIcon(img2);
    	  
       }
       else {   
    	   playButton.setIcon(img3);
    	   //VideoPlayer.setPause();
    	   mediaPlayer.pause();
       }
      }
    });
    
    fastForwardButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        skip(SKIP_TIME_MS);
      }
    });

    volumeSlider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
//        if(!source.getValueIsAdjusting()) {
          mediaPlayer.setVolume(source.getValue());
//        }
      }
    });
 
    fullScreenButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        mediaPlayer.toggleFullScreen();
      }
    });

    loop.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	if (loopOn==false){
        	loopOn=true;
        	mediaPlayer.setRepeat(true);
        	loop.setBorderPainted(false);
        	loopLabel.setText("Loop On");
        	}
        	else{
        	loopOn=false;
        	mediaPlayer.setRepeat(false);
        	loop.setBorderPainted(true);
        	loopLabel.setText("Loop Off");
        	}
        }
      });
  }
  
  private final class UpdateRunnable implements Runnable {

    private final MediaPlayer mediaPlayer;
    
    private UpdateRunnable(MediaPlayer mediaPlayer) {
      this.mediaPlayer = mediaPlayer;
    }
    
    @Override
    public void run() {
      final long time = mediaPlayer.getTime();
      final int position = (int)(mediaPlayer.getPosition() * 1000.0f);
      final int chapter = mediaPlayer.getChapter();
      final int chapterCount = mediaPlayer.getChapterCount();
      
      // Updates to user interface components must be executed on the Event
      // Dispatch Thread
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          if(mediaPlayer.isPlaying()) {
            updateTime(time);
            updatePosition(position);
            updateChapter(chapter, chapterCount);
          }
        }
      });
    }
  }
  
  private void updateTime(long millis) {
    String s = String.format("%02d:%02d:%02d",
      TimeUnit.MILLISECONDS.toHours(millis),
      TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), 
      TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
    );
    timeLabel.setText(s);
  }

  private void updatePosition(int value) {
//    positionProgressBar.setValue(value);
    positionSlider.setValue(value);
  }
  
  private void updateChapter(int chapter, int chapterCount) {
    String s = chapterCount != -1 ? (chapter+1) + "/" + chapterCount : "-";
    chapterLabel.setText(s);
    chapterLabel.invalidate();
    validate();
  }
  
  private void updateVolume(int value) {
    volumeSlider.setValue(value);
  }
  
  protected static void setPlayButton(){
	  playButton.setIcon(img2);
  }
}
