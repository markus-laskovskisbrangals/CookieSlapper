import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;

public class AutoClickButton extends UpgradeButton{
	
	//Class constructor with parameters of parent class
	AutoClickButton(String title, int x, int y, int width, int height, int cookieValue){
		super(title, x, y, width, height, cookieValue);
		super.price = cookieValue * 1000;
		addToList();
		doOnHover();
	}
	
	//Modified parent class method to add button to list for later use
	void addToList() {
		GameUI.autoClickers.add(this);
	}
	
	//Method to check for available cookies and buy an upgrade if it is possible
	void buyUpgrade() {
		if(GameUI.cookieAmount >= price) {
			GameUI.cookieAmount -= price;
			GameLoop.cookiesPerSecond += cookieValue;
			GameUI.cookiesPerSecond.setText(GameLoop.cookiesPerSecond + " cookies per second");
			Main.playAudio(Main.assetPath + "\\upgrade.wav");
			
		}else {
			super.rejectPurchase();
;		}
	}
}
