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
* @author Feng, Michael
* @version 2.0
* Total time spent Feng: 0.75 hours
* Total time spend Michael: 0.5 hours
*/

public class Platform extends Obstacle{

	public Platform(int x, int y, int length, int speed, int endDist){
		super(x, y, length, 30);
		try{setImage(ImageIO.read(new File("platform.png")));}catch(IOException e){e.printStackTrace();}
	}

	public void update(Graphics g){
		g.drawImage(getImage(), getX(), getY(), getX() + getXSize(), getY() + getYSize(), 5, 5, 90, 90, null);
	}
}
