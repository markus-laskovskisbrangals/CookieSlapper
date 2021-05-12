import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SaveGame extends GameButton{

	SaveGame(String title, int x, int y, int width, int height) {
		super(title, x, y, width, height);
	}
	
	//Overwritten parent class method for action when button is clicked
	void doOnClick() {
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				String path = "";
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int option = chooser.showOpenDialog(Main.gameWindow);
				if(option == JFileChooser.APPROVE_OPTION) {
					path = chooser.getSelectedFile().getAbsolutePath();
					saveGameFile(path);
				}
			}
		});
	}
	
	//Method to specify place where to save game file
	void saveGameFile(String path) {
		try {
			File saveFile = new File(path + "\\cookieclicker.sav");
			if(saveFile.createNewFile()) {
				writeToFile(saveFile);
				Main.printInfoMessage("Success", "Game saved successfully!");
			}else {
				askForRewrite(saveFile);
			}
		}catch(IOException e) {
			Main.printErrorMessage("Error", "You can not save your game in this directory!");
		}
		
	}
	
	//Method to write data into save file
	void writeToFile(File file) {
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(String.valueOf(GameUI.cookieAmount) + "\n");
			writer.write(String.valueOf(GameUI.cookiesPerClick) + "\n");
			writer.write(String.valueOf(GameLoop.cookiesPerSecond) + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Method to ask player for overwriting save file if it exists into specified firectory
	void askForRewrite(File pathToSave) {
		int rewritePrompt = Main.printPromptMessage("File Already exists", "Save file already exists in this directory. Do you want to rewrite your save?");
		if(rewritePrompt == JOptionPane.YES_OPTION) {
			writeToFile(pathToSave);
			Main.printInfoMessage("Success", "Game saved successfully!");
		}
		if(rewritePrompt == JOptionPane.NO_OPTION) {
			Main.printErrorMessage("File already exists", "Save file already exists in the directory and changes weren't made.");
		}
	}
}
