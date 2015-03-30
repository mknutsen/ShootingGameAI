package aiproject.player.ai.procedures;

import aiproject.player.ai.AIPlayer;

public class Follow extends ProcedureArchitype {

	public Follow(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		while (isOn()) {
			if (Math.abs(getPlayer().getOpponent().getY() - getPlayer().getY()) > getPlayer()
					.getHeight() / 4) {

				getPlayer().setGoal(getPlayer().getOpponent().getY() < (getPlayer().getY()) ? 0
						: getPlayer().getYLimit());
			}else{
				getPlayer().setGoal(getPlayer().getY());
			}
			chill();
		}
	}
}
