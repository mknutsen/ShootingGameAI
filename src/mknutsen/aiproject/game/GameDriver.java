package mknutsen.aiproject.game;

import java.awt.event.KeyEvent;

import mknutsen.aiproject.Config;
import mknutsen.aiproject.player.KeyboardPlayer;
import mknutsen.aiproject.player.MousePlayer;
import mknutsen.aiproject.player.MouseandKeyboard;
import mknutsen.aiproject.player.Player;
import mknutsen.aiproject.player.ai.decisiontree.LeveledDecisionTree;
import mknutsen.graphicslibrary.GraphicsDriver;

/**
 * Uses GraphicsLibrary's GraphicsDriver to run the program with an instructions
 * component, level select component, and a game component.
 * 
 * @author Max Knutsen - mknutse1@umbc.edu
 *
 */
public class GameDriver {
	public static final int WIDTH = 850, HEIGHT = 850;

	public static void main(String[] args) {
		new GraphicsDriver(WIDTH, HEIGHT, new InstructionsComponent(),
				new LevelSelectComponent(), new GameComponent(
						generateTree(true), generateKB(false)),
				new EndComponent()).setVisible(true);
	}

	/**
	 * Runs a keyboard player with all the default settings
	 * 
	 * @param p1
	 *            is player 1
	 * @return the player
	 */
	public static Player generateKB(boolean p1) {
		return new KeyboardPlayer(Config.DEFAULT_PLAYER_WIDTH,
				Config.DEFAULT_PLAYER_HEIGHT, HEIGHT, KeyEvent.VK_UP,
				KeyEvent.VK_DOWN, KeyEvent.VK_Z, KeyEvent.VK_X, p1);
	}

	/**
	 * Runs an AI player with all the default settings
	 * 
	 * @param p1
	 *            is player 1
	 * @return the player
	 */
	public static Player generateTree(boolean p1) {
		return new LeveledDecisionTree(Config.DEFAULT_PLAYER_WIDTH,
				Config.DEFAULT_PLAYER_HEIGHT, HEIGHT, p1);
	}

	/**
	 * Runs a mouse player with all the default settings
	 * 
	 * @param p1
	 *            is player 1
	 * @return the player
	 */
	public static Player generateMouse(boolean p1) {
		return new MousePlayer(Config.DEFAULT_PLAYER_WIDTH,
				Config.DEFAULT_PLAYER_HEIGHT, HEIGHT, p1);
	}

	/**
	 * Runs a mouse and keyboard player with all the default settings
	 * 
	 * @param p1
	 *            is player 1
	 * @return the player
	 */
	public static Player generateBoth(boolean p1) {
		return new MouseandKeyboard(Config.DEFAULT_PLAYER_WIDTH,
				Config.DEFAULT_PLAYER_HEIGHT, HEIGHT, KeyEvent.VK_UP,
				KeyEvent.VK_DOWN, KeyEvent.VK_Z, KeyEvent.VK_X, p1);

	}
}
