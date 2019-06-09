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
 * Modifications: May 31, 2019, Michael Zhou, Total time: 1 hours
 * added level generation based of text file method
 * added the text files
 * Made r reset the player to his spawn
 */
public class Game extends Canvas implements ActionListener{
  private ArrayList<Obstacle> obstacles;
  private Map map;
  private Timer timer;
  private boolean gameOver;
  private BufferedImage background;
  private BufferedImage canvas;
  private BufferedImage clear;
  private Player player;
  private int canvasX;
  private int canvasY;
  private int moveX;//player movement
  private int moveY;
  private int edgeX;
  private int edgeY;
  private int level;
  private boolean checkNPC;
  private ExitListener el;
  private Door door;
  private long time;
  private int second;
  boolean rightAnswer;
  
  /** COnstructor sets basic values and initalizes arrays and images
    * @param level the specified level
    * @param el the ExitListener
    */
  public Game(int l, ExitListener el){//Created the constructor Feng Xiong 2 hours May 13
    super();
    setSize(800, 800);
    level = l;
    this.el = el;
    if(level == 1){
      level1();
    }else if(level == 2){
      level2();
    }else if(level == 3){
      level3();
      
    }else if(level == 4){
      level4();
    }
    else if(level == 5){
      level5();
    }
    int second = 0;
  }
  /** Sets up the first level
    * Changelog
    * May 25, 2019 - 2 mins - added NPC to the obstacles, Michael
    */
  public void level1(){//Created a basic level set up Feng Xiong May 15 1 hour
    level =1;
    ArrayList <String> dialogue = new ArrayList <String> ();
    try {
      createLevel (new BufferedReader(new FileReader ("Levels/Level1.txt")), dialogue);
      background = ImageIO.read(new File("Screens/surface.jpg"));
    }
    catch (IOException e)
    {
    }
    
    setSpawn(60, 60);
  }
  
  /** Sets up the second level
    *
    */
  public void level2(){
    level =2;
    ArrayList <String> dialogue = new ArrayList <String> ();
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    try {
      createLevel (new BufferedReader(new FileReader ("Levels/Level2-1.txt")), dialogue);
      background = ImageIO.read(new File("Screens/game2.jpg"));
    }
    catch (IOException e)
    {
    }
    
    setSpawn(90, edgeY - 180);
  }
  
  /** Sets up the third level
    *
    */
  public void level3(){
    level =3;
    ArrayList <String> dialogue = new ArrayList <String> ();
    try {
      createLevel (new BufferedReader(new FileReader ("Levels/Level2-2.txt")), dialogue);
      background = ImageIO.read(new File("Screens/game1.jpg"));
    }
    catch (IOException e)
    {
    }
    
    setSpawn(300, edgeY - 180);
  }
  /** Sets up the third level
    *
    */
  public void level4(){
    level =4;
    ArrayList <String> dialogue = new ArrayList <String> ();
    try {
      createLevel (new BufferedReader(new FileReader ("Levels/Level2-3.txt")), dialogue);
      background = ImageIO.read(new File("Screens/game1.jpg"));
    }
    catch (IOException e)
    {
    }
    
    setSpawn(300, edgeY - 180);
  }
  
  /** Sets up the third level
    *
    */
  public void level5(){
    level =5;
    ArrayList <String> dialogue = new ArrayList <String> ();
    try {
      createLevel (new BufferedReader(new FileReader ("Levels/Level3.txt")), dialogue);
      background = ImageIO.read(new File("Screens/game1.jpg"));
    }
    catch (IOException e)
    {
    }
    
    setSpawn(300, edgeY - 180);
  }
  
  //May 31, 2019, Michael Zhou, Total time: 1 hours
  //added method to create level based of reading text file
  public void createLevel (BufferedReader br, ArrayList<String> dialogue)
  {
    canvasX = 0;
    canvasY = 0;
    addKeyListener(new PlayerListener());
    gameOver = false;
    checkNPC = false;
    moveX = 0;
    moveY = 0;
    
    
    time = 0;
    int npcCount = 0;
    obstacles = new ArrayList<Obstacle>();
    int lineCount = 0;
    try {
      String line = br.readLine();
      lineCount++;
      edgeX = (line.length()) * 30;
      int i = 0;
      while (line != null)
      {
        for (i = 0; i <line.length(); i++)
        {
          
          switch(line.charAt(i)){
            case '@':
              obstacles.add(new Platform (i *30, lineCount * 30, 30));
              break;
            case '\'':
              obstacles.add(new InvisPlatform(i*30, lineCount * 30, 30));
              break;
            case 'd':
              obstacles.add(new Door(i*30, lineCount * 30, 60, 90));
              break;
            case 'W'://A white block, meaning clear.
              obstacles.add(new StateSwitchPlatform(i*30, lineCount * 30, 30, true));
              break;
            case 'B'://A black block, cannot exist with white blocks.
              obstacles.add(new StateSwitchPlatform(i*30, lineCount * 30, 30, false));
              break;
            case 'N':
              obstacles.add (new NPC (i*30, lineCount * 30 + 5, 30, 85, ImageIO.read(new File("Player/idleLeft.png")), dialogue.get (npcCount)));
              npcCount++;
              break;
            case 'n':
              obstacles.add (new NPC (i*30, lineCount * 30 + 5, 30, 85, ImageIO.read(new File("Player/idleLeft.png")), dialogue.get (npcCount)));
              npcCount++;
              break;
            default:
              if((int)line.charAt(i) >0 && (int)line.charAt(i) <10)//left
              obstacles.add(new Projectile(i*30, lineCount *30, false, (int)line.charAt(i) - 64, 0));
              if(line.charAt(i)-'0' >0 && line.charAt(i)-'0' <10){//right
                obstacles.add(new Projectile(i*30, lineCount *30, true, Integer.parseInt(line.charAt(i) + ""), edgeX));
              }
              
          }
        }
        line = br.readLine();
        lineCount++;
      }
      edgeY = (lineCount) * 30;
      map =  new Map(i, lineCount);
      for(Obstacle j : obstacles)
        map.add(j, j.getX()/30, j.getY()/30);
      
      canvas = new BufferedImage(edgeX, edgeY, BufferedImage.TYPE_INT_RGB);
      clear = new BufferedImage(edgeX, edgeY, BufferedImage.TYPE_INT_RGB);
      br.close();
    }
    catch (IOException e)
    {
    }
    timer = new Timer(20, this);
    timer.setInitialDelay(30);
    timer.start();
    
  }
  
