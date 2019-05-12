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
		int temp;
		if(onGround){
			temp = checkObstacle(platforms, x+xSpeed, y);
		}else
			temp = checkObstacle(platforms, x+xSpeed, y-ySpeed+GRAVITY);
		if(temp == 1){//If there is a platform where the block wants to go
			ySpeed = 0;			
			xSpeed = 0;
			onGround = true;
		}else{
			ySpeed-=GRAVITY;
			y-=ySpeed;
			x+=xSpeed;
			onGround = false;
		}
		g.drawImage(player, x, y, x+sizeX, y+sizeY, 0, 0, 100, 100, null);
	}

	//0 is no platform. 1 is hit something but no floor. 2 is has floor
	private int checkObstacle(ArrayList<Platform> platforms, int newx, int newy){//May need another param for cannons
		Rectangle playerRect = new Rectangle(newx, newy-100, sizeX, sizeY);
		for(Platform i: platforms){

			Rectangle platformRect = new Rectangle(i.getX(), i.getY(), i.getLength(), 30);
			System.out.println(playerRect);
			System.out.println(platformRect);
			if(playerRect.intersects(platformRect)){
				return 1;
			}
		}
		return 0;
	}

	public int getX(){return x;}

	public int getY(){return y;}

	public int getXSpeed(){return xSpeed;}

	public int getYSpeed(){return ySpeed;}

	public void jump(){
		System.out.println("jump");
		if(onGround)
			ySpeed+=5;
	}

	public void right(int speed){
		System.out.println("right");
		xSpeed=speed;
	}

	public void left(int speed){
		System.out.println("left");
		xSpeed=-speed;
	}
}