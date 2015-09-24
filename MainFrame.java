package com.example.main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.SQLException;



public class MainFrame{
   
    Game game = new Game();
   
    //Skapar grafiska komponenter för huvudfönstret:
    JFrame frame = new JFrame();
    JMenuBar menuBar = new JMenuBar();
   
    JMenu fileMenu = new JMenu("Arkiv");
    JMenuItem newMenuItem = new JMenuItem("Nytt spel");
    JMenuItem highScoresMenuItem = new JMenuItem("Topplista");
    JMenuItem exitMenuItem = new JMenuItem("Avsluta");
   
    JMenu helpMenu = new JMenu("Hjälp");
    JMenuItem aboutMenuItem = new JMenuItem("Om");
   
    static JButton bugButton = new JButton("BUG!!!");
   
   
   
    public MainFrame(){
       
            
        //Bygger det grafiska för huvudfönstret:
       
        fileMenu.add(newMenuItem);                           
        fileMenu.add(highScoresMenuItem);
        fileMenu.add(exitMenuItem);

        helpMenu.add(aboutMenuItem);


        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
       
        frame.add(bugButton);
       
        frame.setTitle("Click the bugs to catch them!");
        frame.setJMenuBar(menuBar);
        frame.setLayout(new FlowLayout());
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
       
        bugButton.setVisible(false);
       
       
       
            

        //Skapar funktionalitet för de olika menyvalen:
        newMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                        game.playGame();
                       
            }
        });
       
       
        highScoresMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    HighScores highScores = new HighScores();
               
                } catch (SQLException e1) {
                    e1.printStackTrace();
               
                }
            }
        });
     
       
        exitMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
               
            }
        });

       
        aboutMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            AboutWindow aboutWindow = new AboutWindow();
           
            }
        });
               
       
        //Funktionalitet bakom bugButton (Buggarna man klickar på i spelet)
        bugButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                game.bugClicked();
                                                                
                    }   
                });

    }    
} 
