import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;

public class MainWindow extends JFrame implements ExitListener{
  Game gameScreen;
  String curScreen;
  int size;
  
  public MainWindow(){
    super("The Slump");
    curScreen = "menu";
    //size = Math.min((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight(), (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth())-10;//finds the largest size it can be
    setSize(811, 821);//Sets the size  
    addMouseListener(new MouseAdapter() {//Allows for mouse interaction         
      public void mousePressed(MouseEvent me){
        int x = me.getX();
        int y = me.getY();
        System.out.println(x + " " + y);
        if(curScreen.equals("menu")){//When the screen is on menu
          if(x>252 && y> 235 && x < 559 && y < 346)
            openLevel();
          if(x>111 && y> 372 && x < 673 && y < 481)
            openIns ();
          if(x>150 && y> 508 && x < 661 && y < 621)
            openHighScore ();
          if(x>288 && y> 685 && x < 509 && y < 784)
            openQuit ();
        }else if(curScreen.equals("level")){//When the screen is on level
          if(x>252 && y> 235 && x < 559 && y < 346)
            newGame(1);
        }else if(curScreen.equals("highScore")){
          openHighScore();
        }else if(curScreen.equals("resume")){
          resumeGame();
        }else if(curScreen.equals("newGame")){
          //newGame();
        }else if (curScreen.equals ("instructions"));
           if(x>361 && y> 709 && x < 487 && y < 786)
            openMenu ();
        }
       
    });
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    repaint();
  }
  
  public void openMenu(){
    curScreen = "menu";
    repaint();
  }
  
  public void openQuit(){
    curScreen = "quit";
    repaint();
  }
  
  public void openIns () {
    curScreen = "instructions";
    repaint();
  }
  
  public void openLevel(){
    curScreen = "level";
    repaint();
  }
  
  public void openHighScore(){
    curScreen = "highScore";
    repaint();
  }
  
  public void resumeGame(){
    add(gameScreen);
  }
  
  public void newGame(int level){
    gameScreen = new Game(level, this);
    add(gameScreen);
    gameScreen.requestFocusInWindow(); 
    pack();
  }
  
  public void exit(){
    remove(gameScreen);
    curScreen = "menu";
  }
  
  public void paint(Graphics g){
    g.setColor(new Color(0, 0, 0));
    g.fillRect(0, 0, getWidth(), getHeight());
    if(curScreen.equals("menu")){
      try {
        BufferedImage background = ImageIO.read(new File("Screens/Menu.png"));
        g.drawImage(background, 0, 10, 811, 811, null);
      } catch (IOException e) {
      }
    }else if(curScreen.equals("level")){
      try {
        BufferedImage background = ImageIO.read(new File("Screens/Levels.png"));
        g.drawImage(background, 0, 10, 811, 811, null);
      } catch (IOException e) {
      }
    }else if(curScreen.equals("instructions")){
      try {
        BufferedImage background = ImageIO.read(new File("Screens/Instructions.png"));
        g.drawImage(background, 0, 20, 811, 811, null);
      } catch (IOException e) {
      }
    }else if(curScreen.equals("quit")){
      dispose();
    }else if(curScreen.equals("highScore")){
      try {
        BufferedImage background = ImageIO.read(new File("Screens/blank.png"));
        g.drawImage(background, 0, 20, 811, 811, null);
      } catch (IOException e) {
      }
    }
  }
  
  public static void main(String[]args){
    new MainWindow();
  }
  
}
