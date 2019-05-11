import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;

public class Game extends Canvas implements ActionListener{
	//Arraylist of objects, like platforms
	Timer timer;
	boolean gameOver;
	BufferedImage background;

	public Game(int level){
		super();
		addKeyListener(new PlayerListener());
		try{background = ImageIO.read(new File("LogoMakr_7PqrnC.png"));}catch(IOException e){}//Remember to add the actual background
		gameOver = false;
		setSize(400, 400);
		timer = new Timer(20, this);
		timer.setInitialDelay(1);
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
		
	}

	public void level2(){}

	public void level3(){}

	public void paint(Graphics g){
		update(g);
	}

	public void update(Graphics g){
		//RedrawBackround
		//Update all items on screen
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