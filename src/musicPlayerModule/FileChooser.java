package musicPlayerModule;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

public class FileChooser extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    static public String filePath;
    JButton openButton;
    JFileChooser fc;
    int panelWidth;
    int panelHeight;
    int buttonWidth = 100;
    int buttonHeight = 50;

    /**
     * Instantiate a new filechooser object. Allows the user
     * to choose directories from a dialog and get their path,
     * for use in java.
     * @param initialisedFilePath
     */
    public FileChooser(String initialisedFilePath) {
        super.setLayout(null);
        setInitialFilePath(initialisedFilePath);

        //Create a file chooser
        fc = new JFileChooser();

        // Set filechooser to only show directories, as only they will be required.
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Set image icon to open playlist button.
        JButton button = new JButton();
        openButton = setUpButtonImage(button, "resources/buttons/openList.png", buttonWidth, buttonHeight);
        openButton.addActionListener(this);
    }

    /**
     * Get the button that opens the playlist.
     * @return
     */
    public JButton getButton() {

        System.out.println("here " + openButton.getBounds());
        return openButton;
    }

    /**
     * get the width of the button in pixels.
     * @return
     */
    public int getButtonWidth() {
        return buttonWidth;
    }

    /**
     * get the height of the button in pixels.
     * @return
     */
    public int getButtonHeight() {
        return buttonHeight;
    }

    /**
     * Sets up a JButton to have an image icon.
     * @param button
     * @param image- Path to the image you wish to be the icon.
     * @param width- width you would like image to be
     * @param height- height you would like image to be.
     * @return
     */
    public JButton setUpButtonImage(JButton button, String image, int width, int height){
        BufferedImage choosePageButtonImage;
        try{
            choosePageButtonImage = ImageIO.read(new File(image));
            Image scaledButton = choosePageButtonImage.getScaledInstance(width,height,java.awt.Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledButton));
        }catch (IOException ex){

        }
        return button;
    }

    /**
     * Provide an initialised filepath for the playlist directory.
     * @param initialisedFilePath
     */
    private void setInitialFilePath(String initialisedFilePath) {
        filePath = initialisedFilePath;     
    }

    /**
     * Action listener for the button. opens the browser dialog.
     */
    public void actionPerformed(ActionEvent e) {
        // only open the dialog if the playlist is not locked.
        if(!LockedPlaylistValueAccess.lockedPlaylist) {
            if (e.getSource() == openButton) {
                int returnVal = fc.showOpenDialog(FileChooser.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    filePath = file.getAbsolutePath();
                } else {
                    System.out.println("Open command cancelled by user.");
                }
            }
        }
    }

    /**
     * Sets the open button to locked. not playlist can be chosen if
     * this has been set true.
     * @param trueOrFalse
     */
    public void lockTheOpenButton(boolean trueOrFalse) {
        LockedPlaylistValueAccess.lockedPlaylist = trueOrFalse;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FileChooser.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Opens the dialog box, which user can then browse files from.
     * @return
     */
    public Component openDialog() {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
            }
        });
        Component jFrameItems = new FileChooser(filePath);
        return jFrameItems; 
    }

    /**
     * gets the chosen filepath.
     * @return
     */
    public String getNewFilePath() {
        return filePath;
    }

}

