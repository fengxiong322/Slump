import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;

public class NPC extends Obstacle {
  String dialogue;
  
  public NPC (int x, int y, int xSize, int ySize, BufferedImage image, String text)
  {
    super (x, y, xSize, ySize);
    setImage(image);
    dialogue = text;
  }
  
  public void speek ()
  {
    System.out.print (dialogue);
  }
  
  public void update (Graphics g)
  {
    g.drawImage(getImage(), getX(), getY(), getX() + getXSize(), getY() + getYSize(), 5, 5, 90, 90, null);
  }
}