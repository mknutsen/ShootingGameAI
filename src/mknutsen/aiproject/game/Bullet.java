package mknutsen.aiproject.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

import mknutsen.aiproject.Config;
import mknutsen.graphicslibrary.GraphicObject;

/**
 * Bullet is a graphicobject that moves horizontally at a preset rate.
 * 
 * @author mknutsen
 *
 */
public class Bullet extends GraphicObject implements Comparable<GraphicObject> {
	private int movePer10Ms;
	private final int windowWidth;
	private int dir;

	public Bullet(int x, int y, int dir, final int windowWidth) {
		super(x, y, Config.BULLET_WIDTH, Config.BULLET_HEIGHT, true);
		this.windowWidth = windowWidth;
		this.dir = dir;
		movePer10Ms = dir * Config.BULLET_SPEED_PER_10_MS;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (getX() > 0 && getX() < windowWidth) {
					setX(getX() + movePer10Ms);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}).start();
	}

	@Override
	public void setY(int y) {

	}

	@Override
	public boolean draw(Graphics gr) {
		if (getX() < windowWidth && getX() > 0) {
			gr.setColor(Color.RED);
			gr.fillRect(getX(), getY(), getWidth(), getHeight());
			return true;
		} else
			return false;
	}

	public int getDir() {
		return dir;
	}

	/**
	 * Sorts a list of bullets
	 * 
	 * @param bullets
	 *            list of bullets
	 * @return list of bullets in vertical ascending order
	 */
	public static List<Bullet> sort(List<Bullet> bullets) {
		if (bullets == null || bullets.size() == 0) {
			return bullets;
		}
		Bullet[] bullet = new Bullet[bullets.size()];
		for (int i = 0; i < bullet.length; i++) {
			bullet[i] = bullets.get(i);
		}

		Arrays.sort(bullet);
		return Arrays.asList(bullet);
	}

	/**
	 * uses the sort function to find an opening in the bullets of a certain
	 * size
	 * 
	 * @param bullets
	 *            list of bullets
	 * @param height
	 *            desired height of the opening
	 * @param windowHeight
	 *            bottom of the window
	 * @return -1 if no opening, location of the top of the opening otherwise
	 */
	public static int findSpace(List<Bullet> bullets, int height,
			int windowHeight) {
		if (bullets.isEmpty())
			return 0;
		bullets = Bullet.sort(bullets);
		if (bullets.get(0).getY() > height) {
			return 0;
		}
		for (int i = 0; i < bullets.size() - 1; i++) {
			if (bullets.get(i + 1).getY() - bullets.get(i).getY()
					+ bullets.get(i).getHeight() > height) {
				return bullets.get(i).getY() + bullets.get(i).getHeight();
			}
		}
		if (windowHeight + height - bullets.get(bullets.size() - 1).getY()
				+ bullets.get(bullets.size() - 1).getHeight() > height) {
			return windowHeight;
		}
		return -1;
	}

	@Override
	public String toString() {
		return "[" + getX() + "," + getY() + "]";
	}

	/**
	 * @param other
	 *            other graphic object
	 * @return 1 if this obj has a > y than the other, -1 otherwise
	 */
	public int compareTo(GraphicObject other) {
		return other.getY() < getY() ? 1 : -1;
	}
}
