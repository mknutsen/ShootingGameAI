package aiproject.player.ai.procedures;

import aiproject.player.ai.AIPlayer;

public class Avoidance extends ProcedureArchitype {

	public Avoidance(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		while (isOn()) {
			getPlayer().setGoal(getPlayer().getOpponent().getY()
					+ getPlayer().getOpponent().getHeight() / 2 > (getPlayer()
					.getYLimit() / 2) ? 0 : getPlayer().getYLimit());
			chill();
		}
	}
}
