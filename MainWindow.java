import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;

public class MainWindow extends JFrame implements ActionListener{
	MainMenu menuScreen;
  Levels levelSelector;
  HighScore highscoreMenu;
  Game gameScreen;
  Exit exitWindow;
  
  public MainWindow(){
    super("The Slump");
    Canvas menuScreen = new MainMenu();
    menuScreen.setSize(400, 400);
    add(menuScreen);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }
  
  public void openMenu(){
    //create new object
    //actionlistener
    //add to the JFrame
  }
  
  public void openLevel(){
  
  }
  
  public void openHighScore(){
  
  }
  
  public void actionPerformed(ActionEvent ae){
    //Add conditions for when buttons are pressed.
    //This is mostly for the main menu and exiting the game. Because those cause actions that are not within the canvas
  }
	
  public static void main(String[]args){
    new MainWindow();
  }
  
}
