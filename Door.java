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

//FENGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
//REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
public class Door extends Obstacle{
  
  public Door (int x, int y, int xSize, int ySize, BufferedImage image)
  {
    super (x, y, xSize, ySize);
    setImage(image);
  }
  
  public void update (Graphics g) //implemented update
  {
    g.drawImage(getImage(), getX(), getY(), getX() + getXSize(), getY() + getYSize(), getImage().getWidth(), getImage().getHeight(), null);
  }
  
  public boolean nearPlayer (Player p)
  {
    return false;
  }
  
}