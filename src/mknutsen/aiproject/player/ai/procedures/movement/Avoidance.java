package mknutsen.aiproject.player.ai.procedures.movement;

import mknutsen.aiproject.player.ai.AIPlayer;

/**
 * Avoids the player by running to the opposite side of the screen
 * 
 * @author mknutsen
 *
 */
public class Avoidance extends MovementArchitype {

	/**
	 * Sets up the movement procedure
	 * 
	 * @param player
	 *            player to move
	 */
	public Avoidance(AIPlayer player) {
		super(player);
	}

	@Override
	public void run() {
		while (isRoutineOn()) {
			getPlayer()
					.setY(getPlayer().getOpponent().getY()
							+ getPlayer().getOpponent().getHeight() / 2 > (getPlayer()
							.getWindowHeight() / 2) ? 0 : getPlayer()
							.getWindowHeight());
			chill();
		}
	}
}