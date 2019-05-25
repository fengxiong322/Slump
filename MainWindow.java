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
  int size;

  public MainWindow(){
    super("The Slump");
    curScreen = "menu";
    size = Math.min((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight(), (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth())-10;//finds the largest size it can be
    setSize(size, size);//Sets the size
    addMouseListener(new MouseAdapter() {//Allows for mouse interaction
      public void mousePressed(MouseEvent me){
        int x = me.getX();
        int y = me.getY();
        if(curScreen.equals("menu")){//When the screen is on menu
          if(x>290 && y> 130 && x < 623 && y > 262)
            newGame();
        }else if(curScreen.equals("level")){//When the screen is on level
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
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        g.drawImage(background, 0, 0, size, size, null);
      } catch (IOException e) {
      }
    }else if(curScreen.equals("level")){
    }
  }

  public static void main(String[]args){
    new MainWindow();
  }
  
}
