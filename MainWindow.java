import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;

public class MainWindow extends JFrame{
  Game gameScreen;
  String curScreen;
  
  public MainWindow(){
    super("The Slump");
    curScreen = "menu";
    setSize(400, 400);
    addMouseListener(new MouseAdapter() { 
      public void mousePressed(MouseEvent me){
        int x = me.getX();
        int y = me.getY();
        if(curScreen.equals("menu")){
          System.out.println(x+ " " + y);
          if(x<100)
            openLevel();
        }
      } 
    });
    setLocationRelativeTo(null);
    setVisible(true);
    repaint();
  }
  
  public void openMenu(){

  }
  
  public void openLevel(){
    curScreen = "level";
    repaint();
  }
  
  public void openHighScore(){

  }

  public void resumeGame(){
    add(gameScreen);
  }

  public void newGame(){
    gameScreen = new Game(1);
    add(gameScreen);
  }

  public void paint(Graphics g){
    g.setColor(new Color(0, 0, 0));
    g.fillRect(0, 0, getWidth(), getHeight());
    if(curScreen.equals("menu")){
      try {
        BufferedImage background = ImageIO.read(new File("LogoMakr_7PqrnC.png"));
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
