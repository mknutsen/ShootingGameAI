package aiproject.player.ai.procedures;

import aiproject.Config;
import aiproject.player.ai.AIPlayer;

public class ShootContinuous extends ProcedureArchitype {

	public ShootContinuous(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		final double MS_FOR_BULLETS_TO_CROSS = Math.abs(getPlayer().getX()
				- getPlayer().getOpponent().getX())
				* 10 / Config.BULLET_SPEED_PER_10_MS;
		while (isOn()) {
			int futureLoc = (int) Math.floor(getPlayer().getOpponent().getVelocity()
					* MS_FOR_BULLETS_TO_CROSS)
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
