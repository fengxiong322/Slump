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

public class Platform{
	BufferedImage platform;
	private int startX;
	private int startY;
	private int length;
	private int speed;
	private int startDist;//start pos
	private int endDist;//end pos
	private boolean right;//if going right, true

	public Platform(int startX, int startY, int length, int speed, int endDist){
		try{platform = ImageIO.read(new File("platform.png"));}catch(IOException e){}
		this.startX = startX;
		this.startY = startY;
		this.length = length;
		this.speed = speed;
		this.startDist = startX;
		this.endDist = endDist;
		right = true;
	}

	public int getX(){
		return startX;
	}

	public int getY(){
		return startY;
	}

	public int getLength(){
		return length;
	}

	public void update(Graphics g){
		g.setColor(new Color(0, 255, 0));
		g.fillRect(startX, startY, length, 30);
		//g.drawImage(platform, startX, startY, startX + length, startY+30, 0, 0, 100, 100, null);
		if(startX<startDist){
			right = true;
		}else if(startX>endDist){
			right = false;
		}
		if(right)
			startX+=speed;
		else
			startX-=speed;

	}
}