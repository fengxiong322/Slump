import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;


/**
* @author Michael
* @version 3.0
* Total time spend Michael: 0.5 hours
* Modifications: May 24, 2019, Michael Zhou, Total time: 0.5 hours
* added basic class structure
* impleneted all the needed methods from Obstacle
*added constuctor
*/
public class Door extends Obstacle{

  public Door (int x, int y, int xSize, int ySize)
  {
    super (x, y, xSize, ySize);
    try{setImage(ImageIO.read(new File("Player/idleLeft.png")));}catch(IOException e){}
  }
  
  public void update (Graphics g) //implemented update
  {
    g.drawImage(getImage(), (int)getX(), (int)getY(), (int)getXSize(), (int)getYSize(), null);
  }

  
  
}