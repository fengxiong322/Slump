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
 * @author Feng Xiong, Michael Zhou
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
public class MainWindow extends JFrame implements ExitListener, ActionListener {
  private Game gameScreen;
  private HighScore highScore;
  private String curScreen;
  private int size;
  private int hover;
  private BufferedImage canvas;
  private boolean settingUp;
  private float alpha;
  private Timer timer;
  
  //May 10, Created the constructor with its general structure, Feng
  public MainWindow(){
    super("The Slump");
//    curScreen = "intro";
//    TestPane t = new TestPane();
//    add(t);
//    pack();
//    remove (t);
    settingUp = true;
    timer = new Timer(20, this);
    timer.setInitialDelay(50);
    timer.start();
    curScreen = "";
    canvas = new BufferedImage(811, 821, BufferedImage.TYPE_INT_RGB);
    setSize(811, 821);//Sets the size May 24, modified to set a default screen size so cordinates do not change, Michael Zhou
    setResizable(false);
    addMouseMotionListener(new MouseMotionListener(){
      public void mouseDragged (MouseEvent e)
      {}
      public void mouseMoved (MouseEvent e)
      {
        int x = e.getX ();
        int y = e.getY ();
        boolean overButton = false;
        if(curScreen.equals("menu")){
          if(x>201 && y> 224 && x < 598 && y < 320)
          {
            hover = 1;
            overButton = true;
          }
          if(x>238 && y> 340 && x < 548 && y < 437)
          {
            hover = 2;
            overButton = true;
          }        
          if(x>126 && y> 458 && x < 685 && y < 551)
          {
            hover = 3;
            overButton = true;
          }         
          if(x>151 && y> 575 && x < 660 && y < 671)
          {
            hover = 4;
            overButton = true;
          } 
          if(x>274 && y> 714 && x < 496 && y < 799)
          {
            hover = 5;
            overButton = true;
          }
          if (!overButton)
            hover = 0;
        }
        if(curScreen.equals("level") || curScreen.equals("resume")){
          if(x>49 && y> 243 && x < 371 && y < 360)
          { 
            hover = 1;
            overButton = true;
          }
          if(x>426 && y> 242 && x < 747 && y < 362)
          { 
            hover = 2;
            overButton = true;
          }
          if(x>50 && y> 390 && x < 373 && y < 510)
          { 
            hover = 3;
            overButton = true;
          }
          if(x>424 && y> 388 && x < 733 && y < 501)
          { 
            hover = 4;
            overButton = true;
          }
          if(x>237 && y> 541 && x < 549 && y < 647)
          { 
            hover = 5;
            overButton = true;
          }
          if(x>288 && y> 685 && x < 512 && y < 783)
          { 
            hover = 6;
            overButton = true;
          }
          if (!overButton)
            hover = 0;
        }
        repaint();
      }
      
    });
    addMouseListener(new MouseAdapter() {//Allows for mouse interaction
      public void mousePressed(MouseEvent me){
        int x = me.getX();
        int y = me.getY();
        //added coord detection for testing
        System.out.println(curScreen);
        //added nagviagion between screens - Michael Zhou, May 24, 2019 5mins
        if(curScreen.equals("menu")){//When the screen is on menu
          if(x>201 && y> 224 && x < 598 && y < 320)
          {
            if(gameScreen != null){
              curScreen = "resume";
              repaint();
            }
          }
          if(x>238 && y> 340 && x < 548 && y < 437)
            openLevel();           
          if(x>126 && y> 458 && x < 685 && y < 551)
            openIns1 ();           
          if(x>151 && y> 575 && x < 660 && y < 671)
            openHighScore ();       
          if(x>274 && y> 714 && x < 496 && y < 799)
            openQuit ();
        }else if(curScreen.equals("level")){//When the screen is on level
          if(x>49 && y> 243 && x < 371 && y < 360)
            newGame(1);
          if(x>426 && y> 242 && x < 747 && y < 362)
            newGame(2);
          if(x>50 && y> 390 && x < 373 && y < 510)
            newGame(3);
          if(x>424 && y> 388 && x < 733 && y < 501)
            newGame(4);
          if(x>237 && y> 541 && x < 549 && y < 647)
            newGame(5);
          if(x>288 && y> 685 && x < 512 && y < 783) {
            hover = 0;
            openMenu();
          }
        }else if(curScreen.equals("resume")){//When the screen is on level
          if(x>49 && y> 243 && x < 371 && y < 360)
            resumeGame(1);
          if(x>426 && y> 242 && x < 747 && y < 362)
            resumeGame(2);
          if(x>50 && y> 390 && x < 373 && y < 510)
            resumeGame(3);
          if(x>424 && y> 388 && x < 733 && y < 501)
            resumeGame(4);
          if(x>237 && y> 541 && x < 549 && y < 647)
            resumeGame(5);
          if(x>288 && y> 685 && x < 512 && y < 783) {
            hover = 0;
            openMenu();
          }
        }
        else if(curScreen.equals("highScore")){
          openHighScore();
        }
        else if (curScreen.equals ("instructions1")) {
          if(x>66 && y> 721 && x < 180 && y < 794)
            openMenu ();
          if (x> 635 && y> 722 && x < 779 && y < 789)
            openIns2 ();
        }
        else if (curScreen.equals ("instructions2")) 
        {
          if(x>51 && y> 718 && x < 167 && y < 783)
            openIns1 ();
          if (x> 639 && y> 704 && x < 772 && y < 780)
            openMenu ();
        }
      }
    });
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    repaint();
  }
  
  
  
