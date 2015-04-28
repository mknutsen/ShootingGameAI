package mknutsen.aiproject.player.ai.procedures;

import mknutsen.aiproject.Config;
import mknutsen.aiproject.player.ai.AIPlayer;

/**
 * A procedure takes in a player and acts upon it
 * 
 * @author Max Knutsen - mknutse1@umbc.edu
 *
 */
public abstract class ProcedureArchitype extends Thread {
	private AIPlayer player;
	private boolean routineIsOn;

	/**
	 * @param player
	 *            player manipulate
	 */
	public ProcedureArchitype(AIPlayer player) {
		this.player = (player);
		routineIsOn = true;
	}

	/**
	 * @return whether or not the procedure should continue to run
	 */
	public boolean isRoutineOn() {
		return routineIsOn;
	}

	/**
	 * Signals that routine should terminate
	 * 
	 * @return whether or not the routine terminated
	 */
	public boolean turnRoutineOff() {
		routineIsOn = false;
		return true;
	}

	/**
	 * Takes a break between procedure runs
	 */
	protected void chill() {
		try {
			Thread.sleep(Config.AI_CHILL_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public AIPlayer getPlayer() {
		return player;
	}
}