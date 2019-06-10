import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;


/**
 * This is the generator class. It is a platform that represents a "cannon". In the game, it is used to block the projectile spawn point.
 * @author Michael Zhou, Feng Xiong
 * @version 4.0
 */
public class Generator extends Obstacle{

	/**
	 * This is the constructor for the Generator. Very similar to the platform init.
	 */
	public Generator(int x, int y, int length){
		super(x, y, length, 30);
		setOn(true);
		try{setImage(ImageIO.read(new File("Blocks/grey.png")));}catch(IOException e){e.printStackTrace();}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Graphics g){
		for (int i = 0; i <= getXSize(); i+=30)
			g.drawImage(getImage(), getX()+i, getY(), getX() + getXSize(), getY() + getYSize(), 0, 0, getImage().getWidth(), getImage().getHeight(), null);
	}
}