package videoModule;

import java.awt.BorderLayout;
import java.awt.Color;
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

/**
 * Class that sets up the control panel which the video player uses in order to
 * control things like play, pause, time, volume etc. Sets up all listeners
 * and buttons with their correct image icons. Extending JPanel which contains the
 * whole control panel.
 */
public class PlayerControlsPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int SKIP_TIME_MS = 10 * 1000;
    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private EmbeddedMediaPlayer mediaPlayer;
    private JLabel timeLabel, chapterLabel;
    private JLabel loopLabel = new JLabel("Loop Off");
    private JSlider positionSlider, volumeSlider;
    private JButton rewindButton, stopButton, playButton, fastForwardButton, fullScreenButton, loop;
    private boolean loopOn = false;
    private boolean mousePressedPlaying = false;
    private ImageIcon img, img1, img2, img3, img4, img5;

    /**
     * Constructor for control panel, must be supplied a vlcj EmbeddedMediaPlayer for
     * which the controls can be associated with.
     * @param mediaPlayer
     */
    public PlayerControlsPanel(EmbeddedMediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        createUI();
        executorService.scheduleAtFixedRate(new UpdateRunnable(mediaPlayer), 0L, 1L, TimeUnit.SECONDS);
    }

    /**
     * Sets up the UI, creating the controls and layout of buttons, as well as setting up listeners.
     */
    private void createUI() {
        createControls();
        layoutControls();
        registerListeners();
    }

    /**
     * Creates the initial buttons and their images, with descriptions, max/min values for sliders etc.
     * All methods in createControls() are self explanatory...
     */
    private void createControls() {
        timeLabel = new JLabel("hh:mm:ss");
        timeLabel.setOpaque(false);
        timeLabel.setForeground(Color.WHITE);

        positionSlider = new JSlider();
        positionSlider.setOpaque(false);
        positionSlider.setMinimum(0);
        positionSlider.setMaximum(1000);
        positionSlider.setValue(0);
        positionSlider.setToolTipText("Position");

        chapterLabel = new JLabel("00/00");
        chapterLabel.setOpaque(false);
        chapterLabel.setForeground(Color.WHITE);

        img = new ImageIcon("resources/buttons/rewind.png");
        rewindButton = new JButton();
        rewindButton.setIcon(img);
        rewindButton.setToolTipText("Skip back");
        rewindButton.setPreferredSize(new Dimension(45, 45));
        rewindButton.setBorderPainted(false); 
        rewindButton.setContentAreaFilled(false); 
        rewindButton.setFocusPainted(false);

        img1 = new ImageIcon("resources/buttons/stop.png");
        stopButton = new JButton();
        stopButton.setIcon(img1);
        stopButton.setToolTipText("Stop");
        stopButton.setPreferredSize(new Dimension(45, 45));
        stopButton.setBorderPainted(false); 
        stopButton.setContentAreaFilled(false); 
        stopButton.setFocusPainted(false);

        img3 = new ImageIcon("resources/buttons/play.png");
        img2 = new ImageIcon("resources/buttons/pause.png");
        playButton = new JButton();
        playButton.setIcon(img3);
        playButton.setToolTipText("Play");
        playButton.setPreferredSize(new Dimension(45, 45));
        playButton.setBorderPainted(false); 
        playButton.setContentAreaFilled(false); 
        playButton.setFocusPainted(false);

        img4 = new ImageIcon("resources/buttons/fastforward.png");
        fastForwardButton = new JButton();
        fastForwardButton.setIcon(img4);
        fastForwardButton.setToolTipText("Skip forward");
        fastForwardButton.setPreferredSize(new Dimension(45, 45));
        fastForwardButton.setBorderPainted(false); 
        fastForwardButton.setContentAreaFilled(false); 
        fastForwardButton.setFocusPainted(false);

        volumeSlider = new JSlider();
        volumeSlider.setOpaque(false);
        volumeSlider.setOrientation(JSlider.HORIZONTAL);
        volumeSlider.setMinimum(LibVlcConst.MIN_VOLUME);
        volumeSlider.setMaximum(LibVlcConst.MAX_VOLUME);
        volumeSlider.setPreferredSize(new Dimension(100, 40));
        volumeSlider.setToolTipText("Change volume");

        fullScreenButton = new JButton();
        fullScreenButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icons/image.png")));
        fullScreenButton.setToolTipText("Toggle full-screen");

        img5 = new ImageIcon("resources/buttons/loop.png");
        loop = new JButton();
        loop.setIcon(img5);
        loop.setToolTipText("Loop video");
        loop.setPreferredSize(new Dimension(45, 45));
        loop.setBorderPainted(false); 
        loop.setContentAreaFilled(false); 
        loop.setFocusPainted(false);

        loopLabel.setOpaque(false);
        loopLabel.setForeground(Color.WHITE);
    }

    /**
     * Sets up the layout for the control panel.
     */
    private void layoutControls() {
        setBorder(new EmptyBorder(4, 4, 4, 4));
        setLayout(new BorderLayout());

        JPanel positionPanel = new JPanel();
        positionPanel.setOpaque(false);
        positionPanel.setLayout(new GridLayout(1, 1));
        positionPanel.add(positionSlider);

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BorderLayout(8, 0));
        topPanel.add(timeLabel, BorderLayout.WEST);
        topPanel.add(positionPanel, BorderLayout.CENTER);
        topPanel.add(chapterLabel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
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
     * Broken out position setting, handles updating mediaPlayer when slider is moved.
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

    /**
     * Updates the state of the UI if the media player has changed state at all.
     */
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

    /**
     * Skips to the given time in the media then updates the UI to show this.
     * @param skipTime
     */
    private void skip(int skipTime) {
        // Only skip time if can handle time setting
        if(mediaPlayer.getLength() > 0) {
            mediaPlayer.skip(skipTime);
            updateUIState();
        }
    }

    /**
     * Adds all the listeners for each of the control panel elements which require them.
     */
    private void registerListeners() {
        // Add volume listener.
        mediaPlayer.addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void playing(MediaPlayer mediaPlayer) {
                updateVolume(mediaPlayer.getVolume());
            }
        });

        // Add position slider listener
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

        // add listener to rewind button
        rewindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                skip(-SKIP_TIME_MS);
            }
        });

        // add listener to stop button
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.stop();
                playButton.setIcon(img3);
            }
        });

        // add listener to play button
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

        // Add listener to fast forward button.
        fastForwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                skip(SKIP_TIME_MS);
            }
        });

        // Add listener to volume slider.
        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                //        if(!source.getValueIsAdjusting()) {
                mediaPlayer.setVolume(source.getValue());
                //        }
            }
        });

        // Add listener for toggling full screen (NOTE fullscreen not working currently.)
        fullScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediaPlayer.toggleFullScreen();
            }
        });

        // Add listener for looping.
        loop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loopOn==false){
                    loopOn=true;
                    mediaPlayer.setRepeat(true);
                    loopLabel.setText("Loop On");
                }
                else{
                    loopOn=false;
                    mediaPlayer.setRepeat(false);
                    loopLabel.setText("Loop Off");
                }
            }
        });
    }

    /**
     * Updates the time slider which shows how far through the media is.
     *
     */
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

    /**
     * Updates the time shown on the time label.
     * @param millis
     */
    private void updateTime(long millis) {
        String s = String.format(
                "%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), 
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
                );
        timeLabel.setText(s);
    }

    /**
     * updates the position of the slider so it moves with the media.
     * @param value
     */
    private void updatePosition(int value) {
        positionSlider.setValue(value);
    }

    /**
     * updates the chapter label shown when the chapter changes in the media player.
     * @param chapter
     * @param chapterCount
     */
    private void updateChapter(int chapter, int chapterCount) {
        String s = chapterCount != -1 ? (chapter+1) + "/" + chapterCount : "-";
        chapterLabel.setText(s);
        chapterLabel.invalidate();
        validate();
    }

    /**
     * set new volume for medis player.
     * @param value
     */
    private void updateVolume(int value) {
        volumeSlider.setValue(value);
    }

    /**
     * sets the play button icon
     */
    public void setPlayButton(){
        playButton.setIcon(img3);
    }

    /**
     * sets the pause button icon.
     */
    public void setPauseButton(){
        playButton.setIcon(img2);
    }

}
