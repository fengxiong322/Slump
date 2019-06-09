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
 * Represents a moving projectile in the game
 * @author Feng Xiong, Michael Zhou
 * @version 2.0
 * Total time spent Feng: 1.5 hours
 */
public class Projectile extends Obstacle{
  /**
   * Sets the speed of the projectile in pixels per tick
   */
  private int speed;

  /**
   * The start x coord, the same value as when the projectile was initialized
   */
  private int startDist;

  /**
   * The end x coord, indicates when the projectile willbe sent back to startDist
   */
  private int endDist;

  /**
   * Indicates direction
   */
  private boolean right;

  /** Constructor
   * @param x current x position
   * @param y current y position
   * @param isRight Indicates the direction
   * @param speed The speed at which the projectile will travel
   * @param endDist Ends at this distance
   */
  public Projectile(int x, int y, boolean isRight, int speed, int endDist){ //Added May 17, 2019 - Feng
    super(x, y, 30, 30);
    setOn(false);
    try{
      setImage(ImageIO.read(new File("Blocks/projectile.png")));
    }catch(IOException e){
      e.printStackTrace();
    }
    right = isRight;
    this.speed = speed;
    this.startDist = x;
    this.endDist = endDist;
  }

  /**
   * {@inheritDoc}
   */  
  public void update(Graphics g){//Added May 17, 2019 - Feng
    g.drawImage(getImage(), getX(), getY(), getX() + getXSize(), getY() + getYSize(), 0, 0, getImage().getWidth(), getImage().getHeight(), null);
    if(right){
      setX(getX()+speed);
    if(getX()>endDist)
      setX(startDist);
    }else{
      setX(getX()-speed);
    if(getX() < endDist)
      setX(endDist);
    }
  }
}
