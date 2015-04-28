package mknutsen.aiproject.player;

import java.awt.Color;
import java.awt.Graphics;

import mknutsen.aiproject.Config;
import mknutsen.aiproject.game.Bullet;
import mknutsen.aiproject.game.GameComponent;
import mknutsen.graphicslibrary.GraphicObject;

/**
 * Player can move up and down and fire and shield. it has certain other
 * properties that affect when it can do those.
 * 
 * @author mknutsen
 *
 */
public abstract class Player extends GraphicObject {
	private final int windowHeight;
	private int health = Config.STARTING_HEALTH;
	private GameComponent game;
	private boolean frozen, shield, laserCool, shieldCool;
	private boolean isPlayer1;
	private int goalY;

	/**
	 * @param width
	 *            player width
	 * @param height
	 *            player height
	 * @param yLimit
	 *            window height
	 * @param isPlayer1
	 *            is it player1?
	 */
	public Player(int width, int height, int yLimit, boolean isPlayer1) {
		super(0, 0, width, height, true);
		this.windowHeight = yLimit;
		this.setPlayer1(isPlayer1);
		goalY = getY();
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					int goal = getGoal(), current = getY();
					if (goalY != getY()) {
						if (Math.abs(goal - current) <= Config.MOVE_PER_10_MS) {
							assignY(goal);
						} else {
							assignY(current + (goal - current)
									/ Math.abs(goal - current)
									* Config.MOVE_PER_10_MS);
						}
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}).start();
	}

	/**
	 * makes the player move inside the window if it currently isnt in the
	 * window
	 */
	private void getInside() {
		if (getY() < 0)
			setY(0);
		if (getY() + getHeight() > windowHeight) {
			setY(windowHeight - getHeight());
		}
	}

	/**
	 * Passes in the copy of a current game so it can shoot bullets and stuff
	 * 
	 * @param game
	 *            copy of the game the player is participating in
	 */
	public void setGame(GameComponent game) {
		this.game = game;
	}

	/**
	 * Spawns a bullet at its current location if possible
	 */
	public void spawnBullet() {
		if (!(frozen || laserCool)) {
			frozen = true;
			laserCool = true;

			int dir, startX;
			// FIX ALL THIS
			dir = isPlayer1() ? 1 : -1;
			if (isPlayer1()) {
				startX = getX() + getWidth() + 5;
			} else {
				startX = getX() - Config.BULLET_WIDTH - 5;
			}
			getGame().addBullet(
					new Bullet(startX, getY() + getHeight() / 2, dir, 850));
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(Config.BULLET_STUN_TIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					frozen = false;
				}

			}).start();
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(Config.BULLET_COOLDOWN_TIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					laserCool = false;
				}

			}).start();

		}
	}

	@Override
	public boolean draw(Graphics gr) {
		if (frozen) {
			gr.setColor(Color.BLUE);
		} else {
			gr.setColor(Color.BLACK);
		}
		gr.fillRect(getX(), getY(), getWidth(), getHeight());
		if (shieldCool) {
			gr.setColor(Color.GRAY);
			gr.fillRect(getX() - 10, getY(), 10, getHeight());
			gr.fillRect(getX() + getWidth(), getY(), 10, getHeight());
		} else if (shield) {
			gr.setColor(Color.YELLOW);
			gr.fillRect(getX() - 10, getY(), 10, getHeight());
			gr.fillRect(getX() + getWidth(), getY(), 10, getHeight());
		} else if (laserCool) {
			gr.setColor(Color.RED);
			gr.fillRect(getX(), getY() - 10, getWidth(), 10);
			gr.fillRect(getX(), getY() + getHeight(), getWidth(), 10);

		}
		return true;
	}

	/**
	 * shields if possible
	 */
	public void shield() {
		if (!(frozen || shieldCool)) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					shield = true;
					try {
						Thread.sleep(Config.SHIELD_DURATION);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					shield = false;
					shieldCool = true;
					try {
						Thread.sleep(Config.SHIELD_COOLDOWN_TIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					shieldCool = false;
				}

			}).start();
			new Thread(new Runnable() {

				@Override
				public void run() {
					frozen = true;
					try {
						Thread.sleep(Config.SHIELD_STUN_TIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					frozen = false;
				}

			}).start();
		}
	}

	/**
	 * Triggered if player gets hit so it can lose health and potentially end
	 * the game
	 */
	public void hit() {
		if (!shield) {
			health--;
			new Thread(new Runnable() {

				@Override
				public void run() {
					frozen = true;
					try {
						Thread.sleep(Config.HIT_STUN_TIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					frozen = false;
				}

			}).start();
			if (health < 1) {
				game.end();
			}
		}
	}

	public int getHealth() {
		return health;
	}

	public GameComponent getGame() {
		return game;
	}

	public int getWindowHeight() {
		return windowHeight;
	}

	public boolean isPlayer1() {
		return isPlayer1;
	}

	/**
	 * Decides if it is player 1
	 * 
	 * @param isPlayer1
	 *            boolean if it is player1
	 */
	public void setPlayer1(boolean isPlayer1) {
		this.isPlayer1 = isPlayer1;
	}

	public boolean isFrozen() {
		return frozen;
	}

	public boolean isShield() {
		return shield;
	}

	public boolean isLaserCool() {
		return laserCool;
	}

	public boolean isShieldCool() {
		return shieldCool;
	}

	/**
	 * @return the goal Y value
	 */
	public int getGoal() {
		return goalY;
	}

	/**
	 * Sets the desired Y location and moves there as fast as possible
	 * (indicated in the config)
	 * 
	 * @param goalY
	 *            the goal Y location.
	 */
	public void setY(int goalY) {
		goalY = goalY > 0 ? goalY : 0;
		this.goalY = goalY < windowHeight ? goalY : windowHeight;
	}

	private void assignY(int y) {
		super.setY(y);
		getInside();
	}
}