package mknutsen.aiproject.player.ai.procedures.shield;

import mknutsen.aiproject.player.ai.AIPlayer;
import mknutsen.aiproject.player.ai.procedures.ProcedureArchitype;

/**
 * 
 * @author mknutsen
 *
 */
public abstract class ShieldingArchitype extends ProcedureArchitype {
	/**
	 * Sets up the shield procedure
	 * 
	 * @param player
	 *            player to shield from danger
	 */
	public ShieldingArchitype(AIPlayer player) {
		super(player);
	}

}
