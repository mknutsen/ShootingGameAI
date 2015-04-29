package mknutsen.aiproject.player.ai.procedures.shoot;

import mknutsen.aiproject.player.ai.AIPlayer;
import mknutsen.aiproject.player.ai.procedures.ProcedureArchitype;

/**
 * Handles shooting procedures
 * 
 * @author mknutsen
 *
 */
public abstract class ShootingArchitype extends ProcedureArchitype {
	/**
	 * @param player
	 *            player to manipulate
	 */
	public ShootingArchitype(AIPlayer player) {
		super(player);
	}

}
