package aiproject.player.ai.procedures;

import aiproject.game.Bullet;
import aiproject.player.ai.AIPlayer;

public class AvoidBullets extends ProcedureArchitype {
	private int oldNum;

	public AvoidBullets(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		while (isOn()) {
			if (getPlayer()
					.findEnemyBullets(getPlayer().getGame().getBullets())
					.size() > oldNum) {
				int space = Bullet.findSpace(
						getPlayer().findEnemyBullets(
								getPlayer().getGame().getBullets()),
						getPlayer().getHeight(), getPlayer().getYLimit());
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
