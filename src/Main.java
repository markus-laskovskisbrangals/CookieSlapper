import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Main {
	//Variable declaration.
	static String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	static String assetPath = System.getProperty("user.dir") + "\\assets";
	static GameWindow gameWindow;
	static Clip clip;
	
	//Main method
	public static void main(String[] args) {
		runGame();
		
	}
	
	//Method to start a game and it's loop
	static void runGame() {
		File gameFiles = new File(assetPath);
		if(gameFiles.exists()) {
			gameWindow = new GameWindow();
			new GameLoop();
		}else {
			printErrorMessage("Error", "Game files are missing. Please check for assets folder and try again.");
		}
		
		
	}
	
	//Methods to print a different dialogs for the game.
	static void printInfoMessage(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	static void printErrorMessage(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
	}
	
	static int printPromptMessage(String title, String message) {
		return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
	}
	
	//Method to play a sound on the game.
	static void playAudio(String filePath) {
		if (GameUI.audioCheckBox.isSelected()) {
			try{		
				clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(new File(filePath)));
				clip.start();
			}catch(Exception e){
				GameUI.audioCheckBox.setSelected(false);
				printInfoMessage("Missing sound file", "The sound file is missing. To prevent further problems sound has been turned off.");
			}
		} else {
			return;
		}
	}



}
