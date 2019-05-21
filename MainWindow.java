import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;

public class MainWindow extends JFrame implements ExitListener{
  Game gameScreen;
  String curScreen;
  
  public MainWindow(){
    super("The Slump");
    curScreen = "menu";
    setSize(943, 943);
    addMouseListener(new MouseAdapter() { 
      public void mousePressed(MouseEvent me){
        int x = me.getX();
        int y = me.getY();
        if(curScreen.equals("menu")){
          if(x>290 && y> 130 && x < 623 && y > 262)
            newGame();
        }else if(curScreen.equals("level")){
          openLevel();
        }else if(curScreen.equals("highScore")){
          openHighScore();
        }else if(curScreen.equals("resume")){
          resumeGame();
        }else if(curScreen.equals("newGame")){
         newGame();
        }
      } 
    });
    setLocationRelativeTo(null);
    setVisible(true);
    repaint();
  }
  
  public void openMenu(){
    curScreen = "menu";
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

  public void newGame(){
    gameScreen = new Game(1, this);
    add(gameScreen);
    gameScreen .requestFocusInWindow(); 
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
        g.drawImage(background, 0, 0, null);
      } catch (IOException e) {
      }
    }else if(curScreen.equals("level")){
    }
  }

  public static void main(String[]args){
    new MainWindow();
  }
  
}
