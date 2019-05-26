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

public class HighScore extends Canvas{
	BufferedImage background;
	File information;

	public HighScore(){
		try{
			background = ImageIO.read(new File("platform.png"));//Change this picture
		}catch(IOException e){
			e.printStackTrace();
		}
		repaint();
	}


	public void paint(Graphics g){

	}


}