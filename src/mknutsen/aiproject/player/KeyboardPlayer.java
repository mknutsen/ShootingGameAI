package mknutsen.aiproject.player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import mknutsen.aiproject.Config;

public class KeyboardPlayer extends Player implements KeyListener {
	private final int upKey, downKey;
	private final int shieldKey;
	private final int shootKey;

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

	private void move(KeyEvent e) {
		if ((getGoal() > getY() && e.getKeyCode() == upKey)
				|| (getGoal() < getY() && e.getKeyCode() == downKey)) {
			setGoal(getY());
		}
		if (Math.abs(getGoal() - getY()) < Config.KB_THRESHOLD) {
			if (e.getKeyCode() == upKey) {
				setGoal(getGoal() - Config.KB_MOVE_PER_CLICK);
			} else if (e.getKeyCode() == downKey) {
				setGoal(getGoal() + Config.KB_MOVE_PER_CLICK);
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
