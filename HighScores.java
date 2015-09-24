package com.example.main;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;


public class HighScores{

    static Connection connection = null;
    static Statement statement = null;
   
    String output = "";
    static int i = 0;
    static int lowestScore = 9999;
   
    //Skapar grafiska komponenter för topplistan:
    JFrame frame = new JFrame();
    JMenuBar menuBar = new JMenuBar();
   
    JMenu fileMenu = new JMenu("Arkiv");
    JMenuItem resetMenuItem = new JMenuItem("Töm topplistan");
    JMenuItem exitMenuItem = new JMenuItem("Stäng");
   
    JTextArea highScoresArea = new JTextArea(getList());
   
   
   
   
   
   
    public HighScores() throws SQLException{
         

        //Bygger det grafiska för topplistan:
       
       



      
        fileMenu.add(resetMenuItem);
        fileMenu.add(exitMenuItem);
       
        menuBar.add(fileMenu); 

        frame.add(highScoresArea);
       
        frame.setTitle("Topplistan!");
        frame.setJMenuBar(menuBar);
        frame.setSize(200, 215);
        frame.setResizable(false);
        frame.setVisible(true);
        
        highScoresArea.setEditable(false);
       
       
       
       
       
       
       
        //Skapar funktionalitet för de olika menyvalen:
        exitMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
               
            }
        });

       
        resetMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                try {
                    clearList();
                   
                } catch (SQLException e1) {
                    e1.printStackTrace();
                   
                }
            }
        });       
    }

   
   
   
   
   
   
   
    //Metod för att hämta Topplistan från databasen
    public String getList() throws SQLException{


        try {
             connection = DriverManager.getConnection("jdbc:mysql://localhost/catchthebugs?user=root");
             statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM highscores ORDER BY score DESC LIMIT 10");
           
             output = "";
          
             while (resultSet.next()) {                 
                 output = output + resultSet.getString("name") +" - " + resultSet.getString("score") + "\n";
                  i++;
                 
                }
        }
       

        catch (SQLException exception){
        }
       
        finally {
            try{
            if (statement != null)
                statement.close();

            if (connection != null)
                connection.close();
            }
           
            catch (SQLException exception) {
                throw exception;
            }
        }
       
        return output;
        }

   
    
    
    
    //Metod för att hämta lägsta poängen i top 10 från databasen
    public static int getLowestScore() throws SQLException{


        try {
             connection = DriverManager.getConnection("jdbc:mysql://localhost/catchthebugs?user=root");
             statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM highscores ORDER BY score DESC LIMIT 10");
           
                       
             while (resultSet.next()) {                 
                 if (lowestScore > resultSet.getInt("score")){
            	 lowestScore = resultSet.getInt("score");
                 }
                  
                 i++;
                 
                }
        }
       

        catch (SQLException exception){
        }
       
        finally {
            try{
            if (statement != null)
                statement.close();

            if (connection != null)
                connection.close();
            }
           
            catch (SQLException exception) {
                throw exception;
            }
        }
       
        return lowestScore;
        }
    
   
   
   
   
   
   
   
    //Metod för att lägga till spelare till topplistan i databasen
    public static void addToList(String name, int score) throws SQLException{

        try {
             connection = DriverManager.getConnection("jdbc:mysql://localhost/catchthebugs?user=root");
             statement = connection.createStatement();
                
                 statement.executeUpdate("INSERT INTO highscores (name, score) VALUES ('" + name + "', " + score + ");");         
        }


        catch (SQLException exception){
        }
        finally {
            try{
            if (statement != null)
                statement.close();

            if (connection != null)
                connection.close();
            }
            catch (SQLException exception) {
                throw exception;
            }
        }
    }
   
   
   
   
   
   
   
   
   
    //Metod för att återställa topplistan i databasen
    public String clearList() throws SQLException{

        try {
             connection = DriverManager.getConnection("jdbc:mysql://localhost/catchthebugs?user=root");
             statement = connection.createStatement();
            
             statement.executeUpdate("DROP TABLE highscores;");
             statement.executeUpdate("CREATE TABLE highscores(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50) NOT NULL, score INT NOT NULL DEFAULT 0);");        
            
             int i = 0;
             while (i < 10) {
                
                 statement.executeUpdate("INSERT INTO highscores (name, score) VALUES ('Evil Bug', 0);");
                                  
                 i++;
             }
            
             highScoresArea.setText(getList());
          }


        catch (SQLException exception){
        }
        finally {
            try{
            if (statement != null)
                statement.close();

            if (connection != null)
                connection.close();
            }
            catch (SQLException exception) {
                throw exception;
            }
        }
        return output;
    }

} 
