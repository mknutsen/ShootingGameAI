package mknutsen.aiproject.player.ai.procedures.movement;

import mknutsen.aiproject.player.ai.AIPlayer;

/**
 * Follows the other player
 * 
 * @author mknutsen
 *
 */
public class Follow extends MovementArchitype {

	/**
	 * Sets up the movement procedure
	 * 
	 * @param player
	 *            player to move
	 */
	public Follow(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		while (isRoutineOn()) {
			if (Math.abs(getPlayer().getOpponent().getY() - getPlayer().getY()) > getPlayer()
					.getHeight() / 4) {

				getPlayer()
						.setGoalY(getPlayer().getOpponent().getY() < (getPlayer().getY()) ? 0 :
								  getPlayer().getWindowHeight());
			} else {
				getPlayer().setGoalY(getPlayer().getY());
			}
			chill();
		}
	}
}
