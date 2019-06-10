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
import java.awt.Rectangle;

/**
* @author Feng Xiong, Michael Zhou
* @version 4.0
* Total time spent Feng: 3 hours
* Total time spend Michael: 3 hours
* Modifications: May 17, 2019, Michael Zhou, Total time: 3 hours
* added changing animations for moving and jumping
* Modifications: May 19, 2019, Feng Xiong, Total time: 2 hour
* Player Edge detection bug fixing
* Modifications: May 23, 2019, Michael Zhou, Total time: 0.05 hours
* modified gravity levels to make the player jump higher
* Feng Xiong, Total time: 30min
* Fix player bug during variable resets
*/
public class Player{

  /**
   * x pixel coordinate
   */
  private int x;

  /**
   * y pixel coordinate
   */
  private int y;

  /**
   * width of the player
   */
  private int sizeX;

  /**
   * height of the player
   */
  private int sizeY;

  /**
   * Image when the player is standing still facing the left
   */
  private BufferedImage idleLeft;

  /**
   * Image when the player is standing still facing the right
   */
  private BufferedImage idleRight;

  /**
   * Image when the player is jumping facing the left
   */
  private BufferedImage jumpLeft;

  /**
   * Image when the player is jumping facing the right
   */
  private BufferedImage jumpRight;

  /**
   * Image when the player is walking to the left
   */
  private BufferedImage[] left = new BufferedImage[2];

  /**
   * Image when the player is walking to the right
   */
  private BufferedImage[] right = new BufferedImage[2];

  /**
   * Acceleration on the x axis
   */
  private int xSpeed;

  /**
   * Acceleration on the y axis
   */
  private int ySpeed;

  /**
   * true if the player is on top of something. Used to check if the player can jump
   */
  private boolean onGround;

  /**
   * Holds the last position of the walking
   */
  private int lastImage;

  /**
   * last direction the player was facing. Used when the player is idle
   */
  private boolean lastDirection;

  /**
   * gameEdge x axis. Used for world borders
   */
  private int edgeX;

  /**
   * gameEdge y axis. Used for world borders
   */
  private int edgeY;

  /**
   * Player bounds, used for convenience.
   */
  private Rectangle playerRect;

  /**
   * Applies a constant gravitational pull
   */
  final static int GRAVITY = 1; //modified gravity levels Michael May 20, 2019

  /**
   * Constructor
   * 
   * @param x starting position x pixel coordinates
   * @param y starting position y pixel coordinates
   * @param sizeX Width of the player
   * @param sizeY Height of the player
   * @param edgeX width of map
   * @param edgeY height of map
   */
  public Player(int x, int y, int sizeX, int sizeY, int edgeX, int edgeY){  //modified michael, added images for animations- may17
    //Added variable set up may 15 Feng Xiong 5 min
    //Adds required images
    try
    {
      idleLeft = ImageIO.read(new File("Player/idleLeft.png"));
      idleRight = ImageIO.read(new File("Player/idleRight.png"));
      jumpLeft = ImageIO.read(new File("Player/jumpLeft.png"));
      jumpRight = ImageIO.read(new File("Player/jumpRight.png"));
      left[0] = ImageIO.read(new File("Player/left1.png"));
      left[1] = ImageIO.read(new File("Player/left2.png"));
      right[0] = ImageIO.read(new File("Player/right1.png"));
      right[1] = ImageIO.read(new File("Player/right2.png"));
    }
    catch(IOException e)
    {
    }
    onGround = false;
    this.x = x;
    this.y = y;
    this.sizeX = sizeX;
    this.sizeY = sizeY;
    this.edgeX = edgeX;
    this.edgeY = edgeY;
    playerRect = new Rectangle(x, y, sizeX, sizeY);
  }

  public Rectangle getBounds(){
    return playerRect;
  }

