package mknutsen.aiproject.player.ai.procedures.movement;

import mknutsen.aiproject.player.ai.AIPlayer;
import mknutsen.aiproject.player.ai.procedures.ProcedureArchitype;

/**
 * Abstract class that sets up all movement based procedures
 * @author mknutsen
 *
 */
public abstract class MovementArchitype extends ProcedureArchitype {
	/**
	 * Sets up the movement procedure
	 * 
	 * @param player
	 *            player to move
	 */
	public MovementArchitype(AIPlayer player) {
		super(player);
	}

}
