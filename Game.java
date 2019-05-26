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


public class Game extends Canvas implements ActionListener{
 ArrayList<Platform> platforms;
 Projectile e;
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

 public Game(int level, ExitListener el){
  super();
  setSize(400, 400);
  canvasX = 0;
  canvasY = 0;
  addKeyListener(new PlayerListener());
  canvas = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
  clear = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
  platforms = new ArrayList<Platform>();
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

 public void level1(){
  setSpawn(100, 200);
  try{
    background = ImageIO.read(new File("platform.png"));
  }
  catch(IOException e){
  }//Remember to add the actual background
  platforms.add(new Platform(0, 380, 400, 0, 0));
  platforms.add(new Platform(190, 340, 60, 0, 0));
  platforms.add(new Platform(193, 300, 30, 0, 0));
  platforms.add(new Platform(3, 160, 0, 0, 0));
  e = new Projectile(100, 100, true, 5, 300);
 }

 public void level2(){}

 public void level3(){}

 public void setSpawn(int x, int y){
  player = new Player(x, y, 32, 102);
 }

 public void paint(Graphics g){
  update(g);
 }

 public void update(Graphics g){
  Graphics g1 = canvas.getGraphics();//Draw the graphics on a separate picture so that we can add pictures without flickering
  //RedrawBackround
  g1.setColor(new Color(0, 0, 0));
  g1.fillRect(0, 0, 1000, 1000);
  g1.drawImage(background, 0, 0,1000, 1000, null);
  //Update all items on screen
  for(Platform i : platforms){
   i.update(g1);//Updates to a new position
  }
  e.update(g1);
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

 public void actionPerformed(ActionEvent e) {
  //update new positions
  if(!gameOver)
   timer.restart();
  else{
   timer.stop();
  }
  repaint();
 }

 class PlayerListener extends KeyAdapter{

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