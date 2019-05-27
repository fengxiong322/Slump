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

	public HighScore(){//Added constructor Feng May 22 1 hour
		//Reading files Feng May 22 30 min
		try{
			background = ImageIO.read(new File("platform.png"));//Change this picture
		}catch(IOException e){
			e.printStackTrace();
		}
		//Prompt Feng May 23 30 min
		boolean completed = false;
		path = "";
		try{
			br = new BufferedReader(new FileReader(path));
			completed = true;
		}catch(IOException e){
			e.printStackTrace();
			JDialog dialog = new JDialog();
			JPanel error = new JPanel();
			dialog.setSize(300, 150);
			JLabel prompt = new JLabel("Choose a file path to save your highscores:");
			JTextField textField = new JTextField(20);
			error.add(prompt);
			error.add(textField);
			JButton submit = new JButton("Submit");
			submit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					path = textField.getText();
					try{
						br = new BufferedReader(new FileReader(path));
					}catch(IOException e){
						textField.setText("That path does not exist!");
					}
				}
			});
			error.add(submit);
			dialog.add(error);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		}
	}


	public void paint(Graphics g){//Created empy paint method Feng 20min

	}
}
