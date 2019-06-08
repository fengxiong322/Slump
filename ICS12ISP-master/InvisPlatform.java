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

public class InvisPlatform extends Platform{
	private Rectangle player;

  public InvisPlatform(int x, int y, int length){
    super(x, y, length);
    setOn(true);
    try{setImage(ImageIO.read(new File("Blocks/invis.jpg")));}catch(IOException e){e.printStackTrace();}
  }
  
  public void update(Graphics g){
    if(player.intersects(new Rectangle(getX()-50, getY()-50, getXSize()+100, getYSize()+100)))
		  g.drawImage(getImage(), getX(), getY(), getXSize(), getYSize(), null);
	}

	public void setPlayer(Rectangle rectangle){
		player = rectangle;
	}
}