  public void update(Graphics g, Map platforms){ //modified michael created method - changing of aniamtions of player when moving - may 17 1.5 hour
    //Added general layout Feng Xiong may 16 20 min
    ySpeed+=GRAVITY;
    checkPlatform(platforms);
    x+=xSpeed;
    y+=ySpeed;
    if (xSpeed == 0 && onGround && !lastDirection)//This runs when the character is completely stationary
      g.drawImage(idleLeft, x, y, x+sizeX, y+sizeY, 0, 0, 16, 51, null);
    else if (xSpeed == 0 && onGround && lastDirection)
      g.drawImage(idleRight, x, y, x+sizeX, y+sizeY, 0, 0, 16, 51, null);
    else if (xSpeed > 0 && onGround)//This runs when the character is moving to the right, and is on the ground
    {
      g.drawImage(right[(int)(lastImage/5)], x, y, x+sizeX, y+sizeY, 0, 0, 24, 51, null);
      lastImage ++;
      if (lastImage > 9)
        lastImage = 0;
    }
    else if (xSpeed < 0 && onGround)//This runs when the character is moving to the right and is on the ground
    {
      g.drawImage(left[(int)(lastImage/5)], x, y, x+sizeX, y+sizeY, 0, 0, 24, 51, null);
      lastImage ++;
      if (lastImage > 9)
        lastImage = 0;
    }
    else if (xSpeed > 0 && !onGround)//This runs when the character is moving to the right, while jumping
    {
      g.drawImage(jumpRight, x, y, x+sizeX, y+sizeY, 0, 0, 25, 47, null);
    }
    else if (xSpeed < 0 && !onGround)//This runs when the character is moving to the left, while jumping
    {
      g.drawImage(jumpLeft, x, y, x+sizeX, y+sizeY, 0, 0, 25, 47, null);
      
    }
    else if (xSpeed == 0 && !onGround)
    {
      if (lastDirection)
        g.drawImage(jumpRight, x, y, x+sizeX, y+sizeY, 0, 0, 25, 47, null);
      else
        g.drawImage(jumpLeft, x, y, x+sizeX, y+sizeY, 0, 0, 25, 47, null);
        
    }
  }
 
 private void checkPlatform(Map platforms){//Added collision detection Feng Xiong may 15 - 3 hours

  boolean intersected = false;
  onGround = false;
  playerRect = new Rectangle(x+xSpeed, y+ySpeed, sizeX, sizeY);
  if(!(new Rectangle(0, 0, edgeX, edgeY)).contains(playerRect)){
   xSpeed = 0;
  }
  for(Obstacle i : platforms.find(x+xSpeed, y+ySpeed, sizeX, sizeY)){
    Rectangle platformRect = new Rectangle(i.getX(), i.getY(), i.getXSize(), i.getYSize());
    if(!i.getOn())
      continue;
   
   playerRect = new Rectangle(x+xSpeed, y+ySpeed, sizeX, sizeY);
    intersected = true;
    if(ySpeed>0){
     for(int j = 0; j <= ySpeed;j++){
      playerRect.setLocation(x, y+j);
      if(playerRect.intersects(platformRect)){
       ySpeed = j-1;
       onGround = true;
       break;
      }
     }
    }else if(ySpeed < 0){
     for(int j = 0; j >= ySpeed;j--){
      playerRect.setLocation(x, y+j);
      if(playerRect.intersects(platformRect)){
       ySpeed = j+1;
       break;
      }
     }
    }
    if(xSpeed>0){
     for(int j = 0; j <= xSpeed;j++){
      playerRect.setLocation(x+j, y);
      if(playerRect.intersects(platformRect)){
       xSpeed = j-1;
       break;
      }
     }
    }else if (xSpeed<0){
     for(int j = 0; j >= xSpeed;j--){
      playerRect.setLocation(x+j, y);
      if(playerRect.intersects(platformRect)){
       xSpeed = j+1;
       break;
      }
     }
    }
  }
 }
  
  public int getX(){return x;}//Created method for user control Feng May 15 1 min

  public int getY(){return y;}//Created method for user control Feng May 15 1 min

  public int getXSpeed(){return xSpeed;}//Created method for user control Feng May 15 1 min

  public int getYSpeed(){return ySpeed;}//Created method for user control Feng May 15 1 min

  public void jump(int speed){//Created method for user control Feng May 15 1 min
   if(onGround)
    ySpeed=0-speed;
  }

  public void move(int speed){//Created method for user control Feng May 15 1 min
   xSpeed=speed;
   if(xSpeed>0)
    lastDirection = true;
   else if(xSpeed<0)
    lastDirection = false;
  }
 }
