package aiproject.player.ai.procedures;

import aiproject.Config;
import aiproject.player.ai.AIPlayer;

public abstract class ProcedureArchitype extends Thread {
	private AIPlayer player;
	private boolean on;

	public ProcedureArchitype(AIPlayer player) {
		this.player = (player);
		on = true;
	}

	public boolean isOn() {
		return on;
	}

	public void turnOff() {
		on = false;
	}

	void chill() {
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