package mknutsen.aiproject.player.ai.procedures.movement;

import mknutsen.aiproject.game.Bullet;
import mknutsen.aiproject.player.ai.AIPlayer;

/**
 * Avoids all the bullets on the screen.
 * 
 * @author mknutsen
 *
 */
public class AvoidBullets extends MovementArchitype {
	private int oldNum;

	/**
	 * Sets up the movement procedure
	 * 
	 * @param player
	 *            player to move
	 */
	public AvoidBullets(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		while (isRoutineOn()) {
			if (getPlayer()
					.findEnemyBullets(getPlayer().getGame().getBullets())
					.size() > oldNum) {
				int space = Bullet.findSpace(
						getPlayer().findEnemyBullets(
								getPlayer().getGame().getBullets()),
						getPlayer().getHeight(), getPlayer().getWindowHeight());
				if (space < 0) {

				} else {
					getPlayer().setGoalY(space);
				}
			}
			oldNum = getPlayer().findEnemyBullets(
					getPlayer().getGame().getBullets()).size();
			chill();
		}
		interrupt();
	}
}
