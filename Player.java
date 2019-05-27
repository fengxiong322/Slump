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
* @author Feng, Michael
* @version 2.0
* Total time spent Feng: 3 hours
* Total time spend Michael: 3 hours
 *Modifications: May 17, 2019, Michael Zhou, Total time: 3 hours
 * added changing animations for moving, jumping,
* Modifications: May 23, 2019, Michael Zhou, Total time: 0.05 hours
* modified gravity levels to make the player jump higher
*/
public class Player{
 int x;
 int y;
 int sizeX;
 int sizeY;
 BufferedImage idleLeft;
 BufferedImage idleRight;
 BufferedImage jumpLeft;
 BufferedImage jumpRight;
 BufferedImage[] left = new BufferedImage[2];
 BufferedImage[] right = new BufferedImage[2];
 int xSpeed;
 int ySpeed;
 boolean onGround;
 int lastImage;
 boolean lastDirection;
 final static int GRAVITY = 1; //modified gravity levels

 public Player(int x, int y, int sizeX, int sizeY){  //modified michael, added images for animations- may17
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
   // left = {left1,left1,left1,left1,left1,left2,left2,left2,left2,left2};
   // right = {right1,right1,right1,right1,right1,right2,right2,right2,right2,right2};
  }
  catch(IOException e)
  {
  }
  onGround = false;
  this.x = x;
  this.y = y;
  this.sizeX = sizeX;
  this.sizeY = sizeY;
 }
 
// public boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
//    if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
//        for (int x = 0; x < img1.getWidth(); x++) {
//            for (int y = 0; y < img1.getHeight(); y++) {
//                if (img1.getRGB(x, y) != img2.getRGB(x, y))
//                    return false;
//            }
//        }
//    } else {
//        return false;
//    }
//    return true;
// }
 
 public void update(Graphics g, ArrayList<Obstacle> platforms){ //modified michael, changing of aniamtions of player when moving - may17
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
 
// public void update2(Graphics g, ArrayList<Platform> platforms){
//   ySpeed+=GRAVITY;
//   checkPlatform(platforms);
//   x+=xSpeed;
//   y+=ySpeed;
//   //g.setColor(Color.RED);
//   //g.fillRect(x, y, sizeX, sizeY);
//   if (xSpeed == 0)
//     g.drawImage(idleLeft, x, y, x+sizeX, y+sizeY, 0, 0, 16, 51, null);
//   if (xSpeed > 0 && ySpeed == 0)
//   {
//     g.drawImage(right1, x, y, x+sizeX, y+sizeY, 0, 0, 16, 51, null);
//   }
//   if (xSpeed < 0 && ySpeed == 0)
//   {
//     g.drawImage(left1, x, y, x+sizeX, y+sizeY, 0, 0, 16, 51, null);
//   }
//   if (xSpeed > 0 && ySpeed > 0)
//     g.drawImage(jumpRight, x, y, x+sizeX, y+sizeY, 0, 0, 16, 51, null);
//   if (xSpeed < 0 && ySpeed > 0)
//     g.drawImage(jumpLeft, x, y, x+sizeX, y+sizeY, 0, 0, 16, 51, null);
// }
 
 private void checkPlatform(ArrayList<Obstacle> platforms){

  boolean intersected = false;
  onGround = false;
  Rectangle playerRect = new Rectangle(x+xSpeed, y+ySpeed, sizeX, sizeY);
  if(!(new Rectangle(0, 0, 400, 400)).contains(playerRect)){
   xSpeed = 0;
  }
  for(Obstacle i: platforms){
   Rectangle platformRect = new Rectangle(i.getX(), i.getY(), i.getXSize(), i.getYSize());
   if(playerRect.intersects(platformRect)){
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
    }else{
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
    }else{
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
 }

  public int getX(){return x;}

  public int getY(){return y;}

  public int getXSpeed(){return xSpeed;}

  public int getYSpeed(){return ySpeed;}

  public void jump(int speed){
   if(onGround)
    ySpeed=0-speed;
  }

  public void move(int speed){
   xSpeed=speed;
   if(xSpeed>0)
    lastDirection = true;
   else if(xSpeed<0)
    lastDirection = false;
  }
 }
