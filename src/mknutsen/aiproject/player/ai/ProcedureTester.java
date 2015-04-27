package mknutsen.aiproject.player.ai;

import mknutsen.aiproject.player.ai.procedures.movement.AvoidBullets;
import mknutsen.aiproject.player.ai.procedures.shield.ShieldContinuous;

public class ProcedureTester extends AIPlayer {

	public ProcedureTester(int width, int height, int yLimit, boolean player1) {
		super(width, height, yLimit, player1);
	}

	@Override
	public void run() {
		new Thread(new ShieldContinuous(this)).start();
//		new Thread(new ShootSporadically(this)).start();
		new Thread(new AvoidBullets(this)).start();
	}

}
