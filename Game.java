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


public class Game extends Canvas implements ActionListener{
	ArrayList<Platform> platforms;
	Timer timer;
	boolean gameOver;
	BufferedImage background;
	BufferedImage canvas;

	public Game(int level){
		super();
		setSize(400, 400);
		addKeyListener(new PlayerListener());
		canvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		platforms = new ArrayList<Platform>();
		try{background = ImageIO.read(new File("companylogo.png"));}catch(IOException e){}//Remember to add the actual background
		gameOver = false;
		timer = new Timer(20, this);
		timer.setInitialDelay(10);
		timer.start();
		if(level == 1){
			level1();
		}else if(level == 2){
			level2();
		}else{
			level3();
		}
	}

	public void level1(){
		platforms.add(new Platform(50, 50, 30, 2, 150));
	}

	public void level2(){}

	public void level3(){}

	public void paint(Graphics g){
		update(g);
	}

	public void update(Graphics g){
		Graphics g1 = canvas.getGraphics();//Draw the graphics on a seperate picture so that we can add pictures without flickering
		//RedrawBackround
		g1.setColor(new Color(0, 0, 0));
		g1.fillRect(0, 0, getWidth(), getHeight());
		g1.drawImage(background, 0, 0, null);
		//Update all items on screen
		for(Platform i : platforms){
			g1.drawImage(i.getImage(), i.getStartX(), i.getStartY(), i.getStartX()+i.getLength(), i.getStartY()+20, 0, 0, 100, 100, null);
			i.update();//Updates to a new position
		}
		g.drawImage(canvas, 0, 0, null);
	}

	public void actionPerformed(ActionEvent e) {
		//update new positions
		if(!gameOver)
			timer.restart();
		else{
			timer.stop();
		}
		repaint();
	}

	class PlayerListener extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent event){
			char ch = event.getKeyChar();
			//Keep track of key presses
		}
	}
}