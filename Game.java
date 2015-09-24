package com.example.main;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.Random;
import javax.swing.*;



public class Game{
   
    int score = 0;
    int lowestScore = 0;
    String name = "";
   
    int x = 0;
    int y = 0;   
    Random random = new Random();
 
       
    Timer timer1;
    Timer timer2;
    int bugdelay = 1000;
   
   
   
    // Metod som startar ett nytt spel
    public void playGame(){
                
        MainFrame.bugButton.setVisible(true);
        changeLocation();
        timers();
           
    }
 
   
   
    // Metod som körs när spelaren klickar på en bugg. Metoden ökar spelarens poäng med ett och flyttar buggen till en ny position.
    public void bugClicked(){
       
        score++;
        changeLocation();
    }
   
   
    // Metod som flyttar bugg till en ny slumpvald position.
    public void changeLocation(){
       
        y = random.nextInt((430 - 60) + 1) + 10;
        x = random.nextInt((430 - 60) + 1) + 10;
        MainFrame.bugButton.setLocation(x, y);

    }

   
     // Metod som körs när en spelomgång är över. Frågar efter namn och lägger sedan till angivet namn och poäng i databasen som topplistan hämtas från.
     // Gömmer även bugButton för att avsluta rundan.
    public void gameEnd() throws SQLException{
       
    MainFrame.bugButton.setVisible(false);
       
          lowestScore = HighScores.getLowestScore();
                 
          if(score > lowestScore){   
        	  name = JOptionPane.showInputDialog(null, "Grattis, du kom in på toplistan med " + score + " poäng! Vad heter du? : ",
                "Catch the bugs", 1);
                  if(name == null || name == ""){
                	  name = "Anonym";
                  }
                                   
                  
                  HighScores.addToList(name, score);
                  score = 0;
                      
            }
          JOptionPane.showMessageDialog(null, "Tack för att du spelade " + name + "!", "Catch the bugs", 1);
    }
    
   
   
    // Metod som håller koll på omgångens längd samt ser till att buggen flyttas varje sekund.
    public void timers(){
       
        timer1 = new Timer(bugdelay, new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            changeLocation();
        }
        });
       
        timer1.start();
           
       
        timer2 = new Timer(60000, new ActionListener(){
        public void actionPerformed(ActionEvent e) {
           
            timer1.stop();
            timer2.stop();
           
            try {
                gameEnd();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        });
       
        timer2.start();
    }
       
  }
