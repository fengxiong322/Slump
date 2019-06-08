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
* Total time spent Feng: 0.25 hours
* Total time spend Michael: 0.25 hours
* Modifications: May 26, 2019, Michael Zhou and feng, Total time: 0.5 hours
* created class and basic layout, implemented methods
* Modifications: May 26, 2019, Michael Zhou, Total time: 0.05 hours
* added get methods for xSize and ySize
*/

public abstract class Obstacle{
 private int x;
 private int y;
 private int xSize;
 private int ySize;
 private BufferedImage image;
 private boolean isOn;

 public Obstacle(int x, int y, int xSize, int ySize){
  this.x = x;
  this.y = y;
  this.xSize = xSize;
  this.ySize = ySize;
 }

 public boolean getOn(){
 	return isOn;
 }

 public void setOn(boolean isOn){
 	this.isOn = isOn;
 }

 public int getX(){return x;}

 public int getY(){return y;}

 public int getXSize(){return xSize;}

 public int getYSize(){return ySize;}

 public void setX(int x){this.x = x;}
 
 public void setXSize(int x){this.xSize = x;} //michael added get method May 25, 2019, 1 min

 public void setY(int y){this.y = y;}
 
 public void setYSize(int y){this.ySize = y;} //michael added get method May 25, 2019, 1 min

 public void setImage(BufferedImage bi){ image = bi;}

 public Rectangle getBounds(){
 	return new Rectangle(x, y, xSize, ySize);
 }

 public BufferedImage getImage(){
  return image;
 }

 public abstract void update(Graphics g);

}
