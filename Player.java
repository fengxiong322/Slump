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
		ySpeed+=GRAVITY;
		checkPlatform(platforms);
		x+=xSpeed;
		y+=ySpeed;
		g.setColor(Color.RED);
		g.fillRect(x, y, sizeX, sizeY);
		//g.drawImage(player, x, y, x+sizeX, y+sizeY, 0, 0, 100, 100, null);
	}

	private void checkPlatform(ArrayList<Platform> platforms){

		boolean intersected = false;
		onGround = false;
		Rectangle playerRect = new Rectangle(x+xSpeed, y+ySpeed, sizeX, sizeY);
		if(!(new Rectangle(0, 0, 400, 400)).contains(playerRect)){
			xSpeed = 0;
		}
		for(Platform i: platforms){
			Rectangle platformRect = new Rectangle(i.getX(), i.getY(), i.getLength(), 30);
			if(playerRect.intersects(platformRect)){
				intersected = true;
				if(ySpeed>0){
					for(int j = 0; j <= ySpeed;j++){
						playerRect.setLocation(x, y+j);
						if(playerRect.intersects(platformRect)){
							ySpeed = j-1;
							onGround = true;
							break;
						}
					}
				}else{
					for(int j = 0; j >= ySpeed;j--){
						playerRect.setLocation(x, y+j);
						if(playerRect.intersects(platformRect)){
							ySpeed = j+1;
							break;
						}
					}
				}
				if(xSpeed>0){
					for(int j = 0; j <= xSpeed;j++){
						playerRect.setLocation(x+j, y);
						if(playerRect.intersects(platformRect)){
							xSpeed = j-1;
							break;
						}
					}
				}else{
					for(int j = 0; j >= xSpeed;j--){
						playerRect.setLocation(x+j, y);
						if(playerRect.intersects(platformRect)){
							xSpeed = j+1;
							break;
						}
					}
				}
			}
		}
	}

		public int getX(){return x;}

		public int getY(){return y;}

		public int getXSpeed(){return xSpeed;}

		public int getYSpeed(){return ySpeed;}

		public void jump(int speed){
			if(onGround)
				ySpeed=0-speed;
		}

		public void move(int speed){
			xSpeed=speed;
		}
	}
