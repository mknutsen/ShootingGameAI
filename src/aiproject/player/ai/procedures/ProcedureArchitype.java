package aiproject.player.ai.procedures;

import aiproject.Config;
import aiproject.player.ai.AIPlayer;

public abstract class ProcedureArchitype extends Thread {
	private AIPlayer player;
	private boolean routineIsOn;

	public ProcedureArchitype(AIPlayer player) {
		this.player = (player);
		routineIsOn = true;
	}

	public boolean isRoutineOn() {
		return routineIsOn;
	}

	public void turnRoutineOff() {
		routineIsOn = false;
	}

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