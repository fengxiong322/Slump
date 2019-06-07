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

/** Represents a moving projectile in the game
* @author Feng,
* @version 2.0
* Total time spent Feng: 1.5 hours
*/
public class Projectile extends Obstacle{
 private int speed;
 private int startDist;//start pos
 private int endDist;//end pos
 private boolean right;//if going right, true

 //Added May 17, 2019 - Feng
 /** Constructor
 * @param
 */
 public Projectile(int x, int y, boolean isRight, int speed, int endDist){
  super(x, y, 30, 30);
  setOn(false);
  try{
   setImage(ImageIO.read(new File("Blocks/projectile.png")));
  }catch(IOException e){
   e.printStackTrace();
  }
  right = isRight;
  this.speed = speed;
  this.startDist = x;
  this.endDist = endDist;
 }

 //Added May 17, 2019 - Feng
 public void update(Graphics g){
  g.drawImage(getImage(), getX(), getY(), getX() + getXSize(), getY() + getYSize(), 0, 0, getImage().getWidth(), getImage().getHeight(), null);
  if(right){
   setX(getX()+speed);
   if(getX()>endDist)
    setX(startDist);
  }else{
   setX(getX()-speed);
   if(getX() < endDist)
    setX(endDist);
  }
 }
}
