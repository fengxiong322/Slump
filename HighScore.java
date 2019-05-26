import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

public class HighScore extends Canvas{
	BufferedImage background;
	String[] lines;
	String path;
	BufferedReader br;

	public HighScore(){
		try{
			background = ImageIO.read(new File("platform.png"));//Change this picture
		}catch(IOException e){
			e.printStackTrace();
		}
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


	public void paint(Graphics g){

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("test");
		frame.setSize(300, 300);
		frame.add(new HighScore());
		frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
	}

}