package aiproject.player.ai.procedures.shoot;

import aiproject.Config;
import aiproject.player.ai.AIPlayer;

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
