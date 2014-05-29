package musicPlayerModule;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.*;
    
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

        static public String filePath;
        static private final String newline = "\n";
        //static private boolean lockOpenPlaylistButton = false;
        JButton openButton;
        JTextArea log;
        JFileChooser fc;
        int panelWidth;
        int panelHeight;
        int buttonWidth = 100;
        int buttonHeight = 50;
        
           public FileChooser(String initialisedFilePath) {
                super.setLayout(null);
                setInitialFilePath(initialisedFilePath);
                //Create the log first, because the action listeners
                //need to refer to it.
                log = new JTextArea(5,20);
                //log.setLayout(null);
                //log.setBounds(0, 100, 50, 50);
                
                log.setMargin(new Insets(5,5,5,5));
                log.setEditable(false);
                //JScrollPane logScrollPane = new JScrollPane(log);

                //Create a file chooser
                fc = new JFileChooser();

                //Uncomment one of the following lines to try a different
                //file selection mode.  The first allows just directories
                //to be selected (and, at least in the Java look and feel,
                //shown).  The second allows both files and directories
                //to be selected.  If you leave these lines commented out,
                //then the default mode (FILES_ONLY) will be used.
                //
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

                //Create the open button.  We use the image from the JLF
                //Graphics Repository (but we extracted it from the jar).
                ImageIcon buttonIcon = new ImageIcon("resources/buttons/openList.png");
                JButton button = new JButton();
                openButton = setUpButtonImage(button, "resources/buttons/openList.png", buttonWidth, buttonHeight);
                
                //openButton = new JButton("Choose a playlist directory", buttonIcon);
                openButton.addActionListener(this);
                //openButton.setBounds(0, 200, 30, 30);

                //For layout purposes, put the buttons in a separate panel
                //JPanel buttonPanel = new JPanel(); //use FlowLayout
                //buttonPanel.setLayout(null);
                //buttonPanel.setBounds(0, 0, 100, 100);
                //buttonPanel.add(openButton);

                //Add the buttons and the log to this panel.
                //add(buttonPanel);
                //add(logScrollPane, BorderLayout.CENTER);
            }
           
           public JButton getButton() {
             
               System.out.println("here " + openButton.getBounds());
               return openButton;
           }
           
           public int getButtonWidth() {
               return buttonWidth;
           }
        
           public int getButtonHeight() {
               return buttonHeight;
           }
           
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
           

//      public FileChooser(String initialisedFilePath, int widthOfPanel, int heightOfPanel) {
//          super.setLayout(null);
//          panelHeight = heightOfPanel;
//          panelWidth = widthOfPanel;
//          setInitialFilePath(initialisedFilePath);
//          //Create the log first, because the action listeners
//          //need to refer to it.
//          log = new JTextArea(5,20);
//          log.setMargin(new Insets(5,5,5,5));
//          log.setEditable(false);
//          //JScrollPane logScrollPane = new JScrollPane(log);
//
//          //Create a file chooser
//          fc = new JFileChooser();
//          fc.setLayout(null);
//          fc.setBounds(0, 0, panelWidth, panelHeight);
//
//          //Uncomment one of the following lines to try a different
//          //file selection mode.  The first allows just directories
//          //to be selected (and, at least in the Java look and feel,
//          //shown).  The second allows both files and directories
//          //to be selected.  If you leave these lines commented out,
//          //then the default mode (FILES_ONLY) will be used.
//          //
//          fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//          //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//
//          //Create the open button.  We use the image from the JLF
//          //Graphics Repository (but we extracted it from the jar).
//          openButton = new JButton("Choose a playlist directory",
//                                   createImageIcon("images/Open16.gif"));
//          openButton.addActionListener(this);
//          openButton.setBounds(0, 0, panelWidth, (int) (panelHeight*0.15));
//
//          //For layout purposes, put the buttons in a separate panel
//          JPanel buttonPanel = new JPanel(); //use FlowLayout
//          buttonPanel.setLayout(null);
//          buttonPanel.add(openButton);
//
//          //Add the buttons and the log to this panel.
//          add(buttonPanel);
//          //add(logScrollPane, BorderLayout.CENTER);
//      }

        private void setInitialFilePath(String initialisedFilePath) {
            filePath = initialisedFilePath;     
        }

        public void actionPerformed(ActionEvent e) {
            System.out.println("RE? ");
            if(!LockedPlaylistValueAccess.lockedPlaylist) {
            //Handle open button action.
                if (e.getSource() == openButton) {
                
                    
                    System.out.println("HERE???" + LockedPlaylistValueAccess.lockedPlaylist);
                    int returnVal = fc.showOpenDialog(FileChooser.this);
    
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        System.out.println("INHERE? ");
                        File file = fc.getSelectedFile();
                        filePath = file.getAbsolutePath();
                      //  //This is where a real application would open the file.
                      //  log.append("Opening: " + file.getName() + "." + newline);
                    } else {
                      //  log.append("Open command cancelled by user." + newline);
                    }
                    log.setCaretPosition(log.getDocument().getLength());
                }
           }
        }
        
        public void lockTheOpenButton(boolean trueOrFalse) {
            System.out.println("in method 2 " + trueOrFalse);
            LockedPlaylistValueAccess.lockedPlaylist = trueOrFalse;
            System.out.println(LockedPlaylistValueAccess.lockedPlaylist + " EHEHHEEHEHEH");
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
        
        public String getNewFilePath() {
            return filePath;
        }

    }

