package aiproject.player.ai;

import aiproject.player.ai.procedures.*;

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
