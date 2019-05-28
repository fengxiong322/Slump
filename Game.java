import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

/**
* @author Feng, Michael
* @version 2.0
* Total time spent Feng: 3 hours
* Total time spend Michael: 1.5 hours
*Modifications: May 18, 2019, Feng, Total time: 3 hours
* added the class and basic methods
* Modifications: May 25, 2019, Michael Zhou, Total time: 1 hours
* added a NPC object into level 1 for testing purposes
* modified platform array so it works will all Obstacle objects
* added converstations with NPCs when the player presses down arrow
*/
public class Game extends Canvas implements ActionListener{
 ArrayList<Obstacle> platforms;
 Timer timer;
 boolean gameOver;
 BufferedImage background;
 BufferedImage canvas;
 BufferedImage clear;
 Player player;
 int canvasX;
 int canvasY;
 int moveX;
 int moveY;
 ExitListener el;

 /** COnstructor sets basic values and initalizes arrays and images
 * @param level the specified level
 * @param el the ExitListener
 */
 public Game(int level, ExitListener el){//Created the constructor Feng Xiong 2 hours May 13
  super();
  setSize(400, 400);
  canvasX = 0;
  canvasY = 0;
  addKeyListener(new PlayerListener());
  canvas = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
  clear = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
  platforms = new ArrayList<Obstacle>();
  gameOver = false;
  moveX = 0;
  moveY = 0;
  this.el = el;
  timer = new Timer(20, this);
  timer.setInitialDelay(30);
  timer.start();
  if(level == 1){
   level1();
  }else if(level == 2){
   level2();
  }else{
   level3();
  }
 }
 /** Sets up the first level
 * Changelog
 * May 25, 2019 - 2 mins - added NPC to the platforms, Michael
 */
 public void level1(){//Created a basic level set up Feng Xiong May 15 1 hour
  setSpawn(100, 200);
  try{
    background = ImageIO.read(new File("platform.png"));
  //emember to add the actual background
  platforms.add (new NPC (320, 300, 50, 100, ImageIO.read(new File("Screens/blank.png")), "i like cats")); //added npc
  }
  catch(IOException e){
  }//R
  platforms.add(new Platform(320, 270, 50));
  platforms.add(new Platform(0, 380, 400));
  platforms.add(new Platform(190, 350, 100));
  platforms.add(new Platform(3, 160, 0));
 }

 /** Sets up the second level
 *
 */
 public void level2(){}

 /** Sets up the thrid level
 *
 */
 public void level3(){}

 /** Sets up the player spawn location
 *
 */
 public void setSpawn(int x, int y){
  player = new Player(x, y, 32, 102);
 }

 public void paint(Graphics g){//runs the update loop, added the logic, Feng Xiogn May 15 10 min
  update(g);
 }


 public void update(Graphics g){//The update loop, added the logic, Feng Xiogn May 15 2 hours
  Graphics g1 = canvas.getGraphics();//Draw the graphics on a separate picture so that we can add pictures without flickering
  //RedrawBackround
  g1.setColor(new Color(0, 0, 0));
  g1.fillRect(0, 0, 1000, 1000);
  g1.drawImage(background, 0, 0,1000, 1000, null);
  //Update all items on screen
  for(Obstacle i : platforms){
   i.update(g1);//Updates to a new position
  }
  player.move(moveX);
  player.jump(moveY);
  player.update(g1, platforms);
  if(player.getX()+canvasX<getWidth()*0.25){
   canvasX+=2;
  }
  if(player.getX()+canvasX>getWidth()*0.75){
   canvasX-=2;
  }
  if(player.getY()+canvasY<getHeight()*0.25){
   canvasY+=2;
  }
  if(player.getY()+canvasY>getHeight()*0.75){
   canvasY-=2;
  }
  Graphics g2 = clear.getGraphics();//Triple buffer
  g2.setColor(new Color(0, 0, 0));
  g2.fillRect(0, 0, 1000, 1000);
  g2.drawImage(canvas, canvasX, canvasY, null);
  g.drawImage(clear, 0, 0, null);
 }

 public void actionPerformed(ActionEvent e) {//Added an action listener for the Timer, Feng Xiong May 16 10min
  //update new positions
  if(!gameOver)
   timer.restart();
  else{
   timer.stop();
  }
  repaint();
 }

 class PlayerListener extends KeyAdapter{//Created a mouse listner class to read user input Feng Xiong May 16 1 hour

  @Override
  public void keyPressed(KeyEvent event){
   int ch = event.getKeyCode();//Keep track of key presses
   if(ch == KeyEvent.VK_UP || ch == KeyEvent.VK_W){
    moveY = 10;
   }else if(ch == KeyEvent.VK_LEFT|| ch == KeyEvent.VK_A){
    moveX = -3;
   }else if(ch == KeyEvent.VK_RIGHT || ch == KeyEvent.VK_D){
    moveX = 3;
   }
   else if (ch == KeyEvent.VK_DOWN)
   {
    //added checking for npc and talking May 22, 2019 - michael
     for (Obstacle n : platforms) //changed to obstacle May 22, 2019 - michael
     {
      if(n instanceof NPC)
     if (Math.abs (player.getX() - n.getX()) <= 33 && player.getY() == player.getX())
       ((NPC) n).speak ();
     }
   }
   if(ch == KeyEvent.VK_ESCAPE){
    el.exit();
   }
  }

  @Override
  public void keyReleased(KeyEvent event){
   int ch = event.getKeyCode();//Keep track of key presses
   if(ch == KeyEvent.VK_UP || ch == KeyEvent.VK_W){
    moveY = 0;
   }else if(ch == KeyEvent.VK_LEFT|| ch == KeyEvent.VK_A){
    if(moveX != 3)
     moveX = 0;
   }else if(ch == KeyEvent.VK_RIGHT || ch == KeyEvent.VK_D){
    if(moveX!=-3)
     moveX = 0;
   }
  }
 }
}
