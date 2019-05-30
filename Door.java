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
* impleneted all the needed methods from OBstacle
*added constuctor
*/
public class Door extends Rectangle{
  BufferedImage door;

  public Door (int x, int y, int xSize, int ySize, BufferedImage image)
  {
    super (x, y, xSize, ySize);
    door = image;
  }
  
  public void update (Graphics g) //implemented update
  {
    g.drawImage(door, (int)getX(), (int)getY(), (int)getWidth(), (int)getHeight(), null);
  }
  
}