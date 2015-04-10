package aiproject.player.ai.procedures.shield;

import aiproject.Config;
import aiproject.game.Bullet;
import aiproject.player.ai.AIPlayer;

public class ShieldOnce extends ShieldingArchitype {

	public ShieldOnce(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		boolean hasNotShielded = false;
		while (!hasNotShielded) {
			for (Bullet bill : getPlayer().findEnemyBullets(getPlayer().getGame()
					.getBullets())) {
				int playerOuter = getPlayer().isPlayer1() ? getPlayer().getX()
						+ getPlayer().getWidth() : getPlayer().getX();
				if (getPlayer().isInside(getPlayer().getX(), bill.getY())
						&& Math.abs(bill.getX() - playerOuter) < Config.BULLET_SPEED_PER_10_MS
								* Config.SHIELD_MULTIPLIER_FOR_SAFETY) {
					getPlayer().shield();
					hasNotShielded = true;
				}
			}
			chill();
		}
		turnRoutineOff();
	}
}