  /** Sets up the player spawn location
    *
    */
  public void setSpawn(int x, int y){
    player = new Player(x, y, 30, 85, edgeX, edgeY);
    canvasX = getWidth()/2-x;
    canvasY = getHeight()/2-y;
  }
  
  public void paint(Graphics g){//runs the update loop, added the logic, Feng Xiong May 15 10 min
    update(g);
  }
  
  public void gameEnd(Graphics g){
    if(level == 1){
      level = 2;
      level2();
      timer.restart();
    }else if(level == 2){
      level = 3;
      level3();
      timer.restart();
    }else if(level == 3){
      level = 3;
      level3();
      timer.restart();
    }else if(level == 4){
      level = 3;
      level3();
      timer.restart();
    }else{
      //Add goodbye and display score
      el.exit();
    }
  }
  
  
  public void update(Graphics g){//The update loop, added the logic, Feng Xiogn May 15 2 hours
    Graphics g1 = canvas.getGraphics();//Draw the graphics on a separate picture so that we can add pictures without flickering
    //RedrawBackround
    g1.drawImage(background, 0, 0, edgeX, edgeY, null);
    //Update all items on screen
    player.move(moveX);
    player.jump(moveY);
    for(Obstacle i : obstacles){
      if(i instanceof Door && i.getBounds().intersects(player.getBounds())){
        timer.stop();
        gameEnd(g);
        return;
      }else if(i instanceof InvisPlatform)
        ((InvisPlatform)i).setPlayer(player.getBounds());
      else if (i instanceof StateSwitchPlatform && time%3 ==0 && second ==0){
        ((StateSwitchPlatform)i).flipType();
        Rectangle tempRect = i.getBounds();
        tempRect.translate(0, -1);
        if(tempRect.intersects(player.getBounds()))
          i.setOn(true);
        else
          i.setOn(((StateSwitchPlatform)i).getType());
      }else if(i instanceof Projectile && i.getBounds().intersects(player.getBounds())){
        try{
          Thread.sleep (1000);
        }
        catch (Exception e)
        {
        }
        respawn();
      }
      i.update(g1);//Updates to a new position
    }
    
    
    player.update(g1, map);
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
    for(Obstacle i: obstacles){
      //added checking for npc and talking May 22, 2019 - michael
      if(checkNPC && i instanceof NPC)
      {
        if (Math.abs (player.getX() - i.getX()) <= 33 && Math.abs (player.getY() - i.getY()) <= 102)
        {
          ((NPC) i).speak (g2);
          i.setOn(false);
          
        }
      }
    }
    g2.drawString("Your Score: " + time, 100, 100);
    g.drawImage(clear, 0, 0, null);
    g.dispose();
    g1.dispose();
    g2.dispose();
  }
  
  public void actionPerformed(ActionEvent e) {//Added an action listener for the Timer, Feng Xiong May 16 10min
    //update new positions
    second++;
    if(second == 50){
      time++;
      second = 0;
    }
    if(!gameOver)
      timer.restart();
    else{
      timer.stop();
    }
    repaint();
  }
  
  private void respawn(){ //editied May 31, Michael - now respawns the player in the bottom left corner of the scneen
    if(level == 1){
      setSpawn(300, edgeY - 180);
    }else if(level == 2){
      setSpawn(90, edgeY - 180);
    }else{
      setSpawn(90, edgeY - 180);
    }
  }
  
  class PlayerListener extends KeyAdapter{//Created a mouse listner class to read user input Feng Xiong May 16 1 hour
    
    @Override
    public void keyPressed(KeyEvent event){
      int ch = event.getKeyCode();//Keep track of key presses
      if (checkNPC = true) {
        if(ch == KeyEvent.VK_UP)
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
        timer.stop();
        el.exit();
      }
      if(ch == KeyEvent.VK_R){
        respawn();
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
