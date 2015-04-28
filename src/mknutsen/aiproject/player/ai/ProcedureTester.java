package mknutsen.aiproject.player.ai;

import mknutsen.aiproject.player.ai.procedures.movement.AvoidBullets;
import mknutsen.aiproject.player.ai.procedures.shield.ShieldContinuous;

/**
 * Used to test procedures
 * 
 * @author Max Knutsen - mknutse1@umbc.edu
 *
 */
public class ProcedureTester extends AIPlayer {
	/**
	 * @param width
	 *            player width
	 * @param height
	 *            player height
	 * @param yLimit
	 *            window height
	 * @param player1
	 *            is it player 1?
	 */
	public ProcedureTester(int width, int height, int yLimit, boolean player1) {
		super(width, height, yLimit, player1);
	}

	@Override
	public void run() {
		new Thread(new ShieldContinuous(this)).start();
		// new Thread(new ShootSporadically(this)).start();
		new Thread(new AvoidBullets(this)).start();
	}

}
