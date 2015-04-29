package mknutsen.aiproject.player.ai.procedures.shoot;

import mknutsen.aiproject.Config;
import mknutsen.aiproject.player.ai.AIPlayer;

/**
 * Constistantly shoots whenever the players sign up
 * 
 * @author mknutsen
 *
 */
public class ShootContinuous extends ShootingArchitype {
	/**
	 * @param player
	 *            player to manipulate
	 */
	public ShootContinuous(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		final double MS_FOR_BULLETS_TO_CROSS = Math.abs(getPlayer().getX()
				- getPlayer().getOpponent().getX())
				* 10 / Config.BULLET_SPEED_PER_10_MS;
		while (isRoutineOn()) {
			int futureLoc = (int) Math.floor(getPlayer().getOpponent()
					.getVelocity() * MS_FOR_BULLETS_TO_CROSS)
					+ getPlayer().getOpponent().getY();
			int shootLoc = getPlayer().getY() + getPlayer().getHeight() / 2;

			if (futureLoc <= shootLoc
					&& futureLoc + getPlayer().getOpponent().getHeight() >= shootLoc) {
				getPlayer().spawnBullet();
			}
			chill();
		}
	}
}
