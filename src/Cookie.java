
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Cookie extends JLabel{
	
	//Variable declaration
	static Icon cookieIcon = new ImageIcon(Main.assetPath + "\\cookie-small.png");
	static int randomX;
	
	//Class constructor which creates cookie on the screen
	Cookie(){
		this.setIcon(cookieIcon);
		randomX = (int)(Math.random() * 900) + 1;
		this.setBounds(randomX, 20, 100, 100);
		dropCookie(this);
	}
	
	//Method which makes a cookie fall animation in a new thread
	void dropCookie(JLabel cookie) {
		new Thread(new Runnable() {
			int distance = 10;
			@Override
			public void run() {
				while(distance < GameWindow.HEIGHT + 50) {
					try {
						cookie.setLocation(cookie.getX(), distance);
						distance++;
						Thread.sleep(5);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				GameWindow.gamePanel.remove(cookie);
				GameWindow.gamePanel.repaint();
			}
		}).start();
	}

}
