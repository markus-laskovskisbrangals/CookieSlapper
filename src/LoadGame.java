import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LoadGame extends GameButton {

	static ArrayList<Long> saveData = new ArrayList<Long>();

	LoadGame(String title, int x, int y, int width, int height, int cookiesPerClick) {
		super(title, x, y, width, height);
	}

	//Overwritten parent class method for an action after button is pressed
	void doOnClick() {
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Save files .sav", "sav");
				File saveFilePath;
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setFileFilter(filter);
				int option = chooser.showOpenDialog(Main.gameWindow);
				if (option == JFileChooser.APPROVE_OPTION) {
					saveFilePath = chooser.getSelectedFile().getAbsoluteFile();
					loadSaveFile(saveFilePath);
				}
			}
		});
	}

	//Method to open a save file which are chosen by plater
	void loadSaveFile(File file) {
		if (isFileLoadable(file)) {
			saveData.clear();
			try {
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextLine()) {
					String data = scanner.nextLine();
					System.out.println(data);
					saveData.add(Long.parseLong(data));
				}
				GameUI.cookieAmount = saveData.get(0);
				GameUI.cookiesPerClick = saveData.get(1);
				GameLoop.cookiesPerSecond = saveData.get(2);
			} catch (Exception e) {
				Main.printErrorMessage("Error",
						"Unable to load save file, it might be modified and unreadable for the game.");
			}
		}else {
			Main.printErrorMessage("Error",
					"Unable to load save file, it might be modified and unreadable for the game.");
		}

	}

	//Method which checks if file is readable
	static boolean isFileLoadable(File file) {
		Pattern checker = Pattern.compile("[0-9]");
		int lineCount = 0;
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				Matcher matcher = checker.matcher(line);
				if (matcher.find()) {
					lineCount++;
				} else {
					break;
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (lineCount != 3) {
			return false;
		}
		return true;
	}

}
