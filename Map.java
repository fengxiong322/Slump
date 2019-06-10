import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

/**
* @author Feng Xiong, Michael Zhou
* @version 4.0
* Total time spent Feng: 1 hour
* Modifications: Added class for collision optimization Feng 1 hour
*/
public class Map{//Added optimization for collisions.
	Obstacle[][] collidableObjects;

	/**
   * Constructor for the Obstacles, initializes the 2D array
   * @param x The number of possible Obstacle positions on the x axis.
   * @param y THe number of poosible Obstacle positions on the y axis.
   */
	public Map(int x, int y){//Map size
		collidableObjects = new Obstacle[x][y];
	}

	/**
   * Adds an Obstacle object to the array.
   */
	public void add(Obstacle o, int x, int y){
		collidableObjects[x][y] = o;
	}


	/**
   * Finds obstacles that intersect with the inputed params
   * @param cordX the left coord
   * @param cordY the top coord
	 * @param sizeX x size of the object
	 * @param sizeY y size of the object
   */
	public ArrayList<Obstacle> find(int cordX, int cordY, int sizeX, int sizeY){//Implemented the search.
		//Calculates the range of values to search.
		int left = cordX/30;//Finds the left grid coordinate of the player
		int right = (cordX+sizeX)/30; //Finds the right grid coordinate of the player
		int top = cordY/30;//Finds the top grid coordinate of the player
		int bottom = (cordY+sizeY)/30;//Finds the bottom grid coordinate of the player
		//Discovers obstacles that intersect wirh the player.
		ArrayList<Obstacle> overlaps = new ArrayList<Obstacle>();
		for(int i = top;i<=bottom; i++)
			for(int j = left;j<=right;j++){
				if(collidableObjects[j][i]!=null)
				overlaps.add(collidableObjects[j][i]);
			}
		return overlaps;
	}
}