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
 * @author Feng Xiong, Michael Zhou
 * @version 4.0
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
    dialogue.add ("It\'s not easy, leaving a slump, you will face all sorts of challenges… but "
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
    dialogue.add ("You forgot to bring your math assignment to school since you were watching TV /nWhat do you say to your teacer /qIt\'s my mothers fault for not reminding me /qIt\'s all your fault for not reminding me /aI am sorry, I will bring it tomorrow /qIt\'s my brothers fault for distracting me");
    dialogue.add ("Which of the following is not an exmaple of a blame /q You\'re the one making it hard on me /aI made a mistake, but I can set things right /q This is all your fault /qEverything is my fault, I\'m a failure");
    dialogue.add ("You didn\'t practice for the last basketball game and as a result, you didnt /n perform too well. What do you say to your teamates? /qIt\'s my teams fault for not passing enough /aIt\'s my fault, I didn\'t practice enough, I will practice for the next game/qIt\'s the coaches fault, he didn\'t teach us right /qI will quit the team");
    dialogue.add ("Your mom asked you to do the dishes but you forgot because you were watching /n Youtube. What do you tell her? /aSorry, I forgot, I will do them right now /qWhat dishes? /qI had a lot of homework to do.../qI forgot because, my bro was listening to loud music");
    dialogue.add ("I failed the english quiz, what do I say to my parents? /qThe teacher ran out of pens, the quizes arn\'t marked /qIt\'s the teacher\'s fault for not teaching us properly /qIt\'s your fault for not making me study /aI was playing video games, I will study harder for the next one");
    dialogue.add ("I lost my assignment, what do I tell my history teacher? /qThe dog ate my homework /qMy brother distracted me too much yesterday /qYou didn\'t teach me properly so I couldn\'t do it /aI lost my assingment, I will hand another in tomorrow");    
    dialogue.add ("Dont give up! You can do this! I believe in you!");
    dialogue.add ("You will soon meet people with questions about certain situations, talk to"
                   +"/nas many of them as you can and help them correctly to gain more score");
    dialogue.add ("Welcome to the first level of your journey and climb to get out of this slump "
                   +"/nOne of the challenges you will face when trying to leave a slump are blaming" 
                   +"/nand excuses. When you begins to blame, you hold other people responsible for" 
                   +"/nyour own emotional pain. You may also make excuses, to help you shift the " 
                   +"/nblame onto others.");
    dialogue.add ("In this level, you will encounter projectiles which represent blame and "
                   +"/nexcuses. Be very careful and patient because if you get hit by these, "
                   +"/nyou will have to start all over again.");
    dialogue.add ("Dont give up! You can do this!");
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
    dialogue.add ("Which of the following are ways to overcome low-self esteem: /qActs of kindess /qOpen mindedness /qSetting goals/aAll of the above");
    dialogue.add ("You feel down and you feel like your actions are pointless. What ist this /nA sign of? /aLow self-esteem /qThe flu /qA sunburn /qsore throat");
    dialogue.add ("You just failed a test and you are scared you will not do well on the next one /n What should you do? /qSkip school on the day of the test /qCall in sick /qKeep it out of your mind /aBe well prepared, study more");   
    dialogue.add ("You did not proform well in the last drama play. You are scared that you will not /ndo well on the next performance. What do you do? /qBeg your parents to let you stay home /qCall in sick on the day of /aAsk your teacher for extra lessons and practice /qLeave the drama team");
    dialogue.add ("You have to bake a cake for your parents, but you are scared that they will not /n enjoy it. What can you do? /a Practice baking and be prepared /qPretend to have a tummy ache /qAvoid thinking about it /qWorry about it");
    dialogue.add ("Another way to overcome low self esteem is to help out those that are"
                   +"/naround you. An act of kindness can go a long way for those around you"
                   +"/nand for yourself as well. You will make someome\'s day and feel better"
                   +"/na sense of confidence and accomplishment.");
    dialogue.add ("Another way to overcome this mentality is to set goals for your self."
                   +"/nThese goals should be realistic. Overcoming each goal will provide you with"
                   +"/nan sense of accomplishment and confidence. Seting and achieving at least one"
                   +"/ngoal each day will help you gain much more confidence");
    dialogue.add ("You can do it, have faith in yourself! You cannot run away from your problems");
    dialogue.add ("One of the ways to overcome this low-self esteem is to have a less"
                   +"/njudgemental mentality. Don\'t rate and judge everything, but rather"
                   +"/nkeep an open mind and accept things as they present themselves.");
    dialogue.add ("Another challenge you will face is your self doubt. After failing and getting"
                   +"/nin a slump, you will be more likely to doubt yourself and not believe in your "
                   +"/nopnions. Your overall self esteem might be lower as well.");
    dialogue.add ("In this level you will encounter invisible platforms that represent your "
                   +"/ndoubt. However, they will show themselves when you get near them. Stay "
                   +"/nconfident and believe in yourself and you will be able to complete this level "
                   +"/nand move on upwards! Also be prepared!");
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
    dialogue.add ("You fail a test in your Math course, what do you take from the situation? /qYou will never be able to pass the course /qYou are a failure /qYou did not score perfecet /qIt was just one mark out of many dozens");
    dialogue.add ("You miss one shote in a soccer game but score two goal, what do you take /n from the situation? /qYou missed a shot /qYou did not perform well become you missed a shot/aYou scored two goals and won the match/qThe enemy team wasn\'t trying/qYou did not score 100 percent of your shots");
    dialogue.add ("You look at a situation. What should you see/set a goal for? /qThe absolute worst outcome /qThe perfect outcome /aThe better end of the possible out comes /qWhat situation?");
    dialogue.add ("You hit a home run in a base ball game but struck out twice, what do you/ntake from the situation? /qYou did not do well because you struck out twice /aYou did well and hit a homerun /qYou didn\'t every homerun /qThe enemy team was going easy on us");
    dialogue.add ("Another way to overcome this is by challanging your negative thoughts. Ask"
                   +"/nyour self is this really a realistic outcome, is this a realisitic image"
                   +"/nof the situation. This will allow you the see situations in a more realistic"
                   +"/nperspective.");
    dialogue.add ("Another way to overcome this mentality is to add a grading scale for outcomes"
                   +"/nFor example, if you missed a shot in a basketball game, you still landed,"
                   +"/neighty percent of the other shots, so you only missed 20 percent. This will"
                   +"/nhelp you stop viewing situations in two extreme paths");
    dialogue.add ("You just missed a few jumps, it is no big deal!");
    dialogue.add ("The best way to overcome polarized thinking is to understand that there is a"
                   +"/nmiddle ground in every situation. There is always grey between the black and"
                   +"/nwhite. For getting about this middle ground is what leads to extremes");
    dialogue.add ("In this level you will encounter black and white platforms that will "
                   +"/nconstantly be switching. These represent the best and worst possible outcome."
                   +"/nFinding a balance of both will lead you to your goal.");
    dialogue.add ("Another challenge you will face in a slump is an extreme mentality every "
                   +"/nsituation (called polarized thinking). In any situation, you might only see"
                   +"/nthe absolutes, such as the best possible outcome and the worst filtering out "
                   +"/nall of the outcomes in between. Keeping a balance and seeing all the different"
                   +"/npossible outcomes.");
    dialogue.add ("Don\'t worry, you can do this! Climp up out of this slump!");
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
    dialogue.add ("You fail your math test because you were studying mostly for your science /ntest, what do you take from the situation? /qYou will never be able to pass math /aYou need to manage your time more wisely, it is just one mark /q What math test?! /qYou failed a test so you are a failure");
    dialogue.add ("When faced with a situation, what should you focus on? /qThe worst possible outcome /qThe perfect outcome /aThe possibilies in between /qIgnore the situation");
    dialogue.add ("Did you enjoy this game? /qNo it was terrible /aYes, but it can be improved /qNooo? /qNO");
    dialogue.add ("Finaly we have the last obstacle, polarized thinking!");
    dialogue.add ("You lost with your artwork in a competition, now you have to present it to the/nclass but you are scared, what do you do?/qAvoid the class /aPrepare, check if you can make your art better /qTell your friend to pretend to be you/qBeg your parents to let you stay home.");
    dialogue.add ("Which of the following are ways to overcome low-self esteem: /qMaking excuses /qClosed mindedness /aSetting goals/qEat plenty of junk food");
    dialogue.add ("You did not proform well in the last dance. You are scared that you will not /ndo well on the next performance. What do you do? /qBeg your parents to let you leave the dance team /qBeg your teacher to let you not present /aAsk your teacher for extra lessons and practice /qWhine to your friends and worry");
    dialogue.add ("Now is time for the obstacle of low self esteem");
    dialogue.add ("I failed the history quiz, what do I say to my parents? /qThe teacher ran out of pencils, the quizes arn\'t marked /qIt\'s the teacher\'s fault for not teaching us properly /qIt\'s the teacher\'s fault for not making me study /aI was watching TV, I will study harder for the next one");
    dialogue.add ("You failed the english assignment because you had to spend your time on other/nhomework. Who is to blame? /qYour teacher, she didn;t teach you correctly /qYour parents, beacuse they made you do the dishes /aPartly yourself, because you need to manage time better/qYour sister because she was playing music");
    dialogue.add ("When you are faced with a problem outside  your comfort zone, what do you/ndo? /qRun away /qMake Excuses /qBlame others /aFace the problem and take action");
    dialogue.add ("In this level, you will encounter all the obstacles that you have encountered"
                    +"/nbefore, use everything you have learned to complete this final level and"
                    +"/nescape the slmup that you are in!");
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
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.PLAIN, 20));
        g.drawString(getTotalScore()+"", 270, 620);
        String name = JOptionPane.showInputDialog(this, "Your Name:");
        if(name == null){
          name = "You";
        }
        if(name.length() >= 13)
          name = name.substring(0, 13);
        g.setFont(new Font("SansSerif", Font.PLAIN, 25));
        g.drawString(name, 400, 30);
        addScore(name, getTotalScore());
      }catch(IOException e){}
      addMouseListener(new MouseAdapter(){
        public void mousePressed(MouseEvent me){
          int mouseX = me.getX();
          int mouseY = me.getY();
          System.out.println(mouseX + " "+ mouseY);
          if(mouseX>335 && mouseY> 700 && mouseX < 470 && mouseY < 790){
            timer.stop();
            map = null;
            obstacles = null;
            Game.this.removeMouseListener(this);
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
      PrintWriter pw = new PrintWriter (new FileWriter (information));
      int i;
      for (i = 0 ; i < 20 ; i += 2)
      {
        if (fileLines [i] == null)
        {
          pw.println (name);
          pw.println (score);
          break;
        }
        if (Integer.parseInt (fileLines [i + 1]) >= score)
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
      pw.close();
    }catch(IOException e){
      System.out.println("rip");
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
                if (!((NPC) i).getTalked())
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
                if (!((NPC) i).getTalked())
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
