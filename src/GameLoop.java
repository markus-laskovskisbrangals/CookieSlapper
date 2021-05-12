import java.awt.Color;

import javax.swing.JOptionPane;

//A game loop class which runs in a new thread
public class GameLoop implements Runnable{
	//Variable declaration
	static long cookiesPerSecond = 0;
	static int[] cookieLevels = {0, 100, 420, 666, 1000, 10000, 1000000};
	static Color[] backgroundColors = {Color.decode("#3a79de"), Color.decode("#2c5aa3"), Color.decode("#009924"), 
			Color.decode("#c21000"), Color.decode("#e3cc00"), Color.decode("#94e300"), Color.decode("#00e394")};
	
	//Class constructor with methods to run
	GameLoop(){
		GameUI.autoClickers.get(0).setVisible(true);
		GameUI.clickerButtons.get(0).setVisible(true);
		run();
	}

	//Run method which is built in thread class and are executed
	@Override
	public void run() {
		//Game loop
		while(true) {
			try {
				unlockButton();
				setBackground((int) GameUI.cookieAmount);
				GameUI.cookieAmount += cookiesPerSecond;
				GameUI.cookieInfo.setText(GameUI.cookieAmount + " cookies");
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//Method to unlock upgrade buttons if player collects enough cookies
	static void unlockButton() {
		for(AutoClickButton button : GameUI.autoClickers) {
			if(GameUI.cookieAmount >= button.price) {
				button.setVisible(true);
			}
		}
		for(UpgradeButton button : GameUI.clickerButtons) {
			if(GameUI.cookieAmount >= button.price) {
				button.setVisible(true);
			}
		}
	}
	
	//Method to set a background color if player collects certain amount of cookies
	static void setBackground(int cookieAmount) {
		for(int i = 0; i < cookieLevels.length; i++) {
			if(GameUI.cookieAmount >= cookieLevels[i]) {
				GameWindow.gamePanel.setBackground(backgroundColors[i]);
			}
		}
	}
}
