package aiproject.player.ai.decisiontree;

import aiproject.player.ai.procedures.ProcedureArchitype;

public class Action extends Thread {
	private ProcedureArchitype action;

	public Action() {
	}

	public Action(ProcedureArchitype action) {
		this.action = action;
	}

	public void turnOff() {
		if (action != null)
			action.turnOff();
	}

	public void setAction(ProcedureArchitype action) {
		action.turnOff();
		this.action = action;
	}

	public void run() {
		action.run();
	}

	public boolean isRunning() {
		return action.isOn();
	}
	
}
