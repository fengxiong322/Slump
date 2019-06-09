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
import java.awt.Rectangle;

/**
* This is an abstract class for all obstacles in the game. This allows for easier traversal and manipulation of objects on the map.
* @author Feng, Michael
* @version 2.0
* Total time spent Feng: 0.25 hours
* Total time spend Michael: 0.25 hours
* Modifications: May 26, 2019, Michael Zhou and feng, Total time: 0.5 hours
* created class and basic layout, implemented methods
* Modifications: May 26, 2019, Michael Zhou, Total time: 0.05 hours
* added get methods for xSize and ySize
*/

public abstract class Obstacle{
	/**
	 * The current x pixel position on the screen, left = 0
	 */
	private int x;

	/**
	 * The current y pixel position on the screen, top = 0
	 */
	private int y;

	/**
	 * The width of this object. Can be resized
	 */
	private int xSize;

	/**
	 * The height of this object. Can be resized
	 */
	private int ySize;

	/**
	 * A picture of the object, so it can be drawn
	 */
	private BufferedImage image;

	/**
	 * true if the player can interact with this object
	 */
	private boolean isOn;


	/**
	 * Constructor
	 *
	 * @param x sets the current x pixel coordinate
	 * @param y sets the current y pixel coordinate
	 * @param xSize sets the current width
	 * @param ySize sets the current height
	 */
	public Obstacle(int x, int y, int xSize, int ySize){
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
	}

	/**
	 * @return returns if the player can interact with this obstacle
	 */
	public boolean getOn(){
		return isOn;
	}

	/**
	 * @param isOn sets if the player can interact with this obstacle
	 */
	public void setOn(boolean isOn){
		this.isOn = isOn;
	}

	/** 
	 * Getter for x
	 */
	public int getX(){return x;}
	
	/** 
	 * Getter for y
	 */
	public int getY(){return y;}

	/** 
	 * Getter for width
	 */
	public int getXSize(){return xSize;}

	/** 
	 * Gets height
	 */
	public int getYSize(){return ySize;}

	/** 
	 * Sets the x pixel position
	 */
	public void setX(int x){this.x = x;}

	/** 
	 * Sets the width
	 */
 	public void setXSize(int x){this.xSize = x;} //michael added get method May 25, 2019, 1 min

	/** 
	 * Sets the height of the object
	 */
 	public void setY(int y){this.y = y;}

	/** 
	 * Gets the height of the object
	 */
 	public void setYSize(int y){this.ySize = y;} //michael added get method May 25, 2019, 1 min

	/** 
	 * Sets the image
	 */
 	public void setImage(BufferedImage bi){ image = bi;}

	/** 
	 * Getter for the bounds of the obstacle. For convenience during collision detection
	 */
 	public Rectangle getBounds(){
 		return new Rectangle(x, y, xSize, ySize);
 	}

	/** 
	 * Getter for the image of the object
	 */
 	public BufferedImage getImage(){
 		return image;
 	}

	/** 
	 * Updates the image onto the screen
	 *
	 * @param g the Graphics object to draw onto
	 */
 	public abstract void update(Graphics g);

}
