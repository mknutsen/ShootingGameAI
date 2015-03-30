package aiproject.player.ai.procedures;

import aiproject.Config;
import aiproject.player.ai.AIPlayer;

public class ShootSporadically extends ProcedureArchitype {

	public ShootSporadically(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		while (isOn()) {
			getPlayer().spawnBullet();
			try {
				Thread.sleep(Config.BULLET_COOLDOWN_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
