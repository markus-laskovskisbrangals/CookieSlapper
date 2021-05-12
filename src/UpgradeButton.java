import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;
import javax.swing.border.LineBorder;

public class UpgradeButton extends GameButton{
	//Variable declaration
	int price;
	int cookieValue;
	
	//Class constructor
	UpgradeButton(String title, int x, int y, int width, int height, int cookieValue){
		super(title, x, y, width, height);
		this.price = cookieValue * 500;
		this.cookieValue = cookieValue;
		setVisible(false);
		addToList();
		doOnHover();
	}
	
	//Method to add button to list for later use
	void addToList() {
		GameUI.clickerButtons.add(this);
	}
	
	//Method to add an action listener to the button
	void doOnClick() {
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				buyUpgrade();
			}
		});
	}
	
	//Method to buy an upgrade if player has enough cookies
	void buyUpgrade() {
		if(GameUI.cookieAmount >= price) {
			GameUI.cookieAmount -= price;
			GameUI.cookiesPerClick += cookieValue;
			GameUI.cookiesPerClickInfo.setText(GameUI.cookiesPerClick + " cookies per click");
			Main.playAudio(Main.assetPath + "\\upgrade.wav");
		}else {
			rejectPurchase();
;		}
	}
	
	//Method to reject purchase if player does not have enough cookies
	void rejectPurchase() {
		GameUI.warning.setText("Not Enough Cookies!");
		Main.playAudio(Main.assetPath + "\\error.wav");
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameUI.warning.setText("");
			}
			
		};
		Timer timer = new Timer(2000, listener);
		timer.setRepeats(false);
		timer.start();
	}
	
	//Method to add a mouse listener to the button
	void doOnHover() {
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				GameUI.warning.setText("Price: " + price + " cookies");
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				GameUI.warning.setText("");
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
	}
}
