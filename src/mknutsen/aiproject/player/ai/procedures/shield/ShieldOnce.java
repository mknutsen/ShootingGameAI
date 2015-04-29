package mknutsen.aiproject.player.ai.procedures.shield;

import mknutsen.aiproject.Config;
import mknutsen.aiproject.game.Bullet;
import mknutsen.aiproject.player.ai.AIPlayer;

/**
 * Shields once when the bullet is close and then terminates
 * 
 * @author mknutsen
 *
 */
public class ShieldOnce extends ShieldingArchitype {

	/**
	 * Sets up the shield procedure
	 * 
	 * @param player
	 *            player to shield from danger
	 */
	public ShieldOnce(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		boolean hasNotShielded = false;
		while (!hasNotShielded) {
			for (Bullet bill : getPlayer().findEnemyBullets(
					getPlayer().getGame().getBullets())) {
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
