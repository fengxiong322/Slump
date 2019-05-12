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
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		onGround = true;
	}

	public void update(Graphics g, ArrayList<Platform> platforms){
		int temp;
		if(onGround)
			temp = checkObstacle(platforms, x+xSpeed, y-ySpeed);
		else
			temp = checkObstacle(platforms, x+xSpeed, y-ySpeed+GRAVITY);
		if(temp == 0){
			if(!onGround)
				ySpeed-=GRAVITY;
			y-=ySpeed;
			x+=xSpeed;
			onGround = false;
		}else{
			if(temp == 2)
				onGround = true;
			ySpeed = 0;
			x+=xSpeed;
		}
		g.drawImage(player, x, y, x+sizeX, y+sizeY, 0, 0, 100, 100, null);
	}

	public int checkObstacle(ArrayList<Platform> platforms, int newx, int newy){//May need another param for cannons
		for(Platform i: platforms){
			if((newx>i.getX()+i.getLength() || newx+sizeX>i.getX()) && (newy>i.getY()+30 || newy+sizeY>i.getY())){
				if(y>=i.getY()+30){
					return 2;
				}
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
		ySpeed+=10;
	}

	public void right(int speed){
		xSpeed=speed;
	}

	public void left(int speed){
		xSpeed=-speed;
	}
}