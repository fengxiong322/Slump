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

public class Map{//Added optimization for collisions.
	Obstacle[][] collidableObjects;

	public Map(int x, int y){//Map size
		collidableObjects = new Obstacle[x][y];
	}

	public void add(Obstacle o, int x, int y){
		collidableObjects[x][y] = o;
	}

	public ArrayList<Obstacle> find(int cordX, int cordY, int sizeX, int sizeY){
		int left = cordX/30;
		int right = (cordX+sizeX)/30;
		int top = cordY/30;
		int bottom = (cordY+sizeY)/30;
		ArrayList<Obstacle> overlaps = new ArrayList<Obstacle>();
		for(int i = top;i<=bottom; i++)
			for(int j = left;j<=right;j++){
				if(collidableObjects[j][i]!=null)
				overlaps.add(collidableObjects[j][i]);
			}
		return overlaps;
	}
}