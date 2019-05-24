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

public class Projectiles{
	BufferedImage generator;
	BufferedImage projectiles;
	private int startX;
	private int startY;
	private int length;
	private int speed;
	private int startDist;//start pos
	private int endDist;//end pos
	private boolean right;//if going right, true

	public Projectiles(int startX, int startY, boolean isRight, int speed, int endDist){
		this.startX = startX;
		this.startY = startY;
		right = isRight;
		this.speed = speed;
		this.startDist = startX;
		this.endDist = endDist;
		int counter;
		try{
			generator = ImageIO.read(new File("platform.png"));
			projectiles = ImageIO.read(new File("platform.png"));
			counter = 0;
		}catch(IOException e){
			System.out.println(e.printStackTrace());
		}
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
		g.fillRect(startX, startY, length, 30);//Generator
		counter++;
		if(right){
			if(end)
		}
	}
}