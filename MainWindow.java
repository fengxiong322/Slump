import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;
/**
* @author Feng, Michael
* @version 2.0
* Total time spent Feng: 1 hour
* Total time spend Michael: 5 hours
* Modifications: May 21, 2019, Feng Xiong, Total time: 1 hour
* Added auto resizing window
* Modifications: May 24, 2019, Michael Zhou, Total time: 2 hours
* add navigation to level, quit, instruction, and highscore screens
* improved navigation between all screens
* added instructions image
* Modifications: May 30, 2019, Michael Zhou, Total time: 0.5 hours
* added images for quit screen and highscore screen
*/
public class MainWindow extends JFrame implements ExitListener{
  Game gameScreen;
  HighScore highScore;
  String curScreen;
  int size;

  //May 10, Created the constructor with its general structure, Feng
  public MainWindow(){
    super("The Slump");
    curScreen = "menu";
    //size = Math.min((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight(), (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth())-10;//finds the largest size it can be
    setSize(811, 821);//Sets the size //May 24, modified to set a defualt screen size so cordinates do not change, Michael Zhou
    setResizable(false);
    addMouseListener(new MouseAdapter() {//Allows for mouse interaction
      public void mousePressed(MouseEvent me){
        int x = me.getX();
        int y = me.getY();
        //added coord detection for testing
        System.out.println(x + " " + y);
        //added nagviagion between screens - Michael Zhou, May 24, 2019 5mins
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
          if(x>249 && y> 372 && x < 559 && y < 483)
            newGame(2);
          if(x>250 && y> 522 && x < 559 && y < 635)
            newGame(3);
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

  /**
  * Set screen to menu
  */
  public void openMenu(){//Implemented open menu, Feng May 10 1 min
    curScreen = "menu";
    repaint();
  }

  /**
  * Set screen to quit screen
  */
  public void openQuit(){//Implemented open quit, Feng May 10 1 min
    curScreen = "quit";
    repaint();
  }

  /**
  * Set screen to instructions
  */
  public void openIns () {//Implemented open instructions, Feng May 10 1 min
    curScreen = "instructions";
    repaint();
  }

  /**
  * Set screen to level selection
  */
  public void openLevel(){//Implemented open level selection, Feng May 10 1 min
    curScreen = "level";
    repaint();
  }
  /**
  * Set screen to highscores
  */
  public void openHighScore(){//Implemented open highscores, Feng May 10 1 min
    curScreen = "highScore";
    highScore = new HighScore(this);
    add(highScore);
    highScore.requestFocusInWindow();
    pack();
  }

  /**
  * Set screen to resumed game
  */
  public void resumeGame(){//Implemented resumeGame, Feng May 10 1 min
    add(gameScreen);
  }

  /**
  * Set screen to a level
  */
  public void newGame(int level){//Implemented new level, Feng May 10 1 min
    curScreen = "game";
    gameScreen = new Game(level, this);
    add(gameScreen);
    gameScreen.requestFocusInWindow();
    pack();
  }

  /**
  * Set screen to menu when exiting
  */
  public void exit(){//Implemented runs exit from the Exit Listener, Feng May 10 1 min
    if(curScreen.equals( "game"))
      remove(gameScreen);
    else if(curScreen.equals("highscore"))
      remove(highScore);
    setSize(811, 821);
    curScreen = "menu";
  }

  /**
  * Set the background to the correct image
  * @param g The graphics for the background
  * Changelog
  * Michael - added the if statements to change between all the screens as well as imported the images 10 mins, May 24, 2019
  */
  public void paint(Graphics g){//Implemented paint, Feng May 10 30 min
    //g.setColor(new Color(0, 0, 0));
    //g.fillRect(0, 0, getWidth(), getHeight());
    if(curScreen.equals("menu")){ //michael - added image and option May 17, 2019 5mins
      try {
        BufferedImage background = ImageIO.read(new File("Screens/Menu.png"));
        g.drawImage(background, 0, 10, 811, 811, null);
      } catch (IOException e) {
      }
    }else if(curScreen.equals("level")){ //michael - added image and option   May 24, 2019 5 mins
      try {
        BufferedImage background = ImageIO.read(new File("Screens/Levels.png"));
        g.drawImage(background, 0, 10, 811, 811, null);
      } catch (IOException e) {
      }
    }else if(curScreen.equals("instructions")){ //michael - added image and option  May 24, 2019 5 mins
      try {
        BufferedImage background = ImageIO.read(new File("Screens/Instructions.png"));
        g.drawImage(background, 0, 20, 811, 811, null);
      } catch (IOException e) {
      }
    }else if(curScreen.equals("quit")){ //michael - added image and option  May 24, 2019 5 mins
      try {
        BufferedImage background = ImageIO.read(new File("Screens/Quit.jpg"));
        g.drawImage(background, 0, 20, 811, 811, null);
      } catch (IOException e) {
      }
      int dialogButton = JOptionPane.YES_NO_OPTION;
      int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you would like to quit?","Warning",dialogButton);
      if(dialogResult == JOptionPane.YES_OPTION)
      dispose();
    }else if(curScreen.equals("highScore")){ //michael - added image and option   May 24, 2019 5 mins
      try {
        BufferedImage background = ImageIO.read(new File("Screens/Highscores.jpg"));
        g.drawImage(background, 0, 20, 811, 811, null);
      } catch (IOException e) {
      }
    }
  }

  /**
  * Run the game
  */
  public static void main(String[]args){
    new MainWindow();
  }

}
