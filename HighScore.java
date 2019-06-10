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

/** This class represents the highscores screen in the game
* @author Feng Xiong, Michael Zhou
* @version 2.0
* Total time spent Feng: 1 hour
* Modifications: Added file management 1 hour May 26
* Total time spent Michael: 1 hour
* Modifications: Added file management 1 hour May 31
*/
public class HighScore extends Canvas{
	/** Background Image
	*/
	BufferedImage background; 
	/** Background Image
	*/
	String[] lines;
	/** Background Image
	*/
	String path;
	/** Background Image
	*/
	BufferedReader br;
	/** Background Image
	*/
	ExitListener el;
	/** Background Image
	*/
	File information;

	/** Constructor
	*/
	public HighScore(ExitListener el){//Added constructor Feng May 22 1 hour
		//Reading files Feng May 22 30 min
		setSize(811, 821);
		this.el = el;
		addMouseListener(new MouseAdapter(){
      public void mousePressed(MouseEvent me){
        int x = me.getX();
        int y = me.getY();
        //added navigation between screens - Michael Zhou, May 24, 2019 5mins
          if(x>344 && y> 691 && x < 477 && y < 810)
          {
          	el.exit();
          }
      }
		});
		try{
			background = ImageIO.read(new File("Screens/Highscores.jpg"));//Change this picture
		}catch(IOException e){
			e.printStackTrace();
		}
		//Create directory
		File directory = new File(System.getProperty("user.home") + "/slump");
		information = new File(System.getProperty("user.home") + "/slump/highscores.txt");
		try{
			directory.mkdirs();
			if(information.createNewFile())
				JOptionPane.showMessageDialog(null, "A new file has been created for you at " + directory.getAbsolutePath());
	}catch(IOException e){
	}
	}

	public void paint(Graphics g){//Created empy paint method Feng 20min
		g.drawImage(background, 0, 0, 811, 821, null);
		try{
			BufferedReader br = new BufferedReader(new FileReader(information));
			for(int i = 200; i < 600; i+=40){
				String temp = br.readLine();
				if(temp == null)
					break;
				g.setColor(Color.WHITE);
				g.setFont(new Font("SansSerif", Font.PLAIN, 30));
				g.drawString(temp, 20, i);
				g.drawString(br.readLine(), 400, i);
			}
		}catch(IOException e){}

	}
}
