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
	Player player;
	int canvasX;
	int canvasY;

	public Game(int level){
		super();
		setSize(400, 400);
		canvasX = 0;
		canvasY = 0;
		addKeyListener(new PlayerListener());
		canvas = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
		platforms = new ArrayList<Platform>();
		gameOver = false;
		timer = new Timer(20, this);
		timer.setInitialDelay(30);
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
		setSpawn(40, 200);
		try{background = ImageIO.read(new File("platform.png"));}catch(IOException e){}//Remember to add the actual background
		platforms.add(new Platform(50, 50, 30, 2, 150));
		platforms.add(new Platform(0, 380, 400, 0, 0));
		platforms.add(new Platform(100, 360, 30, 0, 0));
	}

	public void level2(){}

	public void level3(){}

	public void setSpawn(int x, int y){
		player = new Player(x, y, 20, 20);
	}

	public void paint(Graphics g){
		update(g);
	}

	public void update(Graphics g){
		Graphics g1 = canvas.getGraphics();//Draw the graphics on a separate picture so that we can add pictures without flickering
		//RedrawBackround
		g1.setColor(new Color(0, 0, 0));
		g1.fillRect(0, 0, 400, 400);
		g1.drawImage(background, 0, 0,1000, 1000, null);
		//Update all items on screen
		for(Platform i : platforms){
			i.update(g1);//Updates to a new position
		}
		player.update(g1, platforms);
		if(player.getX()+canvasX<getWidth()*0.25){
			canvasX+=2;
		}
		if(player.getX()+canvasX>getWidth()*0.75){
			canvasX-=2;
		}
		if(player.getY()+canvasY<getHeight()*0.25){
			canvasY+=2;
		}
		if(player.getY()+canvasY>getHeight()*0.75){
			canvasY-=2;
		}

		g.drawImage(canvas, canvasX, canvasY, null);
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
			int ch = event.getKeyCode();//Keep track of key presses
			if(ch == KeyEvent.VK_UP || ch == KeyEvent.VK_W){
				player.jump();
			}else if(ch == KeyEvent.VK_LEFT|| ch == KeyEvent.VK_A){
				player.left(2);
			}else if(ch == KeyEvent.VK_RIGHT || ch == KeyEvent.VK_D){
				player.right(2);
			}
		}

		@Override
		public void keyReleased(KeyEvent event){
			int ch = event.getKeyCode();//Keep track of key presses
			if(ch == KeyEvent.VK_LEFT|| ch == KeyEvent.VK_A){
				player.left(0);
			}else if(ch == KeyEvent.VK_RIGHT || ch == KeyEvent.VK_D){
				player.right(0);
			}
		}
	}
}