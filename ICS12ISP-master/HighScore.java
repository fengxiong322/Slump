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
* @author Feng
* @version 2.0
* Total time spent Feng: 1 hour
* Modifications: Added file management 1 hour May 26
*/
public class HighScore extends Canvas{
	BufferedImage background;
	String[] lines;
	String path;
	BufferedReader br;
	ExitListener el;

	public HighScore(ExitListener el){//Added constructor Feng May 22 1 hour
		//Reading files Feng May 22 30 min
		setSize(811, 821);
		this.el = el;
		try{
			background = ImageIO.read(new File("Highscores.jpg"));//Change this picture
		}catch(IOException e){
			e.printStackTrace();
		}
	}


	public void paint(Graphics g){//Created empy paint method Feng 20min

	}
}
