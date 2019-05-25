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

public abstract class Obstacle{
	private int x;
	private int y;
	private int xSize;
	private int ySize;
	private BufferedImage image;

	public Obstacle(int x, int y, int xSize, int ySize){
		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
	}

	public int getX(){return x;}

	public int getY(){return y;}

	public int getXSize(){return xSize;}

	public int getYSize(){return ySize;}

	public void setX(int x){this.x = x;}

	public void setY(int y){this.y = y;}

	public void setImage(BufferedImage bi){ image = bi;}

	public BufferedImage getImage(){
		return image;
	}

	public abstract void update(Graphics g);
}