import javax.swing.JFrame;

public class GameWindow extends JFrame{
	static String title = "Cookie Slapper";
	static GameUI gamePanel;
	static final int WIDTH = 1024;
	static final int HEIGHT = 768;
	
	GameWindow(){
		gamePanel = new GameUI();
		setTitle(title);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		add(gamePanel);
		setVisible(true);
	}
}
