package mknutsen.aiproject.player.ai.procedures.shoot;

import mknutsen.aiproject.Config;
import mknutsen.aiproject.player.ai.AIPlayer;

public class ShootSporadically extends ShootingArchitype {

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
		}
	}

}
