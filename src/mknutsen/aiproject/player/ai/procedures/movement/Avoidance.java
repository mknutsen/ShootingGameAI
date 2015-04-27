package mknutsen.aiproject.player.ai.procedures.movement;

import mknutsen.aiproject.player.ai.AIPlayer;

public class Avoidance extends MovementArchitype {

	public Avoidance(AIPlayer player) {
		super(player);
	}
	@Override
	public void run() {
		while (isRoutineOn()) {
			getPlayer().setGoal(getPlayer().getOpponent().getY()
					+ getPlayer().getOpponent().getHeight() / 2 > (getPlayer()
					.getWindowHeight() / 2) ? 0 : getPlayer().getWindowHeight());
			chill();
		}
	}
}