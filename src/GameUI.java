import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameUI extends JPanel{
	
	//Variable declaration
	static long cookieAmount = 0;
	static long cookiesPerClick = 1;
	static int buttonWidth = 200;
	static int buttonHeight = 50;
	static int purchaseButtonMarginLeft = 500;
	static int cookieSize = 150;
	static int[] cookieValues = {1, 5, 10, 20, 50, 100, 1000, 5000};
	static JLabel cookieInfo = createLabel(cookieAmount + " cookies", 100, 100, 300, 50, 30);
	static JLabel cookiesPerSecond = createLabel(GameLoop.cookiesPerSecond + " cookies per second", 100, 140, 300, 50, 20);
	static JLabel cookiesPerClickInfo = createLabel(cookiesPerClick + " cookies per click", 100, 165, 300, 50, 20);
	static JLabel warning = GameUI.createLabel("", 600, 50, 250, 50, 25);
	static JCheckBox audioCheckBox = createCheckBox("Enable sound", purchaseButtonMarginLeft + 40, 670, 150, 25);
	static ArrayList<AutoClickButton> autoClickers = new ArrayList<AutoClickButton>();
	static ArrayList<UpgradeButton> clickerButtons = new ArrayList<UpgradeButton>();
	static Icon icon = new ImageIcon(Main.assetPath + "\\cookie.png");
	
	//Class constructor with methods ato add all UI elemnts to the frame
	GameUI(){
		setLayout(null);
		add(cookieInfo);
		add(cookiesPerSecond);
		add(warning);
		add(cookiesPerClickInfo);
		add(audioCheckBox);
		add(new SaveGame("Save Game", 685, 660, buttonWidth - 75, buttonHeight));
		add(new LoadGame("Load Game", 830, 660, buttonWidth - 75, buttonHeight, 0));
		add(createLabel("Auto Clickers", purchaseButtonMarginLeft, 120, 200, 50, 18));
		add(createLabel("Cookies Per Click", purchaseButtonMarginLeft + 250, 120, 200, 50, 18));
		add(new GameButton(icon, 175, 300, cookieSize, cookieSize));
		addButtons();
	}

	//Method to create a new label with text
	public static JLabel createLabel(String text, int x, int y, int width, int height, int fontSize) {
		JLabel label = new JLabel(text, SwingConstants.CENTER);
		label.setFont(new Font("Calibri", Font.BOLD, fontSize));
		label.setBounds(x, y, width, height);
		return label;
	}
	
	//Method to create a chackbox
	private static JCheckBox createCheckBox(String text, int x, int y, int width, int height) {
		JCheckBox box = new JCheckBox(text);
		box.setSelected(true);
		box.setOpaque(false);
		box.setForeground(new Color(50, 50, 50));
		box.setBounds(x, y, width, height);
		return box;
	}
	
	//Method to create many purchase buttons at once
	void addButtons() {
		for(int i = 0; i < cookieValues.length; i++) {
			this.add(new AutoClickButton("Auto Clicker +" + cookieValues[i], purchaseButtonMarginLeft, 170 + i*60, buttonWidth, buttonHeight, cookieValues[i]));
			this.add(new UpgradeButton("+" + cookieValues[i] + " Cookies Per Click", purchaseButtonMarginLeft + 250, 170 + i*60, buttonWidth, buttonHeight, cookieValues[i]));
		}
	}
}
