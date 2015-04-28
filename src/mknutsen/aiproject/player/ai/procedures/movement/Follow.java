package mknutsen.aiproject.player.ai.procedures.movement;

import mknutsen.aiproject.player.ai.AIPlayer;

public class Follow extends MovementArchitype {

	public Follow(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		while (isRoutineOn()) {
			if (Math.abs(getPlayer().getOpponent().getY() - getPlayer().getY()) > getPlayer()
					.getHeight() / 4) {

				getPlayer().setY(getPlayer().getOpponent().getY() < (getPlayer().getY()) ? 0
						: getPlayer().getWindowHeight());
			}else{
				getPlayer().setY(getPlayer().getY());
			}
			chill();
		}
	}
}
