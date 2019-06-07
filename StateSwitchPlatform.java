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

public class StateSwitchPlatform extends Obstacle{//Added June 2 by feng, 0.5 hour
	boolean type;

 public StateSwitchPlatform(int x, int y, int length, boolean type){
  super(x, y, length, 30);
  if(type)
  	try{setImage(ImageIO.read(new File("Blocks/grey.png")));}catch(IOException e){e.printStackTrace();}
  else
  	try{setImage(ImageIO.read(new File("Blocks/grey.png")));}catch(IOException e){e.printStackTrace();}
  this.type = type;
  setOn(type);
 }

 public void update(Graphics g){
 	if(getOn())
   for (int i = 0; i <= getXSize(); i+=30)
  g.drawImage(getImage(), getX()+i, getY(), getX() + getXSize(), getY() + getYSize(), 0, 0, getImage().getWidth(), getImage().getHeight(), null);
 }
}