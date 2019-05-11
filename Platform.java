import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.*;

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

	public int getStartX(){
		return startX;
	}

	public int getStartY(){
		return startY;
	}

	public int getLength(){
		return length;
	}

	public int getSpeed(){
		return speed;
	}

	public BufferedImage getImage(){
		return platform;
	}

	public void update(){
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