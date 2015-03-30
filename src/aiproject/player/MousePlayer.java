package aiproject.player;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MousePlayer extends Player implements MouseListener,
		MouseMotionListener {

	public MousePlayer(int width, int height, int yLimit, boolean isPlayer1) {
		super(width, height, yLimit, isPlayer1);

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		getGame().engaged();
		setGoal(e.getY() - getHeight() / 2);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setGoal(e.getY() - getHeight() / 2);
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
