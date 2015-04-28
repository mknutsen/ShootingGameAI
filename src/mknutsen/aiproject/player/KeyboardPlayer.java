package mknutsen.aiproject.player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import mknutsen.aiproject.Config;

/**
 * Player that uses the keyboard for inputs
 * 
 * @author mknutsen
 *
 */
public class KeyboardPlayer extends Player implements KeyListener {
	private final int upKey, downKey;
	private final int shieldKey;
	private final int shootKey;

	/**
	 * keys are using the KeyEvent key codes
	 * 
	 * @param width
	 *            width of the player
	 * @param height
	 *            height of the player
	 * @param yLimit
	 *            window height
	 * @param upKey
	 *            the key to move up
	 * @param downKey
	 *            the key to move down
	 * @param shootKey
	 *            the key to shoot
	 * @param shieldKey
	 *            the key to shield
	 * @param isPlayer1
	 *            boolean indicating whether it is player 1 or player 2
	 */
	public KeyboardPlayer(int width, int height, int yLimit, int upKey,
			int downKey, int shootKey, int shieldKey, boolean isPlayer1) {
		super(width, height, yLimit, isPlayer1);
		this.shieldKey = shieldKey;
		this.shootKey = shootKey;
		this.upKey = upKey;
		this.downKey = downKey;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		getGame().engaged();
		move(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		getGame().engaged();
		move(e);
	}

	/**
	 * Moves a certain amount depending on the key
	 * 
	 * @param e
	 *            key event
	 */
	private void move(KeyEvent e) {
		if ((getGoal() > getY() && e.getKeyCode() == upKey)
				|| (getGoal() < getY() && e.getKeyCode() == downKey)) {
			setY(getY());
		}
		if (Math.abs(getGoal() - getY()) < Config.KB_THRESHOLD) {
			if (e.getKeyCode() == upKey) {
				setY(getGoal() - Config.KB_MOVE_PER_CLICK);
			} else if (e.getKeyCode() == downKey) {
				setY(getGoal() + Config.KB_MOVE_PER_CLICK);
			}
		}
		if (e.getKeyCode() == shootKey) {
			spawnBullet();
		}
		if (e.getKeyCode() == shieldKey) {
			shield();
		}
	}
}
