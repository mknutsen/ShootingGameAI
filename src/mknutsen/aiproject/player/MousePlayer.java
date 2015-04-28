package mknutsen.aiproject.player;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Mouse Player tracks a mouses movements and left and right clicks to move,
 * shoot, and shield
 * 
 * @author mknutsen
 *
 */
public class MousePlayer extends Player implements MouseListener,
		MouseMotionListener {
	/**
	 * @param width
	 *            player width
	 * @param height
	 *            player height
	 * @param yLimit
	 *            screen height
	 * @param isPlayer1
	 *            is it player 1?
	 */
	public MousePlayer(int width, int height, int yLimit, boolean isPlayer1) {
		super(width, height, yLimit, isPlayer1);

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(final MouseEvent e) {
		setY(e.getY() - getHeight() / 2);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
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
