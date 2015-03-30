package aiproject.game;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import aiproject.Config;
import aiproject.player.KeyboardPlayer;
import aiproject.player.MousePlayer;
import aiproject.player.Player;
import aiproject.player.ai.decisiontree.BasicDecisionTree;

public class GraphicsDriver extends JFrame {

	private static final long serialVersionUID = 1L;

	public GraphicsDriver(final GraphicsComponent component) {
		component.setPreferredSize(new Dimension(850, 850));
		component.setFocusable(true);
		component.requestFocusInWindow();
		add(component);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(823, 849);
		setLocationRelativeTo(null);
		setVisible(true);
		if (!Config.DEBUG)
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (component.complete() == false) {
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					execute();
				}

			}).start();
	}

	// Cursor c =
	// Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(""),
	// new Point(this.getX(), this.getY()), "");
	// this.setCursor(c);// THESE LINES SET THE CURSOR TO INVISIBLE

	protected void execute() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

	}

	public static void main(String[] args) {
		new GraphicsDriver(new GraphicsComponent(generateTree(true),
				generateMouse(false))).setVisible(true);
		// new GraphicsDriver(new GraphicsComponent(generateMouse(true), new
		// ProcedureTester(Config.DEFAULT_PLAYER_WIDTH,
		//			Config.DEFAULT_PLAYER_HEIGHT, 808, false)));
	}

	public static Player generateKB(boolean p1) {
		return new KeyboardPlayer(Config.DEFAULT_PLAYER_WIDTH,
				Config.DEFAULT_PLAYER_HEIGHT, 808, KeyEvent.VK_UP,
				KeyEvent.VK_DOWN, KeyEvent.VK_Z, KeyEvent.VK_X, p1);
	}

	public static Player generateTree(boolean p1) {
		return new BasicDecisionTree(Config.DEFAULT_PLAYER_WIDTH,
				Config.DEFAULT_PLAYER_HEIGHT, 808, p1);
	}

	public static Player generateMouse(boolean p1) {
		return new MousePlayer(Config.DEFAULT_PLAYER_WIDTH,
				Config.DEFAULT_PLAYER_HEIGHT, 808, p1);
	}

}
