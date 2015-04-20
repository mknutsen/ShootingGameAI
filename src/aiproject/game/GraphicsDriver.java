package aiproject.game;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import aiproject.Config;
import aiproject.player.KeyboardPlayer;
import aiproject.player.MousePlayer;
import aiproject.player.MouseandKeyboard;
import aiproject.player.Player;
import aiproject.player.ai.decisiontree.NonLeveledTree;

public class GraphicsDriver extends JFrame {

	private static final long serialVersionUID = 1L;
	private int i;
	private final GraphicsComponent[] components;

	public GraphicsDriver(final GraphicsComponent... components) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		i = 0;
		this.components = components;
		setSize(823, 849);
		setVisible(true);
		setLocationRelativeTo(null);
		switchGraphicsComponent(null);
	}

	// Cursor c =
	// Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(""),
	// new Point(this.getX(), this.getY()), "");
	// this.setCursor(c);// THESE LINES SET THE CURSOR TO INVISIBLE

	protected void execute() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

	}

	public static void main(String[] args) {
		new GraphicsDriver(new Instructions(), new GameComponent(
				generateTree(true), generateBoth(false)), new EndComponent())
				.setVisible(true);
		// new GraphicsDriver(new GraphicsComponent(generateMouse(true), new
		// ProcedureTester(Config.DEFAULT_PLAYER_WIDTH,
		// Config.DEFAULT_PLAYER_HEIGHT, 808, false)));
	}

	public static Player generateKB(boolean p1) {
		return new KeyboardPlayer(Config.DEFAULT_PLAYER_WIDTH,
				Config.DEFAULT_PLAYER_HEIGHT, 808, KeyEvent.VK_UP,
				KeyEvent.VK_DOWN, KeyEvent.VK_Z, KeyEvent.VK_X, p1);
	}

	public static Player generateTree(boolean p1) {
		return new NonLeveledTree(Config.DEFAULT_PLAYER_WIDTH,
				Config.DEFAULT_PLAYER_HEIGHT, 808, p1);
	}

	public static Player generateMouse(boolean p1) {
		return new MousePlayer(Config.DEFAULT_PLAYER_WIDTH,
				Config.DEFAULT_PLAYER_HEIGHT, 808, p1);
	}

	public static Player generateBoth(boolean p1) {
		return new MouseandKeyboard(Config.DEFAULT_PLAYER_WIDTH,
				Config.DEFAULT_PLAYER_HEIGHT, 808, KeyEvent.VK_UP,
				KeyEvent.VK_DOWN, KeyEvent.VK_Z, KeyEvent.VK_X, p1);

	}

	private void switchGraphicsComponent(Object[] previouslyReturned) {
		if (i != 0) {
			components[i - 1].setFocusable(false);
			remove(components[i - 1]);
		}
		if (i >= components.length) {
			if (previouslyReturned != null && previouslyReturned.length != 0) {
				i = (int) previouslyReturned[0];
				System.out.println("trying again");
			} else
				execute();
		} else {
			components[i].setPreferredSize(new Dimension(850, 850));
			components[i].setCallback(new CompletionCallback() {

				@Override
				public void execute(Object... returnValue) {
					switchGraphicsComponent(returnValue);
				}

			});
			if (previouslyReturned != null) {
				components[i].takeParameters(previouslyReturned);
			}
			add(components[i]);
			components[i].setFocusable(true);
			pack();
			components[i].requestFocusInWindow();
			setSize(823, 849);
			setVisible(true);
			i++;
		}
	}
}
