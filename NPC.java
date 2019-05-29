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
  private String dialogue;
  BufferedImage dialogueBox;
  
  //May 24, Michael created constructor for class
  public NPC (int x, int y, int xSize, int ySize, BufferedImage image, String text) //added constuctor
  {
    super (x, y, xSize, ySize);
    setImage(image);
    try{dialogueBox = ImageIO.read(new File("Dialogue/dialogue.png"));}catch(IOException e){}
    dialogue = text + "\n\nPress any key to close...";
  }
  
  //May 24, Michael created basic method outiline 
  public void speak (Graphics g) //added speak method for testing
  {//Should speak, and then close the speach bubble
    g.drawImage(dialogueBox, 0, 600,800,800, 0,0,1520, 470, null);
    g.setFont(new Font("SansSerif", Font.PLAIN, 20)); 
    int y = 670 - g.getFontMetrics().getHeight();
    for (String line : dialogue.split("\n"))
    {
        g.drawString(line, 50, y += g.getFontMetrics().getHeight());
    }
  }
  
  //May 24, Michael implemented method
  public void update (Graphics g) //implemented update
  {
    g.drawImage(getImage(), getX(), getY(), getX() + getXSize(), getY() + getYSize(), 0, 0, getImage().getWidth(), getImage().getHeight(), null);
  }
}
