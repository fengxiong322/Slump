import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.*;
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
 private ArrayList<Obstacle> platforms;
 private Timer timer;
 private boolean gameOver;
 private BufferedImage background;
 private BufferedImage canvas;
 private BufferedImage clear;
 private Player player;
 private int canvasX;
 private int canvasY;
 private int moveX;
 private int moveY;
 private int edgeX;
 private int edgeY;
 private int level;
 private boolean checkNPC;
 private ExitListener el;
 private Door door;

 /** COnstructor sets basic values and initalizes arrays and images
 * @param level the specified level
 * @param el the ExitListener
 */
 public Game(int l, ExitListener el){//Created the constructor Feng Xiong 2 hours May 13
  super();
  setSize(800, 800);
  canvasX = 0;
  level = l;
  canvasY = 0;
  addKeyListener(new PlayerListener());
  platforms = new ArrayList<Obstacle>();
  gameOver = false;
  checkNPC = false;
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
  try {
  createLevel (new BufferedReader(new FileReader ("Levels/Level1.txt")));
  background = ImageIO.read(new File("Screens/game1.jpg"));
  platforms.add (new NPC (320, 300, 50, 100, ImageIO.read(new File("Player/idleLeft.png")), "WELCOME PLAYER \n like cats")); //added npc
  }
 catch (IOException e)
 {
 }
  
  setSpawn(90, edgeY - 180);
 }

 public void createLevel (BufferedReader br)
 {
   int lineCount = 0;
  try {
  String line = br.readLine();
  lineCount++;
  edgeX = (line.length() + 1) * 30;
  while (line != null)
  {
   //System.out.println(line);
   for (int i = 0; i <line.length(); i++)
   {
  if (line.charAt(i) == '@')
    {
      platforms.add(new Platform (i *30, lineCount * 30, 30));
   }else if(line.charAt(i) == '\''){
    platforms.add(new InvisPlatform(i*30, lineCount * 30, 30));
   }
   else if(line.charAt(i) == 'd'){
    platforms.add(new Door(i*30, lineCount * 30, 60, 90));
   }else{
    //Add projectiles
   }
   }
   line = br.readLine();
   lineCount++;
  }
  edgeY = (lineCount + 1) * 30;
  canvas = new BufferedImage(edgeX, edgeY, BufferedImage.TYPE_INT_RGB);
  clear = new BufferedImage(edgeX, edgeY, BufferedImage.TYPE_INT_RGB);
  br.close();
 }
 catch (IOException e)
 {
 }
   
 
 }
 
 /** Sets up the second level
 *
 */
public void level2(){
  try {
  createLevel (new BufferedReader(new FileReader ("Levels/Level2-1.txt")));
  background = ImageIO.read(new File("Screens/game1.jpg"));
  platforms.add (new NPC (320, 300, 50, 100, ImageIO.read(new File("Player/idleLeft.png")), "WELCOME PLAYER \n like cats")); //added npc
  }
 catch (IOException e)
 {
 }
  
  setSpawn(90, edgeY - 180);
 }

 /** Sets up the third level
 *
 */
 public void level3(){}

 /** Sets up the player spawn location
 *
 */
 public void setSpawn(int x, int y){
  player = new Player(x, y, 30, 85, edgeX, edgeY);
 }

 public void paint(Graphics g){//runs the update loop, added the logic, Feng Xiogn May 15 10 min
  update(g);
 }

 public void gameEnd(Graphics g){
  el.exit();
 }


 public void update(Graphics g){//The update loop, added the logic, Feng Xiogn May 15 2 hours
  Graphics g1 = canvas.getGraphics();//Draw the graphics on a separate picture so that we can add pictures without flickering
  //RedrawBackround
  g1.setColor(new Color(0, 0, 0));
  g1.fillRect(0, 0, edgeX, edgeY);
  g1.drawImage(background, 0, 0, edgeX, edgeY, null);
  //Update all items on screen
  player.move(moveX);
  player.jump(moveY);
  //door.update(g1);
  //if(door.intersects(player.getBounds())){
  //  gameEnd(g1);
  //  return;
  //}
  for(Obstacle i : platforms){
    if(i instanceof Door && i.getBounds().intersects(player.getBounds())){
    timer.stop();
    gameEnd(g);
    return;
  }else if(i instanceof InvisPlatform)
      ((InvisPlatform)i).setPlayer(player.getBounds());
   i.update(g1);//Updates to a new position
  }

  player.update(g1, platforms);
  if(player.getX()+canvasX<getWidth()*0.45){
   canvasX+=4;
  }
  if(player.getX()+canvasX>getWidth()*0.55){
   canvasX-=4;
  }
  if(player.getY()+canvasY<getHeight()*0.45){
   canvasY+=4;
  }
  if(player.getY()+canvasY>getHeight()*0.55){
   canvasY-=4;
  }
  Graphics g2 = clear.getGraphics();//Triple buffer
  g2.setColor(new Color(0, 0, 0));
  g2.fillRect(0, 0, 1000, 1000);
  g2.drawImage(canvas, canvasX, canvasY, null);
    for(Obstacle i: platforms){
            //added checking for npc and talking May 22, 2019 - michael
    if(checkNPC && i instanceof NPC)
       {
         if (Math.abs (player.getX() - i.getX()) <= 33 && Math.abs (player.getY() - i.getY()) <= 102)
         {
           ((NPC) i).speak (g2);
           
            
         }
       }
  }
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
   if (checkNPC = true) {
     checkNPC = false;
   }
   if(ch == KeyEvent.VK_UP || ch == KeyEvent.VK_W){
    moveY = 15;
   }else if(ch == KeyEvent.VK_LEFT|| ch == KeyEvent.VK_A){
    moveX = -4;
   }else if(ch == KeyEvent.VK_RIGHT || ch == KeyEvent.VK_D){
    moveX = 4;
   }
   else if (ch == KeyEvent.VK_DOWN || ch == KeyEvent.VK_S)
   {
    checkNPC = true;
   }
   else if(ch == KeyEvent.VK_ESCAPE){
    el.exit();
   }
   if(ch == KeyEvent.VK_R){
     if(level == 1){
       setSpawn(90, edgeY - 180);
     }else if(level == 2){
       setSpawn(90, edgeY - 180);
     }else{
       setSpawn(90, edgeY - 180);
     }
   }
  
  }

  @Override
  public void keyReleased(KeyEvent event){
   int ch = event.getKeyCode();//Keep track of key presses
   if(ch == KeyEvent.VK_UP || ch == KeyEvent.VK_W){
    moveY = 0;
   }else if(ch == KeyEvent.VK_LEFT|| ch == KeyEvent.VK_A){
    if(moveX<0)
     moveX = 0;
   }else if(ch == KeyEvent.VK_RIGHT || ch == KeyEvent.VK_D){
    if(moveX>0)
     moveX = 0;
   }
  }
 }
}
