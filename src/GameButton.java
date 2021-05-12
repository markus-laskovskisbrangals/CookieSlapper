import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

public class GameButton extends JButton{
	//Class variable declaration
	Icon icon;
	String title;
	int x;
	int y;
	int width;
	int height;
	
	//Constructor to make a button with text
	GameButton(String title, int x, int y, int width, int height){
		setupButton(title, x, y, width, height);
		doOnClick();
	}
	
	//Constructor to make a button with a icon
	GameButton(Icon icon, int x, int y, int width, int height){
		setupButtonWithIcon(icon, x, y, width, height);
		doOnClick();
	}
	
	//Default function for all buttons
	void doOnClick() {
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				spawnCookie();
				//Sound is being played and cookie is added to total cookie amount
				Main.playAudio(Main.assetPath + "\\crack.wav");
				GameUI.cookieAmount += GameUI.cookiesPerClick;
				GameUI.cookieInfo.setText(GameUI.cookieAmount + " cookies");
			}
			
		});
	}
	
	//Method to setup a button with text
	void setupButton(String title, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.setText(title);
		setBounds(x, y, width, height);
	}
	
	//Method to setup a button with a icon
	void setupButtonWithIcon(Icon icon, int x, int y, int width, int height) {
		this.icon = icon;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.setIcon(icon);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setBackground(null);
		this.setOpaque(false);
		setBounds(x, y, width, height);
	}
	
	//Generates a cookie on the screen at random position
	void spawnCookie() {
		GameWindow.gamePanel.add(new Cookie());
		GameWindow.gamePanel.repaint();
	}

}