  public void intro ()
  {
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
  public void openIns1 () {//Implemented open instructions, Feng May 10 1 min
    curScreen = "instructions1";
    repaint();
  }
  
  public void openIns2 () {//Implemented open instructions, Feng May 10 1 min
    curScreen = "instructions2";
    repaint();
  }
  
  /**
   * Set screen to level selection
   */
  public void openLevel(){//Implemented open level selection, Feng May 10 1 min
    curScreen = "level";
    gameScreen = null;
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
  public void resumeGame(int level){//Implemented resumeGame, Feng May 10 1 min
    curScreen = "resume";
    if(level == 1){
      gameScreen.level1();
    }else if(level == 2){
      gameScreen.level2();
    }else if(level == 3){
      gameScreen.level3();
    }else if(level == 4){
      gameScreen.level4();
    }else if(level == 5){
      gameScreen.level5();
    }
    else 
    {
      JOptionPane.showMessageDialog(null, "alert", "No game has been started", JOptionPane.ERROR_MESSAGE);
      curScreen = "menu";
    }
    add(gameScreen);
    gameScreen.requestFocusInWindow();
    repaint();
  }

  public void setup(Graphics g){
    try{
      BufferedImage image;
      if(alpha < 1f)
        image = ImageIO.read(new File("Screens/intro1.png"));
      else
        image = ImageIO.read(new File("Screens/intro2.png"));
      
      BufferedImage screen = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
    Graphics g1 = screen.getGraphics();
      Graphics2D g2d = (Graphics2D) g.create();
      g2d.setComposite(AlphaComposite.SrcOver.derive(alpha%1));
    g1.setColor(new Color(255, 255, 255));
    g1.fillRect(0, 0, 800, 800);
    g1.drawImage(image, 0, 0, 800, 800, null);
    g2d.drawImage(screen, 0, 0, null);
  }catch(IOException e){}
  }

  public void actionPerformed(ActionEvent evt) {
    alpha = alpha + 0.01f;
    if(alpha > 2f){
      timer.stop();
      timer = null;
      settingUp = false;
      curScreen = "menu";
      repaint();
    }else{
      timer.restart();
      repaint();
    }
  }
  
  /**
   * Set screen to a level
   */
  public void newGame(int level){//Implemented new level, Feng May 10 1 min
    if (gameScreen != null){
      System.out.println(gameScreen ==null);
      int dialogButton = JOptionPane.YES_NO_OPTION;
      int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you would like to start a new game? Your previous game data will be lost!","Warning",dialogButton);
      if(dialogResult != JOptionPane.YES_OPTION){
        return;
    }
  }
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
    if(curScreen.equals( "game") || curScreen.equals("resume")){
      remove(gameScreen);
      if(gameScreen.getEnd()){
        curScreen = "menu";
        gameScreen = null;
      }else
        curScreen = "resume";
    }
    else if(curScreen.equals("highScore")){
      remove(highScore);
      highScore = null;
      curScreen = "menu";
    }
    setSize(811, 821);
    repaint();
  }
  
  /**
   * Set the background to the correct image
   * @param g The graphics for the background
   * Changelog
   * Michael - added the if statements to change between all the screens as well as imported the images 10 mins, May 24, 2019
   */
  public void paint(Graphics g){//Implemented paint, Feng May 10 30 min
    if(settingUp){
      setup(g);
      return;
    }
    Graphics g2 = canvas.getGraphics();
    BufferedImage background = null;
    try {
      BufferedImage cave = ImageIO.read(new File("Screens/cave.png"));
      if(curScreen.equals("menu")){ //michael - added image and option May 17, 2019 5mins
        if (hover == 0)
          background = ImageIO.read(new File("Screens/Menu.png"));
        if (hover == 1)
          background = ImageIO.read(new File("Screens/menu1.png"));
        if (hover == 2)
          background = ImageIO.read(new File("Screens/menu2.png"));
        if (hover == 3)
          background = ImageIO.read(new File("Screens/menu3.png"));
        if (hover == 4)
          background = ImageIO.read(new File("Screens/menu4.png"));
        if (hover == 5)
          background = ImageIO.read(new File("Screens/menu5.png"));
        
        g2.drawImage(cave, 0, 20, 811, 821, 0,0,cave.getWidth(), cave.getHeight(), null);
        g2.drawImage(background, 0, 20, 811, 821,0,0,background.getWidth(), background.getHeight(), null);
        
      }else if(curScreen.equals("level") || curScreen.equals("resume")){ //michael - added image and option   May 24, 2019 5 mins
        if (hover == 0)
          background = ImageIO.read(new File("Screens/Levels.png"));
        if (hover == 1)
          background = ImageIO.read(new File("Screens/levels1.png"));
        if (hover == 2)
          background = ImageIO.read(new File("Screens/levels2.png"));
        if (hover == 3)
          background = ImageIO.read(new File("Screens/levels3.png"));
        if (hover == 4)
          background = ImageIO.read(new File("Screens/levels4.png"));
        if (hover == 5)
          background = ImageIO.read(new File("Screens/levels5.png"));
        if (hover == 6)
          background = ImageIO.read(new File("Screens/levels6.png"));
        
        g2.drawImage(cave, 0, 20, 811, 821, 0,0,cave.getWidth(), cave.getHeight(), null);
        g2.drawImage(background, 0, 20,811, 821, 0,0,background.getWidth(), background.getHeight(), null);
        if(gameScreen != null){
          g2.setFont(new Font("SansSerif", Font.PLAIN, 20));
          String temp ="Your Current Score: " + gameScreen.getTotalScore();
          g2.drawString(temp, 550, 700);
        }
        
      }else if(curScreen.equals("instructions1")){ //michael - added image and option  May 24, 2019 5 mins
        
        background = ImageIO.read(new File("Screens/Instructions1.png"));
        g2.drawImage(background, 0, 20, 811, 821,0,0,background.getWidth(), background.getHeight(), null);
        
        
      }else if(curScreen.equals("instructions2")){ //michael - added image and option  May 24, 2019 5 mins
        
        background = ImageIO.read(new File("Screens/Instructions2.png"));
        g2.drawImage(background, 0, 20, 811, 821,0,0,background.getWidth(), background.getHeight(), null);
        
      }else if(curScreen.equals("quit")){ //michael - added image and option  May 24, 2019 5 mins
        
        background = ImageIO.read(new File("Screens/Quit.jpg")); //May 30, 2019, Michael Zhou added image
        
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you would like to quit?","Warning",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
          g2.drawImage(background, 0, 20, 811, 821,0,0,background.getWidth(), background.getHeight(), null);
          try{
            Thread.sleep(10000);
          }catch(Exception e){}
          dispose();
        }else{
          curScreen = "menu";
        }
      }else if(curScreen.equals("highScore")){ //michael - added image and option   May 24, 2019 5 mins
        
        background = ImageIO.read(new File("Screens/Highscores.jpg")); //May 30, 2019, Michael Zhou added image
        g2.drawImage(background, 0, 20, 811, 821, 0,0,background.getWidth(), background.getHeight(), null);
        
      }
    } catch (IOException e) {
    }
    g.drawImage(canvas, 0, 0, 811, 821, null);
    g.dispose();
  }
 
  /**
   * Run the game
   */
  public static void main(String[]args){
    //new FadeImage ();
    new MainWindow();
  }
  
}