package mknutsen.aiproject.player.ai.procedures.shoot;

import mknutsen.aiproject.Config;
import mknutsen.aiproject.player.ai.AIPlayer;

/**
 * Shoots constatly
 * 
 * @author mknutsen
 *
 */
public class ShootSporadically extends ShootingArchitype {
	/**
	 * @param player
	 *            player to manipulate
	 */
	public ShootSporadically(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		while (isRoutineOn()) {
			getPlayer().spawnBullet();
			try {
				Thread.sleep(Config.BULLET_COOLDOWN_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(Config.BULLET_COOLDOWN_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
