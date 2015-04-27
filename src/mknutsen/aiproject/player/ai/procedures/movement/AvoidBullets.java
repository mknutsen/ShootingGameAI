package mknutsen.aiproject.player.ai.procedures.movement;

import mknutsen.aiproject.game.Bullet;
import mknutsen.aiproject.player.ai.AIPlayer;

public class AvoidBullets extends MovementArchitype {
	private int oldNum;

	public AvoidBullets(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		while (isRoutineOn()) {
			if (getPlayer()
					.findEnemyBullets(getPlayer().getGame().getBullets())
					.size() > oldNum) {
				int space = Bullet.findSpace(
						getPlayer().findEnemyBullets(
								getPlayer().getGame().getBullets()),
						getPlayer().getHeight(), getPlayer().getWindowHeight());
				if (space < 0) {

				} else {
					getPlayer().setGoal(space);
				}
			}
			oldNum = getPlayer().findEnemyBullets(
					getPlayer().getGame().getBullets()).size();
			chill();
		}
		interrupt();
	}
}
