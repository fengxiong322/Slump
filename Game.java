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
  private int second;
  private boolean inNPC;
  boolean rightAnswer;
  private long[] finished;
  private int spawnX;
  private int spawnY;
  
  /** COnstructor sets basic values and initalizes arrays and images
    * @param level the specified level
    * @param el the ExitListener
    */
  public Game(int l, ExitListener el){//Created the constructor Feng Xiong 2 hours May 13
    super();
    setSize(800, 800);
    level = l;
    this.el = el;
    finished = new long[5];
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
    second = 0;
  }
  /** Sets up the first level
    * Changelog
    * May 25, 2019 - 2 mins - added NPC to the obstacles, Michael
    */
  public void level1(){//Created a basic level set up Feng Xiong May 15 1 hour
    level =1;
    ArrayList <String> dialogue = new ArrayList <String> ();
    dialogue.add ("But sometimes, you might encounter failure in your journey whether its "
                   +"/nbecause you failed a test, an assignment, or lost a soccer game, it okay "
                   +"/nbecause everyone has been through times like this. Sometimes we just get" 
                   +"/nstuck, in a slump…");
    dialogue.add ("In the journey of life you will encounter all sorts of ups and downs..."
                   +"/nWe all have our successes which we hold dear, and we all make mistakes "
                   +"/nthat we can learn from.");
    dialogue.add ("Jump over these platform, I believe in you");
    dialogue.add ("Welcome player! Use the arrow keys or WASD keys to move around. You can talk "
               +"/n to others like you talked to me by using the down or S key. If u ever "
               +"/nwant to leave, you can press escape, you can also press the r key to restart "
               +"/nthe level. Your goal in each level is to reach the brown door. The one in "
               +"/nthis level is over to the right");
    dialogue.add ("Use your up arrow and overome the obstacle ahead.");
    dialogue.add ("Hey, I have just failed my science test, what should I ask myself?/qWhy did I fail, is it because I’m a failure? /a What’s one thing I can learn from this? What is my next plan of action?/qShould I go home, play video games, and just forget about this?/qHmm? What science test?");             
    dialogue.add ("I missed the last shot in my soccer game and my team lost. What should I do?/q Go home and eat junk food to get rid of your worries /q Leave the soccer team/q Blame the rest of the team /aTalk to my friends and family");
    dialogue.add ("I tried my best to study for the test but I was distracted by video game. /n I failed the test, what do I take from this situation? /a I should learn from my mistakes and spend time more wisely /q I am a failure and I should quit school /q I should skip all future tests /q Blame your parents");
     dialogue.add ("Good job, you completed the first level! I hope you talked to most of the/ncharacters in the level for some sweet score points.");
    dialogue.add ("Plan out your path, and take action right away. The hardest part is "
                   +"/nbeginning to take action, but once you start moving, it gets much easier to "
                   +"/ncontinue. If you find this hard, split your plan into small steps and take "
                   +"/nthose steps every once in a while.");
    dialogue.add ("The next step is to understand the situation and learn all you can from it. "
                   +"/nIts okay to make mistakes and fail, but the important thing is that you "
                   +"/nlearned from this situations (like you are now by talking to us).");
    dialogue.add ("The first step to overcoming this is accepting what happened and how you feel"
                   +"/nabout it. Denial will only leave you stuck around here longer.");
    dialogue.add ("You make can a mistake, have setback or you simply fail. I know its not be "
                   +"/nmost fun situation. You can’t avoid it unless you avoid doing anything at all.");
    dialogue.add ("It's not easy, leaving a slump, you will face all sorts of challenges… but "
                   +"/nthere will always be people out there to help you. Talk to as many characters"
                   +"/nas you can and you will not only learn many things, but get some sweet score "
                  +"/npoints doing so. ");
    dialogue.add ("But, you will never be stuck forever, you will learn from your actions, and"
                   +"/nyou can reach the highest peaks you have ever reached.");
    
    
    try {
      createLevel (new BufferedReader(new FileReader ("Levels/Level1.txt")), dialogue);
      background = ImageIO.read(new File("Screens/surface.jpg"));
    }
    catch (IOException e)
    {
    }
    
    setSpawn(spawnX, spawnY);
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
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
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
    
    setSpawn(spawnX, spawnY);
  }
  
  /** Sets up the third level
    *
    */
  public void level3(){
    level =3;
    ArrayList <String> dialogue = new ArrayList <String> ();
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    try {
      createLevel (new BufferedReader(new FileReader ("Levels/Level2-2.txt")), dialogue);
      background = ImageIO.read(new File("Screens/game1.jpg"));
    }
    catch (IOException e)
    {
    }
    
    setSpawn(spawnX, spawnY);
  }
  /** Sets up the third level
    *
    */
  public void level4(){
    level =4;
    ArrayList <String> dialogue = new ArrayList <String> ();
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    dialogue.add ("Hahaha this works ez pz feng is wing aghaas/n reeeee/q ree/q asdas/q fgfg/a gfg");
    try {
      createLevel (new BufferedReader(new FileReader ("Levels/Level2-3.txt")), dialogue);
      background = ImageIO.read(new File("Screens/game1.jpg"));
    }
    catch (IOException e)
    {
    }
    
    setSpawn(spawnX, spawnY);
  }
  
  /** Sets up the third level
    *
    */
  public void level5(){
    level =5;
    ArrayList <String> dialogue = new ArrayList <String> ();
    dialogue.add ("aasd /q /q /q");
    dialogue.add ("aasd /q /q /q");
    dialogue.add ("aasd /q /q /q");
    dialogue.add ("Finaly we have the last obstacle, polarized thinking!");
    dialogue.add ("aasd /q /q /q");
    dialogue.add ("aasd /q /q /q");
    dialogue.add ("aasd /q /q /q");
    dialogue.add ("Now is time for the obstacle of low self esteem");
    dialogue.add ("aasd /q /q /q");
    dialogue.add ("aasd /q /q /q");
    dialogue.add ("aasd /q /q /q");
    dialogue.add ("In this level, you will encounter all the obstacles that you have encountered
                   before, use everything you have learned to complete this final level and
                   escape the slmup that you are in!");
    dialogue.add ("First, comes the challange of blame and excuses...");
    try {
      createLevel (new BufferedReader(new FileReader ("Levels/Level3.txt")), dialogue);
      background = ImageIO.read(new File("Screens/game1.jpg"));
    }
    catch (IOException e)
    {
    }
    
    setSpawn(spawnX, spawnY);
  }
  
  //May 31, 2019, Michael Zhou, Total time: 1 hours
  //added method to create level based of reading text file
  public void createLevel (BufferedReader br, ArrayList<String> dialogue)
  {
    finished[level-1] = 1000;
    inNPC = false;
    canvasX = 0;
    canvasY = 0;
    super.addKeyListener(new PlayerListener());
    gameOver = false;
    checkNPC = false;
    moveX = 0;
    moveY = 0;
    
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
            case 's':
              spawnX = i*30;
              spawnY = lineCount * 30;
            default:
              if((int)line.charAt(i) >0 && (int)line.charAt(i) <10){//left
              obstacles.add(new Projectile(i*30, lineCount *30, false, (int)line.charAt(i) - 64, 0));
              obstacles.add(new Generator(i*30, lineCount *30, 30));
            }
              if(line.charAt(i)-'0' >0 && line.charAt(i)-'0' <10){//right
                obstacles.add(new Projectile(i*30, lineCount *30, true, Integer.parseInt(line.charAt(i) + ""), edgeX));
                obstacles.add(new Generator(i*30, lineCount *30, 30));
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
  
  public long getTotalScore(){
    long score = 0;
    for(int i = 0; i < 5; i ++){
      score += finished[i];
    }
    return score;
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
      level = 4;
      level4();
      timer.restart();
    }else if(level == 4){
      level = 5;
      level5();
      timer.restart();
    }else{
      gameOver = true;
      try{
        g.drawImage(ImageIO.read(new File("Screens/endScreen.png")), 0, 0, 800, 800, null);
        g.drawString(getTotalScore()+"", 100, 100);
        String name = JOptionPane.showInputDialog(this, "Your Name:");
        if(name == null){
          name = "You";
        }
        if(name.length() >= 13)
          name = name.substring(0, 13);
        g.drawString(name, 100, 200);
        addScore(name, getTotalScore());
      }catch(IOException e){}
      addMouseListener(new MouseAdapter(){
        public void mousePressed(MouseEvent me){
          int mouseX = me.getX();
          int mouseY = me.getY();
          if(mouseX>238 && mouseY> 340 && mouseX < 548 && mouseY < 437){
            el.exit();
          }
        }
      });
    }
  }
  
  private void addScore(String name, long score){
    File directory = new File(System.getProperty("user.home") + "/slump");
    File information = new File(System.getProperty("user.home") + "/slump/highscores.txt");
    try{
      directory.mkdirs();
      if(information.createNewFile())
        JOptionPane.showMessageDialog(null, "A new file has been created for you at " + directory.getAbsolutePath());
      BufferedReader br = new BufferedReader(new FileReader(information));
      String[] fileLines = new String [20];
      for (int i = 0 ; i < 20 ; i++)
        fileLines [i] = br.readLine ();
      br.close ();
      PrintWriter pw = new PrintWriter (new FileWriter ("scores.lexd"));
      for (int i = 0 ; i < 20 ; i += 2)
      {
        if (fileLines [i] == null)
        {
          pw.println (name);
          pw.println (score);
          break;
        }
        if (Integer.parseInt (fileLines [i + 1]) < score)
        {
          pw.println (fileLines [i]);
          pw.println (fileLines [i + 1]);
        }
        else
        {
          pw.println (name);
          pw.println (score);
          pw.println (fileLines [i]);
          pw.println (fileLines [i + 1]);
          break;
        }
      }
    }catch(IOException e){
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
      else if (i instanceof StateSwitchPlatform && finished[level-1]%3 ==0 && second ==0){
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
      if (i instanceof NPC && Math.abs (player.getX() - i.getX()) <= 33 && Math.abs (player.getY() - i.getY()) <= 102)
      {
        try{
          BufferedImage arrow = ImageIO.read(new File("NPC/down.png"));
          g1.drawImage (arrow, ((NPC) i).getX (), ((NPC) i).getY () - 30, 30, 30, null);
        }
        catch (IOException e)
        {
        }
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
    boolean detectedNPC = false;
    for(Obstacle i: obstacles){
      //added checking for npc and talking May 22, 2019 - michael
      
      if(checkNPC && i instanceof NPC)
      {         
        if (Math.abs (player.getX() - i.getX()) <= 33 && Math.abs (player.getY() - i.getY()) <= 102)
        {                 
          detectedNPC = true;
          ((NPC) i).speak (g2);
          if(inNPC)
            break;
          inNPC = true;
          addKeyListener(new KeyAdapter(){
            private boolean down;
            public void keyPressed(KeyEvent event){
              if(!((NPC)i).isQuestion()){
                checkNPC = false;//Tells the program to resume the game
                inNPC = false;
                i.setOn(false);//Tells the player to ignore this obstacle
                if (((NPC) i).getTalked () == false);
                {
                  finished[level-1]+=500;//Gives a reward
                  ((NPC) i).setTalked (true);
                }
                Game.this.removeKeyListener(this);
              }
              int ch = event.getKeyCode();
              int key = 0;
              if (ch == KeyEvent.VK_1)
                key = 1;
              else if (ch == KeyEvent.VK_2)
                key = 2;
              else if (ch == KeyEvent.VK_3)
                key = 3;
              else if (ch == KeyEvent.VK_4)
                key = 4;
              else
                return;
              if (((NPC) i).getOption(key).equals (((NPC) i).getAnswer()))              
              {
                //If the answer is correct, these 4 lines should be run to continue
                checkNPC = false;//Tells the program to resume the game
                inNPC = false;
                ((NPC) i).setDialogue ("Hey there, nice to see you again."); 
                i.setOn(false);//Tells the player to ignore this obstacle
                if (((NPC) i).getTalked () == false);
                {
                  finished[level-1]+=500;//Gives a reward
                  ((NPC) i).setTalked (true);
                }
                Game.this.removeKeyListener(this);
              }
              else
              {                
                JOptionPane.showMessageDialog(null, "That wasn't the best answer... try again!");
                finished[level-1]-=50;
              }
            }
          });
          
        }
      }
      
    }
    if(!detectedNPC)
      checkNPC = false;
    g2.setFont(new Font("SansSerif", Font.PLAIN, 20));
    g2.setColor(new Color(255, 255, 255));
    g2.drawString("Your Score: " + finished[level-1], 100, 100);
    g.drawImage(clear, 0, 0, null);
    g.dispose();
    g1.dispose();
    g2.dispose();
  }
  
  public boolean getEnd(){
    return gameOver;
  }
  
  public void actionPerformed(ActionEvent e) {//Added an action listener for the Timer, Feng Xiong May 16 10min
    //update new positions
    second++;
    if(second == 50){
      finished[level-1]--;
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
    finished[level-1]-=50;
    if(level == 1)
      setSpawn(spawnX, spawnY);
    
  }
  
  class PlayerListener extends KeyAdapter{//Created a mouse listner class to read user input Feng Xiong May 16 1 hour
    
    @Override
    public void keyPressed(KeyEvent event){
      int ch = event.getKeyCode();//Keep track of key presses
      if(checkNPC == true)
        return;
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
        map = null;
        obstacles = null;
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