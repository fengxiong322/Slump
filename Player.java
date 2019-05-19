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

public class Player{
	int x;
	int y;
	int sizeX;
	int sizeY;
	BufferedImage player;
	int xSpeed;
	int ySpeed;
	boolean onGround;
	final static int GRAVITY = 1;

	public Player(int x, int y, int sizeX, int sizeY){
		try{player = ImageIO.read(new File("platform.png"));}catch(IOException e){}
		onGround = false;
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	public void update(Graphics g, ArrayList<Platform> platforms){
		if(checkPlatform(platforms)){//If there is a platform where the block wants to go
			onGround = true;//The block is now on the ground
		}else{
			ySpeed-=GRAVITY;
			y-=ySpeed;
			x+=xSpeed;
			onGround = false;
		}
		g.drawImage(player, x, y, x+sizeX, y+sizeY, 0, 0, 100, 100, null);
	}

	private boolean checkPlatform(ArrayList<Platform> platforms){
		Rectangle playerRect = new Rectangle(x+xSpeed, y-ySpeed, sizeX, sizeY);
		Rectangle playerRectWithGravity = new Rectangle(x+xSpeed, y-ySpeed+GRAVITY, sizeX, sizeY);
		for(Platform i: platforms){
				Rectangle platformRect = new Rectangle(i.getX(), i.getY(), i.getLength(), 30);
				if(playerRect.intersects(platformRect)){
					ySpeed = 0;
					xSpeed = 0;
				}
				if(playerRectWithGravity.intersects(platformRect)){
					ySpeed = 0;
					xSpeed = 0;
					return true;
				}
				
		}
		return false;
	}

	public int getX(){return x;}

	public int getY(){return y;}

	public int getXSpeed(){return xSpeed;}

	public int getYSpeed(){return ySpeed;}

	public void jump(){
		if(onGround)
			ySpeed+=10;
	}

	public void right(int speed){
		xSpeed=speed;
	}

	public void left(int speed){
		xSpeed=-speed;
		//ySpeed+=1;
	}
}
