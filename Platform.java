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

public class Platform extends Obstacle{
	private int speed;
	private int startDist;//start pos
	private int endDist;//end pos
	private boolean right;//if going right, true

	public Platform(int x, int y, int length, int speed, int endDist){
		super(x, y, length, 30);
		try{setImage(ImageIO.read(new File("platform.png")));}catch(IOException e){e.printStackTrace();}
		this.speed = speed;
		this.startDist = x;
		this.endDist = endDist;
		right = true;
	}

	public void update(Graphics g){
		g.drawImage(getImage(), getX(), getY(), getX() + getXSize(), getY() + getYSize(), 5, 5, 90, 90, null);
		if(getX()<startDist){
			right = true;
		}else if(getX()>endDist){
			right = false;
		}
		if(right)
			setX(getX()+speed);
		else
			setX(getX()-speed);

	}
}