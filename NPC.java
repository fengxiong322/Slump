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
* @version 2.0
* Total time spend Michael: 0.5 hours
* Modifications: May 24, 2019, Michael Zhou, Total time: 0.5 hours
* added basic class structure
* impleneted all the needed methods from OBstacle
*added constuctor
*/

public class NPC extends Obstacle {
  String dialogue;
  
  //May 24, Michael created constructor for class
  public NPC (int x, int y, int xSize, int ySize, BufferedImage image, String text) //added constuctor
  {
    super (x, y, xSize, ySize);
    setImage(image);
    dialogue = text;
  }
  
  //May 24, Michael created basic method outiline 
  public String speak () //added speak method for testing
  {//Should speak, and then close the speach bubble
    return dialogue;
  }
  
  //May 24, Michael implemented method
  public void update (Graphics g) //implemented update
  {
    g.drawImage(getImage(), getX(), getY(), getX() + getXSize(), getY() + getYSize(), 5, 5, 90, 90, null);
  }
}
