package com.example.main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class AboutWindow{
   
    //Skapar grafiska komponenter för Om-fönstret:
   
    JFrame frame = new JFrame();
    JMenuBar menuBar = new JMenuBar();
   
    JMenu fileMenu = new JMenu("Arkiv");
    JMenuItem exitMenuItem = new JMenuItem("Stäng");
   
    JLabel aboutTextLabel = new JLabel("Catch the bugs av Carl-Henrik Persson");
   
   
   
    public AboutWindow(){
       
       
        //Bygger det grafiska för Om-fönstret:
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);    
        frame.add(aboutTextLabel);
       
        frame.setTitle("Om");
        frame.setJMenuBar(menuBar);
        frame.setSize(300, 200);
        frame.setResizable(false);
        frame.setVisible(true);
       
       
       
             
        //Skapar funktionalitet för menyvalet:
        exitMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
               
            }
        });

       
    }
}

