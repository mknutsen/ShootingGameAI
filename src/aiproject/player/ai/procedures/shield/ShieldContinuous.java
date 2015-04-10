package aiproject.player.ai.procedures.shield;

import java.awt.Graphics;

import aiproject.Config;
import aiproject.game.Bullet;
import aiproject.game.GraphicObject;
import aiproject.player.ai.AIPlayer;

public class ShieldContinuous extends ShieldingArchitype {

	public ShieldContinuous(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		while (isRoutineOn()) {
			for (Bullet bill : getPlayer().findEnemyBullets(getPlayer().getGame()
					.getBullets())) {
				int playerOuter = getPlayer().isPlayer1() ? getPlayer().getX()
						+ getPlayer().getWidth() : getPlayer().getX();
				if (getPlayer().isInside(new GraphicObject(getPlayer().getX(), bill
						.getY(), Config.BULLET_WIDTH, Config.BULLET_HEIGHT,
						false) {

					@Override
					public boolean draw(Graphics gr) {
						return false;
					}

				})
						&& Math.abs(bill.getX() - playerOuter) < Config.BULLET_SPEED_PER_10_MS
								* Config.SHIELD_MULTIPLIER_FOR_SAFETY) {
					getPlayer().shield();
				}
			}
			chill();
		}
	}
}
