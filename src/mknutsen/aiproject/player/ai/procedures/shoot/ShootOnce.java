package mknutsen.aiproject.player.ai.procedures.shoot;

import mknutsen.aiproject.Config;
import mknutsen.aiproject.player.ai.AIPlayer;

/**
 * Shoots only once when the players line up and then quits
 * 
 * @author mknutsen
 *
 */
public class ShootOnce extends ShootingArchitype {
	/**
	 * @param player
	 *            player to manipulate
	 */
	public ShootOnce(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {

		final double MS_FOR_BULLETS_TO_CROSS = Math.abs(getPlayer().getX()
				- getPlayer().getOpponent().getX())
				* 10 / Config.BULLET_SPEED_PER_10_MS;
		int futureLoc = (int) Math.floor(getPlayer().getOpponent()
				.getVelocity() * MS_FOR_BULLETS_TO_CROSS)
				+ getPlayer().getOpponent().getY();
		int shootLoc = getPlayer().getY() + getPlayer().getHeight() / 2;

		while (futureLoc <= shootLoc
				&& futureLoc + getPlayer().getOpponent().getHeight() >= futureLoc) {
			futureLoc = (int) Math.floor(getPlayer().getOpponent()
					.getVelocity() * MS_FOR_BULLETS_TO_CROSS)
					+ getPlayer().getOpponent().getY();
			shootLoc = getPlayer().getY() + getPlayer().getHeight() / 2;
			chill();
		}
		getPlayer().spawnBullet();
		turnRoutineOff();
	}

}
