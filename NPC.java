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
 Modifications: May 29, 2019, Michael Zhou, Total time: 0.5 hours
* modified the speak method so the the dialogue box will be properly formaed on the screen
* made the text properly formated on the screen, if there is a \n the the text will start on a new line as inteneded
*/

public class NPC extends Obstacle {
  private String dialogue;
  BufferedImage dialogueBox;
  String answer;
  String [] options;
  boolean question;
  
  //May 24, Michael created constructor for class
  public NPC (int x, int y, int xSize, int ySize, BufferedImage image, String text) //added constuctor
  {
    super (x, y, xSize, ySize);
    setOn(true);
    setImage(image);
    try{dialogueBox = ImageIO.read(new File("Dialogue/dialogue.png"));}catch(IOException e){}
    dialogue = text; 
  }
  
  //May 24, Michael created basic method outiline 
  //Modifications: May 29, 2019, Michael Zhou, Total time: 0.5 hours
  //modified the speak method so the the dialogue box will be properly formaed on the screen
  //made the text properly formated on the screen, if there is a \n the the text will start on a new line as inteneded
  public void speak (Graphics g) //added speak method for testing
  {//Should speak, and then close the speach bubble
    //modified the speak method so the the dialogue box will be properly formaed on the screen may 29 michael
    //  made the text properly formated on the screen, if there is a \n the the text will start on a new line as inteneded, May 29 michael
    g.setFont(new Font("SansSerif", Font.PLAIN, 20)); 
    if (dialogue.indexOf ("/q") < 0)
    {
      question = false;
      dialogue += "\n\nPress any key to close...";
      g.drawImage(dialogueBox, 0, 600,800,800, 0,0,1520, 470, null); 
    int y = 650 - g.getFontMetrics().getHeight();
    for (String line : dialogue.split("/n"))
    {
        g.drawString(line, 50, y += g.getFontMetrics().getHeight());
    }
    }
    else 
    {
      question = true;
      int y = 650 - g.getFontMetrics().getHeight();
      String [] temp = new String [5];
      temp = dialogue.split ("/q");
      String text = temp [0];
      options = new String [4];
      options [0] = temp [1];
      options [1] = temp [2];
      options [2] = temp [3];
      answer = "";
      for (int i = 0; i <= 2; i ++)
      {
        if (options [i].indexOf ("/a") >= 0)
        {
          String [] t =  options[i].split ("/a");
          answer = t [1];
          options [i+1] = answer;
          options[i] = t [0];
        }
      }
      
      g.drawImage(dialogueBox, 0, 530,800,800, 0,0,1520, 470, null); 
    for (String line : text.split("/n"))
    {
        g.drawString(line, 50, y += g.getFontMetrics().getHeight());
    }
    g.drawString("Press the number that coorasponds with your answer:", 50, y += g.getFontMetrics().getHeight());
    g.drawString("1. " + options [0], 50, y += g.getFontMetrics().getHeight());
    g.drawString("2. " + options [1], 50, y += g.getFontMetrics().getHeight());
    g.drawString("3. " + options [2], 50, y += g.getFontMetrics().getHeight());
    g.drawString("4. " + options [3], 50, y += g.getFontMetrics().getHeight());
    }
  }
  
  public String getAnswer ()
  {
    return answer;
  }
  
  public boolean isQuestion ()
  {
    return question;
  }
  public String getOption (int optionNumber)
  {
    return options [optionNumber-1];
  }
  
  //May 24, Michael implemented method
  public void update (Graphics g) //implemented update
  {
    g.drawImage(getImage(), getX(), getY(), getX() + getXSize(), getY() + getYSize(), 0, 0, getImage().getWidth(), getImage().getHeight(), null);
  }
}
