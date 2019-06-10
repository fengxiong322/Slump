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

/**
 * This is the standard platform, on which the player can stand on.
 * @author Feng Xiong, Michael Zhou
 * @version 4.0
 * Total time spent Feng: 0.75 hours
 * Total time spend Michael: 0.5 hours
 */

public class Platform extends Obstacle{

 /**
  * Constructor.
  *
  * @param x sets the starting x position of the platform. Will always stay at this position
  * @param y sets the starting y position of the platform. Will always stay at this position
  * @param length sets the length (30)
  */
  public Platform(int x, int y, int length){
    super(x, y, length, 30);
    int r = (int) (Math.random () * 3) + 1;//Random number from 1 -3
    //Depending on r, a different style of block will be chosen
    if (r == 1)
      try{setImage(ImageIO.read(new File("Blocks/darkstone.jpg")));}catch(IOException e){e.printStackTrace();}
    else if (r ==2)
      try{setImage(ImageIO.read(new File("Blocks/stone.jpg")));}catch(IOException e){e.printStackTrace();}
    else
      try{setImage(ImageIO.read(new File("Blocks/grey.png")));}catch(IOException e){e.printStackTrace();} 
    setOn(true);
  }

  /**
   * {@inheritDoc}
   */
  public void update(Graphics g){
    for (int i = 0; i <= getXSize(); i+=30)
    g.drawImage(getImage(), getX()+i, getY(), getX() + getXSize(), getY() + getYSize(), 0, 0, getImage().getWidth(), getImage().getHeight(), null);
  }
}
