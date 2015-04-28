package mknutsen.aiproject.player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import mknutsen.aiproject.Config;

/**
 * uses mouse and keyboard to be the player
 * 
 * @author mknutsen
 */
public class MouseandKeyboard extends Player implements KeyListener,
		MouseListener, MouseMotionListener {
	private final int upKey, downKey;
	private final int shieldKey;
	private final int shootKey;

	/**
	 * keys are using the KeyEvent key codes
	 * 
	 * @param width
	 *            player width
	 * @param height
	 *            playr height
	 * @param yLimit
	 *            window height
	 * @param upKey
	 *            key to move up
	 * @param downKey
	 *            key to move down
	 * @param shootKey
	 *            key to shoot
	 * @param shieldKey
	 *            key to shield
	 * @param isPlayer1
	 *            is player 1 boolean
	 */
	public MouseandKeyboard(int width, int height, int yLimit, int upKey,
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

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(final MouseEvent e) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				getGame().engaged();
				setY(e.getY() - getHeight() / 2);

			}

		}).start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// setGoal(e.getY() - getHeight() / 2);
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		getGame().engaged();
		if (e.getButton() == 1)
			spawnBullet();
		if (e.getButton() == 3) {
			shield();
		}
	}
}